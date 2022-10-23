package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorSuper;
import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeatroRepo extends JpaRepository<Teatro, Integer>{


    //Este consulta retorna una lista de todos los teatros dado el nombre de una ciudad
    @Query("select t from Teatro t where t.ciudad.nombre = :ciudad")
    List<Teatro> listaTeatros(String ciudad);

    //Obtener los teatros de una ciudad
    @Query("select teatro.ciudad.codigo, teatro.ciudad.nombre, count(teatro) from Teatro teatro group by teatro.ciudad")
    List<Object[]> contarTeatrosPorCiudad();

}
