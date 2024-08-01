package modelo.objetos;

public class Archivo {

    private int idDocumento;
    private String extensionDocumento;
    private byte[] documento;
    private int idPostFk;

    // Constructor
    public Archivo(int idDocumento, String extensionDocumento, byte[] documento, int idPostFk) {
        this.idDocumento = idDocumento;
        this.extensionDocumento = extensionDocumento;
        this.documento = documento;
        this.idPostFk = idPostFk;
    }

    // Getters y setters
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
