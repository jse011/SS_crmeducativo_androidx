package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.ui;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.ui.DialogCreareComportamiento;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.adapter.CasoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.adapter.ComportamientoAlumnoAdapter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.presenter.ComportAlumnoCPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.presenter.ComportAlumnoCPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoDataLocalSource;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.DeleteComportamiento;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetComportAlumnoList;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.UpdateSuccesDowloadCasoArchivo;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.lib.imageViewZoom.ImageZomDialog;
import com.consultoraestrategia.ss_crmeducativo.login2.service2.worker.SynckService;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.local.RepositorioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.preferents.RepositorioPreferentsDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote.RepositorioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.OpenIntents;
import com.consultoraestrategia.ss_crmeducativo.util.UtilsStorage;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.DOWNLOAD_SERVICE;


public class FragmentComportAlumnoC extends BaseFragment<ComportAlumnoCview, ComportAlumnoCPresenter, ListenerComportAlumnoC> implements ComportAlumnoCview, ListenerComportAlumnoC, DownloadItemListener {


    @BindView(R.id.rc_alumno_com)
    RecyclerView rc_alumno_com;
    String TAG = FragmentComportAlumnoC.class.getSimpleName();

    ComportamientoAlumnoAdapter comportamientoAlumnoAdapter;
    @BindView(R.id.textvacio)
    TextView textvacio;
    Unbinder unbinder;

    public static FragmentComportAlumnoC newInstance(Bundle bundle) {
        FragmentComportAlumnoC fragment = new FragmentComportAlumnoC();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected ComportAlumnoCPresenter getPresenter() {
        ComportamientoRepository comportamientoRepository = new ComportamientoRepository(new ComportamientoDataLocalSource(
                InjectorUtils.provideComportamientoDao(), InjectorUtils.providePersonaDao(),
                InjectorUtils.provideCursoDao(), InjectorUtils.provideParametrosDisenioDao(), InjectorUtils.provideSessionUserDao(), InjectorUtils.provideAlumnoDao()));

        presenter = new ComportAlumnoCPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources()
                , new GetComportAlumnoList(comportamientoRepository),
                new DeleteComportamiento(comportamientoRepository),
                new DowloadImageUseCase(new RepositorioRepository(
                        new RepositorioLocalDataSource(),
                        new RepositorioPreferentsDataSource(),
                        new RepositorioRemoteDataSource(getContext())
                )),
                new UpdateSuccesDowloadCasoArchivo(comportamientoRepository));

        return presenter;
    }

