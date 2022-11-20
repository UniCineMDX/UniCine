package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

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


    @Lob
    @Column(nullable = false)
    private String sipnosis;


    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagenes;


    @Enumerated(EnumType.STRING)
    private EstadoPelicula estadoPelicula;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private List<Genero> generos;


    @Lob
    @Column(nullable = false)
    private String Reparto;


    @Column(nullable = false, length = 100)
    private String urlTrailer;


    @OneToMany(mappedBy = "pelicula")
    @ToString.Exclude
    private List<Funcion>funciones;

    public Pelicula(String nombre, String sipnosis, Map<String, String> imagenes, EstadoPelicula estadoPelicula, List<Genero> generos, String reparto, String urlTrailer, List<Funcion> funciones) {
        this.nombre = nombre;
        this.sipnosis = sipnosis;
        this.imagenes = imagenes;
        this.estadoPelicula = estadoPelicula;
        this.generos = generos;
        Reparto = reparto;
        this.urlTrailer = urlTrailer;
        this.funciones = funciones;
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

    public String getImagenPrincipal(){
        if(!imagenes.isEmpty()){
            String primera = imagenes.keySet().toArray()[0].toString();
            return imagenes.get(primera);
        }
        return "";
    }
}
