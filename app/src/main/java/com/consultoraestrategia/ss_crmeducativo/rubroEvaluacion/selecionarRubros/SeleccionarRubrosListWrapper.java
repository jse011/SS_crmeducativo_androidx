package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;

import org.parceler.Parcel;

/**
 * Created by Jse on 02/08/2018.
 */
@Parcel
public class SeleccionarRubrosListWrapper {
    public CapacidadUi capacidadUi;

    public SeleccionarRubrosListWrapper() {
    }

    public SeleccionarRubrosListWrapper(CapacidadUi capacidadUi) {
        this.capacidadUi = capacidadUi;
    }

    public CapacidadUi getCapacidadUi() {
        return capacidadUi;
    }

    public void setCapacidadUi(CapacidadUi capacidadUi) {
        this.capacidadUi = capacidadUi;
    }
}
