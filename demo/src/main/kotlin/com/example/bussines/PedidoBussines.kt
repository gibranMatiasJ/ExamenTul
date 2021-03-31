package com.example.bussines

import com.example.model.Pedido
import com.example.dao.IPedidoBussines
import com.example.dao.PedidoRepository
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Service
@Repository
class PedidoBussines: IPedidoBussines {

    @Autowired
    lateinit  var pedidoRepository: PedidoRepository;

    override fun list(): List<Pedido> {
        try{
            return pedidoRepository!!.findAll();
        }catch (e:Exception){
            print("hubo un error al traer todos los productos: "+e.message)
            return emptyList()
        }
    }




    override fun load(idPedido: Long): Pedido {
        val op: Optional<Pedido>
        try{
            op= pedidoRepository!!.findById(idPedido)
            if(!op.isPresent)
                throw NotFoundException("No se encontró el producto con id: $idPedido")
            return op.get()

        }catch (e:Exception){
            println("hubo un error al cargar el producto con id $idPedido")
            throw Exception(e);
        }
    }

    override fun save(pedido: Pedido): Pedido {
        try{
            return pedidoRepository!!.save(pedido)
        }catch(e:Exception ){
            println("hubo un error al guardar el producto ")
            throw Exception(e);
        }


    }

    override fun remove(idPedido: Long) {
        val op:Optional<Pedido>
        try {
            op=pedidoRepository!!.findById(idPedido)
            if(!op.isPresent)
                throw NotFoundException("No se encontro en la base de datos el producto con id $idPedido")
            else
                pedidoRepository!!.deleteById(idPedido)
        }catch (e:Exception){
            println("hubo un error al eliminar el producto ")
            throw Exception(e)
        }
    }
    }