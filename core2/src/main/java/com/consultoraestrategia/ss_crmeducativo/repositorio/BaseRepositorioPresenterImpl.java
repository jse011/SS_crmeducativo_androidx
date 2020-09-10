package com.consultoraestrategia.ss_crmeducativo.repositorio;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.repositorio.bundle.RepositorioTBunble;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.DownloadCancelUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.FragmentoTipo;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.ConvertirPathRepositorioUpload;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.GetUrlRepositorioArchivo;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UpdateRepositorio;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.CloneImagenCompress;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UploadRepositorio;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;
import com.consultoraestrategia.ss_crmeducativo.util.YouTubeHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class BaseRepositorioPresenterImpl extends BaseFragmentPresenterImpl<RepositorioView> implements RepositorioPresenter {
    private String TAG = BaseRepositorioPresenterImpl.class.getSimpleName();
    protected List<UpdateRepositorioFileUi> updateRepositorioFileUiList = new ArrayList<>();
    protected List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();
    protected DowloadImageUseCase dowloadImageUseCase;
    private ConvertirPathRepositorioUpload convertirPathRepositorioUpload;
    protected UploadRepositorio uploadRepositorio;
    private GetUrlRepositorioArchivo getUrlRepositorioArchivo;
    protected UpdateRepositorio updateRepositorioDowload;
    private int cantidadMaxima = 5;
    protected RepositorioUi repositorio = RepositorioUi.ARCHIVO;
    protected String urlServidor;
    private FragmentoTipo fragmentoTipo = FragmentoTipo.SUBIDA_DESCARGA_IMAGEN;
    boolean isInternet = false;
    private String colorCurso;
    protected CloneImagenCompress cloneImagenCompress;


    public BaseRepositorioPresenterImpl(UseCaseHandler handler, Resources res,
                                        DowloadImageUseCase dowloadImageUseCase,
                                        ConvertirPathRepositorioUpload convertirPathRepositorioUpload,
                                        UploadRepositorio uploadRepositorioJustificacion,
                                        GetUrlRepositorioArchivo getUrlRepositorioArchivo,
                                        UpdateRepositorio updateRepositorioDowload,
                                        CloneImagenCompress cloneImagenCompress) {
        super(handler, res);
        this.dowloadImageUseCase = dowloadImageUseCase;
        this.convertirPathRepositorioUpload = convertirPathRepositorioUpload;
        this.uploadRepositorio = uploadRepositorioJustificacion;
        this.getUrlRepositorioArchivo = getUrlRepositorioArchivo;
        this.updateRepositorioDowload = updateRepositorioDowload;
        this.cloneImagenCompress = cloneImagenCompress;
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {
    }

    @Override
    public void onCreateView() {
        super.onCreateView();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        showListArchivos(repositorioFileUiList);
    }

    protected void showListArchivos(List<RepositorioFileUi> repositorioFileUiList) {
        try {
            Collections.sort(repositorioFileUiList, new Comparator<RepositorioFileUi>() {
                @Override
                public int compare(RepositorioFileUi o1, RepositorioFileUi o2) {
                    Long obj1 =  o1.getFechaAccionArchivo();
                    Long obj2 =  o2.getFechaAccionArchivo();

                    int compare =obj2.compareTo(obj1);

                    if(o2.isSelect())compare = 1;

                    return compare;
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        showListArchivosSelecte();
        if(view!=null)view.showListArchivos(repositorioFileUiList);
    }

    private void showListArchivosSelecte(){
        List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();
        for(RepositorioFileUi repositorioFileUi : this.repositorioFileUiList){
            if(repositorioFileUi.isSelect())repositorioFileUiList.add(repositorioFileUi);
        }
        if(view!=null)view.showListArchivosSelecte(repositorioFileUiList);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClickDownload(final RepositorioFileUi repositorioFileUi) {

        handler.execute(dowloadImageUseCase, new DowloadImageUseCase.RequestValues(repositorioFileUi),
                new UseCase.UseCaseCallback<UseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(UseCase.ResponseValue response) {
                        if(response instanceof DowloadImageUseCase.ResponseProgressValue){
                            DowloadImageUseCase.ResponseProgressValue responseProgressValue = (DowloadImageUseCase.ResponseProgressValue) response;
                           if(view!=null) view.setUpdateProgress(responseProgressValue.getRepositorioFileUi(), responseProgressValue.getCount());
                            Log.d(TAG,":( :" + repositorioFileUi.getNombreArchivo() +" = " + responseProgressValue.getRepositorioFileUi().getNombreArchivo());
                        }
                        if(response instanceof DowloadImageUseCase.ResponseSuccessValue){
                            final DowloadImageUseCase.ResponseSuccessValue responseValue = (DowloadImageUseCase.ResponseSuccessValue) response;
                            final RepositorioFileUi repositorioFileUi = responseValue.getRepositorioFileUi();
                            updateRepositorioDowload.execute(new UpdateRepositorio.Request(repositorioFileUi.getArchivoId(),repositorioFileUi.getTipoFileU() ,repositorioFileUi.getPath(), repositorio), new UseCaseSincrono.Callback<Boolean>() {
                                @Override
                                public void onResponse(boolean success, Boolean value) {
                                    if(success){
                                       setUpdate(responseValue.getRepositorioFileUi());
                                        if(view!=null)view.leerArchivo(repositorioFileUi.getPath());
                                    }else {
                                        repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.ERROR_DESCARGA);
                                        setUpdate(responseValue.getRepositorioFileUi());
                                    }
                                }
                            });

                            Log.d(TAG,"pathLocal:" + responseValue.getRepositorioFileUi().getPath());
                        }
                        if(response instanceof DowloadImageUseCase.ResponseErrorValue){
                            DowloadImageUseCase.ResponseErrorValue responseErrorValue = (DowloadImageUseCase.ResponseErrorValue) response;
                            setUpdate(responseErrorValue.getRepositorioFileUi());
                        }
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }

    protected void setUpdate(RepositorioFileUi repositorioFileUi) {
        if(view!=null) view.setUpdate(repositorioFileUi);
        showListArchivosSelecte();
    }


    @Override
    public void onClickArchivo(RepositorioFileUi repositorioFileUi) {
        if(repositorioFileUi instanceof UpdateRepositorioFileUi){
            if(view!=null)view.leerArchivo(repositorioFileUi.getPath());
        }else if(repositorioFileUi.getEstadoFileU()==RepositorioEstadoFileU.DESCARGA_COMPLETA){
            if(view!=null)view.leerArchivo(repositorioFileUi.getPath());
        }
    }

    @Override
    public void onClickClose(RepositorioFileUi repositorioFileUi) {
        repositorioFileUi.setCancel(true);
    }

    @Override
    public void onClickAddFile() {
        if(view!=null)isInternet = view.isInternetAvailable();
        if(!isInternet){
            if(view!=null)view.showMessageNotConnection();
            return;
        }
        if(view!=null)view.onShowPickDoc(cantidadMaxima, new ArrayList<UpdateRepositorioFileUi>());
    }

    @Override
    public void onSalirSelectPiket(ArrayList<String> photoPaths) {

        convertirPathRepositorioUpload.execute(photoPaths, new UseCaseSincrono.Callback<List<UpdateRepositorioFileUi>>() {
            @Override
            public void onResponse(boolean succes, List<UpdateRepositorioFileUi> value) {
                if(succes){
                    updateRepositorioFileUiList.addAll(value);
                    repositorioFileUiList.addAll(0, value);
                    for (UpdateRepositorioFileUi updateRepositorioFileUi : value) {
                        onClickUpload(updateRepositorioFileUi);
                    }
                    showListArchivos(repositorioFileUiList);
                }
            }
        });

    }

    @Override
    public void onClickUpload(final UpdateRepositorioFileUi updateRepositorioFileUi) {
        switch (updateRepositorioFileUi.getTipoFileU()){
            case IMAGEN:
                String path = cloneImagenCompress.SaveImage(updateRepositorioFileUi.getPath());
                if(!TextUtils.isEmpty(path))updateRepositorioFileUi.setPath(path);
                break;
        }
        _onClickUpload(updateRepositorioFileUi);
    }

    public void _onClickUpload(final UpdateRepositorioFileUi updateRepositorioFileUi) {
        handler.execute(uploadRepositorio, new UploadRepositorio.Request(urlServidor, repositorio, updateRepositorioFileUi), new UseCase.UseCaseCallback<UploadRepositorio.Response>() {
            @Override
            public void onSuccess(UploadRepositorio.Response response) {
                Log.d(TAG, "success : " +response.isSuccess() + "item: " + response.getUpdateRepositorioFileUi());
                if(response instanceof UploadRepositorio.ResponseProgressValue){
                    UploadRepositorio.ResponseProgressValue responseProgressValue = (UploadRepositorio.ResponseProgressValue) response;
                    if(view!=null) view.setUpdateProgress(responseProgressValue.getUpdateRepositorioFileUi(), responseProgressValue.getCount());
                }else {
                    if(response.isSuccess()){
                        int position = repositorioFileUiList.indexOf(updateRepositorioFileUi);
                        if(position!=-1){
                            repositorioFileUiList.remove(position);
                            updateRepositorioFileUiList.remove(updateRepositorioFileUi);
                            RepositorioFileUi repositorioFileUi = new RepositorioFileUi();
                            repositorioFileUi.setArchivoId(updateRepositorioFileUi.getArchivoId());
                            repositorioFileUi.setPath(updateRepositorioFileUi.getPath());
                            repositorioFileUi.setNombreArchivo(updateRepositorioFileUi.getNombreArchivo());
                            repositorioFileUi.setNombreRecurso(updateRepositorioFileUi.getNombreRecurso());
                            repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
                            repositorioFileUi.setUrl(updateRepositorioFileUi.getUrl());
                            repositorioFileUi.setTipoFileU(updateRepositorioFileUi.getTipoFileU());
                            repositorioFileUi.setSelect(true);
                            repositorioFileUi.setFechaAccionArchivo(new Date().getTime());
                            repositorioFileUiList.add(position,repositorioFileUi);
                            showListArchivos(repositorioFileUiList);
                        }

                    }else {
                        Log.d(TAG, "updateList");
                        if(view!=null)view.updateList(response.getUpdateRepositorioFileUi());
                    }
                }
            }

            @Override
            public void onError() {

            }
        });

    }


    @Override
    public void onClickRemover(UpdateRepositorioFileUi updateRepositorioFileUi) {
        DownloadCancelUi downloadCancelUi = updateRepositorioFileUi.getDownloadCancelUi();
        if(downloadCancelUi!=null)downloadCancelUi.setCancel(true);
        updateRepositorioFileUiList.remove(updateRepositorioFileUi);
        repositorioFileUiList.remove(updateRepositorioFileUi);
        showListArchivos(repositorioFileUiList);
    }

    @Override
    public void onClickClose(UpdateRepositorioFileUi updateRepositorioFileUi) {
        DownloadCancelUi downloadCancelUi = updateRepositorioFileUi.getDownloadCancelUi();
        if(downloadCancelUi!=null)downloadCancelUi.setCancel(true);
    }

    @Override
    public void changeList(List<RepositorioFileUi> repositorioFileUiList) {
        Log.d(TAG, "repositorioFileUiList " + repositorioFileUiList.size());
        this.repositorioFileUiList.clear();
        this.updateRepositorioFileUiList.clear();
        this.repositorioFileUiList.addAll(repositorioFileUiList);
        showListArchivos(repositorioFileUiList);
    }

    @Override
    public void onClickCheck(RepositorioFileUi repositorioFileUi) {
        List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();
        for(RepositorioFileUi itemRepositorioFileUi : this.repositorioFileUiList){
            if(!(itemRepositorioFileUi instanceof UpdateRepositorioFileUi)){
                if(itemRepositorioFileUi.isSelect()){
                    repositorioFileUiList.add(itemRepositorioFileUi);
                }
            }
        }
        showListArchivosSelecte();
       // if(view!=null)view.callbackChange(repositorioFileUiList);
    }

    @Override
    public List<RepositorioFileUi> getListFiles() {
        List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();
        for(RepositorioFileUi itemRepositorioFileUi : this.repositorioFileUiList){
            if(!(itemRepositorioFileUi instanceof UpdateRepositorioFileUi)){
                if(itemRepositorioFileUi.isSelect()){
                    repositorioFileUiList.add(itemRepositorioFileUi);
                }
            }
        }
        return repositorioFileUiList;
    }

    @Override
    public void onClickAddMultimedia() {
        if(view!=null)isInternet = view.isInternetAvailable();
        if(!isInternet){
            if(view!=null)view.showMessageNotConnection();
            return;
        }
        switch (fragmentoTipo){
            case SUBIDA_DESCARGA_IMAGEN:
                if(view!=null)view.showPickPhoto(false, cantidadMaxima, new ArrayList<UpdateRepositorioFileUi>());
                break;
            case SUBIDA_DESCARGA_ARCHIVOS_VINCULOS:
                if(view!=null)view.showPickPhoto(true, cantidadMaxima, new ArrayList<UpdateRepositorioFileUi>());
                break;
            case SUBIDA_DESCARGA_IMAGEN_VIDEOS:
                if(view!=null)view.showPickPhoto(true, cantidadMaxima, new ArrayList<UpdateRepositorioFileUi>());
                break;
            case SUBIDA_DESCARGA__ARCHIVOS:
                if(view!=null)view.showPickPhoto(true, cantidadMaxima, new ArrayList<UpdateRepositorioFileUi>());
                break;
        }
    }

    @Override
    public void onClickAddVinculo() {
        if(view!=null)isInternet = view.isInternetAvailable();
        if(!isInternet){
            if(view!=null)view.showMessageNotConnection();
            return;
        }
        Log.d(TAG, "onClickAddVinculo: " );
        RepositorioFileUi repositorioFileUi = new RepositorioFileUi();
        repositorioFileUi.setArchivoId(IdGenerator.generateId());
        repositorioFileUi.setFechaAccionArchivo(new Date().getTime());
        repositorioFileUi.setFechaCreacionRecuros(new Date().getTime());
        if(view!=null)view.showDialogaddVinculo(repositorioFileUi);
    }

    @Override
    public void onClickAceptarDialogVinculo(RepositorioFileUi repositorioFileUi) {
        if(TextUtils.isEmpty(repositorioFileUi.getUrl()))return;
        String idVideo = YouTubeHelper.extractVideoIdFromUrl(repositorioFileUi.getUrl());
        Log.d("BaseRepositoriFra", "dvideo: " + idVideo);
        if(!TextUtils.isEmpty(idVideo)){
            repositorioFileUi.setTipoFileU(RepositorioTipoFileU.YOUTUBE);
            Log.d(TAG,"Video");
        }else{
            repositorioFileUi.setTipoFileU(RepositorioTipoFileU.VINCULO);
            Log.d(TAG,"vinculo");
        }
        repositorioFileUi.setSelect(true);
        repositorioFileUi.setDescripcion(repositorioFileUi.getUrl());
        repositorioFileUi.setUrl(repositorioFileUi.getUrl());
        repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
        repositorioFileUiList.add(repositorioFileUi);
        showListArchivos(repositorioFileUiList);
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        RepositorioTBunble tBunble = RepositorioTBunble.clone(extras);
        if(tBunble!=null){
            repositorio = tBunble.getRepositorio();
            fragmentoTipo = tBunble.getFragmentoTipo();
            colorCurso = tBunble.getColorCurso();
            Log.d("BaseRepositoriFra", "asd"+ fragmentoTipo.name());
        }
        getUrl();
        initFragment();
        changeColor();

    }

    private void changeColor(){
        if (view!=null)view.changeColorButtom(colorCurso);
    }

    private void initFragment() {
        if(fragmentoTipo!=null){
            switch (fragmentoTipo){
                case SUBIDA_DESCARGA_ARCHIVOS_VINCULOS:
                    if(view!=null)view.showFloadButtonAddFile();
                    if(view!=null)view.showFloadButtonAddMultimedia();
                    if(view!=null)view.showFloadButtonAddVinculo();
                    break;
                case SUBIDA_DESCARGA_IMAGEN_VIDEOS:
                    if(view!=null)view.hideFloadButtonAddFile();
                    if(view!=null)view.showFloadButtonAddMultimedia();
                    if(view!=null)view.hideFloadButtonAddVinculo();
                    break;
                case SUBIDA_DESCARGA_IMAGEN:
                    if(view!=null)view.hideFloadButtonAddFile();
                    if(view!=null)view.showFloadButtonAddMultimedia();
                    if(view!=null)view.hideFloadButtonAddVinculo();
                    break;
                case SUBIDA_DESCARGA__ARCHIVOS:
                    if(view!=null)view.showFloadButtonAddFile();
                    if(view!=null)view.showFloadButtonAddMultimedia();
                    if(view!=null)view.hideFloadButtonAddVinculo();
                    break;
            }
        }
        if(repositorio!=null){
            switch (repositorio){
                case ARCHIVO_RUBRO:
                    if(view!=null)view.changeVersionTwoList();
                    break;
                case ARCHIVO:
                    if(view!=null)view.changeVersionTwoList();
                    break;
            }
        }

    }


    private void getUrl() {
        getUrlRepositorioArchivo.execute(new GetUrlRepositorioArchivo.Request(), new UseCaseSincrono.Callback<String>() {
            @Override
            public void onResponse(boolean success, String value) {
                if (success) {
                    urlServidor = value;
                } else {
                    showMessage("error en la dirección del repositorio");
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        for(UpdateRepositorioFileUi updateRepositorioFileUi : updateRepositorioFileUiList){
            DownloadCancelUi downloadCancelUi = updateRepositorioFileUi.getDownloadCancelUi();
            if(downloadCancelUi!=null)downloadCancelUi.setCancel(true);
        }
        super.onDestroyView();
    }
}
