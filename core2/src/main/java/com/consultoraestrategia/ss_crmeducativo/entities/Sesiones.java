package com.consultoraestrategia.ss_crmeducativo.entities;


/**
 * Created by irvinmarin on 31/03/2017.
 */


public class Sesiones {
    private int nroSesion;
    private String tituloSesion;
    private String ContenidoSesion;
    private String tareasSesion;
    private int cantHoras;
    private int estadoSesion;

    public Sesiones() {
    }

    public Sesiones(int nroSesion, String tituloSesion, String contenidoSesion, String tareasSesion, int cantHoras, int estadoSesion) {
        this.nroSesion = nroSesion;
        this.tituloSesion = tituloSesion;
        ContenidoSesion = contenidoSesion;
        this.tareasSesion = tareasSesion;
        this.cantHoras = cantHoras;
        this.estadoSesion = estadoSesion;
    }


    public int getNroSesion() {
        return nroSesion;
    }

    public void setNroSesion(int nroSesion) {
        this.nroSesion = nroSesion;
    }

    public String getTituloSesion() {
        return tituloSesion;
    }

    public void setTituloSesion(String tituloSesion) {
        this.tituloSesion = tituloSesion;
    }

    public String getContenidoSesion() {
        return ContenidoSesion;
    }

    public void setContenidoSesion(String contenidoSesion) {
        ContenidoSesion = contenidoSesion;
    }

    public String getTareasSesion() {
        return tareasSesion;
    }

    public void setTareasSesion(String tareasSesion) {
        this.tareasSesion = tareasSesion;
    }

    public int getCantHoras() {
        return cantHoras;
    }

    public void setCantHoras(int cantHoras) {
        this.cantHoras = cantHoras;
    }

    public int getEstadoSesion() {
        return estadoSesion;
    }

    public void setEstadoSesion(int estadoSesion) {
        this.estadoSesion = estadoSesion;
    }
}
