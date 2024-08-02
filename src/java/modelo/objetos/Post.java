package modelo.objetos;

import java.sql.Timestamp;
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
    private int contador;
    private Timestamp fechaPost;

    public Post() {
    }

    public Post(int id, String titulo, boolean estado, String observacion, int idUsuarioFk, String nombreUsuario, int contador, Timestamp fechaPost) {
        this.id = id;
        this.titulo = titulo;
        this.estado = estado;
        this.observacion = observacion;
        this.idUsuarioFk = idUsuarioFk;
        this.nombreUsuario = nombreUsuario;
        this.contador = contador;
        this.fechaPost = fechaPost;
    }

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

    public boolean isEstado() {
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

    public void setIdUsuarioFk(int idUsuarioFk) {
        this.idUsuarioFk = idUsuarioFk;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public Timestamp getFechaPost() {
        return fechaPost;
    }

    public void setFechaPost(Timestamp fechaPost) {
        this.fechaPost = fechaPost;
    }
    
    
    
}
