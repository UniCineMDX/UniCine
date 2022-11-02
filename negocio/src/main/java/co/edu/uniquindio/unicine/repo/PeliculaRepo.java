package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.dto.HorarioSalaDTO;
import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepo extends JpaRepository<Pelicula, Integer>{

    @Query("select p from Pelicula p where p.estadoPelicula = :estado")
    List<Pelicula> obtenerPeliculasPorEstado(EstadoPelicula estado);

    @Query("select p from Pelicula p where p.genero = :genero")
    List<Pelicula> obtenerPeliculasPorGenero(Genero genero);

    @Query("select p from Pelicula p join p.funciones f where f.sala.teatro.ciudad.codigo = :codigoCiudad")
    List<Pelicula> obtenerPeliculasCiudad(Integer codigoCiudad);

    @Query("select p from Pelicula p join p.funciones f where f.sala.teatro.ciudad.codigo = :codigoCiudad and f.sala.teatro.codigo = :codigoTeatro")
    List<Pelicula> obtenerPeliculasCiudadTeatro(Integer codigoCiudad, Integer codigoTeatro);

    @Query("select p from Pelicula p where p.nombre like concat('%', :nombre, '%')")
    List<Pelicula> buscarPeliculaNombre(String nombre);

    @Query("select p from Pelicula p where p.nombre like concat('%', :nombre, '%') and p.estadoPelicula = :estado")
    List<Pelicula> buscarPeliculaEstado(String nombre, EstadoPelicula estado);

    @Query("select new co.edu.uniquindio.unicine.dto.HorarioSalaDTO(funcion.horario, funcion.sala) from Pelicula p join p.funciones funcion where p.codigo = :codigoPelicula and funcion.sala.teatro.codigo = :codigoTeatro")
    List<HorarioSalaDTO> listarHorario(Integer codigoPelicula, Integer codigoTeatro);

    @Query("select p from Pelicula p where p.genero =:genero  order by p.nombre asc")
    List<Pelicula> listarPeliculas(Genero genero);

    @Query("select f from Pelicula p join p.funciones f where p.codigo = :codigoPelicula and f.horario.dia = :dia")
    List<Funcion> listarFuncionesDiaPelicula(Integer codigoPelicula, String dia);

    @Query("select f from Funcion f order by f.horario.fechaInicio")
    List<Funcion> listarFuncionHorarioAsendente();

    @Query("select f from Funcion f order by f.horario.fechaInicio desc ")
    List<Funcion> listarFuncionHorarioDesedente();

    Pelicula findByNombre(String nombre);

    Pelicula findByCodigo(Integer codigo);





}
