package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarTipoNota.view;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarTipoNota.TipoNotaListActivityPresenter;

/**
 * Created by SCIEV on 16/04/2018.
 */

public interface TipoNotaListActivityView extends BaseView<TipoNotaListActivityPresenter> {
    void onSucces(String tipoNotaId);
}
