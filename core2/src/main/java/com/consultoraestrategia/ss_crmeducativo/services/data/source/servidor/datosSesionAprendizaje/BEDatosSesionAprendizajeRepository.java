package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosSesionAprendizaje;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosSesionAprendizajeRepository extends ServiceRepository<BEDatosSesionAprendizaje,BEDatosSesionAprendizajeDataSource> implements BEDatosSesionAprendizajeDataSource {
    private static BEDatosSesionAprendizajeRepository mInstance;

    public BEDatosSesionAprendizajeRepository(BEDatosSesionAprendizajeDataSource localDataSource, BEDatosSesionAprendizajeDataSource remoteDataSource, UtilServidor utilServidor) {
        super(localDataSource, remoteDataSource, utilServidor);
    }

    public static BEDatosSesionAprendizajeRepository getInstance(BEDatosSesionAprendizajeDataSource localDataSource, BEDatosSesionAprendizajeDataSource remoteDataSource, UtilServidor utilServidor) {
        if (mInstance == null) {
            mInstance = new BEDatosSesionAprendizajeRepository(localDataSource, remoteDataSource, utilServidor);
        }
        return mInstance;
    }

    @Override
    public void comprobarCambiosSesionAprendizaje(final int sesionAprendizajeId, final DosObjectCallBack<Long, Long> dosObjectCallBack) {
        remoteDataSource.comprobarCambiosSesionAprendizaje(sesionAprendizajeId, new DosObjectCallBack<Long, Long>() {
            @Override
            public void onResponse(boolean success, final Long f_Servidor, Long fechaVacia) {
                if(success){
                    localDataSource.comprobarCambiosSesionAprendizaje(sesionAprendizajeId, new DosObjectCallBack<Long, Long>() {
                        @Override
                        public void onResponse(boolean success, Long fechaVacia, Long f_Local) {
                            dosObjectCallBack.onResponse(success, f_Servidor, f_Local);
                        }
                    });
                }else {
                    dosObjectCallBack.onResponse(false, 0L, 0L );
                }
            }
        });
    }


    @Override
    public void getDatosImportarListSesionPorUnidades(BEVariables importar, final ObjectCallBack<BEDatosSesionAprendizaje> callBack) {
        remoteDataSource.getDatosImportarListSesionPorUnidades(importar, new ObjectCallBack<BEDatosSesionAprendizaje>() {
            @Override
            public void onResponse(boolean success, final BEDatosSesionAprendizaje item) {
                try {
                    saveDatos(item, new SuccessCallBack() {
                        @Override
                        public void onResponse(boolean success) {
                            if(success){
                                callBack.onResponse(true, item);
                            }else {
                                callBack.onResponse(false, null);
                            }
                        }
                    });
                }catch (Exception e){
                    callBack.onResponse(false, null);
                }
            }
        });
    }
}
