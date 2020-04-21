package com.consultoraestrategia.ss_crmeducativo.situacion.entity;


/**
 * Created by @stevecampos on 23/02/2018.
 */


public class SituacionUI {

    public final static int TIPO_DESAFIO = 0;
    public final static int TIPO_RETO = 1;

    private int idUnidadAprendizaje;
    private String tipo;
    private String descripcionTipo;
    private String subtitulo;
    private String descripcionSubtitulo;
    private int tipoSubTitulo;
    private boolean ocultarSecondItem;

    public SituacionUI() {
    }

    public int getTipoSubTitulo() {
        return tipoSubTitulo;
    }

    public void setTipoSubTitulo(int tipoSubTitulo) {
        this.tipoSubTitulo = tipoSubTitulo;
    }

    public boolean isOcultarSecondItem() {
        return ocultarSecondItem;
    }

    public void setOcultarSecondItem(boolean ocultarSecondItem) {
        this.ocultarSecondItem = ocultarSecondItem;
    }

    public int getIdUnidadAprendizaje() {
        return idUnidadAprendizaje;
    }

    public void setIdUnidadAprendizaje(int idUnidadAprendizaje) {
        this.idUnidadAprendizaje = idUnidadAprendizaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDescripcionSubtitulo() {
        return descripcionSubtitulo;
    }

    public void setDescripcionSubtitulo(String descripcionSubtitulo) {
        this.descripcionSubtitulo = descripcionSubtitulo;
    }
}
