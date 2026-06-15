<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Login</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/estilos.css">

</head>
<body>

<div class="contenedor-form">

    <div class="card">

        <h2>Iniciar Sesión</h2>

        <form method="post"
              action="${pageContext.request.contextPath}/login">

            <label>Correo</label>

            <input type="email"
                   name="email"
                   required>

            <label>Contraseña</label>

            <input type="password"
                   name="password"
                   required>

            <button type="submit">

                Ingresar

            </button>

        </form>

        <%
            String error =
                    request.getParameter(
                            "error");

            if(error != null){
        %>

        <p style="color:red">

            Usuario o contraseña incorrectos

        </p>

        <%
            }
        %>

    </div>

</div>

</body>
</html>