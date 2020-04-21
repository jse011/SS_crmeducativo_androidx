package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.CrearRubroDataSource;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.GetIndicadorCallback;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.GetListCriterioEvaluacionCallback;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.GetListTipoEvaluacionCallback;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.SaveRubroCallBack;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.EstrategiaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.FormaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoNotaUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 11/10/2017.
 */

public class CrearRubroRemoteSource implements CrearRubroDataSource {
    private static final String TAG = CrearRubroRemoteSource.class.getSimpleName();

    public CrearRubroRemoteSource() {
    }

    @Override
    public void SaveRubro(CrearRubroEvalUi ui, SaveRubroCallBack callBack) {

    }

    @Override
    public void GetCriterioEvaluacion(CrearRubroEvalUi crearRubroEvalUi, GetListCriterioEvaluacionCallback callback) {

    }

    @Override
    public void GetIndicaor(int indicadorId,ArrayList<Integer> campotematicoIds, GetIndicadorCallback callback) {

    }

    @Override
    public void GetTipoEvaluacion(GetListTipoEvaluacionCallback callback) {

    }

    @Override
    public void GetFormaEvaluacion(ListCallck<FormaEvaluacionUi> callback) {

    }

    @Override
    public void getTipoNota(String tipoNotaId, Callback<TipoNotaUi> callback) {

    }

    @Override
    public void getTareaUi(String id, GetTareaUICallback getTareaUICallback) {

    }

    @Override
    public List<EstrategiaUi> getEstrategiasEvaluacion(int cursoId) {
        return null;
    }

    @Override
    public TipoNotaUi getTipoNota(int promaEducativoId) {
        return null;
    }

    @Override
    public CapacidadUi getCompetencia(int capacidadId) {
        return null;
    }


}
