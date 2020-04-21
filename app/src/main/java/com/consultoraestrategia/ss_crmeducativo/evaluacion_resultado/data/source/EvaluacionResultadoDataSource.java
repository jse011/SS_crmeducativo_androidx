package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.data.source;

import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;

import java.util.List;

/**
 * Created by @stevecampos on 17/10/2017.
 */

public interface EvaluacionResultadoDataSource {


    interface SucessCallback<T> {
        void onLoad(boolean success, T item);
    }

    void getTableEvaluacionResultado(int rubroEvaluacionId ,int tipoCompetencia, SucessCallback<List<AlumnoUi>>callback );
}
