package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.CuponCliente;

import java.util.List;

public interface CuponClienteServicio {

    CuponCliente obteberCuponCliente (Integer codigo) throws Exception;

    CuponCliente registrarCuponCliente (CuponCliente cuponCliente) throws Exception;

    CuponCliente actulizarCuponCliente (CuponCliente cuponCliente) throws Exception;

    void eliminarCuponCliente ( Integer codigo) throws Exception;

    List<CuponCliente> ListarCuponesCliente();

    Cupon obteberCupon (Integer codigoCuponCliente) throws Exception;

    Cliente obtenerCliente (Integer codigoCuponCliente) throws Exception;


}
