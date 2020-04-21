package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCargaAcademica;

/**
 * Created by SCIEV on 18/05/2018.
 */

public interface BEDatosCargaAcademicaDataSource extends ServiceDataSource<BEDatosCargaAcademica> {
    void getDatosImportarCalendarioPerido(BEVariables importar, ObjectCallBack<BEDatosCargaAcademica> callBack);
}
