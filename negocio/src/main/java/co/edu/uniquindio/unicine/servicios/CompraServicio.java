package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.util.List;

public interface CompraServicio {

    Compra obtenerCompraCodigo (Integer codigo) throws Exception;

    Compra registrarCompra (Compra compra) throws Exception;

    Compra actualizarCompra(Compra compra) throws Exception;

    void eliminarCompra(Integer codigo) throws Exception;

    List<Compra> listarCompras();

    List<Entrada> obtenerEntradasCompra(Integer codigoCompra) throws Exception;

    List<CompraConfiteria> obtenerComprasConfiteria (Integer codigoCompra) throws Exception;

    Cupon obtenerCuponCompra(Integer codigoCompra)throws Exception;

    Funcion obtenerFuncionCompra(Integer codigoCompra)throws Exception;

    Cliente obtenerClienteCompra(Integer codigoCompra)throws Exception;


}
