package com.consultoraestrategia.ss_crmeducativo.infoRubro.data.source;

import com.consultoraestrategia.ss_crmeducativo.infoRubro.data.source.local.InfoRubroLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.RubroEvalProcesoUi;

/**
 * Created by SCIEV on 28/08/2018.
 */

public class InfoRubroRepository implements InfoRubroDataSource {

    private static InfoRubroRepository mInstance;
    private InfoRubroLocalDataSource localDataSource;
    private InfoRubroRepository(InfoRubroLocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    public static InfoRubroRepository getInstance(InfoRubroLocalDataSource localDataSource) {
        if (mInstance == null) {
            mInstance = new InfoRubroRepository(localDataSource);
        }
        return mInstance;
    }

    @Override
    public void getInformacionRubro(String rubroEvalProcesoId, Callback<RubroEvalProcesoUi> callback) {
        localDataSource.getInformacionRubro(rubroEvalProcesoId, callback);
    }

}
