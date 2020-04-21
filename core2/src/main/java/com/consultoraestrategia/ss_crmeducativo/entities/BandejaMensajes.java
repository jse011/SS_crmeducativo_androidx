package com.consultoraestrategia.ss_crmeducativo.entities;

/**
 * Created by irvinmarin on 15/08/2017.
 */

public class BandejaMensajes {
    private int idBandejaMensaje;
    private String remitente;
    private String asunto;
    private String descripcion;
    private String fecha;
    private int tipoMensaje;

    public BandejaMensajes() {
    }

    public BandejaMensajes(int idBandejaMensaje, String remitente, String asunto, String descripcion, String fecha, int tipoMensaje) {
        this.idBandejaMensaje = idBandejaMensaje;
        this.remitente = remitente;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.tipoMensaje = tipoMensaje;
    }

    public int getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(int tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public int getIdBandejaMensaje() {
        return idBandejaMensaje;
    }

    public void setIdBandejaMensaje(int idBandejaMensaje) {
        this.idBandejaMensaje = idBandejaMensaje;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
