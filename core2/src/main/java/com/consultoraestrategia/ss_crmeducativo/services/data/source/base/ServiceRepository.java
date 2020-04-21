package com.consultoraestrategia.ss_crmeducativo.services.data.source.base;

import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.CancelCall;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class ServiceRepository<T, R extends ServiceDataSource<T>> implements ServiceDataSource<T> {


    protected R localDataSource;
    protected R remoteDataSource;
    protected UtilServidor utilServidor;

    public ServiceRepository(R localDataSource, R remoteDataSource, UtilServidor utilServidor) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.utilServidor = utilServidor;
    }


    @Override
    public RetrofitCancel<T> getDatosLogin(String usuarioId, final ObjectCallBack<T> callBackList) {
        return  remoteDataSource.getDatosLogin(usuarioId, new ObjectCallBack<T>() {
            @Override
            public void onResponse(boolean success, T item) {
                callBackList.onResponse(success, item);
            }
        });
    }


    @Override
    public void getDatosExportar(final ObjectCallBack<T> callBack) {
        localDataSource.getDatosExportar(new ObjectCallBack<T>() {
            @Override
            public void onResponse(boolean success, T item) {
                if(success){
                    SendDatos(item, BERespuesta.class, new DosObjectCallBack<T, BERespuesta>() {
                    @Override
                    public void onResponse(boolean success, T item, BERespuesta respuesta) {
                        try {
                            callBack.onResponse(respuesta.isCommit(), item);
                        }catch (Exception e){
                            callBack.onResponse(false, null);
                        }
                    }
                });
                }else {
                    //con esta validacion es para saver si la lista esta vacia y no se exporto
                    callBack.onResponse(true,null);
                }

            }
        });
    }

    @Override
    public void SendDatos(final T item, Class<BERespuesta> respuestaClass, final DosObjectCallBack<T, BERespuesta> callBack) {
        remoteDataSource.SendDatos(item, respuestaClass, new DosObjectCallBack<T, BERespuesta>() {
            @Override
            public void onResponse(boolean success, final T item, final BERespuesta respuesta) {
                try {
                    if(success){
                        changeEstado(item, BaseEntity.FLAG_EXPORTED, new SuccessCallBack() {
                            @Override
                            public void onResponse(boolean success) {
                                callBack.onResponse(success, item, respuesta);
                            }
                        });

                    }else {
                        callBack.onResponse(false, item, respuesta);
                    }
                }catch (Exception e){
                    callBack.onResponse(false, item, respuesta);
                }

            }
        });
    }

    @Override
    public void changeEstado(T item, int syncFlag, SuccessCallBack callBack) {
        localDataSource.changeEstado(item,syncFlag,callBack);
    }

    @Override
    public void saveDatos(T item, SuccessCallBack callBack) {
        localDataSource.saveDatos(item, callBack);
    }

    @Override
    public <V extends BEVariables> void getDatosImportar(V importar, final ObjectCallBack<T> callBack) {
        remoteDataSource.getDatosImportar(importar, new ObjectCallBack<T>() {
            @Override
            public void onResponse(boolean success, final T item) {
                try {

                    saveDatos(item, new SuccessCallBack() {
                        @Override
                        public void onResponse(boolean success) {
                            if(success){
                                callBack.onResponse(true, item);
                            }else {
                                callBack.onResponse(false, null);
                            }
                        }
                    });
                }catch (Exception e){
                    callBack.onResponse(false, null);
                }
            }
        });
    }

}
