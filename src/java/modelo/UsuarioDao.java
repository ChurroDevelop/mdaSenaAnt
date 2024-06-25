package modelo;

import modelo.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.objetos.Usuario;

public class UsuarioDAO extends Conexion{
    
    public boolean registrarUsuario(Usuario user, int id_rol) throws SQLException{
        boolean insertado = false;
        PreparedStatement ps = null;
        PreparedStatement psPerfil = null;
        try {
            String sqlUser = "INSERT INTO tb_usuarios(correo_inst, password, id_rol_fk) VALUES (?,?,?)";
            ps = getConexion().prepareStatement(sqlUser, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getCorreoInst());
            ps.setString(2, user.getPassword());
            ps.setInt(3, id_rol);
            
            int rows = ps.executeUpdate();
            
            if (rows == 1) {
                insertado = true;
            }
            
            System.out.println("Usuario creado");
            
        } catch (SQLException e) {
            System.out.println("Ola");
        } finally{
            if (ps != null) {
                ps.close();
            }
            if (psPerfil != null) {
                psPerfil.close();
            }
            if (getConexion() != null) {
                getConexion().close();
            }
        }
        return insertado;
    }

    public boolean autenticacion(Usuario user) {
        boolean accion = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            System.out.println(user.getPassword());
            String sql = "SELECT * FROM tb_usuarios WHERE correo_inst = ? and password = ?";
            ps = getConexion().prepareStatement(sql);
            ps.setString(1, user.getCorreoInst());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            if (rs.absolute(1)) {
                accion = true;
            }
        } catch (Exception e) {
            System.out.println("ERROR AL LOGIN: " + e.getMessage());
        } finally {
            try {
                if (getConexion() != null && ps != null && rs != null) {
                    getConexion().close();
                    ps.close();
                    rs.close();
                    System.out.println("CONEXIONES DEL LOGIN CERRADAS");
                }
            } catch (Exception ex) {
                System.out.println("ERROR CERRANDO LAS CONEXIONES DEL LOGIN: " + ex.getMessage());
            }
        }
        
        return accion;
    }
    
    public int obtenerId(Usuario user){
        int idUser = 0;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT id_usuario FROM tb_usuarios WHERE correo_inst = ?";
            ps = getConexion().prepareStatement(sql);
            ps.setString(1, user.getCorreoInst());
            rs = ps.executeQuery();
            if (rs.next()) {
                idUser = rs.getInt("id_usuario");
                System.out.println("Se obtuvo el usuario");
            }
            
        } catch (Exception e) {
            System.out.println("ERROR EN LA CONSULTA: " + e.getMessage());
        } finally {
            try {
                if (getConexion() != null && ps != null && rs != null) {
                    getConexion().close();
                    ps.close();
                    rs.close();
                    System.out.println("CONEXIONES DEL OBTENER ID CERRADAS");
                }
            } catch (Exception ex) {
                System.out.println("ERROR CERRANDO LAS CONEXIONES DEL OBTENER ID: " + ex.getMessage());
            }
        }
        return idUser;
    }
}
