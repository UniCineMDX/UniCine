package co.edu.uniquindio.unicine.servicios;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServicio {

    private Cloudinary cloudinary;
    private Map<String, String>config;

    public CloudinaryServicio(){

        config = new HashMap<>();
        config.put("cloud_name", "dnquj3d8x");
        config.put("api_key", "219679357811215");
        config.put("api_secret", "sf22MJkwZC3iIq4FCGvG-xZpASM");

        cloudinary = new Cloudinary(config);

    }

    public Map subirImagen(File archivo, String carpeta) throws Exception{

        Map respuesta = cloudinary.uploader().upload(archivo, ObjectUtils.asMap("folder", String.format("unicine/%s", carpeta)));;

        return respuesta;

    }


    public Map eliminarImagen(String idImagen) throws Exception{

        Map respuesta =cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());

        return respuesta;

    }
}
