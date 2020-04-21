package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.MensajePredIntencion;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajePredeterminado;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajePredeterminadoDetalle;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source.CreateMensPreDataSource;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source.callbacks.CreateMensajePredeterminadoCallback;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.domain.usecases.CreateMensajePredeterminadoUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.domain.usecases.GetDataImportantMessageUseCase;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class CreateMensPreLocalDataSource implements CreateMensPreDataSource {

    private static final String TAG = CreateMensPreLocalDataSource.class.getSimpleName();


    public CreateMensPreLocalDataSource() {

    }


    @Override
    public void getDataImportantMessage(GetDataImportantMessageUseCase.RequestValues requestValues, CreateMensajePredeterminadoCallback callback) {

    }

    @Override
    public void getMesnajesPredternidadosUIList(CreateMensajePredeterminadoUseCase.RequestValues requestValues, CreateMensajePredeterminadoCallback callback) {
        Log.d(TAG, "getMesnajesPredternidadosUIList: " + requestValues.getKeyMensajePred());
        if (requestValues.getKeyMensajePred().equals("")) {
            saveNewMensagePredeterminado(requestValues, callback);
        } else {
            editMensajePredeterminado(requestValues, callback);
        }
    }

    private void editMensajePredeterminado(CreateMensajePredeterminadoUseCase.RequestValues requestValues, CreateMensajePredeterminadoCallback callback) {

        MensajePredeterminadoDetalle mensajePredeterminadoDetalle;
        MensajePredIntencion predIntencion;
        MensajePredeterminado mensajePredeterminado = MensajePredeterminado.getMesnajeById(requestValues.getKeyMensajePred());

        if (mensajePredeterminado != null) {
            mensajePredeterminado.setAlcanceMensajeId(requestValues.getIdAlcanceSelected());
            mensajePredeterminado.setObjetivoMensajeId(requestValues.getIdObjetivoSelected());
            mensajePredeterminado.setAsunto(requestValues.getAsunto());
            mensajePredeterminado.setCabecera(requestValues.getCabecera());
            mensajePredeterminado.setPresentacion(requestValues.getPresentacion());
            mensajePredeterminado.setCuerpo(requestValues.getCuerpo());
            mensajePredeterminado.setDespedida(requestValues.getDespedida());
            mensajePredeterminado.setSyncFlag(MensajePredeterminado.FLAG_UPDATED);
            mensajePredeterminado.setEstadoId(326);
            mensajePredeterminado.save();
            callback.onCreateMensPredLoaded(true);
        } else {
            callback.onError("Error de Consulta en tabla Mensajes Predeterminados");
        }
    }

    private void saveNewMensagePredeterminado(CreateMensajePredeterminadoUseCase.RequestValues requestValues, CreateMensajePredeterminadoCallback callback) {
        MensajePredeterminado mensajePredeterminado = new MensajePredeterminado();
        mensajePredeterminado.setAlcanceMensajeId(requestValues.getIdAlcanceSelected());
        mensajePredeterminado.setObjetivoMensajeId(requestValues.getIdObjetivoSelected());
        mensajePredeterminado.setAsunto(requestValues.getAsunto());
        mensajePredeterminado.setCabecera(requestValues.getCabecera());
        mensajePredeterminado.setPresentacion(requestValues.getPresentacion());
        mensajePredeterminado.setCuerpo(requestValues.getCuerpo());
        mensajePredeterminado.setDespedida(requestValues.getDespedida());
        mensajePredeterminado.setSyncFlag(MensajePredeterminado.FLAG_ADDED);
        mensajePredeterminado.setEstadoId(325);
        mensajePredeterminado.save();
        callback.onCreateMensPredLoaded(true);
    }
}