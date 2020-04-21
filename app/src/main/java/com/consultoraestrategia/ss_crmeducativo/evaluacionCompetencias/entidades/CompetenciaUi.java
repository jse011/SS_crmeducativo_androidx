package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades;

import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;

import java.util.List;

/**
 * Created by CCIE on 09/03/2018.
 */

public class CompetenciaUi {
    private int competenciaId;
    private int superComeptenciaId;
    private String contador;
    private String nombre;
    private List<CapacidadUi> capacidadUiList;
    private boolean stado;
    private ParametroDisenioUi parametroDisenioUi;
    private int cargaCursoId;



    public CompetenciaUi() {
    }

    public CompetenciaUi(String contador, String nombre, boolean stado) {
        this.contador = contador;
        this.nombre = nombre;
        this.stado = stado;
    }

    public ParametroDisenioUi getParametroDisenioUi() {
        return parametroDisenioUi;
    }

    public void setParametroDisenioUi(ParametroDisenioUi parametroDisenioUi) {
        this.parametroDisenioUi = parametroDisenioUi;
    }

    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CapacidadUi> getCapacidadUiList() {
        return capacidadUiList;
    }

    public void setCapacidadUiList(List<CapacidadUi> capacidadUiList) {
        this.capacidadUiList = capacidadUiList;
    }

    public boolean isStado() {
        return stado;
    }

    public void setStado(boolean stado) {
        this.stado = stado;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    public int getSuperComeptenciaId() {
        return superComeptenciaId;
    }

    public void setSuperComeptenciaId(int superComeptenciaId) {
        this.superComeptenciaId = superComeptenciaId;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }
}

