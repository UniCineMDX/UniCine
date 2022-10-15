package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.CuponCliente;
import co.edu.uniquindio.unicine.entidades.EstadoCliente;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;




    @Test
    @Sql("classpath:dataset.sql")
    public void registrar(){

        Cliente cliente = new Cliente("123", "Laura", "lala@gmail.com", "efef", "gegegege", EstadoCliente.ACTIVO);
        Cliente guardado = clienteRepo.save(cliente);

        System.out.println(guardado);

    }




    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCompras(){

        List<Compra>compras = clienteRepo.obtenerCompras("lala@gmail.com");
        //List<Compra>compras = clienteRepo.obtenerCompras2("lala@gmail.com");

        compras.forEach(System.out::println);

    }



    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCupones(){

        List<CuponCliente>cupones = clienteRepo.obtenerCupones("lala@gmail.com");

        cupones.forEach(System.out::println);

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComprasPorCliente(){

        List<Object[]>compras = clienteRepo.obtenerComprasTodos();

        compras.forEach(o ->
                System.out.println(o[0] + "," + o[1])
        );

    }

}
