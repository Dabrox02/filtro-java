package com.local.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;

public class ConexionBD {

    private static String url;
    private static String usuario;
    private static String clave;
    public static Connection conexion = null;

    public static Connection MYSQLConexion() {
        url = "jdbc:mysql://localhost/db_misiones";
        usuario = "root";
        clave = "";
        return getConexion(url, usuario, clave);
    }

    private static Connection getConexion(String url, String usuario, String clave) {
        try {
            conexion = DriverManager.getConnection(url, usuario, clave);
            if (conexion != null) {
                DatabaseMetaData meta = conexion.getMetaData();
                System.out.println("Base Datos Conectada " + meta.getDatabaseProductName());
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error: " + e.getMessage());
        }
        return conexion;
    }

}
