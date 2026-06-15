<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Productos</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/estilos.css">

</head>
<body>

<%@ include file="includes/navbar.jspf" %>

<div class="contenedor">

    <h1>Listado de Productos</h1>

    <div class="barra-superior">

        <a class="btn btn-nuevo"
           href="${pageContext.request.contextPath}/productos?action=nuevo">

            Nuevo Producto

        </a>

    </div>

    <table>

        <thead>

        <tr>

            <th>ID</th>
            <th>Nombre</th>
            <th>Categoría</th>
            <th>Precio</th>
            <th>Stock</th>
            <th>Acciones</th>

        </tr>

        </thead>

        <tbody>

        <c:forEach var="p"
                   items="${listaProductos}">

            <tr>

                <td>${p.idProducto}</td>
                <td>${p.nombre}</td>
                <td>${p.categoriaNombre}</td>
                <td>S/ ${p.precio}</td>
                <td>${p.stock}</td>

                <td>

                    <a class="btn btn-editar"
                       href="${pageContext.request.contextPath}/productos?action=editar&id=${p.idProducto}">

                        Editar

                    </a>

                    <a class="btn btn-eliminar"
                       href="${pageContext.request.contextPath}/productos?action=eliminar&id=${p.idProducto}"
                       onclick="return confirm('¿Eliminar producto?')">

                        Eliminar

                    </a>

                    <a class="btn btn-detalle"
                       href="${pageContext.request.contextPath}/carrito?action=agregar&id=${p.idProducto}">

                        Añadir Carrito

                    </a>

                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>

</div>

</body>
</html>