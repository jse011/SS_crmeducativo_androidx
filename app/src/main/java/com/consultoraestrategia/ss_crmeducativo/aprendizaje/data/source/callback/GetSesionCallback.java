package com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback;

import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CardSesionUi;

/**
 * Created by SCIEV on 15/01/2018.
 */

public interface GetSesionCallback {
    void onRecursoLoad(CardSesionUi sesionUi);
    void onError(String error);

}
