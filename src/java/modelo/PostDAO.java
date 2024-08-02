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
 * Clase que gestiona las operaciones relacionadas con la tabla 'tb_post' en la
 * base de datos. Hereda de la clase Conexion para manejar la conexión a la base
 * de datos.
 */
public class PostDAO extends Conexion {

    /**
     * Crea un nuevo post en la base de datos.
     *
     * @param post Objeto Post que contiene la información del post a crear.
     * @return El ID del post recién creado. Si ocurre un error, retorna 0.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
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
     * @return Una lista de objetos Post que contienen la información de los
     * posts activos.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
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

    
    public List<Post> listarPostsUser(String idInstructor) {
        List<Post> posts = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "SELECT p.id_post ,p.fecha_post as fecha ,CONCAT(pe.nombre_usuario, \" \", pe.apellido_usuario) as nombreCompleto, p.titulo_post, COUNT(a.id_archivo) AS cantidadArchivos FROM tb_post p JOIN tb_usuarios u ON p.id_usuario_fk = u.id_usuario JOIN tb_perfil pe ON u.id_usuario = pe.id_usuario_fk JOIN tb_archivo a ON a.id_post_fk = p.id_post WHERE u.id_instructor_asig = ? GROUP BY p.titulo_post";
            ps = getCon().prepareStatement(sql);
            ps.setString(1, idInstructor);
            rs = ps.executeQuery();
            while (rs.next()) {           
                System.out.println("AGREGANDO UN NUEVO POST AL ARREGLO");
                Timestamp a = rs.getTimestamp("fecha");
                Post postIns = new Post();
                postIns.setId(rs.getInt("id_post"));
                postIns.setFechaPost(a);
                postIns.setTitulo(rs.getString("titulo_post"));
                postIns.setNombreUsuario(rs.getString("nombreCompleto"));
                postIns.setContador(rs.getInt("cantidadArchivos"));
                posts.add(postIns);
            }
        } catch (Exception e) {
            System.out.println("ERROR LISTANDO LOS POSTS: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return posts;
    }
    
    public boolean modificarEstado() {
        boolean estado = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "";
        } catch (Exception e) {
        }
        return estado;
    }
}
