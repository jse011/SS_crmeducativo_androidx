package com.consultoraestrategia.ss_crmeducativo.situacion.source.remote;

import com.consultoraestrategia.ss_crmeducativo.situacion.entity.SituacionUI;
import com.consultoraestrategia.ss_crmeducativo.situacion.source.SituacionDataSource;
import com.consultoraestrategia.ss_crmeducativo.situacion.usecase.GetSituacionListUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.callbacks.GetTareasListCallback;

import java.util.List;

/**
 * Created by @stevecampos on 21/02/2018.
 */

public class SituacionRemoteDataSource implements SituacionDataSource {

    public SituacionRemoteDataSource() {
    }


    @Override
    public List<SituacionUI> getSituacionUIList(GetSituacionListUI.RequestValues requestValues) {
        return null;
    }
}
