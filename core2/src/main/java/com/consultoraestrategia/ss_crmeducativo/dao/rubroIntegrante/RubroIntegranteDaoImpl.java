package com.consultoraestrategia.ss_crmeducativo.dao.rubroIntegrante;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by SCIEV on 31/08/2018.
 */

public class RubroIntegranteDaoImpl extends BaseDaoImpl<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC, T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table> implements RubroIntegranteDao{

    private static RubroIntegranteDaoImpl mInstance;

    private RubroIntegranteDaoImpl() {
    }

    public static RubroIntegranteDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new RubroIntegranteDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> getEntityClass() {
        return T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class;
    }

    @Override
    protected Class<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table> getTableclass() {
        return T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.class;
    }

    @Override
    public List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> getRubroIntegranteListPorRubroGrupo(String rubroEvaluacionEquipoId) {
        return SQLite.select()
                .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.class)
                .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC_Table.rubroEvaluacionEquipoId.eq(rubroEvaluacionEquipoId))
                .queryList();
    }
}
