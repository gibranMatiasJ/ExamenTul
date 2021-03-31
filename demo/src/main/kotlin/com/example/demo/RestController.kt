package com.example.demo

import com.example.dao.IPedidoBussines
import com.example.dao.IProductoBussines
import com.example.model.Estado
import com.example.model.Pedido
import com.example.model.Producto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.websocket.server.PathParam



@Controller
@Repository
@RequestMapping("/servicioPrueba")
@Validated
class RestController {

    @Autowired
    lateinit var productoBussines: IProductoBussines

    @Autowired
    lateinit var pedidoBussines: IPedidoBussines


    @Autowired
    lateinit var producto: Producto

    @Autowired
    lateinit var pedido: Pedido


    @GetMapping("/stock")
    fun listarProductos(): ResponseEntity<List<Producto>> {

        return ResponseEntity(productoBussines!!.list(), HttpStatus.OK)

    }

    @GetMapping("/llenar")
    fun llenarRepositoryDefault(): String {
        productoBussines!!.save(Producto("Cemento", "CEM001", "Cemento bueno", 500,  true))
        productoBussines!!.save(Producto("LADRILLOS", "LAD001", "LADRILLOS NARANJAS", 5,  false))
       // pedidoBussines!!.save(Pedido 4, 0))
        return "hola"
    }

    @GetMapping("/stock2")
    fun mostrarProductos(model: Model): String {
        model.addAttribute("productos", productoBussines!!.list())

        return "mostrarProductos.html"
    }

    @GetMapping("/carrito")
    fun mostrarCarrito(model: Model): String {
        val pedidosLista = pedidoBussines.list()
        val arregloProductos = mutableListOf<Producto>()

        pedidosLista.forEachIndexed { index, pedido ->
            producto = productoBussines!!.load(pedido.idProducto)
            arregloProductos.add(producto)
            if (producto.tipo)
                pedidosLista.get(index).importe =
                    (arregloProductos.get(index).precio * pedidosLista.get(index).cantidad) / 2
            else
                pedidosLista.get(index).importe = arregloProductos.get(index).precio * pedidosLista.get(index).cantidad

        }
        model.addAttribute("productos", arregloProductos)
        model.addAttribute("pedidos", pedidosLista)
        model.addAttribute("total", obtenerImporteFinal())

        return "mostrarCarrito.html"
    }

    @PostMapping("/agregarProducto")
    fun agregarProducto(@Valid @RequestBody producto: Producto): ResponseEntity<Producto> {

        return try {
            productoBussines!!.save(producto)

            return ResponseEntity(producto, HttpStatus.OK)
        } catch (e: Exception) {
            println("error al salvar producto " + e.stackTrace)
            return ResponseEntity(producto, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @PostMapping("/agregarPedido")
    fun agregarPedido(@RequestBody pedido: Pedido): ResponseEntity<Pedido> {
        return try {
            pedidoBussines!!.save(pedido)
            return ResponseEntity(pedido, HttpStatus.OK)
        } catch (e: Exception) {
            return ResponseEntity(pedido, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/borrarProducto/{id}")
    fun eliminarProducto(@PathVariable("id") idProducto: Long): ResponseEntity<Any> {
        return try{
            productoBussines!!.remove(idProducto)
            val pedidosLista = pedidoBussines.list()
            pedidosLista.forEachIndexed { index, pedido ->
              if(pedido.idProducto==idProducto)
                  pedidoBussines.remove(pedido.id)
            }
            ResponseEntity(HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/borrarPedido/{id}")
    fun eliminarPedido(@PathVariable("id") idPedido: Long): ResponseEntity<Any> {
        return try {
            pedidoBussines!!.remove(idPedido)
            ResponseEntity(HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun obtenerImporteFinal(): Long {
        var total: Long = 0;
        val pedidosLista = pedidoBussines.list()
        pedidosLista.forEachIndexed { index, pedido ->
            producto = productoBussines!!.load(pedido.idProducto)
            if(pedido.estado==Estado.PENDIENTE)
            total += pedidosLista.get(index).importe

        }
        return total

    }

    @GetMapping("/cambiarEstadoProductos")
    fun cambiarEstadoACompletado(): String {
        val pedidosLista = pedidoBussines.list()
        pedidosLista.forEachIndexed { index, pedido ->
            pedido.estado=Estado.COMPLETADO
            pedidoBussines!!.save(pedido)

        }
        return "mostrarCarrito.html"
    }
}
