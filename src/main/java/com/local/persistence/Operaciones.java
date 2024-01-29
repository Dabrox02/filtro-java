package com.local.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Operaciones {
    private static Connection conexion;
    private static ResultSet rs;

    public static Connection setConnection(Connection con) {
        conexion = con;
        return conexion;
    }

    public static Connection getConnection() {
        return conexion;
    }

    public static ResultSet consultar(PreparedStatement statement) {
        try {
            rs = statement.executeQuery();
            return rs;
        } catch (SQLException | RuntimeException e) {
            System.out.println("Ocurrio un error " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrio un error " + e.getMessage());
        }
        return null;
    }

    public static int insertarActualizarEliminar(PreparedStatement statement) {
        int filas;
        try {
            filas = statement.executeUpdate();
            return filas;
        } catch (SQLException | RuntimeException e) {
            System.out.println("Ocurrio un error " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrio un error " + e.getMessage());
        }
        return 0;
    }

}
