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

    // Metodo para crear archivos y asignarles el id del post
    public void crearArchivo(Archivo archivo) throws SQLException {
        // Preparación de la sentencia SQL
        PreparedStatement ps = null;  

        try {
            // Establece la conexión a la base de datos
            this.conectar();  

            // SQL para insertar un nuevo archivo en la tabla 'tb_documento'
            String sql = "INSERT INTO tb_archivo (extension_archivo, archivo, nombre_archivo, id_post_fk) VALUES (?, ?, ?, ?)";

            // Preparar la sentencia SQL para su ejecución
            ps = getCon().prepareStatement(sql);

            // Asignar valores a los parámetros de la sentencia SQL
            ps.setString(1, archivo.getExtensionDocumento());  // Extensión del archivo
            ps.setBytes(2, archivo.getDocumento());  // Contenido del archivo en formato de bytes
            ps.setString(3, archivo.getNombreDocumento()); // Nombre del archivo a crear
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

    // Metodo para obtener los archivos por id de archivos
    public Archivo obtenerArchivoPorId(int idDocumento) throws SQLException {
        // Instancia de un nuevo objeto Archivo con valor null
        Archivo archivo = null;
        
        // Varibales para las consultas SQL
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Metodo para conectar la base de datos
            this.conectar();
            
            // Contula SQL para obtener todos los datos del archivo por el id
            String sql = "SELECT * FROM tb_archivo WHERE id_archivo= ?";
            
            // Preparar la consulta para ejecutar
            ps = getCon().prepareStatement(sql);
            
            // Se setea el id del archivo en la primera columna es decir "?"
            ps.setInt(1, idDocumento);
            
            // Ejecutar la consulta devuelve un ResultSet
            rs = ps.executeQuery();

            // Si se encuentra algun archivo por ese id
            if (rs.next()) {
                
                // Se le asignan los valores de las columnas a las siguientes variables
                int id = rs.getInt("id_archivo");
                String extension = rs.getString("extension_archivo");
                String nameDoc = rs.getString("nombre_archivo");
                byte[] documento = rs.getBytes("archivo");
                int idPostFk = rs.getInt("id_post_fk");

                // Se instancia de que el archivo es un nuevo objeto y se le asignan los valores
                archivo = new Archivo(id, extension, nameDoc, documento, idPostFk);
            }
        } catch (SQLException e) {
            // Error por si algo falla
            throw new SQLException("Error al obtener el archivo: " + e.getMessage());
        } finally {
            // Metodo para desconectar la base de datos
            this.desconectar();
        }

        return archivo;
    }

    // Metodo para listar los archivos por ID del Post
    public List<Archivo> listarArchivosPorPostId(int postId) throws SQLException {
        // Declaracion de un nuevo array, que es lo que retornara la funcion
        List<Archivo> archivos = new ArrayList<>();
        
        // Variables para el manejo de la consulta SQL
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Metodo para conectar la base de datos
            this.conectar();
            
            // Consulta SQL que busca todo en la tabla archivos cuando se le pasa el id del post
            String sql = "SELECT * FROM tb_archivo WHERE id_post_fk = ?";
            
            // Se prepara la consulta sql
            ps = getCon().prepareStatement(sql);
            
            // Se le asigna a la primera columna el id del post es decri "?"
            ps.setInt(1, postId);
            
            // Ejecuta la consulta con todas las filas que encuentre
            rs = ps.executeQuery();

            // Como encuentra cierta cantidad de filas se hace un bucle WHILE
            while (rs.next()) {
                
                // Se toma el valor de las columnas de la consulta 1 por una
                int id = rs.getInt("id_archivo");
                String extension = rs.getString("extension_archivo");
                String nameDoc = rs.getString("nombre_archivo");
                byte[] documento = rs.getBytes("archivo");
                int idPostFk = rs.getInt("id_post_fk");

                // Se agrega al arreglo un objeto archivo por cada iteracion del while
                archivos.add(new Archivo(id, extension, nameDoc, documento, idPostFk));
            }
        } catch (SQLException e) {
            // Manejo de error por si algo sale mal en la consulta
            throw new SQLException("Error al listar archivos: " + e.getMessage());
        } finally {
            // Metodo para desconectar la base de datos
            this.desconectar();
        }
        // Retornar el arreglo de archivos
        return archivos;
    }

}
