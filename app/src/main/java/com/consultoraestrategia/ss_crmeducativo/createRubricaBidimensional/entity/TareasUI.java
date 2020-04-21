package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

public class TareasUI {
    private String descripcion;
    private long fechaCreacionTarea;
    private String tituloTarea;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setFechaCreacionTarea(long fechaCreacionTarea) {
        this.fechaCreacionTarea = fechaCreacionTarea;
    }

    public long getFechaCreacionTarea() {
        return fechaCreacionTarea;
    }

    public void setTituloTarea(String tituloTarea) {
        this.tituloTarea = tituloTarea;
    }

    public String getTituloTarea() {
        return tituloTarea;
    }
}
