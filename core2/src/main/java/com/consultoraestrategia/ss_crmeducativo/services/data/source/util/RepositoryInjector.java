package com.consultoraestrategia.ss_crmeducativo.services.data.source.util;

import com.consultoraestrategia.ss_crmeducativo.services.data.local.ServiceLocalDataRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.local.ServiceLocalDataRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.services.data.remote.ServiceRemoteDataRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.remote.ServiceRemoteDataRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.BEDatosSesionAprendizajeRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.local.BEDatosSesionAprendizajeLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.remote.BEDatosSesionAprendizajeRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

/**
 * Created by SCIEV on 5/06/2018.
 */

public class RepositoryInjector {

    public static BEDatosSesionAprendizajeRepository getBEDatosSesionAprendizajeRepository(){
        return BEDatosSesionAprendizajeRepository.getInstance(new BEDatosSesionAprendizajeLocalDataSource(),
                new BEDatosSesionAprendizajeRemoteDataSource(UtilServidor.getInstance()),
                UtilServidor.getInstance());
    }

    public static ServiceRemoteDataRepository getServiceRemoteDataRepository(){
        return new ServiceRemoteDataRepositoryImpl(UtilServidor.getInstance());
    }

    public static ServiceLocalDataRepository getServiceLocalDataRepository(){
        return new ServiceLocalDataRepositoryImpl(InjectorUtils.provideSessionUserDao());
    }
}
