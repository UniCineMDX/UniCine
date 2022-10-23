package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.repo.AdministradorTeatroRepo;
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
/**
 * Esta es la clase AdministradorTeatroTest
 */
public class AdministradorTeatroTest {
    @Autowired
    private AdministradorTeatroRepo administradorTeatroRepo;

    @Test
    @Sql("classpath:dataset.sql")
    /**
     * Este metodo test permite registrar un nuevo AdministradorTeatro
     */
    public void registrar() {
        AdministradorTeatro administradorTeatro = new AdministradorTeatro("12344", "Javier", "javier@gmail.com", "3ggd", "sst44ffhgegs5");
        AdministradorTeatro admiGuardado = administradorTeatroRepo.save(administradorTeatro);

        Assertions.assertEquals("Javier", admiGuardado.getNombre());
    }

    @Test
    @Sql("classpath:dataset.sql")
    /**
     * Este metodo test permite eliminar un AdministradorTeatro por medio de su ID
     */
    public void eliminar(){
        AdministradorTeatro buscado = administradorTeatroRepo.findById("98822").orElse(null);
        administradorTeatroRepo.delete(buscado);

        Assertions.assertNull(administradorTeatroRepo.findById("98822").orElse(null));
    }

    @Test
    @Sql("classpath:dataset.sql")
    /**
     * Este metodo test permite actualizar los datos de un AdministradorTeatro por medio de su ID
     */
    public void actualizar(){
        AdministradorTeatro guardado = administradorTeatroRepo.findById("98822").orElse(null);
        guardado.setCorreo("lala@gmail.com");
        AdministradorTeatro nuevo = administradorTeatroRepo.save(guardado);

        Assertions.assertEquals("lala@gmail.com", nuevo.getCorreo());
    }

    @Test
    @Sql("classpath:dataset.sql")
    /**
     * Este metodo test permite obtener un AdministradorTeatro por medio de su ID
     */
    public void obtener(){
        AdministradorTeatro buscado = administradorTeatroRepo.findByCorreo("juan1@gmail.com");
        System.out.println(buscado);
        Assertions.assertNotNull(buscado);
    }

    @Test
    @Sql("classpath:dataset.sql")
    /**
     * Este metodo test permite obtener todos los AdministradorTeatro que estan registrados en la base de datos
     */
    public void listar() {
        List<AdministradorTeatro> lista = administradorTeatroRepo.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTeatros() {
        List<Teatro> lista = administradorTeatroRepo.obtenerTeatrosAdmin("98822");
        lista.forEach(System.out::println);
    }
}
