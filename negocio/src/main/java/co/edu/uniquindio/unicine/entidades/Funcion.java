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
public class Funcion implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;


    @Column(nullable = false)
    private Double precio;


    @ManyToOne
    @ToString.Exclude
    private Horario horario;

    @ManyToOne
    @ToString.Exclude
    private Pelicula pelicula;

    @OneToMany(mappedBy = "funcion")
    @ToString.Exclude
    private List<Compra>compras;

    @ManyToOne
    @ToString.Exclude
    private Sala sala;

    @Builder
    public Funcion(Double precio, Horario horario, Pelicula pelicula, Sala sala) {
        this.precio = precio;
        this.horario = horario;
        this.pelicula = pelicula;
        this.sala = sala;
    }
}
