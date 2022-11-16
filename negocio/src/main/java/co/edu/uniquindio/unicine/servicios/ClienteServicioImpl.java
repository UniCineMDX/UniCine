package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepo clienteRepo;
    private final PeliculaRepo peliculaRepo;
    private final FuncionRepo funcionRepo;
    private final CuponRepo cuponRepo;
    private final CuponClienteRepo cuponClienteRepo;
    private final ConfiteriaRepo confiteriaRepo;
    private final CompraRepo compraRepo;
    private final EntradaRepo entradaRepo;
    private final CompraConfiteriaRepo compraConfiteriaRepo;
    private final CiudadRepo ciudadRepo;
    private final TeatroRepo teatroRepo;
    private final EmailServicio emailServicio;


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
    public Persona login(String correo, String password) throws Exception {
        Persona persona = clienteRepo.findByCorreoAndPassword(correo, password);

        if (persona == null) {
            throw new Exception("El correo o la contraseña son incorrectos");
        }
        if (persona instanceof Cliente) {
            Cliente cliente = (Cliente) persona;
            if (cliente.getEstado().equals(EstadoCliente.INACTIVO)) {
                throw new Exception("La cuenta esta desactivada");
            }
        }
        return persona;
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
        clienteGuardado.setEstado(EstadoCliente.INACTIVO);
        //emailServicio.enviarEmail("Registro en unicine", "Hola, debe ir al siguiente enlace para activar la cuenta: ....", clienteGuardado.getCorreo());


        //CuponCliente cuponBienvenida = new CuponCliente();
        //Cupon cupon = cuponRepo.findByCodigo(1);
        //cuponBienvenida.setEstado(EstadoCupon.SIN_USAR);
        //cuponBienvenida.setCupon(cupon);
        //cuponBienvenida.setCliente(clienteGuardado);
        //CuponCliente cuponGuardado = cuponClienteRepo.save(cuponBienvenida);

        //clienteGuardado.getCupones().add(cuponGuardado);
        Cliente clienteAux = clienteRepo.save(clienteGuardado);

        //emailServicio.enviarEmail("Cupon bienvenida", "Hola, has recibido un cupon con el 15% por realizar tu registro, para obtenerlo ve al siguiente enlace: ....", clienteAux.getCorreo());

        return clienteAux;
    }

    /**
     * Este metodo permite obtener un cliente atraves de su cedula
     */
    @Override
    public Cliente obtenerClientePorCedula(String cedula) throws Exception {

        System.out.println(clienteRepo.findByCedula(cedula));
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

        return clienteRepo.save(cliente);
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
        if(clienteEncontrado.getCompras().isEmpty()){
            throw new Exception("No existe un cliente con cedula " + cedulaCliente);
        }

        clienteRepo.delete(clienteEncontrado);
    }

    //Metodo para buscar una pelicula por el genero

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

        List<Compra> listaComprasRedimidas = new ArrayList<>();
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
        List<Compra> listaComprasNoRedimidas = new ArrayList<>();
        List<Compra> listadoCompras = historialCompras(cedulaCliente);

        if (listadoCompras.isEmpty()) {
            throw new Exception("El cliente con cedula " + cedulaCliente + " no ha realizado ninguna compra");
        }
        for (Compra compra : listadoCompras) {
            LocalDate fecha1 = compra.getFuncion().getHorario().getFechaFin();
            LocalDate fecha2 = LocalDate.now();

            if (fecha1.compareTo(fecha2) > -1) {
                listaComprasNoRedimidas.add(compra);
            }
        }
        return listaComprasNoRedimidas;
    }

    @Override
    public CuponCliente obtenerCuponSeleccionado(Cliente cliente,Integer codigo) throws Exception {

        if(cliente.getCupones().isEmpty()){
            throw new Exception("La lista de cupones esta vacia "+codigo);
        }
        for (CuponCliente cupon: cliente.getCupones()) {
            if(cupon.getCodigo().equals(codigo)){
                return cupon;
            }
        }

        return null;
    }

    @Override
    public boolean cambiarPassword(String correo, String passwordNueva) throws Exception {

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
    public void solicitarCambiarPassword(String correo) throws Exception {

        emailServicio.enviarEmail("Cambio de contraseña", "Hola, debe ir al siguiente enlace para cambiar la contraseña: ....", correo);
    }



    //Metodo para buscar una pelicula por el nombre


    public List<Pelicula> buscarPeliculaPorNombre(String nombre) throws Exception {

        List<Pelicula> peliculaGuardada = peliculaRepo.obtenerPeliculasNombre(nombre);

        if(peliculaGuardada.isEmpty()){
            throw new Exception("La lista de peliculas con nombre "+nombre+" esta vacia");
        }

        return peliculaGuardada;
    }


    @Override
    public Compra realizarCompra(String cedulaCliente, List<Entrada> entradas, List<CompraConfiteria> compraConfiterias, MedioPago medioPago, Integer cuponCodigo,Integer funcionCodigo) throws Exception {


        Compra compra = new Compra();
        Cliente cliente = clienteRepo.findByCedula(cedulaCliente);
        Funcion funcion = funcionRepo.findByCodigo(funcionCodigo);
        Double valorEntradas = 0.0;
        Double valorConfiterias = 0.0;
        Double descuentoCompra = 0.0;

        if (cliente == null) {
            throw new Exception("El cliente con la cedula" + cliente.getCedula() + "no existe");
        }
        if (funcion == null) {
            throw new Exception("La funcion con el codigo" + funcion.getCodigo() + "no existe");
        }
        if (medioPago == null) {
            throw new Exception("Elija un medio de pago disponible");
        }
        if (entradas.isEmpty()) {
            throw new Exception("La lista de entradas esta vacia");
        }

        entradas.forEach(entrada ->
            entrada.setCompra(compra)
        );
        compra.setEntradas(entradas);
        entradaRepo.saveAll(entradas);

        if (!compraConfiterias.isEmpty()) {
            for (int i = 0; i < compraConfiterias.size(); i++) {
                compraConfiterias.get(i).setCompra(compra);
                valorConfiterias += (compraConfiterias.get(i).getPrecio());
            }
            compra.setCompraConfiterias(compraConfiterias);
            compraConfiteriaRepo.saveAll(compraConfiterias);
        }

        if (cuponCodigo != null) {
            CuponCliente cupon = obtenerCuponSeleccionado(cliente, cuponCodigo);
            if (cupon != null) {
                cupon.setCompra(compra);
                compra.setCuponCliente(cupon);
                cupon.setEstado(EstadoCupon.USADO);
                cuponClienteRepo.save(cupon);

                Double porcentajeDescuento = (cupon.getCupon().getDescuento() / 100);
                descuentoCompra = (valorConfiterias + valorEntradas) * porcentajeDescuento;
            } else {
                throw new Exception("El cliente no posee un cupon con codigo " + cuponCodigo);
            }
        }


        for (int i = 0; i < entradas.size(); i++) {
            valorEntradas = (entradas.get(i).getPrecio()) + valorEntradas;
        }
        double valorTotal = (valorConfiterias + valorEntradas) - descuentoCompra;

        funcion.getCompras().add(compra);
        compra.setFuncion(funcion);
        compra.setMedioPago(medioPago);
        compra.setValorTotal(valorTotal);
        cliente.getCompras().add(compra);
        compra.setCliente(cliente);
        compra.setFechaCompra(LocalDate.now());
        compra.setEntradas(entradas);

        clienteRepo.save(cliente);
        funcionRepo.save(funcion);
        entradaRepo.saveAll(entradas);
        compraConfiteriaRepo.saveAll(compraConfiterias);
        Compra compraGuardada = compraRepo.save(compra);

        if (compraRepo.contarComprasCliente(cedulaCliente) == 0) {
            Cupon cuponPrimerCompra = cuponRepo.findByCodigo(2);
            CuponCliente cuponCliente = CuponCliente.builder().cupon(cuponPrimerCompra).cliente(cliente).estado(EstadoCupon.SIN_USAR).build();
            cuponClienteRepo.save(cuponCliente);
            //emailServicio.enviarEmail("Cupon primer compra", "Hola, has recibido un cupon del 10% por realizar tu primer compra, para obtenerlo ve al aiguiente enlace: ....", clienteCedula.getCorreo());
        }

        //emailServicio.enviarEmail("Compra unicine", "Hola" + cliente.getNombre() + "has realizado una compra en unicine de los siguientes productos:" + compraGuardada.getCompraConfiterias() + "\n" + compraGuardada.getEntradas() + " \n" + compraGuardada.getFuncion() + "todo por un valor de $" + valorTotal, cliente.getCorreo());

        return compraGuardada;
    }

    @Override
    public List<Entrada> crearEntradas(List<String> filasColumnas) throws Exception {

        List<Entrada> listaEntradas = new ArrayList<>();

        if (filasColumnas.isEmpty()) {
            throw new Exception("No se ha seleccionado ninguna silla");
        }
        for (String filaColumna : filasColumnas) {
            Entrada entrada = new Entrada();
            entrada.setColumna(Integer.parseInt(filaColumna.charAt(0) + ""));
            entrada.setFila(Integer.parseInt(filaColumna.charAt(1) + ""));
            entrada.setPrecio(5000.0);
            listaEntradas.add(entrada);
        }

        return listaEntradas;
    }

    @Override
    public Compra realizarCompraConfiteria(String cedulaCliente, List<List<Integer>>confiterias, MedioPago medioPago, Integer codigoCuponCliente) throws Exception{

        double descuento = 0.0;
        Compra  compra   = new Compra();
        Cliente cliente  = clienteRepo.findByCedula(cedulaCliente);

        if(cliente == null){
            throw new Exception("El cliente con la cedula "+cedulaCliente+" no existe");
        }
        if(confiterias.isEmpty()){
            throw new Exception("La lista de confiterias esta vacia");
        }
        CuponCliente cuponCliente = obtenerCuponSeleccionado(cliente,codigoCuponCliente);
        List<CompraConfiteria>compraConfiterias = crearComprasConfiteria(confiterias);

        double valorConfiterias=0;

        for (int i=0; i<compraConfiterias.size();i++){
            valorConfiterias += valorConfiterias+compraConfiterias.get(i).getPrecio();
        }

        if(cuponCliente != null){
            compra.setCuponCliente(cuponCliente);
            cuponCliente.setEstado(EstadoCupon.USADO);
            cuponClienteRepo.save(cuponCliente);
            double porcentajeDescuento = (cuponCliente.getCupon().getDescuento()/100);
            descuento = valorConfiterias*porcentajeDescuento;
        }

        double valorTotal = valorConfiterias - descuento;

        compra.setCliente(cliente);
        compra.setFechaCompra(LocalDate.now());
        compra.setCompraConfiterias(compraConfiterias);
        compra.setCuponCliente(cuponCliente);
        compra.setMedioPago(medioPago);
        compra.setValorTotal(valorTotal);
        compra.setFuncion(null);
        compra.setEntradas(null);

        Compra compraGuardada = compraRepo.save(compra);

        return compraGuardada;
    }

    @Override
    public List<CompraConfiteria> crearComprasConfiteria(List<List<Integer>> confiterias) throws Exception {

        List<CompraConfiteria> compraConfiterias = new ArrayList<>();

        if(confiterias.isEmpty()){
            throw new Exception("La lista de confiterias no existe");
        }

        for (List<Integer> confi: confiterias) {

            CompraConfiteria compraConfiteria = new CompraConfiteria();
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

        HashMap<String, Boolean> sillas = new HashMap<>();
        Funcion funcion = funcionRepo.findByCodigo(codigoFuncion);

        if(funcion == null){
            throw new Exception("NO existe una funcion con codigo "+codigoFuncion);
        }

        Integer filas = funcion.getSala().getDistribucionSilla().getFilas();
        Integer columnas = funcion.getSala().getDistribucionSilla().getColumnas();

        if(filas < 0 && columnas < 0){
            throw new Exception("El numero de filas y columas es incorrecto");
        }

        for (int i = 1; i < filas+1; i++) {
            for (int j = 1; j < columnas+1; j++) {
                String codigo = i+"-"+j;
                sillas.put(codigo,false);
            }
        }
        List<Entrada> entradasFuncion = peliculaRepo.obtenerEntradasFuncion(funcion.getCodigo());

        if(!entradasFuncion.isEmpty()){
            for (Entrada entrada:entradasFuncion) {
                String llave = entrada.getFila()+"-"+entrada.getColumna();
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

    public List<Pelicula> buscarPeliculaPorGenero(Genero genero) throws Exception {
        List<Pelicula> peliculaGuardada = peliculaRepo.obtenerPeliculasPorGenero(genero);

        if (peliculaGuardada.isEmpty()) {
            throw new Exception("La pelicula NO EXISTE");
        }
        return peliculaGuardada;
    }
}

