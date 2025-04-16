package com.example.restaurantmanagerproject.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {

    // JDBC URL para SQL Server
    private static final String USER = "sa";
    private static final String PASSWORD = "sysadm";
    private static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=RestaurantSystem;" +
            "integratedSecurity=false;encrypt=false;trustServerCertificate=false;" +
            "user=" + USER + ";password=" + PASSWORD;

    // Static block para cargar el driver JDBC
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("SQL Server JDBC driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("SQL Server JDBC Driver not found!");
            e.printStackTrace();
        }
    }

    /**
     * Obtiene una conexión a la base de datos
     * 
     * @return una nueva conexión a la base de datos
     * @throws SQLException si la conexión falla
     */
    public static Connection getConnection() throws SQLException {
        // System.out.println("Attempting to connect with URL: " + JDBC_URL);
        Connection conn = DriverManager.getConnection(JDBC_URL);
        // System.out.println("Connection successful!");
        return conn;
    }

    /**
     * Cierra una conexión de manera silenciosa (sin lanzar excepciones)
     * 
     * @param conn la conexión a cerrar
     */
    public static void closeQuietly(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    public static void closeQuietly(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // Log or handle quietly
            }
        }
    }

    public static void closeQuietly(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // Log or handle quietly
            }
        }
    }

}