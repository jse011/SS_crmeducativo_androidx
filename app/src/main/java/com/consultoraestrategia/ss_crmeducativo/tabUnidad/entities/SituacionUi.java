package com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities;

public class SituacionUi {

    public enum TipoAprendizaje{SITUACION_SIG, IDEA_CLAVE}

    private int semana;
    private int sessiones;
    private int horas;

    private int idUnidadAprendizaje;
    private TipoAprendizaje tipo;
    private String descripcionTipo;
    private String descripcionSubtitulo;
    private String subtitulo;
    private String titulo;

    public SituacionUi() {
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public int getSessiones() {
        return sessiones;
    }

    public void setSessiones(int sessiones) {
        this.sessiones = sessiones;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getIdUnidadAprendizaje() {
        return idUnidadAprendizaje;
    }

    public void setIdUnidadAprendizaje(int idUnidadAprendizaje) {
        this.idUnidadAprendizaje = idUnidadAprendizaje;
    }

    public TipoAprendizaje getTipo() {
        return tipo;
    }

    public void setTipo(TipoAprendizaje tipo) {
        this.tipo = tipo;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    public String getDescripcionSubtitulo() {
        return descripcionSubtitulo;
    }

    public void setDescripcionSubtitulo(String descripcionSubtitulo) {
        this.descripcionSubtitulo = descripcionSubtitulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
