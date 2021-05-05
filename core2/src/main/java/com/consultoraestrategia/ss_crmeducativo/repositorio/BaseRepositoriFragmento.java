package com.consultoraestrategia.ss_crmeducativo.repositorio;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.consultoraestrategia.ss_crmeducativo.util.DownloadProgressCounter;
import com.consultoraestrategia.ss_crmeducativo.util.UtilsStorage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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

import static android.content.Context.DOWNLOAD_SERVICE;

public class BaseRepositoriFragmento extends BaseFragment<RepositorioView, RepositorioPresenter, RepositorioListener> implements RepositorioView, RepositorioItemListener, RepositorioItemUpdateListener, View.OnClickListener {
    private final static int REQUEST_CODE_DOC_Q = 2312;
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
                new RepositorioRemoteDataSource(getContext()));
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
        getContext().registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getContext().unregisterReceiver(onDownloadComplete);
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
        DownloadManager downloadManager = (DownloadManager) getContext().getSystemService(DOWNLOAD_SERVICE);
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
                        Log.d(getTag(), "FAILED: " + failedReason);
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
                        Log.d(getTag(), "PAUSED: " + pausedReason);
                        break;
                    case DownloadManager.STATUS_PENDING:
                        Log.d(getTag(), "PENDING");
                        break;
                    case DownloadManager.STATUS_RUNNING:
                        Log.d(getTag(), "RUNNING");
                        break;
                    case DownloadManager.STATUS_SUCCESSFUL:
                        Log.d(getTag(), "SUCCESSFUL");
                        presenter.finishedDownload(id);
                        break;
                }
            }
        }

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


    //download and save file in SD
    @Override
    public void download(final RepositorioFileUi repositorioFileUi) {
        try {
            String[] paths = repositorioFileUi.getUrl().split("/");
            String archivoPreview = paths[paths.length - 1];
            DownloadManager.Request r = new DownloadManager.Request(Uri.parse(repositorioFileUi.getUrl()));
            r.setTitle(archivoPreview);
            r.setDescription(getResources().getString(R.string.app_name));
            r.setMimeType(UtilsStorage.getMimeType(archivoPreview));
            // This put the download in the same Download dir the browser uses
            //r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, archivoPreview);
            r.setAllowedOverRoaming(false);
            r.setDestinationUri(Uri.fromFile(new File(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), archivoPreview)));
            // When downloading music and videos they will be listed in the player
            // (Seems to be available since Honeycomb only)
            r.allowScanningByMediaScanner();

            // Notify user when download is completed
            // (Seems to be available since Honeycomb only)
            r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            // Start download
            final DownloadManager dm = (DownloadManager) getContext().getSystemService(DOWNLOAD_SERVICE);
            //presenter.onStarDownload(dm.enqueue(r));
            long downloadId = dm.enqueue(r);
            repositorioFileUi.setDownloadId(downloadId);
            new DownloadProgressCounter(dm, dm.enqueue(r), new DownloadProgressCounter.Listener() {
                @Override
                public void onProgress(double progress, long downloadId) {
                    presenter.onProgressDownload(progress, repositorioFileUi);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(repositorioFileUi.getUrl())));
        }

    }


    @Override
    public void getFileNameDowload(RepositorioFileUi repositorioFileUi) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(repositorioFileUi.getDownloadId());
        DownloadManager downloadManager = (DownloadManager) getContext().getSystemService(DOWNLOAD_SERVICE);
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
    public void cancelDownload(long downloadId) {
        final DownloadManager dm = (DownloadManager) getContext().getSystemService(DOWNLOAD_SERVICE);
        dm.remove(downloadId);
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
        try {
            if (!TextUtils.isEmpty(path)) {
                OpenIntents.openFile(FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", new File(Uri.parse(path).getPath())), getContext());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), getString(R.string.cannot_open_file), Toast.LENGTH_SHORT).show();
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
        /*ArrayList<Uri> photoPaths = new ArrayList<>();
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
                    String nombreArchivo = getNombre(uri, getContext());
                    InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
                    File file = StreamUtil.stream2file(inputStream, nombreArchivo);
                    photoPaths2.add(file.getAbsolutePath());
                    //dumpImageMetaData(uri, activity);
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;
        }

        for (Uri uri: photoPaths){
            photoPaths2.add(ContentUriUtils.INSTANCE.getFilePath(getContext(), uri));
        }

        Log.d("photoPaths","photoPaths " + photoPaths2);
        presenter.onSalirSelectPiket(photoPaths2);*/

    }

    public String getNombre(Uri uri, Context context) {
        String displayName = null;
        try {
            Cursor cursor = context.getContentResolver()
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

    @Override
    public void showPickPhoto(boolean enableVideo, int maxCount, List<UpdateRepositorioFileUi> photoPaths) {
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
            startActivityForResult(intent, REQUEST_CODE_DOC_Q);

        }else {
            FilePickerBuilder filePickerBuilder = FilePickerBuilder.Companion.getInstance()
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
                    .sortDocumentsBy(SortingTypes.name);


            filePickerBuilder.pickFile(this, FilePickerConst.REQUEST_CODE_DOC);
        }
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
