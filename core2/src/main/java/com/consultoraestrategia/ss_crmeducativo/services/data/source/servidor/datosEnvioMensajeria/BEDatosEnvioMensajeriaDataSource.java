package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;

public interface BEDatosEnvioMensajeriaDataSource extends ServiceDataSource<BEDatosEnvioMensajeria> {
    void getDatosExportarMesajeria(ObjectCallBack<BEDatosEnvioMensajeria> callBack);
}
