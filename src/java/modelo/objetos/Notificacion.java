package modelo.objetos;

public class Notificacion {
    private int id_notificacion;
    private boolean estado_notificacion;
    private Usuario id_usuario_fk;

    public Notificacion() {
    }

    public Notificacion(int id_notificacion, boolean estado_notificacion, Usuario id_usuario_fk) {
        this.id_notificacion = id_notificacion;
        this.estado_notificacion = estado_notificacion;
        this.id_usuario_fk = id_usuario_fk;
    }

    public int getId_notificacion() {
        return id_notificacion;
    }

    public void setId_notificacion(int id_notificacion) {
        this.id_notificacion = id_notificacion;
    }

    public boolean isEstado_notificacion() {
        return estado_notificacion;
    }

    public void setEstado_notificacion(boolean estado_notificacion) {
        this.estado_notificacion = estado_notificacion;
    }

    public Usuario getId_usuario_fk() {
        return id_usuario_fk;
    }

    public void setId_usuario_fk(Usuario id_usuario_fk) {
        this.id_usuario_fk = id_usuario_fk;
    }
}
