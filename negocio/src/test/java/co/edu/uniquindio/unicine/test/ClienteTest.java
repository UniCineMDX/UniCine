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
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
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
    @Autowired
    private FuncionRepo funcionRepo;

    @Autowired
    private CompraRepo compraRepo;
    @Autowired
    private CuponClienteRepo cuponClienteRepo;
    @Autowired
    ConfiteriaRepo confiteriaRepo;
    @Autowired
    private CompraConfiteriaRepo compraConfiteriaRepo;

    @Autowired
    private EntradaRepo entradaRepo;

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
            Assertions.assertNotNull(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void login() {

        try {
            Cliente cliente = clienteServicio.login("lala@gmail.com","123");
            Assertions.assertNotNull(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCuponSeleccionado() {

        try {
            CuponCliente cupon = clienteServicio.obtenerCuponSeleccionado("123",1);
            Assertions.assertNotNull(cupon);
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

    @Test
    @Sql("classpath:dataset.sql")
    public void realizarCompra()  {

      Cliente cliente = clienteRepo.findByCedula("1253");
      Funcion funcion = funcionRepo.findByCodigo(1);
      CuponCliente cuponCliente = cuponClienteRepo.findByCodigo(1);
      MedioPago medioPago = MedioPago.TARJETA_CREDITO;
      List<CompraConfiteria> compraConfiterias = new ArrayList<>();
      List<Entrada>entradas = new ArrayList<>();

        try {
            Compra compraNueva = clienteServicio.realizarCompra(cliente, entradas, compraConfiterias, medioPago, cuponCliente, funcion);
            System.out.println(compraNueva);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void realizarCompraConfiteria()  {

        Cliente cliente = clienteRepo.findByCedula("123");
        CuponCliente cuponCliente = cuponClienteRepo.findByCodigo(1);
        MedioPago medioPago = MedioPago.TARJETA_CREDITO;

        CompraConfiteria compraConfiteria= compraConfiteriaRepo.findByCodigo(1);
        CompraConfiteria compraConfiteria1= compraConfiteriaRepo.findByCodigo(2);
        List<List<Integer>> compraConfiterias = new ArrayList<>();
        compraConfiterias.add((List<Integer>) compraConfiteria);
        compraConfiterias.add((List<Integer>) compraConfiteria1);



        try {
            Compra compra = Compra.builder().cliente(cliente).funcion(null).cuponCliente(cuponCliente).medioPago(medioPago).valorTotal(20000.0).build();
            Compra nueva =compraRepo.save(compra);

            Compra compraNueva = clienteServicio.realizarCompraConfiteria(cliente,compraConfiterias,medioPago,cuponCliente);
            System.out.println(compraNueva);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }



}
