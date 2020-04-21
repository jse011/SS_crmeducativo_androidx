package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by kike on 10/05/2018.
 */
@Parcel
public class IndicadoresCamposAccionUi {
    public enum TipoIndicador{SABER,SER, DEFAULT, HACER}
    public enum TipoCompetencia{BASE,ENFOQUE, TRANSVERSAL}
    public int id;
    public String titulo;
    public String alias;
    public String descripcion;
    public String estado;
    public String peso;
    public TipoIndicador tipoIndicador = TipoIndicador.DEFAULT;
    public TipoCompetencia tipoCompetencia = TipoCompetencia.BASE;
    public String url;

    public TipoCompetencia getTipoCompetencia() {
        return tipoCompetencia;
    }

    public void setTipoCompetencia(TipoCompetencia tipoCompetencia) {
        this.tipoCompetencia = tipoCompetencia;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<CamposAccionUi> campoAccionList;

    public IndicadoresCamposAccionUi() {
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public List<CamposAccionUi> getCampoAccionList() {
        return campoAccionList;
    }

    public void setCampoAccionList(List<CamposAccionUi> campoAccionList) {
        this.campoAccionList = campoAccionList;
    }

    public TipoIndicador getTipoIndicador() {
        return tipoIndicador;
    }

    public void setTipoIndicador(TipoIndicador tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
    }

    @Override
    public String toString() {
        return "IndicadoresCamposAccionUi{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", alias='" + alias + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", peso='" + peso + '\'' +
                ", campoAccionList=" + campoAccionList +
                '}';
    }
}
