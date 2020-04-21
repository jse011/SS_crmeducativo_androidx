package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.ListaIndicadoresDataSource;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.source.callback.GetIndicadorListCallback;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;

/**
 * Created by SCIEV on 11/10/2017.
 */

public class ListaIndicadoresRemoteSource implements ListaIndicadoresDataSource {
    public ListaIndicadoresRemoteSource() {
    }

    @Override
    public void getIndicadoresList(int sesionAprendizajeId, int nivel, int competenciaId, GetIndicadorListCallback callback) {

    }

    @Override
    public void getIndicadoresListSilabo(int silaboEventoId, int nivel, GetIndicadorListCallback callback) {

    }

    @Override
    public void getIndicadoresListSilaboCompetencia(int competenciaId, int silavoEventoId, int nivel, int calendarioPeriodoId, GetIndicadorListCallback callback) {

    }

    @Override
    public void getCompetencia(int nivel, int competenciId, Callback<CompetenciaUi> callback) {

    }
}
