package com.consultoraestrategia.ss_crmeducativo.entities;

/**
 * Created by irvinmarin on 22/03/2017.
 */

public class AnunciosTareas {
    private String nombreAnunciante;
    private String fechaAnuncio;
    private String fechaLimite;
    private String tituloTarea;
    private String detallesTarea;

    public AnunciosTareas() {
    }

    public AnunciosTareas(String nombreAnunciante, String fechaAnuncio, String fechaLimite, String tituloTarea, String detallesTarea) {
        this.nombreAnunciante = nombreAnunciante;
        this.fechaAnuncio = fechaAnuncio;
        this.fechaLimite = fechaLimite;
        this.tituloTarea = tituloTarea;
        this.detallesTarea = detallesTarea;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

    public String getFechaAnuncio() {
        return fechaAnuncio;
    }

    public void setFechaAnuncio(String fechaAnuncio) {
        this.fechaAnuncio = fechaAnuncio;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getTituloTarea() {
        return tituloTarea;
    }

    public void setTituloTarea(String tituloTarea) {
        this.tituloTarea = tituloTarea;
    }

    public String getDetallesTarea() {
        return detallesTarea;
    }

    public void setDetallesTarea(String detallesTarea) {
        this.detallesTarea = detallesTarea;
    }
}
