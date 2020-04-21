package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerRubroEvaluacionProceso;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;

/**
 * Created by kike on 07/06/2018.
 */

public class BEObtenerDatosRubroProcesoRepository extends ServiceRepository<BEDatosRubroEvaluacionProceso, ServiceDataSource<BEDatosRubroEvaluacionProceso>> {
    private BEObtenerDatosRubroProcesoRepository mInstance;

    public BEObtenerDatosRubroProcesoRepository getInstance(ServiceDataSource<BEDatosRubroEvaluacionProceso> localDataSource, ServiceDataSource<BEDatosRubroEvaluacionProceso> remoteDataSource, UtilServidor utilServidor) {
        if (mInstance == null) {
            mInstance = new BEObtenerDatosRubroProcesoRepository(localDataSource, remoteDataSource, utilServidor);
        }
        return mInstance;
    }

    public BEObtenerDatosRubroProcesoRepository(ServiceDataSource<BEDatosRubroEvaluacionProceso> localDataSource, ServiceDataSource<BEDatosRubroEvaluacionProceso> remoteDataSource, UtilServidor utilServidor) {
        super(localDataSource, remoteDataSource, utilServidor);
    }
}
