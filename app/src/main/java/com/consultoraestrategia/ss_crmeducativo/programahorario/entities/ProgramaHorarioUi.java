package com.consultoraestrategia.ss_crmeducativo.programahorario.entities;

import java.util.List;

public class ProgramaHorarioUi {
    private String nombre;
    private int id;
    private boolean select;
    private List<DiaUi> diaUiList;

    public ProgramaHorarioUi(String nombre) {
        this.nombre = nombre;
    }

    public ProgramaHorarioUi() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public List<DiaUi> getDiaUiList() {
        return diaUiList;
    }

    public void setDiaUiList(List<DiaUi> diaUiList) {
        this.diaUiList = diaUiList;
    }
}
