package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.dto.HorarioSalaDTO;
import co.edu.uniquindio.unicine.entidades.AdministradorSuper;
import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
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

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculasPorGenero() {
        List<Pelicula> peliculas = peliculaRepo.obtenerPeliculasPorGenero(Genero.TERROR);
        peliculas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPelicula(){
        List<Pelicula> peliculas = peliculaRepo.buscarPelicula("batman", EstadoPelicula.PREVENTA);
        peliculas.forEach(System.out::println);
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


}
