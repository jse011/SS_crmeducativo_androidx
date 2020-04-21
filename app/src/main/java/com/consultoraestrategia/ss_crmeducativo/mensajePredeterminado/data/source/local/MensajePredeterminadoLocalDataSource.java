package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.MensajePredeterminado;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.MensajePredeterminadoDataSource;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.data.source.callbacks.GetMensajesPredeterminadosCallback;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.domain.usecases.GetMensajePredeterninadoUIListUseCase;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.intitiesUI.MensajePredeterminadoUI;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.domain.usecases.GetDataImportantMessageUseCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class MensajePredeterminadoLocalDataSource implements MensajePredeterminadoDataSource {

    private static final String TAG = MensajePredeterminadoLocalDataSource.class.getSimpleName();


    public MensajePredeterminadoLocalDataSource() {

    }


    @Override
    public void getDataImportantMessage(GetDataImportantMessageUseCase.RequestValues requestValues, GetMensajesPredeterminadosCallback callback) {

    }

    @Override
    public void getMesnajesPredternidadosUIList(GetMensajePredeterninadoUIListUseCase.RequestValues requestValues, GetMensajesPredeterminadosCallback callback) {

        int objetivoMensaje = requestValues.getIdObjetiveMessage();

        List<MensajePredeterminado> mensajePredeterminadoList = new ArrayList<>();
        if (objetivoMensaje != 327) {
            mensajePredeterminadoList = MensajePredeterminado.getMesnajesListByObtiveIsntDeleted(requestValues.getIdObjetiveMessage());
        } else {
            mensajePredeterminadoList = MensajePredeterminado.getMesnajeListByStateDeleted();
        }

//        List<MensajePredeterminadoDetalle> mensajePredeterminadoDetalles = MensajePredeterminadoDetalle.getAll();
//        List<MensajePredIntencion> mensajePredIntencions;
//        =MensajePredIntencion.getTime();

        Log.d(TAG, "getListaMensajes: " + mensajePredeterminadoList.toString());
        List<MensajePredeterminadoUI> mensajePredeterminadoUIList = new ArrayList<>();

        for (MensajePredeterminado predeterminado : mensajePredeterminadoList) {

            Tipos tipos = Tipos.getTipoById(predeterminado.getObjetivoMensajeId());
            String tipoMensaje = "";
            if (tipos != null) tipoMensaje = tipos.getNombre();

            String cabeceraDb = "";
            String presentacionDb = "";
            String cuerpoDb = "";
            String despedidaDb = "";
            String contenidoJoin ;

            if (!predeterminado.getCabecera().equals("") || predeterminado.getCabecera() != null)
                cabeceraDb = predeterminado.getCabecera();
            if (!predeterminado.getPresentacion().equals("") || predeterminado.getPresentacion() != null)
                presentacionDb = predeterminado.getPresentacion();
            if (!predeterminado.getCuerpo().equals("") || predeterminado.getCuerpo() != null)
                cuerpoDb = predeterminado.getCuerpo();
            if (!predeterminado.getDespedida().equals("") || predeterminado.getDespedida() != null)
                despedidaDb = predeterminado.getDespedida();

            String cabeceraContent = "";
            if (!cabeceraDb.equals("")) {
                cabeceraContent = cabeceraDb + "\n\n";
            }
            String presentacionContent = "";
            if (!presentacionDb.equals("")) {
                presentacionContent = presentacionDb + "\n\n";
            }
            String cuerpoContent = "";
            if (!cuerpoDb.equals("")) {
                cuerpoContent = cuerpoDb + "\n\n";
            }
            String despedidaContent = "";
            if (!despedidaDb.equals("")) {
                despedidaContent = despedidaDb;
            }


            contenidoJoin = cabeceraContent + presentacionContent + cuerpoContent + despedidaContent;
            boolean deleted = false;
            if (predeterminado.getEstadoId() == 327) {
                deleted = true;
            }

            mensajePredeterminadoUIList.add(new MensajePredeterminadoUI(
                    predeterminado.getKey(),
                    predeterminado.getAsunto(),
                    cabeceraDb,
                    presentacionDb,
                    cuerpoDb,
                    despedidaDb,
                    contenidoJoin,
                    tipoMensaje,
                    predeterminado.getAlcanceMensajeId(),
                    predeterminado.getObjetivoMensajeId(),
                    deleted
            ));

        }

        if (mensajePredeterminadoUIList.isEmpty()) callback.onError("Error");
        if (!mensajePredeterminadoUIList.isEmpty())
            callback.onGetMensajePredeterniandoLoaded(mensajePredeterminadoUIList);
    }
}