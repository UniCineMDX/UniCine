package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Confiteria;

import java.util.List;

public interface ConfiteriaServicio {


    Confiteria obtenerConfiteriaCodigo(Integer codigo) throws Exception;

    Confiteria registrarConfiteria(Confiteria confiteria) throws Exception;

    Confiteria actualizarConfiteria (Confiteria confiteria) throws Exception;

    void eliminarConfiteria (Integer codigoConfiteria) throws Exception;

    List<Confiteria> listarConfiterias();
}
