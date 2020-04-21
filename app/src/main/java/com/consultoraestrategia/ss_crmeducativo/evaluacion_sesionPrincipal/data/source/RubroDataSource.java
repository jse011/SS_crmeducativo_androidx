package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source;

import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetAlumnoGrupoListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetAlumnoListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetIndicadorListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetNotaEvaluacionListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetRubroCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetRubroListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.SaveEvaluacionCallBack;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.SaveEvaluacionGrupoCallBack;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.SaveListEvaluacionCallBack;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;

import java.util.List;

/**
 * Created by SCIEV on 6/10/2017.
 */

public interface RubroDataSource {

    void getRubroList(int sesionAprendizajeId, GetRubroListCallback callback);
    void getRubro(int rubroEvaluacionProcesoId, GetRubroCallback callback);
    void getRubro(String rubroEvaluacionProcesoId, GetRubroCallback callback);
    void getAlumnoListGrupo(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, int entidadId, int georefernciaId,GetAlumnoGrupoListCallback callback);
    void getAlumnoList(RubroEvaluacionUi rubroEvaluacionUi, FiltroTableUi filtroTableUi, int cargaCursoId, int entidadId, int georeferenciaId,GetAlumnoListCallback callback);
    void getIndicadoresList(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, GetIndicadorListCallback callback);
    void SaveRubro(int sesionAprendizajeId,RubroEvaluacionUi rubroEvaluacionUi,AlumnosEvaluacionUi ui, SaveEvaluacionCallBack callBack);
    void SaveEvaluacionList(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, List<AlumnosEvaluacionUi> alumnosEvaluacionUiList, SaveListEvaluacionCallBack callBack);
    void saveEvaluacionGrupo(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, GrupoEvaluacionUi grupoEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi, SaveEvaluacionGrupoCallBack callBack);
    void getNotaEvaluacionList(RubroEvaluacionUi rubroEvaluacionUi, List<GrupoEvaluacionUi> itemEvaluacionUis, GetNotaEvaluacionListCallback getNotaEvaluacionListCallback);
    boolean recalcularMediaDv(String rubroEvaluacionProcesoId);

    void publicarEvaluacion(OptionPublicar optionPublicar);

    interface UpdateEvaluacionFormulaCallback{
        void onPreLoad();
        void onLoad(boolean success);
    }
    void onUpdateEvaluacionFormula(UpdateEvaluacionFormulaCallback callback);
}
