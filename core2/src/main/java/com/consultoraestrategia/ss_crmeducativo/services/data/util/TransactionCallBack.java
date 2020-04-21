package com.consultoraestrategia.ss_crmeducativo.services.data.util;

import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

/**
 * Created by Jse on 20/05/2018.
 */

public interface TransactionCallBack {
    void onResponse(boolean success, Transaction transaction, String className);
}
