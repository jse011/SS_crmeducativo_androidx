package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.data.source;

import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.data.source.local.EvaluacionResultadoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.data.source.remote.EvaluacionResultadoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;

import java.util.List;

/**
 * Created by @stevecampos on 17/10/2017.
 */

public class EvaluacionResultadoRepository implements EvaluacionResultadoDataSource {

    private static final String TAG = EvaluacionResultadoRepository.class.getSimpleName();
    private EvaluacionResultadoLocalDataSource localDataSource;
    private EvaluacionResultadoRemoteDataSource remoteDataSource;

    public EvaluacionResultadoRepository(EvaluacionResultadoLocalDataSource localDataSource, EvaluacionResultadoRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getTableEvaluacionResultado(int rubroEvaluacionId, int tipoCompetencia, SucessCallback<List<AlumnoUi>> callback) {
        localDataSource.getTableEvaluacionResultado(rubroEvaluacionId, tipoCompetencia, callback);
    }

}

