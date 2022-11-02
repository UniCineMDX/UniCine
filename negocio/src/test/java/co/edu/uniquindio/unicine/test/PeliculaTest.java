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
    public void obtenerFuncionesDia(){
        List<Funcion> funciones = peliculaRepo.listarFuncionesDiaPelicula(1,"Lunes");
        funciones.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listasHorario(){
        List<HorarioSalaDTO> listaHoraraio = peliculaRepo.listarHorario(1, 1);
        System.out.println(listaHoraraio);
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

        List<Funcion> listaFunciones = peliculaRepo.listarFuncionHorarioAsendente();

        listaFunciones.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionesFechaDesendente(){

        List<Funcion> listaFunciones = peliculaRepo.listarFuncionHorarioDesedente();

        listaFunciones.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculaCodigo(){

        Pelicula pelicula = peliculaRepo.findByCodigo(5);

        Assertions.assertNotNull(pelicula);
    }

}
