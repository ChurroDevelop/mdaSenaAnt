package modelo;
public class Usuario {
    private int id_usuario;
    private String correoInstitucional;
    private String contrasena;
    private Rol id_rol_fk;

    public Usuario() {
    }

    public Usuario(int id_usuario, String correoInstitucional, String contrasena, Rol id_rol_fk) {
        this.id_usuario = id_usuario;
        this.correoInstitucional = correoInstitucional;
        this.contrasena = contrasena;
        this.id_rol_fk = id_rol_fk;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contraseña) {
        this.contrasena = contraseña;
    }

    public Rol getId_rol_fk() {
        return id_rol_fk;
    }

    public void setId_rol_fk(Rol id_rol_fk) {
        this.id_rol_fk = id_rol_fk;
    }
}
