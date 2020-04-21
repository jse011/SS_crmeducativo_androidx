package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaColumnaCabecera;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kike on 11/05/2018.
 */

public class AlumnosUi extends FormulaColumnaCabecera {

    public String key;
    public String name;
    public String lastName;
    public String urlProfile;
    private boolean vigente;
    public List<NotaUi> notaUiList = new ArrayList<>();

    public AlumnosUi() {
    }

    public AlumnosUi(String key) {
        this.key = key;
    }

    public AlumnosUi(String key, String name, String lastName, String urlProfile) {
        this.key = key;
        this.name = name;
        this.lastName = lastName;
        this.urlProfile = urlProfile;
    }

    public AlumnosUi(String key, String name, String lastName, String urlProfile, List<NotaUi> notaUiList) {
        this.key = key;
        this.name = name;
        this.lastName = lastName;
        this.urlProfile = urlProfile;
        this.notaUiList.addAll(notaUiList);
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public List<NotaUi> getNotaUiList() {
        return notaUiList;
    }

    public void setNotaUiList(List<NotaUi> notaUiList) {
        this.notaUiList = notaUiList;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
}
