package modelo.objetos;

/**
 * Clase que representa un usuario en el sistema.
 */
public class Usuario {

    // Atributos de la clase usuario
    private int id_usuario; // Identificador único del usuario
    private String correoInst; // Correo institucional del usuario
    private String nombreUser; // Nombre del usuario
    private String nombreRol; // Nombre del rol asociado al usuario
    private String password; // Contraseña del usuario
    private String codigo; // Código de usuario (puede ser un código de identificación o similar)
    private Boolean estadoUser; // Estado del usuario (activo/inactivo)
    private Rol id_rol_fk; // Rol asociado al usuario

    /**
     * Constructor vacío de la clase Usuario.
     */
    public Usuario() {
    }

    /**
     * Constructor con parámetros para inicializar un objeto Usuario.
     *
     * @param id_usuario El identificador único del usuario.
     * @param correoInst El correo institucional del usuario.
     * @param nombreUser El nombre del usuario.
     * @param nombreRol El nombre del rol asociado al usuario.
     * @param password La contraseña del usuario.
     * @param codigo El código de usuario.
     * @param estadoUser El estado del usuario.
     * @param id_rol_fk El rol asociado al usuario.
     */
    public Usuario(int id_usuario, String correoInst, String nombreUser, String nombreRol, String password, String codigo, Boolean estadoUser, Rol id_rol_fk) {
        this.id_usuario = id_usuario;
        this.correoInst = correoInst;
        this.nombreUser = nombreUser;
        this.nombreRol = nombreRol;
        this.password = password;
        this.codigo = codigo;
        this.estadoUser = estadoUser;
        this.id_rol_fk = id_rol_fk;
    }

    /**
     * Obtiene el identificador único del usuario.
     * 
     * @return El identificador del usuario.
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * Establece el identificador único del usuario.
     * 
     * @param id_usuario El nuevo identificador del usuario.
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * Obtiene el correo institucional del usuario.
     * 
     * @return El correo institucional del usuario.
     */
    public String getCorreoInst() {
        return correoInst;
    }

    /**
     * Establece el correo institucional del usuario.
     * 
     * @param correoInst El nuevo correo institucional del usuario.
     */
    public void setCorreoInst(String correoInst) {
        this.correoInst = correoInst;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password La nueva contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el código de usuario.
     * 
     * @return El código del usuario.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código de usuario.
     * 
     * @param codigo El nuevo código del usuario.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el rol asociado al usuario.
     * 
     * @return El rol asociado al usuario.
     */
    public Rol getId_rol_fk() {
        return id_rol_fk;
    }

    /**
     * Establece el rol asociado al usuario.
     * 
     * @param id_rol_fk El nuevo rol asociado al usuario.
     */
    public void setId_rol_fk(Rol id_rol_fk) {
        this.id_rol_fk = id_rol_fk;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return El nombre del usuario.
     */
    public String getNombreUser() {
        return nombreUser;
    }

    /**
     * Establece el nombre del usuario.
     * 
     * @param nombreUser El nuevo nombre del usuario.
     */
    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    /**
     * Obtiene el nombre del rol asociado al usuario.
     * 
     * @return El nombre del rol asociado al usuario.
     */
    public String getNombreRol() {
        return nombreRol;
    }

    /**
     * Establece el nombre del rol asociado al usuario.
     * 
     * @param nombreRol El nuevo nombre del rol asociado al usuario.
     */
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    /**
     * Obtiene el estado del usuario.
     * 
     * @return El estado del usuario (activo/inactivo).
     */
    public Boolean getEstadoUser() {
        return estadoUser;
    }

    /**
     * Establece el estado del usuario.
     * 
     * @param estadoUser El nuevo estado del usuario.
     */
    public void setEstadoUser(Boolean estadoUser) {
        this.estadoUser = estadoUser;
    }
}
