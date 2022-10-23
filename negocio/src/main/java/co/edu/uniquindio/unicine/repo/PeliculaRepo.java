package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.dto.HorarioSalaDTO;
import co.edu.uniquindio.unicine.entidades.AdministradorSuper;
import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepo extends JpaRepository<Pelicula, Integer>{

    Pelicula findByNombre(String nombre);

    @Query("select p from Pelicula p where p.nombre like concat('%', :nombre, '%') and p.estadoPelicula = :estadoPelicula ")
    List<Pelicula> buscarPeliculas(String nombre, EstadoPelicula estadoPelicula);

    @Query("select new co.edu.uniquindio.unicine.dto.HorarioSalaDTO (f.horario, f.sala) from Pelicula p join p.funciones f where p.codigo = :codigoPelicula and f.sala.teatro.codigo = :codigoTeatro")
    List<HorarioSalaDTO>listarHorarios(Integer codigoPelicula, Integer codigoTeatro);

    @Query("select p from Pelicula p where p.codigo = :codigo")
    Pelicula obtener(@Param("codigo") Integer codigo);
    Pelicula findByCodigo(Integer codigo);

}
