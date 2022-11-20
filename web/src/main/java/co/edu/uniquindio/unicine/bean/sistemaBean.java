package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminSuperServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class sistemaBean {

    @Getter @Setter
    List<Ciudad> ciudades;

    @Getter @Setter
    Ciudad ciudadSeleccionada;

    @Autowired
    AdminSuperServicio adminServicio;

    @Autowired
    ClienteServicio clienteServicio;

    @Autowired
    AdministradorTeatroServicio administradorTeatroServicio;

    @Getter @Setter
    List<Pelicula> peliculasCartelera;

    @Getter @Setter
    List<Pelicula> peliculaProximas;

    @Getter @Setter
    List<Pelicula> peliculaEstreno;


    @Getter @Setter
    List<String> imagenesIndex;

    @PostConstruct
    public void init (){
        ciudadSeleccionada = new Ciudad();
        peliculasCartelera = new ArrayList<>();
        peliculaProximas   = new ArrayList<>();
        peliculaEstreno    = new ArrayList<>();
        imagenesIndex      = new ArrayList<>();
        try {
            imagenesIndex.add("/resources/img/banner5.webp");
            imagenesIndex.add("/resources/img/banner6.jpg");
            imagenesIndex.add("/resources/img/banner7.png");

            peliculaEstreno = clienteServicio.buscarPeliculaEstado(EstadoPelicula.ESTRENO);
            peliculaProximas = clienteServicio.buscarPeliculaEstado(EstadoPelicula.PROXIMAMENTE);
            peliculasCartelera = clienteServicio.buscarPeliculaEstado(EstadoPelicula.CARTELERA);
            ciudades = adminServicio.listarCiudades();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cargarPeliculasCiudad(){
        System.out.println(ciudadSeleccionada);
       if(ciudadSeleccionada != null){
           try {
               peliculaEstreno = clienteServicio.buscarPeliculaEstado(EstadoPelicula.ESTRENO);
               peliculaProximas = clienteServicio.listarPeliculasCiudad(ciudadSeleccionada.getCodigo(),EstadoPelicula.PROXIMAMENTE);
               peliculasCartelera = clienteServicio.listarPeliculasCiudad(ciudadSeleccionada.getCodigo(),EstadoPelicula.CARTELERA);
               ciudades = adminServicio.listarCiudades();
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
       }
    }
}
