package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source;

import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.callbacks.GetMensajesPredeterminadosCallback;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.local.MensajePredeterminadoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.remote.MensajePredeterninadoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.domain.usecases.GetMensajePredeterninadoUIListUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.domain.usecases.GetDataImportantMessageUseCase;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public class MensajePredeterminadoRepository implements MensajePredeterminadoDataSource {

    private static MensajePredeterminadoRepository mInstance;
    private MensajePredeterminadoLocalDataSource localDataSource;
    private MensajePredeterninadoRemoteDataSource remoteDataSource;


    private MensajePredeterminadoRepository(MensajePredeterminadoLocalDataSource localDataSource, MensajePredeterninadoRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static MensajePredeterminadoRepository getInstance(MensajePredeterminadoLocalDataSource localDataSource, MensajePredeterninadoRemoteDataSource remoteDataSource) {
        if (mInstance == null) {
            mInstance = new MensajePredeterminadoRepository(localDataSource, remoteDataSource);
        }
        return mInstance;
    }


    @Override
    public void getDataImportantMessage(GetDataImportantMessageUseCase.RequestValues requestValues, GetMensajesPredeterminadosCallback callback) {
        localDataSource.getDataImportantMessage(requestValues, callback);
    }

    @Override
    public void getMesnajesPredternidadosUIList(GetMensajePredeterninadoUIListUseCase.RequestValues requestValues, GetMensajesPredeterminadosCallback callback) {
        localDataSource.getMesnajesPredternidadosUIList(requestValues, callback);
    }


}
