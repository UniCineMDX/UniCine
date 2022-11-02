package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
/**
 * Esta es la clase CompraConfiteria
 */
public class CompraConfiteria implements Serializable {

    //Atributos de la clase CompraConfiteria

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @Column(nullable = false, length = 20)
    private Double precio;
    @PositiveOrZero
    @Column(nullable = false, length = 10)
    private Integer unidades;

    //Relaciones de la clase Confiteria
    @ManyToOne
    @ToString.Exclude
    private Confiteria confiteria;

    @ManyToOne
    @ToString.Exclude
    private Compra compra;

    /**
     * Este es el metodo constructor de la clase CompraConfiteria
     * @param precio
     * @param unidades
     * @param confiteria
     * @param compra
     */

    @Builder
    public CompraConfiteria(Double precio, Integer unidades, Confiteria confiteria) {
        this.precio     = precio;
        this.unidades   = unidades;
        this.confiteria = confiteria;
    }
}
