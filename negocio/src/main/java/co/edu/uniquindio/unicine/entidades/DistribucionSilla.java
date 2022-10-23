package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter // Genera los getters de todos los atributos sin necesidad de crearlos gracias Lombok
@Setter // Genera los setters de todos los atributos sin necesidad de crearlos gracias Lombok
@NoArgsConstructor // Genera el constructor vacio
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DistribucionSilla implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;


    @Column(nullable = false)
    private String esquema;



    @PositiveOrZero
    private Integer totalSillas;



    @PositiveOrZero
    private Integer filas;



    @PositiveOrZero
    private Integer columnas;



    @OneToMany(mappedBy = "distribucionSilla")
    @ToString.Exclude
    private List<Sala>salas;



    @Builder
    public DistribucionSilla(String esquema, Integer totalSillas, Integer filas, Integer columnas) {
        this.esquema = esquema;
        this.totalSillas = totalSillas;
        this.filas = filas;
        this.columnas = columnas;
    }
}
