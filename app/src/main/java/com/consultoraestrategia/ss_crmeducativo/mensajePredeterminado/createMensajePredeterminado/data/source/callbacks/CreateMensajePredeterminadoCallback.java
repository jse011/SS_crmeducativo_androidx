package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source.callbacks;

import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.intitiesUI.MensajePredeterminadoUI;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public interface CreateMensajePredeterminadoCallback {
    void onCreateMensPredLoaded(boolean success);

    void onError(String error);

}
