package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import java.util.List;

public interface AdministradorTeatroServicio {

    AdministradorTeatro obtenerAdmiTeatro (String cedula) throws Exception;
    /**
     AdministradorTeatro registrarAdmiTeatro (AdministradorTeatro administradorTeatro) throws Exception;

    AdministradorTeatro actualizarAdmiTeatro ( AdministradorTeatro administradorTeatro) throws Exception;

    void eliminarAdmiTeatro (String cedula) throws Exception;

    List<AdministradorTeatro> listarAdmiTeatros() throws Exception;

    List<Teatro> obtenerListaTeatros(String cedulaAdmiTeatro) throws Exception;
    **/


    //Servicios de gestión de teatros por el administrador de teatro

    Teatro obtenerTeatro (Integer codigo) throws Exception;
    Teatro registrarTeatro (Teatro teatro) throws Exception;
    Teatro actualizarTeatro (Teatro teatro) throws Exception;
    void eliminarTeatro ( Integer codigo) throws Exception;
    List<Teatro> listarTeatros() throws Exception;
    Ciudad obtenerCiudadTeatro (Integer codigoTeatro) throws Exception;
    AdministradorTeatro obtenerAdmiTeatro (Integer codigoTeatro) throws Exception;


    //Servicios de gestión de horarios por el administrador de teatro

    Horario obtenerHorario (Integer codigo) throws Exception;
    Horario registrarHorario (Horario horario) throws Exception;
    Horario actualizarHorario (Horario horario) throws Exception;
    void eliminarHorario (Integer codigo) throws Exception;
    List<Horario> listarHorarios() throws Exception;
    List<Funcion> obtenerListaFuncionesHorario (Integer codigoHorario) throws Exception;


    //Servicios de gestión de funciones por el administrador de teatro

    Funcion asignarPrecioFuncion(Integer codigoFuncion, Double precio) throws Exception;
    Funcion asignarHorarioFuncion (Integer codigoFuncion,Integer codigoHorario) throws Exception;
    Funcion obtenerFuncion (Integer codigo) throws Exception;
    Funcion registrarFuncion (Funcion funcion) throws Exception;
    Funcion actualizarFuncion (Funcion funcion) throws Exception;
    void eliminarFuncion (Integer codigo) throws Exception;
    List<Funcion> listarFunciones() throws Exception;
    Pelicula obtenerPeliculaFuncion (Integer codigoFuncion) throws Exception;
    Horario obtenerHorarioFuncion (Integer codigoFuncion) throws Exception;
    Sala obtenerSalaFuncion (Integer codigoFuncion) throws Exception;
    List<Compra> obtenerListaComprasFuncion (Integer codigoFuncion) throws Exception;


    //Servicios de gestión de salas por el administrador de teatro

    Sala obtenerSala (Integer codigo) throws Exception;
    Sala registrarSala (Sala sala) throws Exception;
    Sala actualizarSala (Sala sala) throws Exception;
    void eliminarSala (Integer codigo) throws Exception;
    List<Sala> listarSalas() throws Exception;
    Teatro obtenerTeatroSala (Integer codigoSala) throws Exception;
    DistribucionSilla obtenerDistribucionSillas (Integer codigoSala) throws Exception;
    List<Funcion> obtenerFuncionesSala (Integer codigoSala) throws Exception;
    List<Sala> obtenerListaSalasTeatro (Integer codigoTeatro) throws Exception;


    //Servicios de gestión de distribución de sillas por el administrador de teatro

    DistribucionSilla obtenerDistribucionSilla (Integer codigo) throws Exception;
    DistribucionSilla registrarDistribucionSilla (DistribucionSilla distribucionSilla) throws Exception;
    DistribucionSilla actualizarDistribucionSilla (DistribucionSilla distribucionSilla) throws Exception;
    void eliminarDistribucionSilla (Integer codigo) throws Exception;
    List<DistribucionSilla> listarDistribucionSillas() throws Exception;
    List<Sala> obtenerSalasDistribucionSilla(Integer codigoDistribucionSilla) throws Exception;
}
