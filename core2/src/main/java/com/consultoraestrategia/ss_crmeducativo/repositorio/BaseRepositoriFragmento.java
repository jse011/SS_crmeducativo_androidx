package com.consultoraestrategia.ss_crmeducativo.repositorio;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnStaggeredGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.ArchivoSelectedColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.RepositorioAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.RepositorioArchivoSelected;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.RepositorioColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.RepositorioColumnV2CountProvider;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.RepositoriotemDecoration;
import com.consultoraestrategia.ss_crmeducativo.repositorio.bundle.RepositorioTBunble;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.local.RepositorioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.preferents.RepositorioPreferentsDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote.RepositorioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemUpdateListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.CloneImagenCompress;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.ConvertirPathRepositorioUpload;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.GetUrlRepositorioArchivo;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UpdateRepositorio;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UploadRepositorio;
import com.consultoraestrategia.ss_crmeducativo.util.OpenIntents;
import com.iceteck.silicompressorr.SiliCompressor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.models.sort.SortingTypes;
import droidninja.filepicker.utils.Orientation;

public class BaseRepositoriFragmento extends BaseFragment<RepositorioView, RepositorioPresenter, RepositorioListener> implements RepositorioView, RepositorioItemListener, RepositorioItemUpdateListener, View.OnClickListener {

    protected static final int CUSTOM_REQUEST_CODE = 532;
    protected RecyclerView rcRepositorio;
    protected RepositorioAdapter repositorioAdapter;
    protected ProgressBar progressBar;
    protected ConstraintLayout root;
    protected FloatingActionButton floatingActionButton;
    private AutoColumnGridLayoutManager autoColumnGridLayoutManager;
    private FloatingActionButton floatingActionButton2;
    private FloatingActionButton floatingActionButton3;
    private RepositorioArchivoSelected repositorioSelected;
    private RecyclerView rc_selcted;
    private RepositoriotemDecoration repositorioItemDecoration;


    public static BaseRepositoriFragmento newInstance(RepositorioTBunble repositorioTBunble) {
        Log.d("BaseRepositoriFra", "starFragmento");
        BaseRepositoriFragmento fragment = new BaseRepositoriFragmento();
        fragment.setArguments(repositorioTBunble.getBundle());
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return BaseRepositoriFragmento.class.getSimpleName();
    }

