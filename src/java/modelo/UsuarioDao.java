package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.objetos.Usuario;

public class UsuarioDAO extends Conexion{ // Hereda todo de la clase Conexion

    // Metodo publico que retornara true o false, recibira como parametros un objeto usuario y el rol que se esta seteando en el Servlet de Registro
    public boolean registrarUsuario(Usuario user, int id_rol) throws SQLException{
        boolean insertado = false; // Por defecto retornara false
        PreparedStatement ps = null; // PreparedStatement para el manejo de los scripts de SQL
        try { // Manejo de excepciones
            this.conectar(); // Se llama al metodo conectar para que conecte con la base de datos
            /*
            IMPORTANTE:
                Dependiendo la cantidad de columnas a la cual le vamos a agregar los valores, en el values se colocan signos de interrogacion es decir
                En este caso se estan manejando que se van a insertar 3 datos, entonces de colocan 3 signos de interrogacion, si simplemente fuera 1 solo 1 signo de interrogacion
            */
            String sqlUser = "INSERT INTO tb_usuarios(correo_inst, password, id_rol_fk) VALUES (?,?,?)"; // Se prepara en String el script que se va a ejecutar, de acuerdo con la base de datos
            ps = getCon().prepareStatement(sqlUser); // Compila y prepara la consulta como codigo SQL
            ps.setString(1, user.getCorreoInst());  // Se setea la primera columna como String, dependiendo de la base de datos
            ps.setString(2, user.getPassword()); // Se sete la segunda columna como String, dependiendo la base de datos
            ps.setInt(3, id_rol); // Se setea la tercera columna como Int, dependiendo la base de datos
            if (ps.executeUpdate() == 1) { // Si la ejecucion del Script es 1 es decir "True"
                insertado = true; // cambia el valor del insertado a true y es lo que devolvera el metodo
            }
            System.out.println("Usuario creado");
        } catch (SQLException e) {
            System.out.println("Error creando el usuario"); // Manejo del error por si no crea el usuario
        } finally{
            this.desconectar(); // Siempre se debera manejar el metodo desconectar la base de datos
        }
        return insertado; // Returna true o false dependiendo del recorrido que hizo en el metodo
    }

    // Metodo publico que retornara true o false, recibira como parametro un objeto usuario el cual se setea en el servlet del login
    public boolean autenticacion(Usuario user) {
        boolean accion = false; // Variable accion que por defecto sera false
        PreparedStatement ps = null; // Variable para el manejo de los Scripts SQL
        ResultSet rs = null; // Variable para manejar los resultados de las consultas 
        try {
            this.conectar(); // Metodo para conectar con la base de datos
            String sql = "SELECT * FROM tb_usuarios WHERE correo_inst = ? and password = ?"; // Consulta SQL que aceptara el correo institucional y la contraseña entrante
            ps = getCon().prepareStatement(sql); // Manejo para la conexion a la base de datos
            ps.setString(1, user.getCorreoInst()); // La primera coolumna tomara el correo entrante 
            ps.setString(2, user.getPassword()); // La segunda columna tomara la contraseña de manera que ya esta encriptada, la cual la buscara en la base de datos
            rs = ps.executeQuery(); // ejecuta la consulta
            if (rs.absolute(1)) { // Si encuentra el correo institucional y coinciden la contraseña 
                accion = true; // devolvera true
            }
        } catch (Exception e) {
            System.out.println("ERROR AL LOGIN: " + e.getMessage());
        } finally {
            this.desconectar(); // Metodo para desconectar
        }
        return accion; // Retorna true o false dependiendo del recorrido
    }
    
    // Metodo para obtener el id del usuario con el correo que ingrese
    public int obtenerId(String correo) throws SQLException{
        int id = 0; // Variable que por defecto sera 0
        PreparedStatement ps = null; // Prepared statement para el manejo de los Scripts SQL
        ResultSet rs = null; // Result set para poder obtener eel valor de las consultas SQL
        try {
            this.conectar(); // Se conecta a la base de datos
            String sql = "SELECT * FROM tb_usuarios WHERE correo_inst = ?"; // Consulta SQL para poder obtener el id del usuario que ingreso el correo
            ps = getCon().prepareStatement(sql); // Preparar el Script SQL
            ps.setString(1, correo); // en la primera columna se le coloca el correo entrante del metod 
            rs = ps.executeQuery(); // Ejecuta la consulta para poder obtener el id del usuario
            if (rs.next()) { // Si existe algun usuario con ese correo devolvera el id
                id = rs.getInt("id_usuario"); // a la variable id, se le asigna el valor que retorne la consulta
           }
            else{
                System.out.println("No se encuentra el correo");
            }
        } catch (Exception e) {
            System.out.println("Error obteniendo el ID: " + e.getMessage());
        } finally {
            this.desconectar(); // Metodo para desconectar la base de datos
        }
        return id; // Retorna el id
    }
}
