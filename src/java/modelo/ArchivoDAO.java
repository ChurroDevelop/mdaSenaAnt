package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.objetos.Archivo;

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
            String sql = "INSERT INTO tb_documento (extension_documento, documento, id_post_fk) VALUES (?, ?, ?)";

            // Preparar la sentencia SQL para su ejecución
            ps = getCon().prepareStatement(sql);

            // Asignar valores a los parámetros de la sentencia SQL
            ps.setString(1, archivo.getExtensionDocumento());  // Extensión del archivo
            ps.setBytes(2, archivo.getDocumento());  // Contenido del archivo en formato de bytes
            ps.setInt(3, archivo.getIdPostFk());  // ID del post al que está asociado el archivo

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
}
