package com.consultoraestrategia.ss_crmeducativo.services.uploadFile.dialog;

public interface DialogUpload {
    interface Callback{
        void onSucessDialogUpload();
        void onErrorDialogUpload();
    }
    void onListenerDialogUpload(Callback callback);
    void onShowDialogUpload();
    void onHideDialogUpload();
}
