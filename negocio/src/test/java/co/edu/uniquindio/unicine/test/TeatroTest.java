package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.EstadoTeatro;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.repo.AdministradorTeatroRepo;
import co.edu.uniquindio.unicine.repo.CiudadRepo;
import co.edu.uniquindio.unicine.repo.TeatroRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

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

        Teatro teatro = new Teatro("Calle sexta #12", "3125679834", EstadoTeatro.HABILITADO, admiGuardado, ciudad);
        Teatro teatroGuardado = teatroRepo.save(teatro);

        System.out.println(teatroGuardado);
    }
}
