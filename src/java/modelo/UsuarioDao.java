package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.objetos.Rol;
import modelo.objetos.Usuario;

public class UsuarioDao extends Conexion { // Hereda todo de la clase Conexion

    // Metodo publico que retornara true o false, recibira como parametros un objeto usuario y el rol que se esta seteando en el Servlet de Registro
    public boolean registrarUsuario(Usuario user, int id_rol) throws SQLException {
        boolean insertado = false; // Por defecto retornara false
        PreparedStatement ps = null; // PreparedStatement para el manejo de los scripts de SQL
        try { // Manejo de excepciones
            this.conectar(); // Se llama al metodo conectar para que conecte con la base de datos
            /*
            IMPORTANTE:
                Dependiendo la cantidad de columnas a la cual le vamos a agregar los valores, en el values se colocan signos de interrogacion es decir
                En este caso se estan manejando que se van a insertar 3 datos, entonces de colocan 3 signos de interrogacion, si simplemente fuera 1 solo 1 signo de interrogacion
             */
            String sqlUser = "INSERT INTO tb_usuarios(correo_inst, password, id_rol_fk) VALUES (?,?,?)"; // Se prepara en String el script que se va a ejecutar, de acuerdo con la base de datos
            ps = getCon().prepareStatement(sqlUser); // Compila y prepara la consulta como codigo SQL
            ps.setString(1, user.getCorreoInst());  // Se setea la primera columna como String, dependiendo de la base de datos
            ps.setString(2, user.getPassword()); // Se sete la segunda columna como String, dependiendo la base de datos
            ps.setInt(3, id_rol); // Se setea la tercera columna como Int, dependiendo la base de datos
            if (ps.executeUpdate() == 1) { // Si la ejecucion del Script es 1 es decir "True"
                insertado = true; // cambia el valor del insertado a true y es lo que devolvera el metodo
            }
            System.out.println("Usuario creado");
        } catch (SQLException e) {
            System.out.println("Error creando el usuario"); // Manejo del error por si no crea el usuario
        } finally {
            this.desconectar(); // Siempre se debera manejar el metodo desconectar la base de datos
        }
        return insertado; // Returna true o false dependiendo del recorrido que hizo en el metodo
    }

    // Metodo publico que retornara true o false, recibira como parametro un objeto usuario el cual se setea en el servlet del login
    public boolean autenticacion(Usuario user) {
        boolean accion = false; // Variable accion que por defecto sera false
        PreparedStatement ps = null; // Variable para el manejo de los Scripts SQL
        ResultSet rs = null; // Variable para manejar los resultados de las consultas 
        try {
            this.conectar(); // Metodo para conectar con la base de datos
            String sql = "SELECT * FROM tb_usuarios WHERE correo_inst = ? and password = ?"; // Consulta SQL que aceptara el correo institucional y la contraseña entrante
            ps = getCon().prepareStatement(sql); // Manejo para la conexion a la base de datos
            ps.setString(1, user.getCorreoInst()); // La primera coolumna tomara el correo entrante 
            ps.setString(2, user.getPassword()); // La segunda columna tomara la contraseña de manera que ya esta encriptada, la cual la buscara en la base de datos
            rs = ps.executeQuery(); // ejecuta la consulta
            if (rs.absolute(1)) { // Si encuentra el correo institucional y coinciden la contraseña 
                System.out.println("Se encontro el usuario y se logeo");
                accion = true; // devolvera true
            }
        } catch (Exception e) {
            System.out.println("ERROR AL LOGIN: " + e.getMessage());
        } finally {
            this.desconectar(); // Metodo para desconectar
        }
        return accion; // Retorna true o false dependiendo del recorrido
    }

