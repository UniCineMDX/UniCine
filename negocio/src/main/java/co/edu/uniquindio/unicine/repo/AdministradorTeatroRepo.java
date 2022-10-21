package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorSuper;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Esta es la interface AdministradorTeatroRepo
 */
public interface AdministradorTeatroRepo extends JpaRepository<AdministradorTeatro, String>{


    /**
     * Este metodo permite obtener un AdministradorTeatro por medio de su correo
     * @param correo
     * @return El administrador o null
     */
    AdministradorTeatro findByCorreo(String correo);

    /**
     * Este metodo permite obtener un AdministradorTeatro por medio de su correo y contraseña
     * @param correo
     * @param password
     * @return El administrador o null
     */
    AdministradorTeatro findByCorreoAndPassword(String correo, String password);
}
