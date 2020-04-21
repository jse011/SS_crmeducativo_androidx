package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEvaluacionResultado;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEvaluacionResultado;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosEvaluacionResultadoRepository extends ServiceRepository<BEDatosEvaluacionResultado, ServiceDataSource<BEDatosEvaluacionResultado>> {
    private static BEDatosEvaluacionResultadoRepository mInstance;

    public BEDatosEvaluacionResultadoRepository(ServiceDataSource<BEDatosEvaluacionResultado> localDataSource, ServiceDataSource<BEDatosEvaluacionResultado> remoteDataSource, UtilServidor utilServidor) {
        super(localDataSource, remoteDataSource, utilServidor);
    }

    public static BEDatosEvaluacionResultadoRepository getInstance(ServiceDataSource<BEDatosEvaluacionResultado> localDataSource, ServiceDataSource<BEDatosEvaluacionResultado> remoteDataSource, UtilServidor utilServidor) {
        if (mInstance == null) {
            mInstance = new BEDatosEvaluacionResultadoRepository(localDataSource, remoteDataSource, utilServidor);
        }
        return mInstance;
    }
}
