package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica.local;

import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodoDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.local.ServiceLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica.BEDatosCargaAcademicaDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCargaAcademica;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

/**
 * Created by SCIEV on 18/05/2018.
 */

public class BEDatosCargaAcademicaLocalDataSource extends ServiceLocalDataSource<BEDatosCargaAcademica> implements BEDatosCargaAcademicaDataSource {

    @Override
    public void getDatosImportarCalendarioPerido(BEVariables importar, ObjectCallBack<BEDatosCargaAcademica> callBack) {

    }

    @Override
    public void saveDatos(BEDatosCargaAcademica item, SuccessCallBack callBack) {

        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            TransaccionUtils.fastStoreListInsert(CalendarioAcademico.class, item.getCalendarioAcademicos(), databaseWrapper, false);
            TransaccionUtils.fastStoreListInsert(PlanEstudios.class, item.getPlanEstudios(), databaseWrapper, false);

            TransaccionUtils.fastStoreListInsert(CalendarioPeriodo.class, item.getCalendarioPeriodos(), databaseWrapper, false);
            TransaccionUtils.fastStoreListInsert(CalendarioPeriodoDetalle.class, item.getObtenerCalendarioPeriodoDetalle(), databaseWrapper, false);
            TransaccionUtils.fastStoreListInsert(CargaCursoCalendarioPeriodo.class, item.getObtenerCargaCursosCalendarioPeriodo(), databaseWrapper, false);

            databaseWrapper.setTransactionSuccessful();
            callBack.onResponse(true);
        } catch (Exception e){
            callBack.onResponse(false);
        }finally {
            databaseWrapper.endTransaction();
        }
    }

    @Override
    public void changeEstado(BEDatosCargaAcademica item, int syncFlag, SuccessCallBack callBack) {
        callBack.onResponse(true);
    }
}
