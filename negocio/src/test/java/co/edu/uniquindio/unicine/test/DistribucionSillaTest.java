package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.DistribucionSilla;
import co.edu.uniquindio.unicine.repo.DistribucionSillaRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DistribucionSillaTest {

    @Autowired
    private DistribucionSillaRepo distribucionSillaRepo;




    @Test
    @Sql("classpath:dataset.sql")
    public void registrar(){

        DistribucionSilla distribucionSilla = new DistribucionSilla("weeqeqe", 90, 10, 10);
        DistribucionSilla guardado = distribucionSillaRepo.save(distribucionSilla);

        System.out.println(guardado);

    }
}
