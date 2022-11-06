package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdministradorTeatroServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
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
            Assertions.assertNotNull(administradorTeatro);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            Assertions.assertNotNull(teatro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void asignarPrecioFuncion(){
        try {
            Funcion funcion = admiTeatroServicio.asignarPrecioFuncion(1,2000.0);
            System.out.println(funcion);
            Assertions.assertNotNull(funcion);
        } catch (Exception e) {
            e.printStackTrace();
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
            Assertions.assertNotNull(teatroNuevo);
        } catch (Exception e) {
            e.printStackTrace();
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
            Assertions.assertEquals("barrio las lomas",teatroNuevo.getDireccion());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTeatro(){
        try {
            admiTeatroServicio.eliminarTeatro(1);
            Assertions.assertNull(admiTeatroServicio.obtenerTeatro(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTeatros(){
        try {
            List<Teatro>  teatros = admiTeatroServicio.listarTeatros();
            teatros.forEach(System.out::println);
            Assertions.assertNotNull(teatros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCiudadTeatro(){
        try {
            Ciudad ciudad = admiTeatroServicio.obtenerCiudadTeatro(1);
            System.out.println(ciudad);
            Assertions.assertNotNull(ciudad);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerAdmiTeatro() {
        try {
            AdministradorTeatro admiTeatro = admiTeatroServicio.obtenerAdmiTeatro(1);
            System.out.println(admiTeatro);
            Assertions.assertNotNull(admiTeatro);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarSalasTeatro() {
        try {
            List<Sala> salas = admiTeatroServicio.obtenerListaSalasTeatro(1);
            salas.forEach(System.out::println);
            Assertions.assertNotNull(salas);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     *  Metodos Test de servicios de Horario
     */
    @Test
    @Sql("classpath:dataset.sql")
    public void asignarHorarioFuncion() throws Exception {
        try {
            Funcion funcionAsignada= admiTeatroServicio.asignarHorarioFuncion(1,1);
            Horario horario = admiTeatroServicio.obtenerHorario(1);
            System.out.println(funcionAsignada);
            Assertions.assertEquals(horario,funcionAsignada.getHorario());
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
            Assertions.assertNotNull(horario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarHorario() {

        Horario horario = Horario.builder().dia("Lunes").hora("20:00").fechaInicio(LocalDate.of(2022,10,30)).fechaFin(LocalDate.of(2022,12,30)).build();

        try {
            Horario guardado = admiTeatroServicio.registrarHorario(horario);
            System.out.println(guardado);
            Assertions.assertNotNull(guardado);
        } catch (Exception e) {
            e.printStackTrace();
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
            Assertions.assertEquals("Martes",horarioNuevo.getDia());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarHorario() {
        try {
            admiTeatroServicio.eliminarHorario(1);
            Horario horario = admiTeatroServicio.obtenerHorario(1);
            Assertions.assertNull(horario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHorarios() {
        try {
            List<Horario> horarios = admiTeatroServicio.listarHorarios();
            horarios.forEach(System.out::println);
            Assertions.assertNotNull(horarios);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarFuncionesHorario() {
        try {
            List<Funcion> funciones = admiTeatroServicio.obtenerListaFuncionesHorario(1);
            funciones.forEach(System.out::println);
            Assertions.assertNotNull(funciones);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            Assertions.assertNotNull(funcion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registarFuncion() {
        try {
            Sala sala         = admiTeatroServicio.obtenerSala(1);
            Horario horario   = admiTeatroServicio.obtenerHorario(1);
            Pelicula pelicula = admiTeatroServicio.obtenerPeliculaFuncion(1);

            Funcion funcion      = Funcion.builder().precio(6500.0).pelicula(pelicula).horario(horario).sala(sala).build();
            Funcion funcionNueva = admiTeatroServicio.registrarFuncion(funcion);

            System.out.println(funcionNueva);
            Assertions.assertNotNull(funcionNueva);
        } catch (Exception e) {
            e.printStackTrace();
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
            Assertions.assertEquals(7000.0,funcionNueva.getPrecio());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarFuncion() {
        try {
            admiTeatroServicio.eliminarFuncion(1);
            Assertions.assertNotNull(admiTeatroServicio.obtenerFuncion(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarFunciones() {
        try {
            List<Funcion> funciones = admiTeatroServicio.listarFunciones();
            funciones.forEach(System.out::println);
            Assertions.assertNotNull(funciones);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculaFuncion() {
        try {
            Pelicula pelicula = admiTeatroServicio.obtenerPeliculaFuncion(1);
            System.out.println(pelicula);
            Assertions.assertNotNull(pelicula);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerHorarioFuncion() {
        try {
            Horario horario = admiTeatroServicio.obtenerHorarioFuncion(1);
            System.out.println(horario);
            Assertions.assertNotNull(horario);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerSalaFuncion() {
        try {
            Sala sala = admiTeatroServicio.obtenerSalaFuncion(1);
            System.out.println(sala);
            Assertions.assertNotNull(sala);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComprasFuncion() {
        try {
            List<Compra> compras = admiTeatroServicio.obtenerListaComprasFuncion(1);
            compras.forEach(System.out::println);
            Assertions.assertNotNull(compras);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            Assertions.assertNotNull(sala);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarSala() {
        try {
            DistribucionSilla distribucionSilla = admiTeatroServicio.obtenerDistribucionSilla(1);
            Teatro teatro = admiTeatroServicio.obtenerTeatro(1);

            Sala sala = Sala.builder().nombre("Sala10").teatro(teatro).distribucionSilla(distribucionSilla).build();
            Sala salaNueva = admiTeatroServicio.registrarSala(sala);

            System.out.println(salaNueva);
            Assertions.assertNotNull(salaNueva);
        } catch (Exception e) {
            e.printStackTrace();
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
            Assertions.assertEquals("Sala XD",salaNueva.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarSala() {

        try {
            admiTeatroServicio.eliminarSala(1);
            Assertions.assertNull(admiTeatroServicio.obtenerSala(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarSalas() {

        try {
            List<Sala> salas = admiTeatroServicio.listarSalas();
            salas.forEach(System.out::println);
            Assertions.assertNotNull(salas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTeatroSala() {
        try {
            Teatro teatro = admiTeatroServicio.obtenerTeatroSala(1);
            System.out.println(teatro);
            Assertions.assertNotNull(teatro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerDistribucionSillasSala() {
        try {
            DistribucionSilla distribucionSilla = admiTeatroServicio.obtenerDistribucionSillas(1);
            System.out.println(distribucionSilla);
            Assertions.assertNotNull(distribucionSilla);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarFuncionesSala() {
        try {
            List<Funcion>  funciones = admiTeatroServicio.obtenerFuncionesSala(1);
            funciones.forEach(System.out::println);
            Assertions.assertNotNull(funciones);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            Assertions.assertNotNull(distribucionSilla);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarDistribucionesSillas() {

       DistribucionSilla distribucionSilla = DistribucionSilla.builder().totalSillas(100).filas(10).columnas(10).esquema("eggrgr").build();

        try {
            DistribucionSilla distribucionSillaNuevo = admiTeatroServicio.registrarDistribucionSilla(distribucionSilla);
            System.out.println(distribucionSillaNuevo);
            Assertions.assertNotNull(distribucionSillaNuevo);
        } catch (Exception e) {
            e.printStackTrace();
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
            Assertions.assertEquals(49,distribucionSillaNueva.getTotalSillas());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarDistribucionesSillas() {
        try {
            admiTeatroServicio.eliminarDistribucionSilla(1);
            Assertions.assertNotNull(admiTeatroServicio.obtenerDistribucionSillas(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarDistribucionesSillas() {
        try {
            List<DistribucionSilla> distribucionSillas = admiTeatroServicio.listarDistribucionSillas();
            distribucionSillas.forEach(System.out::println);
            Assertions.assertNotNull(distribucionSillas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarSalasDistribucionesSillas() {
        try {
            List<Sala>  salas = admiTeatroServicio.obtenerSalasDistribucionSilla(1);
            salas.forEach(System.out::println);
            Assertions.assertNotNull(salas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}