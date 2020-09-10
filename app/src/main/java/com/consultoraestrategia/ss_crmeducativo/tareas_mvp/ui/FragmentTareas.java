package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.cabecera.ui.CrearRubroCabeceraActividad;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.BaseTabFragmentView;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.CreateRubBidActivity;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.grupal.ui.EvaluacionBimencionalGrupalActividad;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.individual.ui.EvaluacionBimencionalActividad;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.contenedor.EvaluacionContainerActivity;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.ui.RegistroFragment;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.ui.RegistroGrupoFragment;

import com.consultoraestrategia.ss_crmeducativo.login2.service2.worker.SynckService;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.local.RepositorioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.preferents.RepositorioPreferentsDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote.RepositorioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageTarea.ui.SendMessageTareaActivity;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ui.ImportarActivity;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.TareasMvpPresenter;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.TareasMvpPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.TareasMvpView;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.adapters.UnidadesAdapter;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.bundle.CrearTareaBundle;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.ui.CrearTareasActivity;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.TareasMvpRepository;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.local.TareasLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.remote.RemoteMvpDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase.GetParametroDisenio;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase.GetTareasUIList;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase.MoverArchivosAlaCarpetaTarea;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase.UpdateSuccesDowloadArchivo;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.HeaderTareasAprendizajeUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RubroEvalProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.listeners.TareasUIListener;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.listeners.UnidadAprendizajeListener;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.OpenIntents;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by irvinmarin on 10/11/2017.
 */

public class FragmentTareas extends Fragment implements TareasMvpView, UnidadAprendizajeListener, TareasUIListener, BaseTabFragmentView {

    private static final String TAG = FragmentTareas.class.getSimpleName();
    View viewPadre;

    @BindView(R.id.rvSesionesMvp)
    RecyclerView rvUnidades;
    @BindView(R.id.txtmensaje)
    TextView txtmensaje;
    @BindView(R.id.progressBar3)
    ProgressBar progressBar3;

    TareasMvpPresenter presenter;
    ParametroDisenioUi parametroDisenioUi;

    public static final String mIdCargaCurso = "FragmentTareas.mIdCargaCurso";
    public static final String mIdCurso = "FragmentTareas.mIdCurso";
    public static final String mSesionAprendizajeId = "FragmentTareas.mSesionAprendizajeId";
    public static final String tipoTareas = "FragmentTareas.tipoTareas";
    public static final String mParametrodisenioid = "FragmentTareas.parametrodisenioid";
    public static final String mCalendarioPeriodoid = "FragmentTareas.mCalendarioPeriodoid";

    private Unbinder unbinder;


