package com.example.dao




import com.example.model.Pedido
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PedidoRepository: JpaRepository<Pedido, Long> {
}