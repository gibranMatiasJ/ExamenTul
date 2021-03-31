package com.example.dao


import com.example.model.Pedido

import org.springframework.stereotype.Repository

@Repository
interface IPedidoBussines {
    fun list():List<Pedido>
    fun load(idPedido:Long): Pedido
    fun save(pedido: Pedido): Pedido
    fun remove(idPedido:Long)

}