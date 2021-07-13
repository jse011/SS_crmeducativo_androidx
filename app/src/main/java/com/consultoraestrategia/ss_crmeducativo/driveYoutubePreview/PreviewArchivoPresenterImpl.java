package com.consultoraestrategia.ss_crmeducativo.driveYoutubePreview;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.driveYoutubePreview.checkConexion.CheckConexRepository;
import com.consultoraestrategia.ss_crmeducativo.driveYoutubePreview.util.DriveYoutubePreview;
import com.consultoraestrategia.ss_crmeducativo.util.UtilsStorage;

public class PreviewArchivoPresenterImpl extends BasePresenterImpl<PreviewArchivoView> implements PreviewArchivoPresenter {


    private final CheckConexRepository checkConexRepo;
    private DriveYoutubePreview preview;
    private long downloadID;
    private boolean dowloadProgress;
    private PreviewArchivoView.ArchivoPreviewView archivoPreviewView;
    private PreviewArchivoView.MultimediaPreviewView multimediaPreviewView;
    private String driveId;
    private String archivoPreview;


    public PreviewArchivoPresenterImpl(UseCaseHandler handler, Resources res, CheckConexRepository checkConexRepo) {
        super(handler, res);
        this.checkConexRepo = checkConexRepo;
    }

    @Override
    protected String getTag() {
        return "PreviewArchivoPresenterTAG";
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        this.preview = (DriveYoutubePreview)extras.getSerializable("serial");
        if(preview!=null)driveId = preview.getDriveId();
        if(preview!=null)archivoPreview = preview.getNombreArchivoPreview();
    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        hideProgress();
        if(preview!=null){
            switch (preview.getTipo()){
                case YOUTUBE:
                    if(view!=null)view.hideButons();
                    if(view!=null)view.showYoutube(preview.getUrl());
                    break;
                case MULTIMEDIA_DRIVE:
                case TAREA_MULTIMEDIA_FIREBASE:
                    if(view!=null)view.showMultimendia();
                    break;
                default:
                    if(view!=null)view.showDocument();
                    break;
            }

        }

    }

    @Override
    public void onProgressChanged(int progress) {
            if (progress < 100) {
            if(archivoPreviewView!=null)archivoPreviewView.showProgress();
        }
        if (progress == 100) {
            if(archivoPreviewView!=null)archivoPreviewView.hideProgress();
        }
    }

    @Override
    public void onClickBtnDownload() {
        if(!TextUtils.isEmpty(driveId)){
            if(!dowloadProgress){
                if(view!=null)view.hideDowloadProgress();
                if(view!=null)view.hideDownloadComplete();
                if(view!=null)view.dowloadArchivo(driveId, archivoPreview);
            }else {
                if(view!=null)view.showMessage("descarga en progreso");
            }
        }
    }

    @Override
    public void onClickBtnOpen() {
        if(!TextUtils.isEmpty(driveId)){
            if(view!=null)view.openBrowser(driveId);
        }

    }

    @Override
    public void onStarDownload(long downloadID) {
        dowloadProgress = true;
        this.downloadID = downloadID;
        if(view!=null)view.showDowloadProgress();
        if(view!=null)view.hideDownloadComplete();
    }

    @Override
    public void finishedDownload(long id) {
        if (downloadID == id) {
            dowloadProgress = false;
            if(view!=null)view.hideDowloadProgress();
            if(view!=null)view.showDownloadComplete();
        }
    }

    @Override
    public void canceledDownload(long id) {
        if (downloadID == id) {
            dowloadProgress = false;
            if(view!=null)view.hideDowloadProgress();
            if(view!=null)view.hideDownloadComplete();
        }
    }

    @Override
    public void onClickMsgSucess() {
        if(view!=null)view.openDownloadFile(downloadID);
    }

    @Override
    public void attachView(PreviewArchivoView.ArchivoPreviewView archivoPreviewView) {
        this.archivoPreviewView = archivoPreviewView;
        archivoPreviewView.showProgress();
        if(view!=null)view.hideButons();
        dowloadProgress=false;
        checkConexRepo.online(new CheckConexRepository.Callback() {
            @Override
            public void onLoad(boolean success) {
                if(success){
                    if(archivoPreviewView!=null)
                        archivoPreviewView.uploadArchivo(driveId);
                    if(view!=null)view.showButons();
                }else {
                    if(archivoPreviewView!=null)
                        archivoPreviewView.hideProgress();
                    if(archivoPreviewView!=null)
                        archivoPreviewView.showBtnReload();
                    if(view!=null)view.showMessage("sin conexión");
                }
            }
        });

    }

    @Override
    public void onDestroyArchivoPreviewView() {
        archivoPreviewView =null;
    }

    @Override
    public void onDestroyMultimediaPreviewView() {
        multimediaPreviewView = null;
    }

    @Override
    public void attachView(PreviewArchivoView.MultimediaPreviewView multimediaPreviewView) {
        this.multimediaPreviewView = multimediaPreviewView;
        multimediaPreviewView.showProgress();
        if(view!=null)view.hideButons();
        dowloadProgress=false;
        checkConexRepo.online(new CheckConexRepository.Callback() {
            @Override
            public void onLoad(boolean success) {
                if(success){
                    uploadPreview();
                    if(multimediaPreviewView!=null)
                        multimediaPreviewView.hideProgress();
                    if(view!=null)view.showButons();
                }else {
                    if(multimediaPreviewView!=null)multimediaPreviewView.hideProgress();
                    if(multimediaPreviewView!=null)multimediaPreviewView.showBtnReload();

                    if(view!=null)view.showMessage("sin conexión");
                }
            }
        });

    }

    private void uploadPreview() {
        String extencion = UtilsStorage.getExtencion(archivoPreview);
        if (!TextUtils.isEmpty(extencion) && (extencion.toLowerCase().contains("mp3") || extencion.toLowerCase().contains("ogg") || extencion.toLowerCase().contains("wav"))) {
            if(multimediaPreviewView!=null)multimediaPreviewView.uploadAudio(driveId);
        }else if (!TextUtils.isEmpty(extencion) && (extencion.toLowerCase().contains("mp4"))) {
            if(multimediaPreviewView!=null)multimediaPreviewView.uploadMp4(driveId);
        }else if (!TextUtils.isEmpty(extencion) && (extencion.toLowerCase().contains("flv"))) {
            if(multimediaPreviewView!=null)multimediaPreviewView.uploadFlv(driveId);
        }else if (!TextUtils.isEmpty(extencion) && (extencion.toLowerCase().contains("mkv"))) {
            if(multimediaPreviewView!=null)multimediaPreviewView.uploadMkv(driveId);
        }else {
            if(multimediaPreviewView!=null)multimediaPreviewView.uploadArchivo(driveId);
        }
    }

    @Override
    public void initializePlayer() {
        if(!TextUtils.isEmpty(driveId)){
            if(multimediaPreviewView!=null)multimediaPreviewView.resumePreview(driveId);
        }

    }

    @Override
    public void onCancelPlayer() {

    }

    @Override
    public void onClickReload() {
        if(!TextUtils.isEmpty(driveId)){
            uploadPreview();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
