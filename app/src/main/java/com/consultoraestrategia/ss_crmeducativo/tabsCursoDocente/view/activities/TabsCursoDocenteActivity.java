package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.view.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.listener.FilterChooserSuccessListener;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.view.FilterChooserBottomSheetDialog;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.ui.AsistenciaCursoFragment;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.base.filterChooser.CallbackFilterChooserBottomDialog;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.ui.CambiarFotoAlumnoActivity;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.ui.ComportamientoFragment;
import com.consultoraestrategia.ss_crmeducativo.core2.application.Core2;
import com.consultoraestrategia.ss_crmeducativo.core2.listener.Core2Listener;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.MyFragmentAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.listener.RubricaFragmentListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtradorDialogFragment.listener.FiltradoSuccessListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.view.EvaluacionCompetenciasFragment;
import com.consultoraestrategia.ss_crmeducativo.grouplist.ui.ListaGrupoFragment;
import com.consultoraestrategia.ss_crmeducativo.lib.GlideImageLoader;
import com.consultoraestrategia.ss_crmeducativo.lib.ImageLoader;
import com.consultoraestrategia.ss_crmeducativo.login2.progress.ListenerProgress;
import com.consultoraestrategia.ss_crmeducativo.login2.progress.ProgressDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.listener.RefrescarListener;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.RubricaBidFragment;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroProcesoListaListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.silabo.RubroResultadoSilaboFragment;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroSilaboListener;
import com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification.CrmeNotificationServiceImpl;
import com.consultoraestrategia.ss_crmeducativo.services.data.local.ServiceLocalDataRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ui.ImportarActivity;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.ComplejoSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.ContratoAlumnoSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.login2.service2.ServicesActivity;
import com.consultoraestrategia.ss_crmeducativo.sesiones.view.fragments.FragmentUnidades;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase.ChangeDataBaseDocenteMentor;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase.GetIsAprendizajeColegio;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase.GetIsTutor;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase.GetParametroDisenio;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase.GetPeriodoList;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase.PrimeravesCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.listener.PeriodoListener;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.data.TabsCursoRepository;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.data.local.TabsCursoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.view.adapters.PeriodoAdapter;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.view.progressDialog.DialogUpdateAlumnos;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.ui.FragmentTareas;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabsCursoDocenteActivity extends BaseActivity<TabCursoDocenteView, TabCursoDocentePresenter> implements TabCursoDocenteView, RubricaFragmentListener, FiltradoSuccessListener, RubroSilaboListener, RefrescarListener, FilterChooserSuccessListener, CrmeNotificationServiceImpl.EventNotificacion, CallbackFilterChooserBottomDialog, PeriodoListener, Core2Listener, RubroProcesoListaListener, ListenerProgress {


    private static final String TAG =TabsCursoDocenteActivity.class.getSimpleName();
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    public static final int UPDATE_RUBRICA = 48123;
    public static final int CHANGE_DATABASE_TABSESION = 48147;// comunicar que se  modifico la base datos
    public static final int CHANGE_DATABASE_SERVICE2 = 48150;// comunicar que se  modifico la base datos
    public static final String RELOAD_X_CERRAR_CURSO = "Reinicio_cerrar_curso";
    @BindView(R.id.imgBackgroundAppbar)
    ImageView imgBackgroundAppbar;
    @BindView(R.id.txtNombreCurso)
    TextView txtNombreCurso;
    @BindView(R.id.txtPeriodoSeccion)
    TextView txtPeriodoSeccion;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_curso)
    TabLayout tabCurso;
    @BindView(R.id.ctl_tabcursos)
    CollapsingToolbarLayout ctlTabcursos;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.vp_Curso)
    ViewPager vpCurso;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.root)
    CoordinatorLayout root;
    @BindView(R.id.recyclerPeriodo)
    RecyclerView recyclerPeriodo;
    @BindView(R.id.toolbarprogress)
    ProgressBar toolbarprogress;
    @BindView(R.id.content)
    ConstraintLayout content;
    private PeriodoAdapter periodoAdapter;
    private ImageLoader imageLoader;
    private Menu menu;
    private CrmeNotificationServiceImpl crmeNotification;
    @BindView(R.id.msg_calendario_periodo)
    TextView msgCalendarioPeriodo;
    @BindView(R.id.fabgrupo)
    FloatingActionButton fabgrupo;
    private MyFragmentAdapter fragmentAdapter;
    private DialogUpdateAlumnos dialogUpdateAlumnos;
    private ProgressDialogFragment progressDialogFragment;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected TabCursoDocentePresenter getPresenter() {
        TabsCursoRepository tabsCursoRepository = new TabsCursoRepository(new TabsCursoLocalDataSource(InjectorUtils.provideCalendarioPeriodo(), InjectorUtils.provideDimensionObservadaDao()));
        return new TabCursoDocentePresenteV2Impl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new GetPeriodoList(tabsCursoRepository),
                new GetIsAprendizajeColegio(tabsCursoRepository),
                new ChangeDataBaseDocenteMentor(new ServiceLocalDataRepositoryImpl(InjectorUtils.provideSessionUserDao())),
                new GetIsTutor(tabsCursoRepository),
                new GetParametroDisenio(tabsCursoRepository),
                new PrimeravesCursoDocente(tabsCursoRepository)
                );
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        desbloqOrientation();
    }

    @Override
    protected TabCursoDocenteView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_tabs_curso);
        ButterKnife.bind(this);
        imageLoader = new GlideImageLoader(this);
        setupToolbar();
        setupImagenColap();
        setupDataBaseRealTime();
        setupAdapterPeriodo();
        setupCore2Listener();
        setTitle("");
    }

    private void setupCore2Listener() {
        Core2.getCore2(this).addCore2Listener(this);
    }

    private void setupDataBaseRealTime() {
        crmeNotification = CrmeNotificationServiceImpl.init(this);
        crmeNotification.setEventNotificacion(this);
    }

    private void setupImagenColap() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return;
        String urlBackgroudResource = bundle.getString("backgroudResource");
        String bgColor = bundle.getString("fondo");
        showAppbarBackground(urlBackgroudResource, bgColor);
        /*try {
            byte[] byteArray = bundle.getByteArray("imagenByte");
            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imgBackgroundAppbar.setImageBitmap(bmp);
            appBar.setBackgroundColor(Color.parseColor(bgColor));
            getIntent().removeExtra("imagenByte");
        } catch (Exception e) {
            e.printStackTrace();

        }*/

    }

    private void setupAdapterPeriodo() {
        recyclerPeriodo.setVisibility(View.VISIBLE);
        periodoAdapter = new PeriodoAdapter(new ArrayList<PeriodoUi>(), this);
        recyclerPeriodo.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerPeriodo.setHasFixedSize(true);
        recyclerPeriodo.setAdapter(periodoAdapter);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progress;
    }


    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBar.setTransitionName("view");
            //change color of status bar
            try {
                String bgColor = getIntent().getExtras().getString("fondo");
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(Color.parseColor(bgColor));
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        Log.d(TAG, "onFabClicked");
        int pagePosition = vpCurso.getCurrentItem();
        //Log.d(TAG, "pagePosition: " + pagePosition);
        presenter.onFabClicked(pagePosition);
    }

    @OnClick(R.id.fabgrupo)
    public void onFabClickedGrupo() {
        presenter.onFabClickedGrupos();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_activity, menu);
        if(presenter!=null)presenter.onCreateOptionsMenu();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refrescar:
                presenter.onClickRefrescar();
                return true;
            case R.id.menu_update:
                presenter.onClickUpdate();
                break;
            case R.id.menu_update_alumnos:
                onClickUpadteAlumnos();
                break;
            case R.id.menu_update_foto_alumnos:
                onClickUpadteFotoAlumnos();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onClickUpadteFotoAlumnos() {
      presenter.onClickUpadteFotoAlumnos();

    }

    private void onClickUpadteAlumnos() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Información");
        builder.setMessage(R.string.msg_update_alumno_contrato);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Log.d(TAG, "Presionos ACEPTAR");
                presenter.onClickUpadteAlumnos();
                dialogInterface.cancel();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });
        //Create AdapterExample
        builder.create().show();
    }



    private void hideOption(int id) {
        if(menu!=null){
            MenuItem item = menu.findItem(id);
            if (item == null) return;
            item.setVisible(false);
        }
    }

    private void showOption(int id) {
        if(menu!=null){
            MenuItem item = menu.findItem(id);
            if (item == null) return;
            item.setVisible(true);
        }

    }

    @Override
    public void onResumenFragmentRubrica(final int idCalendarioPeriodo, boolean status) {
        RubricaBidFragment rubricaBidFragment = getFragment(RubricaBidFragment.class);
        if (rubricaBidFragment == null) return;
        Log.d(TAG, "onResumenFragmentRubrica" + idCalendarioPeriodo);
        rubricaBidFragment.onActualizarRubricaPeriodo(String.valueOf(idCalendarioPeriodo), status);

    }

    @Override
    public void onResumenFragmentResultado(int calendarioPeriodoId, boolean status) {
        EvaluacionCompetenciasFragment evaluacionCompetenciasFragment = getFragment(EvaluacionCompetenciasFragment.class);
        if (evaluacionCompetenciasFragment == null) return;
        evaluacionCompetenciasFragment.onActualizarResultadosPeriodos(String.valueOf(calendarioPeriodoId), status);
    }

    @Override
    public void onResumenFragmentGrupos() {
        Log.d(TAG, "onResumenFragmentGrupos");
        ListaGrupoFragment groupListFragment = getFragment(ListaGrupoFragment.class);
        if (groupListFragment == null) return;
        groupListFragment.onResumeFragment();
    }

    @Override
    public void onResumenFragmentRegistro(int calendarioPeriodoId, boolean status) {
        Log.d(TAG, "onResumenFragmentRegistro2");
        RubroResultadoSilaboFragment rubroResultadoSilaboFragment = getFragment(RubroResultadoSilaboFragment.class);
        if (rubroResultadoSilaboFragment == null) return;
        rubroResultadoSilaboFragment.onActualizarFragment(String.valueOf(calendarioPeriodoId), status);

    }

    @Override
    public void onResumenFragmentTareas(int calendarioPeriodoId, boolean status) {
        FragmentTareas fragmentTareas = getFragment(FragmentTareas.class);
        if (fragmentTareas == null) return;
        fragmentTareas.onActualizarTareasPeriodo(String.valueOf(calendarioPeriodoId),  status);
    }

    @Override
    public void showActualizarRubros(BEVariables beVariables) {
        RubroResultadoSilaboFragment rubroResultadoSilaboFragment = getFragment(RubroResultadoSilaboFragment.class);
        if (rubroResultadoSilaboFragment == null) return;
        int calendarioId = rubroResultadoSilaboFragment.getCalendarioPeriodoId();
        beVariables.setCalendarioPeriodoId(calendarioId);
        Intent intent = ImportarActivity.launchImportarActivityIntent(this, TipoImportacion.RUBROEVALUACION_CALENDARIO, beVariables);
        startActivityForResult(intent, UPDATE_RUBRICA);
    }

    @Override
    public void showActualizarGrupos(BEVariables beVariables) {
        ImportarActivity.launchImportarActivity(this, TipoImportacion.GRUPOS, beVariables);
    }

    @Override
    public void showActualizarRubrica(BEVariables beVariables) {
        RubricaBidFragment rubricaBidFragment = getFragment(RubricaBidFragment.class);
        if (rubricaBidFragment == null) return;
        int calendarioId = rubricaBidFragment.getCalendarioPeriodoId();
        beVariables.setCalendarioPeriodoId(calendarioId);
        Intent intent = ImportarActivity.launchImportarActivityIntent(this, TipoImportacion.RUBROEVALUACION_CALENDARIO, beVariables);
        startActivityForResult(intent, UPDATE_RUBRICA);
    }

    @Override
    public void hideBtnActualizar() {
    }

    @Override
    public void showBtnActualizar() {
    }

    @Override
    public void onEvaluacionRubroSilaboFragmentFabClicked(ArrayList<CompetenciaCheck> tipoCompetencia) {
        for (CompetenciaCheck c : tipoCompetencia)
            Log.d(TAG, "TABCURSODOCENTE  " + c.getTipoCompetencia() + " estado " + c.getChecked());
        RubroResultadoSilaboFragment rubroResultadoSilaboFragment = getFragment(RubroResultadoSilaboFragment.class);
        if (rubroResultadoSilaboFragment != null) {
            rubroResultadoSilaboFragment.onParentFabClicked(tipoCompetencia);
        }
    }


    @Override
    public void onFiltroGrupos(boolean filtro) {
        if (filtro) rotateFabForward();
        else rotateFabBackward();
        ListaGrupoFragment groupListFragment = getFragment(ListaGrupoFragment.class);
        if (groupListFragment == null) return;
        groupListFragment.onFilterListGrupos();
    }

    public void rotateFabForward() {
        Log.d(TAG, "true");
        ViewCompat.animate(fabgrupo)
                .rotation(180f)
                .withLayer()
                .setDuration(300L)
                .setInterpolator(new OvershootInterpolator(10.0F))
                .start();
    }

    public void rotateFabBackward() {
        Log.d(TAG, "false");
        ViewCompat.animate(fabgrupo)
                .rotation(0f)
                .withLayer()
                .setDuration(300L)
                .setInterpolator(new OvershootInterpolator(10.0F))
                .start();
    }

    @Override
    public void onCallSinckService(int programaEducativoId) {
        ComplejoSyncIntenService.start(this, programaEducativoId);
        CMRE.saveNotifyChangeDataBase(this);
    }

    @Override
    public void onResumenAsistenciaCursoFragment(final int idCalendarioPeriodo, boolean status) {

        AsistenciaCursoFragment asistenciaCursoFragment = getFragment(AsistenciaCursoFragment.class);
        if (asistenciaCursoFragment == null) return;
        asistenciaCursoFragment.onResumenFragment(String.valueOf(idCalendarioPeriodo));
    }

    @Override
    public void onResumenFragmentComportamiento(int calendarioPeriodoId, boolean status) {
        ComportamientoFragment comportamientoFragment = getFragment(ComportamientoFragment.class);
        if (comportamientoFragment == null) return;
        comportamientoFragment.onResumenFragment(String.valueOf(calendarioPeriodoId));
    }

    @Override
    public void onComportamientoFabClicked() {
        ComportamientoFragment comportamientoFragment = getFragment(ComportamientoFragment.class);
        if (comportamientoFragment != null) {
            comportamientoFragment.onParentFabClicked();
        }
    }

    @Override
    public void comprobarCPRubro(boolean editar, boolean anclar) {

    }

    @Override
    public void comprobrarCPTarea(boolean vigente) {

    }

    @Override
    public void comprobarCPSesiones(boolean editar, boolean vigente) {

    }

    @Override
    public void progressUpdateColor(int color) {

    }

    @Override
    public void progressUpdateSuccess() {
        if(menu!=null){
            MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
            if(menuItem!=null){
                menuItem.setIcon(R.drawable.ic_refresh2);
            }
        }
    }

    @Override
    public void progressUpdateError() {
        if(menu!=null){
            MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
            if(menuItem!=null){
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_problema_de_sincronizacion);
                if(drawable != null) {
                    drawable.mutate();
                    drawable.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_IN);
                }
                menuItem.setIcon(drawable);
            }
        }
    }

    @Override
    public void onResumenFragmentUnidades(int calendarioPeriodoId, boolean status) {
        FragmentUnidades fragmentUnidades = getFragment(FragmentUnidades.class);
        if (fragmentUnidades == null) return;
        fragmentUnidades.actualizarUnidadesPeriodo(String.valueOf(calendarioPeriodoId), status);
    }

    @Override
    public void onActualizarFragmentRegistro(final int calendarioPeriodoId, boolean status) {
//        RubroResultadoSilaboFragment rubroResultadoSilaboFragment = getFragment(RubroResultadoSilaboFragment.class);
//        if (rubroResultadoSilaboFragment == null) return;
//        rubroResultadoSilaboFragment.onActualizarFragment(calendarioPeriodoId, status);

    }

    @Override
    public void showEvaluacionCompetenciaDialogFilter() {
        Log.d(TAG, " showEvaluacionCompetenciaDialogFilter ");
        EvaluacionCompetenciasFragment evaluacionCompetenciasFragment = getFragment(EvaluacionCompetenciasFragment.class);
        if (evaluacionCompetenciasFragment == null) return;
        evaluacionCompetenciasFragment.showFiltro();
    }

    @Override
    public void hideFab() {
        if (fab != null) fab.hide();
    }

    @Override
    public void hideFabGrupos() {
        if (fabgrupo != null)fabgrupo.hide();
    }

    @Override
    public void showFabGrupos() {
        if (fabgrupo != null)fabgrupo.show();
    }

    @Override
    public void changeFabBg(@DrawableRes final int drawable) {
        if (fab != null){
            fab.hide();
            fab.setImageResource(drawable);
            fab.show();
        }

    }

    @Override
    public void showFragments(int idCargaCurso, int idCurso, int idProgramaEducativo, int parametrodisenioid, int calendarioPerioId, int idCargaAcademica, boolean calendarioVignete, boolean calendarioEditar) {
        Log.d(TAG, "showFragments");
         Log.d(TAG, "setupAdapterViewPager calendarioPerioId: " + calendarioPerioId);
        CRMBundle crmBundle = new CRMBundle(getIntent().getExtras());
        crmBundle.setCargaCursoId(idCargaCurso);
        crmBundle.setCursoId(idCurso);
        crmBundle.setProgramaEducativoId(idProgramaEducativo);
        crmBundle.setCargaAcademicaId(idCargaAcademica);
        crmBundle.setCalendarioPeriodoId(calendarioPerioId);
        crmBundle.setParametroDisenioId(parametrodisenioid);
        crmBundle.setCalendarioActivo(calendarioVignete);
        crmBundle.setCalendarioEditar(calendarioEditar);
        Bundle args = crmBundle.instanceBundle();
        args.putInt("idCargaCurso", idCargaCurso);
        args.putInt("cursoId", idCurso);
        args.putInt("idProgramaEducativo", idProgramaEducativo);
        args.putInt("parametrodisenioid", parametrodisenioid);
        args.putInt("idCalendarioPeriodo", calendarioPerioId);
        args.putInt("idEntidad", crmBundle.getEntidadId());

        Log.d(TAG,args+"");

        if (fragmentAdapter == null) {
            Log.d(TAG,"idCalendarioPeriodo fragmentAdapter null");
            fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
            fragmentAdapter.addFragment(RubricaBidFragment.newInstance(args), "RÚBRICA");
            fragmentAdapter.addFragment(EvaluacionCompetenciasFragment.newInstance(args), "RESULTADO");
            fragmentAdapter.addFragment(RubroResultadoSilaboFragment.newInstance(args), "REGISTRO");
            fragmentAdapter.addFragment(FragmentUnidades.newInstance(args), "SESIONES");
            fragmentAdapter.addFragment(ListaGrupoFragment.newInstance(args,idCargaCurso, idCurso, idCargaAcademica, idProgramaEducativo, crmBundle.getEntidadId(), crmBundle.getGeoreferenciaId()), "GRUPOS");
            fragmentAdapter.addFragment(FragmentTareas.newInstance(args,idCargaCurso, idCurso, parametrodisenioid, crmBundle, calendarioPerioId), "TAREAS");
            fragmentAdapter.addFragment(ComportamientoFragment.newInstance(crmBundle), "CASOS");
            fragmentAdapter.addFragment(AsistenciaCursoFragment.newInstance(crmBundle), "ASISTENCIA");
            //fragmentAdapter.addFragment(TabsBandejaFragment.newInstance(idCargaCurso), "Mensajes");
            vpCurso.setOffscreenPageLimit(7);
            tabCurso.setupWithViewPager(vpCurso);
            vpCurso.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    if (presenter != null)
                        presenter.onPageChanged(fragmentAdapter.getItem(position) != null ? fragmentAdapter.getItem(position).getClass() : null);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                boolean isShow = false;
                int scrollRange = -1;

                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (scrollRange + verticalOffset == 0) {
                        isShow = true;

                        hideOption(R.id.menu_refrescar);

                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tabCurso.getLayoutParams();
                        params.bottomMargin = 0;
                        params.topMargin = (int)Utils.convertDpToPixel(8f, getApplicationContext()) ;

                        ViewGroup.MarginLayoutParams paramsContent = (ViewGroup.MarginLayoutParams) content.getLayoutParams();
                        paramsContent.topMargin = 4;
                        if(getSupportActionBar()!=null)getSupportActionBar().setDisplayHomeAsUpEnabled(false);


                    } else if (isShow) {
                        isShow = false;
                        if (toolbarprogress.getVisibility() == View.GONE) {
                            MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
                            menuItem.setVisible(true);
                        }


                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tabCurso.getLayoutParams();
                        params.bottomMargin = (int)Utils.convertDpToPixel(20f, getApplicationContext()) ;
                        params.topMargin = 0;

                        ViewGroup.MarginLayoutParams paramsContent = (ViewGroup.MarginLayoutParams) content.getLayoutParams();
                        paramsContent.topMargin = (int)Utils.convertDpToPixel(-16f, getApplicationContext()) ;
                        if(getSupportActionBar()!=null)getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                    }
                }
            });
            vpCurso.setSaveFromParentEnabled(false);
            root.postDelayed(new Runnable() {
                @Override
                public void run() {
                    vpCurso.setAdapter(fragmentAdapter);
                }
            }, 100);

        }
    }


    @Override
    public void showTitle(String title) {
        txtNombreCurso.setText(title);
    }

    @Override
    public void showSubtitle(String subtitle) {
        txtPeriodoSeccion.setText(subtitle);
    }

    @Override
    public void showAppbarBackground(String bg, String bgColor) {
        imageLoader.load(imgBackgroundAppbar, bg);
        try {
            appBar.setBackgroundColor(Color.parseColor(bgColor));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRubricaBidFragmentFabClicked() {
        Log.d(TAG, "onRubricaBidFragmentFabClicked");
        RubricaBidFragment rubricaBidFragment = getFragment(RubricaBidFragment.class);
        if (rubricaBidFragment != null) {
            rubricaBidFragment.onParentFabClicked();
        }
    }

    @Override
    public void onGrupoFragmentFabClicked(boolean isAprendizajeColegio) {
        Log.d(TAG, "onGrupoFragmentFabClicked");
        ListaGrupoFragment groupListFragment = getFragment(ListaGrupoFragment.class);
        if (groupListFragment == null) return;
            groupListFragment.onParentFabClickedCrearGrupo(isAprendizajeColegio);
    }


    @Override
    public void onAsistenciaFragmentFabClicked() {
        Log.d(TAG, "onAsistenciaFragmentFabClicked");
    }

    @Override
    public void onTareaFragmentFabClicked() {
        Log.d(TAG, "onTareaFragmentFabClicked");
    }

    @Override
    public void onEvaluacionFragmentFabClicked(Bundle bundle) {

    }

    @Override
    public void showRubroResultadoSilaboDialogFilter(ArrayList<CompetenciaCheck> tipoCompetencia) {
        FilterChooserBottomSheetDialog fragment = FilterChooserBottomSheetDialog.newInstance(tipoCompetencia);
        fragment.show(getSupportFragmentManager(), fragment.getTag());


    }

    @Override
    public void onActualizarEvaluacionFrament() {
        EvaluacionCompetenciasFragment evaluacionCompetenciasFragment = getFragment(EvaluacionCompetenciasFragment.class);
        if (evaluacionCompetenciasFragment != null) {
            evaluacionCompetenciasFragment.onActualizarResultado();
        }
    }

    @Override
    public void onActualizarRegistroFrament() {
        Log.d(TAG, "onActualizarRegistroFrament");
        RubroResultadoSilaboFragment rubroResultadoSilaboFragment = getFragment(RubroResultadoSilaboFragment.class);
        if (rubroResultadoSilaboFragment != null) {
            rubroResultadoSilaboFragment.onActualizarRegitroSilabo();
        }
    }

    @Override
    public void onActualizarRubricaFragment(Intent intent) {
        RubricaBidFragment rubricaBidFragment = getFragment(RubricaBidFragment.class);
        if (rubricaBidFragment != null) {
            rubricaBidFragment.onActualizarRubricaFragment(intent);
        }
    }

    @Override
    public void filtrarRubroCompetencia() {
        RubroResultadoSilaboFragment rubroResultadoSilaboFragment = getFragment(RubroResultadoSilaboFragment.class);
        if (rubroResultadoSilaboFragment != null) {
            rubroResultadoSilaboFragment.filtrarRubroCompetencia();
        }
    }

    @Override
    public void filtrarRubroEnfoqueTrans() {
        RubroResultadoSilaboFragment rubroResultadoSilaboFragment = getFragment(RubroResultadoSilaboFragment.class);
        if (rubroResultadoSilaboFragment != null) {
            rubroResultadoSilaboFragment.filtrarRubroEnfoqueTrans();
        }
    }


    private <T extends Fragment> T getFragment(Class<T> tClass) {
        List<Fragment> fragments = getFragments();
        for (Fragment fragment :
                fragments) {
            if (tClass.isInstance(fragment)) {
                return (T) fragment;
            }
        }
        return null;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (resultCode == RESULT_OK && resultCode == SELECT_RUBRIC_FRAGMENT_AGREGAR) {
            Log.d(TAG, "onActivityResult: ");
        }*/
        //  if (resultCode == RESULT_OK) {
        presenter.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult:2 ");

        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult requestCode: " + requestCode + ", resultCode: " + resultCode);
        if (requestCode == UPDATE_RUBRICA && resultCode == AppCompatActivity.RESULT_OK) {
            presenter.onChangeRubroUpdate();
        }else  if (requestCode == CHANGE_DATABASE_TABSESION && resultCode == AppCompatActivity.RESULT_OK) {
            presenter.onChangeDatabseDesdeTabSesion();
        }else  if (requestCode == CHANGE_DATABASE_SERVICE2 && resultCode == AppCompatActivity.RESULT_OK) {
            presenter.onChangeDatabseDesdeService2();
        }

    }


    private List<Fragment> getFragments() {
        return getSupportFragmentManager().getFragments();
    }

    @Override
    public void onBtnSuccesItemFilter(FiltradoUi filtradoUi) {

    }

    @Override
    public void actualizarRubroTipoAncla() {
        //Log.d(TAG, "actualizarRubroTipoAncla : " + rubroProcesoUi.getTitulo());
        presenter.actualizarRubroTipoAnclaFragmentRegistro();
    }


    @Override
    public void succesDelete() {
        presenter.delteRubrica();
    }

    @Override
    protected void onDestroy() {
        Core2.getCore2(this).removeCore2Listener(this);
        super.onDestroy();
        crmeNotification.onDestroy();
        menu = null;
    }

    @Override
    public void onBtnSuccesItemFilter(ArrayList<CompetenciaCheck> tipoCompetencia) {

        presenter.onBtnSuccesItemFilterRubro(tipoCompetencia);
    }

    @Override
    public void onReceive(TipoImportacion tipoImportacion, boolean success, BEVariables beVariables) {
        presenter.onChange(tipoImportacion, success);
    }

    @Override
    public void onPeriodoSelected(PeriodoUi periodoUi) {
        presenter.onPeriodoSelected(periodoUi);
    }

    @Override
    public void showPeriodoList(List<PeriodoUi> periodoList, String color) {
        periodoAdapter.setColor(color);
        periodoAdapter.setPeriodoList(periodoList);
    }

    @Override
    public void changePeriodo() {
        periodoAdapter.notifyDataSetChanged();
        recyclerPeriodo.post(new Runnable() {
            @Override
            public void run() {
                if(presenter!=null)presenter.PostDelaychangePeriodo();
            }
        });
    }

    @Override
    public void showMsgCalendarioPeriodo() {
        msgCalendarioPeriodo.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMsgCalendarioPeriodo() {
        msgCalendarioPeriodo.setVisibility(View.GONE);
    }

    @Override
    public void hidePeriodosLIst() {
        ViewGroup.LayoutParams params = recyclerPeriodo.getLayoutParams();
        params.width = 0;
        recyclerPeriodo.setLayoutParams(params);
    }

    @Override
    public void showPeriodosLIst() {
        recyclerPeriodo.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams params = recyclerPeriodo.getLayoutParams();
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        recyclerPeriodo.setLayoutParams(params);
    }


    @Override
    public void onAceptarBottomDialog(Object o) {
        presenter.onAceptarBottomDialog(o);
    }

    @Override
    public void onCancelarBottomDialog() {

    }

    @Override
    public void onStart(Class<?> aClass) {
        if (aClass.equals(getClass())) {
            if (toolbarprogress != null) toolbarprogress.setVisibility(View.GONE);
            if (menu != null) {
                MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
                menuItem.setVisible(true);

                MenuItem menuItem2 = menu.findItem(R.id.menu_update_alumnos);
                menuItem2.setEnabled(true);
            }

        }
    }

    @Override
    public void onLoad(int count) {
        try {
            if (toolbarprogress.getVisibility() == View.GONE) {
                toolbarprogress.setVisibility(View.VISIBLE);
                MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
                menuItem.setVisible(false);

                MenuItem menuItem2 = menu.findItem(R.id.menu_update_alumnos);
                menuItem2.setEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFinish() {
        try {
            if (toolbarprogress.getVisibility() == View.VISIBLE) {
                toolbarprogress.setVisibility(View.GONE);
                MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
                menuItem.setVisible(true);

                MenuItem menuItem2 = menu.findItem(R.id.menu_update_alumnos);
                menuItem2.setEnabled(true);

                if(dialogUpdateAlumnos!=null&&!dialogUpdateAlumnos.isRemoving()){
                    dialogUpdateAlumnos.dismiss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        presenter.onChangeFull(true);
        presenter.onFinishSynck();

    }

    @Override
    public void onFinishSimple() {
        try {
            if (toolbarprogress.getVisibility() == View.VISIBLE) {
                toolbarprogress.setVisibility(View.GONE);
                //
                MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
                menuItem.setVisible(true);

                MenuItem menuItem2 = menu.findItem(R.id.menu_update_alumnos);
                menuItem2.setEnabled(true);

                if(dialogUpdateAlumnos!=null&&!dialogUpdateAlumnos.isRemoving()){
                    dialogUpdateAlumnos.dismiss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        presenter.onFinishSynck();
    }

    @Override
    public void contraerToolbar() {
        appBar.setExpanded(false, true);

    }

    @Override
    public void changeColorToolbar(String color1) {
        try {
            ctlTabcursos.setContentScrimColor(Color.parseColor(color1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeColorFloatButon(String color2) {
        try {
            fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color2)));
            fabgrupo.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color2)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onDeleteRubro() {
        presenter.onDeleteRubro();
    }

    public static void enableDisableView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if ( view instanceof ViewGroup ) {
            ViewGroup group = (ViewGroup)view;

            for ( int idx = 0 ; idx < group.getChildCount() ; idx++ ) {
                enableDisableView(group.getChildAt(idx), enabled);
            }
        }
    }

    @Override
    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if(connectivityManager!=null)activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void showMessageNotConnection() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(com.consultoraestrategia.ss_crmeducativo.core2.R.layout.dialog_not_connection);
        dialog.show();

    }



    @Override
    public void showProgressDialogUpdateAlumnos() {
        dialogUpdateAlumnos = new DialogUpdateAlumnos();
        dialogUpdateAlumnos.show(getSupportFragmentManager(), DialogUpdateAlumnos.class.getSimpleName());

    }

    @Override
    public void showSincContratoAlumnos(int silaboId, int grupoAcademicoId, int perdiodoAcademicoId, int calendarioPeriodoId, int idProgramaEducativo) {
        ContratoAlumnoSyncIntenService.start(this, silaboId,grupoAcademicoId, perdiodoAcademicoId, calendarioPeriodoId, idProgramaEducativo );
        CMRE.saveNotifyChangeDataBase(this);
    }

    @Override
    public void showItemMenuUpdateAlumno() {
        if(menu!=null){
            MenuItem menuItem2 = menu.findItem(R.id.menu_update_alumnos);
            menuItem2.setVisible(true);
        }
    }

    @Override
    public void hideItemMenuUpdateAlumno() {
        if(menu!=null){
            MenuItem menuItem2 = menu.findItem(R.id.menu_update_alumnos);
            menuItem2.setVisible(false);
        }

    }

    @Override
    public void showCambiarFotoAlumnoActivity(int idCargaCurso) {
        Intent intent = new Intent(this, CambiarFotoAlumnoActivity.class);
        CRMBundle crmBundle = new CRMBundle(getIntent().getExtras());
        crmBundle.setCargaCursoId(idCargaCurso);
        intent.putExtras(crmBundle.instanceBundle());
        startActivity(intent);
    }

    @Override
    public void showItemMenuUpdateFotoAlumno() {
        if(menu!=null){
            MenuItem menuItem3 = menu.findItem(R.id.menu_update_foto_alumnos);
            menuItem3.setVisible(true);
        }
    }

    @Override
    public void hideItemMenuUpdateFotoAlumno() {
        if(menu!=null){
            MenuItem menuItem3 = menu.findItem(R.id.menu_update_foto_alumnos);
            menuItem3.setVisible(false);
        }
    }

    @Override
    public void showAutoHideProgress() {

    }

    @Override
    public void onSimpleSinckService(final int programaEducativoId) {
        root.postDelayed(new Runnable() {
            @Override
            public void run() {
               SimpleSyncIntenService.start(getApplicationContext(), programaEducativoId);
            }
        },1000);
    }

    @Override
    public void showMessage(CharSequence error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void changeBundleCalendarioId(int idCalendarioPeriodo) {
        CRMBundle crmBundle = new CRMBundle(getIntent().getExtras());
        crmBundle.setCalendarioPeriodoId(idCalendarioPeriodo);
        getIntent().putExtras(crmBundle.instanceBundle());
    }

    @Override
    public boolean ischangeDataBase() {
        return CMRE.isChangeDataBase(getBaseContext());
    }

    @Override
    public void successchangeDataBase() {
        CMRE.succesNotifyChangeDataBase(getBaseContext());
    }

    @Override
    public void changeOrientationUnidad() {
        FragmentUnidades fragmentUnidades = getFragment(FragmentUnidades.class);
        if(fragmentUnidades!=null)fragmentUnidades.changeOrientationUnidad();
    }

    @Override
    public void showActivityService2(int usuarioId, int empleadoId, int idProgramaEducativo, int idCargaCurso, int idCalendarioPeriodo, int idGeoreferenciaId, int idEntidad, int silaboId, int idCurso, int idCargaAcademica, int anioAcademicoId, boolean cursoComplejo) {
        Intent intent = ServicesActivity.start(this, usuarioId, empleadoId, idProgramaEducativo, idCargaCurso, idCalendarioPeriodo, idGeoreferenciaId, idEntidad,silaboId, idCurso,idCargaAcademica, anioAcademicoId, cursoComplejo);
        startActivityForResult(intent, TabsCursoDocenteActivity.CHANGE_DATABASE_SERVICE2);
    }

    @Override
    public void showProgressService2(int usuarioId, int empleadoId, int idProgramaEducativo, int idCargaCurso, int idCalendarioPeriodo, int idGeoreferenciaId, int idEntidad, int silaboId, int idCurso, int idCargaAcademica, boolean cursoComplejo) {
        progressDialogFragment = ProgressDialogFragment.newInstance(usuarioId, empleadoId, idProgramaEducativo, idCargaCurso, idCalendarioPeriodo, idGeoreferenciaId, idEntidad,silaboId, idCurso,idCargaAcademica, cursoComplejo);
        progressDialogFragment.show(getSupportFragmentManager(), "ProgressDialogFragment");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        presenter.onConfigurationChanged();
    }

    public void reloadActivity() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        intent.putExtra(RELOAD_X_CERRAR_CURSO, true);
        startActivity(intent);
    }

    @Override
    public void finshedDialog() {
        presenter.onChangeFull(true);
    }

    @Override
    public void finshedDialogWithError() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(R.string.msg_confirmacionTitlle);
        builder.setMessage("¿No se logró actualizar el curso por completo?");
        builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Log.d(TAG, "Presionos ACEPTAR");
                presenter.onClickAceptarRevizarErroresActualizar();
                dialogInterface.cancel();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        //Create AdapterExample
        builder.create().show();
    }

    @Override
    public void showFinalMessageAceptCancel(final CharSequence message, final CharSequence messageTittle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(messageTittle)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton(R.string.global_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setPositiveButton(R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.onCLickAcceptButtom();
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}



