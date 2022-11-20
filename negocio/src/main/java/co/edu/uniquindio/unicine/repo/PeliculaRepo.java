package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.dto.HorarioSalaDTO;
import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepo extends JpaRepository<Pelicula, Integer>{


    //List<Pelicula> findByNombre(String nombrePelicula);

    @Query("select p from Pelicula p where p.nombre = :nombrePelicula")
    Pelicula buscarPeliculaPorNombre(String nombrePelicula);

    @Query("select p from Pelicula p where p.estadoPelicula = :estado")
    List<Pelicula> obtenerPeliculasPorEstado(EstadoPelicula estado);

    @Query("select p from Pelicula p where :genero member of p.generos")
    List<Pelicula> obtenerPeliculasPorGenero(Genero genero);

    @Query("select distinct p from Pelicula p join p.funciones f where f.sala.teatro.ciudad.codigo = :codigoCiudad and p.estadoPelicula = :estado")
    List<Pelicula> obtenerPeliculasCiudad(Integer codigoCiudad, EstadoPelicula estado);

    @Query("select distinct p from Pelicula p join p.funciones f where f.sala.teatro.ciudad.codigo = :codigoCiudad and f.sala.teatro.codigo = :codigoTeatro")
    List<Pelicula> obtenerPeliculasCiudadTeatro(Integer codigoCiudad, Integer codigoTeatro);

    @Query("select p from Pelicula p where p.nombre like concat('%', :nombre, '%')")
    List<Pelicula> buscarPeliculaNombre(String nombre);

    @Query("select p from Pelicula p where p.nombre like concat('%', :nombre, '%') and p.estadoPelicula = :estado")
    List<Pelicula> buscarPeliculaEstado(String nombre, EstadoPelicula estado);

    @Query("select p from Pelicula p where :genero member of p.generos order by p.nombre asc")
    List<Pelicula> listarPeliculas(Genero genero);

    @Query("select f from Pelicula p join p.funciones f where p.codigo = :codigoPelicula and f.horario.dia = :dia")
    List<Funcion> listarFuncionesDiaPelicula(Integer codigoPelicula, String dia);

    @Query("select distinct  f.horario.fechaInicio from Funcion f where f.pelicula.codigo = :codigoPelicula and f.sala.teatro.codigo = :codigoTeatro")
    List<LocalDate> listarFechaFuncion(Integer codigoPelicula,Integer codigoTeatro);

    @Query("select f.horario.hora from Funcion f where f.pelicula.codigo = :codigoPelicula and f.horario.fechaInicio = :fechaPelicula and f.sala.teatro.codigo = :codigoTeatro")
    List<String> listarHorasFuncion(Integer codigoPelicula, LocalDate fechaPelicula, Integer codigoTeatro);

    @Query("select f from Funcion f where f.horario.fechaFin = :fecha and f.horario.hora = :hora and f.sala.teatro.codigo = :codigoTeatro")
    Funcion obtenerFuncionHorario(String hora,LocalDate fecha, Integer codigoTeatro);

    Pelicula findByNombre(String nombre);

    Pelicula findByCodigo(Integer codigo);


    @Query("select c.entradas from Funcion f join f.compras c where f.codigo = :codigo")
    List<Entrada> obtenerEntradasFuncion(Integer codigo);

    @Query("select p from Pelicula  p where p.nombre = :nombre")
    List<Pelicula> obtenerPeliculasNombre(String nombre);
}
