package com.consultoraestrategia.ss_crmeducativo.dao.unidadAprendizajeDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public class UnidadAprendizajeDaoImpl extends BaseDaoImpl<UnidadAprendizaje, UnidadAprendizaje_Table> implements UnidadAprendizajeDao {

    private static UnidadAprendizajeDao mInstance;

    private UnidadAprendizajeDaoImpl() {
    }


    public static UnidadAprendizajeDao getInstance() {
        if (mInstance == null) {
            mInstance = new UnidadAprendizajeDaoImpl();
        }
        return mInstance;
    }


    @Override
    protected Class<UnidadAprendizaje> getEntityClass() {
        return UnidadAprendizaje.class;
    }

    @Override
    protected Class<UnidadAprendizaje_Table> getTableclass() {
        return UnidadAprendizaje_Table.class;
    }


    @Override
    public UnidadAprendizaje getUnidadAprendizajePorId(int idUnidadAprendizaje) {
        return getWithQuery(UnidadAprendizaje_Table.unidadAprendizajeId.is(idUnidadAprendizaje));
    }

    @Override
    public UnidadAprendizaje getUnidadAprendizajePorSesionId(int sesionAprendizajeId) {
        return SQLite.select(Utils.f_allcolumnTable(UnidadAprendizaje_Table.ALL_COLUMN_PROPERTIES))
                .from(UnidadAprendizaje.class)
                .innerJoin(SesionAprendizaje.class)
                .on(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()
                        .eq(SesionAprendizaje_Table.unidadAprendizajeId.withTable()))
                .where(SesionAprendizaje_Table.sesionAprendizajeId.withTable().eq(sesionAprendizajeId))
                .querySingle();
    }

    @Override
    public String getCantidadAlumnosEvaluadosTarea(String rubroEvalProcesoId) {
        List<EvaluacionProcesoC> evaluacionProcesoCList = SQLite.select()
                .from(EvaluacionProcesoC.class)
                .where(EvaluacionProcesoC_Table.rubroEvalProcesoId.eq(rubroEvalProcesoId))
                .queryList();

        int evaluados = 0;
        for (EvaluacionProcesoC evaluacionProcesoC :evaluacionProcesoCList){
            if(evaluacionProcesoC.getNota() > 0){
                evaluados++;
            }
        }
        int cantidadTotal=evaluacionProcesoCList.size();
        int cantidadNoEval=cantidadTotal-evaluados;
        return evaluados+"/"+cantidadNoEval;
    }
}
