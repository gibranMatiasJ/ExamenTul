package com.example.bussines

import com.example.dao.IProductoBussines
import com.example.dao.ProductoRepository
import com.example.model.Producto
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Service
@Repository
class ProductoBussines: IProductoBussines {

    @Autowired
    lateinit  var productoRepository: ProductoRepository;

    override fun list(): List<Producto> {
        try{
            return productoRepository!!.findAll();
        }catch (e:Exception){
            print("hubo un error al traer todos los productos: "+e.message)
            return emptyList()
        }
    }

    override fun load(idProducto: Long): Producto {
       val op: Optional<Producto>
       try{
           op= productoRepository!!.findById(idProducto)
           if(!op.isPresent)
               throw NotFoundException("No se encontró el producto con id: $idProducto")
               return op.get()

       }catch (e:Exception){
           println("hubo un error al cargar el producto con id $idProducto")
           throw Exception(e);
       }

    }

    override fun save(producto: Producto): Producto {
        try{
            return productoRepository!!.save(producto)
        }catch(e:Exception ){
            println("hubo un error al guardar el producto ")
            throw Exception(e);
        }


    }

    override fun remove(idProducto: Long) {
       val op:Optional<Producto>
        try {
            op=productoRepository!!.findById(idProducto)
            if(!op.isPresent)
                throw NotFoundException("No se encontro en la base de datos el producto con id $idProducto")
            else
                productoRepository!!.deleteById(idProducto)
        }catch (e:Exception){
            println("hubo un error al eliminar el producto ")
            throw Exception(e)
        }
    }
}