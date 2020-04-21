package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota;

import com.consultoraestrategia.ss_crmeducativo.entities.AsistenciaSesionAlumnoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;

import java.util.List;

/**
 * Created by SCIEV on 17/05/2018.
 */

public interface BEDatosEnvioTipoNotaDataSource extends ServiceDataSource<BEDatosEnvioTipoNota> {
    void getDatosExportarRelRubroEvalProceso(List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoList, ObjectCallBack<BEDatosEnvioTipoNota> callBack);
    void getDatosExportarRelAsistenciaAlumnos(List<AsistenciaSesionAlumnoC> asistenciaAlumnos, ObjectCallBack<BEDatosEnvioTipoNota> objectCallBack);
}
