/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Propietario
 */
public class UsuarioDAO extends Conexion{
    
    public Usuario identificar(Usuario user) throws Exception{
        Usuario usu = new Usuario();
        Conexion con = new Conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        
        
        String sql = "SELECT id_usuario, tb_rol.nombre_rol FROM tb_usuarios " +
                    "INNER JOIN tb_rol on tb_rol.id_rol = tb_usuarios.id_rol_fk " +
                    "WHERE correo_inst = '" +  user.getCorreoInstitucional() + "' " +
                    "AND " +
                    "contraseña = '" + user.getContraseña() +"'";
        
        
        try {
            cn = con.conectar();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next() == true) {
                usu = new Usuario();
                usu.setId_usuario((rs.getInt("id_usuario")));
                usu.setCorreoInstitucional(user.getCorreoInstitucional());
                usu.setId_rol_fk(new Rol());
                usu.getId_rol_fk().setNombre_rol(rs.getString("nombre_rol"));
            }
            else{
                System.out.println("No ejecuta el if");
            }
        } catch (Exception e) {
            System.out.println("Error 2: " + e.getMessage());
        }
        finally{
            if (rs != null && rs.isClosed() == false) {
                rs.close();
            }
            
            rs = null;
            if (st != null && st.isClosed() == false) {
                st.close();
            }
            
            st = null;
            if (con != null && cn.isClosed() == false) {
                cn.close();
            }
            
            cn = null;
        }
        return usu;
    }
}
