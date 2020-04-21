package com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source;

import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.callbacks.GetDataImportantMessageCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.callbacks.GetRubrosListCallback;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.local.SendMessageRubroLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.data.source.remote.SendMessageRubroRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.domain.usecases.GetDataImportantMessageUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.domain.usecases.GetNotasRubrosStringUseCase;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public class SendMessageNormalRepository implements SendMessageRubroDataSource {

    private static SendMessageNormalRepository mInstance;
    private SendMessageRubroLocalDataSource localDataSource;
    private SendMessageRubroRemoteDataSource remoteDataSource;


    private SendMessageNormalRepository(SendMessageRubroLocalDataSource localDataSource, SendMessageRubroRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static SendMessageNormalRepository getInstance(SendMessageRubroLocalDataSource localDataSource, SendMessageRubroRemoteDataSource remoteDataSource) {
        if (mInstance == null) {
            mInstance = new SendMessageNormalRepository(localDataSource, remoteDataSource);
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
