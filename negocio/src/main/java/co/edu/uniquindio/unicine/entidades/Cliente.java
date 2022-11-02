package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
/**
 * Esta es la clase Cliente
 */
public class Cliente extends Persona implements Serializable {

    //Atributos de la clase Cliente
    @Enumerated(EnumType.STRING)
    private EstadoCliente estado = EstadoCliente.REGISTRADO;

    @ElementCollection
    private Map<String, String > telefonos;

    //Relaciones de la clase Cliente

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<CuponCliente> cupones;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;

    /**
     * Este es el emtodo constructor de la clase Cliente
     * @param cedula
     * @param nombre
     * @param correo
     * @param password
     * @param fotoUrl
     */
    @Builder
    public Cliente(String cedula, String nombre, String correo, String password, String fotoUrl) {
        super(cedula, nombre, correo, password, fotoUrl);
        this.estado = EstadoCliente.INACTIVO;
    }
}
