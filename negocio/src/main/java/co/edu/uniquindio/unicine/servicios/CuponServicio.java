package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.CuponCliente;

import java.util.List;

public interface CuponServicio {


    Cupon obtenerCupon(Integer codigo) throws Exception;

    Cupon registrarCupon(Cupon cupon) throws Exception;

    Cupon actualizarCupon (Cupon cupon) throws Exception;

    void eliminarCupon (Integer codigoCpon) throws Exception;

    List<Cupon> listarCupones ();

    CuponCliente obtenerCuponCliente (Integer codigoCupon) throws Exception;
}
