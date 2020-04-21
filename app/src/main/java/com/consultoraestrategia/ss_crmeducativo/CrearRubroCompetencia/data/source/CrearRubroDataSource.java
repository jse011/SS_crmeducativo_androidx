package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.GetIndicadorCallback;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.GetListCriterioEvaluacionCallback;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.GetListTipoEvaluacionCallback;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.SaveRubroCallBack;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.EstrategiaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.FormaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 11/10/2017.
 */

public interface CrearRubroDataSource {

    interface ListCallck<T>{
        void onSucess(List<T> items);
    }
    interface Callback<T>{
        void onSucess(T item);
    }
    interface GetTareaUICallback {
        void onTareaLoaded(TareasUI tareasUI);
        void onError();
    }
    void SaveRubro(CrearRubroEvalUi ui, SaveRubroCallBack callBack);
    void GetCriterioEvaluacion(CrearRubroEvalUi crearRubroEvalUi, GetListCriterioEvaluacionCallback callback);
    void GetIndicaor(int indicadorId, ArrayList<Integer> campotematicoIds, GetIndicadorCallback callback);
    void GetTipoEvaluacion(GetListTipoEvaluacionCallback callback);
    void GetFormaEvaluacion(ListCallck<FormaEvaluacionUi> callback);
    void getTipoNota(String tipoNotaId, Callback<TipoNotaUi> callback);
    void getTareaUi(String id, GetTareaUICallback getTareaUICallback);
    List<EstrategiaUi> getEstrategiasEvaluacion(int cursoId);
    TipoNotaUi getTipoNota(int promaEducativoId);
    CapacidadUi getCompetencia(int capacidadId);
}
