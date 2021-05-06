package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.ui;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.BuildConfig;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.InfoRubroPresenter;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.InfoRubroPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.adapters.AdjuntoAdapter;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.adapters.ArchivoComentarioColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.adapters.ComentarioPredeAdapter;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.data.InfoRubroDataLocalSource;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.data.InfoRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.DeleteArchivoComentario;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.DeleteComentario;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.GetArchivoComentario;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.GetComentarioPred;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.SaveArchivoRubro;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.SaveComentario;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.UploadArchivo;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.lib.imageViewZoom.ImageZomDialog;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.RepositorioAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.RepositoriotemDecoration;
import com.consultoraestrategia.ss_crmeducativo.repositorio.bundle.RepositorioTBunble;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.local.RepositorioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.preferents.RepositorioPreferentsDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote.RepositorioRemoteDataSource;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.iceteck.silicompressorr.SiliCompressor;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Jse on 15/09/2018.
 */

public class InfoComentarioDialog extends BaseDialogFragment<InfoRubroView, InfoRubroPresenter,InfoRubroListener> implements InfoRubroView, View.OnClickListener, ComentarioPredeAdapter.ArchivoComentarioListener, TextWatcher, AdjuntoAdapter.Listener {

    static int REQUEST_TAKE_PHOTO = 544, REQUEST_GALLERY_IMAGE = 545;

