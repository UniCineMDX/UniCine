package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio{

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


    public ClienteServicioImpl(CiudadRepo ciudadRepo,TeatroRepo teatroRepo,ClienteRepo clienteRepo, PeliculaRepo peliculaRepo, FuncionRepo funcionRepo, CuponRepo cuponRepo, CuponClienteRepo cuponClienteRepo, ConfiteriaRepo confiteriaRepo, CompraRepo compraRepo, EntradaRepo entradaRepo, CompraConfiteriaRepo compraConfiteriaRepo, EmailServicio emailServicio) {

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
        Cliente clienteEncontrado = clienteRepo.findByCorreoAndPassword(correo,password);

        if(clienteEncontrado == null){
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

        if(clienteCedula != null){
            throw new Exception("Ya existe un cliente registrado con numero de cedula "+cliente.getCedula());
        }
        if(clienteCorreo != null){
            throw new Exception("Ya existe un cliente registrado con el correo "+cliente.getCorreo());
        }

        Cliente clienteGuardado = clienteRepo.save(cliente);
        //emailServicio.enviarEmail("Registro en unicine", "Hola, debe ir al siguiente enlace para activar la cuenta: ....", cliente.getCorreo());

        Cupon cuponRegistro = Cupon.builder().descuento(15.0).criterio("Cupon de descuento para clientes en su registro").vencimiento(LocalDate.of(2022,12,31)).build();
        CuponCliente cuponCliente = CuponCliente.builder().cupon(cuponRegistro).cliente(clienteCedula).estado(EstadoCupon.SIN_USAR).build();
        cuponClienteRepo.save(cuponCliente);
        clienteCedula.getCupones().add(cuponCliente);
        //emailServicio.enviarEmail("Cupon bienvenida", "Hola, has recibido un cupon con el 15% por realizar tu registro, para obtenerlo ve al siguiente enlace: ....", clienteCedula.getCorreo());
        return clienteGuardado;
    }
    /**
     * Este metodo permite obtener un cliente atraves de su cedula
     */
    @Override
    public Cliente obtenerClientePorCedula(String cedula) throws Exception {

        Cliente clienteEncontrado = clienteRepo.findByCedula(cedula);

        if(clienteEncontrado == null){
            throw new Exception("No existe un cliente con numero de cedula "+cedula);
        }

        return clienteEncontrado;
    }

    /**
     * Este metodo permite actualizar el nombre del cliente atraves de su cedula
     */
    @Override
    public Cliente actualizarCliente(Cliente cliente) throws Exception {

        Cliente clienteEncontrado = clienteRepo.findByCedula(cliente.getCedula());
        Cliente clienteCorreo     = clienteRepo.findByCorreo(cliente.getCorreo());

        if(clienteEncontrado == null){
            throw new Exception("No existe un cliente con numero de cedula "+cliente.getCedula());
        }
        if(clienteCorreo != null && !clienteCorreo.getCorreo().equals(cliente.getCorreo())){
            throw new Exception("Ya existe un cliente registrado con este correo "+cliente.getCorreo());
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

        if(clienteEncontrado == null){
            throw new Exception("No existe un cliente con cedula "+cedulaCliente);
        }

        clienteRepo.delete(clienteEncontrado);
    }

    /**
     * Este metodo permite listar los clientes
     */
    @Override
    public List<Cliente> listarClientes()throws Exception {

        List<Cliente> lisaClientes = clienteRepo.findAll();

        if(lisaClientes.isEmpty()){
            throw new Exception("No existen clientes registrado");
        }
        return lisaClientes;
    }

    /**
     * Este metodo permite listar los cupones de un cliente atraves de la cedula
     */
    @Override
    public List<CuponCliente> listarCuponesCliente(String cedulaCliente)throws Exception {

        Cliente clienteEncontrado = clienteRepo.findByCedula(cedulaCliente);

        if(clienteEncontrado == null){
            throw new Exception("No existe un cliente con numero de cedula "+cedulaCliente);
        }
        if(clienteEncontrado.getCupones().isEmpty()){
            throw new Exception("El cliente no posee cupones");
        }

        return clienteEncontrado.getCupones();
    }

    /**
     * Este metodo permite ver el historial de compras de un cliente atraves de la cedula
     */
    @Override
    public List<Compra> historialCompras(String cedulaCliente)throws Exception {

        Cliente clienteEncontrado = clienteRepo.findByCedula(cedulaCliente);

        if(clienteEncontrado == null){
            throw new Exception("No existe un cliente con numero de cedula "+cedulaCliente);
        }
        if(clienteEncontrado.getCompras().isEmpty()){
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

        if(listadoCompras.isEmpty()){
            throw new Exception("El cliente con cedula "+cedulaCliente+" no ha realizado ninguna compra");
        }
        for (Compra compra: listadoCompras) {
            LocalDate fecha1 = compra.getFuncion().getHorario().getFechaFin();
            LocalDate fecha2 = LocalDate.now();
            
            if(fecha1.compareTo(fecha2) < 0){
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

        if(listadoCompras.isEmpty()){
            throw new Exception("El cliente con cedula "+cedulaCliente+" no ha realizado ninguna compra");
        }
        for (Compra compra: listadoCompras) {
            LocalDate fecha1 = compra.getFuncion().getHorario().getFechaFin();
            LocalDate fecha2 = LocalDate.now();

            if(fecha1.compareTo(fecha2) == 1){
                listaComprasNoRedimidas.add(compra);
            }
        }
        return listaComprasNoRedimidas;
    }

    @Override
    public boolean redimirCupon(Integer codigo) throws Exception {
        return true;
    }

    @Override
    public boolean cambiarContraseña(String correo, String passwordNueva) throws Exception {

        Cliente clienteCorreo = clienteRepo.findByCorreo(correo);
        boolean centinela = false;

        if(clienteCorreo == null){
            throw new Exception("El cliente con el correo "+correo+" no existe");
        }
        else{
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
    public Compra realizarCompra(Compra compra ,Cliente cliente, List<Entrada> entradas, List<CompraConfiteria> compraConfiterias, MedioPago medioPago, CuponCliente cupon, Funcion funcion) throws Exception {

        Compra compraCodigo = compraRepo.findByCodigo(compra.getCodigo());
        Cliente clienteCedula = clienteRepo.findByCedula(cliente.getCedula());
        Funcion funcionCodigo = funcionRepo.findByCodigo(funcion.getCodigo());
        CuponCliente cuponCodigo = cuponClienteRepo.buscarCuponClientePorCodigoCupon(cupon.getCodigo());


        if (compraCodigo != null) {
            throw new Exception("La compra con el codigo" + compra.getCodigo() + "ya existe");
        }

        if (clienteCedula == null) {
            throw new Exception("El cliente con la cedula" + cliente.getCedula() + "no existe");
        }

        if (funcionCodigo == null) {
            throw new Exception("La funcion con el codigo" + funcion.getCodigo() + "no existe");
        }

        if (cuponCodigo == null) {
            throw new Exception("El cupon ingresado no existe");
        }

        if (medioPago == null) {
            throw new Exception("Elija un medio de pago disponible");
        }

        Compra compraGuardada = compraRepo.save(compra);
        System.out.println(compra);

        double valorConfiterias = 0;

        if(!compraConfiterias.isEmpty()) {
            compraConfiterias.forEach(c -> {
                c.setCompra(compraGuardada);
                compraConfiteriaRepo.save(c);
            });


            for (int i = 0; i < compraGuardada.getCompraConfiterias().size(); i++) {

                valorConfiterias = (compraGuardada.getCompraConfiterias().get(i).getPrecio()) + valorConfiterias;
            }
        }


        entradas.forEach(e -> {
            e.setCompra(compraGuardada);
            entradaRepo.save(e);
        });



        if (cuponCodigo != null) {
            compraGuardada.setCuponCliente(cuponCodigo);
            cuponCodigo.setEstado(EstadoCupon.USADO);
            cuponClienteRepo.save(cuponCodigo);
        }
        int numComprasCliente = compraRepo.contarComprasCliente(clienteCedula.getCedula());

        CuponCliente cuponCliente;
        if (numComprasCliente == 0) {
            Cupon cuponPrimerCompra = Cupon.builder().descuento(10.0).criterio("Cupon de descuento para clientes en su primer compra").vencimiento(LocalDate.of(2022, 12, 31)).build();
            cuponCliente = CuponCliente.builder().cupon(cuponPrimerCompra).cliente(clienteCedula).estado(EstadoCupon.SIN_USAR).build();
            cuponRepo.save(cuponPrimerCompra);
            cuponClienteRepo.save(cuponCliente);
            clienteCedula.getCupones().add(cuponCliente);
            emailServicio.enviarEmail("Cupon primer compra", "Hola, has recibido un cupon del 10% por realizar tu primer compra, para obtenerlo ve al aiguiente enlace: ....", clienteCedula.getCorreo());

        }

        double valorEntradas = 0;

        for (int i = 0; i < compraGuardada.getEntradas().size(); i++) {

            valorEntradas = (compraGuardada.getEntradas().get(i).getPrecio()) + valorEntradas;
        }

        double porcentajeDescuento = (cuponCodigo.getCupon().getDescuento()/100);
        double descuento = (valorConfiterias + valorEntradas)*porcentajeDescuento;
        double valorTotal = (valorConfiterias + valorEntradas)- descuento;


        compraGuardada.setCliente(clienteCedula);
        compraGuardada.setCuponCliente(cuponCodigo);
        compraGuardada.setFechaCompra(LocalDate.now());
        compraGuardada.setCompraConfiterias(compraGuardada.getCompraConfiterias());
        compraGuardada.setEntradas(compraGuardada.getEntradas());
        compraGuardada.setFuncion(funcion);
        compraGuardada.setMedioPago(medioPago);
        compraCodigo.setValorTotal(valorTotal);

        Compra compraNueva = compraRepo.save(compraGuardada);
        clienteCedula.getCompras().add(compraNueva);

        emailServicio.enviarEmail("Compra unicine", "Hola" + clienteCedula.getNombre() + "has realizado una compra en unicine de los siguientes productos:" + compraNueva.getCompraConfiterias() +"\n" + compraNueva.getEntradas()+ " \n"+ compraNueva.getFuncion()+"todo por un valor de $"+valorTotal, clienteCedula.getCorreo());

        return compraNueva;
    }


    @Override
    public boolean validarPago() {
        return false;
    }

    @Override
    public Compra realizarCompraConfiteria() {
        return null;
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
    public List<Funcion> listarFuncionesPelicula(Integer codigoPelicula) throws Exception {

        Pelicula pelicula = peliculaRepo.findByCodigo(codigoPelicula);

        if(pelicula == null){
            throw new Exception("No existe una pelicula con codigo "+codigoPelicula);
        }

        List<Funcion> funcionesPelicula = pelicula.getFunciones();

        if(funcionesPelicula.isEmpty()){
            throw new Exception("La pelicula no tiene funciones");
        }

        return funcionesPelicula;
    }

    @Override
    public List<Funcion> listarFuncionesDiaPelicula(Integer codigoPelicula, Integer dia) throws Exception {

        Pelicula pelicula = peliculaRepo.findByCodigo(codigoPelicula);
        List<Funcion> funcionesPeliculaDia = pelicula.getFunciones();

        if(pelicula == null){
            throw new Exception("No existe una pelicula con codigo "+codigoPelicula);
        }
        List<Funcion> funcionesPelicula = pelicula.getFunciones();

        if(funcionesPelicula.isEmpty()){
            throw new Exception("La pelicula no tiene funciones");
        }
        for (Funcion funcion: funcionesPelicula) {
            Integer diaFuncion = funcion.getHorario().getFechaFin().getDayOfMonth();
            if(dia == diaFuncion){
                funcionesPeliculaDia.add(funcion);
            }
        }
        return funcionesPeliculaDia;
    }

    @Override
    public DistribucionSilla distribucion(Integer codigoTeatro, Integer codigoSala) throws Exception {
        return null;
    }



}

