package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 26/01/2018.
 */
@Parcel
public class CompetenciaUi implements Serializable {
    public int id;
    public String title;
    public boolean selected;
    public int tipoId;
    public List<CapacidadUi> capacidadList = new ArrayList<>();
    public String color1;

    public CompetenciaUi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CapacidadUi> getCapacidadList() {
        return capacidadList;
    }

    public void setCapacidadList(List<CapacidadUi> capacidadList) {
        this.capacidadList = capacidadList;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public static final int COMPETENCIA_BASE = 347;
    public static final int COMPETENCIA_TRANS = 348;
    public static final int COMPETENCIA_ENFQ = 349;

    public boolean isBase() {
        return tipoId == COMPETENCIA_BASE;
    }

    public boolean isTrans() {
        return tipoId == COMPETENCIA_TRANS;
    }

    public boolean isEnfoque() {
        return tipoId == COMPETENCIA_ENFQ;
    }


    @Override
    public String toString() {
        return title;
    }

    public List<Object> getItems() {
        List<Object> items = new ArrayList<>();
        List<CapacidadUi> capacidadList = getCapacidadList();
        for (CapacidadUi capacidad :
                capacidadList) {
            if (capacidad != null) {
                items.add(capacidad);
                List<IndicadorUi> indicadorList = capacidad.getIndicadorList();
                for (IndicadorUi indicador :
                        indicadorList) {
                    if (indicador != null) {
                        items.add(indicador);
                    }
                }
            }
        }
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompetenciaUi that = (CompetenciaUi) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }
}
