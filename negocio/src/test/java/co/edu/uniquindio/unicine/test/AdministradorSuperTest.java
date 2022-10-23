package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.AdministradorSuper;
import co.edu.uniquindio.unicine.repo.AdministradorSuperRepo;
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
    @Autowired
    private AdministradorSuperRepo administradorSuperRepo;

    @Test
    @Sql("classpath:dataset.sql")
    /**
     * Este metodo test permite registrar un nuevo administradorSuper
     */
    public void listarAdministradores() {

    }

    @Test
    @Sql("classpath:dataset.sql")
    /**
     * Este metodo test permite eliminar un administradorSuper por medio de su ID
     */
    public void eliminar() {
        AdministradorSuper buscado = administradorSuperRepo.findById("12344").orElse(null);
        administradorSuperRepo.delete(buscado);

        Assertions.assertNull(administradorSuperRepo.findById("12344").orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    /**
     * Este metodo test permite actualizar los datos de un administradorSuper por medio de su ID
     */
    public void actualizar() {
        AdministradorSuper guardado = administradorSuperRepo.findById("12344").orElse(null);
        guardado.setCorreo("lala@gmail.com");

        AdministradorSuper nuevo = administradorSuperRepo.save(guardado);

        Assertions.assertEquals("lala@gmail.com", nuevo.getCorreo());
    }

    @Test
    @Sql("classpath:dataset.sql")
    /**
     * Este metodo test permite obtener un administradorSuper por medio de su ID
     */
    public void obtener() {
        Optional<AdministradorSuper> buscado = administradorSuperRepo.findById("12344");

        Assertions.assertNotNull(buscado.orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    /**
     * Este metodo test permite obtener todos los administradorSuper que estan registrados en la base de datos
     */
    public void listar() {
        List<AdministradorSuper> lista = administradorSuperRepo.findAllOrderByNombre();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    /**
     * Este test metodo permite obtener un administradorSuper por medio de su correo
     */
    public void obtenerPorCorreo() {
        AdministradorSuper admiSuper = administradorSuperRepo.findByCorreo("pepito@gmail.com");
        Assertions.assertNotNull(admiSuper);
        System.out.println(admiSuper);
    }

    @Test
    @Sql("classpath:dataset.sql")
    /**
     * Este metodo test permite comprobar la autenticacion de un administradorSuper por su correo y contraseña
     */
    public void comprobarAutenticacion() {
        AdministradorSuper admiSuper = administradorSuperRepo.findByCorreoAndPassword("pedro@gmail.com", "bcbdbdb");
        Assertions.assertNotNull(admiSuper);
        System.out.println(admiSuper);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void paginador() {
        List<AdministradorSuper> lista = administradorSuperRepo.findAll(PageRequest.of(0, 2)).toList();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void ordenarRegistrosPorNombre() {
        List<AdministradorSuper> lista = administradorSuperRepo.findAll(Sort.by("nombre"));
        List<AdministradorSuper> lista2 = administradorSuperRepo.findAll(PageRequest.of(0, 2, Sort.by("nombre"))).toList();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerURL() {
        List<AdministradorSuper> lista = administradorSuperRepo.obtenerUrl("dggs35353",PageRequest.of(0, 2, Sort.by("nombre")));
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCedula() {
        String string = administradorSuperRepo.obtenerCodioAdmin("ana");
        System.out.println(string);
    }

}