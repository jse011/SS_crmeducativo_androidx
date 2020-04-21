package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosTareaRecursos;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosTareasRecursos;

import java.util.List;

/**
 * Created by SCIEV on 25/05/2018.
 */

public class BEDatosTareaRecursosRepository extends ServiceRepository<GEDatosTareasRecursos, BEDatosTareaRecursosDataSource> implements BEDatosTareaRecursosDataSource {

    private static BEDatosTareaRecursosRepository mInstance;

    public BEDatosTareaRecursosRepository(BEDatosTareaRecursosDataSource localDataSource, BEDatosTareaRecursosDataSource remoteDataSource, UtilServidor utilServidor) {
        super(localDataSource, remoteDataSource, utilServidor);
    }


    public static BEDatosTareaRecursosRepository getInstance(BEDatosTareaRecursosDataSource localDataSource, BEDatosTareaRecursosDataSource remoteDataSource, UtilServidor utilServidor) {
        if (mInstance == null) {
            mInstance = new BEDatosTareaRecursosRepository(localDataSource, remoteDataSource,utilServidor);
        }
        return mInstance;
    }

    @Override
    public void getGEDatosTareasRecursosDatosExportar(List<String> rubroEvalKeyList, ObjectCallBack<GEDatosTareasRecursos> callBack) {
        localDataSource.getGEDatosTareasRecursosDatosExportar(rubroEvalKeyList,callBack);
    }

    @Override
    public void comprobarListaCambiosTareaRecursos(List<String> tareaRecusoIdList, DosObjectCallBack<List<Long>, List<Long>> dosObjectCallBack) {

    }
}
