package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosRubroEvaluacionProceso;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosRubroEvaluacionProceso;

import java.util.List;

/**
 * Created by SCIEV on 25/05/2018.
 */

public interface GEDatosRubroEvaluacionProcesoDataSource extends ServiceDataSource<GEDatosRubroEvaluacionProceso>{
    void getGEDatosRubroEvaluacionProcesoDatosExportar(List<String> rubroEvaluacionKeyList, ObjectCallBack<GEDatosRubroEvaluacionProceso> callBack);
    void comprobarCambiosRubroEvaluacion(String rubroEvalProcesoId, DosObjectCallBack<Long, Long> callBack);
    void getDatosImportarPorCalendarioPeriodo(BEVariables beVariables, ObjectCallBack<GEDatosRubroEvaluacionProceso> objectCallBack);
}
