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

    public ClienteServicioImpl(CiudadRepo ciudadRepo,TeatroRepo teatroRepo,ClienteRepo clienteRepo, PeliculaRepo peliculaRepo, FuncionRepo funcionRepo, CuponRepo cuponRepo, CuponClienteRepo cuponClienteRepo, ConfiteriaRepo confiteriaRepo, CompraRepo compraRepo, EntradaRepo entradaRepo, CompraConfiteriaRepo compraConfiteriaRepo) {
        this.clienteRepo = clienteRepo;
        this.peliculaRepo = peliculaRepo;
        this.funcionRepo = funcionRepo;
        this.cuponRepo = cuponRepo;
        this.cuponClienteRepo = cuponClienteRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.compraRepo = compraRepo;
        this.entradaRepo = entradaRepo;
        this.compraConfiteriaRepo = compraConfiteriaRepo;
        this.clienteRepo = clienteRepo;
        this.ciudadRepo = ciudadRepo;
    }


    @Override
    public Cliente login(String correo, String password) throws Exception {
        Cliente clienteEncontrado = clienteRepo.findByCorreoAndPassword(correo,password);

        if(clienteEncontrado == null){
            throw new Exception("El correo o la contraseña son incorrectos");
        }

        return clienteEncontrado;
    }

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

        return clienteGuardado;
    }

    @Override
    public Cliente obtenerClientePorCedula(String cedula) throws Exception {

        Cliente clienteEncontrado = clienteRepo.findByCedula(cedula);

        if(clienteEncontrado == null){
            throw new Exception("No existe un cliente con numero de cedula "+cedula);
        }

        return clienteEncontrado;
    }

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

    @Override
    public void eliminarCliente(String cedulaCliente) throws Exception {

        Cliente clienteEncontrado = clienteRepo.findByCedula(cedulaCliente);

        if(clienteEncontrado == null){
            throw new Exception("No existe un cliente con cedula "+cedulaCliente);
        }

        clienteRepo.delete(clienteEncontrado);
    }

    @Override
    public List<Cliente> listarClientes()throws Exception {

        List<Cliente> lisaClientes = clienteRepo.findAll();

        if(lisaClientes.isEmpty()){
            throw new Exception("No existen clientes registrado");
        }
        return lisaClientes;
    }

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
        return false;
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
    public List<Pelicula> buscarPeliculaPorGenero(Genero genero) throws Exception {


        List<Pelicula> listarPeliculasGenero = peliculaRepo.obtenerPeliculasPorGenero(genero);


        if(listarPeliculasGenero.isEmpty()){
            throw new Exception("No existe peliculas creadas con genero "+genero);
        }
        return listarPeliculasGenero;
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

