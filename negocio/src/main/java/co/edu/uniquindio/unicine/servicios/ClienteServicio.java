package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface ClienteServicio {

    //Metodos del cliente

    Persona login(String correo, String password) throws Exception;
    Cliente registrarCliente(Cliente cliente) throws Exception;
    Cliente obtenerClientePorCedula(String cedula) throws Exception;
    Cliente actualizarCliente(Cliente cliente) throws Exception;
    void    eliminarCliente(String codigoCliente) throws Exception;
    List<Cliente> listarClientes()throws Exception;

    List<CuponCliente> listarCuponesCliente(String cedulaCliente)throws Exception;
    List<Compra> historialCompras(String cedulaCliente)throws Exception;
    List<Compra> historialComprasRedimidas(String cedulaCliente)throws Exception;
    List<Compra> historialCompraNoRedimidas(String cedulaCliente)throws Exception;
    CuponCliente obtenerCuponSeleccionado(Cliente cliente,Integer codigo) throws Exception;
    boolean cambiarPassword(String correo, String passwordNueva)throws Exception;
    void solicitarCambiarPassword(String correo) throws Exception;
    Compra realizarCompra(String cedulaCliente, List<Entrada> entradas, List<CompraConfiteria> compraConfiterias, MedioPago medioPago, Integer cuponCodigo,Integer funcionCodigo) throws Exception;
    List<Entrada> crearEntradas(List<String > filasColumnas) throws Exception;

    Compra realizarCompraConfiteria(String cedulaCliente, List<List<Integer>>confiterias, MedioPago medioPago, Integer codigoCuponCliente)throws Exception;
    List<CompraConfiteria> crearComprasConfiteria(List<List<Integer>> confiterias)throws Exception;
    List<Pelicula> listarPeliculas() throws Exception;
    HashMap<String,Boolean> listaSillasFuncion(Integer codigoFuncion) throws Exception;
    List<Pelicula> listarPeliculasCiudad(Integer codigoCiudad,EstadoPelicula estado) throws Exception;
    List<Pelicula> listarPeliculasCiudadTeatro(Integer codigoCiudad, Integer codigoTeatro) throws Exception;
    List<LocalDate> listarFechaFuncionesPelicula(Integer codigoPelicula,Integer codigoTeatro) throws Exception ;
    List<String> listarHorariosFechaFuncionesPelicula(Integer codigoPelicula, LocalDate fechaPelicula, Integer codigoTeatro) throws Exception ;
    List<Pelicula> buscarPeliculaNombre (String nombre) throws Exception;
    List<Pelicula> buscarPeliculaEstado(EstadoPelicula estadoPelicula) throws Exception;
    List<Pelicula> buscarPeliculaPorGenero (Genero genero) throws Exception;
}
