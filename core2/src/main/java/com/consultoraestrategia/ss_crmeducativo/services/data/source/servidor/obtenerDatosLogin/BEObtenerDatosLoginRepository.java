package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerDatosLogin;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEObtenerDatosLoginRepository extends ServiceRepository<BEObtenerDatosLogin, ServiceDataSource<BEObtenerDatosLogin>> {
    private static BEObtenerDatosLoginRepository mInstance;

    public BEObtenerDatosLoginRepository(ServiceDataSource<BEObtenerDatosLogin> localDataSource, ServiceDataSource<BEObtenerDatosLogin> remoteDataSource, UtilServidor utilServidor) {
        super(localDataSource, remoteDataSource, utilServidor);
    }

    public static BEObtenerDatosLoginRepository getInstance(ServiceDataSource<BEObtenerDatosLogin> localDataSource, ServiceDataSource<BEObtenerDatosLogin> remoteDataSource, UtilServidor utilServidor) {
        if (mInstance == null) {
            mInstance = new BEObtenerDatosLoginRepository(localDataSource, remoteDataSource, utilServidor);
        }
        return mInstance;
    }
}
