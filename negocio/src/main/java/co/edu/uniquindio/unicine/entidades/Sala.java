package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter // Genera los getters de todos los atributos sin necesidad de crearlos gracias Lombok
@Setter // Genera los setters de todos los atributos sin necesidad de crearlos gracias Lombok
@NoArgsConstructor // Genera el constructor vacio
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sala implements Serializable {


    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Codigo;



    @Column(nullable = false,length = 50)
    private String nombre;



    @ManyToOne
    @ToString.Exclude
    private DistribucionSilla distribucionSilla;


    @OneToMany(mappedBy = "sala")
    @ToString.Exclude
    private List<Funcion>funciones;



    @ManyToOne
    @ToString.Exclude
    private Teatro teatro;


    public Sala(String nombre, DistribucionSilla distribucionSilla, Teatro teatro) {
        this.nombre = nombre;
        this.distribucionSilla = distribucionSilla;
        this.teatro = teatro;
    }
}
