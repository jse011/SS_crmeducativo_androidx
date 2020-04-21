package com.consultoraestrategia.ss_crmeducativo.infoRubro.data.source;

import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.RubroEvalProcesoUi;

/**
 * Created by SCIEV on 28/08/2018.
 */

public interface InfoRubroDataSource {

    interface Callback<T>{
        void onLoad(boolean success, T item);
    }

    void getInformacionRubro(String rubroEvalProcesoId, Callback<RubroEvalProcesoUi> callback);
}
