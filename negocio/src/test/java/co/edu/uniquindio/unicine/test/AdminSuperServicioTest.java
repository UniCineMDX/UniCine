package co.edu.uniquindio.unicine.test;
import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.TeatroRepo;
import co.edu.uniquindio.unicine.servicios.AdminSuperServicio;
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
public class AdminSuperServicioTest {

    @Autowired
    private AdminSuperServicio adminSuperServicio;
    @Autowired
    private TeatroRepo teatroRepo;

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
            listaAdminOrden.forEach(System.out::println);
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
            listarAdminTeatros.forEach(System.out::println);
            Assertions.assertNotNull(listarAdminTeatros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void asignarAdminTeatro() {
        try {
            Teatro teatro = teatroRepo.findByCodigo(5);
            AdministradorTeatro administradorTeatro =  adminSuperServicio.asignarAdministradorTeatro(5,"1");
            administradorTeatro.getTeatros().forEach(System.out::println);
            Assertions.assertEquals(administradorTeatro,teatro.getAdmiTeatro());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void desasignarAdminTeatro() {
        try {
            Teatro teatroDesasignado  =  adminSuperServicio.desasignarAdministradorTeatro(1,"1");
            AdministradorTeatro admin = adminSuperServicio.obtenerAdminTeatroCedula("1");
            admin.getTeatros().forEach(System.out::println);
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
            listaPeliculas.forEach(System.out::println);
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

        Confiteria confiteria = new Confiteria("Combo 1", 1500.0,"urlImagen");

        try {
            Confiteria confiServicio = adminSuperServicio.crearConfiteria(confiteria);
            System.out.println(confiteria);
            Assertions.assertNotNull(confiServicio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarConfiteria() {
        try {
            Confiteria confiteria = adminSuperServicio.obtenerConfiteria(1);
            confiteria.setPrecio(80000.0);
            Confiteria confiServicio = adminSuperServicio.actualizarDatosConfiteria(confiteria);
            System.out.println(confiteria);
            Assertions.assertEquals(80000.0,confiServicio.getPrecio());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarConfiteria() {
        try {
            adminSuperServicio.eliminarConfiteria(1);
            Confiteria confiteria = adminSuperServicio.obtenerConfiteria(1);
            Assertions.assertNull(confiteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarConfiteria() {
        try {
            List<Confiteria> listaConfiterias = adminSuperServicio.listarConfiterias();
            listaConfiterias.forEach(System.out::println);
            Assertions.assertNotNull(listaConfiterias);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearCupon() {

        LocalDate local = LocalDate.parse("2021-01-01");
        Cupon cupon = new Cupon(10.0,"por que si",local);

        try {
            Cupon cuponService = adminSuperServicio.crearCupon(cupon);
            Assertions.assertNotNull(cuponService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCupon() {
        try {
            Cupon cupon = adminSuperServicio.obtenerCupon(1);
            cupon.setDescuento(80.0);
            Cupon cuponServicio = adminSuperServicio.actualizarCupon(cupon);
            System.out.println(cuponServicio);
            Assertions.assertEquals(80.0,cuponServicio.getDescuento());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCupon() {
        try {
            adminSuperServicio.eliminarCupon(1);
            Cupon cupon = adminSuperServicio.obtenerCupon(1);
            Assertions.assertNull(cupon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCupon() {
        try {
            List<Cupon> listaCupones = adminSuperServicio.listarCupones();
            listaCupones.forEach(System.out::println);
            Assertions.assertNotNull(listaCupones);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void asignarCuponCliente() {
        try {
            CuponCliente cuponCliente = adminSuperServicio.asignarCuponCliente(1,"123");
            Assertions.assertNotNull(cuponCliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
