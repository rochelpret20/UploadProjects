package com.lab.servlets;

import com.lab.beans.Usuario;
import com.lab.dao.DaoUsuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final DaoUsuario daoUsuario =
            new DaoUsuario();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                        "/login.jsp")
                .forward(
                        request,
                        response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String email =
                request.getParameter(
                        "email");

        String password =
                request.getParameter(
                        "password");

        Usuario usuario =
                daoUsuario.validarUsuario(
                        email,
                        password);

        if (usuario != null) {

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "usuario",
                    usuario);

            response.sendRedirect(
                    request.getContextPath()
                            + "/productos");

        } else {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login?error=1");
        }
    }
}