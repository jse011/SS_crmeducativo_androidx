package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.source.remote;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.source.RubroEvaluacionProcesoListaDataSource;

import java.util.ArrayList;

/**
 * Created by @stevecampos on 17/10/2017.
 */

public class RubroEvaluacionProcesoListaRemoteDataSource implements RubroEvaluacionProcesoListaDataSource {
    private static final String TAG = RubroEvaluacionProcesoListaRemoteDataSource.class.getSimpleName();

    @Override
    public void getCompetenciaList(ArrayList<Integer> competenciaIdList, int SesionAprendizajeId, int nivel, ListCallback<CompetenciaUi> callback) {

    }
}
