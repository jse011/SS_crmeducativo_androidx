package com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities;

import java.util.ArrayList;
import java.util.List;

public class CompetenciaUi {


    private int competenciaId;
    private String nombre;
    private List<CapacidadUi> capacidadUis = new ArrayList<>();

    public CompetenciaUi() {
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CapacidadUi> getCapacidadUis() {
        return capacidadUis;
    }

    public void setCapacidadUis(List<CapacidadUi> capacidadUis) {
        this.capacidadUis = capacidadUis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompetenciaUi that = (CompetenciaUi) o;

        return competenciaId == that.competenciaId;
    }

    @Override
    public int hashCode() {
        return competenciaId;
    }
}