    public static FragmentTareas newInstance(Bundle bundle, int idCargaCurso, int idCurso, int parametrodisenioid, CRMBundle crmBundle, int idCalendarioPeriodo) {
        Log.d(TAG, "newInstance");
        bundle.putInt(FragmentTareas.tipoTareas, 0);
        bundle.putInt(FragmentTareas.mIdCargaCurso, idCargaCurso);
        bundle.putInt(FragmentTareas.mSesionAprendizajeId, 0);
        bundle.putInt(FragmentTareas.mIdCurso, idCurso);
        bundle.putInt(FragmentTareas.mParametrodisenioid, parametrodisenioid);
        bundle.putInt(FragmentTareas.mCalendarioPeriodoid, idCalendarioPeriodo);
        bundle.putAll(crmBundle.instanceBundle());
        FragmentTareas fragment = new FragmentTareas();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPadre = inflater.inflate(R.layout.tab_item_tarea_mvp, container, false);
        unbinder = ButterKnife.bind(this, viewPadre);
        setupPresenter();

        return viewPadre;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void setupPresenter() {

        presenter = new TareasMvpPresenterImpl(
                new UseCaseHandler(
                        new UseCaseThreadPoolScheduler()),
                new GetTareasUIList(
                        TareasMvpRepository.getInstace(
                                new TareasLocalDataSource(),
                                new RemoteMvpDataSource())),
                new GetParametroDisenio(
                        TareasMvpRepository.getInstace(
                                new TareasLocalDataSource(),
                                new RemoteMvpDataSource())),
                new DowloadImageUseCase(new  RepositorioRepository(
                        new RepositorioLocalDataSource(),
                        new RepositorioPreferentsDataSource(),
                        new RepositorioRemoteDataSource(ApiRetrofit.getInstance()))),
                new UpdateSuccesDowloadArchivo(
                        TareasMvpRepository.getInstace(
                                new TareasLocalDataSource(),
                                new RemoteMvpDataSource())),
                new MoverArchivosAlaCarpetaTarea(
                        TareasMvpRepository.getInstace(
                                new TareasLocalDataSource(),
                                new RemoteMvpDataSource())));

        setPresenter(presenter);
        Log.d(TAG, "onCreate");

    }


    UnidadesAdapter unidadesAdapter;


    private void setupRvUnidad() {
//        adapter = new HeaderTareasAdapter(new ArrayList<HeaderTareasAprendizajeUI>(), this, this, null, rvUnidades, 1, status);
//        rvUnidades.setLayoutManager(new LinearLayoutManager(getContext()));
//        rvUnidades.setAdapter(adapter);
        //rvUnidades.setAdapter(unidadesAdapter);

        unidadesAdapter = new UnidadesAdapter(rvUnidades,new ArrayList<HeaderTareasAprendizajeUI>(), this, this, null, rvUnidades, 1);
        unidadesAdapter.setRecyclerView(rvUnidades);
        rvUnidades.setAdapter(unidadesAdapter);
        StickyHeaderLayoutManager layoutManager = new StickyHeaderLayoutManager();
        rvUnidades.setLayoutManager(layoutManager);
        int resId = R.anim.layout_animation_from_bottom;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        rvUnidades.setLayoutAnimation(animation);
        ((SimpleItemAnimator) rvUnidades.getItemAnimator()).setSupportsChangeAnimations(false);
    }


    @Override
    public void setPresenter(TareasMvpPresenter presenter) {
        presenter.setExtras(getArguments());
        presenter.attachView(this);
        presenter.onCreate();
        setupRvUnidad();

    }

    @Override
    public void showTareasUIList(List<HeaderTareasAprendizajeUI> headerTareasAprendizajeUIList, int idCurso, ParametroDisenioUi parametroDisenioUi) {
        unidadesAdapter.setmIdCurso(idCurso);
        unidadesAdapter.setTareasUIList(headerTareasAprendizajeUIList, parametroDisenioUi);
    }

    @Override
    public void hideTareasUIList() {
        rvUnidades.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar3.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar3.setVisibility(View.GONE);
    }

    @Override
    public void showActivityCrearTareas(HeaderTareasAprendizajeUI headerTareasAprendizajeUI, int idUsuario, int idSilaboEvento, int mSesionAprendizajeId, int mIdCurso, String colorCurso, int programaEducativoId) {

        CrearTareaBundle crearTareaBundle = new CrearTareaBundle(getArguments());
        crearTareaBundle.setHeaderTareasAprendizajeUI(headerTareasAprendizajeUI);
        crearTareaBundle.setUsuarioId(idUsuario);
        crearTareaBundle.setSilavoId(idSilaboEvento);
        crearTareaBundle.setSesionAprendizajeId(mSesionAprendizajeId);
        crearTareaBundle.setCursoId(mIdCurso);
        crearTareaBundle.setColorCurso(colorCurso);
        crearTareaBundle.setProgramaEducativoId(programaEducativoId);
        CrearTareasActivity.launchActivityCrearTareas(getContext(),crearTareaBundle);
    }

    @Override
    public void showlActivityEditTareas(TareasUI tareasUI, HeaderTareasAprendizajeUI headerTareasAprendizajeUI, int idUsuario, int idSilaboEvento, int mSesionAprendizajeId, int mIdCurso, String colorCurso, int programaEducativoId) {

        CrearTareaBundle crearTareaBundle = new CrearTareaBundle(getArguments());
        crearTareaBundle.setTareasUI(tareasUI);
        crearTareaBundle.setHeaderTareasAprendizajeUI(headerTareasAprendizajeUI);
        crearTareaBundle.setUsuarioId(idUsuario);
        crearTareaBundle.setSilavoId(idSilaboEvento);
        crearTareaBundle.setSesionAprendizajeId(mSesionAprendizajeId);
        crearTareaBundle.setCursoId(mIdCurso);
        crearTareaBundle.setColorCurso(colorCurso);
        crearTareaBundle.setProgramaEducativoId(programaEducativoId);
        CrearTareasActivity.launchActivityCrearTareas(getContext(), crearTareaBundle);
    }

    @Override
    public void exportarTareasEliminadas(int programaEducativoId) {
        CallService.jobServiceExportarTipos(getContext(), TipoExportacion.TAREA);
        SimpleSyncIntenService.start(getContext(), programaEducativoId);
        SynckService.start(getContext(),programaEducativoId);
        CMRE.saveNotifyChangeDataBase(getContext());
    }

    @Override
    public void showMessage() {
        txtmensaje.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMessage() {
        txtmensaje.setVisibility(View.GONE);
    }


    @Override
    public void onShowActualizarTareas(BEVariables beVariables) {
        ImportarActivity.launchImportarActivity(getContext(), TipoImportacion.TAREA, beVariables);
    }

    @Override
    public void showServiceExportTarea(int programaEducativoId) {
        CallService.jobServiceExportarTipos(getContext(), TipoExportacion.TAREA);
        SimpleSyncIntenService.start(getContext(), programaEducativoId);
        SynckService.start(getContext(),programaEducativoId);
        CMRE.saveNotifyChangeDataBase(getContext());
    }

    @Override
    public void showActivityCrearRubro(TareasUI tareasUI, int silavoEventoId, int idCalendarioPeriodo, int programaEducativoId, int mSesionAprendizajeId, String colorCurso, int idCurso) {
        Intent intent = CrearRubroCabeceraActividad.launchCrearRubroEvaluacionActivity(getContext(),0,silavoEventoId,idCalendarioPeriodo, 0, 0, new ArrayList<Integer>() ,programaEducativoId, tareasUI.getKeyTarea(), idCurso);
        startActivityForResult(intent, CrearRubroCabeceraActividad.REQUESTCODE);
    }

    @Override
    public void showActivityCrearRubrica(TareasUI tareasUI, int idCalendarioPeriodo, int idCargaCurso, int idCurso, int programaEducativoId, int mSesionAprendizajeId, String colorCurso) {

        CRMBundle crmBundle = new CRMBundle(getArguments());
        crmBundle.setCargaCursoId(idCargaCurso);
        crmBundle.setCursoId(idCurso);
        crmBundle.setCalendarioPeriodoId(idCalendarioPeriodo);
        crmBundle.setProgramaEducativoId(programaEducativoId);
        crmBundle.setCargaCursoId(idCargaCurso);
        crmBundle.setCursoId(idCurso);
        crmBundle.setProgramaEducativoId(programaEducativoId);
        crmBundle.setTareaId(tareasUI.getKeyTarea());
        crmBundle.setCalendarioEditar(tareasUI.isCalendarioEditar());
        crmBundle.setCalendarioActivo(tareasUI.isCalendarioVigente());
        Intent intent = CreateRubBidActivity.getStartCreateRubBidActivity(getContext(),crmBundle);
        startActivityForResult(intent, CreateRubBidActivity.REQUESTCODE);
    }

    @Override
    public void showEvaluacionRubricaIndividual(TareasUI tareasUI, RubroEvalProcesoUi rubroEvalProcesoUi, int idCargaCurso, String colorCurso) {
        CRMBundle crmBundle = new CRMBundle(getArguments());
        crmBundle.setCalendarioEditar(tareasUI.isCalendarioEditar());
        crmBundle.setCalendarioActivo(tareasUI.isCalendarioVigente());
        EvaluacionBimencionalActividad.launchEvaluacionBimencionalActividadIndividual(getContext(),crmBundle.instanceBundle(),rubroEvalProcesoUi.getId(), idCargaCurso);
    }

    @Override
    public void showEvaluacionRubricaGrupal(TareasUI tareasUI, RubroEvalProcesoUi rubroEvalProcesoUi, int idCargaCurso, String colorCurso) {
        CRMBundle crmBundle = new CRMBundle(getArguments());
        crmBundle.setCalendarioEditar(tareasUI.isCalendarioEditar());
        crmBundle.setCalendarioActivo(tareasUI.isCalendarioVigente());
        EvaluacionBimencionalGrupalActividad.launchEvalRubBidActivity(getContext(),crmBundle.instanceBundle(),rubroEvalProcesoUi.getId(), idCargaCurso);
    }

    @Override
    public void showEvaluacionRubroGrupal(TareasUI tareasUI, RubroEvalProcesoUi rubroEvalProcesoUi, int idCargaCurso, int mSesionAprendizajeId, int idCurso, String colorCurso) {
        CRMBundle crmBundle = new CRMBundle(getArguments());
        crmBundle.setCalendarioEditar(tareasUI.isCalendarioEditar());
        crmBundle.setCalendarioActivo(tareasUI.isCalendarioVigente());

        Intent intent = EvaluacionContainerActivity.createContainerActivityIntent(getContext(),getArguments(), rubroEvalProcesoUi.getId(), rubroEvalProcesoUi.getTitulo(), !crmBundle.isCalendarioActivo(), RegistroGrupoFragment.class,mSesionAprendizajeId, idCargaCurso, idCurso, null);
        startActivity(intent);
    }

    @Override
    public void showEvaluacionRubroIndividual(TareasUI tareasUI, RubroEvalProcesoUi rubroEvalProcesoUi, int idCargaCurso, int mSesionAprendizajeId, int idCurso, String colorCurso) {
        CRMBundle crmBundle = new CRMBundle(getArguments());
        crmBundle.setCalendarioEditar(tareasUI.isCalendarioEditar());
        crmBundle.setCalendarioActivo(tareasUI.isCalendarioVigente());

        Intent intent = EvaluacionContainerActivity.createContainerActivityIntent(getContext(),getArguments(), rubroEvalProcesoUi.getId(), rubroEvalProcesoUi.getTitulo(), !crmBundle.isCalendarioActivo(), RegistroFragment.class, mSesionAprendizajeId, idCargaCurso, idCurso, null);
        startActivity(intent);
    }

    @Override
    public void onParentFabClicked() {

    }

    @Override
    public void setUpdateProgress(RepositorioFileUi repositorioFileUi, int count) {
        unidadesAdapter.updateProgress(repositorioFileUi, count);
    }

    @Override
    public void setUpdate(RepositorioFileUi repositorioFileUi) {
        unidadesAdapter.update(repositorioFileUi);
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
    public void showVinculo(String url) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(), "Error al abrir el vínculo", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void showYoutube(String url) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }catch (Exception e){
            Toast.makeText(getContext(), "Error al abrir el vínculo de youtube", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showCrearTarea(HeaderTareasAprendizajeUI headerTareasAprendizajeUI, int idSilaboEvento, int idCargaCurso) {
        //Solo Sesiones
    }

    @Override
    public void updateTarea(TareasUI tareasUI) {
        unidadesAdapter.updateItem(tareasUI);
    }


    @Override
    public void onResume() {
        super.onResume();
//        Toast.makeText(getContext(), "OnResume", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBtnCrearTareaClicked(HeaderTareasAprendizajeUI headerTareasAprendizajeUI, int idSilaboEvento, int mIdCurso) {
        presenter.onClickedCrearTarea(headerTareasAprendizajeUI, idSilaboEvento, mIdCurso);
//        DialogFragment dialog = CrearTareasDialog.newInstace(null, this, headerTareasAprendizajeUISelected, 19, this, idSilaboEvento, mIdCurso);
//        dialog.show(getFragmentManager(), "CrearTareasDialog");
//        Log.d(TAG, "idUnidadAprendizaje : " + idUnidadAprendizaje);

    }

    @Override
    public void onReloadAdapter(HeaderTareasAprendizajeUI headerTareasAprendizajeUI, HeaderTareasAprendizajeUI nuevaHeaderTareasAprendizajeUI, int opcionAccion) {

        switch (opcionAccion) {
            case 0:
                showMsjTarea("Creada");
                break;
            case 1:
                showMsjTarea("Editada");
                break;
        }
        setupRvUnidad();

    }

    @Override
    public void onClickOpcion(View view, final HeaderTareasAprendizajeUI headerTareasAprendizajeUI) {
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(getContext(), view);
        DroppyMenuPopup droppyMenu = droppyBuilder
                //.fromMenu(R.menu.droppy)
                .addMenuItem(new DroppyMenuItem("Importar"))
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int positionMenu) {
                        Log.d("positionMenu:", String.valueOf(positionMenu));
                        switch (positionMenu) {
                            case 0:
                                presenter.onActualizarTarea(headerTareasAprendizajeUI);
                                break;
                        }
                    }
                })
                .setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback() {
                    @Override
                    public void call() {
                        Log.d(TAG, "onDismissCallback");
                    }
                })
                .setPopupAnimation(new DroppyFadeInAnimation())
                .setXOffset(5)
                .setYOffset(5)
                .build();
        droppyMenu.show();
    }

