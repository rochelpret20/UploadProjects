package com.lab.dao;

import com.lab.beans.Producto;
import com.lab.config.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaoProducto {

    public ArrayList<Producto> listarProductos() {

        ArrayList<Producto> lista =
                new ArrayList<>();

        String sql = """
                SELECT
                p.id_producto,
                p.id_categoria,
                p.nombre,
                p.descripcion,
                p.precio,

                (
                    p.stock -
                    IFNULL(
                        (
                            SELECT SUM(ci.cantidad)
                            FROM carrito_item ci
                            WHERE ci.id_producto =
                                  p.id_producto
                        ),
                        0
                    )
                ) stock_disponible,

                c.nombre categoria

                FROM producto p

                INNER JOIN categoria c
                ON p.id_categoria =
                   c.id_categoria

                ORDER BY p.id_producto
                """;

        try(Connection con =
                    ConexionDB.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()) {

            while(rs.next()) {

                Producto p =
                        new Producto();

                p.setIdProducto(
                        rs.getInt(
                                "id_producto"));

                p.setIdCategoria(
                        rs.getInt(
                                "id_categoria"));

                p.setNombre(
                        rs.getString(
                                "nombre"));

                p.setDescripcion(
                        rs.getString(
                                "descripcion"));

                p.setPrecio(
                        rs.getBigDecimal(
                                "precio"));

                p.setStock(
                        rs.getInt(
                                "stock_disponible"));

                p.setCategoriaNombre(
                        rs.getString(
                                "categoria"));

                lista.add(p);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

    public Producto obtenerProducto(
            int idProducto){

        Producto producto =
                null;

        String sql = """
                SELECT *
                FROM producto
                WHERE id_producto = ?
                """;

        try(Connection con =
                    ConexionDB.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setInt(
                    1,
                    idProducto);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()){

                producto =
                        new Producto();

                producto.setIdProducto(
                        rs.getInt(
                                "id_producto"));

                producto.setIdCategoria(
                        rs.getInt(
                                "id_categoria"));

                producto.setNombre(
                        rs.getString(
                                "nombre"));

                producto.setDescripcion(
                        rs.getString(
                                "descripcion"));

                producto.setPrecio(
                        rs.getBigDecimal(
                                "precio"));

                producto.setStock(
                        rs.getInt(
                                "stock"));
            }

        }catch(Exception e){

            e.printStackTrace();
        }

        return producto;
    }

    public void crearProducto(
            Producto producto){

        String sql = """
                INSERT INTO producto
                (
                    id_categoria,
                    nombre,
                    descripcion,
                    precio,
                    stock
                )
                VALUES
                (
                    ?,?,?,?,?
                )
                """;

        try(Connection con =
                    ConexionDB.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setInt(
                    1,
                    producto.getIdCategoria());

            ps.setString(
                    2,
                    producto.getNombre());

            ps.setString(
                    3,
                    producto.getDescripcion());

            ps.setBigDecimal(
                    4,
                    producto.getPrecio());

            ps.setInt(
                    5,
                    producto.getStock());

            ps.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public void actualizarProducto(
            Producto producto){

        String sql = """
                UPDATE producto
                SET
                id_categoria=?,
                nombre=?,
                descripcion=?,
                precio=?,
                stock=?
                WHERE id_producto=?
                """;

        try(Connection con =
                    ConexionDB.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setInt(
                    1,
                    producto.getIdCategoria());

            ps.setString(
                    2,
                    producto.getNombre());

            ps.setString(
                    3,
                    producto.getDescripcion());

            ps.setBigDecimal(
                    4,
                    producto.getPrecio());

            ps.setInt(
                    5,
                    producto.getStock());

            ps.setInt(
                    6,
                    producto.getIdProducto());

            ps.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public void eliminarProducto(
            int idProducto){

        String sql = """
                DELETE FROM producto
                WHERE id_producto = ?
                """;

        try(Connection con =
                    ConexionDB.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql)) {

            ps.setInt(
                    1,
                    idProducto);

            ps.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();
        }
    }
}