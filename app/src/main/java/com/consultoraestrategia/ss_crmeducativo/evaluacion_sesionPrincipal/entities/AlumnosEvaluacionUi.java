package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities;

import com.consultoraestrategia.ss_crmeducativo.base.activity.View;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi.BodyCellViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 9/10/2017.
 */

public class AlumnosEvaluacionUi extends BodyCellViewUi {

    int Id;
    String name;
    String lastName;
    String FotoPerfil;
    List<NotaUi> notaUis;
    NotaUi notaUi;
    boolean togle;
    String grupoId;
    GrupoEvaluacionUi parent;
    boolean vigente;
    private DimensionObservadaUi dimensionObservadaUi;

    public AlumnosEvaluacionUi() {
        notaUis = new ArrayList<>();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFotoPerfil() {
        return FotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        FotoPerfil = fotoPerfil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<NotaUi> getNotaUis() {
        return notaUis;
    }

    public void setNotaUis(List<NotaUi> notaUis) {
        this.notaUis = notaUis;
    }

    public NotaUi getNotaUi() {
        return notaUi;
    }

    public void setNotaUi(NotaUi notaUi) {
        this.notaUi = notaUi;
    }

    public boolean isTogle() {
        return togle;
    }

    public void setTogle(boolean togle) {
        this.togle = togle;
    }

    public String getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(String grupoId) {
        this.grupoId = grupoId;
    }

    public GrupoEvaluacionUi getParent() {
        return parent;
    }

    public void setParent(GrupoEvaluacionUi parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlumnosEvaluacionUi that = (AlumnosEvaluacionUi) o;

        return Id == that.Id;
    }

    @Override
    public int hashCode() {
        return Id;
    }

    public DimensionObservadaUi getDimensionObservadaUi() {
        return dimensionObservadaUi;
    }

    public void setDimensionObservadaUi(DimensionObservadaUi dimensionObservadaUi) {
        this.dimensionObservadaUi = dimensionObservadaUi;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
}
