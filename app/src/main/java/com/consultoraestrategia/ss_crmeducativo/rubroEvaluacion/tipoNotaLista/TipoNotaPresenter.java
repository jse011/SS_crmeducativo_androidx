package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.tipoNotaLista;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;

/**
 * Created by CCIE on 07/05/2018.
 */

public interface TipoNotaPresenter extends BasePresenter<TipoNotaView>{
    void onSelectedTipoNota(String tipoNotaId, String nombre);
}
