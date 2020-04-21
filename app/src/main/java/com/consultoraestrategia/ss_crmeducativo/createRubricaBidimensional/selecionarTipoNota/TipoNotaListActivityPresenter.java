package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarTipoNota;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarTipoNota.view.TipoNotaListActivityView;

/**
 * Created by SCIEV on 16/04/2018.
 */

public interface TipoNotaListActivityPresenter extends BasePresenter<TipoNotaListActivityView> {
    void onSelectedTipoNota(String tipoNotaId, String nombre);

    void onBtnCreateClicked();
}
