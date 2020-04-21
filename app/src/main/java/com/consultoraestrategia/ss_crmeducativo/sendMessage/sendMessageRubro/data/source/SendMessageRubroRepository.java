package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source;

import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.callbacks.GetRubrosListCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.callbacks.GetDataImportantMessageCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.local.SendMessageRubroLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.data.source.remote.SendMessageRubroRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.domain.usecases.GetNotasRubrosStringUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.domain.usecases.GetDataImportantMessageUseCase;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public class SendMessageRubroRepository implements SendMessageRubroDataSource {

    private static SendMessageRubroRepository mInstance;
    private SendMessageRubroLocalDataSource localDataSource;
    private SendMessageRubroRemoteDataSource remoteDataSource;


    private SendMessageRubroRepository(SendMessageRubroLocalDataSource localDataSource, SendMessageRubroRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static SendMessageRubroRepository getInstance(SendMessageRubroLocalDataSource localDataSource, SendMessageRubroRemoteDataSource remoteDataSource) {
        if (mInstance == null) {
            mInstance = new SendMessageRubroRepository(localDataSource, remoteDataSource);
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
