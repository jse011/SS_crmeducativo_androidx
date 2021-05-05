package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.local.EvalRubBidLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.remote.EvalRubBidRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ArchivoUi;
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

public class EvalRubBidRepository implements EvalRubBidDataSource {
    public static final String TAG = EvalRubBidRepository.class.getSimpleName();

    private EvalRubBidLocalDataSource localDataSource;
    private EvalRubBidRemoteDataSource remoteDataSource;

    public EvalRubBidRepository(EvalRubBidLocalDataSource localDataSource, EvalRubBidRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public boolean deleteComentario(MensajeUi mensajeUi) {
        return localDataSource.deleteComentario(mensajeUi);
    }

    @Override
    public boolean deleteArchivoComentario(ArchivoUi archivoComentarioUi) {
        return localDataSource.deleteArchivoComentario(archivoComentarioUi);
    }

    @Override
    public PublicarEvaluacionUi getPublicacionEvaluacion(String rubroEvaluacionId, int personaId) {
        return localDataSource.getPublicacionEvaluacion(rubroEvaluacionId, personaId);
    }

    @Override
    public void updatePublicacionEvaluacion(PublicarEvaluacionUi publicarEvaluacionUi) {
        localDataSource.updatePublicacionEvaluacion(publicarEvaluacionUi);
    }

    @Override
    public void onUpdateEvaluacionFormula(UpdateEvaluacionFormulaCallback callback) {
        localDataSource.onUpdateEvaluacionFormula(callback);
    }

    @Override
    public void getRubBid(GetRubBid.RequestValues requestValues, Callback<RubBidUi> callback) {
        Log.d(TAG, "getRubBid");
        localDataSource.getRubBid(requestValues, callback);
    }

    @Override
    public void getGrupoConNotasProceso(GetGrupoConProc.RequestValues requestValues, GetAlumnConProcLisTCallback callback) {
        localDataSource.getGrupoConNotasProceso(requestValues,callback);
    }

    @Override
    public void evalAlumnosConProceso(EvalAlumnosProcesoBid.RequestValues requestValues, Callback<EvalProcUi> callback) {
        Log.d(TAG, "evalAlumnosConProceso");
        localDataSource.evalAlumnosConProceso(requestValues, callback);

    }

    @Override
    public void deleteEvalAlumnosConProceso(DeleteAlumnosProcesoBid.RequestValues requestValues, Callback<Boolean> callback) {
        localDataSource.deleteEvalAlumnosConProceso(requestValues,callback);
    }

    @Override
    public void getAlumnosConNotasProceso(GetAlumnoConProc.RequestValues requestValues, GetAlumnConProcLisTCallback callback) {
        localDataSource.getAlumnosConNotasProceso(requestValues,callback);
    }

    @Override
    public void getIndicador(GetIndicadorRubro.RequestValues requestValues, Callback<IndicadorUi> callback) {
        localDataSource.getIndicador(requestValues ,callback);
    }

    @Override
    public void searchAlumnosConNotasProceso(FiltroTableUi filtroTableUi, String rubroBidemencionalId, int cargaCursoId, int entidadId, int georeferenciaId,GetTableCallback callback) {
        localDataSource.searchAlumnosConNotasProceso(filtroTableUi, rubroBidemencionalId, cargaCursoId, entidadId, georeferenciaId,callback);
    }

    @Override
    public boolean calculaMediaDesviacion(RubBidUi idrubrica) {
        return localDataSource.calculaMediaDesviacion(idrubrica);
    }

    @Override
    public List<MensajeUi> getComentarioPred(EvalProcUi evalProcUi) {
        return localDataSource.getComentarioPred(evalProcUi);
    }

    @Override
    public List<MensajeUi> getComentarios(String rubricaBidimencionalId, int alumnoId) {
        return localDataSource.getComentarios(rubricaBidimencionalId, alumnoId);
    }

    @Override
    public boolean saveComentario(MensajeUi mensajeUi) {
        return localDataSource.saveComentario(mensajeUi);
    }

    @Override
    public List<ArchivoUi> getArchivoComentarioList(String rubroEvaluacionId, int personaId) {
        return localDataSource.getArchivoComentarioList(rubroEvaluacionId, personaId);
    }

}
