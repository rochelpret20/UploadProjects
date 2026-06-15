package com.lab.dao;

import com.lab.beans.Carrito;
import com.lab.config.ConexionDB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaoCarrito {

    public void agregarProducto(
            int idUsuario,
            int idProducto) {

        String sqlBuscar = """
                SELECT *
                FROM carrito_item
                WHERE id_usuario = ?
                AND id_producto = ?
                """;

        String sqlActualizar = """
                UPDATE carrito_item
                SET cantidad = cantidad + 1
                WHERE id_usuario = ?
                AND id_producto = ?
                """;

        String sqlInsertar = """
                INSERT INTO carrito_item
                (
                    id_usuario,
                    id_producto,
                    cantidad
                )
                VALUES
                (
                    ?, ?, 1
                )
                """;

        try(Connection con =
                    ConexionDB.getConnection()) {

            PreparedStatement psBuscar =
                    con.prepareStatement(
                            sqlBuscar);

            psBuscar.setInt(
                    1,
                    idUsuario);

            psBuscar.setInt(
                    2,
                    idProducto);

            ResultSet rs =
                    psBuscar.executeQuery();

            if(rs.next()) {

                PreparedStatement psUpdate =
                        con.prepareStatement(
                                sqlActualizar);

                psUpdate.setInt(
                        1,
                        idUsuario);

                psUpdate.setInt(
                        2,
                        idProducto);

                psUpdate.executeUpdate();

            } else {

                PreparedStatement psInsert =
                        con.prepareStatement(
                                sqlInsertar);

                psInsert.setInt(
                        1,
                        idUsuario);

                psInsert.setInt(
                        2,
                        idProducto);

                psInsert.executeUpdate();
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public ArrayList<Carrito>
    listarCarrito(int idUsuario) {

        ArrayList<Carrito> lista =
                new ArrayList<>();

        String sql = """
                SELECT
                ci.id_item,
                ci.id_usuario,
                p.id_producto,
                p.nombre,
                p.precio,
                ci.cantidad,

                (
                    p.precio *
                    ci.cantidad
                ) subtotal,

                CONCAT(
                    u.nombres,
                    ' ',
                    u.apellidos
                ) usuario

                FROM carrito_item ci

                INNER JOIN producto p
                ON ci.id_producto =
                   p.id_producto

                INNER JOIN usuario u
                ON ci.id_usuario =
                   u.id_usuario

                WHERE ci.id_usuario = ?

                ORDER BY ci.id_item
                """;

        try(Connection con =
                    ConexionDB.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setInt(
                    1,
                    idUsuario);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Carrito carrito =
                        new Carrito();

                carrito.setIdItem(
                        rs.getInt(
                                "id_item"));

                carrito.setIdUsuario(
                        rs.getInt(
                                "id_usuario"));

                carrito.setIdProducto(
                        rs.getInt(
                                "id_producto"));

                carrito.setNombreProducto(
                        rs.getString(
                                "nombre"));

                carrito.setPrecioUnit(
                        rs.getBigDecimal(
                                "precio"));

                carrito.setCantidad(
                        rs.getInt(
                                "cantidad"));

                carrito.setSubtotal(
                        rs.getBigDecimal(
                                "subtotal"));

                carrito.setNombreUsuario(
                        rs.getString(
                                "usuario"));

                lista.add(carrito);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

    public BigDecimal obtenerTotal(
            int idUsuario) {

        BigDecimal total =
                BigDecimal.ZERO;

        String sql = """
                SELECT
                SUM(
                    p.precio *
                    ci.cantidad
                ) total
                FROM carrito_item ci

                INNER JOIN producto p
                ON ci.id_producto =
                   p.id_producto

                WHERE ci.id_usuario = ?
                """;

        try(Connection con =
                    ConexionDB.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setInt(
                    1,
                    idUsuario);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                total =
                        rs.getBigDecimal(
                                "total");

                if(total == null){

                    total =
                            BigDecimal.ZERO;
                }
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return total;
    }
}