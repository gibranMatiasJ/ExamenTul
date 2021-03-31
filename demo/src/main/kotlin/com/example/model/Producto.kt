package com.example.model

import org.jetbrains.annotations.NotNull
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.*
import javax.validation.*
import javax.validation.constraints.*
import java.util.UUID




@Entity
@Table(name="producto")
@Component
 data class Producto(


    @get:NotEmpty(message="el nombre no debe venir vacio")
    var nombre:String="",


    @get: NotEmpty(message="el sku no debe venir vacio")
    var sku:String="",


    @get:NotEmpty(message="la desc no debe venir vacia")
    var descripcion:String="",


    var precio:Long=0,



    var tipo:Boolean=false)
{

    @Id
   // @GeneratedValue(strategy=GenerationType.AUTO)
    var id:Long= UUID.randomUUID().mostSignificantBits

}


