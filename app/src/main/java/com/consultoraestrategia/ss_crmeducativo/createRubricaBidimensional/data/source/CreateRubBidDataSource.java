package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.CreateRubBid;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTipoNotas;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EstrategiaEvalUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TareasUI;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoUi;

import java.util.List;

/**
 * Created by @stevecampos on 29/01/2018.
 */

public interface CreateRubBidDataSource {


    void getCamposAccion(int sesionAprendizajeId, Callback<CampoAccionUi> callback);

    void getCamposAccion(int cursoId, int cargaCursoId, int calendarioPeriodoId, Callback<CampoAccionUi> callback);

    EstrategiaEvalUi getTituloRubrica(String keyRubroEvaluacion);

    TipoNotaUi getTipoNotaDefault(int progrmaEducativoId, List<TipoNotaUi.Tipo> tipo);

    interface SaveCallback {
        void onSuccess(String rubroEvaluacionId);
        void onError();
    }

    interface Callback<T> {
        void onLoaded(List<T> listLoaded);
    }

    interface CallbackSingle<T> {
        void onLoaded(T item);
    }

    interface GetTareaUICallback {
        void onTareaLoaded(TareasUI tareasUI);
        void onError();
    }

    void getCompetencias(int idSesionAprendizaje, Callback<CompetenciaUi> callback);

    void getCompetencias(int cursoId, int cargaCursoId, int calendarioPeriodoId, Callback<CompetenciaUi> callback);

    void getIndicadores(int idCompetencia, Callback<IndicadorUi> callback);

    void getTipoNotas(Callback<TipoNotaUi> callback, GetTipoNotas.RequestValues values);

    void getTipoEvaluacionList(Callback<TipoUi> callback);

    void getEscalaEvaluacionList(Callback<EscalaEvaluacionUi> callback);

    void getFormaEvaluacion(Callback<TipoUi> callback);

    void createRubBid(CreateRubBid.RequestValues requestValues, SaveCallback success);

    void getTipoNota(String id, CallbackSingle<TipoNotaUi> callback);

    void getTareaUi(String id, GetTareaUICallback getTareaUICallback);

    TipoUi getFormaEvaluacion(String rubroEvalaucionId);
    TipoUi getTipoEvaluacion(String rubroEvalaucionId);
    TipoNotaUi getTipoNotaRubrica(String rubroEvalaucionId);
    List<CompetenciaUi> getCompetencias(String rubroEvalaucionId);
    TipoNotaUi getNivelesTipoNotaRubrica(String keyRubroEvaluacion);

    List<EstrategiaEvalUi> getEstrategiasEvaluacion(int cursoId);
}
