package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 6/02/2018.
 */

public class RubroProcesoUi {

    private int id;
    private String Titulo;
    private int posicion;
    private boolean check;
    private String fecha;
    private String media;

    private List<RubroProcesoUi> rubroProcesoUis;

    public RubroProcesoUi() {
        this.rubroProcesoUis = new ArrayList<>();
    }

    public List<RubroProcesoUi> getRubroProcesoUis() {
        return rubroProcesoUis;
    }

    public void setRubroProcesoUis(List<RubroProcesoUi> rubroProcesoUis) {
        this.rubroProcesoUis = rubroProcesoUis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

}
