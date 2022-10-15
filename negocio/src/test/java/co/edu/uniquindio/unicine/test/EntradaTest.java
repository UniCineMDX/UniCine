package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Entrada;
import co.edu.uniquindio.unicine.entidades.EstadoAsistencia;
import co.edu.uniquindio.unicine.repo.CompraRepo;
import co.edu.uniquindio.unicine.repo.EntradaRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EntradaTest {

    @Autowired
    private EntradaRepo entradaRepo;


    @Autowired
    private CompraRepo compraRepo;




    @Test
    @Sql("classpath:dataset.sql")
    public void registrar(){

        Compra compra = compraRepo.findById(1).get();

        Entrada entrada = new Entrada(6.000, 1, 1, EstadoAsistencia.ASISTIO, compra);
        Entrada guardado = entradaRepo.save(entrada);

        System.out.println(guardado);

    }
}
