package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdministradorTeatroServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
public class AdministradorTeatroServicioTest {

    /**
     *  Atributos de la clase
     */
    @Autowired
    private AdministradorTeatroServicio admiTeatroServicio;


    /**
     *  Metodos Test de servicios de administrador de teatro
     */

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerAdministradorTeatro(){

        try {
            AdministradorTeatro administradorTeatro = admiTeatroServicio.obtenerAdmiTeatro("0836544");
            System.out.println(administradorTeatro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarAdministradorTeatro(){

        AdministradorTeatro administradorTeatro = AdministradorTeatro.builder().cedula("129999").nombre("Juan").correo("jj@gmail.com").password("rge3").fotoUrl("grgrgr").build();

        try {
            AdministradorTeatro admiNuevo = admiTeatroServicio.registrarAdmiTeatro(administradorTeatro);

            System.out.println(administradorTeatro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarAdministradorTeatro(){

        try {
            AdministradorTeatro admiTeatro = admiTeatroServicio.obtenerAdmiTeatro("98822");
            admiTeatro.setNombre("Luis");

            AdministradorTeatro admiTeatroNuevo = admiTeatroServicio.actualizarAdmiTeatro(admiTeatro);

            System.out.println(admiTeatroNuevo);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarAdministradorTeatro(){

        try {
            admiTeatroServicio.eliminarAdmiTeatro("98822");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarAdministradoresTeatros(){

        List<AdministradorTeatro> admiTeatros = admiTeatroServicio.listarAdmiTeatros();

        admiTeatros.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTeatrosAdmiTeatros(){

        List<Teatro> teatros = admiTeatroServicio.obtenerListaTeatros("98822");

        teatros.forEach(System.out::println);

    }


    /**
     *  Metodos Test de servicios de teatro
     */

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTeatro(){

        try {
            Teatro teatro = admiTeatroServicio.obtenerTeatro(1);
            System.out.println(teatro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarTeatro(){

        try {
           AdministradorTeatro admiTeatro = admiTeatroServicio.obtenerAdmiTeatro("98822");
            Ciudad ciudad = admiTeatroServicio.obtenerCiudadTeatro(1);
            Teatro teatro = Teatro.builder().ciudad(ciudad).direccion("barrio norte").telefono("235555").admiTeatro(admiTeatro).build();

           Teatro teatroNuevo = admiTeatroServicio.registrarTeatro(teatro);
           System.out.println(teatroNuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarTeatro(){

        try {
            Teatro teatro = admiTeatroServicio.obtenerTeatro(1);
            teatro.setDireccion("barrio las lomas");

            Teatro teatroNuevo = admiTeatroServicio.actualizarTeatro(teatro);
            System.out.println(teatroNuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTeatro(){

        try {
            admiTeatroServicio.eliminarTeatro(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTeatros(){

       List<Teatro> teatros = admiTeatroServicio.listarTeatros();

       teatros.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCiudadTeatro(){

        try {
            Ciudad ciudad = admiTeatroServicio.obtenerCiudadTeatro(1);
            System.out.println(ciudad);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerAdmiTeatro() {

        try {
            AdministradorTeatro admiTeatro = admiTeatroServicio.obtenerAdmiTeatro(1);
            System.out.println(admiTeatro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarSalasTeatro() {

        List<Sala> salas = admiTeatroServicio.obtenerListaSalasTeatro(1);

        salas.forEach(System.out::println);
    }


    /**
     *  Metodos Test de servicios de Horario
     */

    @Test
    @Sql("classpath:dataset.sql")
    public void asignarHorarioFuncion() throws Exception {
        try {
            Funcion funcionObtenida =admiTeatroServicio.obtenerFuncion(1);
            Horario horarioObtenido =admiTeatroServicio.obtenerHorario(1);
            funcionObtenida.setHorario(horarioObtenido);
            Funcion funcionAsignada= admiTeatroServicio.asignarHorarioFuncion(funcionObtenida,horarioObtenido);
            System.out.println(funcionAsignada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerHorario() {

        try {
           Horario horario = admiTeatroServicio.obtenerHorario(1);
            System.out.println(horario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarHorario() {

        Horario horario = Horario.builder().dia("Lunes").hora("20:00").fechaInicio(LocalDate.of(2022,10,30)).fechaFin(LocalDate.of(2022,12,30)).build();

        try {
            Horario guardado = admiTeatroServicio.registrarHorario(horario);
            System.out.println(guardado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarHorario() {

        try {
            Horario horario = admiTeatroServicio.obtenerHorario(1);
            horario.setDia("Martes");

            Horario horarioNuevo = admiTeatroServicio.actualizarHorario(horario);
            System.out.println(horarioNuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarHorario() {

        try {
            admiTeatroServicio.eliminarHorario(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHorarios() {

        List<Horario> horarios = admiTeatroServicio.listarHorarios();

        horarios.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarFuncionesHorario() {

        List<Funcion> funciones = admiTeatroServicio.obtenerListaFuncionesHorario(1);

        funciones.forEach(System.out::println);

    }


    /**
     *  Metodos Test de servicios de administrador de funcion
     */

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncion() {

        try {
            Funcion funcion = admiTeatroServicio.obtenerFuncion(1);
            System.out.println(funcion);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registarFuncion() {

        try {
            Pelicula pelicula = admiTeatroServicio.obtenerPeliculaFuncion(1);
            Horario horario = admiTeatroServicio.obtenerHorario(1);
            Sala sala = admiTeatroServicio.obtenerSala(1);

            Funcion funcion = Funcion.builder().precio(6500.0).pelicula(pelicula).horario(horario).sala(sala).build();

            Funcion funcionNueva = admiTeatroServicio.registrarFuncion(funcion);

            System.out.println(funcionNueva);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarFuncion() {

        try {
            Funcion funcion = admiTeatroServicio.obtenerFuncion(1);
            funcion.setPrecio(7000.0);

            Funcion funcionNueva = admiTeatroServicio.actualizarFuncion(funcion);

            System.out.println(funcionNueva);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarFuncion() {

        try {
            admiTeatroServicio.eliminarFuncion(1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarFunciones() {

        List<Funcion> funciones = admiTeatroServicio.listarFunciones();

        funciones.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculaFuncion() {

        try {
            Pelicula pelicula = admiTeatroServicio.obtenerPeliculaFuncion(1);

            System.out.println(pelicula);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerHorarioFuncion() {

        try {
            Horario horario = admiTeatroServicio.obtenerHorarioFuncion(1);

            System.out.println(horario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerSalaFuncion() {

        try {
            Sala sala = admiTeatroServicio.obtenerSalaFuncion(1);

            System.out.println(sala);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComprasFuncion() {

        List<Compra> compras = admiTeatroServicio.obtenerListaComprasFuncion(1);

        compras.forEach(System.out::println);

    }


    /**
     *  Metodos Test de servicios de sala
     */


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerSala() {

        try {
            Sala sala = admiTeatroServicio.obtenerSala(1);
            System.out.println(sala);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarSala() {

        try {
            DistribucionSilla distribucionSilla = admiTeatroServicio.obtenerDistribucionSilla(1);
            Teatro teatro = admiTeatroServicio.obtenerTeatro(1);

            Sala sala = Sala.builder().nombre("Sala10").estadoSala(EstadoSala.HABILITADA).teatro(teatro).distribucionSilla(distribucionSilla).build();

            Sala salaNueva = admiTeatroServicio.registrarSala(sala);

            System.out.println(salaNueva);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarSala() {

        try {
            Sala sala = admiTeatroServicio.obtenerSala(1);
            sala.setNombre("Sala XD");

            Sala salaNueva = admiTeatroServicio.actualizarSala(sala);

            System.out.println(salaNueva);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarSala() {

        try {
            admiTeatroServicio.eliminarSala(1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarSalas() {

        List<Sala> salas = admiTeatroServicio.listarSalas();

        salas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTeatroSala() {

        try {
            Teatro teatro = admiTeatroServicio.obtenerTeatroSala(1);

            System.out.println(teatro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerDistribucionSillasSala() {

        try {
            DistribucionSilla distribucionSilla = admiTeatroServicio.obtenerDistribucionSillas(1);

            System.out.println(distribucionSilla);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarFuncionesSala() {

        List<Funcion> funciones = admiTeatroServicio.obtenerFuncionesSala(1);

        funciones.forEach(System.out::println);

    }


    /**
     *  Metodos Test de servicios de distribucion de sillas
     */

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerDistribucionesSillas() {

        try {
            DistribucionSilla distribucionSilla = admiTeatroServicio.obtenerDistribucionSilla(1);

            System.out.println(distribucionSilla);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarDistribucionesSillas() {

       DistribucionSilla distribucionSilla = DistribucionSilla.builder().totalSillas(100).filas(10).columnas(10).esquema("eggrgr").build();

        try {
            DistribucionSilla distribucionSillaNuevo = admiTeatroServicio.registrarDistribucionSilla(distribucionSilla);

            System.out.println(distribucionSillaNuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarDistribucionesSillas() {

        try {
            DistribucionSilla distribucionSilla = admiTeatroServicio.obtenerDistribucionSilla(1);
            distribucionSilla.setFilas(7);
            distribucionSilla.setColumnas(7);
            distribucionSilla.setTotalSillas(49);

            DistribucionSilla distribucionSillaNueva = admiTeatroServicio.actualizarDistribucionSilla(distribucionSilla);

            System.out.println(distribucionSillaNueva);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarDistribucionesSillas() {

        try {
            admiTeatroServicio.eliminarDistribucionSilla(1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarDistribucionesSillas() {

        List<DistribucionSilla> distribucionSillas = admiTeatroServicio.listarDistribucionSillas();

        distribucionSillas.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarSalasDistribucionesSillas() {

        List<Sala> salas = admiTeatroServicio.obtenerSalasDistribucionSilla(1);

        salas.forEach(System.out::println);

    }

}