package config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase que maneja la conexión con la base de datos.
 */
public class Conexion {
    // Atributo que mantiene la conexión a la base de datos.
    private Connection con; 

    /**
     * Método que devuelve la conexión actual a la base de datos.
     * 
     * @return La conexión actual a la base de datos.
     */
    public Connection getCon() {
        return con;
    }

    /**
     * Método que establece la conexión con la base de datos.
     * 
     * @param con La nueva conexión a la base de datos.
     */
    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * Método que realiza la conexión a la base de datos.
     * Intenta cargar el controlador JDBC de MySQL y establecer una conexión.
     * Si la conexión es exitosa, muestra un mensaje de confirmación.
     * En caso contrario, captura y muestra un mensaje de error.
     */
    public void conectar() {
        try {
            // Carga el driver JDBC de MySQL.
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establece la conexión con la base de datos usando la URL, usuario y contraseña.
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mda_sena", "root", "");
            System.out.println("Conectada");
        } catch (Exception e) {
            // Captura cualquier excepción y muestra un mensaje de error.
            System.out.println("Error conectando");
        }
    }

    /**
     * Método que cierra la conexión con la base de datos.
     * Verifica si la conexión está abierta antes de cerrarla.
     * Si ocurre un error durante la desconexión, captura y muestra un mensaje de error.
     */
    public void desconectar() {
        try {
            // Verifica si la conexión está abierta.
            if (!con.isClosed()) {
                // Cierra la conexión.
                con.close();
            }
        } catch (Exception e) {
            // Captura cualquier excepción y muestra un mensaje de error.
            System.out.println("Error desconectando");
        }
    }
}
