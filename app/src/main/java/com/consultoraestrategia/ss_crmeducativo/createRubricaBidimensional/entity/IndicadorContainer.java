package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by @stevecampos on 8/02/2018.
 */
@Parcel
public class IndicadorContainer implements Serializable {
    public List<IndicadorUi> indicadorList;

    public IndicadorContainer() {
    }

    public IndicadorContainer(List<IndicadorUi> indicadorList) {
        this.indicadorList = indicadorList;
    }

    public List<IndicadorUi> getIndicadorList() {
        return indicadorList;
    }

    public void setIndicadorList(List<IndicadorUi> indicadorList) {
        this.indicadorList = indicadorList;
    }
}
