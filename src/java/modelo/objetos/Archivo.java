package modelo.objetos;

/**
 * Clase que representa un archivo en el sistema.
 */
public class Archivo {

    // Atributos del archivo
    private int idDocumento; // Identificador único del documento
    private String extensionDocumento; // Extensión del archivo (por ejemplo, .pdf)
    private String nombreDocumento; // Nombre del archivo
    private byte[] documento; // Contenido del archivo en formato byte[]
    private int idPostFk; // Identificador del post asociado al archivo

    /**
     * Constructor vacío de la clase Archivo.
     */
    public Archivo() {
    }
    
    /**
     * Constructor con parámetros para inicializar un objeto Archivo.
     *
     * @param idDocumento Identificador único del documento.
     * @param extensionDocumento Extensión del archivo.
     * @param nombreDocumento Nombre del archivo.
     * @param documento Contenido del archivo en formato byte[].
     * @param idPostFk Identificador del post asociado al archivo.
     */
    public Archivo(int idDocumento, String extensionDocumento, String nombreDocumento, byte[] documento, int idPostFk) {
        this.idDocumento = idDocumento;
        this.extensionDocumento = extensionDocumento;
        this.nombreDocumento = nombreDocumento;
        this.documento = documento;
        this.idPostFk = idPostFk;
    }

    // Getters y Setters
    /**
     * Obtiene el identificador del documento.
     * 
     * @return El identificador del documento.
     */
    public int getIdDocumento() {
        return idDocumento;
    }

    /**
     * Establece el identificador del documento.
     * 
     * @param idDocumento El nuevo identificador del documento.
     */
    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    /**
     * Obtiene la extensión del documento.
     * 
     * @return La extensión del documento.
     */
    public String getExtensionDocumento() {
        return extensionDocumento;
    }

    /**
     * Establece la extensión del documento.
     * 
     * @param extensionDocumento La nueva extensión del documento.
     */
    public void setExtensionDocumento(String extensionDocumento) {
        this.extensionDocumento = extensionDocumento;
    }

    /**
     * Obtiene el nombre del documento.
     * 
     * @return El nombre del documento.
     */
    public String getNombreDocumento() {
        return nombreDocumento;
    }

    /**
     * Establece el nombre del documento.
     * 
     * @param nombreDocumento El nuevo nombre del documento.
     */
    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    /**
     * Obtiene el contenido del documento.
     * 
     * @return El contenido del documento en formato byte[].
     */
    public byte[] getDocumento() {
        return documento;
    }

    /**
     * Establece el contenido del documento.
     * 
     * @param documento El nuevo contenido del documento en formato byte[].
     */
    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    /**
     * Obtiene el identificador del post asociado al archivo.
     * 
     * @return El identificador del post asociado.
     */
    public int getIdPostFk() {
        return idPostFk;
    }

    /**
     * Establece el identificador del post asociado al archivo.
     * 
     * @param idPostFk El nuevo identificador del post asociado.
     */
    public void setIdPostFk(int idPostFk) {
        this.idPostFk = idPostFk;
    }
}
