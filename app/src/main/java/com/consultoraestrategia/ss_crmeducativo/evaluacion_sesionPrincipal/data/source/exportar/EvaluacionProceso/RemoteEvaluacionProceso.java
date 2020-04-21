package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EvaluacionProceso;

import com.consultoraestrategia.ss_crmeducativo.api.RestAPI;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 26/01/2018.
 */

public class RemoteEvaluacionProceso  {
    private static final String TAG = RemoteEvaluacionProceso.class.getSimpleName();
    RestAPI api = new RestAPI();

    public RemoteEvaluacionProceso(RestAPI api) {
        this.api = api;
    }

}
