package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.DistribucionSilla;
import co.edu.uniquindio.unicine.entidades.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistribucionSillaRepo extends JpaRepository<DistribucionSilla, Integer>{

    DistribucionSilla findByCodigo (Integer codigo);

    @Query("select s from DistribucionSilla ds join ds.salas s where ds.codigo = :codigo")
    List<Sala> obtenerSalasDistribucionSilla ( Integer codigo);
}
