package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private final String host = "jdbc:mysql://localhost:3306/db_mda_sena";
    private final String user = "root";
    private final String password = "";
    
    public Connection conectar(){
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(host, user, password);
            System.out.println("Conecto a la base de datos 1");
        } catch (Exception e) { 
            System.out.println("Error al conectar con la base de datos 1: " + e.getMessage());
        }
        return con;
    }
    
}