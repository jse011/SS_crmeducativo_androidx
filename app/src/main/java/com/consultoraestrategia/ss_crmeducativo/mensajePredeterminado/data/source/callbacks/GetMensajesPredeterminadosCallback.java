package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.callbacks;

import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.intitiesUI.MensajePredeterminadoUI;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public interface GetMensajesPredeterminadosCallback {
    void onGetMensajePredeterniandoLoaded(List<MensajePredeterminadoUI> mensajePredeterminadoUIList);

    void onError(String error);

}
