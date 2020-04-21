package com.consultoraestrategia.ss_crmeducativo.services.data.source.base;

import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.CancelCall;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;

/**
 * Created by SCIEV on 16/05/2018.
 */

public interface ServiceDataSource<T> {

    interface ObjectCallBack<T>{
        void onResponse(boolean success, T item);
    }

    interface DosObjectCallBack<T, R>{
        void onResponse(boolean success, T item, R respuesta);
    }

    interface getCallBackList<T>{
        void onResponse(boolean success, List<T> items);
    }

    interface saveCallBackList<T>{
        void onSave(boolean success, List<T> items);
    }

    interface SuccessCallBack{
        void onResponse(boolean success);
    }

    interface TransactionCallBack{
        void onResponse(boolean success, Transaction transaction);
    }

    RetrofitCancel<T> getDatosLogin(String usuarioId, ObjectCallBack<T> callBack);

    void getDatosExportar(ObjectCallBack<T> callBack);

    void SendDatos(T item, Class<BERespuesta> respuestaClass, DosObjectCallBack<T, BERespuesta> callBack);

    void changeEstado(T item, int syncFlag, SuccessCallBack callBack);

    void saveDatos(T item, SuccessCallBack callBack);

    <V extends BEVariables>void getDatosImportar(V importar, ObjectCallBack<T> callBack);

}
