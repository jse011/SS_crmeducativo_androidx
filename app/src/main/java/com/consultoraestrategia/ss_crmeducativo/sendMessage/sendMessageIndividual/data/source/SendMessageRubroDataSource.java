package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source;

import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.callbacks.GetDataImportantMessageCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.callbacks.GetRubrosListCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.domain.usecases.GetDataImportantMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.domain.usecases.GetNotasRubrosStringUseCase;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface SendMessageRubroDataSource {

    void getDataImportantMessage(GetDataImportantMessageUseCase.RequestValues requestValues, GetDataImportantMessageCallback callback);


    void getRubrosStringList(GetNotasRubrosStringUseCase.RequestValues requestValues, GetRubrosListCallback getIntencionListCallback);

}
