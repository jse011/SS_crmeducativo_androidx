package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 31/10/2017.
 */

public class IndicadorUi extends com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.IndicadorUi {
    int id;
    String titulo;
    String selector;
    List<CampotematicoUi> campotematicoUis = new ArrayList<>();
    CapacidadUi capacidadUi;
    boolean seleccionado;
    private String url;

    public IndicadorUi(int id, String titulo, String selector, List<CampotematicoUi> campotematicoUis, boolean seleccionado) {
        this.id = id;
        this.titulo = titulo;
        this.selector = selector;
        this.campotematicoUis = campotematicoUis;
        this.seleccionado = seleccionado;
    }

    public IndicadorUi() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
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

    public List<CampotematicoUi> getCampotematicoUis() {
        return campotematicoUis;
    }

    public void setCampotematicoUis(List<CampotematicoUi> campotematicoUis) {
        this.campotematicoUis = campotematicoUis;
    }

    public CapacidadUi getCapacidadUi() {
        return capacidadUi;
    }

    public void setCapacidadUi(CapacidadUi capacidadUi) {
        this.capacidadUi = capacidadUi;
    }

}
