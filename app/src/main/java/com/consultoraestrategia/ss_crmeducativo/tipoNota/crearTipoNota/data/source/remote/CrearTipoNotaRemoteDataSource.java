package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.data.source.CrearTipoNotaDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoEscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoSelectorUi;

import java.util.List;

/**
 * Created by kike on 16/02/2018.
 */

public class CrearTipoNotaRemoteDataSource implements CrearTipoNotaDataSource {
    @Override
    public void getTipoSelector(int usuarioCreadorId,Callback<List<TipoSelectorUi>> callback) {

    }

    @Override
    public void getTipoEscalaEvaluacion(int usuarioCreadorId,Callback<List<TipoEscalaEvaluacionUi>> callback) {

    }
}
