package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.data.source;


import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.data.source.local.InfoCriteriosEvalLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.entities.IndicadorUi;

public class InfoCriterioEvalRepository implements InfoCriterioEvalDataSource{

    public static InfoCriterioEvalRepository mRepository;
    private InfoCriteriosEvalLocalDataSource infoCriteriosEvalLocalDataSource;

    public InfoCriterioEvalRepository(InfoCriteriosEvalLocalDataSource infoCriteriosEvalLocalDataSource) {
        this.infoCriteriosEvalLocalDataSource = infoCriteriosEvalLocalDataSource;
    }

    public static InfoCriterioEvalRepository getInstance(InfoCriteriosEvalLocalDataSource infoCriteriosEvalLocalDataSource) {
        if (mRepository == null) {
            mRepository = new InfoCriterioEvalRepository(infoCriteriosEvalLocalDataSource);
        }
        return mRepository;
    }

    @Override
    public void getInformacionIndicador(int indicadorId, Callback<IndicadorUi> callback) {
        infoCriteriosEvalLocalDataSource.getInformacionIndicador(indicadorId, callback);
    }
}
