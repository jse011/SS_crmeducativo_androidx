package com.consultoraestrategia.ss_crmeducativo.stiker2.entities;

import java.util.ArrayList;
import java.util.List;

public class CategoriaUi {
    private List<StikersUi> stikersUiList = new ArrayList<>();
    private String id;
    private String nombre;
    private String tipo;

    public List<StikersUi> getStikersUiList() {
        return stikersUiList;
    }

    public void setStikersUiList(List<StikersUi> stikersUiList) {
        this.stikersUiList = stikersUiList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
