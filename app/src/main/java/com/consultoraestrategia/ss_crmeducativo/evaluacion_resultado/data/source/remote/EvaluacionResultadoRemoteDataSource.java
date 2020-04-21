package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.data.source.remote;


import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.data.source.EvaluacionResultadoDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;

import java.util.List;

/**
 * Created by @stevecampos on 17/10/2017.
 */

public class EvaluacionResultadoRemoteDataSource  implements EvaluacionResultadoDataSource {
    private static final String TAG = EvaluacionResultadoRemoteDataSource.class.getSimpleName();

    public EvaluacionResultadoRemoteDataSource() {

    }


    @Override
    public void getTableEvaluacionResultado(int rubroEvaluacionId, int tipoCompetencia, SucessCallback<List<AlumnoUi>> callback) {

    }
}
