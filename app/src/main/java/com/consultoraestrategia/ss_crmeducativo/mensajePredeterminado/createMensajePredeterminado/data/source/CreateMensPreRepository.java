package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source;

import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source.callbacks.CreateMensajePredeterminadoCallback;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source.local.CreateMensPreLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source.remote.CreateMensPreRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.domain.usecases.CreateMensajePredeterminadoUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.domain.usecases.GetDataImportantMessageUseCase;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public class CreateMensPreRepository implements CreateMensPreDataSource {

    private static CreateMensPreRepository mInstance;
    private CreateMensPreLocalDataSource localDataSource;
    private CreateMensPreRemoteDataSource remoteDataSource;


    private CreateMensPreRepository(CreateMensPreLocalDataSource localDataSource, CreateMensPreRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static CreateMensPreRepository getInstance(CreateMensPreLocalDataSource localDataSource, CreateMensPreRemoteDataSource remoteDataSource) {
        if (mInstance == null) {
            mInstance = new CreateMensPreRepository(localDataSource, remoteDataSource);
        }
        return mInstance;
    }


    @Override
    public void getDataImportantMessage(GetDataImportantMessageUseCase.RequestValues requestValues, CreateMensajePredeterminadoCallback callback) {
        localDataSource.getDataImportantMessage(requestValues, callback);
    }

    @Override
    public void getMesnajesPredternidadosUIList(CreateMensajePredeterminadoUseCase.RequestValues requestValues, CreateMensajePredeterminadoCallback callback) {
        localDataSource.getMesnajesPredternidadosUIList(requestValues, callback);
    }


}
