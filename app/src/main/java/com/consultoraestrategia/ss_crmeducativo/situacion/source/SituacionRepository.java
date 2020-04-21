package com.consultoraestrategia.ss_crmeducativo.situacion.source;

import com.consultoraestrategia.ss_crmeducativo.situacion.entity.SituacionUI;
import com.consultoraestrategia.ss_crmeducativo.situacion.source.local.SituacionLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.situacion.source.remote.SituacionRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.situacion.usecase.GetSituacionListUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.callbacks.GetTareasListCallback;

import java.util.List;

/**
 * Created by @stevecampos on 21/02/2018.
 */

public class SituacionRepository implements SituacionDataSource {

    private static final String TAG = SituacionRepository.class.getSimpleName();
    private SituacionLocalDataSource localDataSource;
    private SituacionRemoteDataSource remoteDataSource;

    public SituacionRepository(SituacionLocalDataSource localDataSource, SituacionRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }


    @Override
    public List<SituacionUI> getSituacionUIList(GetSituacionListUI.RequestValues requestValues) {
        return  localDataSource.getSituacionUIList(requestValues);
    }
}
