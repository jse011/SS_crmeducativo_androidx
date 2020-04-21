package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie;

import java.util.List;

/**
 * Created by SCIEV on 31/10/2017.
 */

public class IndicadorUi {
    int id;
    String titulo;
    String selector;
    List<CamposAccionUi> camposAccionUiList;
    public TipoIndicadorUi tipoIndicadorUi = TipoIndicadorUi.DEFAULT;
    String url;
    public String desempenioDesc;
    public String alias;
    public CharSequence descripcion;

    public IndicadorUi() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public List<CamposAccionUi> getCamposAccionUiList() {
        return camposAccionUiList;
    }

    public void setCamposAccionUiList(List<CamposAccionUi> camposAccionUiList) {
        this.camposAccionUiList = camposAccionUiList;
    }

    public TipoIndicadorUi getTipoIndicadorUi() {
        return tipoIndicadorUi;
    }

    public void setTipoIndicadorUi(TipoIndicadorUi tipoIndicadorUi) {
        this.tipoIndicadorUi = tipoIndicadorUi;
    }

    public String getDesempenioDesc() {
        return desempenioDesc;
    }

    public void setDesempenioDesc(String desempenioDesc) {
        this.desempenioDesc = desempenioDesc;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public CharSequence getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(CharSequence descripcion) {
        this.descripcion = descripcion;
    }
}
