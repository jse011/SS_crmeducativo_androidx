package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.seleccionarCompetencias;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by @stevecampos on 6/04/2018.
 */
@Parcel
public class CompetenciaListWrapper implements Serializable {
    public List<CompetenciaUi> items;

    public CompetenciaListWrapper() {
    }

    public CompetenciaListWrapper(List<CompetenciaUi> items) {
        this.items = items;
    }

    public List<CompetenciaUi> getItems() {
        return items;
    }

    public void setItems(List<CompetenciaUi> items) {
        this.items = items;
    }
}
