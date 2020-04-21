package com.consultoraestrategia.ss_crmeducativo.programahorario.entities;

import java.util.List;

public class HoraDiaUi {
    private int id;
    private List<HoraUi> horaUiList;
    private int diaId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<HoraUi> getHoraUiList() {
        return horaUiList;
    }

    public void setHoraUiList(List<HoraUi> horaUiList) {
        this.horaUiList = horaUiList;
    }

    public int getDiaId() {
        return diaId;
    }

    public void setDiaId(int diaId) {
        this.diaId = diaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HoraDiaUi)) return false;

        HoraDiaUi horaDiaUi = (HoraDiaUi) o;

        return id == horaDiaUi.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
