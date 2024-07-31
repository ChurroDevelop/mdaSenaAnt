package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.objetos.Archivo;

public class ArchivoDAO extends Conexion {

  public void crearArchivo(Archivo archivo) throws SQLException {
    PreparedStatement ps = null;
    try {
      this.conectar();
      String sql = "INSERT INTO tb_documento (extension_documento, documento, id_post_fk) VALUES (?, ?, ?)";
      ps = getCon().prepareStatement(sql);
      ps.setString(1, archivo.getExtensionDocumento());
      ps.setBytes(2, archivo.getDocumento());
      ps.setInt(3, archivo.getIdPostFk());
      ps.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Error creando el archivo: " + e.getMessage());
    } finally {
      this.desconectar();
    }
  }
}
