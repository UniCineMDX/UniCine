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
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
/**
 * Esta es la clase AdministradorTeatro
 */
public class AdministradorTeatro extends Persona implements Serializable {

    //Relaciones de la calse AdministradorTeatro

    @ToString.Exclude
    @OneToMany(mappedBy = "admiTeatro")
    private List<Teatro> teatros;

    /**
     * Este es el metodo constructor de la clase AdministradorTeatro
     * @param cedula
     * @param nombre
     * @param correo
     * @param password
     * @param fotoUrl
     */
    public AdministradorTeatro(String cedula, String nombre, String correo, String password, String fotoUrl) {
        super(cedula, nombre, correo, password, fotoUrl);
    }
}
