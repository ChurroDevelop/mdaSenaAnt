package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private Connection con; // Tipo de dato Connection para manejo de la conexion
    
    public Connection getCon(){ // Esto es para obtener la conexion con el PreparedStatement
        return con; // Retorna la conexion
    }
    
    public void setCon(Connection con){
        this.con = con;
    }
    
    public void conectar(){ // Metodo para conectar a la base de datos
        try { // Manejo de excepciones necesarias para la base de datos
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver de mysql para la conexion a la base de datos
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mda_sena", "root", ""); // a la variabel public con, se le asigna la conexion a la base de datos que tendra la url, el usuario y la contraseña
            System.out.println("Conectada");
        } catch (Exception e) {
            System.out.println("Error conectando"); // Error por si no conecta a la base de datos
        }
    }
    
    public void desconectar(){ // Metodo para desconectar la base de datos
        try { // Manejo de excepciones siempre necesarias
            if (!con.isClosed()) { // Condicion si la conexion esta abierta, la cierra
                con.close(); // cierra la conexion
            }
        } catch (Exception e) {
            System.out.println("Error desconectando"); // Error por si no desconecta a la base de datos
        }
    }
}