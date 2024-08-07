package modelo.objetos;

public class Usuario {
    // Atributos de la clase usuario
    private int id_usuario;
    private String correoInst;
    private String password;
    private String codigo;
    private Rol id_rol_fk;

    // Constructor vacio
    public Usuario() {
    }

    // Constructor con parametros
    public Usuario(int id_usuario, String correoInst, String password, String codigo, Rol id_rol_fk) {
        this.id_usuario = id_usuario;
        this.correoInst = correoInst;
        this.password = password;
        this.codigo = codigo;
        this.id_rol_fk = id_rol_fk;
    }

    // Getters and Setters
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCorreoInst() {
        return correoInst;
    }

    public void setCorreoInst(String correoInst) {
        this.correoInst = correoInst;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Rol getId_rol_fk() {
        return id_rol_fk;
    }

    public void setId_rol_fk(Rol id_rol_fk) {
        this.id_rol_fk = id_rol_fk;
    }

    
}
