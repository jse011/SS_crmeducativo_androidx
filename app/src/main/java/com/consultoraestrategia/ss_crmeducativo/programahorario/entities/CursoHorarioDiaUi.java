package com.consultoraestrategia.ss_crmeducativo.programahorario.entities;

import java.util.List;

public class CursoHorarioDiaUi {

    private List<HoraDiaUi> horaDiaUiList;
    private CursoUi cursoUi;

    public List<HoraDiaUi> getHoraDiaUiList() {
        return horaDiaUiList;
    }

    public void setHoraDiaUiList(List<HoraDiaUi> horaDiaUiList) {
        this.horaDiaUiList = horaDiaUiList;
    }

    public CursoUi getCursoUi() {
        return cursoUi;
    }

    public void setCursoUi(CursoUi cursoUi) {
        this.cursoUi = cursoUi;
    }
}
