package com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import java.util.List;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public class TiposDaoImpl extends BaseIntegerDaoImpl<Tipos, Tipos_Table> implements TiposDao {

    private static TiposDao mInstance;

    private TiposDaoImpl() {
    }


    public static TiposDao getInstance() {
        if (mInstance == null) {
            mInstance = new TiposDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return Tipos_Table.tipoId;
    }

    @Override
    protected Class<Tipos> getEntityClass() {
        return Tipos.class;
    }

    @Override
    protected Class<Tipos_Table> getTableclass() {
        return Tipos_Table.class;
    }


    @Override
    public List<Tipos> getFormaEvaluacionList() {
        return getListWithQuery(
                Tipos_Table.concepto.is("Forma Evaluacion"),
                Tipos_Table.objeto.is("T_RN_MAE_RUBRO_EVALUACION_PROCESO")
        );
    }
}
