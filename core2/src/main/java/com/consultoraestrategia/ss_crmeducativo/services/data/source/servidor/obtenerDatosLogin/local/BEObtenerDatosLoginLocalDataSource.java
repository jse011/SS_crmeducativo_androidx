package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerDatosLogin.local;

import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.local.ServiceLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

/**
 * Created by SCIEV on 18/05/2018.
 */

public class BEObtenerDatosLoginLocalDataSource extends ServiceLocalDataSource<BEObtenerDatosLogin> {

    @Override
    public void changeEstado(BEObtenerDatosLogin item, int syncFlag, SuccessCallBack callBack) {
            callBack.onResponse(true);
    }

    @Override
    public void saveDatos(BEObtenerDatosLogin item, SuccessCallBack callBack) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            TransaccionUtils.fastStoreListSetData(Persona.class, item.getPersonas(), databaseWrapper, false);
            TransaccionUtils.fastStoreListSetData(Relaciones.class, item.getRelaciones(), databaseWrapper, false);

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
