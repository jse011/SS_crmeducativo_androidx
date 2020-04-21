package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.MensajePredeterminadoDataSource;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.callbacks.GetMensajesPredeterminadosCallback;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.domain.usecases.GetMensajePredeterninadoUIListUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.domain.usecases.GetDataImportantMessageUseCase;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class MensajePredeterninadoRemoteDataSource implements MensajePredeterminadoDataSource {



    @Override
    public void getDataImportantMessage(GetDataImportantMessageUseCase.RequestValues requestValues, GetMensajesPredeterminadosCallback callback) {

    }

    @Override
    public void getMesnajesPredternidadosUIList(GetMensajePredeterninadoUIListUseCase.RequestValues requestValues, GetMensajesPredeterminadosCallback getMensajesPredeterminadosCallback) {

    }




}
