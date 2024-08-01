package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.objetos.Rol;
import modelo.objetos.Usuario;

/**
 * Data Access Object (DAO) para operaciones relacionadas con roles.
 * Esta clase proporciona un método para obtener el rol de un usuario basado en su correo institucional.
 */
public class RolDAO extends Conexion {

    /**
     * Obtiene el ID y el nombre del rol de un usuario basado en su correo institucional.
     *
     * @param user El objeto Usuario que contiene el correo institucional para buscar el rol.
     * @return El objeto Rol con el ID y nombre del rol, o null si no se encuentra.
     */
    public Rol getIdRol(Usuario user) {
        Rol rol = null; // Inicialmente, no se encuentra el rol.
        PreparedStatement ps = null; // Variable para preparar la consulta SQL.
        ResultSet rs = null; // Variable para manejar los resultados de la consulta.
        try {
            this.conectar(); // Conectar a la base de datos.
            // Consulta SQL para obtener el ID y nombre del rol basado en el correo institucional del usuario.
            String sql = "SELECT id_rol, nombre_rol FROM tb_rol JOIN tb_usuarios ON tb_usuarios.id_rol_fk = id_rol WHERE tb_usuarios.correo_inst = ?";
            ps = getCon().prepareStatement(sql); // Preparar la consulta SQL.
            ps.setString(1, user.getCorreoInst()); // Establecer el valor del correo electrónico en la consulta.
            rs = ps.executeQuery(); // Ejecutar la consulta SQL.
            // Verificar si se encontraron resultados.
            if (rs.next()) {
                rol = new Rol(); // Crear una nueva instancia de Rol.
                int id = rs.getInt("id_rol"); // Obtener el ID del rol.
                String nombre = rs.getString("nombre_rol"); // Obtener el nombre del rol.
                // Establecer los valores en el objeto Rol.
                rol.setId_rol(id);
                rol.setNombre_rol(nombre);
            }
        } catch (Exception e) {
            System.out.println("Error en el metodo Dao del perfil: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconectar de la base de datos.
        }
        return rol; // Retornar el objeto Rol con los datos obtenidos.
    }
}
