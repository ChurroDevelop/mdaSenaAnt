package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.objetos.Perfil;
import config.Conexion;
import java.sql.ResultSet;
import modelo.objetos.Usuario;
import com.google.gson.JsonObject;

/**
 * Data Access Object (DAO) para operaciones relacionadas con perfiles. 
 * Esta clase proporciona métodos para registrar, actualizar, obtener y buscar
 * perfiles en la base de datos.
 */
public class PerfilDAO extends Conexion {

    /**
     * Registra un nuevo perfil asociado con el usuario.
     * 
     * @param profile El objeto Perfil con los datos a registrar.
     * @param id_user El ID del usuario al que se asociará el perfil.
     * @return true si la inserción fue exitosa, false en caso contrario.
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL.
     */
    public boolean registroPerfil(Perfil profile, int id_user) throws SQLException {
        boolean insert = false; // Variable para indicar si la inserción fue exitosa.
        PreparedStatement ps = null; // Variable para preparar la consulta SQL.
        
        try {
            this.conectar(); // Conecta a la base de datos.
            
            // Consulta SQL para insertar un nuevo perfil en la base de datos.
            String sql = "INSERT INTO tb_perfil(nombre_usuario, apellido_usuario, num_documento, centro_formacion, id_usuario_fk) VALUES (?,?,?,?,?)";
            ps = getCon().prepareStatement(sql); // Prepara la consulta SQL.
            
            // Establecer los valores para la consulta en sus debidas columnas.
            ps.setString(1, profile.getNombre_usuario());
            ps.setString(2, profile.getApellido_usuario());
            ps.setString(3, profile.getNum_documento());
            ps.setString(4, profile.getCentro_formacion());
            ps.setInt(5, id_user);
            
            // Ejecutar la consulta y verificar si se insertó un registro.
            if (ps.executeUpdate() == 1) {
                insert = true; // La inserción fue exitosa.
            }
        } catch (SQLException e) {
            // Depuración si falla la creación del perfil.
            System.out.println("Error creando el perfil: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconecta de la base de datos.
        }
        return insert; // Retorna true o false dependiendo del éxito de la inserción.
    }

    /**
     * Obtiene los datos del perfil del usuario asociado.
     * 
     * @param user El objeto Usuario cuyo perfil se desea obtener.
     * @return El objeto Perfil con los datos obtenidos, o null si no se encuentra.
     */
    public Perfil dataPerfil(Usuario user) {
        Perfil profile = null; // Inicialmente, no se encuentra el perfil.
        PreparedStatement ps = null; // Variable para preparar la consulta SQL.
        ResultSet rs = null; // Variable para manejar los resultados de la consulta.
        
        try {
            this.conectar(); // Conecta a la base de datos.
            
            // Consulta SQL para obtener los datos del perfil basado en el correo electrónico del usuario.
            String sql = "SELECT id_perfil, nombre_usuario, apellido_usuario, num_documento, centro_formacion " +
                         "FROM tb_perfil " +
                         "JOIN tb_usuarios ON tb_usuarios.id_usuario = id_usuario_fk " +
                         "WHERE tb_usuarios.correo_inst = ?";
            ps = getCon().prepareStatement(sql); // Prepara la consulta SQL.
            ps.setString(1, user.getCorreoInst()); // Establece el valor del correo electrónico en la consulta.
            rs = ps.executeQuery(); // Ejecuta la consulta SQL.
            
            // Verifica si se encontraron datos del perfil.
            if (rs.next()) {
                profile = new Perfil(); // Crea una nueva instancia de Perfil.
                // Obtener y establecer los datos del perfil en el objeto Perfil.
                profile.setId_perfil(rs.getInt("id_perfil"));
                profile.setNombre_usuario(rs.getString("nombre_usuario"));
                profile.setApellido_usuario(rs.getString("apellido_usuario"));
                profile.setNum_documento(rs.getString("num_documento"));
                profile.setCentro_formacion(rs.getString("centro_formacion"));
                profile.setId_usuario_fk(user); // Asocia el usuario con el perfil.
                
                // Depuración de la consulta.
                System.out.println("Se obtuvieron los datos del perfil.");
            }
        } catch (Exception e) {
            // Depuración por si ocurre un error al obtener los datos del perfil.
            System.out.println("Error al obtener los datos en PerfilDAO: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconecta de la base de datos.
        }
        return profile; // Retorna el objeto Perfil con los datos obtenidos.
    }

    /**
     * Actualiza los datos del perfil de un usuario.
     * 
     * @param p El objeto Perfil con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarPerfil(Perfil p) {
        boolean estado = false; // Variable para indicar si la actualización fue exitosa.
        PreparedStatement ps = null; // Variable para preparar la consulta SQL.
        
        try {
            this.conectar(); // Conecta a la base de datos.
            
            // Consulta SQL para actualizar los datos del perfil.
            String sql = "UPDATE tb_perfil SET nombre_usuario = ?, apellido_usuario = ?, num_documento = ?, centro_formacion = ? WHERE id_perfil = ?";
            ps = getCon().prepareStatement(sql); // Prepara la consulta SQL.
            
            // Establecer los valores para la consulta.
            ps.setString(1, p.getNombre_usuario());
            ps.setString(2, p.getApellido_usuario());
            ps.setString(3, p.getNum_documento());
            ps.setString(4, p.getCentro_formacion());
            ps.setInt(5, p.getId_perfil());
            
            // Ejecutar la consulta y obtener el número de filas afectadas.
            int modificado = ps.executeUpdate();
            
            // Verificar si se actualizó al menos un registro.
            if (modificado > 0) {
                System.out.println("El usuario con ID: " + p.getId_perfil() + " ha sido modificado.");
                estado = true; // La actualización fue exitosa.
            }
        } catch (Exception e) {
            System.out.println("Error actualizando el perfil: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconecta de la base de datos.
        }
        return estado; // Retorna true si la actualización fue exitosa, false en caso contrario.
    }

    /**
     * Busca un aprendiz por número de documento para asignarle el rol de monitor.
     * 
     * @param numDocumento El número de documento del aprendiz a buscar.
     * @return Un JsonObject con la información del aprendiz.
     */
    public JsonObject buscarAprendiz(String numDocumento) {
        JsonObject informacionAprendiz = new JsonObject(); // Crear un nuevo objeto JSON para la información del aprendiz.
        PreparedStatement ps = null; // Variable para preparar la consulta SQL.
        ResultSet rs = null; // Variable para manejar los resultados de la consulta.
        
        try {
            this.conectar(); // Conecta a la base de datos.
            
            // Consulta SQL para obtener los datos del perfil y usuario basado en el número de documento.
            String sql = "SELECT tb_perfil.nombre_usuario, tb_perfil.apellido_usuario, tb_perfil.num_documento, tb_perfil.centro_formacion, tb_usuarios.id_usuario " +
                         "FROM tb_perfil " +
                         "JOIN tb_usuarios ON tb_perfil.id_usuario_fk = tb_usuarios.id_usuario " +
                         "WHERE tb_perfil.num_documento = ? AND tb_usuarios.id_rol_fk = 1";
            ps = getCon().prepareStatement(sql); // Prepara la consulta SQL.
            ps.setString(1, numDocumento); // Establece el número de documento en la consulta.
            rs = ps.executeQuery(); // Ejecuta la consulta SQL.
            
            // Verificar si se encontraron datos del aprendiz.
            if (rs.next()) {
                // Agregar las propiedades al JsonObject con la información del aprendiz.
                informacionAprendiz.addProperty("details", "Nombre: " + rs.getString("nombre_usuario") +
                        "<br>Numero de documento: " + rs.getString("num_documento"));
                informacionAprendiz.addProperty("userId", rs.getInt("id_usuario"));
            } else {
                System.out.println("No se encontró ningún usuario con el número de documento: " + numDocumento);
            }
        } catch (Exception e) {
            System.out.println("Error buscando el aprendiz: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconecta de la base de datos.
        }
        return informacionAprendiz; // Retorna el JsonObject con la información del aprendiz.
    }
}
