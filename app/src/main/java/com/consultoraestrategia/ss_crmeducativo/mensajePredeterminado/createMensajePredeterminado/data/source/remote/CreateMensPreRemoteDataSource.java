package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source.CreateMensPreDataSource;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source.callbacks.CreateMensajePredeterminadoCallback;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.domain.usecases.CreateMensajePredeterminadoUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.domain.usecases.GetDataImportantMessageUseCase;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class CreateMensPreRemoteDataSource implements CreateMensPreDataSource {


    @Override
    public void getDataImportantMessage(GetDataImportantMessageUseCase.RequestValues requestValues, CreateMensajePredeterminadoCallback callback) {

    }

    @Override
    public void getMesnajesPredternidadosUIList(CreateMensajePredeterminadoUseCase.RequestValues requestValues, CreateMensajePredeterminadoCallback callback) {



    }


}
