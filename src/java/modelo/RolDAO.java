package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.objetos.Rol;
import modelo.objetos.Usuario;

public class RolDAO extends Conexion{
    // Metodo para obtener el id y el nombre del rol
    public Rol getIdRol(Usuario user) {
        Rol rol = null; // Instancia null del nuevo rol que va a retornar la funcion
        PreparedStatement ps = null; // Variable para preparar la consulta
        ResultSet rs = null; // Variable para obtener el resultado de la consulta a la Base de datos
        try {
            this.conectar(); // Metodo para conectar a la base de datos
            String sql = "SELECT id_rol, nombre_rol FROM tb_rol JOIN tb_usuarios ON tb_usuarios.id_rol_fk = id_rol WHERE tb_usuarios.correo_inst = ? "; // Consulta sql para obtener el id y el nombre del rol de acuerdo al correo institucional del usuario
            ps = getCon().prepareStatement(sql); // Preparar consulta sql, lista para ejecutar
            ps.setString(1, user.getCorreoInst()); // Se setea en la primera columna el correo institucional del usuario
            rs = ps.executeQuery(); // Ejecutar consulta SQL
            if (rs.next()) { // Si dio algun resultado la consulta
                rol = new Rol(); // Se instancia el nuevo rol
                int id = rs.getInt("id_rol"); // Se obtiene el id del rol como entero
                String nombre = rs.getString("nombre_rol"); // Se toma el nombre del rol
                // Se setean sus propiedades al objeto que retornara la funcion
                rol.setId_rol(id);
                rol.setNombre_rol(nombre);
            }
        } catch (Exception e) {
            System.out.println("Error en el metodo Dao del perfil: " + e.getMessage());
        }
        finally {
            this.desconectar(); // Metodo para desconectar la base de datos
        }
        return rol;
    }
}
