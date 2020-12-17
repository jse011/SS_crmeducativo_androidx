package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities;

import java.util.ArrayList;
import java.util.List;

public class RowTableRegEvalUi {

    private String nombre;
    private String foto;
    private boolean vigencia;
    private int personaId;
    private List<CellTableRegEvalUi> evaluacionUiList = new ArrayList<>();
    private String apellidos;


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public boolean getVigencia() {
        return vigencia;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public List<CellTableRegEvalUi> getEvaluacionUiList() {
        return evaluacionUiList;
    }

    public void setEvaluacionUiList(List<CellTableRegEvalUi> evaluacionUiList) {
        this.evaluacionUiList = evaluacionUiList;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }
}
