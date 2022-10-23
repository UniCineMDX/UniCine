package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * Esta es la interface CiudadRepo
 */
public interface CiudadRepo extends JpaRepository<Ciudad, Integer>{

    /**
     * Este metodo permite obtener el numero de teatros que pertenecen a un administrador de teatro
     * @return
     */
    @Query("select c.codigo, c.nombre, count(t) from Ciudad c inner join c.teatros t group by c.codigo")
    List<Object[]> contarTeatros();

}
