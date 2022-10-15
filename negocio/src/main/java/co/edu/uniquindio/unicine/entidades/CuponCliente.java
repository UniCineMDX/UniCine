package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter // Genera los getters de todos los atributos sin necesidad de crearlos gracias Lombok
@Setter // Genera los setters de todos los atributos sin necesidad de crearlos gracias Lombok
@NoArgsConstructor // Genera el constructor vacio
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CuponCliente implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCupon estado;

    @ManyToOne
    @ToString.Exclude
    private Cliente cliente;


    @ManyToOne
    @ToString.Exclude
    private Cupon cupon;


    @OneToOne(mappedBy = "cuponCliente")
    @ToString.Exclude
    private Compra compra;


    public CuponCliente(EstadoCupon estado) {
        this.estado = estado;
    }
}
