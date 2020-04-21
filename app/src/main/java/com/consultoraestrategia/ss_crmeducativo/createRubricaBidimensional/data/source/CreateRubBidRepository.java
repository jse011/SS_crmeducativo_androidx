package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.local.CreateRubBidLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.remote.CreateRubBidRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.CreateRubBid;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTipoNotas;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EstrategiaEvalUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoUi;

import java.util.List;

/**
 * Created by @stevecampos on 26/01/2018.
 */

public class CreateRubBidRepository implements CreateRubBidDataSource {

    private static final String TAG = CreateRubBidRepository.class.getSimpleName();
    private CreateRubBidLocalDataSource localDataSource;
    private CreateRubBidRemoteDataSource remoteDataSource;

    public CreateRubBidRepository(CreateRubBidLocalDataSource localDataSource, CreateRubBidRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getCamposAccion(int sesionAprendizajeId, Callback<CampoAccionUi> callback) {
        localDataSource.getCamposAccion(sesionAprendizajeId,callback);
    }

    @Override
    public void getCamposAccion(int cursoId, int cargaCursoId, int calendarioPeriodoId, Callback<CampoAccionUi> callback) {
        localDataSource.getCamposAccion(cursoId, cargaCursoId, calendarioPeriodoId, callback);
    }

    @Override
    public EstrategiaEvalUi getTituloRubrica(String keyRubroEvaluacion) {
        return localDataSource.getTituloRubrica(keyRubroEvaluacion);
    }

    @Override
    public TipoNotaUi getTipoNotaDefault(int progrmaEducativoId, List<TipoNotaUi.Tipo> tipos) {
        return localDataSource.getTipoNotaDefault(progrmaEducativoId, tipos);
    }

    @Override
    public void getCompetencias(int idSesionAprendizaje, Callback<CompetenciaUi> callback) {
        Log.d(TAG, "getCompetencias");
        localDataSource.getCompetencias(idSesionAprendizaje, callback);
    }

    @Override
    public void getCompetencias(int cursoId, int cargaCursoId, int calendarioPeriodoId, Callback<CompetenciaUi> callback) {
        Log.d(TAG, "getCompetencias");
        localDataSource.getCompetencias(cursoId, cargaCursoId, calendarioPeriodoId, callback);
    }

    @Override
    public void getIndicadores(int idCompetencia, Callback<IndicadorUi> callback) {
        Log.d(TAG, "getIndicadores");
        localDataSource.getIndicadores(idCompetencia, callback);
    }

    @Override
    public void getTipoNotas(Callback<TipoNotaUi> callback, GetTipoNotas.RequestValues values) {
        Log.d(TAG, "getTipoNotas");
        localDataSource.getTipoNotas(callback, values);
    }

    @Override
    public void getTipoEvaluacionList(Callback<TipoUi> callback) {
        Log.d(TAG, "getTipoEvaluacionList");
        localDataSource.getTipoEvaluacionList(callback);
    }

    @Override
    public void getEscalaEvaluacionList(Callback<EscalaEvaluacionUi> callback) {
        Log.d(TAG, "getEscalaEvaluacionList");
        localDataSource.getEscalaEvaluacionList(callback);
    }

    @Override
    public void getFormaEvaluacion(Callback<TipoUi> callback) {
        Log.d(TAG, "getFormaEvaluacion");
        localDataSource.getFormaEvaluacion(callback);
    }

    @Override
    public void createRubBid(CreateRubBid.RequestValues requestValues, SaveCallback callback) {
        Log.d(TAG, "createRubBid");
        localDataSource.createRubBid(requestValues, callback);
    }

    @Override
    public void getTipoNota(String id, CallbackSingle<TipoNotaUi> callback) {
        localDataSource.getTipoNota(id,callback);
    }

    @Override
    public void getTareaUi(String id, GetTareaUICallback getTareaUICallback) {
        localDataSource.getTareaUi(id, getTareaUICallback);
    }

    @Override
    public TipoUi getFormaEvaluacion(String rubroEvalaucionId) {
        return localDataSource.getFormaEvaluacion(rubroEvalaucionId);
    }

    @Override
    public TipoUi getTipoEvaluacion(String rubroEvalaucionId) {
        return localDataSource.getTipoEvaluacion(rubroEvalaucionId);
    }

    @Override
    public TipoNotaUi getTipoNotaRubrica(String rubroEvalaucionId) {
        return localDataSource.getTipoNotaRubrica(rubroEvalaucionId);
    }

    @Override
    public List<CompetenciaUi> getCompetencias(String rubroEvalaucionId) {
        return localDataSource.getCompetencias(rubroEvalaucionId);
    }

    @Override
    public TipoNotaUi getNivelesTipoNotaRubrica(String keyRubroEvaluacion) {
        return localDataSource.getNivelesTipoNotaRubrica(keyRubroEvaluacion);
    }

    @Override
    public List<EstrategiaEvalUi> getEstrategiasEvaluacion(int cursoId) {
        return localDataSource.getEstrategiasEvaluacion(cursoId);
    }

}
