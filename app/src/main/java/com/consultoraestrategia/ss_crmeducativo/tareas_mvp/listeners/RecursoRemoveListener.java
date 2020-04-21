package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.listeners;

import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RecursosUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;

public interface RecursoRemoveListener {
    void onbtnDeleteRecursoDataBase(TareasUI tareasUI, RecursosUI recursosUI);

    void onbtnDeleteRecursoFromList(RecursosUI recursosUI);
}
