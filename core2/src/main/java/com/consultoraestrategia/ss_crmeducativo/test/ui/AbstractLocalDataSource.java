package com.consultoraestrategia.ss_crmeducativo.test.ui;

import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.LocalEntity;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;

/**
 * Created by @stevecampos on 24/01/2018.
 */

public abstract class AbstractLocalDataSource<T extends LocalEntity, Q extends ModelAdapter<T>> {
    protected abstract Class<T> getEntityClass();

    protected abstract Class<Q> getTableclass();

    public interface SaveCallback {
        void onsuccess();
        void onError();
    }


    public List<T> getPendingItems() {
        return getPendingItems(getEntityClass(), getTableclass());
    }

    private SaveCallback saveCallback;
    private void onSave(List<T> itemsUpdated) {
        Log.d(getEntityClass().getSimpleName(), "save...");
        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        FastStoreModelTransaction fastStoreModelTransaction = FastStoreModelTransaction
                .updateBuilder(FlowManager.getModelAdapter(getEntityClass()))
                .addAll(itemsUpdated)
                .build();
        Transaction transaction = database
                .beginTransactionAsync(fastStoreModelTransaction)
                .success(new Transaction.Success() {
                    @Override
                    public void onSuccess(@NonNull Transaction transaction) {
                        Log.d(getEntityClass().getSimpleName(), "save onSucess");
                        if(saveCallback == null)return;
                        saveCallback.onsuccess();
                    }
                })
                .error(new Transaction.Error() {
                    @Override
                    public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                        Log.d(getEntityClass().getSimpleName(), "save onError: " + error);
                        if(saveCallback == null)return;
                        saveCallback.onError();
                    }
                })
                .build();
        transaction.execute();
    }

    public void save(List<T> itemsUpdated) {
        onSave(itemsUpdated);
    }

    public void save(List<T> itemsUpdated,SaveCallback saveCallback) {
        this.saveCallback = saveCallback;
        onSave(itemsUpdated);
    }

    public static <T extends LocalEntity, Q extends ModelAdapter<T>> List<T> getPendingItems(Class<T> entity, Class<Q> tableKlass) {
        Property<Integer> syncFlag = new Property<Integer>(tableKlass, "syncFlag");
        return SQLite.select()
                .from(entity)
                .where(syncFlag.is(LocalEntity.FLAG_ADDED))
                .or(syncFlag.is(LocalEntity.FLAG_UPDATED))
                .queryList();
    }
}
