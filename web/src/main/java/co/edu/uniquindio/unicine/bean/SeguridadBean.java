package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorSuper;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Persona;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Autowired
    private ClienteServicio clienteServicio;
    @Getter @Setter
    private boolean autenticado;
    @Getter @Setter
    private String correo,password;

    private String tipoSesion;

    @Getter @Setter
    private Persona usuario;

    @PostConstruct
    public void inicializar(){
        autenticado = false;
    }

    public String iniciarSesion(){
        try {
            if(!correo.isEmpty() || !password.isEmpty()){
                usuario = clienteServicio.login(correo,password);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Inicio de sesion existoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);

                if(usuario instanceof Cliente){
                    tipoSesion = "cliente";
                    return "/cliente/perfil.xhtml?faces-redirect=true";
                }
                if(usuario instanceof AdministradorSuper){
                    tipoSesion = "adminSuper";
                    return "/tableroAdminTeatro.xhtml?faces-redirect=true";
                }
                if(usuario instanceof AdministradorTeatro) {
                    tipoSesion = "adminTeatro";
                    return "/tableroAdminSuper.xhtml?faces-redirect=true";
                }
                return "/index.xhtml?faces-redirect=true";
            }else{
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Algunos de los campos estan vacios");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);
        }
        return null;
    }

    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
}
