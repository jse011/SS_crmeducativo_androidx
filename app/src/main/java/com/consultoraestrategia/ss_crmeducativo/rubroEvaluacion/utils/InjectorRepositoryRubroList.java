package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.utils;


import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.local.RubroEvaluacionProcesoListaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.remote.RubroEvaluacionProcesoListaRemoteDataSource;

/**
 * Created by SCIEV on 7/08/2018.
 */

public class InjectorRepositoryRubroList {

    public static RubroEvaluacionProcesoListaRepository getEvaluacionFormulaRepository(){
        return new RubroEvaluacionProcesoListaRepository(
                new RubroEvaluacionProcesoListaLocalDataSource(),
                new RubroEvaluacionProcesoListaRemoteDataSource());
    }
}
