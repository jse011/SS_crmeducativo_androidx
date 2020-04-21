package com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities;

import java.util.List;

public class CapacidadUi {

    private String nombre;
    private int competenciaId;
    private int capacidadId;
    private int tipoId;
    private List<RubricaUi>rubricaUis;

    public CapacidadUi() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    public int getCapacidadId() {
        return capacidadId;
    }

    public void setCapacidadId(int capacidadId) {
        this.capacidadId = capacidadId;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public List<RubricaUi> getRubricaUis() {
        return rubricaUis;
    }

    public void setRubricaUis(List<RubricaUi> rubricaUis) {
        this.rubricaUis = rubricaUis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CapacidadUi that = (CapacidadUi) o;

        return capacidadId == that.capacidadId;
    }

    @Override
    public int hashCode() {
        return capacidadId;
    }
}
