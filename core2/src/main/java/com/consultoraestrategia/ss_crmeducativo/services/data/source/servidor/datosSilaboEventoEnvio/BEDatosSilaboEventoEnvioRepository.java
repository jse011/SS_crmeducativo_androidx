package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSilaboEventoEnvio;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosSilaboEventoEnvio;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosSilaboEventoEnvioRepository extends ServiceRepository<GEDatosSilaboEventoEnvio,ServiceDataSource<GEDatosSilaboEventoEnvio>> {
    private static BEDatosSilaboEventoEnvioRepository mInstance;

    public BEDatosSilaboEventoEnvioRepository(ServiceDataSource<GEDatosSilaboEventoEnvio> localDataSource, ServiceDataSource<GEDatosSilaboEventoEnvio> remoteDataSource, UtilServidor utilServidor) {
        super(localDataSource, remoteDataSource, utilServidor);
    }

    public static BEDatosSilaboEventoEnvioRepository getInstance(ServiceDataSource<GEDatosSilaboEventoEnvio> localDataSource, ServiceDataSource<GEDatosSilaboEventoEnvio> remoteDataSource, UtilServidor utilServidor) {
        if (mInstance == null) {
            mInstance = new BEDatosSilaboEventoEnvioRepository(localDataSource, remoteDataSource, utilServidor);
        }
        return mInstance;
    }
}
