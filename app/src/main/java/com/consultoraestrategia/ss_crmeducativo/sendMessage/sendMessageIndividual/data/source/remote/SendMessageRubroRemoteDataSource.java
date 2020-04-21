package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.SendMessageRubroDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.callbacks.GetDataImportantMessageCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.callbacks.GetRubrosListCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.domain.usecases.GetDataImportantMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.domain.usecases.GetNotasRubrosStringUseCase;

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
