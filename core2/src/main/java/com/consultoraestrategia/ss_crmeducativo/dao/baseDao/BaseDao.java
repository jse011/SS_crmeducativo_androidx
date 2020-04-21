package com.consultoraestrategia.ss_crmeducativo.dao.baseDao;

import com.raizlabs.android.dbflow.sql.language.SQLOperator;

import java.util.List;

/**
 * Created by @stevecampos on 17/04/2018.
 */

public interface BaseDao<T> {
    T get(String id);

    List<T> getAll();

    boolean create(T entity);

    boolean update(T entity);

    boolean remove(T entity);

    T getWithQuery(SQLOperator sqlOperator);

    List<T> getListWithQuery(SQLOperator... operators);
}
