package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.objetos.Post;

public class PostDAO extends Conexion {

  public int crearPost(Post post) throws SQLException {
    int idPost = 0;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      this.conectar();
      String sql = "INSERT INTO tb_post (titulo_post, estado, observacion, id_usuario_fk) VALUES (?, ?, ?, ?)";
      ps = getCon().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, post.getTitulo());
      ps.setBoolean(2, post.getEstado());
      ps.setString(3, post.getObservacion());
      ps.setInt(4, post.getIdUsuarioFk());
      ps.executeUpdate();
      rs = ps.getGeneratedKeys();
      if (rs.next()) {
        idPost = rs.getInt(1);
      }
    } catch (SQLException e) {
      System.out.println("Error creando el post: " + e.getMessage());
    } finally {
      this.desconectar();
    }
    return idPost;
  }
}
