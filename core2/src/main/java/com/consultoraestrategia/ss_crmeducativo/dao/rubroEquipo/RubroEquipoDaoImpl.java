package com.consultoraestrategia.ss_crmeducativo.dao.rubroEquipo;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by SCIEV on 31/08/2018.
 */

public class RubroEquipoDaoImpl extends BaseDaoImpl<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC, T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table> implements RubroEquipoDao {

    private static RubroEquipoDaoImpl mInstance;

    private RubroEquipoDaoImpl() {
    }

    public static RubroEquipoDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new RubroEquipoDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> getEntityClass() {
        return T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class;
    }

    @Override
    protected Class<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table> getTableclass() {
        return T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.class;
    }

    @Override
    public List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> getEquipoRubroList(List<String> rubroEvalProcesoKeyList) {
        return  SQLite.select()
                .from(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.class)
                .where(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC_Table.rubroEvalProcesoId.in(rubroEvalProcesoKeyList))
                .queryList();
    }
}
