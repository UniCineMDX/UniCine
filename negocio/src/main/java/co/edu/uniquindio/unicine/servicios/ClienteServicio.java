package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.util.List;

public interface ClienteServicio {

    //Logueo
    Cliente login(String correo, String password) throws Exception;


    List<Pelicula> buscarPeliculaEstado(String nombre, EstadoPelicula estadoPelicula) throws Exception;
    List<Pelicula> buscarPeliculaNombre (String nombre) throws Exception;

    List<Pelicula> buscarPeliculaPorGenero (Genero genero) throws Exception;

    // Metodos Crud del Cliente

    Cliente registrarCliente(Cliente cliente) throws Exception;
    Cliente obtenerClientePorCedula(Integer cedula) throws Exception;
    Cliente actualizarCliente(Cliente cliente) throws Exception;

    void eliminarCliente(String codigoCliente) throws Exception;

    List<Cliente> listarClientes();


    void HistorialCompra(Integer codigoCliente);


    Compra realizarCompra(Cliente cliente, List<Entrada> entradas, List<CompraConfiteria> compraConfiterias, MedioPago medioPago, Cupon cupon, Funcion funcion) throws Exception;


    boolean redimirCupon(Integer codigo) throws Exception;

    //Metodo para cambiar la contraseña
    boolean cambiarContraseña(String correo, String passwordNueva)throws Exception;

}
