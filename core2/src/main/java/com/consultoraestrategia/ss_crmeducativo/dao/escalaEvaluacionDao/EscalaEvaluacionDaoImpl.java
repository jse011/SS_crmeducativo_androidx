package com.consultoraestrategia.ss_crmeducativo.dao.escalaEvaluacionDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public class EscalaEvaluacionDaoImpl extends BaseIntegerDaoImpl<EscalaEvaluacion, EscalaEvaluacion_Table> implements EscalaEvaluacionDao{

    private static EscalaEvaluacionDao sInstance = null;

    public static EscalaEvaluacionDao getInstance() {
        if (sInstance == null) {
            sInstance = new EscalaEvaluacionDaoImpl();
        }
        return sInstance;
    }

    private EscalaEvaluacionDaoImpl() {
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return EscalaEvaluacion_Table.escalaEvaluacionId;
    }

    @Override
    protected Class<EscalaEvaluacion> getEntityClass() {
        return EscalaEvaluacion.class;
    }

    @Override
    protected Class<EscalaEvaluacion_Table> getTableclass() {
        return EscalaEvaluacion_Table.class;
    }

    @Override
    public EscalaEvaluacion getEscalaEvalPorTipoNota(String tipoNotaKey) {
        return SQLite.select(Utils.f_allcolumnTable(EscalaEvaluacion_Table.ALL_COLUMN_PROPERTIES))
                .from(EscalaEvaluacion.class)
                .innerJoin(TipoNotaC.class)
                .on(EscalaEvaluacion_Table.escalaEvaluacionId.withTable().eq(TipoNotaC_Table.escalaEvaluacionId.withTable()))
                .where(TipoNotaC_Table.key.withTable().withTable().is(tipoNotaKey))
                .querySingle();
    }

    @Override
    public EscalaEvaluacion getEscalaEvalPorRubrosAsociados(String rnpFormulaKeySec) {
        return SQLite.select(Utils.f_allcolumnTable(EscalaEvaluacion_Table.ALL_COLUMN_PROPERTIES))
                .from(EscalaEvaluacion.class)
                .innerJoin(TipoNotaC.class)
                .on(EscalaEvaluacion_Table.escalaEvaluacionId.withTable().eq(TipoNotaC_Table.escalaEvaluacionId.withTable()))
                .innerJoin(RubroEvaluacionProcesoC.class)
                .on(RubroEvaluacionProcesoC_Table.tipoNotaId.withTable().eq(TipoNotaC_Table.key.withTable()))
                .where(RubroEvaluacionProcesoC_Table.key.withTable().is(rnpFormulaKeySec))
                .querySingle();
    }

}
