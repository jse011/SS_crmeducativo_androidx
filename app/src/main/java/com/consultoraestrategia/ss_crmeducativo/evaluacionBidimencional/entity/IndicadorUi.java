package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity;

/**
 * Created by SCIEV on 10/04/2018.
 */

public class IndicadorUi {
    private String id;
    private String titulo;
    private String subtitulo;
    private String descripcion;
    private String CamposTemaicos;
    private String desempenioDesc;
    private String capacidadDesc;
    private String competenciaDesc;
    private TipoIndicadorUi tipoIndicadorUi = TipoIndicadorUi.DEFAULT;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCamposTemaicos() {
        return CamposTemaicos;
    }

    public void setCamposTemaicos(String camposTemaicos) {
        CamposTemaicos = camposTemaicos;
    }

    public String getDesempenioDesc() {
        return desempenioDesc;
    }

    public void setDesempenioDesc(String desempenioDesc) {
        this.desempenioDesc = desempenioDesc;
    }

    public void setCapacidadDesc(String capacidadDesc) {
        this.capacidadDesc = capacidadDesc;
    }

    public String getCapacidadDesc() {
        return capacidadDesc;
    }

    public void setCompetenciaDesc(String competenciaDesc) {
        this.competenciaDesc = competenciaDesc;
    }

    public String getCompetenciaDesc() {
        return competenciaDesc;
    }

    public TipoIndicadorUi getTipoIndicadorUi() {
        return tipoIndicadorUi;
    }

    public void setTipoIndicadorUi(TipoIndicadorUi tipoIndicadorUi) {
        this.tipoIndicadorUi = tipoIndicadorUi;
    }
}
