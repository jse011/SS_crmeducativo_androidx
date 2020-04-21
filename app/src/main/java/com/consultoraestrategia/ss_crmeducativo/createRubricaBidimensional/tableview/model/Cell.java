package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model;


import org.parceler.Parcel;

import java.io.Serializable;

/**
 * Created by @stevecampos on 9/02/2018.
 */
@Parcel
public class Cell implements Serializable {

    public String id;
    public String title;
    public boolean selected;
    public int bgColor;

    public Cell() {
    }

    public Cell(String id) {
        this.id = id;
    }

    public Cell(String id, String title) {
        this.id = id;
        this.title = title;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }
}

