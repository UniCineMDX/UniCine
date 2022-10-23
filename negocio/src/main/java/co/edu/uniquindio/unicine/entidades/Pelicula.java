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
public class Pelicula implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;


    @Column(nullable = false, length = 50,unique = true)
    private String nombre;


    @Column(length = 200)
    private String sipnosis;


    @Column(nullable = false, length = 100)
    private String urlTrailer;


    @Column(nullable = false,length = 100)
    private String urlImagen;


    @Enumerated(EnumType.STRING)
    private EstadoPelicula estadoPelicula;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;


    @Column(length = 100)
    private String Reparto;



    @OneToMany(mappedBy = "pelicula")
    @ToString.Exclude
    private List<Funcion>funciones;



    public Pelicula(String nombre, String sipnosis, String urlTrailer, String urlImagen, String reparto, String genero) {
        this.nombre = nombre;
        this.sipnosis = sipnosis;
        this.urlTrailer = urlTrailer;
        this.urlImagen = urlImagen;
        this.estadoPelicula = EstadoPelicula.CREADA;
        this.Reparto = reparto;
        this.genero = obtenerGenero(genero);
    }

    private Genero obtenerGenero(String genero) {

        if(genero.equals("Terror")){
            return Genero.TERROR;
        }else{
            if(genero.equals("Drama")){
                return Genero.DRAMA;
            }else{
                if(genero.equals("Comedia")){
                    return Genero.COMEDIA;
                }else {
                    if (genero.equals("Romance")) {
                        return Genero.ROMANCE;
                    }else{
                        return Genero.CIENCIA_FICCIÓN;
                    }
                }
            }
        }
    }

    private EstadoPelicula obtenerEstado(String estadoPelicula) {
        if(estadoPelicula.equals("Preventa")){
            return EstadoPelicula.PREVENTA;
        }else{
            if(estadoPelicula.equals("Estreno")){
                return EstadoPelicula.ESTRENO;
            }else{
                if(estadoPelicula.equals("Cartelera")){
                    return EstadoPelicula.CARTELERA;
                }else {
                    return EstadoPelicula.PROXIMAMENTE;
                }
            }
        }
    }
}
