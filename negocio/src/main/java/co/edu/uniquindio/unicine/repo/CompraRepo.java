package co.edu.uniquindio.unicine.repo;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer>{

    @Query("select c from Compra c where c.cliente.correo = :correo")
    List<Compra> obtenerCompras(String correo);

    @Query("select e from Compra c join c.entradas e where c.codigo = :codigoCompra")
    List<Entrada>obtenerEntradas(Integer codigoCompra);

    @Query("select c.cliente.cedula, count(c) from Compra c where c.cuponCliente is not null group by c.cliente")
    List<Object[]> contarCuponesRedimidos();


}
