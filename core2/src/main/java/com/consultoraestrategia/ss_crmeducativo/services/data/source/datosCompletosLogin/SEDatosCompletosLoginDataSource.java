package com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.SEDatosCompletosLogin;

/**
 * Created by SCIEV on 18/05/2018.
 */

public interface SEDatosCompletosLoginDataSource extends ServiceDataSource<SEDatosCompletosLogin>{
    void save(SEDatosCompletosLogin seDatosCompletosLogin, SuccessCallBack callBack);
    void saveImportar(SEDatosCompletosLogin seDatosCompletosLogin, SuccessCallBack callBack);
    void borrarSessionUsuario(SuccessCallBack callBack);
}
