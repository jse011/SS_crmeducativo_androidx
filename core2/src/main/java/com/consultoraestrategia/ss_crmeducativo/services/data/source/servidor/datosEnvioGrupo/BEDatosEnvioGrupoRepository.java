package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo;

import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;

import java.util.List;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosEnvioGrupoRepository extends ServiceRepository<BEDatosEnvioGrupo, BEDatosEnvioGrupoDataSource> implements BEDatosEnvioGrupoDataSource{
    private static BEDatosEnvioGrupoRepository mInstance;

    public BEDatosEnvioGrupoRepository(BEDatosEnvioGrupoDataSource localDataSource, BEDatosEnvioGrupoDataSource remoteDataSource, UtilServidor utilServidor) {
        super(localDataSource, remoteDataSource, utilServidor);
    }

    public static BEDatosEnvioGrupoRepository getInstance(BEDatosEnvioGrupoDataSource localDataSource, BEDatosEnvioGrupoDataSource remoteDataSource, UtilServidor utilServidor) {
        if (mInstance == null) {
            mInstance = new BEDatosEnvioGrupoRepository(localDataSource, remoteDataSource, utilServidor);
        }
        return mInstance;
    }

    @Override
    public void getDatosExportarRelRubroEvalProceso(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> evaluacion_proceso_equipocList, ObjectCallBack<BEDatosEnvioGrupo> callBack) {
        localDataSource.getDatosExportarRelRubroEvalProceso(evaluacion_proceso_equipocList, callBack);
    }

    @Override
    public void comprobarCambiosGrupos(final String grupoEquipoId, final DosObjectCallBack<Long, Long> callBack) {
        remoteDataSource.comprobarCambiosGrupos(grupoEquipoId, new DosObjectCallBack<Long, Long>() {
            @Override
            public void onResponse(boolean success, final Long f_Servidor, Long fechaVacia) {
                if(success){
                    localDataSource.comprobarCambiosGrupos(grupoEquipoId, new DosObjectCallBack<Long, Long>() {
                        @Override
                        public void onResponse(boolean success, Long fechaVacia, Long f_Local) {
                            callBack.onResponse(success, f_Servidor, f_Local);
                        }
                    });
                }else {
                    callBack.onResponse(false, 0L, 0L );
                }
            }
        });
    }

    @Override
    public void getDatosImportarPorUsuario(BEVariables beVariables, final ObjectCallBack<BEDatosEnvioGrupo> callBack) {
        remoteDataSource.getDatosImportarPorUsuario(beVariables, new ObjectCallBack<BEDatosEnvioGrupo>() {
            @Override
            public void onResponse(boolean success, final BEDatosEnvioGrupo item) {
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
