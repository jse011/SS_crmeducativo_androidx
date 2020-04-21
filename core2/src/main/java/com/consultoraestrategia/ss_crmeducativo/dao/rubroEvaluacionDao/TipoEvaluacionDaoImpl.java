package com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import java.util.List;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public class TipoEvaluacionDaoImpl extends BaseIntegerDaoImpl<T_RN_MAE_TIPO_EVALUACION, T_RN_MAE_TIPO_EVALUACION_Table> implements TipoEvaluacionDao {


    private static TipoEvaluacionDao mInstance;

    private TipoEvaluacionDaoImpl() {
    }

    public static TipoEvaluacionDao getInstance() {
        if (mInstance == null) {
            mInstance = new TipoEvaluacionDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Class<T_RN_MAE_TIPO_EVALUACION> getEntityClass() {
        return T_RN_MAE_TIPO_EVALUACION.class;
    }

    @Override
    protected Class<T_RN_MAE_TIPO_EVALUACION_Table> getTableclass() {
        return T_RN_MAE_TIPO_EVALUACION_Table.class;
    }

    @Override
    public List<T_RN_MAE_TIPO_EVALUACION> getAll() {
        return super.getListWithQuery(T_RN_MAE_TIPO_EVALUACION_Table.estado.is(true));
    }

    @Override
    public T_RN_MAE_TIPO_EVALUACION obtenerRegistroTipoEvaluacion(int keyTipoEvaluacion) {
        return SQLite.select().from(T_RN_MAE_TIPO_EVALUACION.class)
                .where(T_RN_MAE_TIPO_EVALUACION_Table.tipoEvaluacionId.is(keyTipoEvaluacion))
                .querySingle();
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return T_RN_MAE_TIPO_EVALUACION_Table.tipoEvaluacionId;
    }
}
