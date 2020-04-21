package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities;

import org.parceler.Parcel;

/**
 * Created by SCIEV on 30/10/2017.
 */
@Parcel
public class IndicadorUi {

    int id;
    String Title;
    String Selector;
    public String alias;


    public IndicadorUi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSelector() {
        return Selector;
    }

    public void setSelector(String selector) {
        Selector = selector;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
