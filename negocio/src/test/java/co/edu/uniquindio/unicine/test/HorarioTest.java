package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Horario;
import co.edu.uniquindio.unicine.repo.HorarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HorarioTest {

    @Autowired
    private HorarioRepo horarioRepo;


    @Test
    @Sql("classpath:dataset.sql")
    public void registrar(){

        Horario horario = new Horario("Lunes", "13:00", LocalDate.of(2021, 01, 01), LocalDate.of(2021, 12, 31));
        Horario guardado = horarioRepo.save(horario);

        //Assertions.assertEquals(9, guardado.getDia());
        System.out.println(guardado);

    }
}
