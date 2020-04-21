package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCargaAcademica;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosCargaAcademicaRepository extends ServiceRepository<BEDatosCargaAcademica, BEDatosCargaAcademicaDataSource> implements BEDatosCargaAcademicaDataSource {
    private static BEDatosCargaAcademicaRepository mInstance;

    public BEDatosCargaAcademicaRepository(BEDatosCargaAcademicaDataSource localDataSource, BEDatosCargaAcademicaDataSource remoteDataSource, UtilServidor utilServidor) {
        super(localDataSource, remoteDataSource, utilServidor);
    }

    public static BEDatosCargaAcademicaRepository getInstance(BEDatosCargaAcademicaDataSource localDataSource, BEDatosCargaAcademicaDataSource remoteDataSource, UtilServidor utilServidor) {
        if (mInstance == null) {
            mInstance = new BEDatosCargaAcademicaRepository(localDataSource, remoteDataSource, utilServidor);
        }
        return mInstance;
    }

    @Override
    public void getDatosImportarCalendarioPerido(BEVariables importar, final ObjectCallBack<BEDatosCargaAcademica> callBack) {
        remoteDataSource.getDatosImportarCalendarioPerido(importar, new ObjectCallBack<BEDatosCargaAcademica>() {
            @Override
            public void onResponse(boolean success, final BEDatosCargaAcademica item) {
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
