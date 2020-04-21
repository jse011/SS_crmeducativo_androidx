package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.data.source;

import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase.GetTipoNotaDefault;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase.GetTipoNotaList;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi;

import java.util.List;

/**
 * Created by SCIEV on 26/02/2018.
 */

public interface TipoNotaListDataSource {

    interface CallbackList<T> {
        void onLoadList(List<T> list);
    }

    interface Callback<T> {;
        void onLoad(boolean success, T item);
    }

    void getTipoNotaList(CallbackList<TipoNotaUi> callback, GetTipoNotaList.RequestValues requestValues);

    void getTipoNotaDefault(Callback<TipoNotaUi> callback, GetTipoNotaDefault.RequestValues requestValues);

    void getTipoNota(String TipoNotaId, Callback<TipoNotaUi> callback);
}
