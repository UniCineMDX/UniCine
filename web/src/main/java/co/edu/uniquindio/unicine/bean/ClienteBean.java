package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Cliente;
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
public class ClienteBean implements Serializable {

    @Getter  @Setter
    private Cliente cliente;

    @Getter @Setter
    private String confirmarPassword;

    @Autowired
    private ClienteServicio clienteServicio;




    @PostConstruct
    public void init (){
        cliente = new Cliente();
    }



    public void registrarCliente(){

        try {
            if(cliente.getPassword().equals(confirmarPassword)){
                clienteServicio.registrarCliente(cliente);

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro existoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);
            }else{
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Las contraseñas no coinciden");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);
            }

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);
        }

    }
}
