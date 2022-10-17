package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.AdministradorSuper;
import co.edu.uniquindio.unicine.entidades.DistribucionSilla;
import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.repo.DistribucionSillaRepo;
import co.edu.uniquindio.unicine.repo.SalaRepo;
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
public class SalaTest {

    @Autowired
    private SalaRepo salaRepo;


    @Autowired
    private DistribucionSillaRepo distribucionSillaRepo;


    @Autowired
    private TeatroRepo teatroRepo;



    @Test
    @Sql("classpath:dataset.sql")
    public void registrar(){

        DistribucionSilla distribucionSilla = distribucionSillaRepo.findById(1).get();

        Teatro teatro = teatroRepo.findById(1).get();

        Sala sala = new Sala("sala1",distribucionSilla,teatro );
        Sala guardado = salaRepo.save(sala);

        System.out.println(guardado);

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar(){

        Sala buscado = salaRepo.findById(1).orElse(null);
        salaRepo.delete(buscado);

        Assertions.assertNull(salaRepo.findById(1).orElse(null));

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar(){

        Sala guardado = salaRepo.findById(1).orElse(null);
        guardado.setNombre("lucia");

        Sala nuevo = salaRepo.save(guardado);

        Assertions.assertEquals("lucia", nuevo.getNombre());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener(){

        Optional<Sala> buscado = salaRepo.findById(1);

        Assertions.assertNotNull(buscado.orElse(null));

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<Sala> lista = salaRepo.findAll();

        //for each para que no aparezcan todos pegados
        lista.forEach(System.out::println);

    }
}
