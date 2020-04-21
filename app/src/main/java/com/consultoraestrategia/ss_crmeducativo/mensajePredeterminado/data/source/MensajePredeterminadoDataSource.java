package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source;

import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.callbacks.GetMensajesPredeterminadosCallback;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.domain.usecases.GetMensajePredeterninadoUIListUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.domain.usecases.GetDataImportantMessageUseCase;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface MensajePredeterminadoDataSource {

    void getDataImportantMessage(GetDataImportantMessageUseCase.RequestValues requestValues, GetMensajesPredeterminadosCallback callback);


    void getMesnajesPredternidadosUIList(GetMensajePredeterninadoUIListUseCase.RequestValues requestValues, GetMensajesPredeterminadosCallback getMensajesPredeterminadosCallback);

}
