package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.repositorioComentario;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.core.content.FileProvider;

import android.net.Uri;
import android.util.Log;
import android.view.Window;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.InfoRubroPresenter;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoComentarioUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.RepositorioPresenter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.RepositorioView;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.RepositorioAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.util.OpenIntents;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.utils.ContentUriUtils;

public class RepositorioArchivoViewImpl implements RepositorioView {
    private RepositorioArchivoRubroPresenterImpl presenter;
    private InfoRubroPresenter infoRubroPresenter;
    private RepositorioAdapter repositorioAdapter;
    private Fragment fragment;
    private final static int CUSTOM_REQUEST_CODE = 543;


    public RepositorioArchivoViewImpl(@NonNull Fragment fragment, InfoRubroPresenter infoRubroPresenter) {
        this.fragment = fragment;
        this.infoRubroPresenter = infoRubroPresenter;
    }

    public void changeList(List<RepositorioFileUi> repositorioFileUiList){
        if(presenter!=null)presenter.changeList(repositorioFileUiList);
    }

    @Override
    public void showListArchivos(List<RepositorioFileUi> repositorioFileUiList) {
        if(repositorioAdapter!=null)repositorioAdapter.setList(repositorioFileUiList);
    }

    @Override
    public void updateList(RepositorioFileUi repositorioFileUi) {
        repositorioAdapter.update(repositorioFileUi);
    }

    @Override
    public void setUpdateProgress(RepositorioFileUi repositorioFileUi, int count) {
        repositorioAdapter.updateProgress(repositorioFileUi, count);
    }

    @Override
    public void setUpdate(RepositorioFileUi repositorioEstadoFileU) {
        repositorioAdapter.update(repositorioEstadoFileU);
    }

    @Override
    public void leerArchivo(String path) {
        Log.d(getClass().getSimpleName(), path);
        try {
            OpenIntents.openFile(FileProvider.getUriForFile(fragment.getContext(), fragment.getContext().getApplicationContext().getPackageName() + ".provider", new File(path)), fragment.getContext());

        }catch (Exception e){
            e.printStackTrace();
            Log.d(getClass().getSimpleName(), fragment.getContext().getString(R.string.cannot_open_file));
        }
    }

    @Override
    public void showPickPhoto(boolean enableVideo, int maxCount, List<UpdateRepositorioFileUi> photoPaths) {
        Log.d(getClass().getSimpleName(), "showPickPhoto");
        ArrayList<String> stringList = new ArrayList<>();
        //for (UpdateRepositorioFileUi recursoUploadFile : photoPaths)stringList.add(recursoUploadFile.getPath());
        FilePickerBuilder filePickerBuilder = FilePickerBuilder.Companion.getInstance()
                //.setSelectedFiles(stringList)
                .setActivityTheme(R.style.LibAppThemeLibrary)
                //.setActivityTitle("Selección de multimedia")
                .enableVideoPicker(enableVideo)
                .enableCameraSupport(true)
                .showGifs(true)
                .showFolderView(true)
                //.enableSelectAll(false)
                .enableImagePicker(true)
                .setMaxCount(1);
                //.setCameraPlaceholder(R.drawable.custom_camera)
                //.withOrientation(Orientation.UNSPECIFIED);
        filePickerBuilder.pickPhoto(fragment, CUSTOM_REQUEST_CODE);
    }

    @Override
    public void onShowPickDoc(int maxCount, List<UpdateRepositorioFileUi> docPaths) {

    }

    @Override
    public void callbackChange(List<RepositorioFileUi> repositorioFileUiList) {

    }

    @Override
    public void showFloadButtonAddMultimedia() {

    }

    @Override
    public void showFloadButtonAddFile() {

    }

    @Override
    public void hideFloadButtonAddFile() {

    }

    @Override
    public void showFloadButtonAddVinculo() {

    }

    @Override
    public void hideFloadButtonAddVinculo() {

    }

    @Override
    public void showDialogaddVinculo(RepositorioFileUi repositorioFileUi) {

    }

