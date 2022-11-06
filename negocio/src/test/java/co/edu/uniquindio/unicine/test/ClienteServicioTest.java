package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminSuperServicioImpl;
import co.edu.uniquindio.unicine.servicios.AdministradorTeatroServicioImpl;
import co.edu.uniquindio.unicine.servicios.ClienteServicioImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Transactional
@SpringBootTest
public class ClienteServicioTest {

    @Autowired
    private ClienteServicioImpl clienteServicio;
    @Autowired
    private AdminSuperServicioImpl adminSuperServicio;
    @Autowired
    private AdministradorTeatroServicioImpl administradorTeatro;

    @Test
    @Sql("classpath:dataset.sql")
    public void login() {
        try {
            Persona persona = clienteServicio.login("lala@gmail.com","123");
            System.out.println(persona);
            Assertions.assertNotNull(persona);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarCliente(){

        Cliente cliente = Cliente.builder().cedula("129").nombre("Lina").correo("lina@gmail.com").password("1234").fotoUrl("grgrgr").build();
        try {
            Cliente clienteNuevo = clienteServicio.registrarCliente(cliente);
            System.out.println(clienteNuevo);
            Assertions.assertNotNull(clienteNuevo);
        } catch (Exception e) {
            e.printStackTrace();
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
            Assertions.assertEquals("Carmen",clienteNuevo.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
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
            Cliente cliente = clienteServicio.obtenerClientePorCedula("344");
            Assertions.assertNull(cliente);
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarClientes()  {
        try {
            List<Cliente> clientes = clienteServicio.listarClientes();
            clientes.forEach(System.out::println);
            Assertions.assertNotNull(clientes);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            cuponClientes.forEach(System.out::println);
            Assertions.assertNotNull(cuponClientes);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            historialCompras.forEach(System.out::println);
            Assertions.assertNotNull(historialCompras);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            historialComprasRedimidas.forEach(System.out::println);
            Assertions.assertNotNull(historialComprasRedimidas);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            historialCompraNoRedimidas.forEach(System.out::println);
            Assertions.assertNotNull(historialCompraNoRedimidas);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCuponSeleccionado() {
        try {
            Cliente cliente = clienteServicio.obtenerClientePorCedula("123");
            CuponCliente cupon = clienteServicio.obtenerCuponSeleccionado(cliente, 1);
            Assertions.assertNotNull(cupon);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void cambiarPassword() {
        try {
            clienteServicio.cambiarPassword("pedro@gmail.com", "8426");
            Cliente cliente = clienteServicio.obtenerClientePorCedula("344");
            Assertions.assertEquals("8426",cliente.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void solicitarCambiarPassword() {
        try {
            clienteServicio.solicitarCambiarPassword("lala@gmail.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void realizarCompra()  {

        List<String> lista = new ArrayList<>();
        lista.add("12");
        lista.add("21");

        try {
            Confiteria confiteria = adminSuperServicio.obtenerConfiteria(1);
            Cliente cliente = clienteServicio.obtenerClientePorCedula("123");
            CuponCliente cuponCliente = cliente.getCupones().get(1);
            MedioPago medioPago = MedioPago.TARJETA_CREDITO;
            List<Entrada> entradas = clienteServicio.crearEntradas(lista);
            List<CompraConfiteria> compraConfiterias = new ArrayList<>();
            compraConfiterias.add(new CompraConfiteria(500.0,5,confiteria));

            Compra compraNueva = clienteServicio.realizarCompra("123", entradas,compraConfiterias, medioPago, cuponCliente.getCodigo(), 1);
            Assertions.assertNotNull(compraNueva);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void realizarCompraConfiteria()  {
        try {
            MedioPago medioPago = MedioPago.TARJETA_CREDITO;
            List<List<Integer>> compras = new ArrayList<>();
            List<Integer> confi = new ArrayList<>();
            confi.add(1);
            confi.add(5);
            compras.add(confi);

            Compra compraNueva = clienteServicio.realizarCompraConfiteria("123",compras,medioPago,1);
            Assertions.assertNotNull(compraNueva);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculas(){
        try {
            List<Pelicula>  listarPeliculas = clienteServicio.listarPeliculas();
            System.out.println(listarPeliculas);
            Assertions.assertNotNull(listarPeliculas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarSillasFuncion(){
        try {
            Funcion funcion = administradorTeatro.obtenerFuncion(1);
            HashMap<String,Boolean> listaSillas = clienteServicio.listaSillasFuncion(1);
            listaSillas.forEach((s, aBoolean) -> System.out.println(s+" "+aBoolean));
            Assertions.assertEquals(listaSillas.size(),(funcion.getSala().getDistribucionSilla().getTotalSillas()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculasCiudad(){
        try {
            List<Pelicula> listaPeliculasCiudad = clienteServicio.listarPeliculasCiudad(1);
            listaPeliculasCiudad.forEach(System.out::println);
            Assertions.assertNotNull(listaPeliculasCiudad);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculasCiudadTeatro(){
        try {
            List<Pelicula> listaPeliculasCiudadTeatro = clienteServicio.listarPeliculasCiudadTeatro(1,1);
            listaPeliculasCiudadTeatro.forEach(System.out::println);
            Assertions.assertNotNull(listaPeliculasCiudadTeatro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarFechaFuncionesPelicula(){
        try {
            List<LocalDate> listaFechaFuncionesPelicula = clienteServicio.listarFechaFuncionesPelicula(1,1);
            listaFechaFuncionesPelicula.forEach(System.out::println);
            Assertions.assertNotNull(listaFechaFuncionesPelicula);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHorariosFechaFuncionesPelicula(){
        try {
            LocalDate localDate = LocalDate.parse("2021-01-01");
            List<String> listaHorariosFechaFuncionesPelicula = clienteServicio.listarHorariosFechaFuncionesPelicula(1,localDate,1);
            listaHorariosFechaFuncionesPelicula.forEach(System.out::println);
            Assertions.assertNotNull(listaHorariosFechaFuncionesPelicula);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculaNombre(){
        try {
            List<Pelicula> pelicula = clienteServicio.buscarPeliculaNombre("batman");
            pelicula.forEach(System.out::println);
            Assertions.assertNotNull(pelicula);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculaEstado(){
        try {
            List<Pelicula> peliculas = clienteServicio.buscarPeliculaEstado(EstadoPelicula.PREVENTA);
            peliculas.forEach(System.out::println);
            Assertions.assertNotNull(peliculas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculaPorGenero(){
        try {
            List<Pelicula> peliculas = clienteServicio.buscarPeliculaPorGenero(Genero.COMEDIA);
            peliculas.forEach(System.out::println);
            Assertions.assertNotNull(peliculas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
