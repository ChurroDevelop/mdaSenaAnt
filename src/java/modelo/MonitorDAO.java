package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.objetos.Perfil;
import modelo.objetos.Usuario;

public class MonitorDAO extends Conexion{
    
    // Metodo para obtener la lista de los monitores de la base de datos que retornara un arrayList
    public List<Perfil> obtenerMonitores(String idInstructor){
        List<Perfil> monitores = new ArrayList<>(); // Se instancia una nueva arrayList
        PreparedStatement ps = null; //  Variable para el manejo de la consulta SQL
        ResultSet rs = null; // Variable para obtener lo que retorna la consulta SQL
        try {
            this.conectar(); // Metodo para conectar con la base de datos
            String sql = "SELECT * FROM tb_perfil INNER JOIN tb_usuarios ON tb_perfil.id_usuario_fk = tb_usuarios.id_usuario WHERE tb_usuarios.id_rol_fk = 3 AND id_instructor_asig = ?"; // Consulta para obtener todos los usuarios que tengan el rol de monitor
            ps = getCon().prepareStatement(sql); // Preparar la consulta SQL
            ps.setString(1, idInstructor);
            rs = ps.executeQuery(); // Ejecutar la consulta SQL
            
            // Realizar bucle para que retorne aprendiz por aprendiz que cumpla con la consulta SQL
            while (rs.next()) {
                System.out.println("SE ESTAN GENERANDO LOS MONITORES, UN MOMENTO");
                Perfil perfil = new Perfil(); // Instancia de un nuevo perfil
                
                // Seteo de perfil para agregarlo al arrayList
                int idPerfil = rs.getInt("id_perfil");
                perfil.setId_perfil(idPerfil);
                perfil.setNombre_usuario(rs.getString("nombre_usuario"));
                perfil.setApellido_usuario(rs.getString("apellido_usuario"));
                perfil.setCentro_formacion(rs.getString("centro_formacion"));
                perfil.setNum_documento(rs.getString("num_documento"));
                perfil.setId_usuario_fk(new Usuario());
                monitores.add(perfil);
            }
        } catch (Exception e) {
            System.out.println("ERROR OBTENIENDO LOS DATOS DEL LOS MONITORES");
        } finally {
            this.desconectar(); // Metodo para desconectar con la base de datos
        }
        return monitores;
    }
    
    // Metodo para quitarle el rol de monitor a un aprendiz que recibe como parametro el id del usuario a modificar
    public boolean eliminarMonitor(String idUser) {
        boolean modificacion = false; // Manejo de estado para saber si se modifico o no el rol del aprendiz
        PreparedStatement ps = null; // Variable para la consulta sql
        try {
            this.conectar(); // Metodo para conectar a la base de datos
            String sql = "UPDATE tb_usuarios SET id_rol_fk = 1, id_instructor_asig = null WHERE id_usuario = ?"; // Consulta SQL para actualizar el rol del usuario
            ps = getCon().prepareStatement(sql); // Preparar la consulta para ejecutar en el gestor de base de datos
            ps.setString(1, idUser); // Pasarle el parametro que es el id del usuario
            int modificado = ps.executeUpdate(); // Ejecutar la consulta, devuelve un entero
            
            // Si lo que a sido modificado es mayor a 0 entonces ejecuta lo siguient
            if (modificado > 0) {
                modificacion = true; // Cambia el estado a true, es decir que modifico el usuario
            } else {
                System.out.println("NO SE PUDO REALIZAR LA MODIFICACION DE ROL");
            }
        } catch (Exception e) {
            System.out.println("ERROR QUITANDO EL ROL MONITOR: " + e.getMessage());
        } finally {
            this.desconectar(); // Metodo para desconectar la base de datos
        }
        return modificacion;
    }
}
