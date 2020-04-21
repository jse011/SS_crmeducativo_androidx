package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioHorario;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioHorario;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosEnvioHorarioRepository extends ServiceRepository<BEDatosEnvioHorario, ServiceDataSource<BEDatosEnvioHorario>> {
    private static BEDatosEnvioHorarioRepository mInstance;

    public BEDatosEnvioHorarioRepository(ServiceDataSource<BEDatosEnvioHorario> localDataSource, ServiceDataSource<BEDatosEnvioHorario> remoteDataSource, UtilServidor utilServidor) {
        super(localDataSource, remoteDataSource, utilServidor);
    }

    public static BEDatosEnvioHorarioRepository getInstance(ServiceDataSource<BEDatosEnvioHorario> localDataSource, ServiceDataSource<BEDatosEnvioHorario> remoteDataSource, UtilServidor utilServidor) {
        if (mInstance == null) {
            mInstance = new BEDatosEnvioHorarioRepository(localDataSource, remoteDataSource, utilServidor);
        }
        return mInstance;
    }
}
