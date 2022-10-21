package co.edu.uniquindio.unicine.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
/**
 * Esta es la clase AdministradorSuper
 */
public class AdministradorSuper extends Persona implements Serializable {

    /**
     * Este es el metodo constructor de la clase AdministradorSuper
     * @param cedula
     * @param nombre
     * @param correo
     * @param password
     * @param fotoUrl
     */
    public AdministradorSuper(String cedula, String nombre, String correo, String password, String fotoUrl) {
        super(cedula, nombre, correo, password, fotoUrl);
    }


}
