package com.example.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@EntityScan(basePackages = ["com.example.model"])
@ComponentScan(basePackages = ["com.example"])
@EnableJpaRepositories(basePackages = ["com.example.bussines","com.example.dao"])
class DemoApplication:CommandLineRunner
{

   // @Autowired
    //val productoRepository: ProductoRepository?=null

    //@Autowired
    //val producto:Producto?=null

    override fun run(vararg args: String?) {
       // println("miraaa "+productoRepository)
     //  val producto1=Producto("CEMENTO","SKUU","CEMENTO PARA MEZCLA DE ALBAÑIL",100,Estado.PENDIENTE)
       // producto!!.nombre="CEMNTO"
      //productoRepository!!.save(producto!!)

    }

}



fun main(args: Array<String>) {
    println("holaaa")

    runApplication<DemoApplication>(*args)

}