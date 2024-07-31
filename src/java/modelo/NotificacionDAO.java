package modelo;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NotificacionDAO extends Conexion{
    
    // Metodo para realizar la notificacion para el usuario al que se le asigna el rol de monitor
    public boolean registrarNotificacion(String idUser) {
        boolean estado = false; // Manejo del estado de la inserccion de una nueva notificacion
        PreparedStatement ps = null; // Variable para preparar la consulta
        try {
            this.conectar(); // Metodo para conectar a la base de datos
            String sql = "INSERT INTO tb_notificaciones(estado_notificacion, id_user_fk) VALUES (true,?)"; // Comando SQL para hacer el registro de la notificacion
            ps = getCon().prepareStatement(sql); // Preparar la consulta SQL
            ps.setString(1, idUser); // Setear el id del usuario al cual se le va a notificar
            int x = ps.executeUpdate(); // Ejecutar el comando SQL
            if (x > 0) { // Si es mayor a 0 lo que cambio 
                estado = true; // Cambie el estado de la insercion de la notificacion
                System.out.println("SE CREO LA NOTIFICACION PARA EL USUARIO: " + idUser); // Mensaje de depuracion
            }
        } catch (Exception e) {
            System.out.println("Error en el notificacionesDAO: " + e.getMessage());
        } finally {
            this.desconectar(); // Metodo para desconectar la base de datos
        }
        return estado;
    }
    
    public boolean leerNotificacion(){
        boolean estado = false;
        return estado;
    }
}
