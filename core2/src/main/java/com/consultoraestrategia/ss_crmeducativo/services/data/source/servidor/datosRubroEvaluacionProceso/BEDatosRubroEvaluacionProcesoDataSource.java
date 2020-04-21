package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso;


import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;

import java.util.List;

/**
 * Created by SCIEV on 17/05/2018.
 */

public interface BEDatosRubroEvaluacionProcesoDataSource extends ServiceDataSource<BEDatosRubroEvaluacionProceso> {
    void getSimpleDatosExportar(ObjectCallBack<BEDatosRubroEvaluacionProceso> callBack);
    void getRubroEvalDatosExportar(List<String> rubroEvalIdList, ObjectCallBack<BEDatosRubroEvaluacionProceso> callBack);
    void onUpdateEvaluacionFormula(SuccessCallBack callBack);
}
