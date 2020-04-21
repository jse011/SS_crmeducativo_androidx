package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.local;


import com.consultoraestrategia.ss_crmeducativo.entities.AsistenciaSesionAlumnoC;
import com.consultoraestrategia.ss_crmeducativo.entities.JustificacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.JustificacionC_Table;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.local.ServiceLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.ConsultaUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.BEDatosEnvioAsistenciaDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioAsistencia;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosEnvioAsistenciaLocalDataSource extends ServiceLocalDataSource<BEDatosEnvioAsistencia> implements BEDatosEnvioAsistenciaDataSource {
    @Override
    public void getDatosExportar(ObjectCallBack<BEDatosEnvioAsistencia> callBack) {
        BEDatosEnvioAsistencia beDatosEnvioAsistencia = new BEDatosEnvioAsistencia();
        beDatosEnvioAsistencia.setAsistenciaAlumnos(ConsultaUtils.getChangeItemsTable(AsistenciaSesionAlumnoC.class));
        List<String> asistenciaSesionAlumnoKey = new ArrayList<>();
        for (AsistenciaSesionAlumnoC asistenciaSesionAlumnoC: beDatosEnvioAsistencia.getAsistenciaAlumnos()){
            asistenciaSesionAlumnoKey.add(asistenciaSesionAlumnoC.getKey());
        }
        beDatosEnvioAsistencia.setJustificacion(ConsultaUtils.getChangeItemsTableChild(JustificacionC.class, JustificacionC_Table.asistenciaSesionId.in(asistenciaSesionAlumnoKey)));
        callBack.onResponse(true, beDatosEnvioAsistencia);
    }

    @Override
    public void saveDatos(final BEDatosEnvioAsistencia item, final SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            TransaccionUtils.fastStoreListComprobacionSave(AsistenciaSesionAlumnoC.class, item.getAsistenciaAlumnos(),  false);
            TransaccionUtils.fastStoreListComprobacionSave(JustificacionC.class, item.getJustificacion(), false);
            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            e.printStackTrace();
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }

    @Override
    public void changeEstado(final BEDatosEnvioAsistencia item, final int syncFlag, final SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            TransaccionUtils.fastStoreListSyncFlagUpdate(AsistenciaSesionAlumnoC.class, item.getAsistenciaAlumnos(),syncFlag, databaseWrapper, true);
            TransaccionUtils.fastStoreListSyncFlagUpdate(JustificacionC.class, item.getJustificacion(), syncFlag, databaseWrapper, true);
            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }
}
