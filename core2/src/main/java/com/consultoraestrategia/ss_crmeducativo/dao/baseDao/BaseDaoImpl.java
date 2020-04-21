package com.consultoraestrategia.ss_crmeducativo.dao.baseDao;

import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseRelEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.LocalEntity;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.SQLOperator;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.util.List;

/**
 * Created by @stevecampos on 17/04/2018.
 */

public abstract class BaseDaoImpl<T extends BaseModel, Q extends ModelAdapter<T>> implements BaseDao<T> {

    protected abstract Class<T> getEntityClass();

    protected abstract Class<Q> getTableclass();

    @Override
    public List<T> getAll() {
        return SQLite.select()
                .from(getEntityClass())
                .queryList();
    }

    @Override
    public T get(String id) {
        return getWithQuery(getDefaultSqlOperator().eq(id));
    }

    private Property<String> getDefaultSqlOperator() {
        return new Property<>(getTableclass(), "key");
    }

    private Property<Object> getStateSqlOperator() {
        return new Property<>(getEntityClass(), "state").withTable();
    }

    protected SQLOperator whereStateisDeleted() {
        return getStateSqlOperator().eq(BaseEntity.STATE_DELETED);
    }

    protected SQLOperator whereStateisCreated() {
        return getStateSqlOperator().eq(BaseEntity.STATE_CREATED);
    }


    @Override
    public T getWithQuery(SQLOperator sqlOperator) {
        return SQLite.select()
                .from(getEntityClass())
                .where(sqlOperator)
                .querySingle();
    }

    @Override
    public List<T> getListWithQuery(SQLOperator... operators) {
        return SQLite
                .select()
                .from(getEntityClass())
                .where(operators)
                .queryList();
    }

    @Override
    public boolean create(T entity) {
        if (entity instanceof BaseEntity) {
            ((BaseEntity) entity).setSyncFlag(BaseEntity.FLAG_ADDED);
        } else if (entity instanceof BaseRelEntity) {
            ((BaseRelEntity) entity).setSyncFlag(BaseRelEntity.FLAG_ADDED);
        }
        return entity.save();
    }

    @Override
    public boolean update(T entity) {
        if (entity instanceof BaseEntity) {
            ((BaseEntity) entity).setSyncFlag(BaseEntity.FLAG_UPDATED);
        } else if (entity instanceof BaseRelEntity) {
            ((BaseRelEntity) entity).setSyncFlag(BaseRelEntity.FLAG_UPDATED);
        }
        return entity.save();
    }

    @Override
    public boolean remove(T entity) {
        return entity.delete();
    }

    public From<T> from() {
        return SQLite.select()
                .from(getEntityClass());
    }

    public From<T> from(IProperty[] ALL_COLUMN_PROPERTIES) {
        return SQLite.select(Utils.f_allcolumnTable(ALL_COLUMN_PROPERTIES))
                .from(getEntityClass());
    }

    public static <T extends LocalEntity, Q extends ModelAdapter<T>> List<T> getPendingItems(Class<T> entity, Class<Q> tableKlass) {
        Property<Integer> syncFlag = new Property<Integer>(tableKlass, "syncFlag");
        return SQLite.select()
                .from(entity)
                .where(syncFlag.is(LocalEntity.FLAG_ADDED))
                .or(syncFlag.is(LocalEntity.FLAG_UPDATED))
                .queryList();
    }

    public interface Callback<T> {
        void onSuccess(T result);

        void onError(Throwable error);
    }
}
