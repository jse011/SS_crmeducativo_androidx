package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota;

import com.consultoraestrategia.ss_crmeducativo.entities.AsistenciaSesionAlumnoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;

import java.util.List;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosEnvioTipoNotaRepository extends ServiceRepository<BEDatosEnvioTipoNota, BEDatosEnvioTipoNotaDataSource> implements BEDatosEnvioTipoNotaDataSource {
    private static BEDatosEnvioTipoNotaRepository mInstance;

    public BEDatosEnvioTipoNotaRepository(BEDatosEnvioTipoNotaDataSource localDataSource, BEDatosEnvioTipoNotaDataSource remoteDataSource, UtilServidor utilServidor) {
        super(localDataSource, remoteDataSource, utilServidor);
    }

    public static BEDatosEnvioTipoNotaRepository getInstance(BEDatosEnvioTipoNotaDataSource localDataSource, BEDatosEnvioTipoNotaDataSource remoteDataSource, UtilServidor utilServidor) {
        if (mInstance == null) {
            mInstance = new BEDatosEnvioTipoNotaRepository(localDataSource, remoteDataSource, utilServidor);
        }
        return mInstance;
    }

    @Override
    public void getDatosExportarRelRubroEvalProceso(List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoList, ObjectCallBack<BEDatosEnvioTipoNota> callBack) {
        localDataSource.getDatosExportarRelRubroEvalProceso(rubroEvaluacionProcesoList,callBack);
    }

    @Override
    public void getDatosExportarRelAsistenciaAlumnos(List<AsistenciaSesionAlumnoC> asistenciaAlumnos, ObjectCallBack<BEDatosEnvioTipoNota> objectCallBack) {
        localDataSource.getDatosExportarRelAsistenciaAlumnos(asistenciaAlumnos, objectCallBack);
    }
}
