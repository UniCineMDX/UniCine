package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.dto.FuncionDTO;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Entrada;
import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionRepo extends JpaRepository<Funcion, Integer> {

    Funcion findByCodigo(Integer codigo);

    @Query("select f.pelicula.nombre from Funcion f where f.codigo = :codigoFuncion")
    String obtenerNombrePelicula(Integer codigoFuncion);

    @Query("select distinct f.pelicula from Funcion f")
    List<Pelicula> obtenerPelicula();

    @Query("select new  co.edu.uniquindio.unicine.dto.FuncionDTO(f.pelicula.nombre, f.pelicula.estadoPelicula, f.pelicula.imagenes, f.sala.codigo, f.sala.teatro.direccion, f.sala.teatro.ciudad.nombre, f.horario) from Funcion f where f.pelicula.codigo = :codigo")
    List<FuncionDTO> listarFunciones(Integer codigo);

    @Query("select f from Funcion f where f.sala.teatro.codigo = :codigo and f.compras is empty ")
    List<Funcion> obtenerFuncionesSinCompra(Integer codigo);

    @Query("select funcion.sala from Funcion funcion where funcion.sala.codigo = :codigoSala")
    Optional<Funcion> buscarSalaPorHorario(Integer codigoSala);

   // @Query("select f from Funcion f where f.sala.numeroSala = :codigoSala and :dIaSemana in f.horario.dia and f.horario.hora = :hora")
    //List<Funcion> obtenerFuncionesHorario(Integer codigoSala, DiaSemana dIaSemana, Time hora);

    @Query("select entradas from Funcion funcion, IN (funcion.compras) compras, IN(compras.entradas) entradas where funcion.codigo = :codigo")
    List<Entrada> listaEntradas(Integer codigo);

    @Query("select compras from Funcion funcion, IN (funcion.compras) compras where funcion.codigo = :codigo")
    List<Compra> listaCompra (Integer codigo);

    @Query("select entradas from Funcion funcion, IN (funcion.compras) compras, IN(compras.entradas) entradas where funcion.codigo = :codigo and entradas.fila = :fila and entradas.columna = :columna")
    List<Entrada> verificarSillas(Integer codigo, Integer fila, Integer columna);

    @Query("select entrada from Funcion funcion, IN (funcion.compras) compras, IN(compras.entradas) entrada where funcion.codigo = :codigo and entrada.fila = :fila and entrada.columna = :columna")
    Entrada verificarSilla(Integer codigo, Integer fila, Integer columna);

    //funcion para obtener las funciones de un teatro
    //funcion para obtener las funciones en cierto horario

    @Query("select f.pelicula from Funcion f where f.codigo = :codigo")
    Pelicula obtenerPeliculaFuncion(Integer codigo);

    @Query("select f.horario from Funcion  f where f.codigo = :codigo")
    Horario obtenerHorarioFuncion(Integer codigo);

    @Query("select f.sala from Funcion  f where f.codigo = :codigo")
    Sala obtenerSalaFuncion (Integer codigo);

    @Query("select comp from Funcion f join f.compras comp where f.codigo = :codigo")
    List<Compra> obtenerComprasFuncion (Integer codigo);

}
