package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.repositorioComentario;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.content.FileProvider;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.InfoRubroPresenter;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoComentarioUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.RepositorioPresenter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.RepositorioView;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.RepositorioAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.util.DownloadProgressCounter;
import com.consultoraestrategia.ss_crmeducativo.util.OpenIntents;
import com.consultoraestrategia.ss_crmeducativo.util.UtilsStorage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.DOWNLOAD_SERVICE;

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
        try {
            if (!TextUtils.isEmpty(path)) {
                OpenIntents.openFile(FileProvider.getUriForFile(fragment.getContext(), fragment.getContext().getApplicationContext().getPackageName() + ".provider", new File(Uri.parse(path).getPath())), fragment.getContext());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(fragment.getContext(), fragment.getString(com.consultoraestrategia.ss_crmeducativo.core2.R.string.cannot_open_file), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getFileNameDowload(RepositorioFileUi repositorioFileUi) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(repositorioFileUi.getDownloadId());
        DownloadManager downloadManager = (DownloadManager) fragment.getContext().getSystemService(DOWNLOAD_SERVICE);
        Cursor c = downloadManager.query(query);
        if (c.moveToFirst()) {
            int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
            if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
                int fileUriIdx = c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
                repositorioFileUi.setPathLocal(c.getString(fileUriIdx));
            }
        }
    }

    @Override
    public void showPickPhoto(boolean enableVideo, int maxCount, List<UpdateRepositorioFileUi> photoPaths) {
        Log.d(getClass().getSimpleName(), "showPickPhoto");
        ArrayList<String> stringList = new ArrayList<>();
        //for (UpdateRepositorioFileUi recursoUploadFile : photoPaths)stringList.add(recursoUploadFile.getPath());
        /*FilePickerBuilder filePickerBuilder = FilePickerBuilder.Companion.getInstance()
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
        filePickerBuilder.pickPhoto(fragment, CUSTOM_REQUEST_CODE);*/
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
    public void download(RepositorioFileUi repositorioFileUi) {
        try {
            String[] paths = repositorioFileUi.getUrl().split("/");
            String archivoPreview = paths[paths.length - 1];
            DownloadManager.Request r = new DownloadManager.Request(Uri.parse(repositorioFileUi.getUrl()));
            r.setTitle(archivoPreview);
            r.setDescription(fragment.getContext().getString(R.string.app_name));
            r.setMimeType(UtilsStorage.getMimeType(archivoPreview));
            // This put the download in the same Download dir the browser uses
            //r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, archivoPreview);
            r.setAllowedOverRoaming(false);
            r.setDestinationUri(Uri.fromFile(new File(fragment.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), archivoPreview)));
            // When downloading music and videos they will be listed in the player
            // (Seems to be available since Honeycomb only)
            r.allowScanningByMediaScanner();

            // Notify user when download is completed
            // (Seems to be available since Honeycomb only)
            r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            // Start download
            final DownloadManager dm = (DownloadManager) fragment.getContext().getSystemService(DOWNLOAD_SERVICE);
            //presenter.onStarDownload(dm.enqueue(r));
            new DownloadProgressCounter(dm, dm.enqueue(r), new DownloadProgressCounter.Listener() {
                @Override
                public void onProgress(double progress, long downloadId) {
                    repositorioFileUi.setDownloadId(downloadId);
                    if(repositorioFileUi.isCancel())dm.remove(downloadId);
                    presenter.onProgressDownload(progress, repositorioFileUi);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            fragment.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(repositorioFileUi.getUrl())));
        }
    }


    @Override
    public void cancelDownload(long downloadId) {
        final DownloadManager dm = (DownloadManager) fragment.getContext().getSystemService(DOWNLOAD_SERVICE);
        dm.remove(downloadId);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fragment.getContext().registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    public void onDestroyView() {
        fragment.getContext().unregisterReceiver(onDownloadComplete);
    }


    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                //Fetching the download id received with the broadcast
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                //Checking if the received broadcast is for our enqueued download by matching download id
                CheckDwnloadStatus(id);
            }

        }
    };

    private void CheckDwnloadStatus(long id) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(id);
        DownloadManager downloadManager = (DownloadManager) fragment.getContext().getSystemService(DOWNLOAD_SERVICE);
        Cursor cursor = null;
        if (downloadManager != null) {
            cursor = downloadManager.query(query);
        }
        if (cursor == null || cursor.getCount() == 0) {
            presenter.canceledDownload(id);
        } else {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                int status = cursor.getInt(columnIndex);
                int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
                int reason = cursor.getInt(columnReason);

                switch (status) {
                    case DownloadManager.STATUS_FAILED:
                        String failedReason = "";
                        switch (reason) {
                            case DownloadManager.ERROR_CANNOT_RESUME:
                                failedReason = "ERROR_CANNOT_RESUME";
                                break;
                            case DownloadManager.ERROR_DEVICE_NOT_FOUND:
                                failedReason = "ERROR_DEVICE_NOT_FOUND";
                                break;
                            case DownloadManager.ERROR_FILE_ALREADY_EXISTS:
                                failedReason = "ERROR_FILE_ALREADY_EXISTS";
                                break;
                            case DownloadManager.ERROR_FILE_ERROR:
                                failedReason = "ERROR_FILE_ERROR";
                                break;
                            case DownloadManager.ERROR_HTTP_DATA_ERROR:
                                failedReason = "ERROR_HTTP_DATA_ERROR";
                                break;
                            case DownloadManager.ERROR_INSUFFICIENT_SPACE:
                                failedReason = "ERROR_INSUFFICIENT_SPACE";
                                break;
                            case DownloadManager.ERROR_TOO_MANY_REDIRECTS:
                                failedReason = "ERROR_TOO_MANY_REDIRECTS";
                                break;
                            case DownloadManager.ERROR_UNHANDLED_HTTP_CODE:
                                failedReason = "ERROR_UNHANDLED_HTTP_CODE";
                                break;
                            case DownloadManager.ERROR_UNKNOWN:
                                failedReason = "ERROR_UNKNOWN";
                                break;
                        }
                        //Log.d(getTag(), "FAILED: " + failedReason);
                        downloadManager.remove(id);
                        presenter.finishedDownload(id);
                        break;
                    case DownloadManager.STATUS_PAUSED:
                        String pausedReason = "";
                        switch (reason) {
                            case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
                                pausedReason = "PAUSED_QUEUED_FOR_WIFI";
                                break;
                            case DownloadManager.PAUSED_UNKNOWN:
                                pausedReason = "PAUSED_UNKNOWN";
                                break;
                            case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
                                pausedReason = "PAUSED_WAITING_FOR_NETWORK";
                                break;
                            case DownloadManager.PAUSED_WAITING_TO_RETRY:
                                pausedReason = "PAUSED_WAITING_TO_RETRY";
                                break;
                        }
                        //Log.d(getTag(), "PAUSED: " + pausedReason);
                        break;
                    case DownloadManager.STATUS_PENDING:
                        //Log.d(getTag(), "PENDING");
                        break;
                    case DownloadManager.STATUS_RUNNING:
                        //Log.d(getTag(), "RUNNING");
                        break;
                    case DownloadManager.STATUS_SUCCESSFUL:
                        //Log.d(getTag(), "SUCCESSFUL");
                        presenter.finishedDownload(id);
                        break;
                }
            }
        }

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
       /* Log.d(getClass().getSimpleName(),"onActivityResult: "+ requestCode +" / "+ resultCode);
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
        presenter.onSalirSelectPiket(photoPaths2);*/
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


