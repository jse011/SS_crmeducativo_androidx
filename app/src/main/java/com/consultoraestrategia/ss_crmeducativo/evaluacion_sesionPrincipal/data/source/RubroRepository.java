package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetAlumnoGrupoListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetAlumnoListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetIndicadorListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetNotaEvaluacionListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetRubroCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetRubroListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.SaveEvaluacionCallBack;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.SaveEvaluacionGrupoCallBack;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.SaveListEvaluacionCallBack;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EquipoEvaluacionProceso.LocalEquipoEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EquipoEvaluacionProceso.RemoteEquipoEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EvaluacionProceso.LocalEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EvaluacionProceso.RemoteEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.rubroEvaluacionProceso.LocalRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.rubroEvaluacionProceso.RemoteRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.local.RubroLocalDataSourse;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.remote.RubroRemoteDataSourse;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by SCIEV on 6/10/2017.
 */

public class RubroRepository implements RubroDataSource {
    private final static String TAG = "RubroRepository";
    private static RubroRepository INSTANCE = null;
    private RubroLocalDataSourse localDataSource;
    private RubroRemoteDataSourse remoteDataSource;
    private LocalRubroEvaluacionProceso localRubroEvaluacionProceso;
    private RemoteRubroEvaluacionProceso remoteRubroEvaluacionProceso;
    private LocalEquipoEvaluacionProceso localEquipoEvaluacionProceso;
    private RemoteEquipoEvaluacionProceso remoteEquipoEvaluacionProceso;
    private LocalEvaluacionProceso localEvaluacionProceso;
    private RemoteEvaluacionProceso remoteEvaluacionProceso;

    public static RubroRepository getInstance(RubroLocalDataSourse localDataSource, RubroRemoteDataSourse remoteDataSource, LocalRubroEvaluacionProceso localRubroEvaluacionProceso, RemoteRubroEvaluacionProceso remoteRubroEvaluacionProceso, LocalEquipoEvaluacionProceso localEquipoEvaluacionProceso, RemoteEquipoEvaluacionProceso remoteEquipoEvaluacionProceso, LocalEvaluacionProceso localEvaluacionProceso, RemoteEvaluacionProceso remoteEvaluacionProceso) {
        if(INSTANCE == null){
            INSTANCE = new RubroRepository(localDataSource, remoteDataSource, localRubroEvaluacionProceso, remoteRubroEvaluacionProceso,localEquipoEvaluacionProceso,remoteEquipoEvaluacionProceso,localEvaluacionProceso,remoteEvaluacionProceso);
        }
        return  INSTANCE;
    }

