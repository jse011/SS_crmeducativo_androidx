package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data;

import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.source.callback.GetIndicadorListCallback;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;

/**
 * Created by SCIEV on 11/10/2017.
 */

public interface ListaIndicadoresDataSource {
    interface Callback<T>{
        void onSucces(T item);
    }
    void getIndicadoresList(int sesionAprendizajeId, int nivel, int competenciaId, GetIndicadorListCallback callback);
    void getIndicadoresListSilabo(int silaboEventoId, int nivel, GetIndicadorListCallback callback);
    void getIndicadoresListSilaboCompetencia(int competenciaId, int silavoEventoId, int nivel, int calendarioPeriodoId, GetIndicadorListCallback callback);
    void getCompetencia(int nivel, int competenciId, Callback<CompetenciaUi> callback);
}