    @Override
    protected RepositorioPresenter getPresenter() {
        RepositorioRepository repository = new RepositorioRepository(new RepositorioLocalDataSource(),
                new RepositorioPreferentsDataSource(),
                new RepositorioRemoteDataSource(ApiRetrofit.getInstance()));
        return new BaseRepositorioPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new DowloadImageUseCase(repository),
                new ConvertirPathRepositorioUpload(),
                new UploadRepositorio(repository),
                new GetUrlRepositorioArchivo(repository),
                new UpdateRepositorio(repository, SiliCompressor.with(getContext())),
                new CloneImagenCompress(SiliCompressor.with(getContext()), getContext()));
    }

    @Override
    protected RepositorioView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_repositorio, container, false);
        rcRepositorio = (RecyclerView)view.findViewById(R.id.rc_repositorio);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        root = (ConstraintLayout)view.findViewById(R.id.root);
        rc_selcted = (RecyclerView)view.findViewById(R.id.rc_selcted);
        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
        floatingActionButton2 = (FloatingActionButton)view.findViewById(R.id.floatingActionButton2);
        floatingActionButton3 = (FloatingActionButton)view.findViewById(R.id.floatingActionButton3);
        floatingActionButton.setOnClickListener(this);
        floatingActionButton2.setOnClickListener(this);
        floatingActionButton3.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupAdapter();
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupAdapter() {
        repositorioAdapter = new RepositorioAdapter(this,this ,rcRepositorio, false);
        autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        RepositorioColumnCountProvider columnCountProvider = new RepositorioColumnCountProvider(getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        rcRepositorio.setLayoutManager(autoColumnGridLayoutManager);
        rcRepositorio.setAdapter(repositorioAdapter);


        if(repositorioItemDecoration==null){
            repositorioItemDecoration = new RepositoriotemDecoration(700);
            rcRepositorio.addItemDecoration(repositorioItemDecoration);
        }

        AutoColumnStaggeredGridLayoutManager autoColumnGridLayoutManager = new AutoColumnStaggeredGridLayoutManager(OrientationHelper.VERTICAL, getContext());
        ArchivoSelectedColumnCountProvider archivoSelectedColumnCountProvider = new ArchivoSelectedColumnCountProvider(getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(archivoSelectedColumnCountProvider);
        rc_selcted.setLayoutManager(autoColumnGridLayoutManager);
        repositorioSelected = new RepositorioArchivoSelected();
        rc_selcted.setAdapter(repositorioSelected);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
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
    public synchronized void setUpdateProgress(RepositorioFileUi repositorioFileUi, int count) {
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
                OpenIntents.openFile(FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", new File(path)), getContext());

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(), getContext().getString(R.string.cannot_open_file),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClickDownload(RepositorioFileUi repositorioFileUi) {
        presenter.onClickDownload(repositorioFileUi);
    }

    @Override
    public void onClickClose(RepositorioFileUi repositorioFileUi) {
        presenter.onClickClose(repositorioFileUi);
    }

    @Override
    public void onClickCheck(RepositorioFileUi repositorioFileUi) {
        presenter.onClickCheck(repositorioFileUi);
    }

    @Override
    public void onClickArchivo(RepositorioFileUi repositorioFileUi) {
        presenter.onClickArchivo(repositorioFileUi);
    }

    @Override
    public void onClickUpload(UpdateRepositorioFileUi updateRepositorioFileUi) {
        presenter.onClickUpload(updateRepositorioFileUi);
    }

    @Override
    public void onClickRemover(UpdateRepositorioFileUi updateRepositorioFileUi) {
        presenter.onClickRemover(updateRepositorioFileUi);
    }

    @Override
    public void onClickClose(UpdateRepositorioFileUi updateRepositorioFileUi) {
        presenter.onClickClose(updateRepositorioFileUi);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.floatingActionButton){
            presenter.onClickAddFile();
        }else if(id == R.id.floatingActionButton2){
            presenter.onClickAddMultimedia();
        }else if(id == R.id.floatingActionButton3){
            presenter.onClickAddVinculo();
        }
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
                repositorioFileUi.setNombreArchivo(txtUrlVideo.getText().toString());
                repositorioFileUi.setNombreRecurso(txtTituloVideo.getText().toString());
                repositorioFileUi.setUrl(txtUrlVideo.getText().toString());
                presenter.onClickAceptarDialogVinculo(repositorioFileUi);
                dialog.dismiss();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<String> photoPaths = new ArrayList<>();
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

        presenter.onSalirSelectPiket(photoPaths);

    }

    @Override
    public void showPickPhoto(boolean enableVideo, int maxCount, List<UpdateRepositorioFileUi> photoPaths) {
        ArrayList<String> stringList = new ArrayList<>();
        //for (UpdateRepositorioFileUi recursoUploadFile : photoPaths)stringList.add(recursoUploadFile.getPath());
        FilePickerBuilder filePickerBuilder = FilePickerBuilder.getInstance()
                //.setSelectedFiles(stringList)
                .setActivityTheme(R.style.LibAppThemeLibrary)
                //.setActivityTitle("Selección de multimedia")
                .enableVideoPicker(enableVideo)
                .enableCameraSupport(true)
                .showGifs(true)
                .showFolderView(true)
                //.enableSelectAll(false)
                .enableImagePicker(true)
                .setMaxCount(1)
                //.setCameraPlaceholder(R.drawable.custom_camera)
                .withOrientation(Orientation.UNSPECIFIED);
            filePickerBuilder.pickPhoto(this, CUSTOM_REQUEST_CODE);
    }


    /* DOC_ESCRITOS(),
     DOC_PRESENTACIONES(new String[]{".ppt", ".pptx"}),
     PDF(new String[]{".pdf"}),
     DOC_TABLAS(new String[]{".xls", ".xlsx",".ods"}),
     MUSICA(new String[]{".mp3", ".ogg",".wav"}),
     VIDEOS(new String[]{".mpg",".3gp",".mpg4",".wmv",".mov",".ogv"}),
     IMAGENES(new String[]{".gif",".jpeg",".jpg",".png"}),
     COMPRESION(new String[]{".gz",".gzip",".rar",".zip"});*/
    @Override
    public void onShowPickDoc(int maxCount, List<UpdateRepositorioFileUi> docPaths) {

        FilePickerBuilder filePickerBuilder = FilePickerBuilder.getInstance()
                .setMaxCount(1)
                //.setSelectedFiles(stringList)
                .setActivityTheme(R.style.LibAppThemeLibrary)
                //.setActivityTitle("Selección de documento");
                .addFileSupport("DOCUMENTO", new String[]{".doc", ".docx", ".txt"},R.drawable.ext_doc)
                .addFileSupport("HOJA DE CALCULO", new String[]{".xls", ".xlsx",".ods"},R.drawable.ext_xls)
                .addFileSupport("PDF", new String[]{".pdf"},R.drawable.ext_pdf)
                .addFileSupport("PRESENTACION", new String[]{".ppt", ".pptx"},R.drawable.ext_ppt)
                .addFileSupport("MUSICA", new String[]{".mp3", ".ogg",".wav"},R.drawable.ext_aud)
                //filePickerBuilder.addFileSupport("COMPRESION", new String[]{".gz",".gzip",".rar",".zip"});
                .enableDocSupport(false)
                //.enableSelectAll(true)
                //.showFolderView(true)
                .sortDocumentsBy(SortingTypes.name)
                .withOrientation(Orientation.UNSPECIFIED);


        filePickerBuilder.pickFile(this, FilePickerConst.REQUEST_CODE_DOC);
    }


    @Override
    public void callbackChange(List<RepositorioFileUi> repositorioFileUiList) {
       if(listener!=null)listener.onChangeList(repositorioFileUiList);
    }

    @Override
    public void showFloadButtonAddMultimedia() {
        floatingActionButton2.show();
    }

    @Override
    public void showFloadButtonAddFile() {
        floatingActionButton.show();
    }

    @Override
    public void hideFloadButtonAddFile() {
        floatingActionButton.hide();
    }

    @Override
    public void showFloadButtonAddVinculo() {
        floatingActionButton3.show();
    }

    @Override
    public void hideFloadButtonAddVinculo() {
        floatingActionButton3.hide();
    }

    @Override
    public void showDialogaddVinculo(RepositorioFileUi repositorioFileUi) {
        showDialog(getContext(), repositorioFileUi);
    }

    @Override
    public void onClickArchivo(UpdateRepositorioFileUi updateRepositorioFileUi) {
        presenter.onClickArchivo(updateRepositorioFileUi);
    }

    public void changeList(List<RepositorioFileUi> repositorioFileUiList){
       presenter.changeList(repositorioFileUiList);
    }

    public List<RepositorioFileUi> getListFiles() {
        return presenter.getListFiles();
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if(connectivityManager!=null)activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void showMessageNotConnection() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_not_connection);
        dialog.show();

    }

    @Override
    public void changeVersionTwoList() {
        if(repositorioAdapter!=null){
            autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
            RepositorioColumnV2CountProvider columnCountProvider = new RepositorioColumnV2CountProvider(getContext());
            autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
            rcRepositorio.setLayoutManager(autoColumnGridLayoutManager);
            repositorioAdapter.setVersion_two(true);
        }
    }

    @Override
    public void changeColorButtom(String colorButtom) {
        if (colorButtom!=null){
            try{
                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorButtom)));
                floatingActionButton2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorButtom)));
                floatingActionButton3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorButtom)));
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void showListArchivosSelecte(List<RepositorioFileUi> repositorioFileUiList) {
        if(repositorioSelected!=null)repositorioSelected.setList(repositorioFileUiList);
    }
}
