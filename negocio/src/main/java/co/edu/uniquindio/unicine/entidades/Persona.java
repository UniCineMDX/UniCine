package co.edu.uniquindio.unicine.entidades;


import lombok.*;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 10)
    @Column(unique = true, length = 10)
    private String cedula;


    @Length(max = 100)
    @Column(nullable = false,length = 100)
    private String nombre;


    @Email
    @Length(max = 50)
    @Column(nullable = false,length = 50, unique = true)
    private String correo;


    @ToString.Exclude
    @Length(max = 50)
    @Column(nullable = false,length = 50)
    private String password;


    @Length(max = 100)
    @Column(nullable = false,length = 100)
    private String fotoUrl = "";


}
