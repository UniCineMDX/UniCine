package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorSuper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * Esta es la interface AdministradorSuperRepo
 */
public interface AdministradorSuperRepo extends JpaRepository<AdministradorSuper, String>{

    /**
     * Este metodo permite obtener un AdministradorSuper por medio de su correo
     * @param correo
     * @return El administrador o null
     */
    AdministradorSuper findByCorreo(String correo);

    /**
     * Este metodo permite obtener un Administrador por medio de su correo y contraseña
     * @param correo
     * @param password
     * @return El administrador o null
     */
    AdministradorSuper findByCorreoAndPassword(String correo, String password);


    @Query("select c.codigo, c.nombre, count(t) from Ciudad c inner join c.teatros t group by c.codigo")
    List<Object[]> contarTeatros();

}
