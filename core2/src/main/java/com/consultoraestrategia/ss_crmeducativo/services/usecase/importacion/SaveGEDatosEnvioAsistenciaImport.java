package com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosEnvioAsistencia.GEDatosEnvioAsistenciaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosRubroEvaluacionProceso.GEDatosRubroEvaluacionProcesoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosRubroEvaluacionProceso;

/**
 * Created by SCIEV on 28/05/2018.
 */

public class SaveGEDatosEnvioAsistenciaImport extends UseCase<SaveGEDatosEnvioAsistenciaImport.RequestValues, SaveGEDatosEnvioAsistenciaImport.ResponseValue> {

    private GEDatosEnvioAsistenciaRepository repository;

    public SaveGEDatosEnvioAsistenciaImport(GEDatosEnvioAsistenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

    }

    public static class RequestValues implements UseCase.RequestValues{
        private GEDatosEnvioAsistencia geDatosEnvioAsistencia;

        public RequestValues(GEDatosEnvioAsistencia geDatosEnvioAsistencia) {
            this.geDatosEnvioAsistencia = geDatosEnvioAsistencia;
        }

        public GEDatosEnvioAsistencia getGeDatosEnvioAsistencia() {
            return geDatosEnvioAsistencia;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
    }
}
