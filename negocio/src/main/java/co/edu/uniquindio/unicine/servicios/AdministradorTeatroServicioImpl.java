package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorTeatroServicioImpl implements AdministradorTeatroServicio{

    //Atributos de la clase

    private AdministradorTeatroRepo admiTeatroRepo;
    private TeatroRepo teatroRepo;
    private HorarioRepo horarioRepo;
    private FuncionRepo funcionRepo;
    private SalaRepo salaRepo;
    private DistribucionSillaRepo distriSillaRepo;



    //Constructor de la clase

    public AdministradorTeatroServicioImpl(AdministradorTeatroRepo admiTeatroRepo, TeatroRepo teatroRepo, HorarioRepo horarioRepo, FuncionRepo funcionRepo, SalaRepo salaRepo, DistribucionSillaRepo distriSillaRepo) {
        this.admiTeatroRepo = admiTeatroRepo;
        this.teatroRepo = teatroRepo;
        this.horarioRepo = horarioRepo;
        this.funcionRepo = funcionRepo;
        this.salaRepo = salaRepo;
        this.distriSillaRepo = distriSillaRepo;
    }



    //Implementación de servicios de administrador teatro

    /*
    Metodo que permite obtener un administrador de teatro por medio de la cedula
    @Param cedula
     */
    @Override
    public AdministradorTeatro obtenerAdmiTeatro(String cedula) throws Exception {

        AdministradorTeatro admi = admiTeatroRepo.findByCedula(cedula);

        if(admi== null){
            throw  new Exception("El admnistrador de teatro no existe");
        }

        return admi;
    }

    /*
    Metodo que permite loguearse en la plataforma por medio del correo y contraseña de la persona
    @Param correo
    @Param password
     */

    @Override
    public AdministradorTeatro login(String correo, String password) throws Exception {

        AdministradorTeatro admi = admiTeatroRepo.comprobarAutenticacion(correo, password);

        if(admi == null){
            throw  new Exception("Los datos ingresados son incorrectos");
        }
        return admi;
    }

    /*
    Metodo que permite resgitrar un administrador de teatro en la plataforma
    @Param administradorTeatro
     */

    @Override
    public AdministradorTeatro registrarAdmiTeatro(AdministradorTeatro administradorTeatro) throws Exception {

        AdministradorTeatro admiExistenteCorreo = admiTeatroRepo.findByCorreo(administradorTeatro.getCorreo());
        AdministradorTeatro admiExistenteCedula = admiTeatroRepo.findByCedula(administradorTeatro.getCedula());

        if(admiExistenteCorreo != null){
            throw new Exception("El correo que desea ingresar ya existe");
        }
        else{
            if(admiExistenteCedula != null){
                throw new Exception("El administrador con esa cedula ya existe");
            }
        }
        return admiTeatroRepo.save(administradorTeatro);
    }

    /*
    Metodo que permite actualizar los datos de un administrador de teatro
    @Param administradorTeatro
     */

    @Override
    public AdministradorTeatro actualizarAdmiTeatro(AdministradorTeatro administradorTeatro) throws Exception {

        Optional<AdministradorTeatro> admiGuardado = admiTeatroRepo.findById(administradorTeatro.getCedula());

        if( admiGuardado.isEmpty()){
            throw new Exception("El administrador no existe ");
        }
        return admiTeatroRepo.save(administradorTeatro);
    }

    /*
    Metodo que permite eliminar un administrador de teatro de la plataforma por medio de su cedula
    @Param cedula
     */

    @Override
    public void eliminarAdmiTeatro(String cedula) throws Exception {

        Optional<AdministradorTeatro> admiGuardado = admiTeatroRepo.findById(cedula);

        if( admiGuardado.isEmpty()){
            throw new Exception("El administrador no existe ");
        }

        admiTeatroRepo.delete(admiGuardado.get());
    }

    /*
    Metodo que permite listar todos los administradores de teatro registrados en la plataforma
     */

    @Override
    public List<AdministradorTeatro> listarAdmiTeatros() {
        return admiTeatroRepo.findAll();
    }

    /*
    Metodo que permite listar los teatros de la ciudad administrados por el administrador de teatro
    @Param cedulaAdmiTeatro
     */

    @Override
    public List<Teatro> obtenerListaTeatros(String cedulaAdmiTeatro) {

        List<Teatro> teatros = admiTeatroRepo.obtenerListaTeatros(cedulaAdmiTeatro);
        return teatros;
    }




    //Implementacion de servicios de teatro

    /*
    Metodo que permite obtener un teatro por medio de su codigo
    @Param codigo
     */
    @Override
    public Teatro obtenerTeatro(Integer codigo) throws Exception {

        Teatro teatro = teatroRepo.obtener(codigo);

        if(teatro== null){
            throw new Exception("El teatro no existe ");
        }

        return teatro;
    }

    /*
    Metodo que permite registrar un teatro en la plataforma
    @Param teatro
     */

    @Override
    public Teatro registrarTeatro(Teatro teatro) throws Exception {

        Teatro teatroExistenteCodigo = teatroRepo.obtener(teatro.getCodigo());

        if(teatroExistenteCodigo != null){
            throw  new Exception("El teatro con ese codigo ya existe");
        }

        return teatroRepo.save(teatro);
    }

    /*
    Metodo que permite actualizar los datos de un teatro
    @teatro
     */

    @Override
    public Teatro actualizarTeatro(Teatro teatro) throws Exception {

        Optional<Teatro> teatroGuardado = teatroRepo.findById(teatro.getCodigo());

        if(teatroGuardado.isEmpty()){
            throw new Exception("El teatro no existe");
        }
        return teatroRepo.save(teatro);
    }

    /*
    Metodo que permite eliminar un teatro por medio de su codigo
    @Param codigo
     */

    @Override
    public void eliminarTeatro(Integer codigo) throws Exception {

        Optional<Teatro> guardado = teatroRepo.findById(codigo);

        if(guardado.isEmpty()){
            throw new Exception("El teatro no existe por lo tanto no se puede eliminar");
        }
        teatroRepo.delete(guardado.get());
    }

    /*
    Metodo que permite listar todos los teatros registrados
     */

    @Override
    public List<Teatro> listarTeatros() {
        return teatroRepo.findAll();
    }

    /*
    Metodo que permite obtener la ciudad a la que el teatro pertenece por medio del codigo del teatro
    @Param codigoTeatro
     */

    @Override
    public Ciudad obtenerCiudadTeatro(Integer codigoTeatro) throws Exception {

        Ciudad ciudad = teatroRepo.obtenerCiudadTeatro(codigoTeatro);

        if(ciudad == null){
            throw  new Exception("El teatro no tiene ciudad asociada");
        }
        return ciudad;
    }

    /*
    Metodo que permite obtener el administrador encargado del teatro por medio del codigo del teatro
    @Param codigoTeatro
     */

    @Override
    public AdministradorTeatro obtenerAdmiTeatro(Integer codigoTeatro) throws Exception {

        AdministradorTeatro admiTeatro = teatroRepo.obtenerAdmiTeatro(codigoTeatro);

        if(admiTeatro == null){
            throw  new Exception("El teatrp no tiene administardor de teatro asociado");
        }
        return admiTeatro;
    }

    /*
    Metodo que permite obtener las salas pertenecientes al teatro por medio del codigo del teatro
    @Param codigoTeatro
     */

    @Override
    public List<Sala> obtenerListaSalasTeatro(Integer codigoTeatro){

        List<Sala> salas = teatroRepo.obtenerSalasTeatro(codigoTeatro);
        return salas;
    }




    //Implementación de servicios de horario

    /*
    Metodo que permite obtener un horario por medio de su codigo
    @Param codigo
     */

    @Override
    public Horario obtenerHorario(Integer codigo) throws Exception {

        Horario horario = horarioRepo.findByCodigo(codigo);

        if(horario == null ){
            throw new Exception("El horario con ese codigo no existe");
        }
        return horario;
    }

    /*
    Metodo que permite registrar un horario en la plataforma
    @Param horario
     */

    @Override
    public Horario registrarHorario(Horario horario) throws Exception {

        Horario horarioExistenteCodigo = horarioRepo.findByCodigo(horario.getCodigo());

        if(horarioExistenteCodigo != null){
            throw new Exception("El horario con ese codigo ya existe ");
        }

        return horarioRepo.save(horario);
    }

    /*
    Metodo que permite actualizar los datos de un horario
    @Param horario
     */

    @Override
    public Horario actualizarHorario(Horario horario) throws Exception {

        Optional<Horario> guardado = horarioRepo.findById(horario.getCodigo());

        if(guardado.isEmpty()){
            throw new Exception("El horario con ese codigo no existe");
        }

        return horarioRepo.save(horario);
    }

    /*
    Metodo que permite eliminar un horario por medio de su codigo
    @Param codigo
     */

    @Override
    public void eliminarHorario(Integer codigo) throws Exception {

        Optional<Horario> guardado = horarioRepo.findById(codigo);

        if(guardado.isEmpty()){
            throw new Exception("El horario con ese codigo no existe por lo tanto no se puede eliminar");
        }
        horarioRepo.delete(guardado.get());

    }

    /*
    Metodo que permite listar todos los horarios registrados en la plataforma
     */

    @Override
    public List<Horario> listarHorarios() {
        return horarioRepo.findAll();
    }

    /*
    Metodo que permite obtener la lista de las funciones que tienen cierto horario por medio del codigo del horario
    @Param codigoHorario
     */

    @Override
    public List<Funcion> obtenerListaFuncionesHorario(Integer codigoHorario) {

        List<Funcion> funciones = horarioRepo.obtenerListaFunciones(codigoHorario);
        return funciones;
    }




    //Implementación de servicios de funcion

    /*
    Metodo que permite obtener una funcion por medio de su codigo
    @Param codigo
     */

    @Override
    public Funcion obtenerFuncion(Integer codigo) throws Exception {
        Funcion funcion = funcionRepo.findByCodigo(codigo);

        if(funcion == null){
            throw new Exception("La funcion con el codigo" + codigo + "no existe");
        }

        return funcion;
    }

    /*
    Metodo que permite registrar una funcion en la plataforma
    @Param funcion
     */

    @Override
    public Funcion registrarFuncion(Funcion funcion) throws Exception {

        Funcion funcionExistenteCodigo = funcionRepo.findByCodigo(funcion.getCodigo());

        if(funcionExistenteCodigo != null){
            throw new Exception("La funcion con ese codigo ya existe");
        }

        return funcionRepo.save(funcion);
    }

    /*
    Metodo que permite actualizar los datos de una funcion
    @Param funcion
     */

    @Override
    public Funcion actualizarFuncion(Funcion funcion) throws Exception {

        Optional<Funcion> guardada = funcionRepo.findById(funcion.getCodigo());

        if(guardada.isEmpty()){
            throw new Exception("La funcion con ese codigo no existe");
        }
        return funcionRepo.save(funcion);
    }

    /*
    Metodo que permite eliminar una funcion por medio de su codigo
    @Param codigo
     */

    @Override
    public void eliminarFuncion(Integer codigo) throws Exception {

        Optional<Funcion> guardada = funcionRepo.findById(codigo);

        if(guardada.isEmpty()){
            throw new Exception("La funcion con ese codigo no existe por lo tanto no se puede eliminar");
        }

        funcionRepo.delete(guardada.get());

    }

    /*
    Metodo que permite obtener todas las funciones registradas en la plataforma
     */

    @Override
    public List<Funcion> listarFunciones() {
        return funcionRepo.findAll();
    }

    /*
    Metodo que permite obtener la pelicula asociada a la funcion por medio del codigo de la funcion
    @Param codigoFuncion
     */

    @Override
    public Pelicula obtenerPeliculaFuncion(Integer codigoFuncion) throws Exception {

        Pelicula pelicula = funcionRepo.obtenerPeliculaFuncion(codigoFuncion);

        if(pelicula == null){
            throw new Exception("La funcion con ese codigo no tiene una pelicula asociada");
        }

        return pelicula;
    }

    /*
    Metodo que permite obtener el horario asociado a la funcion por medio del codigo de la funcion
    @Param codigoFuncion
     */

    @Override
    public Horario obtenerHorarioFuncion(Integer codigoFuncion) throws Exception {

        Horario horario = funcionRepo.obtenerHorarioFuncion(codigoFuncion);

        if(horario == null){
            throw new Exception("La funcion con ese codigo no tiene un horario asignado");
        }
        return horario;
    }

    /*
    Metodo que permite obtener la sala asociada a la funcion por medio del codigo de la funcion
    @Param codigoFuncion
     */

    @Override
    public Sala obtenerSalaFuncion(Integer codigoFuncion) throws Exception {

        Sala sala = funcionRepo.obtenerSalaFuncion(codigoFuncion);

        if(sala == null){
            throw new Exception("La funcion con ese codigo no tiene una sala asignada");
        }
        return sala;
    }

    /*
    Metodo que permite obtener la lista de compras que tienen cierta funcion por medio del codigo de la funcion
    @Param codigoFuncion
     */

    @Override
    public List<Compra> obtenerListaComprasFuncion(Integer codigoFuncion) {

        List<Compra> compras = funcionRepo.obtenerComprasFuncion(codigoFuncion);

        return compras;
    }




    //Implementacion de servicios de sala

    /*
    Metodo que permite obtener una sala por medio de su codigo
    @Param codigo
     */
    @Override
    public Sala obtenerSala(Integer codigo) throws Exception {

        Sala sala = salaRepo.findByCodigo(codigo);

        if(sala == null){
            throw new Exception("La sala con ese codigo no existe");
        }
        return sala;
    }

    /*
    Metodo que permite registrar una sala en la plataforma
    @Param sala
     */

    @Override
    public Sala registrarSala(Sala sala) throws Exception {

        Sala salaExistenteCodigo = salaRepo.findByCodigo(sala.getCodigo());

        if(salaExistenteCodigo != null){
            throw new Exception("La sala con ese codigo ya existe");
        }

        return salaRepo.save(sala);
    }

    /*
    Metodo que permite actualizar los datos de una sala
    @Param sala
     */

    @Override
    public Sala actualizarSala(Sala sala) throws Exception {

        Optional<Sala> guardada = salaRepo.findById(sala.getCodigo());

        if(guardada.isEmpty()){
            throw new Exception("La sala con ese codigo no existe");
        }
        return salaRepo.save(sala);
    }

    /*
    Metodo que permite eliminar una sala por medio de su codigo
    @Param codigo
     */

    @Override
    public void eliminarSala(Integer codigo) throws Exception {

        Optional<Sala> guardada = salaRepo.findById(codigo);

        if(guardada.isEmpty()){
            throw new Exception("La sala con ese codigo no existe por lo tanto no se puede eliminar");
        }
        salaRepo.delete(guardada.get());
    }

    /*
    Metodo que permite obtener la lista de todas las salas registradas
     */

    @Override
    public List<Sala> listarSalas() {
        return salaRepo.findAll();
    }

    /*
    Metodo que permite obtener el teatro al que la sala pertenece por medio del codigo de la sala
    @Param codigoSala
     */

    @Override
    public Teatro obtenerTeatroSala(Integer codigoSala) throws Exception {

        Teatro teatro = salaRepo.obtenerTeatroSala(codigoSala);

        if(teatro == null){
            throw new Exception("La sala no tiene un teatro asociado");
        }
        return teatro;
    }

    /*
    Metodo que permite obtener la distribucion de sillas que tiene asignada la sala por medio del codigo de la sala
    @Param codigoSala
     */

    @Override
    public DistribucionSilla obtenerDistribucionSillas(Integer codigoSala) throws Exception {

        DistribucionSilla distribucionSilla = salaRepo.obtenerDistribucionSillaSala(codigoSala);

        if(distribucionSilla == null){
            throw new Exception("La sala no tiene una distribucion de sillas asignadas");
        }
        return distribucionSilla;
    }

    /*
    Metodo que permite obtener la lista de funciones que se presentaran en cierta sala por medio del codigo de la sala
    @Param codigoSala
     */
    @Override
    public List<Funcion> obtenerFuncionesSala(Integer codigoSala) {

        List<Funcion> funciones = salaRepo.obtenerFuncionesSala(codigoSala);

        return funciones;
    }




    //Implementación de servicios de distribucion de sillas

    /*
    Metodo que permite obtener una distribucion de sillas por medio de su codigo
    @Param codigo
     */
    @Override
    public DistribucionSilla obtenerDistribucionSilla(Integer codigo) throws Exception {

        DistribucionSilla distribucionSilla = distriSillaRepo.findByCodigo(codigo);

        if(distribucionSilla == null){
            throw new Exception("La distribicion de sillas con ese codigo no existe");
        }
        return distribucionSilla;
    }

    /*
    Metodo que permite registrar una distribucion de sillas en la plataforma
    @Param distribucionSilla
     */

    @Override
    public DistribucionSilla registrarDistribucionSilla(DistribucionSilla distribucionSilla) throws Exception {

        DistribucionSilla distriSillaRepoExistenteCodigo = distriSillaRepo.findByCodigo(distribucionSilla.getCodigo());
        Integer verificacionSillas = distribucionSilla.getTotalSillas();

        if( distriSillaRepoExistenteCodigo != null){
            throw  new Exception("La distribucion de sillas con ese codigo ya existe");
        }

        if(distribucionSilla.getColumnas()*distribucionSilla.getFilas() != verificacionSillas){
            throw new Exception("El número de filas y clumnas no coincide con el total de sillas");
        }

        return distriSillaRepo.save(distribucionSilla);
    }

    /*
    Metodo que permite actualizar los datos de una distribucion de sillas
    @Param distribucionSilla
     */

    @Override
    public DistribucionSilla actualizarDistribucionSilla(DistribucionSilla distribucionSilla) throws Exception {

        Optional<DistribucionSilla> guardada = distriSillaRepo.findById(distribucionSilla.getCodigo());

        if(guardada.isEmpty()){
            throw new Exception("La distribucion de sillas con ese codigo no existe");
        }
        return distriSillaRepo.save(guardada.get());
    }

    /*
    Metodo que permite eliminar una distribucion de sillas por medio de su codigo
    @Param codigo
     */

    @Override
    public void eliminarDistribucionSilla(Integer codigo) throws Exception {

        Optional<DistribucionSilla> guardada = distriSillaRepo.findById(codigo);

        if(guardada.isEmpty()){
            throw new Exception("La distribucion de sillas con ese codigo no existe por lo tanto no se puede eliminar");
        }
        distriSillaRepo.delete(guardada.get());
    }

    /*
    Metodo que permite obtener la lista de todas las distribuciones de sillas registradas
     */

    @Override
    public List<DistribucionSilla> listarDistribucionSillas() {
        return distriSillaRepo.findAll();
    }

    /*
    Metodo que permite obetener la lista de salas que tienen cierta distribucion de sillas por medio del codigo de la distribucion de sillas
    @Param codigoDistribucionSilla
     */

    @Override
    public List<Sala> obtenerSalasDistribucionSilla(Integer codigoDistribucionSilla) {

        List<Sala> salas = distriSillaRepo.obtenerSalasDistribucionSilla(codigoDistribucionSilla);

        return salas;
    }
}
