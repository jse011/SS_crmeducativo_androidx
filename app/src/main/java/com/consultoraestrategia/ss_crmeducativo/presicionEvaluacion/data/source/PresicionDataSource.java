package com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.data.source;

import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.ValorTipoNotaPrecisionUi;

public interface PresicionDataSource {
    interface Callback<T>{
        void onLoad(boolean success, T item);
    }

    void getTipoNota(String rubroEvaluacionId, String valorTipoNotaId, Callback<ValorTipoNotaPrecisionUi> callback);
}
