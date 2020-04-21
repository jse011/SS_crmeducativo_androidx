package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.intitiesUI;

import com.raizlabs.android.dbflow.annotation.Column;

import java.io.Serializable;

/**
 * Created by irvinmarin on 09/08/2018.
 */

public class MensajePredeterminadoUI implements Serializable {
    private String keyMensajePredeterminado;
    private String asuntoMensaje;
    private String cabeceraMensaje;
    private String presentacionMensaje;
    private String cuerpoMensaje;
    private String despedidaMensaje;
    private String contenidoJoin;
    private String tipoMensaje;
    private int alcance;
    private int objetivo;
    private boolean deleted;

    public MensajePredeterminadoUI() {
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public MensajePredeterminadoUI(String keyMensajePredeterminado, String asuntoMensaje, String cabeceraMensaje, String presentacionMensaje, String cuerpoMensaje, String despedidaMensaje, String contenidoJoin, String tipoMensaje, int alcance, int objetivo, boolean deleted) {
        this.keyMensajePredeterminado = keyMensajePredeterminado;
        this.asuntoMensaje = asuntoMensaje;
        this.cabeceraMensaje = cabeceraMensaje;
        this.presentacionMensaje = presentacionMensaje;
        this.cuerpoMensaje = cuerpoMensaje;
        this.despedidaMensaje = despedidaMensaje;
        this.contenidoJoin = contenidoJoin;
        this.tipoMensaje = tipoMensaje;
        this.alcance = alcance;
        this.objetivo = objetivo;
        this.deleted = deleted;
    }

    public String getCabeceraMensaje() {
        return cabeceraMensaje;
    }

    public void setCabeceraMensaje(String cabeceraMensaje) {
        this.cabeceraMensaje = cabeceraMensaje;
    }

    public String getPresentacionMensaje() {
        return presentacionMensaje;
    }

    public void setPresentacionMensaje(String presentacionMensaje) {
        this.presentacionMensaje = presentacionMensaje;
    }

    public String getCuerpoMensaje() {
        return cuerpoMensaje;
    }

    public void setCuerpoMensaje(String cuerpoMensaje) {
        this.cuerpoMensaje = cuerpoMensaje;
    }

    public String getDespedidaMensaje() {
        return despedidaMensaje;
    }

    public void setDespedidaMensaje(String despedidaMensaje) {
        this.despedidaMensaje = despedidaMensaje;
    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public int getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(int objetivo) {
        this.objetivo = objetivo;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getKeyMensajePredeterminado() {
        return keyMensajePredeterminado;
    }

    public void setKeyMensajePredeterminado(String keyMensajePredeterminado) {
        this.keyMensajePredeterminado = keyMensajePredeterminado;
    }

    public String getAsuntoMensaje() {
        return asuntoMensaje;
    }

    public void setAsuntoMensaje(String asuntoMensaje) {
        this.asuntoMensaje = asuntoMensaje;
    }

    public String getContenidoJoin() {
        return contenidoJoin;
    }

    public void setContenidoJoin(String contenidoJoin) {
        this.contenidoJoin = contenidoJoin;
    }

    @Override
    public String toString() {
        return "MensajePredeterminadoUI{" +
                "keyMensajePredeterminado='" + keyMensajePredeterminado + '\'' +
                ", asuntoMensaje='" + asuntoMensaje + '\'' +
                ", cabeceraMensaje='" + cabeceraMensaje + '\'' +
                ", presentacionMensaje='" + presentacionMensaje + '\'' +
                ", cuerpoMensaje='" + cuerpoMensaje + '\'' +
                ", despedidaMensaje='" + despedidaMensaje + '\'' +
                ", contenidoJoin='" + contenidoJoin + '\'' +
                ", tipoMensaje='" + tipoMensaje + '\'' +
                ", alcance=" + alcance +
                ", objetivo=" + objetivo +
                '}';
    }
}
