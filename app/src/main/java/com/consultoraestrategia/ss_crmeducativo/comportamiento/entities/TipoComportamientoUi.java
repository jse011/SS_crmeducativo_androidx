package com.consultoraestrategia.ss_crmeducativo.comportamiento.entities;

public class TipoComportamientoUi {
    private int id;
    private String titulo;
    private String descripcion;
    private boolean selected;
    private int cantidad;
    private int tipoId;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public int getTipoId() {
        return tipoId;
    }
}
