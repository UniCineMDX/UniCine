package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.dto.HorarioSalaDTO;
import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.PeliculaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaTest {

    @Autowired
    private PeliculaRepo peliculaRepo;

    @Test
    @Sql("classpath:dataset.sql")

    public void obtenerPeliculasPorEstado() {
        List<Pelicula> peliculas = peliculaRepo.obtenerPeliculasPorEstado(EstadoPelicula.PREVENTA);
        peliculas.forEach(System.out::println);
    }

    public void registrar(){

        Pelicula pelicula = new Pelicula("Batman", "pelicula de accion", "fggrgrgr", "hhhhrhr","Preventa", "Creada");
        Pelicula guardado = peliculaRepo.save(pelicula);

        System.out.println(guardado);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculasPorGenero() {
        List<Pelicula> peliculas = peliculaRepo.obtenerPeliculasPorGenero(Genero.TERROR);
        peliculas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculaNombre(){
        List<Pelicula> peliculas = peliculaRepo.buscarPeliculaNombre("batman");
        peliculas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculaEstado(){
        List<Pelicula> peliculas = peliculaRepo.buscarPeliculaEstado("batman", EstadoPelicula.PREVENTA);
        peliculas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculaCiudad(){
        List<Pelicula> peliculas = peliculaRepo.obtenerPeliculasCiudad(2);
        peliculas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculas(){
        List<Pelicula> listaPeliculas = peliculaRepo.listarPeliculas(Genero.TERROR);
        System.out.println(listaPeliculas);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionesFechaAscendente(){

        List<LocalDate> listaFechaFuncion = peliculaRepo.listarFechaFuncion(1,1);

        listaFechaFuncion.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionesFechaDesendente(){

        LocalDate local = LocalDate.parse("2021-01-01");

        List<String> listaFunciones = peliculaRepo.listarHorasFuncion(1,local,1);

        listaFunciones.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionFechaHora(){

        LocalDate local = LocalDate.parse("2021-01-01");

        Funcion funcion = peliculaRepo.obtenerFuncionHorario("13:00",local,1);

        System.out.println(funcion);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerEntradasFuncion(){

        List<Entrada> entradas = peliculaRepo.obtenerEntradasFuncion(1);

        entradas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculaCodigo(){

        Pelicula pelicula = peliculaRepo.findByCodigo(5);

        Assertions.assertNotNull(pelicula);
    }

}
