package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.repositorioComentario;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.BuildConfig;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.repositorio.RepositorioPresenter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.RepositorioView;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.RepositorioAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.CreateTareaPresenter;
import com.consultoraestrategia.ss_crmeducativo.util.OpenIntents;
import com.consultoraestrategia.ss_crmeducativo.util.StreamUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.models.sort.SortingTypes;
import droidninja.filepicker.utils.ContentUriUtils;

public class RepositorioArchivoViewImpl implements RepositorioView {
    private RepositorioArchivoTareaPresenterImpl presenter;
    private CreateTareaPresenter createTareaPresenter;
    private RepositorioAdapter repositorioAdapter;
    private Activity activity;
    private final static int CUSTOM_REQUEST_CODE = 543;
    private final static int REQUEST_CODE_DOC_Q = 2312;

    public RepositorioArchivoViewImpl(@NonNull Activity activity, CreateTareaPresenter createTareaPresenter) {
        this.activity = activity;
        this.createTareaPresenter = createTareaPresenter;
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
            OpenIntents.openFile(FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".provider", new File(path)), activity);

        }catch (Exception e){
            e.printStackTrace();
            Log.d(getClass().getSimpleName(), activity.getString(R.string.cannot_open_file));
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
        filePickerBuilder.pickPhoto(activity, CUSTOM_REQUEST_CODE);
    }

    @Override
    public void onShowPickDoc(int maxCount, List<UpdateRepositorioFileUi> docPaths) {


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
            // browser.
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            // Filter to only show results that can be "opened", such as a
            // file (as opposed to a list of contacts or timezones)
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            // Filter to show only images, using the image MIME data type.
            // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
            // To search for all documents available via installed storage providers,
            // it would be "*/*".
            intent.setType("*/*");
            activity.startActivityForResult(intent, REQUEST_CODE_DOC_Q);

        }else {
            FilePickerBuilder filePickerBuilder = FilePickerBuilder.Companion.getInstance()
                    .setMaxCount(1)
                    //.setSelectedFiles(stringList)
                    .setActivityTheme(com.consultoraestrategia.ss_crmeducativo.core2.R.style.LibAppThemeLibrary)
                    //.setActivityTitle("Selección de documento");
                    .addFileSupport("DOCUMENTO", new String[]{".doc", ".docx", ".txt"}, com.consultoraestrategia.ss_crmeducativo.core2.R.drawable.ext_doc)
                    .addFileSupport("HOJA DE CALCULO", new String[]{".xls", ".xlsx",".ods"}, com.consultoraestrategia.ss_crmeducativo.core2.R.drawable.ext_xls)
                    .addFileSupport("PDF", new String[]{".pdf"}, com.consultoraestrategia.ss_crmeducativo.core2.R.drawable.ext_pdf)
                    .addFileSupport("PRESENTACION", new String[]{".ppt", ".pptx"}, com.consultoraestrategia.ss_crmeducativo.core2.R.drawable.ext_ppt)
                    .addFileSupport("MUSICA", new String[]{".mp3", ".ogg",".wav"}, com.consultoraestrategia.ss_crmeducativo.core2.R.drawable.ext_aud)
                    //filePickerBuilder.addFileSupport("COMPRESION", new String[]{".gz",".gzip",".rar",".zip"});
                    .enableDocSupport(false)
                    //.enableSelectAll(true)
                    //.showFolderView(true)
                    .sortDocumentsBy(SortingTypes.name)
                    .withOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);


            filePickerBuilder.pickFile(activity, FilePickerConst.REQUEST_CODE_DOC);
        }

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
        showDialog(activity, repositorioFileUi);
    }

    public void showDialog(Context context, final RepositorioFileUi repositorioFileUi){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_add_vinculo);
        Log.d("dialog", "dialog: " );
        final EditText txtTituloVideo = (EditText) dialog.findViewById(R.id.txtTituloVideo);
        final EditText txtUrlVideo = (EditText) dialog.findViewById(R.id.txtUrlVideo);
        txtTituloVideo.setText(repositorioFileUi.getNombreArchivo());
        txtUrlVideo.setText(repositorioFileUi.getUrl());

        Button btnCancelarVideo = (Button) dialog.findViewById(R.id.btnCancelarVideo);
        Button btnAceptar = (Button) dialog.findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txtTituloVideo.getText().toString())){
                    Toast.makeText(context, "Ingrese un título valido",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(txtUrlVideo.getText().toString())){
                    Toast.makeText(context, "Ingrese una  url valida",Toast.LENGTH_SHORT).show();
                }else{
                    repositorioFileUi.setNombreArchivo(txtUrlVideo.getText().toString());
                    repositorioFileUi.setNombreRecurso(txtTituloVideo.getText().toString());
                    repositorioFileUi.setUrl(txtUrlVideo.getText().toString());
                    presenter.onClickAceptarDialogVinculo(repositorioFileUi);
                    dialog.dismiss();
                }

            }
        });
        btnCancelarVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if(connectivityManager!=null)activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void showMessageNotConnection() {
        final Dialog dialog = new Dialog(activity);
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
        this.presenter = (RepositorioArchivoTareaPresenterImpl)presenter;
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
        ArrayList<String> photoPaths2 = new ArrayList<>();
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
            case REQUEST_CODE_DOC_Q:
                try{
                    Uri uri = data.getData();
                    String nombreArchivo = getNombre(uri, activity);
                    InputStream inputStream = activity.getContentResolver().openInputStream(uri);
                    File file = StreamUtil.stream2file(inputStream, nombreArchivo);
                    photoPaths2.add(file.getAbsolutePath());
                    //dumpImageMetaData(uri, activity);
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;
        }

        for (Uri uri: photoPaths){
            try {
                photoPaths2.add(ContentUriUtils.INSTANCE.getFilePath( context, uri));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        Log.d("photoPaths","photoPaths " + photoPaths2);
        presenter.onSalirSelectPiket(photoPaths2);
    }

    public String getNombre(Uri uri, Activity activity) {
        String displayName = null;
        try {
            Cursor cursor = activity.getContentResolver()
                    .query(uri, null, null, null, null, null);
            // moveToFirst() returns false if the cursor has 0 rows.  Very handy for
            // "if there's anything to look at, look at it" conditionals.
            if (cursor != null) {
                if(cursor.moveToFirst()){
                   displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
                cursor.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return displayName;
    }

    public void destroy() {

    }

    public void onClickAddMultimedia() {
        presenter.onClickAddMultimedia();
    }
}


