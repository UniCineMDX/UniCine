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
public class AdministradorSuperTest {

    @Autowired
    private AdministradorSuperRepo administradorSuperRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void registrar() {


        AdministradorSuper administradorSuper = new AdministradorSuper("12344", "Javier", "javier@gmail.com", "3ggd", "sst44ffhgegs5");
        AdministradorSuper admiGuardado = administradorSuperRepo.save(administradorSuper);

        Assertions.assertEquals("Javier", admiGuardado.getNombre());
        System.out.println(admiGuardado);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar(){

        AdministradorSuper buscado = administradorSuperRepo.findById("12344").orElse(null);
        administradorSuperRepo.delete(buscado);

        Assertions.assertNull(administradorSuperRepo.findById("12344").orElse(null));

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar(){

        AdministradorSuper guardado = administradorSuperRepo.findById("12344").orElse(null);
        guardado.setCorreo("lala@gmail.com");

        AdministradorSuper nuevo = administradorSuperRepo.save(guardado);

        Assertions.assertEquals("lala@gmail.com", nuevo.getCorreo());

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtener(){

        Optional<AdministradorSuper> buscado = administradorSuperRepo.findById("12344");

        Assertions.assertNotNull(buscado.orElse(null));

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {

        List<AdministradorSuper> lista = administradorSuperRepo.findAll();

        //for each para que no aparezcan todos pegados
        lista.forEach(System.out::println);

    }



    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPorCorreo(){

        AdministradorSuper admiSuper = administradorSuperRepo.findByCorreo("pepito@gmail.com");
        Assertions.assertNotNull(admiSuper);
        System.out.println(admiSuper);

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void comprobarAutenticacion(){
        AdministradorSuper admiSuper = administradorSuperRepo.findByCorreoAndPassword("pedro@gmail.com","dvd335");
        Assertions.assertNotNull(admiSuper);
        System.out.println(admiSuper);

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void paginador(){
       List<AdministradorSuper>lista = administradorSuperRepo.findAll(PageRequest.of(0, 2)).toList();
        lista.forEach(System.out::println);

    }



    @Test
    @Sql("classpath:dataset.sql")
    public void ordenarRegistrosPorNombre(){
        List<AdministradorSuper>lista = administradorSuperRepo.findAll(Sort.by("nombre"));
        List<AdministradorSuper>lista2 = administradorSuperRepo.findAll(PageRequest.of(0, 2, Sort.by("nombre"))).toList();
        lista.forEach(System.out::println);

    }
}