package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdminSuperServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorTeatroServicio;
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
public class TeatroBean implements Serializable {

    @Getter
    @Setter
    private Teatro teatro;

    @Getter  @Setter
    private List<Teatro> teatros;

    @Getter  @Setter
    private List<Teatro>teatrosSeleccionados;

    @Getter  @Setter
    private List<Ciudad> ciudades;

    @Autowired
    private AdminSuperServicio adminSuperServicio;

    @Autowired
    private AdministradorTeatroServicio administradorTeatroServicio;

    private boolean editar;


    @PostConstruct
    public void init (){
        teatro = new Teatro();
        editar = false;
        System.out.println("Ingreso teatroBean");
        try {
            ciudades = adminSuperServicio.listarCiudades();
            System.out.println(ciudades);
            teatros = administradorTeatroServicio.listarTeatros();
            System.out.println("Teatros: "+teatros);
        } catch (Exception e) {
            e.printStackTrace();
        }

        teatrosSeleccionados = new ArrayList<>();
    }


    public void crearTeatro(){

        try {
            if(!editar) {

                //esto se debe borrar cuando se implemente los inicios de sesion
                AdministradorTeatro administradorTeatro = adminSuperServicio.obtenerAdminTeatroCedula("1");
                teatro.setAdmiTeatro(administradorTeatro);
                Teatro registro = administradorTeatroServicio.registrarTeatro(teatro);
                teatros.add(registro);

                teatro = new Teatro();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro creado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else{
                administradorTeatroServicio.actualizarTeatro(teatro);

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro actualizado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);

        }
    }



    public void eliminarTeatros(){

        try {

            for(Teatro t : teatrosSeleccionados) {

                administradorTeatroServicio.eliminarTeatro(t.getCodigo());
                teatros.remove(t);

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Teatro eliminado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
            teatrosSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);
        }

    }


    public String getMensajeBorrar(){
        if(teatrosSeleccionados.isEmpty()){
            return "Borrar";
        }
        else{
            return teatrosSeleccionados.size()==1 ?"Borrar 1 elemento": "Borrar "+teatrosSeleccionados.size()+" elementos";
        }
    }

    public void seleccionarTeatro(Teatro teatroSeleccionado){
        this.teatro = teatroSeleccionado;
        editar = true;
    }


    public String getMensajeCrear(){
        if(editar==true){
            return "Editar teatro";
        }
        else{
            return "Crear teatro";
        }
    }

    public void crearTeatroDialog(){
        this.teatro = new Teatro();
        editar = false;
    }


}
