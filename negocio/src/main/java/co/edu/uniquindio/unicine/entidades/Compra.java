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
public class Compra implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MedioPago medioPago;
    @Column(nullable = false)
    private LocalDate fechaCompra;
    @Column(nullable = false)
    private Double valorTotal;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<CompraConfiteria> compraConfiterias;
    @ManyToOne
    @ToString.Exclude
    private Cliente cliente;
    @OneToOne
    @ToString.Exclude
    private CuponCliente cuponCliente;
    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<Entrada> entradas;
    @ManyToOne
    @ToString.Exclude
    private Funcion funcion;

    public Compra(List<Entrada> entradas,MedioPago medioPago, LocalDate fechaCompra, Double valorTotal, Cliente cliente, CuponCliente cuponCliente, Funcion funcion) {
        this.cliente     = cliente;
        this.entradas    = entradas;
        this.funcion     = funcion;
        this.medioPago   = medioPago;
        this.valorTotal  = valorTotal;
        this.fechaCompra = fechaCompra;
    }
}
