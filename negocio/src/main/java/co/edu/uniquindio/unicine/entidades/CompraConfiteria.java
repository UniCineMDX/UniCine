package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Entity
@Getter // Genera los getters de todos los atributos sin necesidad de crearlos gracias Lombok
@Setter // Genera los setters de todos los atributos sin necesidad de crearlos gracias Lombok
@NoArgsConstructor // Genera el constructor vacio
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CompraConfiteria implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;


    @Column(nullable = false)
    private Double precio;


    @Column(nullable = false)
    @PositiveOrZero
    private Integer unidades;


    @ManyToOne
    @ToString.Exclude
    private Confiteria confiteria;



    @ManyToOne
    @ToString.Exclude
    private Compra compra;


    public CompraConfiteria(Double precio, Integer unidades) {
        this.precio = precio;
        this.unidades = unidades;
    }
}
