package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * Esta es la interface AdministradorTeatroRepo
 */
public interface AdministradorTeatroRepo extends JpaRepository<AdministradorTeatro, String>{


    AdministradorTeatro findByCedula(String cedula);

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

    @Query("select d from AdministradorTeatro a, IN (a.teatros) d where a.cedula = :cedula")
    List<Teatro> obtenerTeatrosAdmin(String cedula);

    @Query("select a from AdministradorTeatro a where a.correo = :correo and a.password = :password")
    AdministradorTeatro comprobarAutenticacion(String correo, String password);

    @Query("select t from AdministradorTeatro a join a.teatros t where a.cedula = :cedulaAdmiTeatro")
    List<Teatro> obtenerListaTeatros (String cedulaAdmiTeatro);


}
