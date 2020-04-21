package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ArchivoComentarioUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.PublicarEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.DeleteAlumnosProcesoBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.EvalAlumnosProcesoBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetAlumnoConProc;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetGrupoConProc;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetIndicadorRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetRubBid;

import java.util.List;

/**
 * Created by @stevecampos on 23/02/2018.
 */

public class EvalRubBidRemoteDataSource implements EvalRubBidDataSource {

    public static final String TAG = EvalRubBidRemoteDataSource.class.getSimpleName();


    @Override
    public boolean deleteComentario(MensajeUi mensajeUi) {
        return false;
    }

    @Override
    public boolean deleteArchivoComentario(ArchivoComentarioUi archivoComentarioUi) {
        return false;
    }

    @Override
    public PublicarEvaluacionUi getPublicacionEvaluacion(String rubroEvaluacionId, int personaId) {
        return null;
    }

    @Override
    public void updatePublicacionEvaluacion(PublicarEvaluacionUi publicarEvaluacionUi) {

    }

    @Override
    public void onUpdateEvaluacionFormula(UpdateEvaluacionFormulaCallback callback) {

    }

    @Override
    public void getRubBid(GetRubBid.RequestValues requestValues, Callback<com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubBidUi> callback) {

    }

    @Override
    public void getGrupoConNotasProceso(GetGrupoConProc.RequestValues requestValues, GetAlumnConProcLisTCallback callback) {

    }

    @Override
    public void evalAlumnosConProceso(EvalAlumnosProcesoBid.RequestValues requestValues, Callback<EvalProcUi> callback) {

    }

    @Override
    public void deleteEvalAlumnosConProceso(DeleteAlumnosProcesoBid.RequestValues requestValues, Callback<Boolean> callback) {

    }

    @Override
    public void getAlumnosConNotasProceso(GetAlumnoConProc.RequestValues requestValues, GetAlumnConProcLisTCallback callback) {

    }

    @Override
    public void getIndicador(GetIndicadorRubro.RequestValues requestValues, Callback<IndicadorUi> callback) {

    }

    @Override
    public void searchAlumnosConNotasProceso(FiltroTableUi filtroTableUi, String rubroBidemencionalId, int cargaCursoId, int entidadId, int georeferneciaId,GetTableCallback callback) {

    }

    @Override
    public boolean calculaMediaDesviacion(RubBidUi idrubrica) {
        return false;
    }

    @Override
    public List<MensajeUi> getComentarioPred(EvalProcUi evaluacionProcesoId) {
        return null;
    }

    @Override
    public List<MensajeUi> getComentarios(String rubricaBidimencionalId, int alumnoId) {
        return null;
    }

    @Override
    public boolean saveComentario(MensajeUi mensajeUi) {
        return false;
    }

    @Override
    public List<ArchivoComentarioUi> getArchivoComentarioList(String rubroEvaluacionId, int personaId) {
        return null;
    }

    @Override
    public boolean saveComentarioArchivo(ArchivoComentarioUi archivoComentarioUi) {
        return false;
    }
}
