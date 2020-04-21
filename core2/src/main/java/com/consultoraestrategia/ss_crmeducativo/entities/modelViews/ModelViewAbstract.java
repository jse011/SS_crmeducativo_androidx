package com.consultoraestrategia.ss_crmeducativo.entities.modelViews;


import androidx.annotation.NonNull;

import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.SQLOperator;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by SCIEV on 5/03/2018.
 */

public abstract class ModelViewAbstract<T extends BaseModel, E extends ModelViewAbstract> {

    private E find;
    protected Select select;
    protected From<T> from;
    protected Where<T> where;
    protected IProperty[] iProperties;

    protected abstract E getFindInstance();

    ModelViewAbstract() {
        this.find = getFindInstance();
    }

    public E select(IProperty... properties) {
        iProperties = properties;
        _select(properties);
        from = _from();
        where = new Where<T>(from);
        return find;
    }

    public E selectTable(IProperty... properties) {
        iProperties =Utils.f_allcolumnTable( properties);
        _select(iProperties);
        from = _from();
        where = new Where<T>(from);
        return find;
    }

    public E where(@NonNull SQLOperator... conditions) {
        _where(conditions);
        return find;
    }

    public E and(@NonNull SQLOperator condition) {
        where.and(condition);
        return find;
    }

    private void _select(IProperty... properties) {
        select = new Select(properties);
    }

    abstract From<T> _from();


    private void _where(@NonNull SQLOperator... conditions) {
        where = new Where<T>(from, conditions);
    }

    public abstract Where<T> getQuery();

}
