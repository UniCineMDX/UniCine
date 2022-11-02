package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    @Autowired
    private ClienteRepo clienteRepo;
    private PeliculaRepo peliculaRepo;
    private FuncionRepo funcionRepo;
    private CuponRepo cuponRepo;
    private CuponClienteRepo cuponClienteRepo;
    private ConfiteriaRepo confiteriaRepo;
    private CompraRepo compraRepo;
    private EntradaRepo entradaRepo;
    private CompraConfiteriaRepo compraConfiteriaRepo;
    private CiudadRepo ciudadRepo;
    private TeatroRepo teatroRepo;


    private EmailServicio emailServicio;


    public ClienteServicioImpl(CiudadRepo ciudadRepo, TeatroRepo teatroRepo, ClienteRepo clienteRepo, PeliculaRepo peliculaRepo, FuncionRepo funcionRepo, CuponRepo cuponRepo, CuponClienteRepo cuponClienteRepo, ConfiteriaRepo confiteriaRepo, CompraRepo compraRepo, EntradaRepo entradaRepo, CompraConfiteriaRepo compraConfiteriaRepo, EmailServicio emailServicio) {

        this.clienteRepo = clienteRepo;
        this.peliculaRepo = peliculaRepo;
        this.funcionRepo = funcionRepo;
        this.cuponRepo = cuponRepo;
        this.cuponClienteRepo = cuponClienteRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.compraRepo = compraRepo;
        this.entradaRepo = entradaRepo;
        this.compraConfiteriaRepo = compraConfiteriaRepo;
        this.emailServicio = emailServicio;
        this.ciudadRepo = ciudadRepo;
        this.teatroRepo = teatroRepo;

    }


    @Override
    public Cliente login(String correo, String password) throws Exception {
        Cliente clienteEncontrado = clienteRepo.findByCorreoAndPassword(correo, password);

        if (clienteEncontrado == null) {
            throw new Exception("El correo o la contraseña son incorrectos");
        }

        return clienteEncontrado;
    }


    //Metodo para buscar una pelicula por el genero

    public List<Pelicula> buscarPeliculaPorGenero(Genero genero) throws Exception {
        List<Pelicula> peliculaGuardada = peliculaRepo.obtenerPeliculasPorGenero(genero);

        if (peliculaGuardada.isEmpty()) {
            throw new Exception("La pelicula NO EXISTE");
        }

        return peliculaGuardada;

    }

    /**
     * Este metodo permite registrar un cliente
     */
    @Override
    public Cliente registrarCliente(Cliente cliente) throws Exception {

        Cliente clienteCorreo = clienteRepo.findByCorreo(cliente.getCorreo());
        Cliente clienteCedula = clienteRepo.findByCedula(cliente.getCedula());

        if (clienteCedula != null) {
            throw new Exception("Ya existe un cliente registrado con numero de cedula " + cliente.getCedula());
        }
        if (clienteCorreo != null) {
            throw new Exception("Ya existe un cliente registrado con el correo " + cliente.getCorreo());
        }

        Cliente clienteGuardado = clienteRepo.save(cliente);
        //emailServicio.enviarEmail("Registro en unicine", "Hola, debe ir al siguiente enlace para activar la cuenta: ....", cliente.getCorreo());

        return clienteGuardado;
    }

    /**
     * Este metodo permite obtener un cliente atraves de su cedula
     */
    @Override
    public Cliente obtenerClientePorCedula(String cedula) throws Exception {

        Cliente clienteEncontrado = clienteRepo.findByCedula(cedula);

        if (clienteEncontrado == null) {
            throw new Exception("No existe un cliente con numero de cedula " + cedula);
        }

        return clienteEncontrado;
    }

    /**
     * Este metodo permite actualizar el nombre del cliente atraves de su cedula
     */
    @Override
    public Cliente actualizarCliente(Cliente cliente) throws Exception {

        Cliente clienteEncontrado = clienteRepo.findByCedula(cliente.getCedula());
        Cliente clienteCorreo = clienteRepo.findByCorreo(cliente.getCorreo());

        if (clienteEncontrado == null) {
            throw new Exception("No existe un cliente con numero de cedula " + cliente.getCedula());
        }
        if (clienteCorreo != null && !clienteCorreo.getCorreo().equals(cliente.getCorreo())) {
            throw new Exception("Ya existe un cliente registrado con este correo " + cliente.getCorreo());
        }

        Cliente clienteGuardado = clienteRepo.save(cliente);

        return clienteGuardado;
    }

    /**
     * Este metodo permite eliminar un cliente atraves de su cedula
     */
    @Override
    public void eliminarCliente(String cedulaCliente) throws Exception {

        Cliente clienteEncontrado = clienteRepo.findByCedula(cedulaCliente);

        if (clienteEncontrado == null) {
            throw new Exception("No existe un cliente con cedula " + cedulaCliente);
        }

        clienteRepo.delete(clienteEncontrado);
    }

    /**
     * Este metodo permite listar los clientes
     */
    @Override
    public List<Cliente> listarClientes() throws Exception {

        List<Cliente> lisaClientes = clienteRepo.findAll();

        if (lisaClientes.isEmpty()) {
            throw new Exception("No existen clientes registrado");
        }
        return lisaClientes;
    }

    /**
     * Este metodo permite listar los cupones de un cliente atraves de la cedula
     */
    @Override
    public List<CuponCliente> listarCuponesCliente(String cedulaCliente) throws Exception {

        List<CuponCliente> cuponesCliente = null;
        Cliente clienteEncontrado = clienteRepo.findByCedula(cedulaCliente);

        if (clienteEncontrado == null) {
            throw new Exception("No existe un cliente con numero de cedula " + cedulaCliente);
        }

        cuponesCliente = cuponClienteRepo.obtenerCuponesNoUsados(cedulaCliente, EstadoCupon.SIN_USAR);

        return cuponesCliente;
    }

    /**
     * Este metodo permite ver el historial de compras de un cliente atraves de la cedula
     */
    @Override
    public List<Compra> historialCompras(String cedulaCliente) throws Exception {

        Cliente clienteEncontrado = clienteRepo.findByCedula(cedulaCliente);

        if (clienteEncontrado == null) {
            throw new Exception("No existe un cliente con numero de cedula " + cedulaCliente);
        }
        if (clienteEncontrado.getCompras().isEmpty()) {
            throw new Exception("El cliente no ha realizado ninguna compra");
        }

        return clienteEncontrado.getCompras();
    }

    /**
     * Este metodo permite ver el historial de compras redimidas de un cliente atraves de la cedula
     */
    @Override
    public List<Compra> historialComprasRedimidas(String cedulaCliente) throws Exception {

        List<Compra> listaComprasRedimidas = null;
        List<Compra> listadoCompras = historialCompras(cedulaCliente);

        if (listadoCompras.isEmpty()) {
            throw new Exception("El cliente con cedula " + cedulaCliente + " no ha realizado ninguna compra");
        }
        for (Compra compra : listadoCompras) {
            LocalDate fecha1 = compra.getFuncion().getHorario().getFechaFin();
            LocalDate fecha2 = LocalDate.now();

            if (fecha1.compareTo(fecha2) < 0) {
                listaComprasRedimidas.add(compra);
            }
        }
        return listaComprasRedimidas;
    }

    /**
     * Este metodo permite ver el historial de compras no redimidas de un cliente atraves de la cedula
     */
    @Override
    public List<Compra> historialCompraNoRedimidas(String cedulaCliente) throws Exception {
        List<Compra> listaComprasNoRedimidas = null;
        List<Compra> listadoCompras = historialCompras(cedulaCliente);

        if (listadoCompras.isEmpty()) {
            throw new Exception("El cliente con cedula " + cedulaCliente + " no ha realizado ninguna compra");
        }
        for (Compra compra : listadoCompras) {
            LocalDate fecha1 = compra.getFuncion().getHorario().getFechaFin();
            LocalDate fecha2 = LocalDate.now();

            if (fecha1.compareTo(fecha2) == 1) {
                listaComprasNoRedimidas.add(compra);
            }
        }
        return listaComprasNoRedimidas;
    }

    @Override
    public Cupon obtenerCuponSeleccionado(Integer codigo) throws Exception {

        Cupon cupon = cuponRepo.findByCodigo(codigo);

        if(cupon == null){
            throw new Exception("No existe un cupon con codigo "+codigo);
        }

        return cupon;
    }

    @Override
    public boolean cambiarContraseña(String correo, String passwordNueva) throws Exception {

        Cliente clienteCorreo = clienteRepo.findByCorreo(correo);
        boolean centinela = false;

        if (clienteCorreo == null) {
            throw new Exception("El cliente con el correo " + correo + " no existe");
        } else {
            clienteCorreo.setPassword(passwordNueva);
            clienteRepo.save(clienteCorreo);
            centinela = true;
        }
        return centinela;
    }

    @Override
    public void solicitarCambiarContraseña(String correo) throws Exception {

        emailServicio.enviarEmail("Cambio de contraseña", "Hola, debe ir al siguiente enlace para cambiar la contraseña: ....", correo);

    }


    //Metodo para buscar una pelicula por el nombre


    public List<Pelicula> buscarPeliculaPorNombre(String nombre) throws Exception {
        List<Pelicula> peliculaGuardada = (List<Pelicula>) peliculaRepo.findByNombre(nombre);

        return peliculaGuardada;
    }


    @Override
    public Compra realizarCompra(Cliente cliente, List<Entrada> entradas, List<CompraConfiteria> compraConfiterias, MedioPago medioPago, Cupon cupon, Funcion funcion) throws Exception {
        return null;
    }


    @Override
    public boolean validarPago() {
        return false;
    }

    @Override
    public List<Entrada> crearEntradas(List<String> filasColumnas) throws Exception {

        List<Entrada> listaEntradas = null;

        if (filasColumnas.isEmpty()) {
            throw new Exception("No se ha seleccionado ninguna silla");
        }

        for (String filaColumna : filasColumnas) {
            Entrada entrada = new Entrada();
            entrada.setColumna(Integer.parseInt(filaColumna.charAt(0) + ""));
            entrada.setFila(Integer.parseInt(filaColumna.charAt(1) + ""));
            entrada.setPrecio(5000.0);
        }

        return listaEntradas;
    }

    @Override
    public Compra realizarCompraConfiteria() {
        return null;
    }

    @Override
    public List<CompraConfiteria> crearComprasConfiteria(List<List<Integer>> confiterias) throws Exception {

        List<CompraConfiteria> compraConfiterias = null;

        if(confiterias.isEmpty()){
           return null;
        }

        for (List<Integer> confi: confiterias) {

            CompraConfiteria compraConfiteria = null;
            Confiteria confiteria = confiteriaRepo.findByCodigo(confi.get(0));
            if(confiteria == null){
                throw new Exception("No existe una confiteria concodigo "+confi.get(0));
            }
            compraConfiteria.setConfiteria(confiteria);
            compraConfiteria.setUnidades(confi.get(1));
            compraConfiteria.setPrecio(confiteria.getPrecio()*compraConfiteria.getUnidades());
            compraConfiterias.add(compraConfiteria);
        }
        return compraConfiterias;
    }


    @Override
    public List<Pelicula> listarPeliculas() throws Exception {
        List<Pelicula> listarPeliculas = peliculaRepo.findAll();

        if(listarPeliculas.isEmpty()){
           throw new Exception("No existe peliculas creadas");
        }

        return listarPeliculas;
    }

    @Override
    public HashMap<String, Boolean> listaSillasFuncion(Integer codigoFuncion) throws Exception {

        HashMap<String, Boolean> sillas = null;
        Funcion funcion = funcionRepo.findByCodigo(codigoFuncion);

        if(funcion == null){
            throw new Exception("NO existe una funcion con codigo "+codigoFuncion);
        }

        Integer filas = funcion.getSala().getDistribucionSilla().getFilas();
        Integer columnas = funcion.getSala().getDistribucionSilla().getColumnas();

        if(filas < 0 && columnas < 0){
            throw new Exception("El numero de filas y columas es incorrecto");
        }

        for (int i = 1; i == filas; i++) {
            for (int j = 1; j == columnas; j++) {
                String codigo = i+j+"";
                sillas.put(codigo,false);
            }
        }

        List<Entrada> entradasFuncion = peliculaRepo.obtenerEntradasFuncion(funcion.getCodigo());

        if(!entradasFuncion.isEmpty()){
            for (Entrada entrada:entradasFuncion) {
                String llave = entrada.getFila()+entrada.getColumna()+"";
                sillas.put(llave,true);
            }
        }

        return sillas;
    }

    @Override
    public List<Pelicula> listarPeliculasCiudad(Integer codigoCiudad) throws Exception {

        List<Pelicula> listaPeliculasCiudad = peliculaRepo.obtenerPeliculasCiudad(codigoCiudad);
        Ciudad ciudad = ciudadRepo.findByCodigo(codigoCiudad);

        if(ciudad == null){
            throw new Exception("No existe una ciudad con codigo "+codigoCiudad);
        }
        if(listaPeliculasCiudad.isEmpty()){
            throw new Exception("No existen peliculas para esta ciudad");
        }
        return listaPeliculasCiudad;
    }


    @Override
    public List<Pelicula> buscarPeliculaNombre(String nombre) throws Exception {
        List<Pelicula> listarPeliculasNombre = peliculaRepo.buscarPeliculaNombre(nombre);

        if(listarPeliculasNombre.isEmpty()){
            throw new Exception("No existe peliculas creadas con el nombre "+nombre);
        }
        return listarPeliculasNombre;
    }

    @Override
    public List<Pelicula> buscarPeliculaEstado(EstadoPelicula estadoPelicula) throws Exception {
        List<Pelicula> listarPeliculasEstado = peliculaRepo.obtenerPeliculasPorEstado(estadoPelicula);

        if(listarPeliculasEstado.isEmpty()){
            throw new Exception("No existe peliculas creadas con estado "+estadoPelicula.toString());
        }
        return listarPeliculasEstado;
    }

    @Override
    public List<Pelicula> listarPeliculasCiudadTeatro(Integer codigoCiudad, Integer codigoTeatro) throws Exception {

        List<Pelicula> listarPeliculasCiudadTeatro = peliculaRepo.obtenerPeliculasCiudadTeatro(codigoCiudad,codigoTeatro);
        Ciudad ciudad = ciudadRepo.findByCodigo(codigoCiudad);
        Teatro teatro = teatroRepo.findByCodigo(codigoTeatro);

        if(ciudad == null){
            throw new Exception("No existe una ciudad con codigo "+codigoCiudad);
        }
        if(teatro == null){
            throw new Exception("No existe un teatro con codigo "+codigoTeatro);
        }
        if(listarPeliculasCiudadTeatro.isEmpty()){
            throw new Exception("No existe peliculas para la ciudad con codigo "+codigoCiudad+" y el teatro "+codigoTeatro);
        }
        return listarPeliculasCiudadTeatro;
    }

    @Override
    public List<LocalDate> listarFechaFuncionesPelicula(Integer codigoPelicula,Integer codigoTeatro) throws Exception {

        Pelicula pelicula = peliculaRepo.findByCodigo(codigoPelicula);
        Teatro teatro     = teatroRepo.findByCodigo(codigoTeatro);

        if(pelicula == null){
            throw new Exception("No existe una pelicula con codigo "+codigoPelicula);
        }
        if(teatro == null){
            throw new Exception("No existe un teatro con codigo "+codigoTeatro);
        }

        List<LocalDate> fechas = peliculaRepo.listarFechaFuncion(codigoPelicula,codigoTeatro);

        if(fechas.isEmpty()){
            throw new Exception("La lista de fechas esta vacia de la pelicula con codigo "+codigoPelicula+" y en el teatro "+codigoTeatro);
        }

        return fechas;
    }

    @Override
    public List<String> listarHorariosFechaFuncionesPelicula(Integer codigoPelicula, LocalDate fechaPelicula, Integer codigoTeatro) throws Exception {

        Pelicula pelicula = peliculaRepo.findByCodigo(codigoPelicula);
        Teatro teatro     = teatroRepo.findByCodigo(codigoTeatro);

        if(pelicula == null){
            throw new Exception("No existe una pelicula con codigo "+codigoPelicula);
        }
        if(teatro == null){
            throw new Exception("No existe un teatro con codigo "+codigoTeatro);
        }
        if(fechaPelicula == null){
            throw new Exception("La fecha seleccionada es incorrecta "+fechaPelicula);
        }

        List<String> horarios = peliculaRepo.listarHorasFuncion(codigoPelicula,fechaPelicula,codigoTeatro);

        if(horarios.isEmpty()){
            throw new Exception("La lista de horarios esta vacia de la pelicula con codigo "+codigoPelicula+" y en el teatro "+codigoTeatro);
        }

        return horarios;
    }



    @Override
    public DistribucionSilla distribucion(Integer codigoTeatro, Integer codigoSala) throws Exception {
        return null;
    }



}

