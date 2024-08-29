package modelo.objetos;

import java.sql.Timestamp;

/**
 * Clase que representa un post en el sistema.
 */
public class Post {

    // Atributos del post
    private String titulo; // Título del post
    private String observacion; // Observación del post
    private String nombreUsuario; // Nombre del usuario que creó el post
    private boolean estado; // Estado del post (activo/inactivo)
    private boolean validacion; // Validación del post (aceptado/rechazado)
    private int id; // Identificador único del post
    private int idUsuarioFk; // Identificador del usuario asociado al post
    private int contador; // Contador asociado al post
    private Timestamp fechaPost; // Fecha de creación del post
    private String nombreInstructor; // Nombre del instructor asociado al post

    /**
     * Constructor vacío de la clase Post.
     */
    public Post() {
    }

    /**
     * Constructor con parámetros para inicializar un objeto Post.
     *
     * @param titulo El título del post.
     * @param observacion La observación del post.
     * @param nombreUsuario El nombre del usuario que creó el post.
     * @param estado El estado del post (activo/inactivo).
     * @param validacion La validación del post (aceptado/rechazado).
     * @param id El identificador único del post.
     * @param idUsuarioFk El identificador del usuario asociado al post.
     * @param contador El contador asociado al post.
     * @param fechaPost La fecha de creación del post.
     * @param nombreInstructor El nombre del instructor asociado al post.
     */
    public Post(String titulo, String observacion, String nombreUsuario, boolean estado, boolean validacion, int id, int idUsuarioFk, int contador, Timestamp fechaPost, String nombreInstructor) {    
        this.titulo = titulo;
        this.observacion = observacion;
        this.nombreUsuario = nombreUsuario;
        this.estado = estado;
        this.validacion = validacion;
        this.id = id;
        this.idUsuarioFk = idUsuarioFk;
        this.contador = contador;
        this.fechaPost = fechaPost;
        this.nombreInstructor = nombreInstructor;
    }

    // Getters y Setters

    /**
     * Obtiene el título del post.
     * 
     * @return El título del post.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del post.
     * 
     * @param titulo El nuevo título del post.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la observación del post.
     * 
     * @return La observación del post.
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * Establece la observación del post.
     * 
     * @param observacion La nueva observación del post.
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * Obtiene el nombre del usuario que creó el post.
     * 
     * @return El nombre del usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre del usuario que creó el post.
     * 
     * @param nombreUsuario El nuevo nombre del usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene el estado del post.
     * 
     * @return El estado del post (activo/inactivo).
     */
    public boolean getEstado() {
        return estado;
    }

    /**
     * Establece el estado del post.
     * 
     * @param estado El nuevo estado del post.
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la validación del post.
     * 
     * @return La validación del post (aceptado/rechazado).
     */
    public boolean getValidacion() {
        return validacion;
    }

    /**
     * Establece la validación del post.
     * 
     * @param validacion La nueva validación del post.
     */
    public void setValidacion(boolean validacion) {
        this.validacion = validacion;
    }

    /**
     * Obtiene el identificador único del post.
     * 
     * @return El identificador del post.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del post.
     * 
     * @param id El nuevo identificador del post.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador del usuario asociado al post.
     * 
     * @return El identificador del usuario.
     */
    public int getIdUsuarioFk() {
        return idUsuarioFk;
    }

    /**
     * Establece el identificador del usuario asociado al post.
     * 
     * @param idUsuarioFk El nuevo identificador del usuario.
     */
    public void setIdUsuarioFk(int idUsuarioFk) {
        this.idUsuarioFk = idUsuarioFk;
    }

    /**
     * Obtiene el contador asociado al post.
     * 
     * @return El contador del post.
     */
    public int getContador() {
        return contador;
    }

    /**
     * Establece el contador asociado al post.
     * 
     * @param contador El nuevo contador del post.
     */
    public void setContador(int contador) {
        this.contador = contador;
    }

    /**
     * Obtiene la fecha de creación del post.
     * 
     * @return La fecha del post.
     */
    public Timestamp getFechaPost() {
        return fechaPost;
    }

    /**
     * Establece la fecha de creación del post.
     * 
     * @param fechaPost La nueva fecha del post.
     */
    public void setFechaPost(Timestamp fechaPost) {
        this.fechaPost = fechaPost;
    }

    /**
     * Obtiene el nombre del instructor asociado al post.
     * 
     * @return El nombre del instructor.
     */
    public String getNombreInstructor() {
        return nombreInstructor;
    }

    /**
     * Establece el nombre del instructor asociado al post.
     * 
     * @param nombreInstructor El nuevo nombre del instructor.
     */
    public void setNombreInstructor(String nombreInstructor) {
        this.nombreInstructor = nombreInstructor;
    }
}
