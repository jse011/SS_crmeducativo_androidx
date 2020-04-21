package com.consultoraestrategia.ss_crmeducativo.services.uploadFile.selectFilePiker;

import android.content.Intent;

import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities.GroupFile;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities.RecursoUploadFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jse on 17/01/2019.
 */

public interface SelectFilePiker {

    void onActivityResult(int requestCode, int resultCode, Intent data);
    void showPickPhoto(boolean enableVideo,int maxCount, List<RecursoUploadFile> photoPaths);
    void onShowPickDoc(List<GroupFile> groupFiles, int maxCount, List<RecursoUploadFile> docPaths );
    void SelectFilePikerOnChangeListner(CallBack callBack);
    interface CallBack{
        void onSalirSelectPiket(List<RecursoUploadFile> recursoUploadFiles);
    }
}
