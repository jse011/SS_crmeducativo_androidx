package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista;

import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.view.TipoNotaListaView;

/**
 * Created by SCIEV on 26/02/2018.
 */

public interface TipoNotaListaPresenter extends BaseFragmentPresenter<TipoNotaListaView> {
    void onClickTipoNota(TipoNotaUi tipoNotaUi);

    void onCancelar();
    void onAceptar();

    void onClickRefresh();

    void onClickImportar();
}
