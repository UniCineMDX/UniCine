package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorSuper;
import co.edu.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeatroRepo extends JpaRepository<Teatro, Integer>{

    @Query("select t from Teatro t where t.ciudad = :nombreCiudad")
    List<Teatro>listar (String nombreCiudad);

    @Query("select t from Teatro t where t.codigo = :codigo")
    Teatro obtener(@Param("codigo") Integer codigo);
    Teatro findByCodigo(Integer codigo);

    @Query("select t from Teatro t where t.direccion = :direccion")
    Teatro obtener(@Param("direccion") String direccion);
    Teatro findByDireccion(String direccion);



}
