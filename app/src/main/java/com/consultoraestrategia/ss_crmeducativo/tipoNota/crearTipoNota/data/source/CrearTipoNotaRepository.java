package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.data.source;

import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.data.source.local.CrearTipoNotaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.data.source.remote.CrearTipoNotaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoEscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoSelectorUi;

import java.util.List;

/**
 * Created by kike on 16/02/2018.
 */

public class CrearTipoNotaRepository implements CrearTipoNotaDataSource {
    private CrearTipoNotaLocalDataSource localDataSource;
    private CrearTipoNotaRemoteDataSource remoteDataSource;

    public CrearTipoNotaRepository(CrearTipoNotaLocalDataSource localDataSource, CrearTipoNotaRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getTipoSelector(int usuarioCreadorId,Callback<List<TipoSelectorUi>> callback) {
        localDataSource.getTipoSelector(usuarioCreadorId,callback);
        remoteDataSource.getTipoSelector(usuarioCreadorId,callback);
    }

    @Override
    public void getTipoEscalaEvaluacion(int usuarioCreadorId,Callback<List<TipoEscalaEvaluacionUi>> callback) {
        localDataSource.getTipoEscalaEvaluacion(usuarioCreadorId,callback);
        remoteDataSource.getTipoEscalaEvaluacion(usuarioCreadorId,callback);
    }
}
