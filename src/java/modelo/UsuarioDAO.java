/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import config.Conexion;
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
            
            
            
            // Apartado para encriptar las contraseñas
//            String encriptada = PasswordEncryptionUtil.encriptar(user.getContrasena());
//            System.out.println("Contraseña encripatada " + encriptada);
            // ****************************************************
            
            
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
}
