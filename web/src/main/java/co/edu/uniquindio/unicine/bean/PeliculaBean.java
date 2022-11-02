package co.edu.uniquindio.unicine.bean;

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

@Component
@ViewScoped
public class PeliculaBean implements Serializable {

    @Getter @Setter
    private Pelicula pelicula;

    @Autowired
    private AdminSuperServicio adminSuperServicio;

    @PostConstruct
    public void init (){
        pelicula = new Pelicula();
    }

    public String crearPelicula (){

        pelicula.setEstadoPelicula(EstadoPelicula.ESTRENO);
        pelicula.setGenero(Genero.DRAMA);
        try {
            adminSuperServicio.crearPelicula(pelicula);

            return "/adminSuper/pelicula_creada?faces-redirect=true";
            //FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La pelicula se creo correctamente");
            //FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);
        } catch (Exception e) {
            //FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            //FacesContext.getCurrentInstance().addMessage("mensaje_bean" , fm);
            return "";
        }

    }

}
