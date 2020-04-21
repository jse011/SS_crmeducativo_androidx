package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.listeners;

import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;

public interface SpinnerListener {

    void onEliminarListener(TareasUI tareasUI);
    void onAddTareaListener(TareasUI tareasUI);
    void onUpdateTareaListener(TareasUI tareasUI);
}
