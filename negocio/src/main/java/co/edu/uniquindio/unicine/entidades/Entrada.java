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
public class Entrada implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;


    @Column(nullable = false)
    private Double precio;


    @Column(nullable = false)
    private Integer fila;


    @Column(nullable = false)
    private Integer columna;


    @Enumerated(EnumType.STRING)
    private EstadoAsistencia estadoAsistencia;


    @ManyToOne
    @ToString.Exclude
    private Compra compra;


    public Entrada(Double precio, Integer fila, Integer columna, EstadoAsistencia estadoAsistencia, Compra compra) {
        this.precio = precio;
        this.fila = fila;
        this.columna = columna;
        this.estadoAsistencia = estadoAsistencia;
        this.compra = compra;
    }
}
