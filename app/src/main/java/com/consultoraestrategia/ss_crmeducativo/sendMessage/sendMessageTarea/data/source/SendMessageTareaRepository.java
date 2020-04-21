package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source;

import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source.callbacks.GetDataImportantMessageCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source.callbacks.GetRubrosListCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source.local.SendMessageTareaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.data.source.remote.SendMessageTareaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.domain.usecases.GetDataImportantMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.domain.usecases.GetNotasRubrosStringUseCase;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public class SendMessageTareaRepository implements SendMessageTareaDataSource {

    private static SendMessageTareaRepository mInstance;
    private SendMessageTareaLocalDataSource localDataSource;
    private SendMessageTareaRemoteDataSource remoteDataSource;


    private SendMessageTareaRepository(SendMessageTareaLocalDataSource localDataSource, SendMessageTareaRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static SendMessageTareaRepository getInstance(SendMessageTareaLocalDataSource localDataSource, SendMessageTareaRemoteDataSource remoteDataSource) {
        if (mInstance == null) {
            mInstance = new SendMessageTareaRepository(localDataSource, remoteDataSource);
        }
        return mInstance;
    }


    @Override
    public void getDataImportantMessage(GetDataImportantMessageUseCase.RequestValues requestValues, GetDataImportantMessageCallback callback) {
        localDataSource.getDataImportantMessage(requestValues, callback);
    }


    @Override
    public void getRubrosStringList(GetNotasRubrosStringUseCase.RequestValues requestValues, GetRubrosListCallback callback) {
        localDataSource.getRubrosStringList(requestValues, callback);
    }


}
