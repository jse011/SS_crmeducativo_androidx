package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.callbacks;

import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;

/**
 * Created by irvinmarin on 24/11/2017.
 */

public interface CrearTareaCallback {
    void onTareasCreated(String msjCorrecto, TareasUI tareasUI);

    void onError(String error);
}
