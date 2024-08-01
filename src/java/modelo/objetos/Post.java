package modelo.objetos;

import java.util.ArrayList;
import java.util.List;

public class Post {

    // Atributos del post
    private int id;
    private String titulo;
    private boolean estado;
    private String observacion;
    private int idUsuarioFk;
    private String nombreUsuario;

    // Constructor vacio
    public Post() {
    }

    // Constructor con parametros
    public Post(int id, String titulo, boolean estado, String observacion, int idUsuarioFk, String nombreUsuario) {
        this.id = id;
        this.titulo = titulo;
        this.estado = estado;
        this.observacion = observacion;
        this.idUsuarioFk = idUsuarioFk;
        this.nombreUsuario = nombreUsuario;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getIdUsuarioFk() {
        return idUsuarioFk;
    }

    public void setIdUsuarioFk(int idUsuario) {
        this.idUsuarioFk = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

}
