package com.lab.servlets;

import com.lab.beans.Producto;
import com.lab.dao.DaoCategoria;
import com.lab.dao.DaoProducto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/productos")
public class ProductoServlet
        extends HttpServlet {

    private final DaoProducto daoProducto =
            new DaoProducto();

    private final DaoCategoria daoCategoria =
            new DaoCategoria();

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

        String action =
                request.getParameter("action");

        if(action == null){

            request.setAttribute(
                    "listaProductos",
                    daoProducto.listarProductos());

            request.getRequestDispatcher(
                            "/productos.jsp")
                    .forward(
                            request,
                            response);

            return;
        }

        switch(action){

            case "nuevo":

                request.setAttribute(
                        "listaCategorias",
                        daoCategoria.listarCategorias());

                request.getRequestDispatcher(
                                "/productoForm.jsp")
                        .forward(
                                request,
                                response);

                break;

            case "editar":

                int idEditar =
                        Integer.parseInt(
                                request.getParameter(
                                        "id"));

                request.setAttribute(
                        "producto",
                        daoProducto.obtenerProducto(
                                idEditar));

                request.setAttribute(
                        "listaCategorias",
                        daoCategoria.listarCategorias());

                request.getRequestDispatcher(
                                "/productoForm.jsp")
                        .forward(
                                request,
                                response);

                break;

            case "eliminar":

                int idEliminar =
                        Integer.parseInt(
                                request.getParameter(
                                        "id"));

                daoProducto.eliminarProducto(
                        idEliminar);

                response.sendRedirect(
                        request.getContextPath()
                                + "/productos");

                break;
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

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

        String idProducto =
                request.getParameter(
                        "idProducto");

        Producto producto =
                new Producto();

        producto.setIdCategoria(
                Integer.parseInt(
                        request.getParameter(
                                "idCategoria")));

        producto.setNombre(
                request.getParameter(
                        "nombre"));

        producto.setDescripcion(
                request.getParameter(
                        "descripcion"));

        producto.setPrecio(
                new BigDecimal(
                        request.getParameter(
                                "precio")));

        producto.setStock(
                Integer.parseInt(
                        request.getParameter(
                                "stock")));

        if(idProducto == null ||
                idProducto.isEmpty()){

            daoProducto.crearProducto(
                    producto);

        }else{

            producto.setIdProducto(
                    Integer.parseInt(
                            idProducto));

            daoProducto.actualizarProducto(
                    producto);
        }

        response.sendRedirect(
                request.getContextPath()
                        + "/productos");
    }
}