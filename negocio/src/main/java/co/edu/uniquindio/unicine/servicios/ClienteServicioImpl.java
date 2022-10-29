package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio{

    @Autowired
    private ClienteRepo clienteRepo;
    private PeliculaRepo peliculaRepo;
    private FuncionRepo funcionRepo;
    private CuponRepo cuponRepo;
    private CuponClienteRepo cuponClienteRepo;
    private ConfiteriaRepo confiteriaRepo;
    private CompraRepo compraRepo;
    private EntradaRepo entradaRepo;
    private CompraConfiteriaRepo compraConfiteriaRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo, PeliculaRepo peliculaRepo, FuncionRepo funcionRepo, CuponRepo cuponRepo, CuponClienteRepo cuponClienteRepo, ConfiteriaRepo confiteriaRepo, CompraRepo compraRepo, EntradaRepo entradaRepo, CompraConfiteriaRepo compraConfiteriaRepo) {
        this.clienteRepo = clienteRepo;
        this.peliculaRepo = peliculaRepo;
        this.funcionRepo = funcionRepo;
        this.cuponRepo = cuponRepo;
        this.cuponClienteRepo = cuponClienteRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.compraRepo = compraRepo;
        this.entradaRepo = entradaRepo;
        this.compraConfiteriaRepo = compraConfiteriaRepo;
    }

    @Override
    public Cliente login(String correo, String password) throws Exception{
        /*
        Cliente cliente = clienteRepo.comprobarAutenticacion(correo, password);

        if(cliente == null){
            throw new Exception(" Campos INCORRECTOS");
        }
        //validar  estado del cliente
        cliente = clienteRepo.obtenerPorEstado(cliente.getCedula(), true);
        if(cliente == null){
            throw new Exception("No se activado la cuenta para ingresar");
        }

        return cliente;

         */
        return  null;
    }


    //Metodo para buscar una pelicula por el genero

    public List<Pelicula> buscarPeliculaPorGenero(Genero genero) throws Exception {
        List<Pelicula> peliculaGuardada = peliculaRepo.obtenerPeliculasPorGenero(genero);

        if (peliculaGuardada.isEmpty()) {
            throw new Exception("La pelicula NO EXISTE");
        }

        return peliculaGuardada;
    }

    @Override
    public Cliente registrarCliente(Cliente cliente) throws Exception {
        return null;
    }

    @Override
    public Cliente obtenerClientePorCedula(Integer cedula) throws Exception {
        return null;
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) throws Exception {
        return null;
    }

    @Override
    public void eliminarCliente(String cedulaCliente) throws Exception {

        Cliente clientebuscadoCedula = clienteRepo.findByCedula(cedulaCliente);

        if(clientebuscadoCedula == null){
            throw new Exception("No existe un cliente con este numero de cedula "+cedulaCliente);
        }
        clienteRepo.delete(clientebuscadoCedula);
        System.out.println("El cliente fue eliminado");
    }

    @Override
    public List<Cliente> listarClientes() {
        return null;
    }

    @Override
    public void HistorialCompra(Integer codigoCliente) {

    }


    public Compra realizarCompra(Cliente cliente, List<Entrada> entradas, List<CompraConfiteria> compraConfiteria, MedioPago medioPago, Cupon cupon, Funcion funcion) throws Exception {

        Compra compra = new Compra();

        //verificar cliente,
        Optional<Cliente> clienteExiste = clienteRepo.findById(cliente.getCedula());
        if (clienteExiste.isEmpty()){
            throw new Exception("Cliente no existe");
        }

        //verificar que las sillas esten disponibles

        for (Entrada entrada : entradas ) {
            Entrada entradaOcupada= funcionRepo.verificarSilla(funcion.getCodigo(), entrada.getFila(), entrada.getColumna());
            if (entradas != null) {
                throw new Exception("Las entradas seleccionadas no estan disponibles");
            }
        }
        //redimir el cupon si no es null

        Optional<Cupon> cuponExiste = cuponRepo.findById(cupon.getCodigo());
        if (cuponExiste.isEmpty()) {
            throw new Exception("El cupon no existe");
        }
        boolean cuponCliente = false;

        List<Cupon> cuponesCliente = cuponRepo.obtenerCuponesCliente(cliente.getCedula());
        for (Cupon cup : cuponesCliente) {
            if (cupon.getCodigo() == cupon.getCodigo()) {
                cuponCliente = true;
            }
        }
        if (!cuponCliente) {
            throw new Exception("El cupon no es del cliente");
        }
        redimirCupon(cupon.getCodigo());

        //sumar los precios, aplicar el descuento

        Double valorTotal = calcularValorTotal(entradas, compraConfiteria);
        Double valorTotalConDescuento = calcularValorTotalConDescuento (valorTotal, cupon.getDescuento());

        //persiste la compra
        compra.setValorTotal(valorTotalConDescuento);
        compra.setFechaCompra(LocalDate.now());
        compraRepo.save(compra);

        //Mandar compra a todas las entrdas y las conprasConfiterias que lleagn
        for (Entrada entrada : entradas) {
            entrada.setCompra(compra);
            entradaRepo.save(entrada);
        }

        for (CompraConfiteria compConfi : compraConfiteria){
            compConfi.setCompra(compra);
            compraConfiteriaRepo.save(compConfi);
        }


        return compra;
    }


    public Double calcularValorTotal(List<Entrada> entradas,List<CompraConfiteria> compraConfiteria ){

        Double valorTotal = 0.0;

        for (CompraConfiteria cmConfi : compraConfiteria){
            valorTotal = valorTotal + cmConfi.getPrecio();
        }
        for (Entrada entrada : entradas){
            valorTotal = valorTotal + entrada.getPrecio();
        }
        System.out.println(valorTotal) ;
        return valorTotal ;
    }

    public Double calcularValorTotalConDescuento(Double valorTotal, Double descuento ){

        Double valorConDescuento = valorTotal-(valorTotal*descuento);
        return valorConDescuento ;
    }

    public boolean redimirCupon(Integer codigoCupon) throws Exception{
        CuponCliente cuponGuardado = cuponClienteRepo.buscarCuponClientePorCodigoCupon(codigoCupon);
        if(cuponGuardado == null) {
            throw new Exception("El cupon no existe");
        }
        cuponGuardado.setEstado(EstadoCupon.SIN_USAR);
        cuponClienteRepo.save(cuponGuardado);
        return true;
    }

    @Override
    public boolean cambiarContraseña(String correo, String passwordNueva) throws Exception {
        return false;
    }

    //Metodo para buscar una pelicula por el nombre

    public List<Pelicula> buscarPeliculaNombre(String nombre) throws Exception {
        List<Pelicula> peliculaGuardada = peliculaRepo.buscarPeliculaNombre(nombre);

        if (peliculaGuardada.isEmpty()) {
            throw new Exception("La pelicula NO EXISTE");
        }

        return (List<Pelicula>) peliculaGuardada.get(3);
    }



    public List<Pelicula> buscarPeliculaEstado(String nombre, EstadoPelicula estadoPelicula) throws Exception {
        List<Pelicula> peliculaGuardada = peliculaRepo.buscarPeliculaEstado(nombre, estadoPelicula);

        if (peliculaGuardada.isEmpty()) {
            throw new Exception("La pelicula NO EXISTE");
        }

        return (List<Pelicula>) peliculaGuardada.get(3);
    }


}

