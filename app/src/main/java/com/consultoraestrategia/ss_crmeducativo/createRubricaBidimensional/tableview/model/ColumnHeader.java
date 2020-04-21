package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model;

import androidx.annotation.ColorRes;

import org.parceler.Parcel;

/**
 * Created by @stevecampos on 9/02/2018.
 */

@Parcel
public class ColumnHeader extends Cell {

    public String subtitle;
    public @ColorRes
    int backgroundColor;
    public @ColorRes
    int textColor;

    public ColumnHeader() {
    }

    public ColumnHeader(String id, String title, String subtitle) {
        super(id, title);
        this.subtitle = subtitle;
    }

    public ColumnHeader(String id, String title, String subtitle, @ColorRes int backgroundColor, int textColor) {
        super(id, title);
        this.subtitle = subtitle;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(@ColorRes int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}

