package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminSuperServicioImpl implements AdminSuperServicio{

    private  AdministradorSuperRepo adminSuperRepo;
    private  AdministradorTeatroRepo adminTeatroRepo;
    private TeatroRepo teatroRepo;
    private  PeliculaRepo peliculaRepo;
    private  ConfiteriaRepo confiteriaRepo;
    private  CuponRepo cuponRepo;
    private  FuncionRepo funcionRepo;
    private ClienteRepo clienteRepo;
    private CuponClienteRepo cuponClienteRepo;
    private CompraConfiteriaRepo compraConfiteriaRepo;

    public AdminSuperServicioImpl(AdministradorSuperRepo adminSuperRepo,ClienteRepo clienteRepo, AdministradorTeatroRepo adminTeatroRepo,CuponClienteRepo cuponClienteRepo,CompraConfiteriaRepo compraConfiteriaRepo,FuncionRepo funcionRepo, TeatroRepo teatroRepo, PeliculaRepo peliculaRepo, ConfiteriaRepo confiteriaRepo, CuponRepo cuponRepo) {
        this.cuponRepo            = cuponRepo;
        this.teatroRepo           = teatroRepo;
        this.funcionRepo          = funcionRepo;
        this.clienteRepo          = clienteRepo;
        this.peliculaRepo         = peliculaRepo;
        this.confiteriaRepo       = confiteriaRepo;
        this.adminSuperRepo       = adminSuperRepo;
        this.adminTeatroRepo      = adminTeatroRepo;
        this.cuponClienteRepo     = cuponClienteRepo;
        this.compraConfiteriaRepo = compraConfiteriaRepo;

    }

    @Override
    public List<AdministradorSuper> listarAdministradores() throws Exception{

        List<AdministradorSuper> listaAdminSuper = adminSuperRepo.findAll();

        if(listaAdminSuper.isEmpty()){
            throw new Exception("La lista de super administradores esta vacia");
        }

        return listaAdminSuper;
    }
    @Override
    public List<AdministradorSuper> listarAdministradoresOrdenados()  throws Exception{

        List<AdministradorSuper> listaAdminSuper = adminSuperRepo.findAllOrderByNombre();

        if(listaAdminSuper.isEmpty()){
            throw new Exception("La lista de super administradores esta vacia");
        }

        return listaAdminSuper;
    }
    @Override
    public AdministradorSuper verificarRegistroAdminSuper(String correo, String password) throws Exception {

        AdministradorSuper adminSuper = adminSuperRepo.findByCorreoAndPassword(correo, password);

        if(adminSuper == null){
            throw new Exception("El correo o la contraseña son incorrectos");
        }

        return adminSuper;
    }
    @Override
    public AdministradorTeatro crearAdminTeatro(AdministradorTeatro adminTeatro) throws Exception {

        AdministradorTeatro buscadoCedula = adminTeatroRepo.findByCedula(adminTeatro.getCedula());
        AdministradorTeatro buscadoCorreo = adminTeatroRepo.findByCorreo(adminTeatro.getCorreo());

        if(buscadoCedula != null){
            throw new Exception("Ya esxite un administrador de teatro con el numero de cedula "+adminTeatro.getCedula());
        }
        if(buscadoCorreo !=  null){
            throw new Exception("Ya esxite un administrador de teatro con el correo "+adminTeatro.getCorreo());
        }

        return adminTeatroRepo.save(adminTeatro);
    }
    @Override
    public AdministradorTeatro actualizarDatosAdminTeatro(AdministradorTeatro adminTeatro) throws Exception {

        AdministradorTeatro buscadoCedula = adminTeatroRepo.findByCedula(adminTeatro.getCedula());
        AdministradorTeatro buscadoCorreo = adminTeatroRepo.findByCorreo(adminTeatro.getCorreo());

        if(buscadoCedula == null){
            throw new Exception("No esxite un administrador de teatro con la cedula "+adminTeatro.getCedula());
        }
        if(buscadoCorreo != null && !buscadoCorreo.getCorreo().equals(buscadoCedula.getCorreo())){
            throw new Exception("Ya esxite un administrador de teatro con el correo "+adminTeatro.getCorreo());
        }

        return adminTeatroRepo.save(adminTeatro);
    }
    @Override
    public AdministradorTeatro obtenerAdminTeatroCedula(String cedula) throws Exception {

        AdministradorTeatro adminTeatro = adminTeatroRepo.findByCedula(cedula);

        if(adminTeatro == null){
            throw new Exception("No se encontro un administrador de teatro con cedula "+cedula);
        }

        return adminTeatro;
    }
    @Override
    public AdministradorTeatro obtenerAdminTeatroCorreo(String correo) throws Exception {

        AdministradorTeatro adminTeatro = adminTeatroRepo.findByCorreo(correo);

        if(adminTeatro == null){
            throw new Exception("No se encontro un administrador de teatro con correo "+correo);
        }

        return adminTeatro;
    }
    @Override
    public void eliminarAdminTeatro(String cedula) throws Exception {

        AdministradorTeatro adminbuscadoCedula = adminTeatroRepo.findByCedula(cedula);

        if(adminbuscadoCedula == null){
            throw new Exception("Ya esxite un administrador de teatro con el numero de cedula "+cedula);
        }
        if(!adminbuscadoCedula.getTeatros().isEmpty()){
            throw new Exception("No se puede eliminar el administrador de teatro por que esta asignado a algunos teatros");
        }

        adminTeatroRepo.delete(adminbuscadoCedula);
        System.out.println("El administrador de teatro fue eliminado");
    }
    @Override
    public List<AdministradorTeatro> listarAdminTeatros() throws Exception {
        List<AdministradorTeatro> listaAdminteatro = adminTeatroRepo.findAll();

        if(listaAdminteatro.isEmpty()){
            throw new Exception("La lista de administradores de teatro esta vacia");
        }

        return listaAdminteatro;
    }
    @Override
    public Teatro asignarAdministradorTeatro(Integer codigoTeatro, String cedulaAdminTeatro) throws Exception {

        Teatro teatro                   = teatroRepo.findByCodigo(codigoTeatro);
        AdministradorTeatro adminTeatro = adminTeatroRepo.findByCedula(cedulaAdminTeatro);

        if(teatro == null){
            throw new Exception("No existe un teatro con codigo "+codigoTeatro);
        }
        if(adminTeatro == null){
            throw  new Exception("No existe un administrador de teatro con cedula "+cedulaAdminTeatro);
        }
        if(adminTeatro.getTeatros().contains(teatro)){
            throw  new Exception("El administrador de teatro con cedula "+cedulaAdminTeatro+" ya administra el teatro "+codigoTeatro);
        }
        if(teatro.getAdmiTeatro() != null){
            AdministradorTeatro adminTeatroAux = adminTeatroRepo.findByCedula(teatro.getAdmiTeatro().getCedula());
            if(adminTeatroAux.getTeatros().contains(teatro)){
                adminTeatroAux.getTeatros().remove(teatro);
                adminTeatroRepo.save(adminTeatroAux);
            }
        }

        adminTeatro.getTeatros().add(teatro);
        teatro.setAdmiTeatro(adminTeatro);

        adminTeatroRepo.save(adminTeatro);
        Teatro teatroGuardado = teatroRepo.save(teatro);

        //System.out.println("El teatro con direccion "+teatroGuardado.getDireccion()+" fue asignado a el administrador de teatro con cedula "+adminTeatro.getCedula());
        return teatroGuardado;
    }
    @Override
    public Teatro desasignarAdministradorTeatro(Integer codigoTeatro, String cedulaAdminTeatro) throws Exception {

        Teatro teatro                   = teatroRepo.findByCodigo(codigoTeatro);
        AdministradorTeatro adminTeatro = adminTeatroRepo.findByCedula(cedulaAdminTeatro);

        if(teatro == null){
            throw new Exception("No existe un teatro con codigo "+codigoTeatro);
        }
        if(adminTeatro == null){
            throw  new Exception("No existe un administrador de teatro con cedula "+cedulaAdminTeatro);
        }
        if(!adminTeatro.getTeatros().contains(teatro)){
            throw  new Exception("El administrador con cedula "+cedulaAdminTeatro+" no administra el teatro con codigo "+codigoTeatro);
        }

        adminTeatro.getTeatros().remove(teatro);
        teatro.setAdmiTeatro(null);

        adminTeatroRepo.save(adminTeatro);
        Teatro teatroGuardado  = teatroRepo.save(teatro);

        return teatroGuardado;
    }
    @Override
    public Pelicula crearPelicula(Pelicula pelicula) throws Exception {

        Pelicula peliculaNueva = peliculaRepo.findByNombre(pelicula.getNombre());

        if(peliculaNueva != null){
            throw new Exception("La pelicula con nombre "+pelicula.getNombre()+" ya se encuentra registrada");
        }

        Pelicula peliculaRegistrada = peliculaRepo.save(pelicula);

        return peliculaRegistrada;
    }
    @Override
    public Pelicula actualizarDatosPelicula(Integer codigoPelicula,Pelicula pelicula) throws Exception {

        Pelicula peliculaActualizada = peliculaRepo.findByCodigo(codigoPelicula);
        Pelicula peliculaAux         = peliculaRepo.findByNombre(pelicula.getNombre());

        if(peliculaActualizada == null){
            throw new Exception("No existe una pelicula con codigo "+codigoPelicula);
        }
        if(peliculaActualizada != null && !peliculaAux.getNombre().equals(peliculaActualizada.getNombre())){
            throw new Exception("Ya existe una pelicula con nombre "+peliculaActualizada.getNombre());
        }

        Pelicula peliculaRegistrada = peliculaRepo.save(pelicula);

        return peliculaRegistrada;
    }
    @Override
    public Pelicula obtenerPeliculaCodigo(Integer codigoPelicula) throws Exception {

        Pelicula peliculaEncontrada = peliculaRepo.findByCodigo(codigoPelicula);

        if(peliculaEncontrada == null){
            throw new Exception("No existe una pelicula con nombre con codigo "+codigoPelicula);
        }

        return peliculaEncontrada;
    }
    @Override
    public Pelicula obtenerPeliculaNombre(String nombrePelicula) throws Exception {

        Pelicula peliculaEncontrada = peliculaRepo.findByNombre(nombrePelicula);

        if(peliculaEncontrada == null){
            throw new Exception("No existe una pelicula con nombre con nombre "+nombrePelicula);
        }
        return peliculaEncontrada;
    }
    @Override
    public void eliminarPelicula(Integer codigoPelicula) throws Exception {

        Pelicula peliculaEncontrada = peliculaRepo.findByCodigo(codigoPelicula);
        if(peliculaEncontrada == null){
            throw new Exception("No se puede eliminar la pelicula ya que no existe la pelicula con codigo "+codigoPelicula);
        }
        for (Funcion funcion: funcionRepo.findAll()) {
            if(funcion.getPelicula().equals(peliculaEncontrada)){
                throw new Exception("No se puede eliminar la pelicula ya que esta en la funcion con codigo "+funcion.getCodigo());
            }
        }

        peliculaRepo.delete(peliculaEncontrada);
        System.out.println("La pelicula con nombre "+ peliculaEncontrada.getNombre()+" fue eliminada");
    }
    @Override
    public List<Pelicula> listarPeliculas() throws Exception {

        List<Pelicula> peliculas = peliculaRepo.findAll();

        if(peliculas.isEmpty()){
            throw new Exception("No hay peliculas registradas");
        }

        return peliculas;
    }
    @Override
    public Pelicula cambiarEstadoPelicula(Integer codigoPelicula, EstadoPelicula estadoPelicula) throws Exception {

        Pelicula peliculaEncontrada = peliculaRepo.findByCodigo(codigoPelicula);

        if(peliculaEncontrada == null){
            throw new Exception("No existe una pelicula con codigo "+codigoPelicula);
        }

        peliculaEncontrada.setEstadoPelicula(estadoPelicula);
        Pelicula peliculaGuardada = peliculaRepo.save(peliculaEncontrada);

        return peliculaGuardada;
    }

    @Override
    public Confiteria crearConfiteria(Confiteria confiteria) throws Exception {

        Confiteria confiteriaEncontrado = confiteriaRepo.findByNombre(confiteria.getNombre());

        if(confiteriaEncontrado != null){
            throw new Exception("Ya existe una confiteria con el nombre "+confiteria.getNombre());
        }

        Confiteria confiteriaGuardado = confiteriaRepo.save(confiteria);

        return confiteriaGuardado;
    }

    @Override
    public Confiteria actualizarDatosConfiteria(Confiteria confiteria) throws Exception {

        Confiteria confiteriaCodigo     = confiteriaRepo.findByCodigo(confiteria.getCodigo());
        Confiteria confiteriaNombre     = confiteriaRepo.findByNombre(confiteria.getNombre());

        if(confiteriaCodigo == null){
            throw  new Exception("No existe una confiteria con codigo "+confiteria.getCodigo());
        }
        if(confiteriaNombre != null && !confiteriaNombre.getNombre().equals(confiteriaCodigo.getNombre())){
            throw new Exception("Ya existe una confiteria con el nombre "+confiteria.getNombre());
        }

        Confiteria confiteriaGuardado = confiteriaRepo.save(confiteria);

        return confiteriaGuardado;
    }

    @Override
    public Confiteria obtenerConfiteria(Integer codigoConfiteria) throws Exception {
        Confiteria confiteriaCodigo     = confiteriaRepo.findByCodigo(codigoConfiteria);

        if(confiteriaCodigo == null){
            throw  new Exception("No existe una confiteria con codigo "+codigoConfiteria);
        }

        return confiteriaCodigo;
    }

    @Override
    public void eliminarConfiteria(Integer codigoConfiteria) throws Exception {

        Confiteria confiteriaCodigo = confiteriaRepo.findByCodigo(codigoConfiteria);

        if(confiteriaCodigo == null){
            throw  new Exception("No existe una confiteria con codigo "+codigoConfiteria);
        }
        for (CompraConfiteria compraConfiteria:compraConfiteriaRepo.findAll()) {
            if(compraConfiteria.getConfiteria().getCodigo().equals(codigoConfiteria)){
                throw  new Exception("No se puede eliminar la confiteria ya que se encuentra relacionado con la compraConfiteria con codgio "+compraConfiteria.getCodigo());
            }
        }

        confiteriaRepo.delete(confiteriaCodigo);
        System.out.println("Se elimino la confiteria con nombre "+confiteriaCodigo.getNombre());
    }

    @Override
    public List<Confiteria> listarConfiterias() throws Exception {
        List<Confiteria> listaConfiterias = confiteriaRepo.findAll();

        if(listaConfiterias.isEmpty()){
            throw new Exception("La lista de confiterias esta vacia");
        }

        return listaConfiterias;
    }

    @Override
    public Cupon crearCupon(Cupon cupon) throws Exception {

        if(cuponRepo.findAll().contains(cupon.getCriterio())){
            throw new Exception("Ya existe un cupon con este mismo criterio");
        }
        if(cupon.getVencimiento().equals(LocalDate.now())){
            throw new Exception("La fecha de vencimineto debe ser superior a la fecha del dia de hoy");
        }

        Cupon cuponGuardado = cuponRepo.save(cupon);

        return cuponGuardado;
    }

    @Override
    public Cupon actualizarCupon(Cupon cupon) throws Exception {

        Cupon cuponEncontrado = cuponRepo.findByCodigo(cupon.getCodigo());

        if(cuponEncontrado ==  null){
            throw new Exception("No existe un cupon con codigo "+cupon.getCodigo());
        }
        if(cuponRepo.findAll().contains(cupon.getCriterio()) && !cupon.getCriterio().equals(cuponEncontrado.getCriterio())){
            throw new Exception("Ya existe un cupon con este mismo criterio");
        }
        if(cupon.getVencimiento().equals(LocalDate.now())){
            throw new Exception("La fecha de vencimineto debe ser superior a la fecha del dia de hoy");
        }

        Cupon cuponGuardado = cuponRepo.save(cupon);

        return cuponGuardado;
    }

    @Override
    public Cupon obtenerCupon(Integer codigoCupon) throws Exception {
        Cupon cuponEncontrado = cuponRepo.findByCodigo(codigoCupon);

        if(cuponEncontrado ==  null){
            throw new Exception("No existe un cupon con codigo "+codigoCupon);
        }

        return cuponEncontrado;
    }

    @Override
    public void eliminarCupon(Integer codigoCupon) throws Exception {
        Cupon cuponEncontrado = cuponRepo.findByCodigo(codigoCupon);

        if(cuponEncontrado ==  null){
            throw new Exception("No existe un cupon con codigo "+codigoCupon);
        }
        for (CuponCliente cuponCliente:cuponClienteRepo.findAll()) {
            if(cuponCliente.getCupon().equals(cuponEncontrado)){
                throw new Exception("No se puede eliminar el cupon con codigo "+codigoCupon+" por que esta en el cuponClinete con codigo "+cuponCliente.getCodigo());
            }
        }

        cuponRepo.delete(cuponEncontrado);
        System.out.println("El cupon con codigo "+codigoCupon+" ha sido eliminado");
    }

    @Override
    public List<Cupon> listarCupones() throws Exception {

        List<Cupon> listaCupones = cuponRepo.findAll();

        if(listaCupones.isEmpty()){
            throw new Exception("La lsita de cupones esta vacia");
        }

        return listaCupones;
    }

    @Override
    public CuponCliente asignarCuponCliente(Integer codigoCupon, String cedulaCliente) throws Exception {

        Cupon cupon = cuponRepo.findByCodigo(codigoCupon);
        Cliente cliente = clienteRepo.findByCedula(cedulaCliente);

        if(cliente == null){
            throw new Exception("No existe un cliente con la cedula "+cedulaCliente);
        }
        if(cupon == null){
            throw new Exception("No existe un cupon con codigo "+codigoCupon);
        }

        CuponCliente cuponCliente = new CuponCliente(EstadoCupon.SIN_USAR,cliente,cupon);
        cupon.getCuponesCliente().add(cuponCliente);
        cliente.getCupones().add(cuponCliente);

        CuponCliente cuponClienteGuardado = cuponClienteRepo.save(cuponCliente);
        Cliente clienteGuardado = clienteRepo.save(cliente);
        Cupon cuponGuardado = cuponRepo.save(cupon);

        return cuponClienteGuardado;
    }

}
