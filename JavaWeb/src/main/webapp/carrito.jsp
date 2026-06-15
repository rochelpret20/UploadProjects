<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Carrito</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/estilos.css">

</head>
<body>

<%@ include file="includes/navbar.jspf" %>

<div class="contenedor">

    <h1>Carrito de Compras</h1>

    <table>

        <thead>

        <tr>

            <th>ID Item</th>

            <th>Producto</th>

            <th>Usuario</th>

            <th>Precio Unitario</th>

            <th>Cantidad</th>

            <th>Subtotal</th>

        </tr>

        </thead>

        <tbody>

        <c:forEach var="c"
                   items="${listaCarrito}">

            <tr>

                <td>${c.idItem}</td>

                <td>${c.nombreProducto}</td>

                <td>${c.nombreUsuario}</td>

                <td>S/ ${c.precioUnit}</td>

                <td>${c.cantidad}</td>

                <td>S/ ${c.subtotal}</td>

            </tr>

        </c:forEach>

        </tbody>

    </table>

    <br>

    <div class="card">

        <h2>

            Total General:
            S/ ${total}

        </h2>

    </div>

</div>

</body>
</html>