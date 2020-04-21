package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidDataSource;
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
 * Created by @stevecampos on 29/01/2018.
 */

public class CreateRubBidRemoteDataSource implements CreateRubBidDataSource{
    @Override
    public void getCamposAccion(int sesionAprendizajeId, Callback<CampoAccionUi> callback) {

    }

    @Override
    public void getCamposAccion(int cursoId, int cargaCursoId, int calendarioPeriodoId, Callback<CampoAccionUi> callback) {

    }

    @Override
    public EstrategiaEvalUi getTituloRubrica(String keyRubroEvaluacion) {
        return null;
    }

    @Override
    public TipoNotaUi getTipoNotaDefault(int progrmaEducativoId, List<TipoNotaUi.Tipo> tipo) {
        return null;
    }

    @Override
    public void getCompetencias(int idSesionAprendizaje, Callback<CompetenciaUi> callback) {

    }

    @Override
    public void getCompetencias(int cursoId, int cargaCursoId, int calendarioPeriodoId, Callback<CompetenciaUi> callback) {

    }

    @Override
    public void getIndicadores(int idCompetencia, Callback<IndicadorUi> callback) {

    }

    @Override
    public void getTipoNotas(Callback<TipoNotaUi> callback, GetTipoNotas.RequestValues values) {

    }

    @Override
    public void getTipoEvaluacionList(Callback<TipoUi> callback) {

    }

    @Override
    public void getEscalaEvaluacionList(Callback<EscalaEvaluacionUi> callback) {

    }

    @Override
    public void getFormaEvaluacion(Callback<TipoUi> callback) {

    }

    @Override
    public void createRubBid(CreateRubBid.RequestValues requestValues, SaveCallback success) {

    }

    @Override
    public void getTipoNota(String id, CallbackSingle<TipoNotaUi> callback) {

    }

    @Override
    public void getTareaUi(String id, GetTareaUICallback getTareaUICallback) {

    }

    @Override
    public TipoUi getFormaEvaluacion(String rubroEvalaucionId) {
        return null;
    }

    @Override
    public TipoUi getTipoEvaluacion(String rubroEvalaucionId) {
        return null;
    }

    @Override
    public TipoNotaUi getTipoNotaRubrica(String rubroEvalaucionId) {
        return null;
    }

    @Override
    public List<CompetenciaUi> getCompetencias(String rubroEvalaucionId) {
        return null;
    }

    @Override
    public TipoNotaUi getNivelesTipoNotaRubrica(String keyRubroEvaluacion) {
        return null;
    }

    @Override
    public List<EstrategiaEvalUi> getEstrategiasEvaluacion(int cursoId) {
        return null;
    }


}
