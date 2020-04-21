package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosSesionAprendizaje;

/**
 * Created by SCIEV on 17/05/2018.
 */

public interface BEDatosSesionAprendizajeDataSource extends ServiceDataSource<BEDatosSesionAprendizaje>{
    void comprobarCambiosSesionAprendizaje(int sesionAprendizajeId, DosObjectCallBack<Long, Long> dosObjectCallBack);
    void getDatosImportarListSesionPorUnidades(BEVariables importar, ObjectCallBack<BEDatosSesionAprendizaje> objectCallBack);
}
