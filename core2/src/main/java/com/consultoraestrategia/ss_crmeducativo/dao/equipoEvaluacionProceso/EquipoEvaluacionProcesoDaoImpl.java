package com.consultoraestrategia.ss_crmeducativo.dao.equipoEvaluacionProceso;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by kike on 14/05/2018.
 */

public class EquipoEvaluacionProcesoDaoImpl extends BaseDaoImpl<EquipoEvaluacionProcesoC,EquipoEvaluacionProcesoC_Table> implements EquipoEvaluacionProcesoDao {

    private static EquipoEvaluacionProcesoDaoImpl mInstance;

    private EquipoEvaluacionProcesoDaoImpl() {
    }

    public static EquipoEvaluacionProcesoDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new EquipoEvaluacionProcesoDaoImpl();
        }
        return mInstance;
    }

    @Override
    public List<EquipoEvaluacionProcesoC> getEquipoEvaluacionProcesoRubrica(List<String> rubroEvalProcesoKeyList, List<String> equipoKeyList) {
        return SQLite.select()
                .from(EquipoEvaluacionProcesoC.class)
                .where(EquipoEvaluacionProcesoC_Table.rubroEvalProcesoId.in(rubroEvalProcesoKeyList))
                .and(EquipoEvaluacionProcesoC_Table.equipoId.in(equipoKeyList))
                .orderBy(EvaluacionProcesoC_Table.fechaAccion.desc())
                .queryList();
    }

    @Override
    protected Class<EquipoEvaluacionProcesoC> getEntityClass() {
        return EquipoEvaluacionProcesoC.class;
    }

    @Override
    protected Class<EquipoEvaluacionProcesoC_Table> getTableclass() {
        return EquipoEvaluacionProcesoC_Table.class;
    }
}
