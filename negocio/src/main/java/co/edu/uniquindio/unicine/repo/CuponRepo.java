package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuponRepo extends JpaRepository<Cupon, Integer>{

    @Query("select c from Cliente c where c.cedula = :cedula")
    List<Cupon> obtenerCuponesCliente(String cedula);

    Cupon findByCodigo(Integer codigo);

}
