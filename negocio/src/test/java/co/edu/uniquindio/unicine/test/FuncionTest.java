package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.FuncionRepo;
import co.edu.uniquindio.unicine.repo.HorarioRepo;
import co.edu.uniquindio.unicine.repo.PeliculaRepo;
import co.edu.uniquindio.unicine.repo.SalaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FuncionTest {

    @Autowired
    private FuncionRepo funcionRepo;


    @Autowired
    private HorarioRepo horarioRepo;


    @Autowired
    private PeliculaRepo peliculaRepo;


    @Autowired
    private SalaRepo salaRepo;




    @Test
    @Sql("classpath:dataset.sql")
    public void registrar(){

        Horario horario = horarioRepo.findById(1).get();

        Pelicula pelicula = peliculaRepo.findById(1).get();

        Sala sala = salaRepo.findById(1).get();

        Funcion funcion = new Funcion(6.500,horario,pelicula,sala);
        Funcion guardado = funcionRepo.save(funcion);

        System.out.println(guardado);

    }



    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculaFuncion(){

        String nombrePelicula = funcionRepo.obtenerNombrePelicula(1);

        Assertions.assertEquals("Corre", nombrePelicula);

        System.out.println(nombrePelicula);

    }




    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionesSinCompra(){

        List<Funcion>funciones = funcionRepo.obtenerFuncionesSinCompra(1);

        funciones.forEach(System.out::println);


    }

}
