package com.consultoraestrategia.ss_crmeducativo.dao.baseDao;

/**
 * Created by @stevecampos on 17/04/2018.
 */

public interface BaseIntegerDao<T> extends BaseDao<T> {
    T get(int id);
}
