package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source;

import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.local.EvaluacionCompetenciaLocal;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.remote.EvaluacionCompetenciaRemote;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;

import java.util.List;

/**
 * Created by CCIE on 09/03/2018.
 */

public class EvaluacionCompetenciaRepository implements EvaluacionCompetenciaDataSource {

    private EvaluacionCompetenciaLocal competenciaLocal;
    private EvaluacionCompetenciaRemote competenciaRemote;

    public EvaluacionCompetenciaRepository(EvaluacionCompetenciaLocal competenciaLocal, EvaluacionCompetenciaRemote competenciaRemote) {
        this.competenciaLocal = competenciaLocal;
        this.competenciaRemote = competenciaRemote;
    }

    @Override
    public void getCompenteciaLista(int periodoId, int cargaCursoId, int cursoId, List<FiltradoUi> filtradoUiList, CallBack callBack) {
        competenciaRemote.getCompenteciaLista(periodoId, cargaCursoId, cursoId,filtradoUiList, callBack);
        competenciaLocal.getCompenteciaLista(periodoId, cargaCursoId, cursoId, filtradoUiList, callBack);
    }

    @Override
    public void getEnfoqueTransVersal(int periodoId, int cargaCursoId, int cursoId, CallBack callBack) {
        competenciaLocal.getEnfoqueTransVersal(periodoId, cargaCursoId, cursoId, callBack);
        competenciaRemote.getEnfoqueTransVersal(periodoId, cargaCursoId, cursoId, callBack);
    }

    @Override
    public void onEvaluacionResultado(Object object,int periodoId, ObjectCallback objectCallback) {
        competenciaLocal.onEvaluacionResultado(object,periodoId, objectCallback);
        competenciaRemote.onEvaluacionResultado(object,periodoId, objectCallback);
    }

    @Override
    public boolean changeToogleCompetencia(int competenciaId, boolean toogle) {
        return competenciaLocal.changeToogleCompetencia(competenciaId, toogle);
    }

    @Override
    public void anclarResultados( int calendarioPeriodoId, int cargaCursoId,List<FiltradoUi> filtradoUiList, Callback2 callback) {
        competenciaLocal.anclarResultados(calendarioPeriodoId, cargaCursoId, filtradoUiList, callback);
        competenciaRemote.anclarResultados( calendarioPeriodoId, cargaCursoId,filtradoUiList, callback);
    }

    @Override
    public boolean validarAnclaCalendarioPeriodo(int idCalendarioPeriodo, int idCargaCurso, boolean isRubroResultado) {
        return competenciaLocal.validarAnclaCalendarioPeriodo(idCalendarioPeriodo, idCargaCurso, isRubroResultado);
    }

    @Override
    public boolean recalcularMediaDv(int idRubro) {
        return competenciaLocal.recalcularMediaDv(idRubro);
    }
}
