package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.objetos.Rol;
import modelo.objetos.Usuario;

public class RolDAO extends Conexion{
    // Metodo para obtener el id del rol
    public Rol getIdRol(Usuario user) {
        Rol rol = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "SELECT id_rol, nombre_rol FROM tb_rol JOIN tb_usuarios ON tb_usuarios.id_rol_fk = id_rol WHERE tb_usuarios.correo_inst = ? ";
            ps = getCon().prepareStatement(sql);
            ps.setString(1, user.getCorreoInst());
            rs = ps.executeQuery();
            if (rs.next()) {
                rol = new Rol();
                int id = rs.getInt("id_rol");
                String nombre = rs.getString("nombre_rol");
                rol.setId_rol(id);
                rol.setNombre_rol(nombre);
            }
        } catch (Exception e) {
            System.out.println("Error en el metodo Dao del perfil: " + e.getMessage());
        }
        finally {
            this.desconectar();
        }
        return rol;
    }
}
