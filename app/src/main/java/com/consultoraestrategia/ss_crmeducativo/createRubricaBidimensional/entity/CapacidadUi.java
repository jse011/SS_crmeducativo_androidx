package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 30/01/2018.
 */
@Parcel
public class CapacidadUi implements Serializable {
    public int id;
    public String title;
    public boolean selected;
    public List<IndicadorUi> indicadorList = new ArrayList<>();
    public long cantidadRubros;

    public CapacidadUi() {
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public List<IndicadorUi> getIndicadorList() {
        return indicadorList;
    }

    public void setIndicadorList(List<IndicadorUi> indicadorList) {
        this.indicadorList = indicadorList;
    }

    public void setCantidadRubros(long cantidadRubros) {
        this.cantidadRubros = cantidadRubros;
    }

    public long getCantidadRubros() {
        return cantidadRubros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CapacidadUi)) return false;

        CapacidadUi that = (CapacidadUi) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
