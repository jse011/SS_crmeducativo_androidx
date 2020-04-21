package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source.SendMessageTareaDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source.callbacks.GetDataImportantMessageCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source.callbacks.GetRubrosListCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.domain.usecases.GetDataImportantMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.domain.usecases.GetNotasRubrosStringUseCase;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class SendMessageTareaRemoteDataSource implements SendMessageTareaDataSource {



    @Override
    public void getDataImportantMessage(GetDataImportantMessageUseCase.RequestValues requestValues, GetDataImportantMessageCallback callback) {

    }


    @Override
    public void getRubrosStringList(GetNotasRubrosStringUseCase.RequestValues requestValues, GetRubrosListCallback getIntencionListCallback) {

    }


}
