package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.entidades.*;
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

    @Query("select t from Teatro t where t.ciudad = :nombreCiudad")
    List<Teatro>listar (String nombreCiudad);

    @Query("select t from Teatro t where t.codigo = :codigo")
    Teatro obtener(@Param("codigo") Integer codigo);

    Teatro findByCodigo(Integer codigo);

    @Query("select t from Teatro t where t.direccion = :direccion")
    Teatro obtener(@Param("direccion") String direccion);

    Teatro findByDireccion(String direccion);

    @Query("select t.ciudad from Teatro t where  t.codigo = :codigo")
    Ciudad obtenerCiudadTeatro(Integer codigo);

    @Query("select t.admiTeatro from Teatro t where t.codigo = :codigo")
    AdministradorTeatro obtenerAdmiTeatro(Integer codigo);

    @Query("select s from Teatro t join t.salas s where t.codigo = :codigo")
    List<Sala> obtenerSalasTeatro (Integer codigo);

    //Obtener los teatros de una ciudad
    @Query("select teatro.ciudad.codigo, teatro.ciudad.nombre, count(teatro) from Teatro teatro group by teatro.ciudad")
    List<Object[]> contarTeatrosPorCiudad();

}
