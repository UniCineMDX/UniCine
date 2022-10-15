package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorSuper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorSuperRepo extends JpaRepository<AdministradorSuper, String>{

    @Query("select a from AdministradorSuper a where a.correo = :correo")
    AdministradorSuper obtener(@Param("correo") String correo);
    AdministradorSuper findByCorreo(String correo);


    @Query("select a from AdministradorSuper a where a.correo = :correo and a.password = :password")
    AdministradorSuper comprobarAutenticacion(String correo, String password);

    AdministradorSuper findByCorreoAndPassword(String correo, String password);
}
