package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroDataSource;
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

public class RubroRemoteDataSourse  implements RubroDataSource {

    private static final String TAG = RubroRemoteDataSourse.class.getSimpleName();

    public RubroRemoteDataSourse() {
    }

    @Override
    public void getRubroList(int sesionAprendizajeId, GetRubroListCallback callback) {

    }

    @Override
    public void getRubro(int rubroEvaluacionProcesoId, GetRubroCallback callback) {

    }

    @Override
    public void getRubro(String rubroEvaluacionProcesoId, GetRubroCallback callback) {

    }

    @Override
    public void getAlumnoListGrupo(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, int entidadId, int georederenciaId, GetAlumnoGrupoListCallback callback) {

    }

    @Override
    public void getAlumnoList(RubroEvaluacionUi rubroEvaluacionUi, FiltroTableUi filtroTableUi,int cargaCursoId, int entidadId, int georeferenciaId, GetAlumnoListCallback callback) {

    }

    @Override
    public void getIndicadoresList(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, GetIndicadorListCallback callback) {

    }

    @Override
    public void SaveRubro(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, AlumnosEvaluacionUi ui, SaveEvaluacionCallBack callBack) {

    }

    @Override
    public void SaveEvaluacionList(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, List<AlumnosEvaluacionUi> alumnosEvaluacionUiList, SaveListEvaluacionCallBack callBack) {

    }

    @Override
    public void saveEvaluacionGrupo(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, GrupoEvaluacionUi grupoEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi, SaveEvaluacionGrupoCallBack callBack) {

    }

    @Override
    public void getNotaEvaluacionList(RubroEvaluacionUi rubroEvaluacionUi, List<GrupoEvaluacionUi> itemEvaluacionUis, GetNotaEvaluacionListCallback getNotaEvaluacionListCallback) {

    }

    @Override
    public boolean recalcularMediaDv(String rubroEvaluacionProcesoId) {
        return false;
    }

    @Override
    public void publicarEvaluacion(OptionPublicar optionPublicar) {

    }

    @Override
    public void onUpdateEvaluacionFormula(UpdateEvaluacionFormulaCallback callback) {

    }


}
