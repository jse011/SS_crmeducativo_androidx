package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie;

import org.parceler.Parcel;

/**
 * Created by SCIEV on 18/10/2017.
 */

@Parcel
public class CriterioEvalUi {
    String id;
    String Descripcion;
    public boolean edit;

    public CriterioEvalUi() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
