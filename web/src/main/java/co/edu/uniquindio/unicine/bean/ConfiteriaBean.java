package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import co.edu.uniquindio.unicine.servicios.AdminSuperServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConfiteriaBean implements Serializable {
    @Getter @Setter
    private Confiteria confiteria;

    @Getter @Setter
    private List<Confiteria> confiterias;

    @Getter @Setter
    private List<Confiteria> confiteriasSeleccionados;

    private boolean editar;

    @Autowired
    private AdminSuperServicio adminSuperServicio;


    @PostConstruct
    public void init() throws Exception {
        confiteria = new Confiteria();

        confiterias = adminSuperServicio.listarConfiterias();
        confiteriasSeleccionados = new ArrayList<>();
        editar= false;
    }

    public void registrarConfiteria(){

        try {
            if(!editar) {

                Confiteria registro = adminSuperServicio.crearConfiteria(confiteria);
                confiterias.add(registro);

                confiteria = new Confiteria();
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_confiteria", facesMessage);
            }else{
                adminSuperServicio.actualizarDatosConfiteria(confiteria);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Actualizacion Exitosa");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_confiteria", facesMessage);
            }
        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_confiteria", facesMessage);
        }
    }



    public void eliminarConfiterias(){

        try {
            for (Confiteria confiteria : confiteriasSeleccionados){
                adminSuperServicio.eliminarConfiteria(confiteria.getCodigo());
                confiterias.remove(confiteria);
            }
            confiteriasSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_eliminar_confiteria", fm);
        }
    }


    public String getMensajeBorrar(){
        if(confiteriasSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return "Borrar (" + confiteriasSeleccionados.size() + ")" ;
        }

    }

    public String getMensajeCrearEditar(){
        if(editar){
            return "EDITAR CONFITERIA";
        }
        return "CREAR CONFITERIA" ;
    }


    public void seleccionarConfiteria(Confiteria confiteriaSelec){
        this.confiteria=confiteriaSelec;
        editar=true;
    }

    public void crearConfiteriaDialog(){
        this.confiteria= new Confiteria();
        editar=false;
    }
}

