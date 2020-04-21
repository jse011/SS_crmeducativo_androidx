package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 6/02/2018.
 */

public class CapacidadUi {
    private int id;
    private boolean toogle;
    private String title;
    private List<RubroProcesoUi> rubroProcesoUis;
    private int cantidad;
    private int posicion;
    private CompetenciaUi competenciaUi;

    public CapacidadUi() {
        this.rubroProcesoUis = new ArrayList<>();
    }

    public CapacidadUi(int id) {
        this.id = id;
        this.rubroProcesoUis = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isToogle() {
        return toogle;
    }

    public void setToogle(boolean toogle) {
        this.toogle = toogle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<RubroProcesoUi> getRubroProcesoUis() {
        return rubroProcesoUis;
    }

    public void setRubroProcesoUis(List<RubroProcesoUi> rubroProcesoUis) {
        this.rubroProcesoUis = rubroProcesoUis;
    }

    public void addRubroProceso(RubroProcesoUi rubroProcesoUi){
        this.rubroProcesoUis.add(rubroProcesoUi);
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public CompetenciaUi getCompetenciaUi() {
        return competenciaUi;
    }

    public void setCompetenciaUi(CompetenciaUi competenciaUi) {
        this.competenciaUi = competenciaUi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CapacidadUi that = (CapacidadUi) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