    public RubroRepository(RubroLocalDataSourse localDataSource, RubroRemoteDataSourse remoteDataSource, LocalRubroEvaluacionProceso localRubroEvaluacionProceso, RemoteRubroEvaluacionProceso remoteRubroEvaluacionProceso, LocalEquipoEvaluacionProceso localEquipoEvaluacionProceso, RemoteEquipoEvaluacionProceso remoteEquipoEvaluacionProceso, LocalEvaluacionProceso localEvaluacionProceso, RemoteEvaluacionProceso remoteEvaluacionProceso) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.localRubroEvaluacionProceso = localRubroEvaluacionProceso;
        this.remoteRubroEvaluacionProceso = remoteRubroEvaluacionProceso;
        this.localEquipoEvaluacionProceso = localEquipoEvaluacionProceso;
        this.remoteEquipoEvaluacionProceso = remoteEquipoEvaluacionProceso;
        this.localEvaluacionProceso = localEvaluacionProceso;
        this.remoteEvaluacionProceso = remoteEvaluacionProceso;
    }

    public RubroRepository(RubroLocalDataSourse localDataSource, RubroRemoteDataSourse remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getRubroList(int sesionAprendizajeId, GetRubroListCallback callback) {
        localDataSource.getRubroList(sesionAprendizajeId ,callback);
        remoteDataSource.getRubroList(sesionAprendizajeId ,callback);
    }

    @Override
    public void getRubro(int rubroEvaluacionProcesoId, GetRubroCallback callback) {

    }

    @Override
    public void getRubro(String rubroEvaluacionProcesoId,GetRubroCallback callback) {
        localDataSource.getRubro(rubroEvaluacionProcesoId, callback);
    }

    @Override
    public void getAlumnoList(RubroEvaluacionUi rubroEvaluacionUi, FiltroTableUi filtroTableUi,int cargaCursoId, int entidadId, int georeferenciaId, GetAlumnoListCallback callback) {
        localDataSource.getAlumnoList(rubroEvaluacionUi, filtroTableUi,cargaCursoId, entidadId, georeferenciaId, callback);
        remoteDataSource.getAlumnoList(rubroEvaluacionUi, filtroTableUi,cargaCursoId, entidadId, georeferenciaId, callback);
    }

    @Override
    public void getIndicadoresList(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, GetIndicadorListCallback callback) {
        localDataSource.getIndicadoresList( sesionAprendizajeId,rubroEvaluacionUi, callback);
        remoteDataSource.getIndicadoresList(sesionAprendizajeId,rubroEvaluacionUi, callback);
    }

    @Override
    public void SaveRubro(int sesionAprendizajeId, final RubroEvaluacionUi rubroEvaluacionUi, AlumnosEvaluacionUi ui, final SaveEvaluacionCallBack callBack) {


            localDataSource.SaveRubro(sesionAprendizajeId, rubroEvaluacionUi,ui, new SaveEvaluacionCallBack() {
                @Override
                public void localSuccess(AlumnosEvaluacionUi alumnosEvaluacionUi, boolean success) {
                    callBack.localSuccess(alumnosEvaluacionUi,success);
                    if(!success) return;
                    if(rubroEvaluacionUi.getIdServer() > 0){
                        exportarRubroEvaluacionProceso(rubroEvaluacionUi);
                    }else {
                      int idServer = getIdServerRubroEvaluacionProceso(rubroEvaluacionUi);
                      if(idServer > 0){
                          rubroEvaluacionUi.setIdServer(idServer);
                          exportarRubroEvaluacionProceso(rubroEvaluacionUi);
                          return;
                      }


                    }

                }
            });
        //remoteDataSource.SaveRubro(rubroEvalProcesoId,sesionAprendizajeId,ui,callBack);
    }

    @Override
    public void SaveEvaluacionList(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, List<AlumnosEvaluacionUi> alumnosEvaluacionUiList, SaveListEvaluacionCallBack callBack) {
        localDataSource.SaveEvaluacionList(sesionAprendizajeId, rubroEvaluacionUi, alumnosEvaluacionUiList, callBack);
    }

    private void exportarRubroEvaluacionProceso(RubroEvaluacionUi rubroEvaluacionUi){

        /*if(rubroEvaluacionUi.getIdServer() > 0){
            SQLite.update(EvaluacionProceso.class)
                    .set(EvaluacionProceso_Table.rubroEvalProcesoId.is(rubroEvaluacionUi.getIdServer()))
                    .where(EvaluacionProceso_Table.rubroEvalProcesoAndroidId.is(rubroEvaluacionUi.getId()))
                    .execute();
        }*/

    }

    private int getIdServerRubroEvaluacionProceso(RubroEvaluacionUi rubroEvaluacionUi){
        /*int idServer = 0;
        RubroEvaluacionProceso rubroEvaluacionProceso = SQLite.select()
                .from(RubroEvaluacionProceso.class)
                .where(RubroEvaluacionProceso_Table.androidId.is(rubroEvaluacionUi.getId()))
                .querySingle();

        if(rubroEvaluacionProceso == null)return idServer;

        if(rubroEvaluacionProceso.getSyncFlag() == RubroEvaluacionProceso.FLAG_EXPORTED){
            idServer = rubroEvaluacionProceso.getRubroEvalProcesoId();
        }
        return idServer;*/
        return 0;
    }


    @Override
    public void getAlumnoListGrupo(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, int entidadId, int georefernciaId, GetAlumnoGrupoListCallback callback) {
        localDataSource.getAlumnoListGrupo(sesionAprendizajeId,rubroEvaluacionUi, entidadId, georefernciaId, callback);
        remoteDataSource.getAlumnoListGrupo(sesionAprendizajeId,rubroEvaluacionUi, entidadId, georefernciaId, callback);
    }

    @Override
    public void saveEvaluacionGrupo(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, GrupoEvaluacionUi grupoEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi, final SaveEvaluacionGrupoCallBack callBack) {
        localDataSource.saveEvaluacionGrupo(sesionAprendizajeId, rubroEvaluacionUi, grupoEvaluacionUi, alumnosEvaluacionUi, new SaveEvaluacionGrupoCallBack() {
            @Override
            public void localSuccess(GrupoEvaluacionUi grupoEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi, boolean success) {
                callBack.localSuccess(grupoEvaluacionUi,alumnosEvaluacionUi,success);
                if(!success)return;



            }
        });
    }

    @Override
    public void getNotaEvaluacionList(RubroEvaluacionUi rubroEvaluacionUi, List<GrupoEvaluacionUi> itemEvaluacionUis, GetNotaEvaluacionListCallback callback) {
        localDataSource.getNotaEvaluacionList(rubroEvaluacionUi,itemEvaluacionUis, callback);
    }

    @Override
    public boolean recalcularMediaDv(String rubroEvaluacionProcesoId) {
        return localDataSource.recalcularMediaDv(rubroEvaluacionProcesoId);
    }

    @Override
    public void publicarEvaluacion(OptionPublicar optionPublicar) {
        localDataSource.publicarEvaluacion(optionPublicar);
    }

    @Override
    public void onUpdateEvaluacionFormula(UpdateEvaluacionFormulaCallback callback) {
        localDataSource.onUpdateEvaluacionFormula(callback);
    }

}
