package com.consultoraestrategia.ss_crmeducativo.tipoNota.data.source;

import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoNotaUi;

import java.util.List;

/**
 * Created by kike on 15/02/2018.
 */

public interface TipoNotaDataSource {

    interface CallabackTipoNota{
        void onListTipoNota(List<TipoNotaUi> tipoNotaUiList);
    }

    void getTipoNota(int usuarioCreadorId,CallabackTipoNota callabackTipoNota);
}
