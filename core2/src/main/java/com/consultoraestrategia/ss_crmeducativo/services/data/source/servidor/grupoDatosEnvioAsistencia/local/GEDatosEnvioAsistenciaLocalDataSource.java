package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosEnvioAsistencia.local;

import com.consultoraestrategia.ss_crmeducativo.entities.AsistenciaSesionAlumnoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.JustificacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.RelProgramaEducativoTipoNota;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.local.ServiceLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.local.BEDatosEnvioAsistenciaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.local.BEDatosEnvioTipoNotaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosEnvioAsistencia.GEDatosEnvioAsistenciaDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosEnvioAsistencia;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

/**
 * Created by SCIEV on 25/05/2018.
 */

public class GEDatosEnvioAsistenciaLocalDataSource extends ServiceLocalDataSource<GEDatosEnvioAsistencia> implements GEDatosEnvioAsistenciaDataSource {
    private BEDatosEnvioAsistenciaLocalDataSource beDatosEnvioAsistenciaLocalDataSource;
    private BEDatosEnvioTipoNotaLocalDataSource beDatosEnvioTipoNotaLocalDataSource;

    public GEDatosEnvioAsistenciaLocalDataSource(BEDatosEnvioAsistenciaLocalDataSource beDatosEnvioAsistenciaLocalDataSource, BEDatosEnvioTipoNotaLocalDataSource beDatosEnvioTipoNotaLocalDataSource) {
        this.beDatosEnvioAsistenciaLocalDataSource = beDatosEnvioAsistenciaLocalDataSource;
        this.beDatosEnvioTipoNotaLocalDataSource = beDatosEnvioTipoNotaLocalDataSource;
    }

    @Override
    public void getDatosExportar(final ObjectCallBack<GEDatosEnvioAsistencia> callBack) {
       final GEDatosEnvioAsistencia geDatosEnvioAsistencia = new GEDatosEnvioAsistencia();
       beDatosEnvioAsistenciaLocalDataSource.getDatosExportar(new ObjectCallBack<BEDatosEnvioAsistencia>() {
           @Override
           public void onResponse(boolean success, BEDatosEnvioAsistencia item) {
               try {
                   geDatosEnvioAsistencia.setBeDatosEnvioAsistencia(item);
                   beDatosEnvioTipoNotaLocalDataSource.getDatosExportarRelAsistenciaAlumnos(item.getAsistenciaAlumnos(), new ObjectCallBack<BEDatosEnvioTipoNota>() {
                       @Override
                       public void onResponse(boolean success, BEDatosEnvioTipoNota item) {
                           geDatosEnvioAsistencia.setBeDatosEnvioTipoNota(item);
                           if(!geDatosEnvioAsistencia.getBeDatosEnvioAsistencia().getAsistenciaAlumnos().isEmpty()){
                               callBack.onResponse(true, geDatosEnvioAsistencia);
                           }else {
                               callBack.onResponse(false, null);
                           }
                       }
                   });
               }catch (Exception e){
                   callBack.onResponse(false, null);
               }
           }
       });

    }

    @Override
    public void changeEstado(final GEDatosEnvioAsistencia item, int syncFlag, final SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            BEDatosEnvioAsistencia beDatosEnvioAsistencia = item.getBeDatosEnvioAsistencia();
            TransaccionUtils.fastStoreListSyncFlagUpdate(AsistenciaSesionAlumnoC.class, beDatosEnvioAsistencia.getAsistenciaAlumnos(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(JustificacionC.class, beDatosEnvioAsistencia.getJustificacion(), syncFlag, databaseWrapper, false);

            BEDatosEnvioTipoNota beDatosEnvioTipoNota = item.getBeDatosEnvioTipoNota();
            TransaccionUtils.fastStoreListSyncFlagUpdate(TipoNotaC.class, beDatosEnvioTipoNota.getTipoNota(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(ValorTipoNotaC.class, beDatosEnvioTipoNota.getValorTipoNota(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdateRel(RelProgramaEducativoTipoNota.class, beDatosEnvioTipoNota.getRel_Programa_Educativo_TipoNota(), syncFlag, databaseWrapper, false);
            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }

    }

    @Override
    public void saveDatos(final GEDatosEnvioAsistencia item, final SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            BEDatosEnvioAsistencia beDatosEnvioAsistencia = item.getBeDatosEnvioAsistencia();
            TransaccionUtils.fastStoreListComprobacionSave(AsistenciaSesionAlumnoC.class, beDatosEnvioAsistencia.getAsistenciaAlumnos(), false);
            TransaccionUtils.fastStoreListComprobacionSave(JustificacionC.class, beDatosEnvioAsistencia.getJustificacion(), false);

            BEDatosEnvioTipoNota beDatosEnvioTipoNota = item.getBeDatosEnvioTipoNota();
            TransaccionUtils.fastStoreListComprobacionSave(TipoNotaC.class, beDatosEnvioTipoNota.getTipoNota(), false);
            TransaccionUtils.fastStoreListComprobacionSave(ValorTipoNotaC.class, beDatosEnvioTipoNota.getValorTipoNota(), false);
            TransaccionUtils.fastStoreListComprobacionSave(RelProgramaEducativoTipoNota.class, beDatosEnvioTipoNota.getRel_Programa_Educativo_TipoNota(), false);
            TransaccionUtils.fastStoreListInsert(EscalaEvaluacion.class, beDatosEnvioTipoNota.getEscalaEvaluacion(), databaseWrapper, false);
            TransaccionUtils.fastStoreListInsert(T_RN_MAE_TIPO_EVALUACION.class, beDatosEnvioTipoNota.getRn_mae_tipo_evaluacion(), databaseWrapper, false);
            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            e.printStackTrace();
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }
}
