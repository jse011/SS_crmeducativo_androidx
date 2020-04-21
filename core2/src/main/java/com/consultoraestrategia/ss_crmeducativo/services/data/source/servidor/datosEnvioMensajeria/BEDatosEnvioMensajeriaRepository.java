package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosEnvioMensajeriaRepository extends ServiceRepository<BEDatosEnvioMensajeria, BEDatosEnvioMensajeriaDataSource> implements BEDatosEnvioMensajeriaDataSource {
    private static final String TAG = BEDatosEnvioMensajeriaRepository.class.getSimpleName();
    private static BEDatosEnvioMensajeriaRepository mInstance;

    public BEDatosEnvioMensajeriaRepository(BEDatosEnvioMensajeriaDataSource localDataSource, BEDatosEnvioMensajeriaDataSource remoteDataSource, UtilServidor utilServidor) {
        super(localDataSource, remoteDataSource, utilServidor);
    }

    public static BEDatosEnvioMensajeriaRepository getInstance(BEDatosEnvioMensajeriaDataSource localDataSource, BEDatosEnvioMensajeriaDataSource remoteDataSource, UtilServidor utilServidor) {
        if (mInstance == null) {
            mInstance = new BEDatosEnvioMensajeriaRepository(localDataSource, remoteDataSource, utilServidor);
        }
        return mInstance;
    }

    @Override
    public void getDatosExportarMesajeria(final ObjectCallBack<BEDatosEnvioMensajeria> callBack) {
        localDataSource.getDatosExportar(new ObjectCallBack<BEDatosEnvioMensajeria>() {
            @Override
            public void onResponse(boolean success, BEDatosEnvioMensajeria item) {
                if(success){
                    SendDatos(item, BERespuesta.class, new DosObjectCallBack<BEDatosEnvioMensajeria, BERespuesta>() {
                        @Override
                        public void onResponse(boolean success, BEDatosEnvioMensajeria item, BERespuesta respuesta) {
                            try {
                                callBack.onResponse(respuesta.isCommit(), item);
                            }catch (Exception e){
                                callBack.onResponse(false, null);
                            }
                        }
                    });
                }else {
                    //con esta validacion es para saver si la lista esta vacia y no se exporto
                    callBack.onResponse(true,null);
                }

            }
        });
    }

}
