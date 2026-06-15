package com.lab.dao;

import com.lab.beans.Categoria;
import com.lab.config.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DaoCategoria {

    public ArrayList<Categoria> listarCategorias() {

        ArrayList<Categoria> lista =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM categoria
                ORDER BY nombre
                """;

        try(Connection con =
                    ConexionDB.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery()) {

            while(rs.next()) {

                Categoria categoria =
                        new Categoria();

                categoria.setIdCategoria(
                        rs.getInt(
                                "id_categoria"));

                categoria.setNombre(
                        rs.getString(
                                "nombre"));

                lista.add(categoria);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
}