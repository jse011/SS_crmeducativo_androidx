package com.consultoraestrategia.ss_crmeducativo.dbflowCompat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.structure.QueryModelAdapter;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.List;

public class DbflowCompat  {


    private boolean cachingEnabled = true;
    private String query;

    public DbflowCompat(From from) {
        this.query = from.getQuery();
    }

    public DbflowCompat(Where where) {
        this.query = where.getQuery();
    }

    @NonNull
    public <QueryClass> List<QueryClass> queryCustomList(@NonNull Class<QueryClass> queryModelClass, DatabaseWrapper wrapper) {
        FlowLog.log(FlowLog.Level.V, "Executing query: " + query);
        QueryModelAdapter<QueryClass> adapter = FlowManager.getQueryModelAdapter(queryModelClass);
        return cachingEnabled
                ? adapter.getListModelLoader().load(wrapper,query)
                : adapter.getNonCacheableListModelLoader().load(wrapper,query);
    }

    @Nullable
    public <QueryClass> QueryClass queryCustomSingle(@NonNull Class<QueryClass> queryModelClass) {
        FlowLog.log(FlowLog.Level.V, "Executing query: " + query);
        QueryModelAdapter<QueryClass> adapter = FlowManager.getQueryModelAdapter(queryModelClass);
        return cachingEnabled
                ? adapter.getSingleModelLoader().load(query)
                : adapter.getNonCacheableSingleModelLoader().load(query);
    }

    @NonNull
    public <TModel> DbflowCompat disableCaching() {
        cachingEnabled = false;
        return this;
    }
}
