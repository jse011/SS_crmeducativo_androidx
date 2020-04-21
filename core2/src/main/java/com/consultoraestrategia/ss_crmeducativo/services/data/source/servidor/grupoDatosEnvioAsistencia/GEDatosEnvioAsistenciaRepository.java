package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosEnvioAsistencia;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosEnvioAsistencia;

/**
 * Created by SCIEV on 25/05/2018.
 */

public class GEDatosEnvioAsistenciaRepository extends ServiceRepository<GEDatosEnvioAsistencia, GEDatosEnvioAsistenciaDataSource> implements GEDatosEnvioAsistenciaDataSource {

    private static GEDatosEnvioAsistenciaRepository mInstance;

    public GEDatosEnvioAsistenciaRepository(GEDatosEnvioAsistenciaDataSource localDataSource, GEDatosEnvioAsistenciaDataSource remoteDataSource, UtilServidor utilServidor) {
        super(localDataSource, remoteDataSource, utilServidor);
    }


    public static GEDatosEnvioAsistenciaRepository getInstance(GEDatosEnvioAsistenciaDataSource localDataSource, GEDatosEnvioAsistenciaDataSource remoteDataSource, UtilServidor utilServidor) {
        if (mInstance == null) {
            mInstance = new GEDatosEnvioAsistenciaRepository(localDataSource, remoteDataSource,utilServidor);
        }
        return mInstance;
    }
}
