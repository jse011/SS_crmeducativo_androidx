package com.consultoraestrategia.ss_crmeducativo.driveYoutubePreview;


import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;

public interface PreviewArchivoPresenter extends BasePresenter<PreviewArchivoView> {
    void onProgressChanged(int progress);

    void onClickBtnDownload();

    void onClickBtnOpen();

    void onStarDownload(long downloadID);

    void finishedDownload(long id);

    void canceledDownload(long id);

    void onClickMsgSucess();

    void attachView(PreviewArchivoView.ArchivoPreviewView archivoPreviewView);

    void onDestroyArchivoPreviewView();

    void onDestroyMultimediaPreviewView();

    void attachView(PreviewArchivoView.MultimediaPreviewView multimediaPreviewView);

    void initializePlayer();

    void onCancelPlayer();

    void onClickReload();
}
