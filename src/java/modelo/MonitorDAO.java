package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.objetos.Perfil;
import modelo.objetos.Post;
import modelo.objetos.Usuario;

/**
 * Clase DAO para realizar operaciones relacionadas con los monitores en la base de datos.
 * Hereda de la clase {@link Conexion} para manejar la conexión a la base de datos.
 */
public class MonitorDAO extends Conexion {

    /**
     * Obtiene la lista de perfiles de monitores asignados a un instructor específico.
     * 
     * @param idInstructor El ID del instructor al que se le han asignado los monitores.
     * @return Una lista de perfiles de monitores asignados al instructor.
     */
    public List<Perfil> obtenerMonitores(String idInstructor) {
        List<Perfil> monitores = new ArrayList<>(); // Lista para almacenar los perfiles de los monitores
        PreparedStatement ps = null; // Variable para la sentencia SQL
        ResultSet rs = null; // Variable para el resultado de la consulta SQL
        try {
            this.conectar(); // Conecta a la base de datos

            // Consulta SQL para obtener monitores asignados al instructor con rol de monitor
            String sql = "SELECT * FROM tb_perfil " +
                         "INNER JOIN tb_usuarios ON tb_perfil.id_usuario_fk = tb_usuarios.id_usuario " +
                         "WHERE tb_usuarios.id_rol_fk = 3 AND id_instructor_asig = ?";
            ps = getCon().prepareStatement(sql); // Prepara la sentencia SQL
            ps.setString(1, idInstructor); // Asigna el ID del instructor a la consulta SQL
            rs = ps.executeQuery(); // Ejecuta la consulta SQL

            // Procesa los resultados de la consulta
            while (rs.next()) {
                Perfil perfil = new Perfil(); // Crea una nueva instancia de Perfil

                // Asigna valores a los atributos del perfil desde el ResultSet
                perfil.setId_perfil(rs.getInt("id_perfil"));
                perfil.setNombre_usuario(rs.getString("nombre_usuario"));
                perfil.setApellido_usuario(rs.getString("apellido_usuario"));
                perfil.setCentro_formacion(rs.getString("centro_formacion"));
                perfil.setNum_documento(rs.getString("num_documento"));
                perfil.setId_usuario_fk(new Usuario()); // Se puede mejorar si se requiere información adicional del usuario

                monitores.add(perfil); // Agrega el perfil a la lista de monitores
            }
        } catch (Exception e) {
            System.out.println("Error obteniendo los datos de los monitores: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconecta de la base de datos
        }
        return monitores; // Devuelve la lista de monitores
    }

    /**
     * Elimina el rol de monitor de un usuario específico.
     * 
     * @param idUser El ID del usuario cuyo rol de monitor se desea eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean eliminarMonitor(String idUser) {
        boolean modificacion = false; // Estado de la operación de modificación
        PreparedStatement ps = null; // Variable para la sentencia SQL
        
        try {
            this.conectar(); // Conecta a la base de datos
            
            // Consulta SQL para actualizar el rol del usuario a 'aprendiz' y eliminar la asignación del instructor
            String sql = "UPDATE tb_usuarios " +
                         "SET id_rol_fk = 1, id_instructor_asig = NULL " +
                         "WHERE id_usuario = (SELECT id_usuario_fk FROM tb_perfil WHERE id_perfil = ?)";
            ps = getCon().prepareStatement(sql); // Prepara la sentencia SQL
            ps.setString(1, idUser); // Asigna el ID del usuario a la consulta SQL
            
            // Ejecuta la consulta SQL de actualización
            int modificado = ps.executeUpdate();
            
            // Verifica si la actualización afectó alguna fila
            if (modificado > 0) {
                modificacion = true; // La operación fue exitosa
            } else {
                System.out.println("No se pudo realizar la modificación del rol: " + idUser);
            }
        } catch (Exception e) {
            System.out.println("Error quitando el rol monitor: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconecta de la base de datos
        }
        return modificacion; // Devuelve el estado de la operación
    }
    
    /**
     * Lista los posts publicados por un monitor específico.
     * 
     * @param idMonitor El ID del monitor cuyos posts se desean listar.
     * @return Una lista de posts publicados por el monitor.
     */
    public List<Post> listaPostMonitor(int idMonitor) {
        List<Post> postsMonitor = new ArrayList<>(); // Lista para almacenar los posts del monitor
        PreparedStatement ps = null; // Variable para la sentencia SQL
        ResultSet rs = null; // Variable para el resultado de la consulta SQL
        
        try {
            this.conectar(); // Conecta a la base de datos
            
            // Consulta SQL para obtener los posts del monitor y contar el número de archivos asociados a cada post
            String sql = "SELECT p.id_post, p.titulo_post, p.estado, p.validacion, p.observacion, p.fecha_post, " +
                         "COUNT(a.id_archivo) AS cantidadArchivos, CONCAT(pe.nombre_usuario, ' ', pe.apellido_usuario) AS nombreCompleto " +
                         "FROM tb_post p " +
                         "JOIN tb_archivo a ON p.id_post = a.id_post_fk " +
                         "JOIN tb_usuarios u ON p.id_usuario_fk = u.id_usuario " +
                         "JOIN tb_perfil pe ON u.id_usuario = pe.id_usuario_fk " +
                         "WHERE p.id_usuario_fk = ? " +
                         "GROUP BY id_post";
            ps = getCon().prepareStatement(sql); // Prepara la sentencia SQL
            ps.setInt(1, idMonitor); // Asigna el ID del monitor a la consulta SQL
            
            rs = ps.executeQuery(); // Ejecuta la consulta SQL
            
            // Procesa los resultados de la consulta
            while (rs.next()) {
                Post post = new Post(); // Crea una nueva instancia de Post
                post.setId(rs.getInt("id_post"));
                post.setIdUsuarioFk(idMonitor);
                post.setTitulo(rs.getString("titulo_post"));
                post.setNombreUsuario(rs.getString("nombreCompleto"));
                post.setObservacion(rs.getString("observacion"));
                post.setValidacion(rs.getBoolean("validacion"));
                post.setEstado(rs.getBoolean("estado"));
                post.setContador(rs.getInt("cantidadArchivos"));
                post.setFechaPost(rs.getTimestamp("fecha_post"));

                postsMonitor.add(post); // Agrega el post a la lista de posts del monitor
            }
        } catch (Exception e) {
            System.out.println("Error obteniendo los posts del monitor: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconecta de la base de datos
        }
        return postsMonitor; // Devuelve la lista de posts del monitor
    }
}
