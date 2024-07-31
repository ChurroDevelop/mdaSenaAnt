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
            ps = getCon().prepareStatement(sql); // Preparar la consulta sql
            // Setear los parametros para registrar el perfil
            ps.setString(1, profile.getNombre_usuario());
            ps.setString(2, profile.getApellido_usuario());
            ps.setString(3, profile.getNum_documento());
            ps.setString(4, profile.getCentro_formacion());
            ps.setInt(5, id_user);
            // Si se realizo la inserccion devuelve tru
            if (ps.executeUpdate() == 1) {
                insert = true;
            }
        } catch (SQLException e) {
            System.out.println("Error creando el perfil: " + e.getMessage());
        } finally {
            // Metodo para desconectar
            this.desconectar();
        }
        return insert;
    }
    
    // Metodo para obtener los datos del perfil
    public Perfil dataPerfil(Usuario user){
        Perfil profile = null; // Perfil nullo para luego instanciarlo al momento de obtener los datos de perfil
        PreparedStatement ps = null; // Variable para preparar la consulta
        ResultSet rs = null; // Variable para obtener los datos de dicho perfil
        try {
            // Metodo para conectar a la base de datos
            this.conectar();
            String sql = "SELECT id_perfil, nombre_usuario, apellido_usuario, num_documento, centro_formacion FROM tb_perfil JOIN  tb_usuarios on tb_usuarios.id_usuario = id_usuario_fk WHERE tb_usuarios.correo_inst = ?"; // Preparar consulta sql para obtner los datos
            ps = getCon().prepareStatement(sql); // Preparar consulta sql para ejecutarla
            ps.setString(1, user.getCorreoInst()); // Setear el parametro a la consulta
            rs = ps.executeQuery(); // Ejecutar la consulta SQL
            
            // Si se realizo la consulta
            if (rs.next()) {
                profile = new Perfil(); // Instancia el nuevo perfil
                int id_perfil = rs.getInt("id_perfil"); // Convierte el id en entero
                // Obtener los datos en varibales para despues setearlas en el objeto
                String nombre = rs.getString("nombre_usuario");
                String apellido = rs.getString("apellido_usuario");
                String numero = rs.getString("num_documento");
                String centro = rs.getString("centro_formacion");
                
                // Setear los datos al objeto
                profile.setId_perfil(id_perfil);
                profile.setNombre_usuario(nombre);
                profile.setApellido_usuario(apellido);
                profile.setNum_documento(numero);
                profile.setCentro_formacion(centro);
                profile.setId_usuario_fk(user);
                System.out.println("EN EL PERFIL DAO SE OBTUVIERON LOS DATOS DEL PERFIL");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los datos en perfilDao: " + e.getMessage());
        }
        finally {
            // Metodo para desconectar la base de datos
            this.desconectar();
        }
        return profile;
    }
    
    // Metodo para actualizar los datos del perfil que recibe un objeto perfil
    public boolean actualizarPerfil(Perfil p){
        boolean estado = false; // Retornara un estado true o false dependiendo
        PreparedStatement ps = null; // Variable para preparar la consulta
        try {
            this.conectar(); // Metodo para conectar a la base de datos
            String sql = "UPDATE tb_perfil SET nombre_usuario = ?, apellido_usuario = ?, num_documento = ?, centro_formacion = ? WHERE id_perfil = ?"; // Consulta SQL para actualizar los datos del perfil
            ps = getCon().prepareStatement(sql); // Conectar y preparar consulta para ejecutarla
            
            // Setear los parametros para la actualizacion 
            ps.setString(1, p.getNombre_usuario());
            ps.setString(2, p.getApellido_usuario());
            ps.setString(3, p.getNum_documento());
            ps.setString(4, p.getCentro_formacion());
            ps.setInt(5, p.getId_perfil());
            int modificado = ps.executeUpdate(); // ExecutaUpdate, devuelve un entero es decir casi igual true o false
            // Si el usuario fue modificado
            if (modificado > 0) {
                System.out.println("EL USUARIO CON ID: " + p.getId_perfil() + " HA SIDO MODIFICADO");
                estado = true; // Cambia el estado a true
                return estado; // Retorna el estado
            }
        } catch (Exception e) {
            System.out.println("Error actualizando el perfil: " + e.getMessage());
        } finally {
            // Metodo para desconectar con la base de datos
            this.desconectar();
        }
        return estado;
    }
    
    // Metodo para buscar el aprendiz dependiendo del numero de documento
    public JsonObject buscarAprendiz(String numDocumento){
     JsonObject informacionAprendiz = new JsonObject(); // Variable de JSONObjetc para obtener los datos del usuario como un objeto
     PreparedStatement ps = null; // Variable para preparar las consultas SQL
     ResultSet rs = null; // Varibale para obtener los datos que devuelve la base de datos
        try {
            this.conectar(); // Metodo para conectar a la base de datos
            String sql = "SELECT tb_perfil.nombre_usuario, tb_perfil.apellido_usuario, tb_perfil.num_documento, tb_perfil.centro_formacion, tb_usuarios.id_usuario FROM tb_perfil JOIN tb_usuarios ON tb_perfil.id_usuario_fk = tb_usuarios.id_usuario WHERE tb_perfil.num_documento = ? AND tb_usuarios.id_rol_fk = 1;"; // Consulta para obtener los usuarios que tienen el rol de aprendiz para poder otorgarles la asignacion
            ps = getCon().prepareStatement(sql); // Preparar la consulta sql
            ps.setString(1, numDocumento); // Setear el numero de documento entrante
            rs = ps.executeQuery(); // Ejecutar consulta en la base de datos
            // Si existe alguien con este numero de documento
            if (rs.next()) {
                // Se agregan propiedades al JSONObject
                informacionAprendiz.addProperty("details", "Nombre: " + rs.getString("nombre_usuario")
                + "<br>" + "Numero de documento: " + rs.getString("num_documento"));
                informacionAprendiz.addProperty("userId", rs.getInt("id_usuario"));
            }
            else {
                System.out.println("NO SE ENCONTRO NINGUN USUARIO CON EL NUMERO DE DOCUMENTO: " + numDocumento);
            }
        } catch (Exception e) {
            System.out.println("Error buscando el aprendiz");
        } finally {
            // Metodo para desconectar la base de datos
            this.desconectar();
        }
     return informacionAprendiz;
    }
}
