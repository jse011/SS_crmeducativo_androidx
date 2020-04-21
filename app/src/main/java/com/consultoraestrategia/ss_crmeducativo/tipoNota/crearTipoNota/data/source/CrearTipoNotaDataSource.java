package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.data.source;

import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoEscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoSelectorUi;

import java.util.List;

/**
 * Created by kike on 16/02/2018.
 */

public interface CrearTipoNotaDataSource {
    interface Callback<T> {
        void onLoaded(T list);
    }

    void getTipoSelector(int usuarioCreadorId,CrearTipoNotaDataSource.Callback<List<TipoSelectorUi>> callback);
    void getTipoEscalaEvaluacion(int usuarioCreadorId,CrearTipoNotaDataSource.Callback<List<TipoEscalaEvaluacionUi>> callback);
}
