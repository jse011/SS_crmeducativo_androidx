package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.SendMessageRubroDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.callbacks.GetRubrosListCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.callbacks.GetDataImportantMessageCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.domain.usecases.GetNotasRubrosStringUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.domain.usecases.GetDataImportantMessageUseCase;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class SendMessageRubroRemoteDataSource implements SendMessageRubroDataSource {



    @Override
    public void getDataImportantMessage(GetDataImportantMessageUseCase.RequestValues requestValues, GetDataImportantMessageCallback callback) {

    }


    @Override
    public void getRubrosStringList(GetNotasRubrosStringUseCase.RequestValues requestValues, GetRubrosListCallback getIntencionListCallback) {

    }


}
