package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.data.source;

import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.data.source.local.TipoNotaListLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase.GetTipoNotaDefault;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase.GetTipoNotaList;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi;

/**
 * Created by SCIEV on 26/02/2018.
 */

public class TipoNotaListRepository implements TipoNotaListDataSource {
    private TipoNotaListLocalDataSource localDataSource;

    public TipoNotaListRepository(TipoNotaListLocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    @Override
    public void getTipoNotaList(CallbackList<TipoNotaUi> callback, GetTipoNotaList.RequestValues requestValues) {
        localDataSource.getTipoNotaList(callback,requestValues);
    }

    @Override
    public void getTipoNota(String tipoNotaId, Callback<TipoNotaUi> callback) {
        localDataSource.getTipoNota(tipoNotaId, callback);
    }

    @Override
    public void getTipoNotaDefault(Callback<TipoNotaUi> callback, GetTipoNotaDefault.RequestValues requestValues) {
        localDataSource.getTipoNotaDefault(callback,requestValues);
    }
}
