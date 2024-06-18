package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private Connection con; // Atributo privado

    public Connection getCon() { // metodo get del atributo
        return con;
    }

    public void setCon(Connection con) { // metodo set del atributo
        this.con = con;
    }
    
    public void conectar(){ // metodo de la clase para conectar a la DB
        try { // Manejo de excepciones
            Class.forName("com.mysql.jdbc.Driver"); // Tipo de driver en este caso mysql
            con = DriverManager.getConnection("jdbc:mysql://localhost/db_mda_sena", "", "");
            System.out.println("Base de datos conectada correctamente");
        } catch (Exception e) {
            System.out.println("Error al conecta a la db " + e.getMessage()); // Imprime el error a la conexion a la base de datos
        }
    }
    
    public void desconectar(){ // Metodo de la clase para desconectar la DB
        try { // Manejo de excepciones
            if (!con.isClosed()) { // Si la conexion esta abierta
                con.close(); // Cierra la conexion a la base de datos
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexion " + e.getMessage());
        }
    }
    
}