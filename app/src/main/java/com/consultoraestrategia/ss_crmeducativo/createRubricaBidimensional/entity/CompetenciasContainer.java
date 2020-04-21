package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by @stevecampos on 6/02/2018.
 */
@Parcel
public class CompetenciasContainer implements Serializable {
    List<CompetenciaUi> competenciaList;

    public CompetenciasContainer() {
    }

    public CompetenciasContainer(List<CompetenciaUi> competenciaList) {
        this.competenciaList = competenciaList;
    }

    public List<CompetenciaUi> getCompetenciaList() {
        return competenciaList;
    }

    public void setCompetenciaList(List<CompetenciaUi> competenciaList) {
        this.competenciaList = competenciaList;
    }
}
