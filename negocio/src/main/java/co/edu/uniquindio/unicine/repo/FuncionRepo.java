package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionRepo extends JpaRepository<Funcion, Integer> {

    Funcion findByCodigo(Integer codigo);

    @Query("select f.pelicula.nombre from Funcion f where f.codigo = :codigoFuncion")
    String obtenerNombrePelicula(Integer codigoFuncion);

    @Query("select f from Funcion f where f.sala.teatro.codigo = :codigoTeatro ")
    List<Funcion> obtenerFunciones(Integer codigoTeatro);

    @Query("select f from Funcion f where f.sala.teatro.codigo = :codigoTeatro and f.compras is empty ")
    List<Funcion> obtenerFuncionesSinCompra(Integer codigoTeatro);

    @Query("select f.pelicula from Funcion f where f.codigo = :codigo")
    Pelicula obtenerPeliculaFuncion(Integer codigo);

    @Query("select f.horario from Funcion  f where f.codigo = :codigo")
    Horario obtenerHorarioFuncion(Integer codigo);

    @Query("select f.sala from Funcion  f where f.codigo = :codigo")
    Sala obtenerSalaFuncion (Integer codigo);

    @Query("select comp from Funcion f join f.compras comp where f.codigo = :codigo")
    List<Compra> obtenerComprasFuncion (Integer codigo);

}
