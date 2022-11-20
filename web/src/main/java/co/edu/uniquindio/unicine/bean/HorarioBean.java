package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Horario;
import co.edu.uniquindio.unicine.entidades.Teatro;
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
public class HorarioBean implements Serializable {


    @Getter  @Setter
    private Horario horario;

    @Getter  @Setter
    private List<Horario> horarios;

    @Getter  @Setter
    private List<Horario> horariosSeleccionados;

    @Autowired
    private AdministradorTeatroServicio adminTeatroServicio;

    private boolean editar;


    @PostConstruct
    public void init(){
        horario = new Horario();
        editar = false;

        try {
            horarios = adminTeatroServicio.listarHorarios();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        horariosSeleccionados = new ArrayList<>();

    }

    public void crearHorario(){

        try {
            if(!editar){
                Horario horarioNuevo = adminTeatroServicio.registrarHorario(horario);
                horarios.add(horarioNuevo);

                horario = new Horario();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Horario creado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }else{
                adminTeatroServicio.actualizarHorario(horario);

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Horario actualizado correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);
        }

    }


    public void eliminarHorarios(){

            try {
                for(Horario h : horariosSeleccionados) {
                    adminTeatroServicio.eliminarHorario(h.getCodigo());
                    horarios.remove(h);

                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "horario eliminado correctamente");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean", fm);
                }
                horariosSeleccionados.clear();

            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);
            }
    }

    public String getMensajeBorrar(){
        if(horariosSeleccionados.isEmpty()){
            return "Borrar";
        }
        else{
            return horariosSeleccionados.size()==1 ?"Borrar 1 elemento": "Borrar "+horariosSeleccionados.size()+" elementos";
        }
    }

    public void seleccionarHorario(Horario horarioSeleccionado){
        this.horario = horarioSeleccionado;
        editar = true;
    }


    public String getMensajeCrear(){
        if(editar==true){
            return "Editar horario";
        }
        else{
            return "Crear horario";
        }
    }

    public void crearHorarioDialog(){
        this.horario = new Horario();
        editar = false;
    }


}
