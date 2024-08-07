package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.objetos.Perfil;
import config.Conexion;
import java.sql.ResultSet;
import modelo.objetos.Usuario;
import com.google.gson.JsonObject;

/**
 * Data Access Object (DAO) para operaciones relacionadas con perfiles. Esta
 * clase proporciona métodos para registrar, actualizar, obtener y buscar
 * perfiles en la base de datos.
 */
public class PerfilDAO extends Conexion {

    // Metodo para crear un nuevo perfil asociado con el usuario
    public boolean registroPerfil(Perfil profile, int id_user) throws SQLException {
        // Variable para indicar si la inserción fue exitosa.
        boolean insert = false; 
        
        // Variable para preparar la consulta SQL.
        PreparedStatement ps = null; 
        
        try {
            // Metodo para conectar la base de datos
            this.conectar(); 
            // Consulta SQL para insertar un nuevo perfil en la base de datos.
            String sql = "INSERT INTO tb_perfil(nombre_usuario, apellido_usuario, num_documento, centro_formacion, id_usuario_fk) VALUES (?,?,?,?,?)";
            
            // Preparar la consulta SQL
            ps = getCon().prepareStatement(sql);
            
            // Establecer los valores para la consulta en sus debidas columnas.
            ps.setString(1, profile.getNombre_usuario());
            ps.setString(2, profile.getApellido_usuario());
            ps.setString(3, profile.getNum_documento());
            ps.setString(4, profile.getCentro_formacion());
            ps.setInt(5, id_user);
            
            // Ejecutar la consulta y verificar si se insertó un registro.
            if (ps.executeUpdate() == 1) {
                // La inserción fue exitosa.
                insert = true; 
            }
        } catch (SQLException e) {
            // Depuracion si fallo creando el perfil
            System.out.println("Error creando el perfil: " + e.getMessage());
        } finally {
            // Metodo para desconectar la base de datos
            this.desconectar(); 
        }
        // Retornar true o false dependiendo del éxito de la inserción.
        return insert; 
    }

    // Metodo para obtener los datos del perfil del usuario asociado
    public Perfil dataPerfil(Usuario user) {
        Perfil profile = null; // Inicialmente, no se encuentra el perfil.
        PreparedStatement ps = null; // Variable para preparar la consulta SQL.
        ResultSet rs = null; // Variable para manejar los resultados de la consulta.
        try {
            this.conectar(); // Conectar a la base de datos.
            // Consulta SQL para obtener los datos del perfil basado en el correo electrónico del usuario.
            String sql = "SELECT id_perfil, nombre_usuario, apellido_usuario, num_documento, centro_formacion FROM tb_perfil JOIN tb_usuarios on tb_usuarios.id_usuario = id_usuario_fk WHERE tb_usuarios.correo_inst = ?";
            ps = getCon().prepareStatement(sql); // Preparar la consulta SQL.
            ps.setString(1, user.getCorreoInst()); // Establecer el valor del correo electrónico en la consulta.
            rs = ps.executeQuery(); // Ejecutar la consulta SQL.
            // Verificar si se encontraron datos del perfil.
            if (rs.next()) {
                profile = new Perfil(); // Crear una nueva instancia de Perfil.
                int id_perfil = rs.getInt("id_perfil"); // Obtener el ID del perfil.
                // Obtener los datos del perfil y establecerlos en el objeto Perfil.
                String nombre = rs.getString("nombre_usuario");
                String apellido = rs.getString("apellido_usuario");
                String numero = rs.getString("num_documento");
                String centro = rs.getString("centro_formacion");

                // Se setean al nuevo objeto Perfil, sus atributos
                profile.setId_perfil(id_perfil);
                profile.setNombre_usuario(nombre);
                profile.setApellido_usuario(apellido);
                profile.setNum_documento(numero);
                profile.setCentro_formacion(centro);
                profile.setId_usuario_fk(user);
                
                // Depuracion de la consulta
                System.out.println("EN EL PERFIL DAO SE OBTUVIERON LOS DATOS DEL PERFIL");
            }
        } catch (Exception e) {
            // Depuracion por si se obtuvo un error al obtener los datos del perfil
            System.out.println("Error al obtener los datos en perfilDao: " + e.getMessage());
        } finally {
            // Metodo para desconectar la base de datos
            this.desconectar(); 
        }
        return profile; // Retornar el objeto Perfil con los datos obtenidos.
    }

