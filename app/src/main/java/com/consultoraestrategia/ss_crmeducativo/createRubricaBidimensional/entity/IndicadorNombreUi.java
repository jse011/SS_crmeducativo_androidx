package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.Cell;

import org.parceler.Parcel;

import java.io.Serializable;
@Parcel
public class IndicadorNombreUi extends Cell implements Serializable {
    public IndicadorUi indicadorUi;

    public IndicadorNombreUi() {
    }

    public IndicadorNombreUi(IndicadorUi indicadorUi) {
        this.indicadorUi = indicadorUi;
    }

    public IndicadorUi getIndicadorUi() {
        return indicadorUi;
    }

    public void setIndicadorUi(IndicadorUi indicadorUi) {
        this.indicadorUi = indicadorUi;
    }
}
