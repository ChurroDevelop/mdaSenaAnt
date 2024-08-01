package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            String sql = "INSERT INTO tb_post (titulo_post, estado, observacion, id_usuario_fk) VALUES (?, ?, ?, ?)";

            // Preparar la sentencia SQL para su ejecución
            ps = getCon().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // Asignar valores a los parámetros de la sentencia SQL
            ps.setString(1, post.getTitulo());  // Título del post
            ps.setBoolean(2, post.getEstado());  // Estado del post (activo/inactivo)
            ps.setString(3, post.getObservacion());  // Observaciones adicionales sobre el post
            ps.setInt(4, post.getIdUsuarioFk());  // ID del usuario que creó el post

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
}
