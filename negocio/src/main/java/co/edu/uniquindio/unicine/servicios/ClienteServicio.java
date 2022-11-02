package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface ClienteServicio {

    //Metodos del cliente

    Cliente login(String correo, String password) throws Exception;
    Cliente registrarCliente(Cliente cliente) throws Exception;
    Cliente obtenerClientePorCedula(String cedula) throws Exception;
    Cliente actualizarCliente(Cliente cliente) throws Exception;
    void    eliminarCliente(String codigoCliente) throws Exception;
    List<Cliente> listarClientes()throws Exception;

    List<CuponCliente> listarCuponesCliente(String cedulaCliente)throws Exception;
    List<Compra> historialCompras(String cedulaCliente)throws Exception;
    List<Compra> historialComprasRedimidas(String cedulaCliente)throws Exception;
    List<Compra> historialCompraNoRedimidas(String cedulaCliente)throws Exception;
    CuponCliente obtenerCuponSeleccionado(String cedula,Integer codigo) throws Exception;
    boolean cambiarContraseña(String correo, String passwordNueva)throws Exception;
    void solicitarCambiarContraseña(String correo) throws Exception;
    Compra realizarCompra(Cliente cliente, List<Entrada> entradas, List<CompraConfiteria> compraConfiterias, MedioPago medioPago, CuponCliente cupon, Funcion funcion) throws Exception;
    boolean validarPago();
    List<Entrada> crearEntradas(List<String > filasColumnas) throws Exception;

    Compra realizarCompraConfiteria(Cliente cliente, List<List<Integer>>confiterias, MedioPago medioPago, CuponCliente cuponCliente)throws Exception;

    List<CompraConfiteria> crearComprasConfiteria(List<List<Integer>> confiterias)throws Exception;
    List<Pelicula> listarPeliculas() throws Exception;
    HashMap<String,Boolean> listaSillasFuncion(Integer codigoFuncion) throws Exception;
    List<Pelicula> listarPeliculasCiudad(Integer codigoCiudad) throws Exception;
    List<Pelicula> listarPeliculasCiudadTeatro(Integer codigoCiudad, Integer codigoTeatro) throws Exception;
    List<LocalDate> listarFechaFuncionesPelicula(Integer codigoPelicula,Integer codigoTeatro) throws Exception ;
    List<String> listarHorariosFechaFuncionesPelicula(Integer codigoPelicula, LocalDate fechaPelicula, Integer codigoTeatro) throws Exception ;
    List<Pelicula> buscarPeliculaNombre (String nombre) throws Exception;
    List<Pelicula> buscarPeliculaEstado(EstadoPelicula estadoPelicula) throws Exception;
    List<Pelicula> buscarPeliculaPorGenero (Genero genero) throws Exception;
    DistribucionSilla distribucion(Integer codigoTeatro, Integer codigoSala) throws Exception;


}
