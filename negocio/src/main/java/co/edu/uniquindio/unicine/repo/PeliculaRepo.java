package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.dto.HorarioSalaDTO;
import co.edu.uniquindio.unicine.entidades.AdministradorSuper;
import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepo extends JpaRepository<Pelicula, Integer>{

    List<Pelicula> findByNombrePelicula(String nombrePelicula);

    @Query("select p from Pelicula p where p.nombre = :nombrePelicula")
    Pelicula buscarPeliculaPorNombre(String nombrePelicula);

    @Query("select p from Pelicula p where p.estadoPelicula = :estado")
    List<Pelicula> obtenerPeliculasPorEstado(EstadoPelicula estado);

    @Query("select p from Pelicula p where p.genero = :genero")
    List<Pelicula> obtenerPeliculasPorGenero(Genero genero);

    @Query("select p from Pelicula p where p.nombre like concat('%', :nombre, '%') and p.estadoPelicula = :estado")
    List<Pelicula> buscarPelicula(String nombre, EstadoPelicula estado);

    @Query("select p from Pelicula p where p.nombre like concat('%', :nombre, '%') and p.estadoPelicula = :estado")
    Optional<Pelicula> buscarPeliculaEstado(String nombre, Boolean estado);

    @Query("select new co.edu.uniquindio.unicine.dto.HorarioSalaDTO(funcion.horario, funcion.sala) from Pelicula p join p.funciones funcion where p.codigo = :codigoPelicula and funcion.sala.teatro.codigo = :codigoTeatro")
    List<HorarioSalaDTO> listarHorario(Integer codigoPelicula, Integer codigoTeatro);

    @Query("select p from Pelicula p where p.genero =:genero  order by p.nombre asc")
    List<Pelicula> listarPeliculas(Genero genero);



}
