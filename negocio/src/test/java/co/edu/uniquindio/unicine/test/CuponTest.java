package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.EstadoCupon;
import co.edu.uniquindio.unicine.repo.CuponRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CuponTest {

    @Autowired
    private CuponRepo cuponRepo;

    /*
    @Test
    @Sql("classpath:dataset.sql")
    public void registrar(){

        Cupon cupon = new Cupon(25.0,"Clientes nuevos", LocalDate.of(2022,12,30), EstadoCupon.SIN_USAR);
        Cupon guardado = cuponRepo.save(cupon);

        System.out.println(guardado);

    }

    public CuponTest(CuponRepo cuponRepo) {
        this.cuponRepo = cuponRepo;
    }

    */


}
