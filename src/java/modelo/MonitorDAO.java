package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.objetos.Perfil;
import modelo.objetos.Usuario;
import modelo.objetos.Post;

/**
 * Clase DAO para operaciones relacionadas con los monitores.
 */
public class MonitorDAO extends Conexion {

    /**
     * Obtiene una lista de perfiles de monitores asignados a un instructor
     * específico.
     *
     * @param idInstructor El ID del instructor.
     * @return Una lista de objetos Perfil que representan a los monitores.
     */
    public List<Perfil> obtenerMonitores(String idInstructor) {
        List<Perfil> monitores = new ArrayList<>(); // Instancia de un nuevo ArrayList
        PreparedStatement ps = null; // Variable para el manejo de la consulta SQL
        ResultSet rs = null; // Variable para obtener lo que retorna la consulta SQL
        try {
            this.conectar(); // Método para conectar con la base de datos
            String sql = "SELECT * FROM tb_perfil INNER JOIN tb_usuarios ON tb_perfil.id_usuario_fk = tb_usuarios.id_usuario WHERE tb_usuarios.id_rol_fk = 3 AND id_instructor_asig = ?"; // Consulta para obtener todos los usuarios que tengan el rol de monitor
            ps = getCon().prepareStatement(sql); // Preparar la consulta SQL
            ps.setString(1, idInstructor);
            rs = ps.executeQuery(); // Ejecutar la consulta SQL

            // Realizar bucle para que retorne aprendiz por aprendiz que cumpla con la consulta SQL
            while (rs.next()) {
                System.out.println("SE ESTAN GENERANDO LOS MONITORES, UN MOMENTO");
                Perfil perfil = new Perfil(); // Instancia de un nuevo perfil

                // Seteo de perfil para agregarlo al ArrayList
                perfil.setId_perfil(rs.getInt("id_perfil"));
                perfil.setNombre_usuario(rs.getString("nombre_usuario"));
                perfil.setApellido_usuario(rs.getString("apellido_usuario"));
                perfil.setCentro_formacion(rs.getString("centro_formacion"));
                perfil.setNum_documento(rs.getString("num_documento"));
                perfil.setId_usuario_fk(new Usuario()); // Puede ser mejorado si necesitas más información del usuario

                monitores.add(perfil);
            }
        } catch (Exception e) {
            System.out.println("ERROR OBTENIENDO LOS DATOS DE LOS MONITORES: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return monitores;
    }

    /**
     * Elimina el rol de monitor de un usuario específico.
     *
     * @param idUser El ID del usuario a modificar.
     * @return true si la modificación fue exitosa, false en caso contrario.
     */
    public boolean eliminarMonitor(String idUser) {
        boolean modificacion = false; // Manejo de estado para saber si se modificó o no el rol del aprendiz
        PreparedStatement ps = null; // Variable para la consulta SQL
        try {
            this.conectar(); // Método para conectar a la base de datos
            String sql = "UPDATE tb_usuarios\n" +
                        "SET id_rol_fk = 1, id_instructor_asig = null\n" +
                        "WHERE id_usuario = (SELECT id_usuario_fk FROM tb_perfil WHERE id_perfil = ?);"; // Consulta SQL para actualizar el rol del usuario
            ps = getCon().prepareStatement(sql); // Preparar la consulta para ejecutar en el gestor de base de datos
            ps.setString(1, idUser); // Pasarle el parámetro que es el ID del usuario
            int modificado = ps.executeUpdate(); // Ejecutar la consulta, devuelve un entero

            // Si lo que ha sido modificado es mayor a 0 entonces ejecuta lo siguiente
            if (modificado > 0) {
                modificacion = true; // Cambia el estado a true, es decir que modificó el usuario
            } else {
                System.out.println("NO SE PUDO REALIZAR LA MODIFICACION DE ROL: " + idUser);
            }
        } catch (Exception e) {
            System.out.println("ERROR QUITANDO EL ROL MONITOR: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                this.desconectar(); // Método para desconectar la base de datos
            } catch (Exception e) {
                System.out.println("ERROR CERRANDO RECURSOS: " + e.getMessage());
            }
        }
        return modificacion;
    }
    
    // Metodo para listar los post por id de monitor
    public List<Post> listaPostMonitor(int idMonitor){
        
        // Retornara una lista de posts, los cuales son los que ha subido el monitor
        List<Post> postsMonitor = new ArrayList<>();
        
        // Variable para el manejo de las consultas
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        // Manejo de errores
        try {
            // Metodo para conectar con la base de datos
            this.conectar();
            
            // Consutla sql para obtener los datos necesarios
            String sql = "SELECT p.id_post, p.titulo_post, p.estado, p.validacion, p.observacion, p.fecha_post, COUNT(a.id_archivo) as cantidadArchivos, CONCAT(pe.nombre_usuario, \" \", pe.apellido_usuario) as nombreCompleto FROM tb_post p JOIN tb_archivo a ON p.id_post = a.id_post_fk JOIN tb_usuarios u ON p.id_usuario_fk = u.id_usuario JOIN tb_perfil pe ON u.id_usuario = pe.id_usuario_fk WHERE p.id_usuario_fk = ? GROUP BY id_post";
            
            // Preparar la conexion con la consulta sql
            ps = getCon().prepareStatement(sql);
            
            // Se setea el id del monitor en la primea columna para que se cumpla la consulta
            ps.setInt(1, idMonitor);
            
            // Ejecutar la consulta
            rs = ps.executeQuery();
            
            // Si se encuentran posts de ese monitor
            while(rs.next()){
                
                // Se instancia un nuevo objeto post, y lo agregara en un arreglo
                Post post = new Post();
                post.setId(rs.getInt("id_post"));
                post.setIdUsuarioFk(idMonitor);
                post.setTitulo(rs.getString("titulo_post"));
                post.setNombreUsuario(rs.getString("nombreCompleto"));
                post.setObservacion(rs.getString("observacion"));
                post.setValidacion(rs.getBoolean("validacion"));
                post.setEstado(rs.getBoolean("estado"));
                post.setContador(rs.getInt("cantidadArchivos"));
                post.setFechaPost(rs.getTimestamp("fecha_post"));
                
                // Agregar el posts al arreglo
                postsMonitor.add(post);
            }
        } catch (Exception e) {
            System.out.println("ERROR OBTENIENDO LOS POSTS DEL MONITOR: " + e.getMessage());
        } finally {
            // Metodo para desconectar la base de datos
            this.desconectar();
        }
        return  postsMonitor;
    }
}
