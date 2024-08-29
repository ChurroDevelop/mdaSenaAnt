package modelo.objetos;

/**
 * Clase que representa un rol en el sistema.
 */
public class Rol {

    // Atributos de la clase rol
    private int id_rol; // Identificador único del rol
    private String nombre_rol; // Nombre del rol

    /**
     * Constructor vacío de la clase Rol.
     */
    public Rol() {
    }

    /**
     * Constructor con parámetros para inicializar un objeto Rol.
     *
     * @param id_rol El identificador único del rol.
     * @param nombre_rol El nombre del rol.
     */
    public Rol(int id_rol, String nombre_rol) {
        this.id_rol = id_rol;
        this.nombre_rol = nombre_rol;
    }

    /**
     * Obtiene el identificador único del rol.
     * 
     * @return El identificador del rol.
     */
    public int getId_rol() {
        return id_rol;
    }

    /**
     * Establece el identificador único del rol.
     * 
     * @param id_rol El nuevo identificador del rol.
     */
    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    /**
     * Obtiene el nombre del rol.
     * 
     * @return El nombre del rol.
     */
    public String getNombre_rol() {
        return nombre_rol;
    }

    /**
     * Establece el nombre del rol.
     * 
     * @param nombre_rol El nuevo nombre del rol.
     */
    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }
}
