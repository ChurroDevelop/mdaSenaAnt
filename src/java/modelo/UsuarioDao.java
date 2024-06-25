package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDao extends Conexion{
    
    public boolean autenticacion(String correo, String pass){
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            // Se hace de esta manera para evitar SQL Inyection
            String encript = PasswordEncryptionUtil.encriptar(pass);
            String sql = "SELECT * FROM tb_usuarios WHERE correo_inst = ? and password = ?";
            ps = getConexion().prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, encript);
            
            rs = ps.executeQuery();
            
            if (rs.absolute(1)) {
                return true;
            }
            
        } catch (Exception e) {
            System.out.println("ERROR 2: " + e.getMessage());
        } finally{
            try {
                if (getConexion() != null) {
                    getConexion().close();
                    if (ps != null && rs != null) {
                        ps.close();
                        rs.close();
                        System.out.println("SE CERRARON LAS CONEXIONES PARA EL LOGIN");
                    }
                }
            } catch (Exception ex) {
                System.out.println("ERROR 3: " + ex.getMessage());
            }
        }
        
        
        return false;
    }
    
    public boolean registrarUsuario(String correo, String pass, int rol){
        
        PreparedStatement ps = null;
        
        try {
            String encript = PasswordEncryptionUtil.encriptar(pass);

            String sql = "INSERT INTO tb_usuarios(correo_inst, password, id_rol_fk) VALUES (?,?,?)";
            ps = getConexion().prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, encript);
            ps.setInt(3, rol);
            ps.executeUpdate();
            System.out.println("Se creo el usuario");
        } catch (Exception e) {
            System.out.println("ERROR 4: " + e.getMessage());
        } finally {
            try {
                if (getConexion() != null) {
                    getConexion().close();
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (Exception ex) {
                System.out.println("ERROR 5: " + ex.getMessage() );
            }
        }
        
        return false;
    }
    
}
