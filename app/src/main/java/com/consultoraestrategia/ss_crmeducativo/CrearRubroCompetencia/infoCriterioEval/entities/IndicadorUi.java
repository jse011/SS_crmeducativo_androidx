package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class IndicadorUi implements Serializable {

    int id;
    String titulo;
    String descripcion;
    String subtitulo;
    String url;
    TipoIndicadorUi tipoIndicador= TipoIndicadorUi.DEFAULT;
    public IndicadorUi() {
    }

    public IndicadorUi(int id, String titulo, String descripcion, String subtitulo) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.subtitulo = subtitulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TipoIndicadorUi getTipoIndicador() {
        return tipoIndicador;
    }

    public void setTipoIndicador(TipoIndicadorUi tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

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

    @Override
    public String toString() {
        return "IndicadorUi{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", subtitulo='" + subtitulo + '\'' +
                '}';
    }
}