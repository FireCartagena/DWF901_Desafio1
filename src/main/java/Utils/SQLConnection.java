/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;
import java.sql.*;
/**
 *
 * @author pyload
 */
public class SQLConnection {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/gestion_rh";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "";
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch(Exception e) {
            System.out.println("Error al conectarse a la base de datos " + e.getMessage());
        }
        return conn;
    }
    public static void close(Connection conn) {
        try {
            conn.close();   
        } catch(Exception e) {
            System.out.println("Error al cerrar la conexión");
        }
    }
}
