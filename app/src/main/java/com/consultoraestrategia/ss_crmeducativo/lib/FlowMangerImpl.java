package com.consultoraestrategia.ss_crmeducativo.lib;

import android.content.Context;

//import com.raizlabs.android.dbflow.annotation.Database;
//import com.raizlabs.android.dbflow.config.DatabaseConfig;
//import com.raizlabs.android.dbflow.config.FlowConfig;

/**
 * Created by kelvi on 03/03/2017.
 */

public class FlowMangerImpl implements FlowManager {
//    private com.raizlabs.android.dbflow.config.FlowManager flowManager;
//    private Context context;

    public FlowMangerImpl(Context context) {
//        com.raizlabs.android.dbflow.config.FlowManager.init(new FlowConfig.Builder(context)
//                .addDatabaseConfig(new DatabaseConfig.Builder(AppDatabase.class).build())
//                .openDatabasesOnInit(true).build());
//        this.context = context;
    }

    @Override
    public void init() {
//        Toast.makeText(context, "DbFlow Init", Toast.LENGTH_LONG).show();
    }
//
//    @Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
//    public class AppDatabase {
//
//        public static final String NAME = "CRME_1";
//
//        public static final int VERSION = 1;
//    }
}
