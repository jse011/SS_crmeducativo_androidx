package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback;


import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;

/**
 * Created by SCIEV on 16/10/2017.
 */

public interface SaveEvaluacionGrupoCallBack {
        void localSuccess(GrupoEvaluacionUi grupoEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi, boolean success);
}
