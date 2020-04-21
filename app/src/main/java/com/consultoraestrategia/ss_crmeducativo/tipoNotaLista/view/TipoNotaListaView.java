package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.view;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.TipoNotaListaPresenter;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi;

import java.util.List;

/**
 * Created by SCIEV on 26/02/2018.
 */

public interface TipoNotaListaView extends BaseView<TipoNotaListaPresenter> {
    void onShowList(List<TipoNotaUi> tipoNotaUiList);
    void succesTipoNota(int tipoNotaId, String nombre);
    void succesTipoNota(String tipoNotaId, String nombre);
    void updateTipoNota(TipoNotaUi tipoNotaUi);
    void showActivityImportarTipoNota(BEVariables beVariables);
}
