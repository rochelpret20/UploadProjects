<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Producto</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/estilos.css">

</head>
<body>

<%@ include file="includes/navbar.jspf" %>

<div class="contenedor-form">

    <div class="card">

        <h2>

            ${empty producto ?
                    "Registrar Producto"
                    :
                    "Editar Producto"}

        </h2>

        <form method="post"
              action="${pageContext.request.contextPath}/productos">

            <input type="hidden"
                   name="idProducto"
                   value="${producto.idProducto}">

            <label>Categoría</label>

            <select name="idCategoria">

                <c:forEach var="c"
                           items="${listaCategorias}">

                    <option value="${c.idCategoria}"

                        ${producto.idCategoria ==
                                c.idCategoria ?
                                'selected' : ''}>

                            ${c.nombre}

                    </option>

                </c:forEach>

            </select>

            <label>Nombre</label>

            <input type="text"
                   name="nombre"
                   value="${producto.nombre}"
                   required>

            <label>Descripción</label>

            <input type="text"
                   name="descripcion"
                   value="${producto.descripcion}"
                   required>

            <label>Precio</label>

            <input type="number"
                   step="0.01"
                   name="precio"
                   value="${producto.precio}"
                   required>

            <label>Stock</label>

            <input type="number"
                   name="stock"
                   value="${producto.stock}"
                   required>

            <div class="botones-form">

                <button type="submit">

                    Guardar

                </button>

                <a class="btn btn-regresar"
                   href="${pageContext.request.contextPath}/productos">

                    Cancelar

                </a>

            </div>

        </form>

    </div>

</div>

</body>
</html>