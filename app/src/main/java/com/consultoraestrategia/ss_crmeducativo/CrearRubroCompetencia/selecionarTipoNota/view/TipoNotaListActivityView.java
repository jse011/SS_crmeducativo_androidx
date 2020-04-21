package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.selecionarTipoNota.view;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.selecionarTipoNota.TipoNotaListActivityPresenter;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;

/**
 * Created by SCIEV on 16/04/2018.
 */

public interface TipoNotaListActivityView extends BaseView<TipoNotaListActivityPresenter> {
    void onSucces(String tipoNotaId);
}
