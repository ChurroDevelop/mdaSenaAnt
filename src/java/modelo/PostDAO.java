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

    // Metodo para crear el post
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

    // metodo para listar todos los post de la base de datos
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

    // Metodo para listar los posts de los monitores que asigno el instructor
    public List<Post> listarPostsUser(String idInstructor) {
        List<Post> posts = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "SELECT p.id_post ,p.fecha_post as fecha ,CONCAT(pe.nombre_usuario, \" \", pe.apellido_usuario) as nombreCompleto, p.titulo_post, COUNT(a.id_archivo) AS cantidadArchivos, p.estado, p.validacion, p.observacion FROM tb_post p JOIN tb_usuarios u ON p.id_usuario_fk = u.id_usuario JOIN tb_perfil pe ON u.id_usuario = pe.id_usuario_fk JOIN tb_archivo a ON a.id_post_fk = p.id_post WHERE u.id_instructor_asig = ? GROUP BY p.titulo_post";
            ps = getCon().prepareStatement(sql);
            ps.setString(1, idInstructor);
            rs = ps.executeQuery();
            while (rs.next()) {           
                System.out.println("AGREGANDO UN NUEVO POST AL ARREGLO");
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
            System.out.println("ERROR LISTANDO LOS POSTS: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return posts;
    }
    
    // Metodo para modificar la visualizacion del post
    public boolean modificarEstado(int idPost) {
        // Manejo de estado para saber si se modifico o no
        boolean estado = false;
        
        // Variable para el manejo de las consultas SQL
        PreparedStatement ps = null;
        
        try {
            // Metodo para conectar la base de datos
            this.conectar();
            
            // Update SQL para modificar el estaod del post y la validacion del post
            String sql = "UPDATE tb_post set validacion = true, estado = true WHERE id_post = ?";
            
            // Preparar la consulta SQL
            ps = getCon().prepareStatement(sql);
            
            // Se setea el valor a su respectiva columna
            ps.setInt(1, idPost);
            
            // Entero para manejar su se ejecuto el update SQL o no
            int x = ps.executeUpdate();
            if (x > 0) {
                System.out.println("SE MODIFICO EL ESTADO DEL POST");
                estado = true;
            }
        } catch (Exception e) {
            // Depuracion del error por consola
            System.out.println("ERROR ACTUALIZANDO EL ESTADO DEL PSOT: " + e.getMessage());
        } finally {
            // Metodo para desconectar la base de datos
            this.desconectar();
        }
        // Retorna el valor booleano
        return estado;
    }
    
    // Metodo para agregar la observacion al post
    public boolean agregarObservacion(int idPost, String observacion){
        // Manejo de estado para saber si fue o no actualizado
        boolean estado = false;
        
        // Variable para el manejo de la consulta SQL
        PreparedStatement ps = null;
        
        try {
            // Metodo para conectar la base de datos
            this.conectar();
            
            // Update SQL de dicho post, agregandole la observacion
            String sql = "UPDATE tb_post SET estado = false, validacion = true, observacion = ? WHERE id_post = ?";
            
            // Preparar la consulta SQL
            ps = getCon().prepareStatement(sql);
            
            // Setearle a sus columnas los datos correspondientes
            ps.setString(1, observacion);
            ps.setInt(2, idPost);
            
            // Entero para saber si fue o no actualizado la observacion del post
            int x = ps.executeUpdate();
            if (x > 0) {
                System.out.println("SE MODIFICO Y SE AGREGO UNA OBSERVACION"); 
                estado = true;
           }
        } catch (Exception e) {
            // Depuracion del error
            System.out.println("ERROR ACTUALIZANDO EL POST CON OBSERVACION: " + e.getMessage());
        } finally {
            // Metodo para desconectar la base de datos
            this.desconectar();
        }
        // Retorna el estado
        return estado;
    }
    
    /*
        Metodo para deshabilitar el post, "No se esta utilizando este metodo"
    */
    public boolean deshabilitarPost(String idPost){
        boolean estado = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "UPDATE tb_post SET estado = false, validacion = true WHERE id_post = ?";
            ps = getCon().prepareStatement(sql);
            ps.setString(1, idPost);
            int x = ps.executeUpdate();
            if (x > 0) {
                estado = true;
                System.out.println("SE MODIFICO EL ESTADO DEL POST: " + idPost);
            }
        } catch (Exception e) {
            System.out.println("ERROR AL MODIFICAR EL ESTADO DEL POST: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return estado;
    }

    // Metodo para eliminar el post de la base de datos
    public boolean eliminarPost(String idPost) {
        
        // Manejo del estado para saber si se elimino o no el post
        boolean estado = false;
        
        // Variables para el manejo de las consu,tas
        PreparedStatement ps = null;
        PreparedStatement psDos = null;
        try {
            // Metodo para conectar con la base de datos
            this.conectar();
            
            // Consultas SQL
            String sql = "DELETE FROM tb_post WHERE id_post = ?";
            String sqlDos = "DELETE FROM tb_archivo WHERE id_post_fk = ?";
            
            // Tomar y preparar las consultas sql
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
                System.out.println("SE ELIMINO EL POST CON EL ID: " + idPost);
            }
        } catch (Exception e) {
            System.out.println("ERROR ELIMINANDO EL POST: " + e.getMessage());
        } finally {
            // Metodo para desconectar la base de datos
            this.desconectar();
        }
        // Retornara un valor booleano
        return estado;
    }
}
