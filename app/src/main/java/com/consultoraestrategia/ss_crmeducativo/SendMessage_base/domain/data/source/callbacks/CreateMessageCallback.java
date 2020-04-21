package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public interface CreateMessageCallback {
    void onCreateMessageLoaded(boolean isSuccess);

    void onError(String error);

}
