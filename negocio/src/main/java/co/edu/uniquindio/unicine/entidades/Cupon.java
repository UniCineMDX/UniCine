package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter // Genera los getters de todos los atributos sin necesidad de crearlos gracias Lombok
@Setter // Genera los setters de todos los atributos sin necesidad de crearlos gracias Lombok
@NoArgsConstructor // Genera el constructor vacio
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cupon implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(length = 50)
    private String descripcion;

    @Column(nullable = false)
    private Double descuento;

    @Column(nullable = false, length = 50)
    private String criterio;

    @Column(nullable = false)
    private LocalDate vencimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCupon estadoCupon;

    @OneToMany(mappedBy = "cupon")
    @ToString.Exclude
    private List<CuponCliente> cuponesCliente;

    public Cupon( Double descuento, String criterio, LocalDate vencimiento, EstadoCupon estadoCupon) {
        this.descuento = descuento;
        this.criterio = criterio;
        this.vencimiento = vencimiento;
        this.estadoCupon = estadoCupon;
    }
}
