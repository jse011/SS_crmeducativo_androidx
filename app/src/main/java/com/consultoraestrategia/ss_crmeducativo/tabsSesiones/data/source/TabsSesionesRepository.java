package com.consultoraestrategia.ss_crmeducativo.tabsSesiones.data.source;

import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.data.source.local.TabsSesionesLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.data.source.remote.TabsSesionesRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.entities.DatosEnsencialesUI;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.entities.RubroEvaluacionResultadoUi;

/**
 * Created by SCIEV on 25/01/2018.
 */

public class TabsSesionesRepository implements TabSesionesDataSource {
    private static TabsSesionesRepository INSTANCE = null;
    TabsSesionesLocalDataSource localDataSourse;
    TabsSesionesRemoteDataSource remoteDataSourse;

    public static TabsSesionesRepository getInstance(TabsSesionesLocalDataSource localDataSourse, TabsSesionesRemoteDataSource remoteDataSourse){
        if(INSTANCE == null){
            INSTANCE = new TabsSesionesRepository(localDataSourse, remoteDataSourse);
        }
        return  INSTANCE;
    }

    private TabsSesionesRepository(TabsSesionesLocalDataSource localDataSourse, TabsSesionesRemoteDataSource remoteDataSourse) {
        this.localDataSourse = localDataSourse;
        this.remoteDataSourse = remoteDataSourse;
    }

    @Override
    public DatosEnsencialesUI getDatosEsenciales(int sesionAprendizajeId, int cursoId) {
        return localDataSourse.getDatosEsenciales(sesionAprendizajeId, cursoId );
    }
}
