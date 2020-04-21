package com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.local.ServiceLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.local.SEDatosCompletosLoginLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.remote.SEDatosCompletosLoginRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.CancelCall;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.SEDatosCompletosLogin;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class SEDatosCompletosLoginRepository extends ServiceLocalDataSource<SEDatosCompletosLogin> implements SEDatosCompletosLoginDataSource {

    private static SEDatosCompletosLoginRepository mInstance;
    private SEDatosCompletosLoginLocalDataSource localDataSource;
    private SEDatosCompletosLoginRemoteDataSource remoteDataSource;

    public SEDatosCompletosLoginRepository(SEDatosCompletosLoginLocalDataSource localDataSource, SEDatosCompletosLoginRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static SEDatosCompletosLoginRepository getInstance(SEDatosCompletosLoginLocalDataSource localDataSource, SEDatosCompletosLoginRemoteDataSource remoteDataSource) {
        if (mInstance == null) {
            mInstance = new SEDatosCompletosLoginRepository(localDataSource, remoteDataSource);
        }
        return mInstance;
    }


    @Override
    public void save(SEDatosCompletosLogin seDatosCompletosLogin, SuccessCallBack callBack) {
        localDataSource.save(seDatosCompletosLogin, callBack);
    }

    @Override
    public void saveImportar(SEDatosCompletosLogin seDatosCompletosLogin, SuccessCallBack callBack) {
        localDataSource.saveImportar(seDatosCompletosLogin, callBack);
    }

    @Override
    public void borrarSessionUsuario(SuccessCallBack callBack) {
        localDataSource.borrarSessionUsuario(callBack);
    }

}