    @Override
    protected ComportAlumnoCview getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_comport_creados, container, false);
        return view;
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getContext().registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        initadapter();
    }

    private void initadapter() {
        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        CasoColumnGridLayoutManager columnCountProvider = new CasoColumnGridLayoutManager(getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        rc_alumno_com.setLayoutManager(autoColumnGridLayoutManager);
        comportamientoAlumnoAdapter = new ComportamientoAlumnoAdapter(new ArrayList<ComportamientoUi>(), this, this, rc_alumno_com);
        rc_alumno_com.setAdapter(comportamientoAlumnoAdapter);
        rc_alumno_com.setHasFixedSize(true);
    }
    @Override
    public void showListComportamiento(List<ComportamientoUi> comportamientoUiList) {
        textvacio.setVisibility(View.GONE);
        comportamientoAlumnoAdapter.setComportamientoUiList(comportamientoUiList);
    }

    @Override
    public void dialogDeleteComportamiento(CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.deleteComportamiento();
                        dialogInterface.dismiss();

                    }
                })
                .setNegativeButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void lauchEditComportamiento(ComportamientoUi comportamientoUi) {

        CRMBundle crmBundle = new CRMBundle();
        crmBundle.setCalendarioPeriodoId(comportamientoUi.getCalendarioPeridoId());
        crmBundle.setCargaAcademicaId(comportamientoUi.getCargaAcademicaId());
        crmBundle.setCargaCursoId(comportamientoUi.getCargaCursoId());
        crmBundle.setEmpleadoId(comportamientoUi.getDocenteId());
        crmBundle.setProgramaEducativoId(comportamientoUi.getIdprogramaEducativo());
        crmBundle.setGeoreferenciaId(comportamientoUi.getGeoreferenciaId());
        crmBundle.setEntidadId(comportamientoUi.getEntidadId());

        FragmentManager manager = getActivity().getSupportFragmentManager();
        DialogCreareComportamiento dialogCreareComportamiento = DialogCreareComportamiento.newInstance(crmBundle, comportamientoUi.getId());
        dialogCreareComportamiento.show(manager, DialogCreareComportamiento.class.getSimpleName());

    }

    @Override
    public void updateComportamiento(ComportamientoUi comportamientoUi) {
        comportamientoAlumnoAdapter.update(comportamientoUi);
    }

    @Override
    public void deleteComportamiento(ComportamientoUi comportamientoUi, int programaEducativoId) {
        comportamientoAlumnoAdapter.delete(comportamientoUi);
        SimpleSyncIntenService.start(getContext(), programaEducativoId);
        CMRE.saveNotifyChangeDataBase(getContext());
        SynckService.start(getContext(),programaEducativoId);
    }
    @Override
    public void showEmptyText() {
        textvacio.setVisibility(View.VISIBLE);
    }

    @Override
    public void setUpdateProgress(RepositorioFileUi repositorioFileUi, int count) {
        comportamientoAlumnoAdapter.updateProgress(repositorioFileUi, count);
    }

    @Override
    public void setUpdate(RepositorioFileUi repositorioFileUi) {
        comportamientoAlumnoAdapter.update(repositorioFileUi);
    }

    @Override
    public void leerArchivo(String path) {
        try {
            if (!TextUtils.isEmpty(path))
                OpenIntents.openFile(FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", new File(Uri.parse(path).getPath())), getContext());

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(), getContext().getString(R.string.cannot_open_file),Toast.LENGTH_SHORT).show();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onclickOpciones(final ComportamientoUi comportamientoUi, View view, Context context) {
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(context, view);
        DroppyMenuPopup droppyMenu = droppyBuilder
                .addMenuItem(new DroppyMenuItem("Editar"))
                .addMenuItem(new DroppyMenuItem("Eliminar"))
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int positionMenu) {
                        Log.d("positionMenu:", String.valueOf(positionMenu));
                        presenter.onSelectOpcionMenuComportamiento(comportamientoUi, positionMenu);
                    }
                })
                .setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback() {
                    @Override
                    public void call() {
                        Log.d("call:", "dismiss");
                    }
                })
                .setPopupAnimation(new DroppyFadeInAnimation())
                .setXOffset(5)
                .setYOffset(5)
                .build();
        droppyMenu.show();
    }

    @Override
    public void onClickComportamientoAlumno(ComportamientoUi comportamientoUi) {
        presenter.onClickComportamientoAlumno(comportamientoUi);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getContext().unregisterReceiver(onDownloadComplete);
        unbinder.unbind();
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

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

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
    public void onClickArchivo(RepositorioFileUi repositorioFileUi) {
        if(repositorioFileUi.getTipoFileU()== RepositorioTipoFileU.IMAGEN){
            ImageZomDialog imageZomDialog = new ImageZomDialog();
            imageZomDialog.show(getContext(),repositorioFileUi.getUrl());
        }else {
            try {
                DownloadManager.Request r = new DownloadManager.Request(Uri.parse(repositorioFileUi.getUrl()));
                r.setTitle(repositorioFileUi.getNombreArchivo());
                r.setDescription(getResources().getString(com.consultoraestrategia.ss_crmeducativo.core2.R.string.app_name));
                r.setMimeType(UtilsStorage.getMimeType(repositorioFileUi.getNombreArchivo()));
                // This put the download in the same Download dir the browser uses
                //r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, archivoPreview);
                r.setAllowedOverRoaming(false);
                r.setDestinationUri(Uri.fromFile(new File(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), repositorioFileUi.getNombreArchivo())));
                // When downloading music and videos they will be listed in the player
                // (Seems to be available since Honeycomb only)
                r.allowScanningByMediaScanner();

                // Notify user when download is completed
                // (Seems to be available since Honeycomb only)
                r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                // Start download
                DownloadManager dm = (DownloadManager) getContext().getSystemService(DOWNLOAD_SERVICE);
                repositorioFileUi.setDownloadId(dm.enqueue(r));
            } catch (Exception e) {
                e.printStackTrace();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(repositorioFileUi.getUrl())));
            }
        }
    }




}
