package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerRubroEvaluacionProceso.listener;

/**
 * Created by kike on 07/06/2018.
 */

public interface DatosRubroProcesoListener {

    interface ObjectCallBack<T> {
        void onResponse(boolean success, T item);
    }

    void getDatosRubroProces(int usuarioId, int calendarioPeriod, int cargaCurso, ObjectCallBack objectCallBack);

}
