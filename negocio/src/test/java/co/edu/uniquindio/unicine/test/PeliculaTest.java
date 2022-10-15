package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.repo.PeliculaRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaTest {

    @Autowired
    private PeliculaRepo peliculaRepo;



    @Test
    @Sql("classpath:dataset.sql")
    public void registrar(){

        Pelicula pelicula = new Pelicula("Batman", "pelicula de accion", "fggrgrgr", "hhhhrhr",EstadoPelicula.PREVENTA, "andres,juan roberto", Genero.COMEDIA);
        Pelicula guardado = peliculaRepo.save(pelicula);

        System.out.println(guardado);
    }



    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculas(){

        List<Pelicula> peliculas = peliculaRepo.buscarPeliculas("Harry", EstadoPelicula.PREVENTA);

        peliculas.forEach(System.out::println);
    }
}
