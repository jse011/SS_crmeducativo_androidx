package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.callbacks;

/**
 * Created by irvinmarin on 24/11/2017.
 */

public interface EliminarRecursoCallback {
    void onRecursoDeleted(String msjCorrecto);

    void onError(String error);
}
