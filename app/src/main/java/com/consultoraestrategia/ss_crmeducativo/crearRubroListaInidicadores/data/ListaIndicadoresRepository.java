package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data;


import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.source.callback.GetIndicadorListCallback;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.source.local.ListaIndicadoresLocalSource;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.source.remote.ListaIndicadoresRemoteSource;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;

/**
 * Created by SCIEV on 11/10/2017.
 */

public class ListaIndicadoresRepository implements ListaIndicadoresDataSource {

    private static ListaIndicadoresRepository INSTANCE = null;
    ListaIndicadoresLocalSource localDataSourse;
    ListaIndicadoresRemoteSource remoteDataSourse;

    public static ListaIndicadoresRepository getInstance(ListaIndicadoresLocalSource localDataSourse, ListaIndicadoresRemoteSource remoteDataSourse){
        if(INSTANCE == null){
            INSTANCE = new ListaIndicadoresRepository(localDataSourse, remoteDataSourse);
        }
        return  INSTANCE;
    }

    private ListaIndicadoresRepository(ListaIndicadoresLocalSource localDataSourse, ListaIndicadoresRemoteSource remoteDataSourse) {
        this.localDataSourse = localDataSourse;
        this.remoteDataSourse = remoteDataSourse;
    }

    @Override
    public void getIndicadoresList(int sesionAprendizajeId, int nivel, int competenciaId, GetIndicadorListCallback callback) {
        localDataSourse.getIndicadoresList(sesionAprendizajeId, nivel, competenciaId, callback);
        remoteDataSourse.getIndicadoresList(sesionAprendizajeId, nivel, competenciaId, callback);
    }

    @Override
    public void getIndicadoresListSilabo(int silaboEventoId, int nivel, GetIndicadorListCallback callback) {
        localDataSourse.getIndicadoresListSilabo(silaboEventoId, nivel, callback);
        remoteDataSourse.getIndicadoresListSilabo(silaboEventoId, nivel, callback);
    }

    @Override
    public void getIndicadoresListSilaboCompetencia(int competenciaId, int silavoEventoId, int nivel, int calendarioPeriodoId, GetIndicadorListCallback callback) {
        localDataSourse.getIndicadoresListSilaboCompetencia(competenciaId, silavoEventoId, nivel, calendarioPeriodoId, callback);
    }

    @Override
    public void getCompetencia(int nivel, int competenciId, Callback<CompetenciaUi> callback) {
        localDataSourse.getCompetencia(nivel,competenciId,callback);
    }

}
