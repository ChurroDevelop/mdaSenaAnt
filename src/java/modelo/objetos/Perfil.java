package modelo.objetos;

public class Perfil {
    // Atributos del objeto perfil
    private int id_perfil;
    private String nombre_usuario;
    private String apellido_usuario;
    private String num_documento;
    private String centro_formacion;
    private Usuario id_usuario_fk;

    // Constructor vacio
    public Perfil() {
    }

    // Constructor con parametros
    public Perfil(int id_perfil, String nombre_usuario, String apellido_usuario, String num_documento, String centro_formacion, Usuario id_usuario_fk) {
        this.id_perfil = id_perfil;
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
        this.num_documento = num_documento;
        this.centro_formacion = centro_formacion;
        this.id_usuario_fk = id_usuario_fk;
    }

    // Getters and Setters
    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    public String getCentro_formacion() {
        return centro_formacion;
    }

    public void setCentro_formacion(String centro_formacion) {
        this.centro_formacion = centro_formacion;
    }

    public Usuario getId_usuario_fk() {
        return id_usuario_fk;
    }

    public void setId_usuario_fk(Usuario id_usuario_fk) {
        this.id_usuario_fk = id_usuario_fk;
    }
}
