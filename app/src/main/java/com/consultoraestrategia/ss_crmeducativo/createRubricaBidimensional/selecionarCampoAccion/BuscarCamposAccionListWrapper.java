package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarCampoAccion;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by @stevecampos on 6/04/2018.
 */
@Parcel
public class BuscarCamposAccionListWrapper implements Serializable {
    public List<CampoAccionUi> items;

    public BuscarCamposAccionListWrapper() {
    }

    public BuscarCamposAccionListWrapper(List<CampoAccionUi> items) {
        this.items = items;
    }

    public List<CampoAccionUi> getItems() {
        return items;
    }

    public void setItems(List<CampoAccionUi> items) {
        this.items = items;
    }
}
