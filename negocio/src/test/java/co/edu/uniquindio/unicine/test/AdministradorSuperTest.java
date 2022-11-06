package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.AdministradorSuperRepo;
import co.edu.uniquindio.unicine.servicios.AdminSuperServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
/**
 * Esta es la clase AdministradorSuperTest
 */
public class AdministradorSuperTest {


    private AdminSuperServicio adminSuperServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void listarAdministradoresSuper() {
        try {
            List<AdministradorSuper> listaAdmin = adminSuperServicio.listarAdministradores();
            listaAdmin.forEach(System.out::println);
            Assertions.assertNotNull(listaAdmin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarAdministradoresOrdenadosNombre() {
        try {
            List<AdministradorSuper> listaAdminOrden = adminSuperServicio.listarAdministradoresOrdenados();
            //listaAdmin.forEach(System.out::println);
            Assertions.assertNotNull(listaAdminOrden);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearAdminTeatro() {

        AdministradorTeatro adminTeatro = new AdministradorTeatro("123","Eminem","eminem@gmailc.com","213","urlFoto");

        try {
            AdministradorTeatro administradorTeatro= adminSuperServicio.crearAdminTeatro(adminTeatro);
            System.out.println(administradorTeatro);
            Assertions.assertNotNull(administradorTeatro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarAdminTeatro() {

        AdministradorTeatro adminTeatro = new AdministradorTeatro("8","Ricardo","juan1@gmail.com","213","urlFoto");

        try {
            AdministradorTeatro administradorTeatroActualizado = adminSuperServicio.actualizarDatosAdminTeatro(adminTeatro);
            System.out.println(administradorTeatroActualizado);
            Assertions.assertEquals(adminTeatro,administradorTeatroActualizado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerAdminTeatroCedula() {
        try {
            AdministradorTeatro administradorTeatro = adminSuperServicio.obtenerAdminTeatroCedula("1");
            System.out.println(administradorTeatro);
            Assertions.assertNotNull(administradorTeatro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerAdminTeatroCorreo() {
        try {
            AdministradorTeatro administradorTeatro = adminSuperServicio.obtenerAdminTeatroCorreo("juan1@gmail.com");
            System.out.println(administradorTeatro);
            Assertions.assertNotNull(administradorTeatro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarAdminTeatro() {

        try {
            adminSuperServicio.eliminarAdminTeatro("5");
            AdministradorTeatro administradorTeatro= adminSuperServicio.obtenerAdminTeatroCedula("5");
            System.out.println(administradorTeatro);
            Assertions.assertNull(administradorTeatro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarAdministradoresTeatro() {
        try {
            List<AdministradorTeatro> listarAdminTeatros = adminSuperServicio.listarAdminTeatros();
            // listaAdmin.forEach(System.out::println);
            Assertions.assertNotNull(listarAdminTeatros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    @Test
    @Sql("classpath:dataset.sql")
    public void asignarAdminTeatro() {
        try {
            AdministradorTeatro admin = adminSuperServicio.obtenerAdminTeatroCedula("1");
            Teatro teatroAsignado =  adminSuperServicio.asignarAdministradorTeatro(1,"1");
            System.out.println(teatroAsignado.getAdmiTeatro().getCedula());
            Assertions.assertEquals(admin,teatroAsignado.getAdmiTeatro());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
    @Test
    @Sql("classpath:dataset.sql")
    public void desasignarAdminTeatro() {
        try {
            Teatro teatroDesasignado =  adminSuperServicio.desasignarAdministradorTeatro(1,"1");
            Assertions.assertNull(teatroDesasignado.getAdmiTeatro());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearPelicula() {

        Pelicula pelicula = new Pelicula("El regreso del aguacate","el aguacate regresa","sd","as","asd","Creada");
        try {
            Pelicula peliculaCreada =  adminSuperServicio.crearPelicula(pelicula);
            System.out.println(peliculaCreada);
            Assertions.assertEquals(pelicula,peliculaCreada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarPelicula() {

        Pelicula pelicula = new Pelicula("El regreso del aguacate","el aguacate regresa","sd","as","asd","Creada");
        try {
            Pelicula peliculaCreada =  adminSuperServicio.crearPelicula(pelicula);
            Pelicula peliculaActualizada =  adminSuperServicio.actualizarDatosPelicula(peliculaCreada.getCodigo(),peliculaCreada);
            System.out.println(peliculaActualizada);
            Assertions.assertEquals(pelicula,peliculaActualizada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculaCodigo() {

        try {
            Pelicula peliculaEncontrada =  adminSuperServicio.obtenerPeliculaCodigo(1);
            System.out.println(peliculaEncontrada);
            Assertions.assertNotNull(peliculaEncontrada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculaNombre() {

        try {
            Pelicula peliculaEncontrada =  adminSuperServicio.obtenerPeliculaNombre("Btman");
            System.out.println(peliculaEncontrada);
            Assertions.assertNotNull(peliculaEncontrada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarPelicula() {

        try {
            adminSuperServicio.eliminarPelicula(1);
            Pelicula peliculaEncontrada =  adminSuperServicio.obtenerPeliculaCodigo(1);
            Assertions.assertNull(peliculaEncontrada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculas() {
        try {
            List<Pelicula> listaPeliculas = adminSuperServicio.listarPeliculas();
            //listaPeliculas.forEach(System.out::println);
            Assertions.assertNotNull(listaPeliculas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void cambiarEstadoPelicula() {
        try {
            Pelicula peliculaEstado = adminSuperServicio.cambiarEstadoPelicula(1,EstadoPelicula.PREVENTA);
            System.out.println(peliculaEstado);
            Assertions.assertEquals(EstadoPelicula.PREVENTA,peliculaEstado.getEstadoPelicula());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearConfiteria() {

        Confiteria confiteria = new Confiteria();

        try {
            Pelicula peliculaEstado = adminSuperServicio.cambiarEstadoPelicula(1,EstadoPelicula.PREVENTA);
            System.out.println(peliculaEstado);
            Assertions.assertEquals(EstadoPelicula.PREVENTA,peliculaEstado.getEstadoPelicula());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     @Test
     @Sql("classpath:dataset.sql")
     public void crearTeatro() {
     Teatro teatroAux = new Teatro(new Ciudad("Armenia"),"Calle sexta #11","3125679835");
     try {
     Teatro teatro = adminSuperServicio.crearTeatro(teatroAux);
     System.out.println(teatro);
     Assertions.assertNotNull(teatro);
     } catch (Exception e) {
     e.printStackTrace();
     }
     }
     **/
}
