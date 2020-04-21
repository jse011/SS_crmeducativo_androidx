package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.local;

import com.consultoraestrategia.ss_crmeducativo.entities.CanalDestinoEstadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.InteraccionTextual;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeIntencionItemC;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajePredIntencion;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajePredeterminado;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajePredeterminadoDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajeUsuarioC;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.local.ServiceLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.ConsultaUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.BEDatosEnvioMensajeriaDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.List;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosEnvioMensajeriaLocalDataSource extends ServiceLocalDataSource<BEDatosEnvioMensajeria> implements BEDatosEnvioMensajeriaDataSource {

    @Override
    public void getDatosExportar(ObjectCallBack<BEDatosEnvioMensajeria> callBack) {
        BEDatosEnvioMensajeria beDatosEnvioMensajeria = new BEDatosEnvioMensajeria();

        List<MensajeUsuarioC> mensajeUsuario = ConsultaUtils.getChangeItemsTable(MensajeUsuarioC.class);//no se modifico el id de tipo int a String
        List<MensajeIntencionItemC> mensajeIntencionItem = ConsultaUtils.getChangeItemsTable(MensajeIntencionItemC.class);
        //List<UsuarioCanalComunicacion> usCanalComunicacion = ConsultaUtils.getChangeItemsTable(UsuarioCanalComunicacion.class);
        //List<CanalComunicacion> canalComunicacion = ConsultaUtils.getChangeItemsTable(CanalComunicacion.class);
        List<CanalDestinoEstadoC> canalDestinoEstado = ConsultaUtils.getChangeItemsTable(CanalDestinoEstadoC.class);
        List<MensajeC> mensajes = ConsultaUtils.getChangeItemsTable(MensajeC.class);
        //List<Intencion> intenciones = ConsultaUtils.getChangeItemsTable(Intencion.class);
        //List<IntencionItem> intencionItems = ConsultaUtils.getChangeItemsTable(IntencionItem.class);
        //List<ListaUsuario> listaUsuarios = ConsultaUtils.getChangeItemsTable(ListaUsuario.class);
        //List<ListaUsuarioDetalle> listUsuarioDetalle = ConsultaUtils.getChangeItemsTable(ListaUsuarioDetalle.class);
        List<MensajePredeterminado> listaMensajePredeterminado = ConsultaUtils.getChangeItemsTable(MensajePredeterminado.class);
        List<MensajePredeterminadoDetalle> listMensajePredeterminadoDetalle = ConsultaUtils.getChangeItemsTable(MensajePredeterminadoDetalle.class);
        List<MensajePredIntencion> listMensajePredIntencion = ConsultaUtils.getChangeItemsTable(MensajePredIntencion.class);

        beDatosEnvioMensajeria.setMensajeUsuario(mensajeUsuario);
        beDatosEnvioMensajeria.setMensajeIntencionItem(mensajeIntencionItem);
        beDatosEnvioMensajeria.setCanalDestinoEstado(canalDestinoEstado);
        beDatosEnvioMensajeria.setMensajes(mensajes);
        beDatosEnvioMensajeria.setListaMensajePredeterminado(listaMensajePredeterminado);
        beDatosEnvioMensajeria.setListMensajePredeterminadoDetalle(listMensajePredeterminadoDetalle);
        beDatosEnvioMensajeria.setListMensajePredIntencion(listMensajePredIntencion);

        beDatosEnvioMensajeria.setListInteraccionTextual(ConsultaUtils.getChangeItemsTable(InteraccionTextual.class));
        callBack.onResponse(true, beDatosEnvioMensajeria);
    }


    @Override
    public void saveDatos(BEDatosEnvioMensajeria item, SuccessCallBack callBack) {

        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();

            TransaccionUtils.fastStoreListComprobacionSave(MensajeUsuarioC.class, item.getMensajeUsuario(), false);
            TransaccionUtils.fastStoreListComprobacionSave(MensajeIntencionItemC.class, item.getMensajeIntencionItem(), false);
            TransaccionUtils.fastStoreListComprobacionSave(CanalDestinoEstadoC.class, item.getCanalDestinoEstado(), false);
            TransaccionUtils.fastStoreListSetData(MensajeC.class, item.getMensajes(), databaseWrapper, false);
            TransaccionUtils.fastStoreListSetData(MensajePredeterminado.class, item.getListaMensajePredeterminado(), databaseWrapper, false);
            TransaccionUtils.fastStoreListSetData(MensajePredeterminadoDetalle.class, item.getListMensajePredeterminadoDetalle(), databaseWrapper, false);
            TransaccionUtils.fastStoreListSetData(MensajePredIntencion.class, item.getListMensajePredIntencion(), databaseWrapper, false);
            TransaccionUtils.fastStoreListSetData(InteraccionTextual.class, item.getListInteraccionTextual(), databaseWrapper, false);

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
    public void changeEstado(BEDatosEnvioMensajeria item, int syncFlag, SuccessCallBack callBack) {

        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            TransaccionUtils.fastStoreListSyncFlagUpdate(MensajeUsuarioC.class, item.getMensajeUsuario(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(MensajeIntencionItemC.class, item.getMensajeIntencionItem(),syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(CanalDestinoEstadoC.class, item.getCanalDestinoEstado(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(MensajeC.class, item.getMensajes(), syncFlag, databaseWrapper, false);
            TransaccionUtils.fastStoreListSyncFlagUpdate(InteraccionTextual.class, item.getListInteraccionTextual(), syncFlag, databaseWrapper, false);

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
    public void getDatosExportarMesajeria(ObjectCallBack<BEDatosEnvioMensajeria> callBack) {

    }

}
