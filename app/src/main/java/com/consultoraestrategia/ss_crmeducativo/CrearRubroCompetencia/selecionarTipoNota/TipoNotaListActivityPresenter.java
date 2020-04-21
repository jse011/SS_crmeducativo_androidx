package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.selecionarTipoNota;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.selecionarTipoNota.view.TipoNotaListActivityView;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;

/**
 * Created by SCIEV on 16/04/2018.
 */

public interface TipoNotaListActivityPresenter extends BasePresenter<TipoNotaListActivityView> {
    void onSelectedTipoNota(String tipoNotaId, String nombre);

    void onBtnCreateClicked();
}
