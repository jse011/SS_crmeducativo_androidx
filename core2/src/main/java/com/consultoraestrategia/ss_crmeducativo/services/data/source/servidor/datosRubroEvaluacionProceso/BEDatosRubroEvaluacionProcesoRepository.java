package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;

import java.util.List;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosRubroEvaluacionProcesoRepository extends ServiceRepository<BEDatosRubroEvaluacionProceso, BEDatosRubroEvaluacionProcesoDataSource> implements BEDatosRubroEvaluacionProcesoDataSource{
    private static BEDatosRubroEvaluacionProcesoRepository mInstance;

    public BEDatosRubroEvaluacionProcesoRepository(BEDatosRubroEvaluacionProcesoDataSource localDataSource, BEDatosRubroEvaluacionProcesoDataSource remoteDataSource, UtilServidor utilServidor) {
        super(localDataSource, remoteDataSource, utilServidor);
    }

    public static BEDatosRubroEvaluacionProcesoRepository getInstance(BEDatosRubroEvaluacionProcesoDataSource localDataSource, BEDatosRubroEvaluacionProcesoDataSource remoteDataSource, UtilServidor utilServidor)  {
        if (mInstance == null) {
            mInstance = new BEDatosRubroEvaluacionProcesoRepository(localDataSource, remoteDataSource, utilServidor);
        }
        return mInstance;
    }


    @Override
    public void getSimpleDatosExportar(ObjectCallBack<BEDatosRubroEvaluacionProceso> callBack) {
        localDataSource.getDatosExportar(callBack);
    }

    @Override
    public void getRubroEvalDatosExportar(List<String> rubroEvalIdList, ObjectCallBack<BEDatosRubroEvaluacionProceso> callBack) {
        localDataSource.getRubroEvalDatosExportar(rubroEvalIdList, callBack);
    }

    @Override
    public void onUpdateEvaluacionFormula(SuccessCallBack callBack) {
        localDataSource.onUpdateEvaluacionFormula(callBack);
    }

}
