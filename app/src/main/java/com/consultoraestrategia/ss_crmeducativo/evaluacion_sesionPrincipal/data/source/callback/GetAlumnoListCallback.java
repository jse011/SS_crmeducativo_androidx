package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback;

import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;

import java.util.List;

/**
 * Created by SCIEV on 9/10/2017.
 */

public interface GetAlumnoListCallback {

    void onRecursoLoad(List<AlumnosEvaluacionUi> alumnosEvaluacionUis);
    void onError(String errot);
}
