package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.CuponCliente;
import co.edu.uniquindio.unicine.entidades.EstadoCupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuponClienteRepo extends JpaRepository<CuponCliente, Integer>{

    //alreves
    @Query("select cup from Cliente cli join cli.cupones cup where cli.correo = :correo")
    List<CuponCliente>obtenerCupones (String correo);

    //Esta consulta busca el cupon de un cliente dado el codigo del cupon
    @Query("select cupCli from CuponCliente cupCli where cupCli.cupon.codigo = :codigoCupon")
    CuponCliente buscarCuponClientePorCodigoCupon(Integer codigoCupon);

    @Query("select c from CuponCliente c where c.cliente.cedula = :cedulaCliente and c.estado = :estadoCupon")
    List<CuponCliente> obtenerCuponesNoUsados(String cedulaCliente, EstadoCupon estadoCupon);
}
