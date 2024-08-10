package modelo.objetos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Post {

    // Atributos del post
    private String titulo;
    private String observacion;
    private String nombreUsuario;
    private boolean estado;
    private boolean validacion;
    private int id;
    private int idUsuarioFk;
    private int contador;
    private Timestamp fechaPost;
    private String nombreInstructor;

    // Constructor vacio

    public Post() {
    }
    

    // Constructor con parametros
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

    // Getters and Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isValidacion() {
        return validacion;
    }

    public void setValidacion(boolean validacion) {
        this.validacion = validacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuarioFk() {
        return idUsuarioFk;
    }

    public void setIdUsuarioFk(int idUsuarioFk) {
        this.idUsuarioFk = idUsuarioFk;
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

    public Boolean getEstado() {
        return estado;
    }

    public Boolean getValidacion() {
        return validacion;
    }
    
    public String getNombreInstructor() {
        return nombreInstructor;
    }

    public void setNombreInstructor(String nombreInstructor) {
        this.nombreInstructor = nombreInstructor;
    }

}