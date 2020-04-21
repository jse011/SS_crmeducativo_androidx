package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioAsistencia;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosEnvioAsistenciaRepository extends ServiceRepository<BEDatosEnvioAsistencia, BEDatosEnvioAsistenciaDataSource> implements BEDatosEnvioAsistenciaDataSource {

    private static BEDatosEnvioAsistenciaRepository mInstance;

    public BEDatosEnvioAsistenciaRepository(BEDatosEnvioAsistenciaDataSource localDataSource, BEDatosEnvioAsistenciaDataSource remoteDataSource, UtilServidor utilServidor) {
        super(localDataSource, remoteDataSource, utilServidor);
    }

    public static BEDatosEnvioAsistenciaRepository getInstance(BEDatosEnvioAsistenciaDataSource localDataSource, BEDatosEnvioAsistenciaDataSource remoteDataSource, UtilServidor utilServidor) {
        if (mInstance == null) {
            mInstance = new BEDatosEnvioAsistenciaRepository(localDataSource, remoteDataSource, utilServidor);
        }
        return mInstance;
    }
}
