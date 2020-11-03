package com.consultoraestrategia.ss_crmeducativo.services.data.remote;

import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEGuardarEntidadesGlobal;

/**
 * Created by Jse on 30/12/2018.
 */

public interface ServiceRemoteDataRepository {
    interface RespuestaCallBack<T, R>{
        void onResponse(boolean success, T item, R respuesta);
    }
    void SendDatosGlobalSimple(BEGuardarEntidadesGlobal item,RespuestaCallBack<BEGuardarEntidadesGlobal, BERespuesta> callBack);
    void SendDatosGlobalUpdateContratoAlumno(BEGuardarEntidadesGlobal item,RespuestaCallBack<BEGuardarEntidadesGlobal, BERespuesta> callBack);
}
