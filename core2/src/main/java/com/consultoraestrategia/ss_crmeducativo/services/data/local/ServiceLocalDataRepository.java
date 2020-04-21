package com.consultoraestrategia.ss_crmeducativo.services.data.local;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEGuardarEntidadesGlobal;

import java.util.List;

/**
 * Created by Jse on 30/12/2018.
 */

public interface ServiceLocalDataRepository {


    interface SuccessCallBack{
        void onResponse(boolean success);
    }

    interface CallBack<T>{
        void onResponse(boolean success, T item);
    }

    void changeEstadoGlobal(BEGuardarEntidadesGlobal item, BERespuesta respuesta, int syncFlag, SuccessCallBack callBack);

    //void changeEstadoGlobal(GEDatosEnvioAsistencia geDatosEnvioAsistencia, int syncFlag, SuccessCallBack callBack);

    //void changeEstadoGlobal(BEDatosEnvioGrupo geDatosEnvioAsistencia, int syncFlag, SuccessCallBack callBack);

    void saveDatosGlobal(BERespuesta item, ServiceDataSource.SuccessCallBack callBack);

    void getDatosExportarGlobal(CallBack<BEGuardarEntidadesGlobal> callBack);

    BEGuardarEntidadesGlobal getDatosExportarGlobalSimple();

    List<String> comprobrarCambiosBaseDatosDaocenteMentor();
}
