package com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.remote;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.SEDatosCompletosLoginDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteSimpleDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.SEDatosCompletosLogin;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class SEDatosCompletosLoginRemoteDataSource extends ServiceRemoteSimpleDataSource<SEDatosCompletosLogin> implements SEDatosCompletosLoginDataSource {

    @Override
    public void save(SEDatosCompletosLogin seDatosCompletosLogin, SuccessCallBack callBack) {

    }

    @Override
    public void saveImportar(SEDatosCompletosLogin seDatosCompletosLogin, SuccessCallBack callBack) {

    }

    @Override
    public void borrarSessionUsuario(SuccessCallBack callBack) {

    }
}
