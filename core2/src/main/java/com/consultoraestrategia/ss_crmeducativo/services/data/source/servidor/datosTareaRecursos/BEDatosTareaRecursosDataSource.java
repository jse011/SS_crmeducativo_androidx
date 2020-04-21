package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosTareaRecursos;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosTareasRecursos;

import java.util.List;

/**
 * Created by SCIEV on 25/05/2018.
 */

public interface BEDatosTareaRecursosDataSource extends ServiceDataSource<GEDatosTareasRecursos>{
    void getGEDatosTareasRecursosDatosExportar(List<String> rubroEvalKeyList, ObjectCallBack<GEDatosTareasRecursos> callBack);
    void comprobarListaCambiosTareaRecursos(List<String> tareaRecusoIdList, DosObjectCallBack<List<Long>, List<Long>> dosObjectCallBack);
}
