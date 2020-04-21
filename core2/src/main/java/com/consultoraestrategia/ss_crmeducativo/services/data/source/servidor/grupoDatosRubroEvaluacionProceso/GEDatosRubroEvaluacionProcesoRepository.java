package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosRubroEvaluacionProceso;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosRubroEvaluacionProceso;

import java.util.List;

/**
 * Created by SCIEV on 25/05/2018.
 */

public class GEDatosRubroEvaluacionProcesoRepository extends ServiceRepository<GEDatosRubroEvaluacionProceso, GEDatosRubroEvaluacionProcesoDataSource> implements GEDatosRubroEvaluacionProcesoDataSource{

    private static GEDatosRubroEvaluacionProcesoRepository mInstance;

    public GEDatosRubroEvaluacionProcesoRepository(GEDatosRubroEvaluacionProcesoDataSource localDataSource, GEDatosRubroEvaluacionProcesoDataSource remoteDataSource, UtilServidor utilServidor) {
        super(localDataSource, remoteDataSource, utilServidor);
    }


    public static GEDatosRubroEvaluacionProcesoRepository getInstance(GEDatosRubroEvaluacionProcesoDataSource localDataSource, GEDatosRubroEvaluacionProcesoDataSource remoteDataSource, UtilServidor utilServidor) {
        if (mInstance == null) {
            mInstance = new GEDatosRubroEvaluacionProcesoRepository(localDataSource, remoteDataSource,utilServidor);
        }
        return mInstance;
    }


    @Override
    public void getGEDatosRubroEvaluacionProcesoDatosExportar(List<String> rubroEvaluacionKeyList, ObjectCallBack<GEDatosRubroEvaluacionProceso> callBack) {
        localDataSource.getGEDatosRubroEvaluacionProcesoDatosExportar(rubroEvaluacionKeyList, callBack);
    }

    @Override
    public void comprobarCambiosRubroEvaluacion(final String rubroEvalProcesoId, final DosObjectCallBack<Long,Long> callBack) {
       remoteDataSource.comprobarCambiosRubroEvaluacion(rubroEvalProcesoId, new DosObjectCallBack<Long,Long>() {
           @Override
           public void onResponse(boolean success, final Long f_Servidor, Long fechaVacia) {
               if(success){
                   localDataSource.comprobarCambiosRubroEvaluacion(rubroEvalProcesoId, new DosObjectCallBack<Long, Long>() {
                       @Override
                       public void onResponse(boolean success, Long fechaVacia, Long f_Local) {
                          callBack.onResponse(success,f_Servidor,f_Local);
                       }
                   });
               }else {
                   callBack.onResponse(false, 0L,0L);
               }
           }
       });
    }

    @Override
    public void getDatosImportarPorCalendarioPeriodo(BEVariables beVariables, final ObjectCallBack<GEDatosRubroEvaluacionProceso> callBack) {
        remoteDataSource.getDatosImportarPorCalendarioPeriodo(beVariables, new ObjectCallBack<GEDatosRubroEvaluacionProceso>() {
            @Override
            public void onResponse(boolean success, final GEDatosRubroEvaluacionProceso item) {
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
