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
public class Teatro implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false, length = 50)
    private String direccion;


    @Column(nullable = false, length = 10)
    private String telefono;


    @ManyToOne
    @ToString.Exclude
    private AdministradorTeatro admiTeatro;


    @ManyToOne
    @ToString.Exclude
    private Ciudad ciudad;


    @OneToMany(mappedBy = "teatro")
    @ToString.Exclude
    private List<Sala>salas;

    public Teatro(String direccion, String telefono, AdministradorTeatro admiTeatro, Ciudad ciudad) {
        this.direccion = direccion;
        this.telefono = telefono;
        this.admiTeatro = admiTeatro;
        this.ciudad = ciudad;
    }
}
