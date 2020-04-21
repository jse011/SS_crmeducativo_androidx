package com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvaluacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvaluacionC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;


/**
 * Created by SCIEV on 24/04/2018.
 */

public class RubroEvaluacionDaoImpl extends BaseDaoImpl<RubroEvaluacionProcesoC, RubroEvaluacionProcesoC_Table> implements RubroEvaluacionDao {
    private static RubroEvaluacionDao mInstance;

    private RubroEvaluacionDaoImpl() {
    }

    public static RubroEvaluacionDao getInstance() {
        if (mInstance == null) {
            mInstance = new RubroEvaluacionDaoImpl();
        }
        return mInstance;
    }


    @Override
    protected Class<RubroEvaluacionProcesoC> getEntityClass() {
        return RubroEvaluacionProcesoC.class;
    }

    @Override
    protected Class<RubroEvaluacionProcesoC_Table> getTableclass() {
        return RubroEvaluacionProcesoC_Table.class;
    }

    @Override
    public List<CriterioRubroEvaluacionC> getCriterio(String rubroEvalProcesoId) {
        return SQLite.select()
                .from(CriterioRubroEvaluacionC.class)
                .where(CriterioRubroEvaluacionC_Table.rubroEvalProcesoId.is(rubroEvalProcesoId))
                .orderBy(CriterioRubroEvaluacionC_Table.valorTipoNotaId.desc())
                .queryList();
    }
}
