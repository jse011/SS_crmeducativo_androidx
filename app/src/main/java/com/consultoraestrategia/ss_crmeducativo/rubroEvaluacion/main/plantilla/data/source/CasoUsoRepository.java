package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.CrearRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.colorCondicional.LocalColorCondicional;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.colorCondicional.RemoteColorCondicional;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.criterioRubroEvaluacion.LocalCriterioRubroEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.criterioRubroEvaluacion.RemoteCriterioRubroEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.rubroEvaluacionProcesoCampotematico.LocalRubroEvaluacionProcesoCampotematico;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.rubroEvaluacionProcesoCampotematico.RemoteRubroEvaluacionProcesoCampotematico;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.local.CrearRubroLocalSource;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.remote.CrearRubroRemoteSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.entidades.IndicadoresUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.local.CasoUsoLocalDataSource;

import java.util.List;

/**
 * Created by kike on 19/02/2018.
 */

public class CasoUsoRepository implements CasoUsoDataSource{

    private CasoUsoLocalDataSource localDataSource;

    public CasoUsoRepository(CasoUsoLocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    @Override
    public void deleteRubroEvaluacionProceso(RubroProcesoUi rubroProcesoUi, final ObjectCallback objectCallback) {
        localDataSource.deleteRubroEvaluacionProceso(rubroProcesoUi, new ObjectCallback() {
            @Override
            public void onDelete(RubroProcesoUi rubroProcesoUi, int validateSuccess) {
                objectCallback.onDelete(rubroProcesoUi,validateSuccess);
                CrearRubroRepository crearRubroRepository = new CrearRubroRepository(
                        new CrearRubroLocalSource(),
                        new CrearRubroRemoteSource(),
                        new LocalCriterioRubroEvaluacion(),
                        new RemoteCriterioRubroEvaluacion(),
                        new LocalColorCondicional(),
                        new RemoteColorCondicional(),
                        new LocalRubroEvaluacionProcesoCampotematico(),
                        new RemoteRubroEvaluacionProcesoCampotematico()
                );

                crearRubroRepository.exporRubroEvaluacionProceso();
            }
        });
    }

    @Override
    public void showListCamposTematicos(String rubroProcesoKey, ObjectCallbackList<List<IndicadoresCamposAccionUi>> listCamposTematicosCallBack) {
        localDataSource.showListCamposTematicos(rubroProcesoKey, listCamposTematicosCallBack);
    }

    @Override
    public void showListIndicadores(String rubroProcesoKey, ObjectCallbackList<List<IndicadoresUi>> listObjectCallbackList) {
        localDataSource.showListIndicadores(rubroProcesoKey, listObjectCallbackList);
    }

    @Override
    public boolean changeToogleCapacidad(int capacidadId, boolean toogle) {
        return localDataSource.changeToogleCapacidad(capacidadId, toogle);
    }

    /*@Override
    public void showListCamposTematicos(RubroProcesoUi rubroProcesoUi, ListCamposTematicosCallBack listCamposTematicosCallBack) {
        localDataSource.showListCamposTematicos(rubroProcesoUi, listCamposTematicosCallBack);
    }*/

   /* @Override
    public void showListDesempenioIcd(RubroProcesoUi rubroProcesoUi, ListDesempenioIcdsCallBack listDesempenioIcdsCallBack) {
        localDataSource.showListDesempenioIcd(rubroProcesoUi, listDesempenioIcdsCallBack);
    }*/



}
