package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.campoAccionLista;

import java.io.Serializable;
import java.util.List;

/**
 * Created by @stevecampos on 6/04/2018.
 */

public class CamposAccionListWrapper implements Serializable {
    public List<Object> items;

    public CamposAccionListWrapper() {
    }

    public CamposAccionListWrapper(List<Object> items) {
        this.items = items;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }
}
