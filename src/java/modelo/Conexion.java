package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String user = "root";
    private String password = "";
    private String host = "localhost";
    private String port = "3306";
    private String database = "mdasena_db";
    private String className = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://"+host+":"+port+"/"+database;
    private Connection con;
    
    public Conexion(){
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado correctamente");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR 1: " + e);
        } catch (SQLException ex){
            System.out.println("ERROR 2: " + ex);
        }
    }
    
    public Connection getConexion(){
        return con;
    }
}