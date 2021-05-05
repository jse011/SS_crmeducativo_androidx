package com.consultoraestrategia.ss_crmeducativo.services.uploadFile.selectFilePiker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.fragment.app.Fragment;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities.EnumFile;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities.GroupFile;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities.RecursoUploadFile;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jse on 17/01/2019.
 */

public class SelectFilePiketImpl implements SelectFilePiker {

    private static final int CUSTOM_REQUEST_CODE = 532;
    private Activity activity;
    private Fragment fragmento;
    private CallBack callback;

    public SelectFilePiketImpl(Activity activity) {
        this.activity = activity;
    }


    public SelectFilePiketImpl(Fragment fragment) {
        this.fragmento = fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void showPickPhoto(boolean enableVideo, int maxCount, List<RecursoUploadFile> photoPaths) {
        ArrayList<Uri> stringList = new ArrayList<>();
        for (RecursoUploadFile recursoUploadFile : photoPaths)stringList.add(Uri.parse(recursoUploadFile.getPath()));
        /*FilePickerBuilder filePickerBuilder = FilePickerBuilder.Companion.getInstance()
                .setMaxCount(maxCount)
                .setSelectedFiles(stringList)
                .setActivityTheme(R.style.LibAppTheme)
                .setActivityTitle("Selección de multimedia")
                .enableVideoPicker(enableVideo)
                .enableCameraSupport(true)
                .showGifs(true)
                .showFolderView(true)
                .enableSelectAll(false)
                .enableImagePicker(true)
                .setCameraPlaceholder(R.drawable.custom_camera);
                //.withOrientation(Orientation.UNSPECIFIED);

        if(fragmento==null){
            filePickerBuilder.pickPhoto(activity, CUSTOM_REQUEST_CODE);
        }else {
            filePickerBuilder.pickPhoto(fragmento, CUSTOM_REQUEST_CODE);
        }*/
    }

    @Override
    public void onShowPickDoc(List<GroupFile> groupFiles, int maxCount, List<RecursoUploadFile> docPaths) {
       /* ArrayList<Uri> stringList = new ArrayList<>();
        for (RecursoUploadFile recursoUploadFile : docPaths)stringList.add(Uri.parse(recursoUploadFile.getPath()));
        FilePickerBuilder filePickerBuilder = FilePickerBuilder.Companion.getInstance()
                .setMaxCount(maxCount)
                .setSelectedFiles(stringList)
                .setActivityTheme(R.style.LibAppTheme)
                .setActivityTitle("Selección de documento");
        for (GroupFile groupFile: groupFiles){
            if(groupFile.getDrawableIcon()==0){
                filePickerBuilder.addFileSupport(groupFile.getNombre(), groupFile.getExtenciones());
            }else {
                filePickerBuilder.addFileSupport(groupFile.getNombre(), groupFile.getExtenciones(), groupFile.getDrawableIcon());
            }

        }

        filePickerBuilder.enableDocSupport(false)
                .enableSelectAll(true)
                .sortDocumentsBy(SortingTypes.name);
                //.withOrientation(Orientation.UNSPECIFIED);

        if(fragmento==null){
            filePickerBuilder.pickFile(activity);
        }else {
            filePickerBuilder.pickFile(fragmento);
        }*/
    }

    @Override
    public void SelectFilePikerOnChangeListner(CallBack callBack) {
        this.callback = callBack;
    }
}
