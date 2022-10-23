package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.AdministradorTeatroRepo;
import co.edu.uniquindio.unicine.repo.CiudadRepo;
import co.edu.uniquindio.unicine.repo.TeatroRepo;
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
public class TeatroTest {

    @Autowired
    private TeatroRepo teatroRepo;

    @Autowired
    private AdministradorTeatroRepo administradorTeatroRepo;


    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar(){

        //Es mejor obtener el admin y la ciudad desde el dataset
        AdministradorTeatro admiGuardado = administradorTeatroRepo.findById("98822").get();
        Ciudad ciudad = ciudadRepo.findById(1).get();

        Teatro teatro = new Teatro("Calle sexta #12", "3125679834", admiGuardado, ciudad);
        Teatro teatroGuardado = teatroRepo.save(teatro);

        System.out.println(teatroGuardado);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar(){

        Teatro buscado = teatroRepo.findById(1).orElse(null);
        teatroRepo.delete(buscado);

        Assertions.assertNull(teatroRepo.findById(1).orElse(null));

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar(){

        Teatro guardado = teatroRepo.findById(1).orElse(null);
        guardado.setTelefono("758439");

        Teatro nuevo = teatroRepo.save(guardado);

        Assertions.assertEquals("758439", nuevo.getTelefono());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener(){

        Optional<Teatro> buscado = teatroRepo.findById(1);

        Assertions.assertNotNull(buscado.orElse(null));

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<Teatro> lista = teatroRepo.findAll();
        //for each para que no aparezcan todos pegados
        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPorDireccion(){

        Teatro buscado = teatroRepo.findByDireccion("Calle sexta #12");
        Assertions.assertNotNull(buscado);
        System.out.println(buscado);

    }

}