    @BindView(R.id.btnEnviar)
    ImageView btnEnviar;
    @BindView(R.id.inputComentario)
    EditText inputComentario;
    @BindView(R.id.content_comentario)
    ConstraintLayout contentComentario;
    @BindView(R.id.recycomentarios)
    RecyclerView recycomentarios;
    @BindView(R.id.rec_rubro_archivo)
    RecyclerView recRubroArchivo;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.btnRetroceder)
    Button btnRetroceder;
    private InfoRubroPresenter presenter;

    public static final String ID_EVALUACION_PROCESO = "eveluacionId";
    private ComentarioPredeAdapter comentarioPredeAdapter;
    private AdjuntoAdapter adjuntoAdapter;

    public static InfoComentarioDialog newInstance(String evaluacionProcesoId) {
        Bundle args = new Bundle();
        args.putString(ID_EVALUACION_PROCESO, evaluacionProcesoId);
        InfoComentarioDialog fragment = new InfoComentarioDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        View view = super.onCreateView(inflater, container, savedInstanceState);
        btnEnviar.setOnClickListener(this);
        return view;

    }

    private void initEditText() {
        inputComentario.clearFocus();
        inputComentario.addTextChangedListener(this);
    }


    @OnClick(R.id.btn_add_evidencias)
    public void onClickAddMultimedia() {
        new OpcionesAdjuntarDialog(presenter).show(getChildFragmentManager(), "OpcionesAdjuntarDialog");
    }

    @Override
    protected String getLogTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected InfoRubroPresenter getPresenter() {
        RepositorioRepository repositorioRepository = new RepositorioRepository(new RepositorioLocalDataSource(),
                new RepositorioPreferentsDataSource(),
                new RepositorioRemoteDataSource(getContext()));

        InfoRubroRepository infoRubroRepository = new InfoRubroRepository(new InfoRubroDataLocalSource());
        presenter = new InfoRubroPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetComentarioPred(infoRubroRepository),
                new GetArchivoComentario(infoRubroRepository),
                new SaveComentario(infoRubroRepository),
                new DeleteComentario(infoRubroRepository),
                new SaveArchivoRubro(infoRubroRepository),
                new DeleteArchivoComentario(infoRubroRepository),
                new UploadArchivo(getContext()));
        return presenter;
    }

    @Override
    protected InfoComentarioDialog getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.activity_info_comentario_rubro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnRetroceder.setOnClickListener(this);
        setupAdapter();
        initEditText();
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onActivityCreated();
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    private void setupAdapter() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycomentarios.setLayoutManager(layoutManager);
        comentarioPredeAdapter = new ComentarioPredeAdapter(this);
        recycomentarios.setAdapter(comentarioPredeAdapter);
        recycomentarios.setNestedScrollingEnabled(false);

        recRubroArchivo.setLayoutManager(new LinearLayoutManager(getContext()));
        adjuntoAdapter = new AdjuntoAdapter(this);
        recRubroArchivo.setAdapter(adjuntoAdapter);
    }



    @Override
    public void showListComentarios(List<MensajeUi> mensajeUiList) {
        comentarioPredeAdapter.setMensajesList(mensajeUiList);
    }

    @Override
    public void showListComentariosArchivos(List<ArchivoUi> objects) {
        adjuntoAdapter.setList(objects);
    }

    @Override
    public void cerrarDialog(String evaluacionProcesoId) {
        dismiss();
        if(listener!=null)listener.cerrarDialogComentario(evaluacionProcesoId);
    }

    @Override
    public void showBtnSendComentario() {
        if (btnEnviar.getVisibility() == View.INVISIBLE) {
            btnEnviar.setVisibility(View.VISIBLE);
            btnEnviar.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_up));
        }

    }

    @Override
    public void hideBtnSendComentario() {
        if (btnEnviar.getVisibility() == View.VISIBLE) {
            btnEnviar.setVisibility(View.INVISIBLE);
            btnEnviar.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_down));
        }
    }

    @Override
    public void clearTextInpuComentario() {
        inputComentario.setText(null);
        inputComentario.setSelected(false);
        inputComentario.clearFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(inputComentario.getWindowToken(), 0);
    }

    @Override
    public void showCamera() {
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {
                        ArrayList<String> stringList = new ArrayList<>();
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
                            // Create the File where the photo should go
                            File photoFile = null;
                            try {
                                photoFile = createImageFile();
                            } catch (IOException ex) {
                                // Error occurred while creating the File
                            }
                            // Continue only if the File was successfully created
                            if (photoFile != null) {
                                Uri photoURI = FileProvider.getUriForFile(getContext(),
                                        BuildConfig.APPLICATION_ID+".provider",
                                        photoFile);
                                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);


                                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                            }
                        }
                    }
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(getContext(), "Se necesita permiso ", Toast.LENGTH_SHORT).show();
                    }
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();
    }

    String currentPhotoPath;
    String currentPhotoFileName;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        currentPhotoFileName = image.getName();
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void showGalery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, REQUEST_GALLERY_IMAGE);
    }

    @Override
    public void updateTareaArchivo(ArchivoUi item) {
        adjuntoAdapter.update(item);
    }

    @Override
    public void removeTareaArchivo(ArchivoUi item) {
        adjuntoAdapter.remove(item);
    }

    @Override
    public void addTareaArchivo(ArchivoUi item) {
        adjuntoAdapter.add(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEnviar:
                presenter.saveComentario(String.valueOf(inputComentario.getText()));
                break;
            default:
                presenter.closeDialog();
                break;
        }
    }

    @Override
    public void onClickComentarioNormal(MensajeUi mensajeUi) {
        presenter.onClickComentarioNormal(mensajeUi);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Map<Uri, String> photoPaths = new HashMap<>();
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            Uri uri = galleryAddPic();
            photoPaths.put(uri, currentPhotoFileName);
            presenter.onUpdload(photoPaths);
        }else if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_GALLERY_IMAGE){
            Uri imageUri = data.getData();
            photoPaths.put(imageUri, queryName(getContext().getContentResolver(), imageUri));
            presenter.onUpdload(photoPaths);
        }
    }

    private static String queryName(ContentResolver resolver, Uri uri) {
        Cursor returnCursor =
                resolver.query(uri, null, null, null, null);
        assert returnCursor != null;
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String name = returnCursor.getString(nameIndex);
        returnCursor.close();
        return name;
    }

    private Uri galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getContext().sendBroadcast(mediaScanIntent);
        return  contentUri;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        presenter.onTextChangedEditComentario(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClickRemoveTareaArchivo(ArchivoUi archivoUi) {
        presenter.removerComentarioArchivo(archivoUi);
    }

    @Override
    public void onClickTareaArchivo(ArchivoUi tareaArchivoUi) {
        ImageZomDialog imageZomDialog = new ImageZomDialog();
        imageZomDialog.show(getContext(),tareaArchivoUi.getUrl());
    }

    public static class OpcionesAdjuntarDialog extends BottomSheetDialogFragment implements View.OnClickListener {
        InfoRubroPresenter presenter;

        public OpcionesAdjuntarDialog(InfoRubroPresenter presenter) {
            this.presenter = presenter;
        }

        private Unbinder unbinder;
        private android.app.AlertDialog alertDialog;

        @BindView(R.id.bottomSheet)
        LinearLayout bottomSheet;
        @BindView(R.id.root)
        CoordinatorLayout root;

        private BottomSheetBehavior<LinearLayout> sheetBehavior;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View contentView = inflater.inflate(R.layout.dialog_seleccionar_archivo_evaluacion, container, false);
            unbinder = ButterKnife.bind(this, contentView);
            root.setOnClickListener(v -> dismiss());
            sheetBehavior = BottomSheetBehavior.from(bottomSheet);
            /**
             * bottom sheet state change listener
             * we are changing button text when sheet changed state
             * */
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            sheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                double SLIDEOFFSETHIDEN = -0.9f;

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    switch (newState) {

                        case BottomSheetBehavior.STATE_COLLAPSED: {
                            dismiss();
                            Log.d("BSB", "collapsed");
                        }
                        case BottomSheetBehavior.STATE_SETTLING: {

                            Log.d("BSB", "settling");
                        }
                        case BottomSheetBehavior.STATE_EXPANDED: {

                            Log.d("BSB", "expanded");
                        }
                        case BottomSheetBehavior.STATE_HIDDEN: {

                            Log.d("BSB", "hidden");
                        }
                        case BottomSheetBehavior.STATE_DRAGGING: {

                            Log.d("BSB", "dragging");
                        }
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    try {
                        if (SLIDEOFFSETHIDEN >= slideOffset) dismiss();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });
            return contentView;
        }

        @Override
        public void onClick(View view) {
            int i = view.getId();
        }

        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
        }

        public void onStart() {
            super.onStart();
        }

        @Override
        public void onPause() {
            super.onPause();
            if(alertDialog!=null)alertDialog.dismiss();
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            unbinder.unbind();
        }

        @OnClick({R.id.btn_camera, R.id.btn_galeria})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.btn_camera:
                    if(presenter!=null)presenter.onClickCamera();
                    dismiss();
                    break;
                case R.id.btn_galeria:
                    if(presenter!=null)presenter.onClickGalery();
                    dismiss();
                    break;
            }
        }

    }
}
