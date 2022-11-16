package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminSuperServicioImpl implements AdminSuperServicio{

    private final AdministradorSuperRepo adminSuperRepo;
    private final AdministradorTeatroRepo adminTeatroRepo;
    private final TeatroRepo teatroRepo;
    private final PeliculaRepo peliculaRepo;
    private final ConfiteriaRepo confiteriaRepo;
    private final CuponRepo cuponRepo;
    private final FuncionRepo funcionRepo;
    private final ClienteRepo clienteRepo;
    private final CiudadRepo ciudadRepo;
    private final CuponClienteRepo cuponClienteRepo;
    private final CompraConfiteriaRepo compraConfiteriaRepo;

    public  AdminSuperServicioImpl(CiudadRepo ciudadRepo,AdministradorSuperRepo adminSuperRepo,ClienteRepo clienteRepo, AdministradorTeatroRepo adminTeatroRepo,CuponClienteRepo cuponClienteRepo,CompraConfiteriaRepo compraConfiteriaRepo,FuncionRepo funcionRepo, TeatroRepo teatroRepo, PeliculaRepo peliculaRepo, ConfiteriaRepo confiteriaRepo, CuponRepo cuponRepo) {
        this.ciudadRepo            = ciudadRepo;
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
    /*
     * Este metodo permite obtener toda la lista de administradores que existen en la base de datos
     */
    public List<AdministradorSuper> listarAdministradores() throws Exception{

        List<AdministradorSuper> listaAdminSuper = adminSuperRepo.findAll();
        if(listaAdminSuper.isEmpty()){
            throw new Exception("La lista de super administradores esta vacia");
        }
        return listaAdminSuper;
    }
    @Override
    /*
     * Este metodo permite obtener toda la lista de administradores que existen en la base de datos ordenados por nombre
     */
    public List<AdministradorSuper> listarAdministradoresOrdenados()  throws Exception{

        List<AdministradorSuper> listaAdminSuper = adminSuperRepo.findAllOrderByNombre();

        if(listaAdminSuper.isEmpty()){
            throw new Exception("La lista de super administradores esta vacia");
        }

        return listaAdminSuper;
    }

    @Override
    public List<Ciudad> listarCiudades() throws Exception {
        List<Ciudad> ciudads = ciudadRepo.findAll();
        if(ciudads.isEmpty()){
            throw new Exception("La lista de super administradores esta vacia");
        }
        return ciudads;
    }

    @Override
    /*
     * Este metodo permite registrar un nuevo administrador de teatro en la base de datos
     */
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
    /*
     * Este metodo permite actualizar los datos administrador de teatro en la base de datos
     */
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
    /*
     * Este metodo permite obtener un administrador de teatro dado su cedula
     */
    public AdministradorTeatro obtenerAdminTeatroCedula(String cedula) throws Exception {

        AdministradorTeatro adminTeatro = adminTeatroRepo.findByCedula(cedula);

        if(adminTeatro == null){
            throw new Exception("No se encontro un administrador de teatro con cedula "+cedula);
        }

        return adminTeatro;
    }
    @Override
    /*
     * Este metodo permite obtener un administrador de teatro dado su correo
     */
    public AdministradorTeatro obtenerAdminTeatroCorreo(String correo) throws Exception {

        AdministradorTeatro adminTeatro = adminTeatroRepo.findByCorreo(correo);

        if(adminTeatro == null){
            throw new Exception("No se encontro un administrador de teatro con correo "+correo);
        }

        return adminTeatro;
    }
    @Override
    /*
     * Este metodo permite eliminar un administrador de teatro dado su cedula
     */
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
    /*
     * Este metodo permite obtener la lista de administradores de teatros que estan en la base de datos
     */
    public List<AdministradorTeatro> listarAdminTeatros() throws Exception {
        List<AdministradorTeatro> listaAdminteatro = adminTeatroRepo.findAll();

        if(listaAdminteatro.isEmpty()){
            throw new Exception("La lista de administradores de teatro esta vacia");
        }

        return listaAdminteatro;
    }
    @Override
    /*
     * Este metodo permite asignar un administrador de teatro a un teatro dado el codigo del teatro
     */
    public AdministradorTeatro  asignarAdministradorTeatro(Integer codigoTeatro, String cedulaAdminTeatro) throws Exception {

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

        return adminTeatroRepo.save(adminTeatro);
    }
    @Override
    /*
     * Este metodo permite desasignar un adminitrador de teatro de un teatro dado su codigo
     */
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

        return teatroRepo.save(teatro);
    }
    @Override
    /*
     * Este metodo permite crear una nueva pelicula
     */
    public Pelicula crearPelicula(Pelicula pelicula) throws Exception {

        Pelicula peliculaNueva = peliculaRepo.findByNombre(pelicula.getNombre());

        if(peliculaNueva != null){
            throw new Exception("La pelicula con nombre "+pelicula.getNombre()+" ya se encuentra registrada");
        }

        return peliculaRepo.save(pelicula);
    }
    @Override
    /*
     * Este metodo permite actualizar los datos de una pelicula dado su codigo
     */
    public Pelicula actualizarDatosPelicula(Integer codigoPelicula,Pelicula pelicula) throws Exception {

        Pelicula peliculaActualizada = peliculaRepo.findByCodigo(codigoPelicula);
        Pelicula peliculaAux         = peliculaRepo.findByNombre(pelicula.getNombre());

        if(peliculaActualizada == null){
            throw new Exception("No existe una pelicula con codigo "+codigoPelicula);
        }
        if(!peliculaAux.getNombre().equals(peliculaActualizada.getNombre())){
            throw new Exception("Ya existe una pelicula con nombre "+peliculaActualizada.getNombre());
        }

        return peliculaRepo.save(pelicula);
    }
    @Override
    /*
     * Este metodo permite obtener una pelicula dado su codigo
     */
    public Pelicula obtenerPeliculaCodigo(Integer codigoPelicula) throws Exception {

        Pelicula peliculaEncontrada = peliculaRepo.findByCodigo(codigoPelicula);

        if(peliculaEncontrada == null){
            throw new Exception("No existe una pelicula con nombre con codigo "+codigoPelicula);
        }

        return peliculaEncontrada;
    }
    @Override
    /*
     * Este metodo permite obtener una pelicula dado su nombre
     */
    public Pelicula obtenerPeliculaNombre(String nombrePelicula) throws Exception {

        Pelicula peliculaEncontrada = peliculaRepo.findByNombre(nombrePelicula);

        if(peliculaEncontrada == null){
            throw new Exception("No existe una pelicula con nombre con nombre "+nombrePelicula);
        }
        return peliculaEncontrada;
    }
    @Override
    /*
     * Este metodo permite eliminar una pelicula dado su codigo
     */
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
    /*
     * Este metodo permite obtener la lista de peliculas registradas en la base de datos
     */
    public List<Pelicula> listarPeliculas() throws Exception {

        List<Pelicula> peliculas = peliculaRepo.findAll();

        if(peliculas.isEmpty()){
            throw new Exception("No hay peliculas registradas");
        }

        return peliculas;
    }
    @Override
    /*
     * Este metodo permite cambiar el estado de una pelicula dado si codigo
     */
    public Pelicula cambiarEstadoPelicula(Integer codigoPelicula, EstadoPelicula estadoPelicula) throws Exception {

        Pelicula peliculaEncontrada = peliculaRepo.findByCodigo(codigoPelicula);

        if(peliculaEncontrada == null){
            throw new Exception("No existe una pelicula con codigo "+codigoPelicula);
        }

        peliculaEncontrada.setEstadoPelicula(estadoPelicula);

        return peliculaRepo.save(peliculaEncontrada);

    }

    @Override
    /*
     * Este metodo permite crear una nueva confiteria
     */
    public Confiteria crearConfiteria(Confiteria confiteria) throws Exception {

        Confiteria confiteriaEncontrado = confiteriaRepo.findByNombre(confiteria.getNombre());

        if(confiteriaEncontrado != null){
            throw new Exception("Ya existe una confiteria con el nombre "+confiteria.getNombre());
        }

        return confiteriaRepo.save(confiteria);
    }

    @Override
    /*
     * Este metodo permite actualizar los datos de una confiteria
     */
    public Confiteria actualizarDatosConfiteria(Confiteria confiteria) throws Exception {

        Confiteria confiteriaCodigo     = confiteriaRepo.findByCodigo(confiteria.getCodigo());
        Confiteria confiteriaNombre     = confiteriaRepo.findByNombre(confiteria.getNombre());

        if(confiteriaCodigo == null){
            throw  new Exception("No existe una confiteria con codigo "+confiteria.getCodigo());
        }
        if(confiteriaNombre != null && !confiteriaNombre.getNombre().equals(confiteriaCodigo.getNombre())){
            throw new Exception("Ya existe una confiteria con el nombre "+confiteria.getNombre());
        }

        return confiteriaRepo.save(confiteria);
    }

    @Override
    /*
     * Este metodo permite obtener una confiteria dado su codigo
     */
    public Confiteria obtenerConfiteria(Integer codigoConfiteria) throws Exception {
        Confiteria confiteriaCodigo     = confiteriaRepo.findByCodigo(codigoConfiteria);

        if(confiteriaCodigo == null){
            throw  new Exception("No existe una confiteria con codigo "+codigoConfiteria);
        }

        return confiteriaCodigo;
    }

    @Override
    /*
     * Este metodo permite eliminar una confiteria dado su codigo
     */
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
    /*
     * Este metodo permite obtener la lista de confiterias registradas
     */
    public List<Confiteria> listarConfiterias() throws Exception {
        List<Confiteria> listaConfiterias = confiteriaRepo.findAll();

        if(listaConfiterias.isEmpty()){
            throw new Exception("La lista de confiterias esta vacia");
        }

        return listaConfiterias;
    }

    @Override
    /*
     * Este metodo permite crear un nuevo cupon
     */
    public Cupon crearCupon(Cupon cupon) throws Exception {

        for (Cupon cuponAux: cuponRepo.findAll()) {
            if(cuponAux.getCriterio().equals(cupon.getCriterio())){
                throw new Exception("Ya existe un cupon con este mismo criterio");
            }
        }
        if(cupon.getVencimiento().equals(LocalDate.now())){
            throw new Exception("La fecha de vencimineto debe ser superior a la fecha del dia de hoy");
        }

        return cuponRepo.save(cupon);
    }

    @Override
    /*
     * Este metodo permite actualizar los datos de un cupon
     */
    public Cupon actualizarCupon(Cupon cupon) throws Exception {

        Cupon cuponEncontrado = cuponRepo.findByCodigo(cupon.getCodigo());

        if(cuponEncontrado ==  null){
            throw new Exception("No existe un cupon con codigo "+cupon.getCodigo());
        }
        for (Cupon cuponAux: cuponRepo.findAll()) {
            if(cuponAux.getCriterio().equals(cupon.getCriterio()) && !cuponAux.equals(cuponEncontrado)){
                throw new Exception("Ya existe un cupon con este mismo criterio");
            }
        }
        if(cupon.getVencimiento().equals(LocalDate.now())){
            throw new Exception("La fecha de vencimineto debe ser superior a la fecha del dia de hoy");
        }

        return cuponRepo.save(cupon);
    }

    @Override
    /*
     * Este metodo permite obtener un cupon dado su codigo
     */
    public Cupon obtenerCupon(Integer codigoCupon) throws Exception {
        Cupon cuponEncontrado = cuponRepo.findByCodigo(codigoCupon);

        if(cuponEncontrado ==  null){
            throw new Exception("No existe un cupon con codigo "+codigoCupon);
        }

        return cuponEncontrado;
    }

    @Override
    /*
     * Este metodo permite eliminar un cupon dado su codigo
     */
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
    /*
     * Este metodo permite obtener la lista de cupones registrados en la base de datos
     */
    public List<Cupon> listarCupones() throws Exception {

        List<Cupon> listaCupones = cuponRepo.findAll();

        if(listaCupones.isEmpty()){
            throw new Exception("La lsita de cupones esta vacia");
        }

        return listaCupones;
    }

    @Override
    /*
     * Este metodo permite asignar un cuopn a un cliente
     */
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
        return cuponClienteRepo.save(cuponCliente);
    }

    @Override
    public Ciudad obtenerCiudad(Integer codigo) throws Exception {

        Ciudad ciudad = ciudadRepo.findByCodigo(codigo);

        if(ciudad == null){
            throw new Exception("No existe una ciudad con el codigo" +codigo);
        }

        return ciudad;
    }

}
