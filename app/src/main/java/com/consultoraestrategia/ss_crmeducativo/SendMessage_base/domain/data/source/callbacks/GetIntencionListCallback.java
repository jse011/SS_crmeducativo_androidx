package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks;

import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.IntencionUI;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public interface GetIntencionListCallback {
    void onListLoaded(List<IntencionUI> intencionUIList);

    void onError(String error);

}
