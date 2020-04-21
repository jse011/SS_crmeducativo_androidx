package com.consultoraestrategia.ss_crmeducativo.main.entities;

import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.DiaUi;

import java.util.List;

/**
 * Created by irvinmarin on 16/10/2017.
 */

public class ProgramaEduactivosUI {
    private int idPrograma;
    private String nombrePrograma;
    private List<DiaUi> diaUiList;
    private boolean seleccionado;

    public ProgramaEduactivosUI(int idPrograma, String nombrePrograma) {
        this.idPrograma = idPrograma;
        this.nombrePrograma = nombrePrograma;
    }

    public ProgramaEduactivosUI() {
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    @Override
    public String toString() {
        return "ProgramaEduactivosUI{" +
                "idPrograma=" + idPrograma +
                ", nombrePrograma='" + nombrePrograma + '\'' +
                '}';
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public List<DiaUi> getDiaUiList() {
        return diaUiList;
    }

    public void setDiaUiList(List<DiaUi> diaUiList) {
        this.diaUiList = diaUiList;
    }
}
