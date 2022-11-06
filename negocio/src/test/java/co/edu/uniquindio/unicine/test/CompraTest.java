package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Entrada;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
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

    @Autowired
    private ClienteRepo clienteRepo;


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
    public void contarComprasCliente(){

        System.out.println("hola");
        Integer numCompras = compraRepo.contarComprasCliente("123");
        System.out.println(numCompras);

    }


}
