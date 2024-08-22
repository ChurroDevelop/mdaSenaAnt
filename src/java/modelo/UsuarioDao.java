package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.objetos.Perfil;
import modelo.objetos.Rol;
import modelo.objetos.Usuario;

/**
 * Data Access Object (DAO) para operaciones relacionadas con los usuarios. Esta
 * clase proporciona métodos para registrar usuarios, autenticarlos, obtener su
 * ID, verificar su existencia, y realizar otras operaciones relacionadas con
 * los usuarios en la base de datos.
 */
public class UsuarioDao extends Conexion {

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param user El objeto Usuario que contiene la información del usuario a
     * registrar.
     * @param id_rol El ID del rol asignado al usuario.
     * @return true si el usuario fue registrado exitosamente, false en caso
     * contrario.
     * @throws SQLException Si ocurre un error en la operación SQL.
     */
    public boolean registrarUsuario(Usuario user, int id_rol) throws SQLException {
        boolean insertado = false; // Por defecto, el usuario no se ha insertado.
        PreparedStatement ps = null; // Variable para preparar la consulta SQL.
        try {
            this.conectar(); // Conectar a la base de datos.
            // Consulta SQL para insertar un nuevo usuario en la base de datos.
            String sqlUser = "INSERT INTO tb_usuarios(correo_inst, password, id_rol_fk, estado_usuario) VALUES (?,?,?, true)";
            ps = getCon().prepareStatement(sqlUser); // Preparar la consulta SQL.
            // Establecer los valores para la consulta.
            ps.setString(1, user.getCorreoInst());
            ps.setString(2, user.getPassword());
            ps.setInt(3, id_rol);
            // Ejecutar la consulta y verificar si se insertó un registro.
            if (ps.executeUpdate() == 1) {
                insertado = true; // El usuario fue insertado exitosamente.
            }
            System.out.println("Usuario creado");
        } catch (SQLException e) {
            System.out.println("Error creando el usuario: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconectar de la base de datos.
        }
        return insertado; // Retornar true o false dependiendo del éxito de la operación.
    }

    /**
     * Autentica a un usuario basándose en su correo electrónico y contraseña.
     *
     * @param user El objeto Usuario con el correo electrónico y la contraseña
     * para autenticación.
     * @return true si el usuario es autenticado exitosamente, false en caso
     * contrario.
     */
    public boolean autenticacion(Usuario user) {
        boolean accion = false; // Por defecto, la autenticación falla.
        PreparedStatement ps = null; // Variable para preparar la consulta SQL.
        ResultSet rs = null; // Variable para manejar los resultados de la consulta.
        try {
            this.conectar(); // Conectar a la base de datos.
            // Consulta SQL para verificar el correo electrónico y la contraseña del usuario.
            String sql = "SELECT * FROM tb_usuarios WHERE correo_inst = ? and password = ?";
            ps = getCon().prepareStatement(sql); // Preparar la consulta SQL.
            // Establecer los valores para la consulta.
            ps.setString(1, user.getCorreoInst());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery(); // Ejecutar la consulta SQL.
            // Verificar si se encontró un usuario con el correo electrónico y contraseña proporcionados.
            if (rs.absolute(1)) {
                System.out.println("Se encontró el usuario y se logeó");
                accion = true; // La autenticación fue exitosa.
            }
        } catch (Exception e) {
            System.out.println("ERROR AL LOGIN: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconectar de la base de datos.
        }
        return accion; // Retornar true o false dependiendo del éxito de la autenticación.
    }

    /**
     * Obtiene el ID del usuario basado en su correo electrónico.
     *
     * @param correo El correo electrónico del usuario.
     * @return El ID del usuario si se encuentra, 0 si no se encuentra.
     * @throws SQLException Si ocurre un error en la operación SQL.
     */
    public int obtenerId(String correo) throws SQLException {
        int id = 0; // Por defecto, el ID del usuario es 0.
        PreparedStatement ps = null; // Variable para preparar la consulta SQL.
        ResultSet rs = null; // Variable para manejar los resultados de la consulta.
        try {
            this.conectar(); // Conectar a la base de datos.
            // Consulta SQL para obtener el ID del usuario basado en su correo electrónico.
            String sql = "SELECT * FROM tb_usuarios WHERE correo_inst = ?";
            ps = getCon().prepareStatement(sql); // Preparar la consulta SQL.
            // Establecer el valor del correo electrónico en la consulta.
            ps.setString(1, correo);
            rs = ps.executeQuery(); // Ejecutar la consulta SQL.
            // Verificar si se encontró un usuario con el correo electrónico proporcionado.
            if (rs.next()) {
                id = rs.getInt("id_usuario"); // Obtener el ID del usuario.
            } else {
                System.out.println("No se encuentra el correo");
            }
        } catch (Exception e) {
            System.out.println("Error obteniendo el ID: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconectar de la base de datos.
        }
        return id; // Retornar el ID del usuario.
    }

    /**
     * Verifica si un usuario ya existe en la base de datos.
     *
     * @param user El objeto Usuario con el correo electrónico para verificar.
     * @return true si el usuario existe, false en caso contrario.
     */
    public boolean buscarUser(Usuario user) {
        boolean encontrado = false; // Por defecto, el usuario no se encuentra.
        PreparedStatement ps = null; // Variable para preparar la consulta SQL.
        ResultSet rs = null; // Variable para manejar los resultados de la consulta.
        try {
            this.conectar(); // Conectar a la base de datos.
            // Consulta SQL para verificar si existe un usuario con el correo electrónico proporcionado.
            String sql = "SELECT COUNT(*) FROM tb_usuarios WHERE correo_inst = ?";
            ps = getCon().prepareStatement(sql); // Preparar la consulta SQL.
            // Establecer el valor del correo electrónico en la consulta.
            ps.setString(1, user.getCorreoInst());
            rs = ps.executeQuery(); // Ejecutar la consulta SQL.
            // Verificar si se encontró al menos un usuario con el correo electrónico proporcionado.
            if (rs.next()) {
                int contador = rs.getInt(1); // Obtener el conteo de usuarios encontrados.
                if (contador > 0) {
                    System.out.println("Se encontró un usuario");
                    encontrado = true; // El usuario fue encontrado.
                } else {
                    System.out.println("No se encontró un usuario");
                }
            }
        } catch (Exception e) {
            System.out.println("Error encontrando el usuario: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconectar de la base de datos.
        }
        return encontrado; // Retornar true o false dependiendo de si el usuario fue encontrado.
    }

    /**
     * Obtiene todos los datos de un usuario basado en su correo electrónico.
     *
     * @param user El objeto Usuario con el correo electrónico para buscar.
     * @return El objeto Usuario con todos los datos encontrados, o null si no
     * se encuentra.
     */
    public Usuario getDataUser(Usuario user) {
        RolDAO rolDao = new RolDAO(); // Instanciar RolDAO para obtener el rol del usuario.
        Usuario u = null; // Crear una nueva instancia de Usuario.
        PreparedStatement ps = null; // Variable para preparar la consulta SQL.
        ResultSet rs = null; // Variable para manejar los resultados de la consulta.
        try {
            this.conectar(); // Conectar a la base de datos.
            // Consulta SQL para obtener todos los datos del usuario basado en el correo electrónico.
            String sql = "SELECT id_usuario, correo_inst, password, id_rol_fk, estado_usuario FROM tb_usuarios WHERE correo_inst = ?";
            ps = getCon().prepareStatement(sql); // Preparar la consulta SQL.
            // Establecer el valor del correo electrónico en la consulta.
            ps.setString(1, user.getCorreoInst());
            rs = ps.executeQuery(); // Ejecutar la consulta SQL.
            // Verificar si se encontró un usuario con el correo electrónico proporcionado.
            if (rs.next()) {
                u = new Usuario(); // Crear una nueva instancia de Usuario.
                int idUser = rs.getInt("id_usuario"); // Obtener el ID del usuario.
                String correo = rs.getString("correo_inst");
                String password = rs.getString("password");
                boolean estado = rs.getBoolean("estado_usuario");

                // Establecer los datos del usuario en la instancia.
                u.setCorreoInst(correo);
                u.setId_usuario(idUser);
                u.setPassword(password);
                u.setEstadoUser(estado);
                // Obtener el rol del usuario utilizando RolDAO.
                Rol rol = rolDao.getIdRol(user);
                u.setId_rol_fk(rol);
            }
        } catch (Exception e) {
            System.out.println("Error en obtener los datos del usuario: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconectar de la base de datos.
        }
        return u; // Retornar el objeto Usuario con los datos obtenidos.
    }

    /**
     * Asigna el rol de monitor a un usuario específico.
     *
     * @param userId El ID del usuario al que se le asignará el rol de monitor.
     * @param idInstructor El ID del instructor que asigna el rol.
     * @return true si el rol fue asignado exitosamente, false en caso
     * contrario.
     */
    public boolean asignarRolMonitor(String userId, String idInstructor) {
        boolean estado = false; // Por defecto, el rol no se asigna.
        PreparedStatement ps = null; // Variable para preparar la consulta SQL.
        try {
            this.conectar(); // Conectar a la base de datos.
            // Consulta SQL para actualizar el rol del usuario a "monitor" (ID = 3) y asignar el instructor.
            String sql = "UPDATE tb_usuarios SET id_rol_fk = 3, id_instructor_asig = ? WHERE id_usuario = ?";
            ps = getCon().prepareStatement(sql); // Preparar la consulta SQL.
            // Establecer los valores para la consulta.
            ps.setString(1, idInstructor);
            ps.setString(2, userId);
            // Ejecutar la consulta y verificar si se actualizó al menos un registro.
            int columnas = ps.executeUpdate();
            if (columnas > 0) {
                estado = true; // El rol fue asignado exitosamente.
            }
        } catch (Exception e) {
            System.out.println("Error actualizando rol: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconectar de la base de datos.
        }
        return estado; // Retornar true o false dependiendo del éxito de la asignación del rol.
    }

    /**
     * Cambia la contraseña de un usuario.
     *
     * @param userId El ID del usuario cuya contraseña se cambiará.
     * @param nuevaPassword La nueva contraseña del usuario.
     * @return true si la contraseña fue actualizada exitosamente, false en caso
     * contrario.
     */
    public boolean cambiarContrasena(int userId, String nuevaPassword) {
        boolean actualizado = false; // Por defecto, la contraseña no se actualiza.
        PreparedStatement ps = null; // Variable para preparar la consulta SQL.
        try {
            this.conectar(); // Conectar a la base de datos.
            // Consulta SQL para actualizar la contraseña del usuario.
            String sqlUpdate = "UPDATE tb_usuarios SET password = ? WHERE id_usuario = ?";
            ps = getCon().prepareStatement(sqlUpdate); // Preparar la consulta SQL.
            // Establecer los valores para la consulta.
            ps.setString(1, nuevaPassword);
            ps.setInt(2, userId);
            // Ejecutar la consulta y verificar si se actualizó al menos un registro.
            if (ps.executeUpdate() == 1) {
                actualizado = true; // La contraseña fue actualizada exitosamente.
                System.out.println("Contraseña actualizada");
            }
        } catch (SQLException e) {
            System.out.println("Error actualizando la contraseña: " + e.getMessage());
        } finally {
            this.desconectar(); // Desconectar de la base de datos.
        }
        return actualizado; // Retornar true o false dependiendo del éxito de la actualización de la contraseña.
    }
}
