package com.consultoraestrategia.ss_crmeducativo.tipoNota.data.source;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.tipoNota.data.source.local.TipoNotaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.data.source.remote.TipoNotaRemoteDataSource;

/**
 * Created by kike on 15/02/2018.
 */

public class TipoNotaRepository implements TipoNotaDataSource {
    private String TAG = TipoNotaRepository.class.getSimpleName();
    private TipoNotaLocalDataSource localDataSource;
    private TipoNotaRemoteDataSource remoteDataSource;

    public TipoNotaRepository(TipoNotaLocalDataSource localDataSource, TipoNotaRemoteDataSource remoteDataSource) {
        Log.d(TAG,"TipoNotaRepository");
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getTipoNota(int usuarioCreadorId, CallabackTipoNota callabackTipoNota) {
        localDataSource.getTipoNota(usuarioCreadorId,callabackTipoNota);
        remoteDataSource.getTipoNota(usuarioCreadorId,callabackTipoNota);
    }
}
