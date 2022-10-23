package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.CuponCliente;
import co.edu.uniquindio.unicine.entidades.EstadoCliente;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
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
    public void obtener(){
        Optional<Cliente> clienteBuscado = clienteRepo.findById("123");
        Assertions.assertNotNull(clienteBuscado.orElse(null));
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
/*
    @Test
    public void eliminar(){
        Cliente clienteBuscado = clienteRepo.findById("344").orElse(null);
        clienteRepo.delete(clienteBuscado);
        Assertions.assertNull(clienteRepo.findById("344").orElse(null));
    }
*/

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar(){
        Cliente guardado = clienteRepo.findById("123").orElse(null);
        guardado.setNombre("Camilo");
        Cliente clienteNuevo = clienteRepo.save(guardado);
        Assertions.assertEquals("Camilo", clienteNuevo.getNombre());
    }

}
