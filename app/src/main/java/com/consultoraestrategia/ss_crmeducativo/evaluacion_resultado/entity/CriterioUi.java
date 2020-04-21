package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity;

import org.parceler.Parcel;

/**
 * Created by @stevecampos on 23/10/2017.
 */
@Parcel
public class CriterioUi {
    int id;
    String description;

    public CriterioUi() {
    }


    public CriterioUi(int id, String description) {
        this.id = id;

        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
