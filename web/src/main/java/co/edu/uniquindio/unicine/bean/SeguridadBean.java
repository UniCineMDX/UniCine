package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
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
import java.util.ArrayList;
import java.util.List;

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

    @Getter @Setter
    private Cliente cliente;

    @Getter @Setter
    private List<Compra> listaCompraRedimidas;

    @Getter @Setter
    private List<Compra> listaCompraSinRedimir;

    @Getter @Setter
    private AdministradorSuper administradorSuper;

    @Getter @Setter
    private AdministradorTeatro administradorTeatro;

    @PostConstruct
    public void inicializar(){
        autenticado = false;
        listaCompraSinRedimir = new ArrayList<>();
        listaCompraRedimidas  = new ArrayList<>();
    }
    public String verificarInicioSesion(){
        if(!autenticado){
            return "PF('IniciarSesion').show()";
        }else{
            if(usuario instanceof Cliente){
                return "/cliente/perfil.xhtml?faces-redirect=true";
            }else{
                if(usuario instanceof AdministradorSuper){
                    return "/tableroAdminTeatro.xhtml?faces-redirect=true";
                }else{
                    return "/tableroAdminSuper.xhtml?faces-redirect=true";
                }
            }
        }
    }
    public String iniciarSesion(){
        try {
            if(!correo.isEmpty() || !password.isEmpty()){
                usuario = clienteServicio.login(correo,password);
                autenticado = true;
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Inicio de sesion existoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);

                if(usuario instanceof Cliente){
                    cliente = (Cliente) usuario;
                    return "/cliente/perfil.xhtml?faces-redirect=true";
                }
                if(usuario instanceof AdministradorSuper){
                    return "/tableroAdminTeatro.xhtml?faces-redirect=true";
                }
                if(usuario instanceof AdministradorTeatro) {
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
        autenticado = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
}
