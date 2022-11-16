package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdminSuperServicio;
import co.edu.uniquindio.unicine.servicios.CloudinaryServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.*;
import java.util.*;

@Component
@ViewScoped
public class PeliculaBean implements Serializable {

    @Setter @Getter
    private Pelicula pelicula;

    @Setter @Getter
    private List<Pelicula> peliculas;

    @Setter @Getter
    private List<Pelicula> peliculasSeleccionadas;
    private boolean editar;
    @Autowired
    private CloudinaryServicio cloudinaryServicio;
    @Autowired
    private  AdminSuperServicio adminSuperServicio;

    private Map<String, String> imagenes;

    @Setter @Getter
    private List<Genero> generos;

    @PostConstruct
    public void init()  {
        pelicula = new Pelicula();
        try {
            peliculas = adminSuperServicio.listarPeliculas();
        } catch (Exception e) {
            e.printStackTrace();
        }
        peliculasSeleccionadas = new ArrayList<>();
        editar= false;
        imagenes = new HashMap<>();
        generos = Arrays.asList(Genero.values());
    }

    public void registrarPelicula(){
        try {
            if(!editar) {

                if(!imagenes.isEmpty()) {
                    pelicula.setImagenes(imagenes);
                    pelicula.setEstadoPelicula(EstadoPelicula.CARTELERA);
                    Pelicula registro = adminSuperServicio.crearPelicula(pelicula);
                    peliculas.add(registro);

                    pelicula = new Pelicula();
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La pelicula se ha creado exitosamente");
                    FacesContext.getCurrentInstance().addMessage("mensaje_registro_pelicula", facesMessage);
                }else {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Es necesario subir al menos una foto");
                    FacesContext.getCurrentInstance().addMessage("mensaje_registro_pelicula", fm);
                }
            }else{
                adminSuperServicio.actualizarDatosPelicula(pelicula.getCodigo(),pelicula);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La pelicula se ha actualizado exitosamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_pelicula", facesMessage);
            }
        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_pelicula", facesMessage);
        }
    }



    public void eliminarPelicula(){

        try {
            for (Pelicula pelicula : peliculasSeleccionadas){
                adminSuperServicio.eliminarPelicula(pelicula.getCodigo());
                peliculas.remove(pelicula);
            }
            peliculasSeleccionadas.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_eliminar_pelicula", fm);
        }
    }


    public String getMensajeBorrar(){
        if(peliculasSeleccionadas.isEmpty()){
            return "Borrar";
        }else{
            return "Borrar (" + peliculasSeleccionadas.size() + ")" ;
        }

    }

    public String getMensajeCrearEditar(){
        if(editar){
            return "EDITAR PELICULA";
        }
        return "CREAR PELICULA" ;
    }


    public void seleccionarPelicula(Pelicula peliculaSelec){
        this.pelicula=peliculaSelec;
        editar=true;
    }

    public void crearPeliculaDialog(){
        this.pelicula= new Pelicula();
        editar=false;
    }

    public void subirImagenes(FileUploadEvent event) throws IOException {
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedfile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "peliculas");
            imagenes.put(resultado.get("public_id").toString(), resultado.get("url").toString());
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_pelicula", fm);
        }
    }

    private File convertirUploadedfile(UploadedFile imagen) {
        File file = new File(imagen.getFileName());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(imagen.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}