    // Metodo para obtener el id del usuario con el correo que ingrese
    public int obtenerId(String correo) throws SQLException {
        int id = 0; // Variable que por defecto sera 0
        PreparedStatement ps = null; // Prepared statement para el manejo de los Scripts SQL
        ResultSet rs = null; // Result set para poder obtener eel valor de las consultas SQL
        try {
            this.conectar(); // Se conecta a la base de datos
            String sql = "SELECT * FROM tb_usuarios WHERE correo_inst = ?"; // Consulta SQL para poder obtener el id del usuario que ingreso el correo
            ps = getCon().prepareStatement(sql); // Preparar el Script SQL
            ps.setString(1, correo); // en la primera columna se le coloca el correo entrante del metod 
            rs = ps.executeQuery(); // Ejecuta la consulta para poder obtener el id del usuario
            if (rs.next()) { // Si existe algun usuario con ese correo devolvera el id
                id = rs.getInt("id_usuario"); // a la variable id, se le asigna el valor que retorne la consulta
            } else {
                System.out.println("No se encuentra el correo");
            }
        } catch (Exception e) {
            System.out.println("Error obteniendo el ID: " + e.getMessage());
        } finally {
            this.desconectar(); // Metodo para desconectar la base de datos
        }
        return id; // Retorna el id
    }

    // Metodo para validar que el usuario ya existe, tendra como parametros un objeto de tipo usuario
    public boolean buscarUser(Usuario user) {
        boolean encontrado = false; // Estado para saber si se encuentra o no el usuario registrado, por defecto false
        PreparedStatement ps = null; // Prepared statement para el manejo de los Scripts SQL
        ResultSet rs = null; // ResultSet para el manejo del retorno de las consultas
        try {
            this.conectar(); // Metodo para conectar con la base de datos
            String sql = "SELECT COUNT(*) FROM tb_usuarios WHERE correo_inst = ?"; // Script SQL que buscara si existe un usuario con ese correo institucional
            ps = getCon().prepareStatement(sql); // Se prepara el Script SQL para ser ejecutado
            ps.setString(1, user.getCorreoInst()); // Se setea en String el correo institucional
            rs = ps.executeQuery(); // Se ejecuta la consulta y el RS tomara el valor de retorno de esa consulta
            if (rs.next()) { // si se encuentra un usuario
                int contador = rs.getInt(1); // se debera devolver el id del usuario
                if (encontrado = (contador > 0)) {
                    System.out.println("Se encontro un usuario");

                    return true;
                } else {
                    System.out.println("No se encontro un usuario");

                    return false;
                }
//                encontrado = (contador > 0); // si contador es mayor a 0 devuelve true, se le asignara al encontrado y eso retornara
            }
        } catch (Exception e) {
            System.out.println("Error encontrando el usuario: " + e.getMessage());
        } finally {
            this.desconectar(); // Metodo para desconectar la base de datos
        }
        return encontrado; // Retorna la variable encontrad, que depende del recorrido de todo el metodo
    }

    // Metodo para obtener todos los datos del usuario
    public Usuario getDataUser(Usuario user) {
        RolDAO rolDao = new RolDAO();
        Usuario u = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "SELECT id_usuario, correo_inst, password, id_rol_fk FROM tb_usuarios WHERE correo_inst = ?";
            ps = getCon().prepareStatement(sql);
            ps.setString(1, user.getCorreoInst());
            rs = ps.executeQuery();
            if (rs.next()) {
                u = new Usuario();
                int idUser = rs.getInt("id_usuario");
                String correo = rs.getString("correo_inst");
                String password = rs.getString("password");
                u.setCorreoInst(correo);
                u.setId_usuario(idUser);
                u.setPassword(password);
                Rol rol = rolDao.getIdRol(user);
                u.setId_rol_fk(rol);
                System.out.println("Se pudo obtener todos los datos del usuario desde el Login");
            }
        } catch (Exception e) {
            System.out.println("Error en obtener los datos del usuario: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return u;
    }

    public boolean asignarRolMonitor(String userId) {
        boolean estado = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "UPDATE tb_usuarios SET id_rol_fk = 3 WHERE id_usuario = ?";
            ps = getCon().prepareStatement(sql);
            ps.setString(1, userId);
            int columnas = ps.executeUpdate();
            if (columnas > 0) {
                estado = true;
                System.out.println("Se pudo hacer la actualizacion de rol del usuario: " + userId);
            }
        } catch (Exception e) {
            System.out.println("Error actualizando rol: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return estado;
    }
}
