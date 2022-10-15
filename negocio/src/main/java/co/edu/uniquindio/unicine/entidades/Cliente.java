package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Getter // Genera los getters de todos los atributos sin necesidad de crearlos gracias Lombok
@Setter // Genera los setters de todos los atributos sin necesidad de crearlos gracias Lombok
@NoArgsConstructor // Genera el constructor vacio
@ToString(callSuper = true)
public class Cliente extends Persona implements Serializable {

    @Enumerated(EnumType.STRING)
    private EstadoCliente estado;

    @ElementCollection
    private Map<String, String > telefonos;


    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private List<CuponCliente> cupones;


    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private List<Compra> compras;


    @Builder
    public Cliente(String cedula, String nombre, String correo, String password, String fotoUrl, EstadoCliente estado) {
        super(cedula, nombre, correo, password, fotoUrl);
        this.estado = estado;
    }
}
