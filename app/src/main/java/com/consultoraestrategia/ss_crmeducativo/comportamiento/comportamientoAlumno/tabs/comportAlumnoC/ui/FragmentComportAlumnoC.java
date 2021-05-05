package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
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
import com.consultoraestrategia.ss_crmeducativo.login2.service2.worker.SynckService;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.local.RepositorioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.preferents.RepositorioPreferentsDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote.RepositorioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.OpenIntents;
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
                OpenIntents.openFile(FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", new File(path)), getContext());

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(), getContext().getString(R.string.cannot_open_file),Toast.LENGTH_SHORT).show();
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
        presenter.onClickArchivo(repositorioFileUi);
    }


}
