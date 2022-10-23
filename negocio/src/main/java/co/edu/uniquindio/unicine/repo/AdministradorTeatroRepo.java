package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministradorTeatroRepo extends JpaRepository<AdministradorTeatro, String>{

    AdministradorTeatro findByCedula(String cedula);

    AdministradorTeatro findByCorreo(String correo);

    @Query("select a from AdministradorTeatro a where a.correo = :correo and a.password = :password")
    AdministradorTeatro comprobarAutenticacion(String correo, String password);

    @Query("select t from AdministradorTeatro a join a.teatros t where a.cedula = :cedulaAdmiTeatro")
    List<Teatro> obtenerListaTeatros (String cedulaAdmiTeatro);

}
