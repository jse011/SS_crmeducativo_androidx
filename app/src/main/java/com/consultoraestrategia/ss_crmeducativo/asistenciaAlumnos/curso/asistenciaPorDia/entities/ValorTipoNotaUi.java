package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;

public class ValorTipoNotaUi  {
    public enum Tipo{ PUNTUAL,TARDE, TARDE_JTD, AUSENTE, AUSENTE_JTD }
    String id;
    String titulo;
    String alias;
    String icono;
    String colorCurso;
    boolean selected;
    AsistenciaUi  asistenciaUi;
    public Tipo tipo= Tipo.PUNTUAL;
    boolean enabled;
    String estado;

    public ValorTipoNotaUi() {
    }

    public String getColorCurso() {
        return colorCurso;
    }

    public void setColorCurso(String colorCurso) {
        this.colorCurso = colorCurso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public AsistenciaUi getAsistenciaUi() {
        return asistenciaUi;
    }

    public void setAsistenciaUi(AsistenciaUi asistenciaUi) {
        this.asistenciaUi = asistenciaUi;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValorTipoNotaUi that = (ValorTipoNotaUi) o;

        return estado != null ? estado.equals(that.estado) : that.estado == null;
    }

    @Override
    public String toString() {
        return "ValorTipoNotaUi{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", alias='" + alias + '\'' +
                ", icono='" + icono + '\'' +
                ", colorCurso='" + colorCurso + '\'' +
                ", selected=" + selected +
                ", asistenciaUi=" + asistenciaUi +
                ", tipo=" + tipo +
                ", enabled=" + enabled +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return estado != null ? estado.hashCode() : 0;
    }

}
