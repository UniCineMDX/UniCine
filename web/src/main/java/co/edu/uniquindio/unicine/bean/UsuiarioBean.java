package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorSuper;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Persona;
import co.edu.uniquindio.unicine.servicios.AdministradorTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class UsuiarioBean implements Serializable {

    @Getter  @Setter
    private Cliente cliente;
    @Getter  @Setter
    private AdministradorSuper adminSuper;
    @Getter  @Setter
    private AdministradorTeatro adminTeatro;
    @Getter  @Setter
    private Persona usuario;
    @Getter @Setter
    private String correo;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String confirmarPassword;
    @Getter @Setter
    private String direccionSesion;
    @Autowired
    private ClienteServicio clienteServicio;

    @PostConstruct
    public void init (){
        System.out.println("usuario: "+usuario);
        cliente = new Cliente();
        adminSuper = new AdministradorSuper();
        adminTeatro = new AdministradorTeatro();
    }
    public void registrarCliente(){

        try {
            if(cliente.getPassword().equals(confirmarPassword)){
                System.out.println(cliente);
                clienteServicio.registrarCliente(cliente);

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro existoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);
            }else{
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Las contraseñas no coinciden");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);
            }

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);
        }
    }

    public String verificarInicioSesion(){
        if(usuario == null){
            return "PF('IniciarSesion').show()";
        }else{
            return "/cliente/inicioSesion.xhtml?faces-redirect=true";
        }
    }

    public String iniciarSesion(){
        try {
            if(!correo.isEmpty() || !password.isEmpty()){
                usuario = clienteServicio.login(correo,password);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Inicio de sesion existoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);

                if(usuario instanceof Cliente){
                    cliente = (Cliente) usuario;
                    return "/cliente/perfil.xhtml?faces-redirect=true";
                }
                if(usuario instanceof AdministradorSuper){
                    adminSuper = (AdministradorSuper) usuario;
                    System.out.println("Inicio admin");
                }
                if(usuario instanceof AdministradorTeatro) {
                    adminTeatro = (AdministradorTeatro) usuario;
                    System.out.println("Inicio adminTeatro");
                }
            }else{
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Algunos de los campos estan vacios");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);
            }
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);
        }
        return "/";
    }
}
