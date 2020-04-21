package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo;

import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;

import java.util.List;

/**
 * Created by SCIEV on 18/05/2018.
 */

public interface BEDatosEnvioGrupoDataSource extends ServiceDataSource<BEDatosEnvioGrupo> {
    void getDatosExportarRelRubroEvalProceso(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> evaluacion_proceso_equipocList, ObjectCallBack<BEDatosEnvioGrupo> callBack);
    void comprobarCambiosGrupos(String grupoEquipoId, DosObjectCallBack<Long, Long> callBack);
    void getDatosImportarPorUsuario(BEVariables beVariables, ObjectCallBack<BEDatosEnvioGrupo> objectCallBack);
}
