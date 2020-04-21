package com.consultoraestrategia.ss_crmeducativo.entities;

/**
 * Created by irvinmarin on 09/06/2017.
 */

public class Anotaciones {

    private String titulo;
    private String content;
    private String hora;
    private String Curso;
    private String NombreDocente;

    public Anotaciones(String titulo, String content, String hora, String curso, String nombreDocente) {
        this.titulo = titulo;
        this.content = content;
        this.hora = hora;
        Curso = curso;
        NombreDocente = nombreDocente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCurso() {
        return Curso;
    }

    public void setCurso(String curso) {
        Curso = curso;
    }

    public String getNombreDocente() {
        return NombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        NombreDocente = nombreDocente;
    }
}
