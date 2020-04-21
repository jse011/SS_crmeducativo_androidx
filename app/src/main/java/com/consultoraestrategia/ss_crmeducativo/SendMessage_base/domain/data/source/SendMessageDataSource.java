package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source;

import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks.CreateMessageCallback;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks.GetIntencionListCallback;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks.GetPersonasRelacionadasCallback;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.CreateMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetIntencionListUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetPersonasRelacionasUseCase;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface SendMessageDataSource {

    void getPersonasRelacionadasList(GetPersonasRelacionasUseCase.RequestValues requestValues, GetPersonasRelacionadasCallback callback);


    void getIntencionList(GetIntencionListUseCase.RequestValues requestValues, GetIntencionListCallback getIntencionListCallback);

    void createMessage(CreateMessageUseCase.RequestValues requestValues, CreateMessageCallback createMessageCallback);
}
