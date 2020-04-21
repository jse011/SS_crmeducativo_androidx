package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.source;


import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.source.local.RubroEvaluacionProcesoListaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.source.remote.RubroEvaluacionProcesoListaRemoteDataSource;

import java.util.ArrayList;

/**
 * Created by @stevecampos on 17/10/2017.
 */

public class RubroEvaluacionProcesoListaRepository implements RubroEvaluacionProcesoListaDataSource {

    private static final String TAG = RubroEvaluacionProcesoListaRepository.class.getSimpleName();
    private RubroEvaluacionProcesoListaLocalDataSource localDataSource;
    private RubroEvaluacionProcesoListaRemoteDataSource remoteDataSource;

    public RubroEvaluacionProcesoListaRepository(RubroEvaluacionProcesoListaLocalDataSource localDataSource, RubroEvaluacionProcesoListaRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }


    @Override
    public void getCompetenciaList(ArrayList<Integer> competenciaIdList, int SesionAprendizajeId, int nivel, ListCallback<CompetenciaUi> callback) {
        localDataSource.getCompetenciaList(competenciaIdList,SesionAprendizajeId, nivel,callback);
    }
}

