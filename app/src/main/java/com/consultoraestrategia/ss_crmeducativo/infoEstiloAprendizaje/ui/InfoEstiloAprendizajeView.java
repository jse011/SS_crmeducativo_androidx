package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.ui;


import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.InfoEstiloAprendizajePresenter;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.GraficoEstilosAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.InstrumentoObservadoUi;

import java.util.List;

public interface InfoEstiloAprendizajeView extends BaseView<InfoEstiloAprendizajePresenter> {
    void setCabecera(AlumnoUi alumnoUi);
    void setListTestEvalaucion(List<InstrumentoObservadoUi> instrumentoObservadoUiList);
    void setCabeceraResultadoEvalaucion(InstrumentoObservadoUi instrumentoObservadoUi);
    void setListResultadoEvaluacion(List<Object> objectsList);
    void showGraficoAprendizaje(final GraficoEstilosAprendizajeUi aprendizajeUi);
}
