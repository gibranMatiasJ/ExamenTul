package com.example.model

import com.example.model.Estado
import com.example.model.Producto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.*

@Entity
@Table(name="pedido")
@Component
data class Pedido(
    var idProducto:Long=0,
    var cantidad: Long=1,
    var importe:Long=0,
    var estado:Estado=Estado.PENDIENTE
){



    @Id
  //  @GeneratedValue(strategy=GenerationType.AUTO)
    var id:Long= UUID.randomUUID().mostSignificantBits

}

enum class Estado{
    PENDIENTE,
    COMPLETADO
}

