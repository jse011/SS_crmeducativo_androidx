package com.consultoraestrategia.ss_crmeducativo.driveYoutubePreview;


import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;

public interface PreviewArchivoView extends BaseView<PreviewArchivoPresenter> {

    void openBrowser(String idDrive);

    void showButons();

    void hideButons();

    void showDowloadProgress();

    void hideDowloadProgress();

    void showDownloadComplete();

    void dowloadArchivo(String idDrive, String archivoPreview);

    void hideDownloadComplete();

    void openDownloadFile(long downloadID);

    void showYoutube(String youtube);

    void showMultimendia();

    void showDocument();

    void closeOpenLink(String url);

    interface ArchivoPreviewView {
        void uploadArchivo(String idDrive);

        void showProgress();

        void hideProgress();

        void setPresenter(PreviewArchivoPresenter presenter);

        void showBtnReload();

    }

    interface MultimediaPreviewView {
        void uploadArchivo(String idDrive);

        void showProgress();

        void hideProgress();

        void setPresenter(PreviewArchivoPresenter presenter);

        void resumePreview(String idDrive);

        boolean onBackPressed();

        void uploadAudio(String idDrive);

        void uploadMp4(String idDrive);

        void uploadFlv(String idDrive);

        void uploadMkv(String idDrive);

        void showBtnReload();

        void dimensionRatio(int a, int b);
    }
}
