package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.dto.FuncionDTO;
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
    private PeliculaRepo peliculaRepo;

    @Autowired
    private HorarioRepo horarioRepo;

    @Autowired
    private SalaRepo salaRepo;

    //@Autowired
    //private AdminTeatroServicioImpl adminTeatroServicioImpl;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculaFuncion(){
        String nombrePelicula = funcionRepo.obtenerNombrePelicula(5);
        Assertions.assertEquals("Batman", nombrePelicula);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculas(){
        List<Pelicula> listaPelicula = funcionRepo.obtenerPelicula();
        listaPelicula.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFunciones(){
        List<FuncionDTO> listaFunciones = funcionRepo.listarFunciones(2);
        listaFunciones.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionesSinCompra(){
        List<Funcion> listaFuncion = funcionRepo.obtenerFuncionesSinCompra(3);
        System.out.println(listaFuncion);
    }

    /*
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionesTeatro(){
        List<Funcion> listaFunciones = funcionRepo.obtenerFuncionesTeatro(1, "", "");
        System.out.println(listaFunciones);
    }*/


    @Test
    @Sql("classpath:dataset.sql")
    public void ListaEntradas(){
        List<Entrada> listaEntradas = funcionRepo.listaEntradas(4);
        System.out.println(listaEntradas);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void ListasCompras() {
        List<Compra> listaCompras = funcionRepo.listaCompra(3);
        System.out.println(listaCompras);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void verificarSillas() {
        List<Entrada> listaEntradas = funcionRepo.verificarSillas(1, 2, 2);
        System.out.println(listaEntradas);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void verificarSilla() {
        Entrada entrada = funcionRepo.verificarSilla(1, 3, 3);
        System.out.println(entrada);
    }





}
