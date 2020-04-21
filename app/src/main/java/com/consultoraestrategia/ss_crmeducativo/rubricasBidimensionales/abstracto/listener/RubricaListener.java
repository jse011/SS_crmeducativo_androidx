package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.listener;

import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

/**
 * Created by CCIE on 08/03/2018.
 */

public interface RubricaListener {
    void onItemSelected(RubBidUi item);
    void onOpciones(RubBidUi rubBidUi,View itemview);
}