    // Metodo para actualizar los datos del perfil de dicho usuario
    public boolean actualizarPerfil(Perfil p) {
        boolean estado = false; // Variable para indicar si la actualización fue exitosa.
        PreparedStatement ps = null; // Variable para preparar la consulta SQL.
        try {
            this.conectar(); // Conectar a la base de datos.
            // Consulta SQL para actualizar los datos del perfil.
            String sql = "UPDATE tb_perfil SET nombre_usuario = ?, apellido_usuario = ?, num_documento = ?, centro_formacion = ? WHERE id_perfil = ?";
            
            // Preparar la consulta SQL
            ps = getCon().prepareStatement(sql);
            
            // Establecer los valores para la consulta.
            ps.setString(1, p.getNombre_usuario());
            ps.setString(2, p.getApellido_usuario());
            ps.setString(3, p.getNum_documento());
            ps.setString(4, p.getCentro_formacion());
            ps.setInt(5, p.getId_perfil());
            
            int modificado = ps.executeUpdate(); // Ejecutar la consulta y obtener el número de filas afectadas.
            // Verificar si se actualizó al menos un registro.
            if (modificado > 0) {
                System.out.println("EL USUARIO CON ID: " + p.getId_perfil() + " HA SIDO MODIFICADO");
                estado = true; // La actualización fue exitosa.
                return estado; // Retornar true si la actualización fue exitosa.
            }
        } catch (Exception e) {
            System.out.println("Error actualizando el perfil: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconectar de la base de datos.
        }
        return estado; // Retornar false si la actualización no fue exitosa.
    }

    // Metodo para buscar el aprendiz por numero de documento, para asignarle el rol monitor
    public JsonObject buscarAprendiz(String numDocumento) {
        JsonObject informacionAprendiz = new JsonObject(); // Crear un nuevo objeto JSON para la información del aprendiz.
        PreparedStatement ps = null; // Variable para preparar la consulta SQL.
        ResultSet rs = null; // Variable para manejar los resultados de la consulta.
        try {
            this.conectar(); // Conectar a la base de datos.
            // Consulta SQL para obtener los datos del perfil y usuario basado en el número de documento.
            String sql = "SELECT tb_perfil.nombre_usuario, tb_perfil.apellido_usuario, tb_perfil.num_documento, tb_perfil.centro_formacion, tb_usuarios.id_usuario FROM tb_perfil JOIN tb_usuarios ON tb_perfil.id_usuario_fk = tb_usuarios.id_usuario WHERE tb_perfil.num_documento = ? AND tb_usuarios.id_rol_fk = 1;";
            ps = getCon().prepareStatement(sql); // Preparar la consulta SQL.
            ps.setString(1, numDocumento); // Establecer el número de documento en la consulta.
            rs = ps.executeQuery(); // Ejecutar la consulta SQL.
            // Verificar si se encontraron datos del aprendiz.
            if (rs.next()) {
                // Agregar las propiedades al JsonObject con la información del aprendiz.
                informacionAprendiz.addProperty("details", "Nombre: " + rs.getString("nombre_usuario")
                        + "<br>" + "Numero de documento: " + rs.getString("num_documento"));
                informacionAprendiz.addProperty("userId", rs.getInt("id_usuario"));
            } else {
                System.out.println("NO SE ENCONTRO NINGUN USUARIO CON EL NUMERO DE DOCUMENTO: " + numDocumento);
            }
        } catch (Exception e) {
            System.out.println("Error buscando el aprendiz");
        } finally {
            this.desconectar(); // Desconectar de la base de datos.
        }
        return informacionAprendiz; // Retornar el JsonObject con la información del aprendiz.
    }
}
