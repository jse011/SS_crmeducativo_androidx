package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.repositorioComentario;

import android.content.res.Resources;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.repositorio.BaseRepositorioPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.CloneImagenCompress;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.ConvertirPathRepositorioUpload;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.GetUrlRepositorioArchivo;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UpdateRepositorio;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UploadRepositorio;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase.MoverArchivosAlaCarpetaTarea;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RecursosUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;

import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivoTareaPresenterImpl extends BaseRepositorioPresenterImpl {
    private MoverArchivosAlaCarpetaTarea moverArchivosAlaCarpetaTarea;
    public RepositorioArchivoTareaPresenterImpl(UseCaseHandler handler, Resources res,
                                                DowloadImageUseCase dowloadImageUseCase,
                                                ConvertirPathRepositorioUpload convertirPathRepositorioUpload,
                                                UploadRepositorio uploadRepositorioJustificacion,
                                                GetUrlRepositorioArchivo getUrlRepositorioArchivo,
                                                UpdateRepositorio updateRepositorioDowload,
                                                CloneImagenCompress cloneImagenCompress,
                                                MoverArchivosAlaCarpetaTarea moverArchivosAlaCarpetaTarea) {
        super(handler, res, dowloadImageUseCase, convertirPathRepositorioUpload, uploadRepositorioJustificacion, getUrlRepositorioArchivo, updateRepositorioDowload, cloneImagenCompress);
        this.moverArchivosAlaCarpetaTarea= moverArchivosAlaCarpetaTarea;
    }


    @Override
    public void changeList(List<RepositorioFileUi> repositorioFileUiList) {
        super.changeList(repositorioFileUiList);
    }

    @Override
    public void onClickCheck(RepositorioFileUi repositorioFileUi) {
        repositorioFileUiList.remove(repositorioFileUi);
        showListArchivos(repositorioFileUiList);
        // if(view!=null)view.callbackChange(repositorioFileUiList);
    }

    @Override
    public List<RepositorioFileUi> getListFiles() {
        return super.getListFiles();
    }

    @Override
    public void onClickUpload(UpdateRepositorioFileUi updateRepositorioFileUi) {
        super.onClickUpload(updateRepositorioFileUi);
    }

    @Override
    public void onClickDownload(RepositorioFileUi repositorioFileUi) {
        handler.execute(dowloadImageUseCase, new DowloadImageUseCase.RequestValues(repositorioFileUi),
                new UseCase.UseCaseCallback<UseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(UseCase.ResponseValue response) {
                        if(response instanceof DowloadImageUseCase.ResponseProgressValue){
                            Log.d(getTag(),"DowloadImageUseCase.ResponseProgressValue");
                            DowloadImageUseCase.ResponseProgressValue responseProgressValue = (DowloadImageUseCase.ResponseProgressValue) response;
                            if(view!=null) view.setUpdateProgress(responseProgressValue.getRepositorioFileUi(), responseProgressValue.getCount());
                            //Log.d(TAG,":( :" + repositorioFileUi.getNombreArchivo() +" = " + responseProgressValue.getRepositorioFileUi().getNombreArchivo());
                        }
                        if(response instanceof DowloadImageUseCase.ResponseSuccessValue){
                            Log.d(getTag(),"DowloadImageUseCase.ResponseSuccessValue");
                            final DowloadImageUseCase.ResponseSuccessValue responseValue = (DowloadImageUseCase.ResponseSuccessValue) response;
                            final RepositorioFileUi repositorioFileUi = responseValue.getRepositorioFileUi();
                            updateRepositorioDowload.execute(new UpdateRepositorio.Request(repositorioFileUi.getArchivoId(),repositorioFileUi.getTipoFileU() ,repositorioFileUi.getPath(), repositorio), new UseCaseSincrono.Callback<Boolean>() {
                                @Override
                                public void onResponse(boolean success, Boolean value) {
                                    if(success){
                                        moverArchivosAlaCarpetaTarea(responseValue.getRepositorioFileUi());
                                        setUpdate(responseValue.getRepositorioFileUi());
                                        if(view!=null)view.leerArchivo(repositorioFileUi.getPath());

                                    }else {
                                        repositorioFileUi.setEstadoFileU(RepositorioEstadoFileU.ERROR_DESCARGA);
                                        setUpdate(responseValue.getRepositorioFileUi());
                                    }
                                }
                            });

                        }
                        if(response instanceof DowloadImageUseCase.ResponseErrorValue){
                            Log.d(getTag(),"DowloadImageUseCase.ResponseErrorValue");
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

    private void moverArchivosAlaCarpetaTarea(RepositorioFileUi repositorioFileUi) {
        List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();
        repositorioFileUiList.add(repositorioFileUi);

        String nombreCurso = "";
        String titulo = "";
        if(repositorioFileUi instanceof RecursosUI){
            TareasUI tareasUI = ((RecursosUI)repositorioFileUi).getTarea();
            if(tareasUI!=null){
                titulo = tareasUI.getTituloTarea();
                nombreCurso=tareasUI.getNombreCurso();
            }
        }
        moverArchivosAlaCarpetaTarea.execute(nombreCurso,titulo, repositorioFileUiList );
    }
}
