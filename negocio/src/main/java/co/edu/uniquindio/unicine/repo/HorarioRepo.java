package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorSuper;
import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepo extends JpaRepository<Horario, Integer>{

    @Query("select h from Horario h where h.codigo = :codigo")
    Horario obtener(@Param("codigo") Integer codigo);
    Horario findByCodigo(Integer correo);

    @Query("select f from Horario h join h.funciones f where h.codigo = :codigo")
    List<Funcion> obtenerListaFunciones(Integer codigo);
}
