package com.lab.servlets;

import com.lab.beans.Usuario;
import com.lab.dao.DaoCarrito;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/carrito")
public class CarritoServlet
        extends HttpServlet {

    private final DaoCarrito daoCarrito =
            new DaoCarrito();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null ||
                session.getAttribute("usuario")
                        == null){

            response.sendRedirect(
                    request.getContextPath()
                            + "/login");

            return;
        }

        Usuario usuario =
                (Usuario)
                        session.getAttribute(
                                "usuario");

        String action =
                request.getParameter(
                        "action");

        if(action != null &&
                action.equals(
                        "agregar")) {

            int idProducto =
                    Integer.parseInt(
                            request.getParameter(
                                    "id"));

            daoCarrito.agregarProducto(
                    usuario.getIdUsuario(),
                    idProducto);

            response.sendRedirect(
                    request.getContextPath()
                            + "/productos");

            return;
        }

        request.setAttribute(
                "listaCarrito",
                daoCarrito.listarCarrito(
                        usuario.getIdUsuario()));

        request.setAttribute(
                "total",
                daoCarrito.obtenerTotal(
                        usuario.getIdUsuario()));

        request.getRequestDispatcher(
                        "/carrito.jsp")
                .forward(
                        request,
                        response);
    }
}