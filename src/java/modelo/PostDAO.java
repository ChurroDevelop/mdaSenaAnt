package modelo;

import com.mysql.cj.xdevapi.Result;
import config.Conexion;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.objetos.Post;

/**
 * Data Access Object (DAO) para las operaciones relacionadas con la tabla 'tb_post' en la base de datos.
 * Esta clase hereda de {@link Conexion} para gestionar la conexión a la base de datos y proporciona métodos
 * para crear, listar, modificar, agregar observaciones, deshabilitar y eliminar posts.
 */
public class PostDAO extends Conexion {

    /**
     * Crea un nuevo post en la base de datos.
     * 
     * @param post El objeto {@link Post} que contiene la información del nuevo post.
     * @return El ID del nuevo post creado. Retorna 0 si ocurre un error.
     * @throws SQLException Si ocurre un error durante la operación de base de datos.
     */
    public int crearPost(Post post) throws SQLException {
        int idPost = 0;  // Variable para almacenar el ID del post creado
        PreparedStatement ps = null;  // Preparación de la sentencia SQL
        ResultSet rs = null;  // Resultado de la ejecución de la sentencia SQL

        try {
            this.conectar();  // Establece la conexión a la base de datos

            // SQL para insertar un nuevo post en la tabla 'tb_post'
            String sql = "INSERT INTO tb_post (titulo_post, estado, observacion, id_usuario_fk) VALUES (?, false, ?, ?)";

            // Preparar la sentencia SQL para su ejecución
            ps = getCon().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Asignar valores a los parámetros de la sentencia SQL
            ps.setString(1, post.getTitulo());  // Título del post
            ps.setString(2, post.getObservacion());  // Observaciones adicionales sobre el post
            ps.setInt(3, post.getIdUsuarioFk());  // ID del usuario que creó el post

            // Ejecutar la sentencia SQL de inserción
            ps.executeUpdate();

            // Obtener el ID generado para el nuevo post
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idPost = rs.getInt(1);  // Obtener el ID generado
            }
        } catch (SQLException e) {
            // Manejo de errores en caso de excepción
            System.out.println("Error creando el post: " + e.getMessage());
        } finally {
            this.desconectar();  // Cerrar la conexión a la base de datos
        }

