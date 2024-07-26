    package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.objetos.Perfil;
import config.Conexion;
import java.sql.ResultSet;
import modelo.objetos.Usuario;
import com.google.gson.JsonObject;

public class PerfilDAO extends Conexion{
    // Metodo para realizar el nuevo registro del perfil con el usuario asociado
    public boolean registroPerfil(Perfil profile, int id_user) throws SQLException{
        boolean insert = false; // Manejo del estado para saber si se registro el perfil o no
        PreparedStatement ps = null; // Variable para preparar las consultas
        try {
            this.conectar(); // Metodo para conectar a la base de datos
            String sql = "INSERT INTO tb_perfil(nombre_usuario, apellido_usuario, num_documento, centro_formacion, id_usuario_fk) VALUES (?,?,?,?,?)"; // Insert into del nuevo perfil asociado al usuario
            ps = getCon().prepareStatement(sql);
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
            this.desconectar();
        }
        return insert;
    }
    
    // Metodo para obtener los datos del perfil
    public Perfil dataPerfil(Usuario user){
        Perfil profile = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "SELECT id_perfil, nombre_usuario, apellido_usuario, num_documento, centro_formacion FROM tb_perfil JOIN  tb_usuarios on tb_usuarios.id_usuario = id_usuario_fk WHERE tb_usuarios.correo_inst = ?";
            ps = getCon().prepareStatement(sql);
            ps.setString(1, user.getCorreoInst());
            rs = ps.executeQuery();
            if (rs.next()) {
                profile = new Perfil();
                int id_perfil = rs.getInt("id_perfil");
                System.out.println(user.getCorreoInst() + " Correo");
                String nombre = rs.getString("nombre_usuario");
                String apellido = rs.getString("apellido_usuario");
                String numero = rs.getString("num_documento");
                String centro = rs.getString("centro_formacion");
                profile.setId_perfil(id_perfil);
                profile.setNombre_usuario(nombre);
                profile.setApellido_usuario(apellido);
                profile.setNum_documento(numero);
                profile.setCentro_formacion(centro);
                profile.setId_usuario_fk(user);
                System.out.println("Se obtuvieron los datos del perfil asociado con el usuario " + user.getCorreoInst());
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los datos en perfilDao: " + e.getMessage());
        }
        finally {
            this.desconectar();
        }
        return profile;
    }
    
    public boolean actualizarPerfil(Perfil p){
        boolean estado= false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            this.conectar();
            System.out.println("Preparando consulta");
            String sql = "UPDATE tb_perfil SET nombre_usuario = ?, apellido_usuario = ?, num_documento = ?, centro_formacion = ? WHERE id_perfil = ?";
            ps = getCon().prepareStatement(sql);
            ps.setString(1, p.getNombre_usuario());
            ps.setString(2, p.getApellido_usuario());
            ps.setString(3, p.getNum_documento());
            ps.setString(4, p.getCentro_formacion());
            ps.setInt(5, p.getId_perfil());
            System.out.println(p.getId_perfil());
            int modificado = ps.executeUpdate();
            if (modificado > 0) {
                System.out.println("Usuario modificado");
                estado = true;
                return estado;
            }
        } catch (Exception e) {
            System.out.println("Error actualizando el perfil: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return estado;
    }
    
    public JsonObject buscarAprendiz(String numDocumento){
     JsonObject informacionAprendiz = new JsonObject();
     PreparedStatement ps = null;
     ResultSet rs = null;
        try {
            this.conectar();
            String sql = "SELECT tb_perfil.nombre_usuario, tb_perfil.apellido_usuario, tb_perfil.num_documento, tb_perfil.centro_formacion, tb_usuarios.id_usuario FROM tb_perfil JOIN tb_usuarios ON tb_perfil.id_usuario_fk = tb_usuarios.id_usuario WHERE tb_perfil.num_documento = ? AND tb_usuarios.id_rol_fk = 1;";
            ps = getCon().prepareStatement(sql);
            ps.setString(1, numDocumento);
            rs = ps.executeQuery();
            System.out.println("Numero de documento dentro del perfilDao: " + numDocumento);
            if (rs.next()) {
                informacionAprendiz.addProperty("details", "Nombre: " + rs.getString("nombre_usuario")
                + "<br>" + "Numero de documento: " + rs.getString("num_documento"));
                informacionAprendiz.addProperty("userId", rs.getInt("id_usuario"));
            }
            else {
                System.out.println("No se pudo agregar ni chimba");
            }
        } catch (Exception e) {
            System.out.println("Error buscando el aprendiz");
        } finally {
            this.desconectar();
        }
     return informacionAprendiz;
    }
}
