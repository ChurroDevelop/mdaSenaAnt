package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.objetos.Archivo;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones relacionadas con la tabla 'tb_archivo'
 * en la base de datos. Hereda de la clase {@link Conexion} para manejar la conexión
 * a la base de datos.
 */
public class ArchivoDAO extends Conexion {

    /**
     * Crea un nuevo archivo en la base de datos y lo asocia con un post específico.
     * 
     * @param archivo El objeto {@link Archivo} que contiene la información del archivo a insertar.
     * @throws SQLException Si ocurre un error durante la operación de inserción.
     */
    public void crearArchivo(Archivo archivo) throws SQLException {
        // Preparación de la sentencia SQL
        PreparedStatement ps = null;

        try {
            // Establece la conexión a la base de datos
            this.conectar();

            // SQL para insertar un nuevo archivo en la tabla 'tb_archivo'
            String sql = "INSERT INTO tb_archivo (extension_archivo, archivo, nombre_archivo, id_post_fk) VALUES (?, ?, ?, ?)";

            // Preparar la sentencia SQL para su ejecución
            ps = getCon().prepareStatement(sql);

            // Asignar valores a los parámetros de la sentencia SQL
            ps.setString(1, archivo.getExtensionDocumento());  // Extensión del archivo
            ps.setBytes(2, archivo.getDocumento());  // Contenido del archivo en formato de bytes
            ps.setString(3, archivo.getNombreDocumento()); // Nombre del archivo
            ps.setInt(4, archivo.getIdPostFk());  // ID del post asociado al archivo

            // Ejecutar la sentencia SQL de inserción
            ps.executeUpdate();
        } catch (SQLException e) {
            // Manejo de errores en caso de excepción
            System.out.println("Error creando el archivo: " + e.getMessage());
        } finally {
            // Cerrar la conexión a la base de datos, independientemente de si ocurre un error o no
            this.desconectar();
        }
    }

    /**
     * Obtiene un archivo de la base de datos basado en su ID.
     * 
     * @param idDocumento El ID del archivo a recuperar.
     * @return El objeto {@link Archivo} con la información del archivo, o {@code null} si no se encuentra.
     * @throws SQLException Si ocurre un error durante la operación de consulta.
     */
    public Archivo obtenerArchivoPorId(int idDocumento) throws SQLException {
        // Instancia de un nuevo objeto Archivo con valor null
        Archivo archivo = null;

        // Variables para la consulta SQL
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Establece la conexión a la base de datos
            this.conectar();
            
            // Consulta SQL para obtener los datos del archivo por su ID
            String sql = "SELECT * FROM tb_archivo WHERE id_archivo = ?";
            
            // Preparar la consulta SQL
            ps = getCon().prepareStatement(sql);
            
            // Asignar el ID del archivo al parámetro de la consulta
            ps.setInt(1, idDocumento);
            
            // Ejecutar la consulta y obtener el resultado
            rs = ps.executeQuery();

            // Si se encuentra un archivo con el ID proporcionado
            if (rs.next()) {
                
                // Recuperar los valores de las columnas y asignarlos a las variables
                int id = rs.getInt("id_archivo");
                String extension = rs.getString("extension_archivo");
                String nameDoc = rs.getString("nombre_archivo");
                byte[] documento = rs.getBytes("archivo");
                int idPostFk = rs.getInt("id_post_fk");

                // Crear un nuevo objeto Archivo con los valores recuperados
                archivo = new Archivo(id, extension, nameDoc, documento, idPostFk);
            }
        } catch (SQLException e) {
            // Manejo de errores en caso de excepción
            throw new SQLException("Error al obtener el archivo: " + e.getMessage());
        } finally {
            // Cerrar la conexión a la base de datos
            this.desconectar();
        }

        return archivo;
    }

    /**
     * Lista todos los archivos asociados a un post específico.
     * 
     * @param postId El ID del post para el cual recuperar los archivos.
     * @return Una lista de objetos {@link Archivo} asociados al post.
     * @throws SQLException Si ocurre un error durante la operación de consulta.
     */
    public List<Archivo> listarArchivosPorPostId(int postId) throws SQLException {
        // Lista que almacenará los archivos recuperados de la base de datos
        List<Archivo> archivos = new ArrayList<>();
        
        // Variables para la consulta SQL
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Establece la conexión a la base de datos
            this.conectar();
            
            // Consulta SQL para obtener todos los archivos asociados al ID del post
            String sql = "SELECT * FROM tb_archivo WHERE id_post_fk = ?";
            
            // Preparar la consulta SQL
            ps = getCon().prepareStatement(sql);
            
            // Asignar el ID del post al parámetro de la consulta
            ps.setInt(1, postId);
            
            // Ejecutar la consulta y obtener el resultado
            rs = ps.executeQuery();

            // Procesar cada fila del resultado
            while (rs.next()) {
                
                // Recuperar los valores de las columnas
                int id = rs.getInt("id_archivo");
                String extension = rs.getString("extension_archivo");
                String nameDoc = rs.getString("nombre_archivo");
                byte[] documento = rs.getBytes("archivo");
                int idPostFk = rs.getInt("id_post_fk");

                // Crear un nuevo objeto Archivo y agregarlo a la lista
                archivos.add(new Archivo(id, extension, nameDoc, documento, idPostFk));
            }
        } catch (SQLException e) {
            // Manejo de errores en caso de excepción
            throw new SQLException("Error al listar archivos: " + e.getMessage());
        } finally {
            // Cerrar la conexión a la base de datos
            this.desconectar();
        }

        return archivos;
    }

}
