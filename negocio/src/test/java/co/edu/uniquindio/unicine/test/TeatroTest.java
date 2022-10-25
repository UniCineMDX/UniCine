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
    private CiudadRepo ciudadRepo;
    private AdministradorTeatroRepo administradorTeatroRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTeatroCiudad() {
        List<Teatro> listaTeatrosCiudad = teatroRepo.listaTeatros("Pereira");
        Assertions.assertEquals(3, listaTeatrosCiudad.size());
        System.out.println(listaTeatrosCiudad);
    }
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
    public void contarTeatros(){
        List<Object[]> listaCiudades = teatroRepo.contarTeatrosPorCiudad();
        listaCiudades.forEach( o ->
                System.out.println(o [0] + "," + o[1] + "," + o[2])
        );
    }


}
