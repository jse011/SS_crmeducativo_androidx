package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source.callbacks;

import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.ui.entities.DataImportantMessageUI;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public interface GetDataImportantMessageCallback {
    void onGetDataImportantMessageLoaded(DataImportantMessageUI dataImportantMessageUI);

    void onError(String error);

}