    private void showMsjTarea(String Msj) {
        Toast.makeText(getContext(), "Tarea " + Msj, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onOpTareaEditClicked(TareasUI tareasUI, HeaderTareasAprendizajeUI headerTareasAprendizajeUI) {
        presenter.onClickedOpTareaEdit(tareasUI, headerTareasAprendizajeUI);
        Log.d(TAG, "tareasUI edit : " + tareasUI);
    }

    @Override
    public void onOpTareaDelteClicked(TareasUI tareasUI) {
        presenter.deleteTarea(tareasUI);
    }

    @Override
    public void onOpNotificarTareasClicked(TareasUI tareasUI, HeaderTareasAprendizajeUI headerTareasAprendizajeUI) {
        int idCargaCurso = getArguments().getInt(FragmentTareas.mIdCargaCurso);

        PersonaDao personaDao = InjectorUtils.providePersonaDao();

        SendMessageTareaActivity.launchSendMessageTareaActivity(
                getContext(),
                new ArrayList<Persona>(),//personaDao.getAlumnosDeCargaCurso(idCargaCurso),
                idCargaCurso,
                tareasUI,
                tareasUI.getTituloTarea(),
                tareasUI.getDescripcion(),
                Utils.getFechaDetalle(tareasUI.getFechaLimite())
        );
    }

    @Override
    public void onClikEstado(TareasUI tareasUI) {
        presenter.onChangeEstado(tareasUI);
    }

    @Override
    public void onOpCrearRubricaClicked(TareasUI tareasUI, HeaderTareasAprendizajeUI headerTareasAprendizajeUI) {
        presenter.onCrearRubrica(tareasUI, headerTareasAprendizajeUI);
    }

    @Override
    public void onOpCrearRubroClicked(TareasUI tareasUI, HeaderTareasAprendizajeUI headerTareasAprendizajeUI) {
        presenter.onCrearRubro(tareasUI, headerTareasAprendizajeUI);
    }

    @Override
    public void onClikRubroTarea(TareasUI tareasUI) {
        presenter.onClikRubroTarea(tareasUI);
    }


    public void onReloadFragmentTareas() {
        Log.d(TAG, "onReloadFragmentTareas");
        setupRvUnidad();
    }

    @Override
    public void onResumeFragment() {
        presenter.onResumeFragment();
    }
    public void onActualizarTareasPeriodo(String idCalendarioPeriodo, boolean status){

        CRMBundle crmBundle= new CRMBundle(getArguments());
        crmBundle.setCalendarioPeriodoId(Integer.parseInt(idCalendarioPeriodo));
        crmBundle.setCalendarioActivo(status);
        if(getArguments()!=null)
            getArguments().putAll(crmBundle.instanceBundle());

        presenter.actualizarTareasPeriodo(idCalendarioPeriodo);
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
