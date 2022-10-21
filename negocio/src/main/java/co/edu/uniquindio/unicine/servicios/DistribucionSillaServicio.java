package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.DistribucionSilla;
import co.edu.uniquindio.unicine.entidades.Sala;

import java.util.List;

public interface DistribucionSillaServicio {

    DistribucionSilla obtenerDistribucionSilla(Integer codigo) throws Exception;

    DistribucionSilla registrarDistribucionSilla ( DistribucionSilla distribucionSilla) throws Exception;

    DistribucionSilla actualizarDistribucionSilla ( DistribucionSilla distribucionSilla) throws Exception;

    void eliminarDistribucionSilla (Integer codigo) throws Exception;

    List<DistribucionSilla> listarDistribucionSilla();

    Sala obtenerSala (Integer codigoDistribucion) throws Exception;
}
