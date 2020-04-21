package com.consultoraestrategia.ss_crmeducativo.services.uploadFile.selectFilePiker;

import android.app.Activity;
import android.content.Intent;
import androidx.fragment.app.Fragment;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities.EnumFile;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities.GroupFile;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities.RecursoUploadFile;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.models.sort.SortingTypes;
import droidninja.filepicker.utils.Orientation;

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
        ArrayList<String> photoPaths = new ArrayList<>();;
        switch (requestCode) {
            case CUSTOM_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));
                }
                break;

            case FilePickerConst.REQUEST_CODE_DOC:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS));
                }
                break;
        }

        ArrayList<RecursoUploadFile> recursoUploadFiles = new ArrayList<>();
        for (String fileName : photoPaths){
            String extension = "";
            int i = fileName.lastIndexOf('.');
            int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
            String file = fileName.substring(p + 1);
            if (i > p) {
                extension = fileName.substring(i+1);
            }

            RecursoUploadFile recursoUploadFile = new RecursoUploadFile();
            recursoUploadFile.setId(IdGenerator.generateId());
            recursoUploadFile.setNombre(file);
            recursoUploadFile.setExtencion(extension);
            recursoUploadFile.setPath(fileName);
            recursoUploadFile.setTipo(EnumFile.getFile(extension));
            recursoUploadFiles.add(recursoUploadFile);
        }

        callback.onSalirSelectPiket(recursoUploadFiles);
    }

    @Override
    public void showPickPhoto(boolean enableVideo, int maxCount, List<RecursoUploadFile> photoPaths) {
        ArrayList<String> stringList = new ArrayList<>();
        for (RecursoUploadFile recursoUploadFile : photoPaths)stringList.add(recursoUploadFile.getPath());
        FilePickerBuilder filePickerBuilder = FilePickerBuilder.getInstance()
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
                .setCameraPlaceholder(R.drawable.custom_camera)
                .withOrientation(Orientation.UNSPECIFIED);

        if(fragmento==null){
            filePickerBuilder.pickPhoto(activity, CUSTOM_REQUEST_CODE);
        }else {
            filePickerBuilder.pickPhoto(fragmento, CUSTOM_REQUEST_CODE);
        }
    }

    @Override
    public void onShowPickDoc(List<GroupFile> groupFiles, int maxCount, List<RecursoUploadFile> docPaths) {
        ArrayList<String> stringList = new ArrayList<>();
        for (RecursoUploadFile recursoUploadFile : docPaths)stringList.add(recursoUploadFile.getPath());
        FilePickerBuilder filePickerBuilder = FilePickerBuilder.getInstance()
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
                .sortDocumentsBy(SortingTypes.name)
                .withOrientation(Orientation.UNSPECIFIED);

        if(fragmento==null){
            filePickerBuilder.pickFile(activity);
        }else {
            filePickerBuilder.pickFile(fragmento);
        }
    }

    @Override
    public void SelectFilePikerOnChangeListner(CallBack callBack) {
        this.callback = callBack;
    }
}
