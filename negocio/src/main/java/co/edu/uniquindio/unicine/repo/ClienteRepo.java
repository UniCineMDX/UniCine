package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.CuponCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, String>{

    @Query("select c from Cliente c where c.correo = ?1")
    Cliente obtener(String email);
    Cliente findByCorreo(String correo);

    @Query("select c from Cliente c where c.correo = :correo and c.password = :password")
    Cliente comprobarAutenticacion (String correo, String password);

    //Lista de compras por medio del correo del cliente(Metodo in)
    @Query("select comp from Cliente cli, in (cli.compras) comp where cli.correo = :correo")
    List<Compra> obtenerCompras(String correo);

    //Lista de compras por medio del correo del cliente(Metodo join)
    @Query("select comp from Cliente cli join cli.compras comp where cli.correo = :correo")
    List<Compra> obtenerCompras2(String correo);

    @Query("select cup from Cliente cli join cli.cupones cup where cli.correo = :correo")
    List<CuponCliente>obtenerCupones(String correo);

    @Query("select cli.nombre, comp from Cliente cli left join cli.compras comp")
    List<Object[]>obtenerComprasTodos();


}
