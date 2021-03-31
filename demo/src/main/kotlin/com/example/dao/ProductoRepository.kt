package com.example.dao

import com.example.model.Producto
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository


@Repository
interface ProductoRepository: JpaRepository<Producto,Long> {

}