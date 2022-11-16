package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
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

public class AdminTeatroBean implements Serializable {

    @Getter
    @Setter
    private AdministradorTeatro administradorTeatro;

    @Getter
    @Setter
    private List<AdministradorTeatro> adminTeatros;

    @Getter
    @Setter
    private List<AdministradorTeatro> adminTeatrosSeleccionados;

    private boolean editar;

    @Autowired
    private AdminSuperServicio adminSuperServicio;

    @PostConstruct
    public void init() throws Exception {
        administradorTeatro = new AdministradorTeatro();

        adminTeatrosSeleccionados = new ArrayList<>();
        adminTeatros = adminSuperServicio.listarAdminTeatros();
        editar = false;
    }

    public void registrarAdminTeatro(){

        try {

            if(!editar) {

                AdministradorTeatro registro = adminSuperServicio.crearAdminTeatro(administradorTeatro);
                adminTeatros.add(registro);

                administradorTeatro = new AdministradorTeatro();
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_adminTeatro", facesMessage);
            }else {

                adminSuperServicio.actualizarDatosAdminTeatro(administradorTeatro);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Actualizacion Exitosa");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_adminTeatro", facesMessage);
            }
        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_adminTeatro", facesMessage);
        }
    }


    public void eliminarAdminTeatros(){

        try {
            for (AdministradorTeatro adminTeatro : adminTeatrosSeleccionados){
                adminSuperServicio.eliminarAdminTeatro(adminTeatro.getCedula());
                adminTeatros.remove(adminTeatro);
            }
            adminTeatrosSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_eliminar_adminTeatros", fm);
        }
    }


    public String getMensajeBorrar(){
        if(adminTeatrosSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return "Borrar (" + adminTeatrosSeleccionados.size() + ")" ;
        }

    }

    public String getMensajeCrearEditar(){
        if(editar){
            return "EDITAR ADMINISTRADOR TEATRO";
        }
        return "CREAR ADMINISTRADOR TEATRO" ;
    }


    public void seleccionarAdminTeatros(AdministradorTeatro adminTeatroSelec){
        this.administradorTeatro =adminTeatroSelec;
        editar=true;
    }

    public void crearAdminTeatroDialog(){
        this.administradorTeatro= new AdministradorTeatro();
        editar=false;
    }
}
