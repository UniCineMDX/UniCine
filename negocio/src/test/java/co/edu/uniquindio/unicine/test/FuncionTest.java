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
import java.util.Optional;

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


    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar(){

        Funcion buscado = funcionRepo.findById(1).orElse(null);
        funcionRepo.delete(buscado);

        Assertions.assertNull(funcionRepo.findById(1).orElse(null));

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar(){

        Funcion guardado = funcionRepo.findById(1).orElse(null);
        guardado.setPrecio(9000.0);

        Funcion nuevo = funcionRepo.save(guardado);

        Assertions.assertEquals(9000.0, nuevo.getPrecio());

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtener(){

        Optional<Funcion> buscado = funcionRepo.findById(1);

        Assertions.assertNotNull(buscado.orElse(null));

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<Funcion> lista = funcionRepo.findAll();

        //for each para que no aparezcan todos pegados
        lista.forEach(System.out::println);

    }

}
