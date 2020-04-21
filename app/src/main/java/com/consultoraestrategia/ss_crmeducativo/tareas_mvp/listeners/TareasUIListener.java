package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.listeners;

import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadItemListener;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.HeaderTareasAprendizajeUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;

/**
 * Created by irvinmarin on 24/11/2017.
 */

public interface TareasUIListener extends DownloadItemListener {
    void onOpTareaEditClicked(TareasUI tareasUI, HeaderTareasAprendizajeUI headerTareasAprendizajeUI);

    void onOpTareaDelteClicked(TareasUI tareasUI);

    void onOpNotificarTareasClicked(TareasUI tareasUI, HeaderTareasAprendizajeUI headerTareasAprendizajeUI);

    void onClikEstado(TareasUI tareasUI);

    void onOpCrearRubricaClicked(TareasUI tareasUI, HeaderTareasAprendizajeUI headerTareasAprendizajeUI);

    void onOpCrearRubroClicked(TareasUI tareasUI, HeaderTareasAprendizajeUI headerTareasAprendizajeUI);

    void onClikRubroTarea(TareasUI tareasUI);
}
