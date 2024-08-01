package modelo.objetos;

public class Archivo {

    // Atributos del archivo
    private int idDocumento;
    private String extensionDocumento;
    private String nombreDocumento;
    private byte[] documento;
    private int idPostFk;

    // Constructor vacio

    public Archivo() {
    }
    
    // Constructor con parametros
    public Archivo(int idDocumento, String extensionDocumento, String nombreDocumento, byte[] documento, int idPostFk) {
        this.idDocumento = idDocumento;
        this.extensionDocumento = extensionDocumento;
        this.nombreDocumento = nombreDocumento;
        this.documento = documento;
        this.idPostFk = idPostFk;
    }

    // Getters y Setters
    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getExtensionDocumento() {
        return extensionDocumento;
    }

    public void setExtensionDocumento(String extensionDocumento) {
        this.extensionDocumento = extensionDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    public int getIdPostFk() {
        return idPostFk;
    }

    public void setIdPostFk(int idPostFk) {
        this.idPostFk = idPostFk;
    }
    
    
    
}
