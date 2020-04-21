package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source;

import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source.callbacks.GetDataImportantMessageCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source.callbacks.GetRubrosListCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.domain.usecases.GetDataImportantMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.domain.usecases.GetNotasRubrosStringUseCase;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface SendMessageTareaDataSource {

    void getDataImportantMessage(GetDataImportantMessageUseCase.RequestValues requestValues, GetDataImportantMessageCallback callback);


    void getRubrosStringList(GetNotasRubrosStringUseCase.RequestValues requestValues, GetRubrosListCallback getIntencionListCallback);

}
