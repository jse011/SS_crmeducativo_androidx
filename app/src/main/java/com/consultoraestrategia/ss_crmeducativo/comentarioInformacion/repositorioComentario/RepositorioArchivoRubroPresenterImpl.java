package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.repositorioComentario;

import android.content.res.Resources;
import android.text.TextUtils;
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

import java.util.Date;
import java.util.List;

public class RepositorioArchivoRubroPresenterImpl extends BaseRepositorioPresenterImpl {
    public RepositorioArchivoRubroPresenterImpl(UseCaseHandler handler, Resources res,
                                                DowloadImageUseCase dowloadImageUseCase,
                                                ConvertirPathRepositorioUpload convertirPathRepositorioUpload,
                                                UploadRepositorio uploadRepositorioJustificacion,
                                                GetUrlRepositorioArchivo getUrlRepositorioArchivo,
                                                UpdateRepositorio updateRepositorioDowload,
                                                CloneImagenCompress cloneImagenCompress) {
        super(handler, res, dowloadImageUseCase, convertirPathRepositorioUpload, uploadRepositorioJustificacion, getUrlRepositorioArchivo, updateRepositorioDowload, cloneImagenCompress);
    }

    @Override
    public void onClickUpload(final UpdateRepositorioFileUi updateRepositorioFileUi) {
        Log.d(getClass().getSimpleName(), "onClickUpload");
        switch (updateRepositorioFileUi.getTipoFileU()){
            case IMAGEN:
                String path = cloneImagenCompress.SaveImage(updateRepositorioFileUi.getPath());
                if(!TextUtils.isEmpty(path))updateRepositorioFileUi.setPath(path);
                break;
        }

        handler.execute(uploadRepositorio, new UploadRepositorio.Request(urlServidor, repositorio, updateRepositorioFileUi), new UseCase.UseCaseCallback<UploadRepositorio.Response>() {
            @Override
            public void onSuccess(UploadRepositorio.Response response) {
                Log.d(RepositorioArchivoRubroPresenterImpl.this.getClass().getSimpleName(), "success : " + response.isSuccess() + "item: " + response.getUpdateRepositorioFileUi());
                if (response.isSuccess()) {
                    int position = repositorioFileUiList.indexOf(updateRepositorioFileUi);
                    if (position != -1) {
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
                        repositorioFileUiList.add(position, repositorioFileUi);
                        if (view instanceof RepositorioArchivoViewImpl) {
                            RepositorioArchivoViewImpl repositorioArchivoView = (RepositorioArchivoViewImpl) view;
                            repositorioArchivoView.saveArchivoComentario(repositorioFileUi);
                        }
                        showListArchivos(repositorioFileUiList);
                    }

                } else {
                    Log.d(getClass().getSimpleName(), "updateList");
                    if (view != null) view.updateList(response.getUpdateRepositorioFileUi());
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void changeList(List<RepositorioFileUi> repositorioFileUiList) {
        super.changeList(repositorioFileUiList);
    }

    @Override
    public void onClickCheck(RepositorioFileUi repositorioFileUi) {
        if (view instanceof RepositorioArchivoViewImpl) {
            RepositorioArchivoViewImpl repositorioArchivoView = (RepositorioArchivoViewImpl) view;
            repositorioArchivoView.removerArchivoComentario(repositorioFileUi);
        }
        repositorioFileUiList.remove(repositorioFileUi);
        showListArchivos(repositorioFileUiList);
        // if(view!=null)view.callbackChange(repositorioFileUiList);
    }
}