package modelo.objetos;

/**
 * Clase que representa el perfil de un usuario en el sistema.
 */
public class Perfil {

    // Atributos del objeto perfil
    private int id_perfil; // Identificador único del perfil
    private String nombre_usuario; // Nombre del usuario
    private String apellido_usuario; // Apellido del usuario
    private String num_documento; // Número de documento del usuario
    private String centro_formacion; // Centro de formación asociado al perfil
    private Usuario id_usuario_fk; // Usuario asociado al perfil

    /**
     * Constructor vacío de la clase Perfil.
     */
    public Perfil() {
    }

    /**
     * Constructor con parámetros para inicializar un objeto Perfil.
     *
     * @param id_perfil Identificador único del perfil.
     * @param nombre_usuario Nombre del usuario.
     * @param apellido_usuario Apellido del usuario.
     * @param num_documento Número de documento del usuario.
     * @param centro_formacion Centro de formación asociado al perfil.
     * @param id_usuario_fk Usuario asociado al perfil.
     */
    public Perfil(int id_perfil, String nombre_usuario, String apellido_usuario, String num_documento, String centro_formacion, Usuario id_usuario_fk) {
        this.id_perfil = id_perfil;
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
        this.num_documento = num_documento;
        this.centro_formacion = centro_formacion;
        this.id_usuario_fk = id_usuario_fk;
    }

    // Getters y Setters

    /**
     * Obtiene el identificador del perfil.
     * 
     * @return El identificador del perfil.
     */
    public int getId_perfil() {
        return id_perfil;
    }

    /**
     * Establece el identificador del perfil.
     * 
     * @param id_perfil El nuevo identificador del perfil.
     */
    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    /**
     * Obtiene el nombre del usuario asociado al perfil.
     * 
     * @return El nombre del usuario.
     */
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    /**
     * Establece el nombre del usuario asociado al perfil.
     * 
     * @param nombre_usuario El nuevo nombre del usuario.
     */
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    /**
     * Obtiene el apellido del usuario asociado al perfil.
     * 
     * @return El apellido del usuario.
     */
    public String getApellido_usuario() {
        return apellido_usuario;
    }

    /**
     * Establece el apellido del usuario asociado al perfil.
     * 
     * @param apellido_usuario El nuevo apellido del usuario.
     */
    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    /**
     * Obtiene el número de documento del usuario asociado al perfil.
     * 
     * @return El número de documento del usuario.
     */
    public String getNum_documento() {
        return num_documento;
    }

    /**
     * Establece el número de documento del usuario asociado al perfil.
     * 
     * @param num_documento El nuevo número de documento del usuario.
     */
    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    /**
     * Obtiene el centro de formación asociado al perfil.
     * 
     * @return El centro de formación.
     */
    public String getCentro_formacion() {
        return centro_formacion;
    }

    /**
     * Establece el centro de formación asociado al perfil.
     * 
     * @param centro_formacion El nuevo centro de formación.
     */
    public void setCentro_formacion(String centro_formacion) {
        this.centro_formacion = centro_formacion;
    }

    /**
     * Obtiene el usuario asociado al perfil.
     * 
     * @return El usuario asociado.
     */
    public Usuario getId_usuario_fk() {
        return id_usuario_fk;
    }

    /**
     * Establece el usuario asociado al perfil.
     * 
     * @param id_usuario_fk El nuevo usuario asociado al perfil.
     */
    public void setId_usuario_fk(Usuario id_usuario_fk) {
        this.id_usuario_fk = id_usuario_fk;
    }
}
