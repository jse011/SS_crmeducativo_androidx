package com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.data.source;

import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.data.source.local.LocalPresicionDataSource;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.ValorTipoNotaPrecisionUi;

public class PresicionRepository implements PresicionDataSource {
    private static PresicionRepository mInstance;
    private LocalPresicionDataSource localPresicionDataSource;

    private PresicionRepository(LocalPresicionDataSource localPresicionDataSource) {
        this.localPresicionDataSource = localPresicionDataSource;
    }

    public static PresicionRepository getInstance(LocalPresicionDataSource localPresicionDataSource) {
        if (mInstance == null) {
            mInstance = new PresicionRepository(localPresicionDataSource);
        }
        return mInstance;
    }

    @Override
    public void getTipoNota(String rubroEvaluacionId, String valorTipoNotaId, Callback<ValorTipoNotaPrecisionUi> callback) {
        this.localPresicionDataSource.getTipoNota(rubroEvaluacionId, valorTipoNotaId, callback);
    }
}
