package co.edu.uniquindio.unicine.entidades;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //Herencia
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Persona implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Column(unique = true, length = 10)
    private String cedula;

    @Column(nullable = false,length = 100)
    private String nombre;

    @Email
    @Column(nullable = false,length = 50, unique = true)
    private String correo;


    @ToString.Exclude
    @Column(nullable = false,length = 50)
    private String password;

    @Column(nullable = false,length = 100)
    private String fotoUrl;


}
