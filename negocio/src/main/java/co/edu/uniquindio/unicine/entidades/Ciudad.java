package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
/**
 * Esta es la clase Ciudad
 */
public class Ciudad implements Serializable {

    //Atributos de la clase Ciudad
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @Column(unique = true, nullable = false, length = 50)
    private String nombre;

    //Relaciones de la clase Ciudad
    @ToString.Exclude
    @OneToMany(mappedBy = "ciudad")
    private List<Teatro> teatros;

    /**
     * Este es el metodo constructor de la clase Ciudad
     * @param nombre
     */
    public Ciudad(String nombre) {
        this.nombre = nombre;
    }
}
