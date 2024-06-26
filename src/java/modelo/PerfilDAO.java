package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.objetos.Perfil;
import config.Conexion;

public class PerfilDAO extends Conexion{
    public boolean registroPerfil(Perfil profile, int id_user) throws SQLException{
        boolean insert = false;
        
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO tb_perfil(nombre_usuario, apellido_usuario, num_documento, centro_formacion, id_usuario_fk) VALUES (?,?,?,?,?)";
            ps = getConexion().prepareStatement(sql);
            ps.setString(1, profile.getNombre_usuario());
            ps.setString(2, profile.getApellido_usuario());
            ps.setString(3, profile.getNum_documento());
            ps.setString(4, profile.getCentro_formacion());
            ps.setInt(5, id_user);
            
            if (ps.executeUpdate() == 1) {
                insert = true;
            }
            
            System.out.println("Se creo el perfil con el id_del usuario: " + id_user);
        } catch (SQLException e) {
            System.out.println("Error creando el perfil: " + e.getMessage());
        } finally {
            if (ps != null && getConexion() != null) {
                ps.close();
                getConexion().close();
            }
        }
        
        return insert;
    }
}
