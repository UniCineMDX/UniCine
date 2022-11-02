package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfiteriaRepo extends JpaRepository<Confiteria, Integer>{

    Confiteria findByCodigo(Integer codigo);

    Confiteria findByNombre(String nombre);

}
