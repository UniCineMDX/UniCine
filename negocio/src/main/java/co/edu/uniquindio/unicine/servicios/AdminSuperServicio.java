package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.time.LocalDate;
import java.util.List;

public interface AdminSuperServicio {

    //Loguearse

    List<AdministradorSuper> listarAdministradores() throws Exception;
    List<AdministradorSuper> listarAdministradoresOrdenados() throws Exception;
    AdministradorSuper       verificarRegistroAdminSuper(String correo, String password) throws Exception;

    //Gestion de Teatros

    AdministradorTeatro crearAdminTeatro(AdministradorTeatro adminTeatro) throws Exception;
    AdministradorTeatro actualizarDatosAdminTeatro(AdministradorTeatro adminTeatro) throws Exception;
    AdministradorTeatro obtenerAdminTeatroCedula(String cedula) throws Exception;
    AdministradorTeatro obtenerAdminTeatroCorreo(String correo) throws Exception;
    void                eliminarAdminTeatro(String cedula) throws Exception; ;
    List<AdministradorTeatro>  listarAdminTeatros() throws Exception;

    Teatro asignarAdministradorTeatro(Integer codigoTeatro, String cedulaAdminTeatro) throws Exception;
    Teatro   desasignarAdministradorTeatro(Integer codigoTeatro, String cedulaAdminTeatro) throws Exception;

    //Gestion de peliculas

    Pelicula crearPelicula(Pelicula pelicula) throws Exception;
    Pelicula actualizarDatosPelicula(Integer codigoPelicula, Pelicula pelicula) throws Exception;
    Pelicula obtenerPeliculaCodigo(Integer codigoPelicula) throws Exception;
    Pelicula obtenerPeliculaNombre(String nombrePelicula) throws Exception;
    void     eliminarPelicula(Integer codigoPelicula) throws Exception;
    List<Pelicula>  listarPeliculas() throws Exception;

    Pelicula cambiarEstadoPelicula(Integer codigoPelicula, EstadoPelicula estadoPelicula) throws Exception;

    //Gestion de Confiteria

    Confiteria crearConfiteria(Confiteria confiteria) throws Exception;
    Confiteria actualizarDatosConfiteria(Confiteria confiteria) throws Exception;
    Confiteria obtenerConfiteria(Integer codigoConfiteria) throws Exception;
    void       eliminarConfiteria(Integer codigoConfiteria) throws Exception;
    List<Confiteria>  listarConfiterias() throws Exception;

    //Gestion de Cupones

    Cupon crearCupon(Cupon cupon) throws Exception;
    Cupon actualizarCupon(Cupon cupon) throws Exception;
    Cupon obtenerCupon(Integer codigoCupon) throws Exception;
    void  eliminarCupon(Integer codigoCupon) throws Exception;
    List<Cupon> listarCupones() throws Exception;
    CuponCliente asignarCuponCliente(Integer codigo, String cedulaCliente) throws Exception;
}
