package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.objetos.Post;
import modelo.objetos.Usuario;

public class AdminDAO extends Conexion {

    /**
     * Obtiene una lista de todos los posts desde la base de datos.
     * 
     * @return Una lista de objetos {@link Post} con la información de los posts.
     */
    public List<Post> listarTodosLosPosts() {

        // Lista que almacenará todos los posts recuperados de la base de datos
        List<Post> allPosts = new ArrayList<>();

        // Declaración de variables para el manejo de consultas SQL
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Establece la conexión con la base de datos
            this.conectar();

            // Consulta SQL para obtener todos los posts junto con información adicional
            String sql = "SELECT \n"
                    + "    p.id_post,\n"
                    + "    p.observacion,\n"
                    + "    p.fecha_post,\n"
                    + "    CONCAT(per.nombre_usuario, ' ', per.apellido_usuario) AS nombreCompleto,\n"
                    + "    p.titulo_post,\n"
                    + "    COUNT(a.id_archivo) AS cantidadArchivos,\n"
                    + "    CONCAT(pi.nombre_usuario, ' ', pi.apellido_usuario) AS nombre_instructor_asignado,\n"
                    + "    p.estado,\n"
                    + "    p.validacion\n"
                    + "FROM \n"
                    + "    tb_post p\n"
                    + "LEFT JOIN \n"
                    + "    tb_usuarios u ON p.id_usuario_fk = u.id_usuario\n"
                    + "LEFT JOIN \n"
                    + "    tb_perfil per ON u.id_usuario = per.id_usuario_fk\n"
                    + "LEFT JOIN \n"
                    + "    tb_archivo a ON p.id_post = a.id_post_fk\n"
                    + "LEFT JOIN \n"
                    + "    tb_usuarios ui ON u.id_instructor_asig = ui.id_usuario\n"
                    + "LEFT JOIN \n"
                    + "    tb_perfil pi ON ui.id_usuario = pi.id_usuario_fk\n"
                    + "GROUP BY \n"
                    + "    p.id_post, p.fecha_post, p.titulo_post, nombreCompleto, nombre_instructor_asignado, p.estado, p.validacion;";
            
            // Prepara la consulta SQL
            ps = getCon().prepareStatement(sql);

            // Ejecuta la consulta y obtiene el resultado
            rs = ps.executeQuery();

            // Procesa cada fila del resultado
            while (rs.next()) {

                // Crea una nueva instancia de Post y asigna sus atributos
                Post post = new Post();
                post.setId(rs.getInt("id_post"));
                post.setTitulo(rs.getString("titulo_post"));
                post.setNombreUsuario(rs.getString("nombreCompleto"));
                post.setObservacion(rs.getString("observacion"));
                post.setValidacion(rs.getBoolean("validacion"));
                post.setEstado(rs.getBoolean("estado"));
                post.setContador(rs.getInt("cantidadArchivos"));
                post.setFechaPost(rs.getTimestamp("fecha_post"));
                post.setNombreInstructor(rs.getString("nombre_instructor_asignado"));

                // Agrega el objeto Post a la lista
                allPosts.add(post);
            }
        } catch (Exception e) {
            // Manejo de errores en caso de excepción
            System.out.println("ERROR AL LISTAR POSTS: " + e.getMessage());
        } finally {
            // Cierra la conexión con la base de datos
            this.desconectar();
        }

        // Retorna la lista de posts
        return allPosts;
    }

    /**
     * Obtiene una lista de usuarios desde la base de datos, excluyendo los administradores.
     * 
     * @return Una lista de objetos {@link Usuario} con la información de los usuarios.
     */
    public List<Usuario> listaUsuarios() {
        // Lista que almacenará todos los usuarios recuperados de la base de datos
        List<Usuario> allUsers = new ArrayList<>();

        // Declaración de variables para el manejo de consultas SQL
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Establece la conexión con la base de datos
            this.conectar();

            // Consulta SQL para obtener la información de los usuarios excluyendo administradores
            String sql = "SELECT u.id_usuario, CONCAT(p.nombre_usuario, ' ', p.apellido_usuario) AS nombreCompleto, u.estado_usuario, r.nombre_rol "
                    + "FROM tb_usuarios u "
                    + "JOIN tb_perfil p ON u.id_usuario = p.id_usuario_fk "
                    + "JOIN tb_rol r ON r.id_rol = u.id_rol_fk "
                    + "WHERE r.nombre_rol != 'Administrador'";
            
            // Prepara la consulta SQL
            ps = getCon().prepareStatement(sql);

            // Ejecuta la consulta y obtiene el resultado
            rs = ps.executeQuery();

            // Procesa cada fila del resultado
            while (rs.next()) {                
                // Crea una nueva instancia de Usuario y asigna sus atributos
                Usuario user = new Usuario();
                user.setId_usuario(rs.getInt("id_usuario"));
                user.setNombreUser(rs.getString("nombreCompleto"));
                user.setNombreRol(rs.getString("nombre_rol"));
                user.setEstadoUser(rs.getBoolean("estado_usuario"));

                // Agrega el objeto Usuario a la lista
                allUsers.add(user);
            }
        } catch (Exception e) {
            // Manejo de errores en caso de excepción
            System.out.println("ERROR AL LISTAR USUARIOS: " + e.getMessage());
        } finally {
            // Cierra la conexión con la base de datos
            this.desconectar();
        }

        // Retorna la lista de usuarios
        return allUsers;
    }
    
    /**
     * Deshabilita un usuario basado en su ID.
     * 
     * @param idUser El ID del usuario a deshabilitar.
     * @return {@code true} si la operación fue exitosa, {@code false} en caso contrario.
     */
    public boolean modificarEstado(String idUser) {
        boolean estado = false;
        PreparedStatement ps = null;

        try {
            // Establece la conexión con la base de datos
            this.conectar();

            // Consulta SQL para deshabilitar al usuario
            String sql = "UPDATE tb_usuarios SET estado_usuario = 0 WHERE id_usuario = ?";
            ps = getCon().prepareStatement(sql);
            ps.setString(1, idUser);
            int rowsAffected = ps.executeUpdate();

            // Verifica si la operación fue exitosa
            if (rowsAffected > 0) {
                estado = true;
            }
        } catch (Exception e) {
            // Manejo de errores en caso de excepción
            System.out.println("ERROR AL DESHABILITAR USUARIO: " + e.getMessage());
        } finally {
            // Cierra la conexión con la base de datos
            this.desconectar();
        }

        return estado;
    }
    
    /**
     * Habilita un usuario basado en su ID.
     * 
     * @param idUser El ID del usuario a habilitar.
     * @return {@code true} si la operación fue exitosa, {@code false} en caso contrario.
     */
    public boolean estadoActivo(String idUser) {
        boolean estado = false;
        PreparedStatement ps = null;

        try {
            // Establece la conexión con la base de datos
            this.conectar();

            // Consulta SQL para habilitar al usuario
            String sql = "UPDATE tb_usuarios SET estado_usuario = 1 WHERE id_usuario = ?";
            ps = getCon().prepareStatement(sql);
            ps.setString(1, idUser);
            int rowsAffected = ps.executeUpdate();

            // Verifica si la operación fue exitosa
            if (rowsAffected > 0) {
                estado = true;
            }
        } catch (Exception e) {
            // Manejo de errores en caso de excepción
            System.out.println("ERROR AL HABILITAR USUARIO: " + e.getMessage());
        } finally {
            // Cierra la conexión con la base de datos
            this.desconectar();
        }

        return estado;
    }
}
