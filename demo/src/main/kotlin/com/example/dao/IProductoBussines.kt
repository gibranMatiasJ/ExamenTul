package com.example.dao

import com.example.model.Producto
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Repository
interface IProductoBussines {
    fun list():List<Producto>
    fun load(idProducto:Long):Producto
    fun save(producto: Producto):Producto
    fun remove(idProducto:Long)

}