        return idPost;  // Retornar el ID del post creado (0 si ocurrió un error)
    }

    /**
     * Lista todos los posts activos en la base de datos.
     * 
     * @return Una lista de objetos {@link Post} que representan los posts activos.
     * @throws SQLException Si ocurre un error durante la operación de base de datos.
     */
    public List<Post> listarPosts() throws SQLException {
        List<Post> posts = new ArrayList<>();  // Lista para almacenar los posts activos
        PreparedStatement ps = null;  // Preparación de la sentencia SQL
        ResultSet rs = null;  // Resultado de la ejecución de la sentencia SQL

        try {
            this.conectar();  // Establece la conexión a la base de datos

            // SQL para seleccionar todos los posts activos junto con el nombre del usuario que los creó
            String sql = "SELECT p.*, \n"
                    + "CONCAT(pf.nombre_usuario, ' ', pf.apellido_usuario) AS nombre_completo\n"
                    + "FROM tb_post p\n"
                    + "INNER JOIN tb_usuarios u ON p.id_usuario_fk = u.id_usuario\n"
                    + "INNER JOIN tb_perfil pf ON u.id_usuario = pf.id_usuario_fk\n"
                    + "WHERE p.estado = 1;";

            // Preparar la sentencia SQL para su ejecución
            ps = getCon().prepareStatement(sql);

            // Ejecutar la sentencia SQL y obtener el resultado
            rs = ps.executeQuery();

            // Procesar los resultados
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id_post"));  // Establecer el ID del post
                post.setTitulo(rs.getString("titulo_post"));  // Establecer el título del post
                post.setEstado(rs.getBoolean("estado"));  // Establecer el estado del post
                post.setObservacion(rs.getString("observacion"));  // Establecer la observación del post
                post.setNombreUsuario(rs.getString("nombre_completo"));  // Establecer el nombre del usuario que creó el post
                posts.add(post);  // Agregar el post a la lista
            }
        } catch (SQLException e) {
            // Manejo de errores en caso de excepción
            System.out.println("Error listando los posts: " + e.getMessage());
        } finally {
            this.desconectar();  // Cerrar la conexión a la base de datos
        }

        return posts;  // Retornar la lista de posts activos
    }

    /**
     * Lista los posts asignados por un instructor a un monitor específico.
     * 
     * @param idInstructor El ID del instructor cuyo posts queremos listar.
     * @return Una lista de objetos {@link Post} asignados por el instructor.
     */
    public List<Post> listarPostsUser(String idInstructor) {
        List<Post> posts = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            this.conectar();
            // SQL para seleccionar los posts asignados por el instructor junto con información adicional
            String sql = "SELECT p.id_post, p.fecha_post AS fecha, CONCAT(pe.nombre_usuario, ' ', pe.apellido_usuario) AS nombreCompleto, p.titulo_post, COUNT(a.id_archivo) AS cantidadArchivos, p.estado, p.validacion, p.observacion " +
                         "FROM tb_post p " +
                         "JOIN tb_usuarios u ON p.id_usuario_fk = u.id_usuario " +
                         "JOIN tb_perfil pe ON u.id_usuario = pe.id_usuario_fk " +
                         "JOIN tb_archivo a ON a.id_post_fk = p.id_post " +
                         "WHERE u.id_instructor_asig = ? " +
                         "GROUP BY p.titulo_post";

            // Preparar y ejecutar la sentencia SQL
            ps = getCon().prepareStatement(sql);
            ps.setString(1, idInstructor);
            rs = ps.executeQuery();

            // Procesar los resultados
            while (rs.next()) {           
                Timestamp a = rs.getTimestamp("fecha");
                Post postIns = new Post();
                postIns.setId(rs.getInt("id_post"));
                postIns.setEstado(rs.getBoolean("estado"));
                postIns.setValidacion(rs.getBoolean("validacion"));
                postIns.setObservacion(rs.getString("observacion"));
                postIns.setFechaPost(a);
                postIns.setTitulo(rs.getString("titulo_post"));
                postIns.setNombreUsuario(rs.getString("nombreCompleto"));
                postIns.setContador(rs.getInt("cantidadArchivos"));
                posts.add(postIns);
            }
        } catch (Exception e) {
            System.out.println("Error listando los posts: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return posts;
    }
    
    /**
     * Modifica el estado de un post a 'activo' y establece la validación como 'verdadera'.
     * 
     * @param idPost El ID del post a modificar.
     * @return {@code true} si la operación fue exitosa, {@code false} en caso contrario.
     */
    public boolean modificarEstado(int idPost) {
        boolean estado = false;
        PreparedStatement ps = null;
        
        try {
            this.conectar();
            
            // SQL para actualizar el estado y la validación del post
            String sql = "UPDATE tb_post SET validacion = true, estado = true WHERE id_post = ?";
            
            // Preparar y ejecutar la sentencia SQL
            ps = getCon().prepareStatement(sql);
            ps.setInt(1, idPost);
            int x = ps.executeUpdate();
            if (x > 0) {
                estado = true;
            }
        } catch (Exception e) {
            System.out.println("Error actualizando el estado del post: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return estado;
    }
    
    /**
     * Agrega una observación a un post y cambia su estado a 'inactivo' y su validación a 'verdadera'.
     * 
     * @param idPost El ID del post al que se le agregará la observación.
     * @param observacion La observación a agregar al post.
     * @return {@code true} si la operación fue exitosa, {@code false} en caso contrario.
     */
    public boolean agregarObservacion(int idPost, String observacion){
        boolean estado = false;
        PreparedStatement ps = null;
        
        try {
            this.conectar();
            
            // SQL para actualizar la observación del post
            String sql = "UPDATE tb_post SET estado = false, validacion = true, observacion = ? WHERE id_post = ?";
            
            // Preparar y ejecutar la sentencia SQL
            ps = getCon().prepareStatement(sql);
            ps.setString(1, observacion);
            ps.setInt(2, idPost);
            int x = ps.executeUpdate();
            if (x > 0) {
                estado = true;
            }
        } catch (Exception e) {
            System.out.println("Error actualizando el post con observación: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return estado;
    }
    
    /**
     * Deshabilita un post marcándolo como 'inactivo' y estableciendo la validación como 'verdadera'.
     * Este método no se está utilizando actualmente.
     * 
     * @param idPost El ID del post a deshabilitar.
     * @return {@code true} si la operación fue exitosa, {@code false} en caso contrario.
     */
    public boolean deshabilitarPost(String idPost){
        boolean estado = false;
        PreparedStatement ps = null;
        try {
            this.conectar();
            // SQL para deshabilitar un post
            String sql = "UPDATE tb_post SET estado = false, validacion = true WHERE id_post = ?";
            ps = getCon().prepareStatement(sql);
            ps.setString(1, idPost);
            int x = ps.executeUpdate();
            if (x > 0) {
                estado = true;
            }
        } catch (Exception e) {
            System.out.println("Error al modificar el estado del post: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return estado;
    }

    /**
     * Elimina un post de la base de datos junto con sus archivos asociados.
     * 
     * @param idPost El ID del post a eliminar.
     * @return {@code true} si la operación fue exitosa, {@code false} en caso contrario.
     */
    public boolean eliminarPost(String idPost) {
        boolean estado = false;
        PreparedStatement ps = null;
        PreparedStatement psDos = null;
        
        try {
            this.conectar();
            
            // SQL para eliminar el post y sus archivos asociados
            String sql = "DELETE FROM tb_post WHERE id_post = ?";
            String sqlDos = "DELETE FROM tb_archivo WHERE id_post_fk = ?";
            
            ps = getCon().prepareStatement(sql);
            psDos = getCon().prepareStatement(sqlDos);
            
            // Eliminar los archivos relacionados al post
            psDos.setString(1, idPost);
            psDos.executeUpdate();
            
            // Eliminar el post
            ps.setString(1, idPost);
            int x = ps.executeUpdate();
            if (x > 0) {
                estado = true;
            }
        } catch (Exception e) {
            System.out.println("Error eliminando el post: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return estado;
    }
}
