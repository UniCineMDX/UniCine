package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.repo.CompraRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CompraServicioImpl {


    private CompraRepo compraRepo;

    public CompraServicioImpl(CompraRepo compraRepo) {
        this.compraRepo = compraRepo;
    }

    public Compra obtenerCompra(Integer codigoCompra) throws Exception{

        Compra compra = compraRepo.findByCodigo(codigoCompra);

        if(compra == null){
            throw new Exception("La compra no existe");
        }

        return compraRepo.save(compra);

    }

    public void eliminarCompra(Integer codigoCompra) throws Exception{

        Optional<Compra> compraGuardada = compraRepo.findById(codigoCompra);
    }


    public List<Compra> listarCompras(){
       return  compraRepo.findAll();
    }
}
