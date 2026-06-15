package com.lab.dao;

import com.lab.beans.Usuario;
import com.lab.config.ConexionDB;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoUsuario {

    public Usuario validarUsuario(
            String email,
            String password) {

        Usuario usuario = null;

        String sql = """
                SELECT *
                FROM usuario
                WHERE email = ?
                AND estado = 'ACTIVO'
                """;

        try (Connection con =
                     ConexionDB.getConnection();

             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, email);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                String hashBD =
                        rs.getString(
                                "password_hash");

                String hashIngresado =
                        generarSHA256(
                                password);

                if (hashBD.equals(
                        hashIngresado)) {

                    usuario =
                            new Usuario();

                    usuario.setIdUsuario(
                            rs.getInt(
                                    "id_usuario"));

                    usuario.setNombre(
                            rs.getString(
                                    "nombres")
                                    + " "
                                    +
                                    rs.getString(
                                            "apellidos"));

                    usuario.setCorreo(
                            rs.getString(
                                    "email"));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return usuario;
    }

    private String generarSHA256(
            String texto) {

        try {

            MessageDigest md =
                    MessageDigest.getInstance(
                            "SHA-256");

            byte[] hash =
                    md.digest(
                            texto.getBytes());

            StringBuilder sb =
                    new StringBuilder();

            for (byte b : hash) {

                sb.append(
                        String.format(
                                "%02x",
                                b));
            }

            return sb.toString();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}