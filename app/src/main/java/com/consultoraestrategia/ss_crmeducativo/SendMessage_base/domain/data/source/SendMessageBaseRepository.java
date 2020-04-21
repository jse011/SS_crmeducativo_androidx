package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source;

import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks.CreateMessageCallback;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks.GetIntencionListCallback;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks.GetPersonasRelacionadasCallback;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.local.SendMessageBaseLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.remote.SendMessageBaseRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.CreateMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetIntencionListUseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases.GetPersonasRelacionasUseCase;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public class SendMessageBaseRepository implements SendMessageDataSource {

    private static SendMessageBaseRepository mInstance;
    private SendMessageBaseLocalDataSource localDataSource;
    private SendMessageBaseRemoteDataSource remoteDataSource;


    private SendMessageBaseRepository(SendMessageBaseLocalDataSource localDataSource, SendMessageBaseRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static SendMessageBaseRepository getInstance(SendMessageBaseLocalDataSource localDataSource, SendMessageBaseRemoteDataSource remoteDataSource) {
        if (mInstance == null) {
            mInstance = new SendMessageBaseRepository(localDataSource, remoteDataSource);
        }
        return mInstance;
    }


    @Override
    public void getPersonasRelacionadasList(GetPersonasRelacionasUseCase.RequestValues requestValues, GetPersonasRelacionadasCallback callback) {
        localDataSource.getPersonasRelacionadasList(requestValues, callback);
    }


    @Override
    public void getIntencionList(GetIntencionListUseCase.RequestValues requestValues, GetIntencionListCallback callback) {
        localDataSource.getIntencionList(requestValues, callback);
    }

    @Override
    public void createMessage(CreateMessageUseCase.RequestValues requestValues, CreateMessageCallback createMessageCallback) {
        localDataSource.createMessage(requestValues, createMessageCallback);
    }
}
