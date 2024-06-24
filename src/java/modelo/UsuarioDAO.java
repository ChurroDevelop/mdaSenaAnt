/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import modelo.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.PasswordEncryptionUtil;

/**
 *
 * @author Propietario
 */
public class UsuarioDAO extends Conexion{
    
    public Usuario identificar(Usuario user) throws Exception{
        Usuario usu = null;
        ResultSet rs;
        String sql = "SELECT id_usuario, tb_rol.nombre_rol FROM tb_usuarios " +
                    "INNER JOIN tb_rol on tb_rol.id_rol = tb_usuarios.id_rol_fk " +
                    "WHERE correo_inst = '" +  user.getCorreoInstitucional() + "' " +
                    "AND " +
                    "contrasena = '" + user.getContrasena() +"'";
        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            if (rs.next() == true) {
                usu = new Usuario();
                usu.setId_usuario((rs.getInt("id_usuario")));
                usu.setCorreoInstitucional(usu.getCorreoInstitucional());
                usu.setId_rol_fk(new Rol());
                usu.getId_rol_fk().setNombre_rol(rs.getString("nombre_rol"));
            }
            else{
                System.out.println("No ejecuta el if");
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error 2: " + e.getMessage());
        }finally{
            this.cerrar(false);
        }
        return usu;
    }
    
    public void registro(Usuario user) throws Exception{
         String sql;
        sql = "INSERT INTO tb_usuarios(correo_inst, password, id_rol_fk) VALUES ('"+ user.getCorreoInstitucional() + "', '"+ user.getContrasena() +"', '"+ user.getId_rol_fk().getId_rol() +"')";
        try {
            this.conectar(false);
            this.ejecutarOrder(sql);
            this.cerrar(true);
            System.out.println("Se ejecuto correctamente");
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }
    
    public boolean registroUsuario(String correo, String password, int rol) throws Exception{
        
//        PreparedStatement ps = null;
//        
//        try {
//            String encriptPassword = PasswordEncryptionUtil.encriptar(password);
//            System.out.println(encriptPassword + ": Contraseña encriptada");
//            String sql = "INSERT INTO tb_usuarios(correo_inst, password, id_rol_fk) VALUES (?,?,?)";
//            System.out.println(encriptPassword.length());
//            ps = getConexion().prepareStatement(sql);
//            
//            ps.setString(1, correo);
//            ps.setString(2, encriptPassword);
//            ps.setInt(3, rol);
//            
//            if (ps.executeUpdate() == 1) {
//               return true;
//            }
//            
//        } catch (Exception e) {
//            System.out.println("ERRORES ANTES DE CERRAR: " + e.getMessage());
//        }
//        finally {
//            if (getConexion() != null) {
//                try {
//                    getConexion().close();
//                    if (ps != null) {
//                        ps.close();
//                        System.out.println("CERRANDO LAS CONEXIONES");
//                    }
//                } catch (Exception ex) {
//                    System.out.println("ERRORES: " + ex.getMessage());
//                }
//            } else {
//            }
//        }
        return false;
    }
}
