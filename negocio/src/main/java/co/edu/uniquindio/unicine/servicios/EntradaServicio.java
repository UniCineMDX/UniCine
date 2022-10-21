package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Entrada;

import java.util.List;

public interface EntradaServicio {

    Entrada obtenerEntrada(Integer codigoEntrada) throws Exception;

    Entrada registrarEntrada(Entrada entrada) throws Exception;

    Entrada actualizarEntrada(Entrada entrada) throws Exception;

    void eliminarEntrada(Integer codigoEntrada) throws Exception;

    List<Entrada> listarEntradas ();

    Compra obtenerCompra (Integer codigoEntrada) throws Exception;

}
