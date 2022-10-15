package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
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
public class AdministradorTeatroTest {


    @Autowired
    private AdministradorTeatroRepo administradorTeatroRepo;


    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {


        AdministradorTeatro administradorTeatro = new AdministradorTeatro("12344", "Javier", "javier@gmail.com", "3ggd", "sst44ffhgegs5");
        AdministradorTeatro admiGuardado = administradorTeatroRepo.save(administradorTeatro);

        Assertions.assertEquals("Javier", admiGuardado.getNombre());
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar(){

        AdministradorTeatro buscado = administradorTeatroRepo.findById("98822").orElse(null);
        administradorTeatroRepo.delete(buscado);

        Assertions.assertNull(administradorTeatroRepo.findById("98822").orElse(null));

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar(){

        AdministradorTeatro guardado = administradorTeatroRepo.findById("98822").orElse(null);
        guardado.setCorreo("lala@gmail.com");

        AdministradorTeatro nuevo = administradorTeatroRepo.save(guardado);

        Assertions.assertEquals("lala@gmail.com", nuevo.getCorreo());

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtener(){

        Optional<AdministradorTeatro> buscado = administradorTeatroRepo.findById("98822");

        Assertions.assertNotNull(buscado.orElse(null));

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<AdministradorTeatro> lista = administradorTeatroRepo.findAll();

        //for each para que no aparezcan todos pegados
        lista.forEach(System.out::println);

    }
}
