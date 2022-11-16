package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorSuper;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.servicios.AdminSuperServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorTeatroServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
public class sistemaBean {

    @Getter @Setter
    List<Ciudad> ciudades;

    @Getter @Setter
    Ciudad ciudadSeleccionada;

    @Autowired
    AdminSuperServicio adminServicio;

    @PostConstruct
    public void init (){
        ciudadSeleccionada = new Ciudad();
        try {
            ciudades = adminServicio.listarCiudades();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
