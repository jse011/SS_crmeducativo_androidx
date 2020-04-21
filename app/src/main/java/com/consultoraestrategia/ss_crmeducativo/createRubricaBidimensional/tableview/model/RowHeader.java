package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model;

import org.parceler.Parcel;

/**
 * Created by @stevecampos on 9/02/2018.
 */
@Parcel
public class RowHeader extends Cell {

    public String boldText;

    public RowHeader() {
    }

    public RowHeader(String id, String title, String boldText) {
        super(id, title);
        this.boldText = boldText;
    }

    public String getBoldText() {
        return boldText;
    }

    public void setBoldText(String boldText) {
        this.boldText = boldText;
    }
}
