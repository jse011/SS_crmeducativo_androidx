package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.ui.CrearTipoNotaView;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoEscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoSelectorUi;

/**
 * Created by kike on 16/02/2018.
 */

public interface CrearTipoNotaPresenter extends BasePresenter<CrearTipoNotaView> {
    void setExtras(Bundle extras);

 //   void onSelectTipoSelector(TipoSelectorUi tipoSelectorUi);

    //void onSelectTipoEscalaEvaluacon(TipoEscalaEvaluacionUi tipoEscalaEvaluacionUi);

    void onCheckBox(boolean isTrue);

    /*Tipos Selección*/

    void onBnTipoSelectorClicked();

    void onBnTipoEscalaEvaluacionClicked();
}
