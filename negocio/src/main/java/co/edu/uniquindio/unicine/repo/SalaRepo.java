package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaRepo extends JpaRepository<Sala,Integer>{
    @Query("select s from Sala s where s.codigo = :codigo")
    Sala obtener(@Param("codigo") Integer codigo);
    Sala findByCodigo(Integer codigo);

    @Query("select s.teatro from Sala s where s.codigo = :codigo")
    Teatro obtenerTeatroSala (Integer codigo);

    @Query("select s.distribucionSilla from Sala s where s.codigo = :codigo")
    DistribucionSilla obtenerDistribucionSillaSala(Integer codigo);

    @Query("select fun from Sala s join s.funciones fun where s.codigo = :codigo")
    List<Funcion> obtenerFuncionesSala(Integer codigo);

}
