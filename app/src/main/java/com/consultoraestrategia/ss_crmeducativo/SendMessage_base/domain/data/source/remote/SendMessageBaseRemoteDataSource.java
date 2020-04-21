package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.remote;


import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.SendMessageDataSource;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks.CreateMessageCallback;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks.GetPersonasRelacionadasCallback;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.CreateMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetIntencionListUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetPersonasRelacionasUseCase;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class SendMessageBaseRemoteDataSource implements SendMessageDataSource {



    @Override
    public void getPersonasRelacionadasList(GetPersonasRelacionasUseCase.RequestValues requestValues, GetPersonasRelacionadasCallback callback) {

    }


    @Override
    public void getIntencionList(GetIntencionListUseCase.RequestValues requestValues, com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks.GetIntencionListCallback getIntencionListCallback) {

    }

    @Override
    public void createMessage(CreateMessageUseCase.RequestValues requestValues, CreateMessageCallback createMessageCallback) {

    }
}
