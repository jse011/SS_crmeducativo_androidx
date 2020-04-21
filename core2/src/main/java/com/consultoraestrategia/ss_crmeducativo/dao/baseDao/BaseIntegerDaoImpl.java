package com.consultoraestrategia.ss_crmeducativo.dao.baseDao;

import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

/**
 * Created by @stevecampos on 17/04/2018.
 */

public abstract class BaseIntegerDaoImpl<T extends BaseModel, Q extends ModelAdapter<T>> extends BaseDaoImpl<T, Q> implements BaseIntegerDao<T> {

    protected abstract Property<Integer> getPrimaryKeyProperty();

    @Override
    public T get(int id) {
        return getWithQuery(getPrimaryKeyProperty().eq(id));
    }
}
