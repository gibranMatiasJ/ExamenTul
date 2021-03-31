

function mostrarModal(id){
    $("#"+id).show();
}

function ocultarModal(id){
    $("#"+id).hide();
}
function irACarrito(){
    window.location.href="http://localhost:8080/servicioPrueba/carrito";
}
function borrarRegistroPedido(id){
    console.log("le diste borrar al id "+id);
    var settings = {
        "url": "http://localhost:8080/servicioPrueba/borrarPedido/"+id,
        "method": "DELETE",
        "timeout": 0,
    };

    $.ajax(settings).done(function (response) {
        console.log("eliminación correcta del producto");
        location.reload()

    });
}
 function borrarRegistroProducto(id){
     var settings = {
         "url": "http://localhost:8080/servicioPrueba/borrarProducto/"+id,
         "method": "DELETE",
         "timeout": 0,
     };

     $.ajax(settings).done(function (response) {
         console.log("eliminación correcta del producto");

     });

 }
$(function () {
    $(document).on('click', '.borrar', function (event) {
        event.preventDefault();
        $(this).closest('tr').remove();
        //aqui va ajax para eliminarlo de repositorio

    });
});

function agregarPedido(idProducto,cantidad){
    if(cantidad==null||cantidad==0)
        alert("la cantidad no puede ir vacia")
    else{
        console.log("los parametros que llegaron son "+idProducto+", "+cantidad);
        var settings = {
            "url": "http://localhost:8080/servicioPrueba/agregarPedido",
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify({"idProducto":idProducto,"cantidad":cantidad}),
        };

        $.ajax(settings).done(function (response) {
            $('#mymodal').show();

        });
    }
}
function agregarProducto(sku,nombre,descripcion,precio,descuento){
    if(nombre==null||nombre==""||sku==null||sku==""||descripcion==null||descripcion=="")
        alert('no debes dejar campos vacios')
    else{
        var settings = {
            "url": "http://localhost:8080/servicioPrueba/agregarProducto",
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/json"
            },
            "data": JSON.stringify({"nombre":nombre,"sku":sku,"descripcion":descripcion,"precio":precio,"tipo":descuento}),
        };

        $.ajax(settings).done(function (response) {
            location.reload()
        });
    }}

    function terminarCompra(){
        var settings = {
            "url": "http://localhost:8080/servicioPrueba/cambiarEstadoProductos",
            "method": "GET",
            "timeout": 0,
        };

        $.ajax(settings).done(function (response) {
          alert("su compra se realizó con éxito, el estado de sus productos ha pasado a completado");
            location.reload()

        });
    }