    @Override
    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) fragment.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if(connectivityManager!=null)activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void showMessageNotConnection() {
        final Dialog dialog = new Dialog(fragment.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(com.consultoraestrategia.ss_crmeducativo.core2.R.layout.dialog_not_connection);
        dialog.show();
    }

    @Override
    public void changeVersionTwoList() {

    }

    @Override
    public void changeColorButtom(String colorButtom) {

    }

    @Override
    public void showListArchivosSelecte(List<RepositorioFileUi> repositorioFileUiList) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMessage(CharSequence message) {

    }

    @Override
    public void showImportantMessage(CharSequence message) {

    }

    @Override
    public void showFinalMessage(CharSequence message) {

    }

    @Override
    public void showListSingleChooser(String title, List<Object> items, int positionSelected) {

    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void setPresenter(RepositorioPresenter presenter) {

        this.presenter = (RepositorioArchivoRubroPresenterImpl)presenter;
        this.presenter.attachView(this);
        this.presenter.onCreate();
    }

    public void onClickRemover(UpdateRepositorioFileUi updateRepositorioFileUi) {
        presenter.onClickRemover(updateRepositorioFileUi);
    }

    public void onClickUpload(UpdateRepositorioFileUi updateRepositorioFileUi) {
        presenter.onClickUpload(updateRepositorioFileUi);
    }

    public void onClickArchivo(RepositorioFileUi repositorioFileUi) {
        presenter.onClickArchivo(repositorioFileUi);
    }

    public void onClickCheck(RepositorioFileUi repositorioFileUi) {
        presenter.onClickCheck(repositorioFileUi);
    }

    public void onClickDownload(RepositorioFileUi repositorioFileUi) {
        presenter.onClickDownload(repositorioFileUi);
    }

    public void onClickClose(RepositorioFileUi repositorioFileUi) {
        presenter.onClickClose(repositorioFileUi);
    }

    public void setAdapterArchivo(RepositorioAdapter repositorioAdapter) {
        this.repositorioAdapter = repositorioAdapter;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data, Context context) {
        Log.d(getClass().getSimpleName(),"onActivityResult: "+ requestCode +" / "+ resultCode);
        ArrayList<Uri> photoPaths = new ArrayList<>();
        switch (requestCode) {
            case CUSTOM_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    photoPaths.addAll(data.<Uri>getParcelableArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));
                }
                break;

            case FilePickerConst.REQUEST_CODE_DOC:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    photoPaths.addAll(data.<Uri>getParcelableArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS));
                }
                break;
        }
        ArrayList<String> photoPaths2 = new ArrayList<>();
        for (Uri uri: photoPaths){
            try {
                photoPaths2.add(ContentUriUtils.INSTANCE.getFilePath(context, uri));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        presenter.onSalirSelectPiket(photoPaths2);
    }

    public void destroy() {

    }

    public void saveArchivoComentario(RepositorioFileUi repositorioFileUi) {
        ArchivoComentarioUi archivoComentarioUi = new ArchivoComentarioUi();
        archivoComentarioUi.setArchivoId(repositorioFileUi.getArchivoId());
        archivoComentarioUi.setPath(repositorioFileUi.getPath());
        archivoComentarioUi.setNombreArchivo(repositorioFileUi.getNombreArchivo());
        archivoComentarioUi.setNombreRecurso(repositorioFileUi.getNombreRecurso());
        archivoComentarioUi.setEstadoFileU(RepositorioEstadoFileU.DESCARGA_COMPLETA);
        archivoComentarioUi.setUrl(repositorioFileUi.getUrl());
        archivoComentarioUi.setTipoFileU(repositorioFileUi.getTipoFileU());
        archivoComentarioUi.setSelect(true);
        infoRubroPresenter.saveComentarioArchivo(archivoComentarioUi);
    }

    public void removerArchivoComentario(RepositorioFileUi repositorioFileUi) {
        if(repositorioFileUi instanceof ArchivoComentarioUi){
            infoRubroPresenter.removerComentarioArchivo((ArchivoComentarioUi)repositorioFileUi);
        } else {
            ArchivoComentarioUi archivoComentarioUi = new ArchivoComentarioUi();
            archivoComentarioUi.setArchivoId(repositorioFileUi.getArchivoId());
            infoRubroPresenter.removerComentarioArchivo(archivoComentarioUi);
        }

    }

    public void onClickAddMultimedia() {
        presenter.onClickAddMultimedia();
    }
}


