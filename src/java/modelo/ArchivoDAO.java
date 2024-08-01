package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.objetos.Archivo;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las operaciones relacionadas con la tabla 'tb_documento'
 * en la base de datos. Hereda de la clase Conexion para manejar la conexión a
 * la base de datos.
 */
public class ArchivoDAO extends Conexion {

    /**
     * Crea un nuevo archivo en la base de datos.
     *
     * @param archivo Objeto Archivo que contiene la información del archivo a
     * crear.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public void crearArchivo(Archivo archivo) throws SQLException {
        PreparedStatement ps = null;  // Preparación de la sentencia SQL

        try {
            this.conectar();  // Establece la conexión a la base de datos

            // SQL para insertar un nuevo archivo en la tabla 'tb_documento'
            String sql = "INSERT INTO tb_archivo (extension_archivo, archivo, nombre_archivo, id_post_fk) VALUES (?, ?, ?, ?)";

            // Preparar la sentencia SQL para su ejecución
            ps = getCon().prepareStatement(sql);

            // Asignar valores a los parámetros de la sentencia SQL
            ps.setString(1, archivo.getExtensionDocumento());  // Extensión del archivo
            ps.setBytes(2, archivo.getDocumento());  // Contenido del archivo en formato de bytes
            ps.setString(3, archivo.getNombreDocumento());
            ps.setInt(4, archivo.getIdPostFk());  // ID del post al que está asociado el archivo

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

    public Archivo obtenerArchivoPorId(int idDocumento) throws SQLException {
        Archivo archivo = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            this.conectar();
            String sql = "SELECT * FROM tb_archivo WHERE id_archivo= ?";
            ps = getCon().prepareStatement(sql);
            ps.setInt(1, idDocumento);
            rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_archivo");
                String extension = rs.getString("extension_archivo");
                String nameDoc = rs.getString("nombre_archivo");
                byte[] documento = rs.getBytes("archivo");
                int idPostFk = rs.getInt("id_post_fk");

                archivo = new Archivo(id, extension, nameDoc, documento, idPostFk);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el archivo: " + e.getMessage());
        } finally {
            this.desconectar();
        }

        return archivo;
    }

    public List<Archivo> listarArchivosPorPostId(int postId) throws SQLException {
        List<Archivo> archivos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            this.conectar();
            String sql = "SELECT * FROM tb_archivo WHERE id_post_fk = ?";
            ps = getCon().prepareStatement(sql);
            ps.setInt(1, postId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_archivo");
                String extension = rs.getString("extension_archivo");
                String nameDoc = rs.getString("nombre_archivo");
                byte[] documento = rs.getBytes("archivo");
                int idPostFk = rs.getInt("id_post_fk");

                archivos.add(new Archivo(id, extension, nameDoc, documento, idPostFk));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al listar archivos: " + e.getMessage());
        } finally {
            this.desconectar();
        }

        return archivos;
    }

}
