package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.repo.CiudadRepo;
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
public class CiudadTest {


    @Autowired
    private CiudadRepo ciudadRepo;


    @Test
    @Sql("classpath:dataset.sql")
    public void registrar(){

        Ciudad ciudad = new Ciudad("Armenia");
        Ciudad guardado = ciudadRepo.save(ciudad);

        Assertions.assertEquals("Armenia", guardado.getNombre());

    }



    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar(){

        Ciudad buscado = ciudadRepo.findById(1).orElse(null);
        ciudadRepo.delete(buscado);

        Assertions.assertNull(ciudadRepo.findById(1).orElse(null));

    }



    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar(){

        Ciudad guardado = ciudadRepo.findById(1).orElse(null);
        guardado.setNombre("Pereira");

        Ciudad nuevo = ciudadRepo.save(guardado);

        Assertions.assertEquals("Pereira", nuevo.getNombre());

    }




    @Test
    @Sql("classpath:dataset.sql")
    public void obetener(){

        Optional<Ciudad> buscado = ciudadRepo.findById(1);

        Assertions.assertNotNull(buscado.orElse(null));

    }



    @Test
    @Sql("classpath:dataset.sql")
    public void listar(){

        List<Ciudad>ciudades= ciudadRepo.findAll();

        //for each para que no aparezcan todos pegados
        ciudades.forEach(System.out::println);

    }




    @Test
    @Sql("classpath:dataset.sql")
    public void contarTeatrosPorCiudad(){

        List<Object[]>teatros = ciudadRepo.contarTeatros();

        teatros.forEach(o ->
                System.out.println(o[0] + "," + o[1] + ","+ o[2])
        );



    }
}
