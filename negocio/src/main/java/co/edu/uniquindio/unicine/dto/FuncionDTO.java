package co.edu.uniquindio.unicine.dto;

import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Horario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@Setter
@ToString

public class FuncionDTO {

    private String nombrePelicula;
    private EstadoPelicula estadoPelicula;
    private String urlImagen;
    private Integer numeroSala;
    private String direccionTeatro;
    private String nombreCiudad;
    private Horario horario;


}
