package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.GetIndicadorCallback;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.GetListCriterioEvaluacionCallback;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.GetListTipoEvaluacionCallback;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.SaveRubroCallBack;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.colorCondicional.LocalColorCondicional;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.colorCondicional.RemoteColorCondicional;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.criterioRubroEvaluacion.LocalCriterioRubroEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.criterioRubroEvaluacion.RemoteCriterioRubroEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.rubroEvaluacionProcesoCampotematico.LocalRubroEvaluacionProcesoCampotematico;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.rubroEvaluacionProcesoCampotematico.RemoteRubroEvaluacionProcesoCampotematico;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.local.CrearRubroLocalSource;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.remote.CrearRubroRemoteSource;
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

public class CrearRubroRepository implements CrearRubroDataSource {

    public static final String TAG = CrearRubroRepository.class.getSimpleName();
    private static CrearRubroRepository INSTANCE = null;
    CrearRubroLocalSource localDataSource;
    CrearRubroRemoteSource remoteDataSource;
    LocalCriterioRubroEvaluacion localCriterioRubroEvaluacion;
    RemoteCriterioRubroEvaluacion remoteCriterioRubroEvaluacion;
    LocalColorCondicional localColorCondicional;
    RemoteColorCondicional remoteColorCondicional;
    LocalRubroEvaluacionProcesoCampotematico localRubroEvaluacionProcesoCampotematico;
    RemoteRubroEvaluacionProcesoCampotematico remoteRubroEvaluacionProcesoCampotematico;


    public static CrearRubroRepository getInstance(CrearRubroLocalSource localDataSource, CrearRubroRemoteSource remoteDataSource, LocalCriterioRubroEvaluacion localCriterioRubroEvaluacion, RemoteCriterioRubroEvaluacion remoteCriterioRubroEvaluacion, LocalColorCondicional localColorCondicional, RemoteColorCondicional remoteColorCondicional, LocalRubroEvaluacionProcesoCampotematico localRubroEvaluacionProcesoCampotematico, RemoteRubroEvaluacionProcesoCampotematico remoteRubroEvaluacionProcesoCampotematico) {
        if (INSTANCE == null) {
            INSTANCE = new CrearRubroRepository(localDataSource, remoteDataSource, localCriterioRubroEvaluacion, remoteCriterioRubroEvaluacion, localColorCondicional, remoteColorCondicional, localRubroEvaluacionProcesoCampotematico, remoteRubroEvaluacionProcesoCampotematico);
        }
        return INSTANCE;
    }

    public CrearRubroRepository(CrearRubroLocalSource localDataSource, CrearRubroRemoteSource remoteDataSource, LocalCriterioRubroEvaluacion localCriterioRubroEvaluacion, RemoteCriterioRubroEvaluacion remoteCriterioRubroEvaluacion, LocalColorCondicional localColorCondicional, RemoteColorCondicional remoteColorCondicional, LocalRubroEvaluacionProcesoCampotematico localRubroEvaluacionProcesoCampotematico, RemoteRubroEvaluacionProcesoCampotematico remoteRubroEvaluacionProcesoCampotematico) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.localCriterioRubroEvaluacion = localCriterioRubroEvaluacion;
        this.remoteCriterioRubroEvaluacion = remoteCriterioRubroEvaluacion;
        this.localColorCondicional = localColorCondicional;
        this.remoteColorCondicional = remoteColorCondicional;
        this.localRubroEvaluacionProcesoCampotematico = localRubroEvaluacionProcesoCampotematico;
        this.remoteRubroEvaluacionProcesoCampotematico = remoteRubroEvaluacionProcesoCampotematico;
    }

    @Override
    public void SaveRubro(CrearRubroEvalUi ui, final SaveRubroCallBack callBack) {
        Log.d(TAG, "SaveRubro" );
        localDataSource.SaveRubro(ui, new SaveRubroCallBack() {
            @Override
            public void localSuccess(String rubroEvaluacionId, boolean success) {
                callBack.localSuccess(rubroEvaluacionId, success);
                if (!success) return;
                Log.d(TAG, "exporRubroEvaluacionProceso" );
                exporRubroEvaluacionProceso();
            }
        });
    }


    public void exporRubroEvaluacionProceso() {

    }

    @Override
    public void GetCriterioEvaluacion(CrearRubroEvalUi crearRubroEvalUi, GetListCriterioEvaluacionCallback callback) {
        localDataSource.GetCriterioEvaluacion(crearRubroEvalUi, callback);
        remoteDataSource.GetCriterioEvaluacion(crearRubroEvalUi, callback);
    }

    @Override
    public void GetIndicaor(int indicadorId, ArrayList<Integer> campotematicoIds, GetIndicadorCallback callback) {
        localDataSource.GetIndicaor(indicadorId,campotematicoIds, callback);
        remoteDataSource.GetIndicaor(indicadorId,campotematicoIds, callback);
    }

    @Override
    public void GetTipoEvaluacion(GetListTipoEvaluacionCallback callback) {
        localDataSource.GetTipoEvaluacion(callback);
        remoteDataSource.GetTipoEvaluacion(callback);
    }

    @Override
    public void GetFormaEvaluacion(ListCallck<FormaEvaluacionUi> callback) {
        localDataSource.GetFormaEvaluacion(callback);
    }

    @Override
    public void getTipoNota(String tipoNotaId, Callback<TipoNotaUi> callback) {
        localDataSource.getTipoNota(tipoNotaId,callback);
    }

    @Override
    public void getTareaUi(String id, GetTareaUICallback getTareaUICallback) {
        localDataSource.getTareaUi(id, getTareaUICallback);
    }

    @Override
    public List<EstrategiaUi> getEstrategiasEvaluacion(int cursoId) {
        return localDataSource.getEstrategiasEvaluacion(cursoId);
    }

    @Override
    public TipoNotaUi getTipoNota(int promaEducativoId) {
        return localDataSource.getTipoNota(promaEducativoId);
    }

    @Override
    public CapacidadUi getCompetencia(int capacidadId) {
        return localDataSource.getCompetencia(capacidadId);
    }


}
