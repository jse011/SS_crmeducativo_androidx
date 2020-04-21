package com.consultoraestrategia.ss_crmeducativo.infoRubro.entities;

import java.util.List;

/**
 * Created by SCIEV on 28/08/2018.
 */

public class RubroEvalProcesoUi {
    private String id;
    private String titulo;
    private CapacidadUi capacidadUi;
    private DesempenioUi desempenioUi;
    private List<CampoTematicoUi> campoTematicoUiList;

    private TipoNotaUi tipoNotaUi;
    private String subtitulo;

    public TipoNotaUi getTipoNotaUi() {
        return tipoNotaUi;
    }

    public void setTipoNotaUi(TipoNotaUi tipoNotaUi) {
        this.tipoNotaUi = tipoNotaUi;
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

    public CapacidadUi getCapacidadUi() {
        return capacidadUi;
    }

    public void setCapacidadUi(CapacidadUi capacidadUi) {
        this.capacidadUi = capacidadUi;
    }

    public DesempenioUi getDesempenioUi() {
        return desempenioUi;
    }

    public void setDesempenioUi(DesempenioUi desempenioUi) {
        this.desempenioUi = desempenioUi;
    }

    public List<CampoTematicoUi> getCampoTematicoUiList() {
        return campoTematicoUiList;
    }

    public void setCampoTematicoUiList(List<CampoTematicoUi> campoTematicoUiList) {
        this.campoTematicoUiList = campoTematicoUiList;
    }

    @Override
    public String toString() {
        return "RubroEvalProcesoUi{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", capacidadUi=" + capacidadUi +
                ", desempenioUi=" + desempenioUi +
                ", campoTematicoUiList=" + campoTematicoUiList +
                ", tipoNotaUi=" + tipoNotaUi +
                '}';
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }
}
