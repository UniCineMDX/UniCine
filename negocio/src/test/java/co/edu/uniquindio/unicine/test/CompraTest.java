package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Entrada;
import co.edu.uniquindio.unicine.repo.CompraRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    @Autowired
    private CompraRepo compraRepo;


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCompras(){

        List<Compra>compras = compraRepo.obtenerCompras("lala@gmail.com");
        compras.forEach(System.out::println);

    }



    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerEntradas(){

       List<Entrada>entradas = compraRepo.obtenerEntradas(1);

        Assertions.assertEquals(2, entradas.size());
       entradas.forEach(System.out::println);

    }



    @Test
    @Sql("classpath:dataset.sql")
    public void contarCuponesRedimidosPorCliente(){


        List<Object[]>cupones = compraRepo.contarCuponesRedimidos();

        cupones.forEach(o ->
                System.out.println(o[0] + "," + o[1])
        );

    }


}
