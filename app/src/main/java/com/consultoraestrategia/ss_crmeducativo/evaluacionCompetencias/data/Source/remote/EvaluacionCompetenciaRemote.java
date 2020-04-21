package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.remote;

import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.EvaluacionCompetenciaDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;

import java.util.List;

/**
 * Created by CCIE on 09/03/2018.
 */

public class EvaluacionCompetenciaRemote implements EvaluacionCompetenciaDataSource {

    public EvaluacionCompetenciaRemote() {
    }

    @Override
    public void getCompenteciaLista(int periodoId, int cargaCursoId, int cursoId, List<FiltradoUi> filtradoUiList, CallBack callBack) {

    }

    @Override
    public void getEnfoqueTransVersal(int periodoId, int cargaCursoId, int cursoId, CallBack callBack) {

    }

    @Override
    public void onEvaluacionResultado(Object object,int periodoId, ObjectCallback objectCallback) {

    }

    @Override
    public boolean changeToogleCompetencia(int competenciaId, boolean toogle) {
        return false;
    }

    @Override
    public void anclarResultados(int calendarioPeriodoId, int cargaCursoId, List<FiltradoUi> filtradoUiList, Callback2 callback) {

    }

    @Override
    public boolean validarAnclaCalendarioPeriodo(int idCalendarioPeriodo, int idCargaCurso, boolean isRubroResultado) {
        return false;
    }

    @Override
    public boolean recalcularMediaDv(int rubroEvaluacionResultadoId) {
        return false;
    }
}
