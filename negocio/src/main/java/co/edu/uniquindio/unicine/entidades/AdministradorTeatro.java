package co.edu.uniquindio.unicine.entidades;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter // Genera los getters de todos los atributos sin necesidad de crearlos gracias Lombok
@Setter // Genera los setters de todos los atributos sin necesidad de crearlos gracias Lombok
@NoArgsConstructor // Genera el constructor vacio
@ToString(callSuper = true)
public class AdministradorTeatro extends Persona implements Serializable {

    @OneToMany(mappedBy = "admiTeatro")
    @ToString.Exclude
    private List<Teatro> teatros;


    public AdministradorTeatro(String cedula, String nombre, String correo, String password, String fotoUrl) {
        super(cedula, nombre, correo, password, fotoUrl);
    }
}
