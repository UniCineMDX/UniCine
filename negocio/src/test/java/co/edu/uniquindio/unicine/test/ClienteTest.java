package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicioImpl;
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
/**
 * Esta es la clase ClienteTest
 */
public class ClienteTest {
    @Autowired
    private ClienteRepo clienteRepo;
    private ClienteServicioImpl clienteServicio;


    /**
     * Este metodo permite registrar un cliente
     */
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarCliente(){

        Cliente cliente = Cliente.builder().cedula("129").nombre("Lina").correo("lina@gmail.com").password("1234").fotoUrl("grgrgr").build();

        try {
            Cliente clienteNuevo = clienteServicio.registrarCliente(cliente);

            System.out.println(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Este metodo permite obtener un cliente atraves de su cedula
     */
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerClientePorCedula() {

        try {
            Cliente cliente = clienteServicio.obtenerClientePorCedula("344");
            System.out.println(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Este metodo permite actualizar el nombre del cliente atraves de su cedula
     */
    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCliente(){

        try {
            Cliente cliente = clienteServicio.obtenerClientePorCedula("344");
            cliente.setNombre("Carmen");

            Cliente clienteNuevo = clienteServicio.actualizarCliente(cliente);
            System.out.println(clienteNuevo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Este metodo permite eliminar un cliente atraves de su cedula
     */
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCliente(){

        try {
            clienteServicio.eliminarCliente("344");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Este metodo permite listar los clientes
     */
    @Test
    @Sql("classpath:dataset.sql")
    public void listarClientes()  {

        List<Cliente> clientes = null;
        try {
            clientes = clienteServicio.listarClientes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        clientes.forEach(System.out::println);

    }

    /**
     * Este metodo permite listar los cupones de un cliente atraves de la cedula
     */
    @Test
    @Sql("classpath:dataset.sql")
    public void listarCuponesClientes()  {

        List<CuponCliente> cuponClientes = null;
        try {
            cuponClientes = clienteServicio.listarCuponesCliente("344");
        } catch (Exception e) {
            e.printStackTrace();
        }

        cuponClientes.forEach(System.out::println);

    }

    /**
     * Este metodo permite ver el historial de compras de un cliente atraves de la cedula
     */
    @Test
    @Sql("classpath:dataset.sql")
    public void historialCompras()  {

        List<Compra> historialCompras = null;
        try {
          historialCompras = clienteServicio.historialCompras("344");
        } catch (Exception e) {
            e.printStackTrace();
        }

        historialCompras.forEach(System.out::println);

    }

    /**
     * Este metodo permite ver el historial de compras redimidas de un cliente atraves de la cedula
     */
    @Test
    @Sql("classpath:dataset.sql")
    public void historialComprasRedimidas()  {

        List<Compra> historialComprasRedimidas = null;
        try {
            historialComprasRedimidas = clienteServicio.historialComprasRedimidas("344");
        } catch (Exception e) {
            e.printStackTrace();
        }

        historialComprasRedimidas.forEach(System.out::println);

    }


    /**
     * Este metodo permite ver el historial de compras no redimidas de un cliente atraves de la cedula
     */
    @Test
    @Sql("classpath:dataset.sql")
    public void historialCompraNoRedimidas()  {

        List<Compra> historialCompraNoRedimidas = null;
        try {
            historialCompraNoRedimidas = clienteServicio.historialCompraNoRedimidas("344");
        } catch (Exception e) {
            e.printStackTrace();
        }

        historialCompraNoRedimidas.forEach(System.out::println);

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

    @Test
    @Sql("classpath:dataset.sql")
    /**
     * Este metodo test permite obtener todos los Clientes que estan registrados en la base de datos

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
  */
}
