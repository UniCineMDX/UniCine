package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Transactional
@SpringBootTest
/**
 * Esta es la clase ClienteTest
 */
public class ClienteTest {

    @Autowired
    private ClienteServicioImpl clienteServicio;

    @Autowired
    private CuponClienteRepo cuponClienteRepo;

    @Autowired
    CompraConfiteriaRepo compraConfiteriaRepo;

    @Autowired
    CompraRepo compraRepo;

    @Autowired
    private FuncionRepo funcionRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void login() {
        try {
            Persona persona = clienteServicio.login("lala@gmail.com","123");
            System.out.println(persona);
            Assertions.assertNotNull(persona);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este metodo permite registrar un cliente
     */
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarCliente(){

        Cliente cliente = Cliente.builder().cedula("129").nombre("Lina").correo("lina@gmail.com").password("1234").fotoUrl("grgrgr").build();
        try {
            System.out.println(cliente);
            Cliente clienteNuevo = clienteServicio.registrarCliente(cliente);
            System.out.println(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }










    /*
    @Test
    @Sql("classpath:dataset.sql")/**
     * Este metodo test permite registrar un nuevo Cliente

    public void registrar(){
        Cliente cliente = new Cliente("123", "Laura", "lala@gmail.com", "efef", "gegegege");
        Cliente guardado = clienteRepo.save(cliente);
        System.out.println(guardado);

    }
    @Test
    @Sql("classpath:dataset.sql")
    public void obtener(){
        Optional<Cliente> clienteBuscado = clienteRepo.findById("123");
        Assertions.assertNotNull(clienteBuscado.orElse(null));
    }
    */

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCompras(){
        List<Compra>compras = clienteRepo.obtenerCompras("lala@gmail.com");
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

    @Test
    public void eliminar()  {
        try {
            clienteServicio.eliminarCliente("123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertNull(clienteRepo.findById("123").orElse(null));
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar(){
        Cliente guardado = clienteRepo.findById("123").orElse(null);
        guardado.setNombre("Camilo");
        Cliente clienteNuevo = clienteRepo.save(guardado);
        Assertions.assertEquals("Camilo", clienteNuevo.getNombre());
    }









}
