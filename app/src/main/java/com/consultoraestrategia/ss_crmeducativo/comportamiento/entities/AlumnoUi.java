package com.consultoraestrategia.ss_crmeducativo.comportamiento.entities;

import java.io.Serializable;
import java.util.List;

public class AlumnoUi {
    private int id;
    private List<TipoUi>tipoUiList;
    private String nombre;
    private String lastName;
    private String urlProfile;
    private String username;
    private List<ComportamientoUi > comportamientoUiList;
    private int cargacursoId;
    private CursoUi cursoUi;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TipoUi> getTipoUiList() {
        return tipoUiList;
    }

    public void setTipoUiList(List<TipoUi> tipoUiList) {
        this.tipoUiList = tipoUiList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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



    public List<ComportamientoUi> getComportamientoUiList() {
        return comportamientoUiList;
    }

    public void setComportamientoUiList(List<ComportamientoUi> comportamientoUiList) {
        this.comportamientoUiList = comportamientoUiList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCargacursoId() {
        return cargacursoId;
    }

    public void setCargacursoId(int cargacursoId) {
        this.cargacursoId = cargacursoId;
    }

    public CursoUi getCursoUi() {
        return cursoUi;
    }

    public void setCursoUi(CursoUi cursoUi) {
        this.cursoUi = cursoUi;
    }

    @Override
    public String toString() {
        return  nombre+ " " + lastName;
    }
}
