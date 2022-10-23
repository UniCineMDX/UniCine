package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaRepo extends JpaRepository<Sala,Integer>{

    //@Query("select s from Sala h where s.nombre = :nombre")
    //Sala obtenerSalaPorNombre(@Param("nombre") String nombre);
    //Sala findByCodigo(Integer codigo);

    //@Query("select s from Sala s where s.estadoSala =:estadoSala ")
    //List<Sala> listarSalas(EstadoSala estadoSala);


}
