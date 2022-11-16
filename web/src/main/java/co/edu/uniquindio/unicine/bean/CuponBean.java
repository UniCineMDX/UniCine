package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdminSuperServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class CuponBean implements Serializable {

    @Getter @Setter
    private Cupon cupon;

    @Getter @Setter
    private List<Cupon> cupones;

    @Getter @Setter
    private List<Cupon> cuponesSeleccionados;

    private boolean editar;

    @Autowired
    private AdminSuperServicio adminSuperServicio;

    @PostConstruct
    public void init() throws Exception {
        cupon = new Cupon();

        cuponesSeleccionados = new ArrayList<>();
        cupones = adminSuperServicio.listarCupones();
        editar=false;
    }

    public void registrarCupon(){

        try {
            if (!editar) {
                Cupon registro = adminSuperServicio.crearCupon(cupon);
                cupones.add(registro);

                cupon= new Cupon();
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_cupon", facesMessage);
            }else{
                adminSuperServicio.actualizarCupon(cupon);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Actualizacion Exitosa");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_cupon", facesMessage);
            }
        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_cupon", facesMessage);
        }
    }


    public void eliminarCupones(){

        try {
            for (Cupon cupon : cuponesSeleccionados){
                adminSuperServicio.eliminarCupon(cupon.getCodigo());
                cupones.remove(cupon);
            }
            cuponesSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_eliminar_cupon", fm);
        }
    }


    public String getMensajeBorrar(){
        if(cuponesSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return "Borrar (" + cuponesSeleccionados.size() + ")" ;
        }

    }

    public String getMensajeCrearEditar(){
        if(editar){
            return "EDITAR CUPON";
        }
        return "CREAR CUPON" ;
    }


    public void seleccionarCupon(Cupon cuponSelec){
        this.cupon=cuponSelec;
        editar=true;
    }

    public void crearCuponDialog(){
        this.cupon= new Cupon();
        editar=false;
    }
}