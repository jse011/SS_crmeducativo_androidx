package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities;

public class SentimientoUi {
    private int position;
    private String nombre;
    private String descripcion;
    private int plantilla;
    private String imagen;
    private String foto;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(int plantilla) {
        this.plantilla = plantilla;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SentimientoUi that = (SentimientoUi) o;

        return position == that.position;
    }

    @Override
    public int hashCode() {
        return position;
    }
}
