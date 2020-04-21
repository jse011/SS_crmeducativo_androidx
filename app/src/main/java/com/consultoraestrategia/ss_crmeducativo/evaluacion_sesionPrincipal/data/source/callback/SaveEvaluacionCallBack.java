package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback;


import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;

/**
 * Created by SCIEV on 16/10/2017.
 */

public interface SaveEvaluacionCallBack {

        void localSuccess(AlumnosEvaluacionUi alumnosEvaluacionUi, boolean success);
}
