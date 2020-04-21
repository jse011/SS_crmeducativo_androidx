package com.consultoraestrategia.ss_crmeducativo.tabsSesiones.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.DrawableRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.listener.FilterChooserSuccessListener;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.view.FilterChooserBottomSheetDialog;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.actividades.ui.ActividadesFragment;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.ui.FragmentAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comentarios.ui.ComentariosFragment;
import com.consultoraestrategia.ss_crmeducativo.core2.application.Core2;
import com.consultoraestrategia.ss_crmeducativo.core2.listener.Core2Listener;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.listener.CrearRubroListaIndicadoresListener;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.ui.ListaIndicadoresFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.CreateRubBidActivity;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.MyFragmentAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.listener.RefrescarListener;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.RubricaSesionFragment;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.sesion.view.FragmentRubroEvalProcesoLista;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.listener.RubroEvaluacionCompetenciasListaListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroProcesoListaListener;
import com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification.CrmeNotificationServiceImpl;
import com.consultoraestrategia.ss_crmeducativo.services.data.local.ServiceLocalDataRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.util.RepositoryInjector;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ui.ImportarActivity;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.ComplejoSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.validacion.GetFechaCreacionSesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.situacion.ui.SituacionFragment;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.NewFragment;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.TabsSesionPresenter;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.TabsSesionPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.TabsSesionView;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.adapters.PagerSesionesAdapter;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.data.source.TabsSesionesRepository;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.data.source.local.TabsSesionesLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.data.source.remote.TabsSesionesRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.domain.UseCase.ChangeDataBaseDocenteMentor;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.domain.UseCase.GetDatosEsenciales;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.ui.FragmentTareasSesiones;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Intent.ACTION_SEARCH;

public class TabsSesionesActivityV2 extends AppCompatActivity implements TabsSesionView, CrearRubroListaIndicadoresListener, RubroEvaluacionCompetenciasListaListener, ViewPager.OnPageChangeListener, RefrescarListener, FilterChooserSuccessListener, CrmeNotificationServiceImpl.EventNotificacion, Core2Listener, RubroProcesoListaListener {


    private static final String TAG = TabsSesionesActivityV2.class.getSimpleName();
    public static final String INT_SESIONAPRENDIZAJEID = "sesionesAprendizajeId";
    public static final String INT_SESIONAPRENDIZAJE_TITULO = "sesionesAprendizaje.Titulo";
    public static final String INT_SESIONAPRENDIZAJE_NUMERO = "sesionesAprendizaje.Numero";
    public static final String INT_SESIONAPRENDIZAJE_UI = "sesionesAprendizaje.Ui";
    public static final String INT_PERSONAID = "personasId";
    public static final String INT_BACKGROUND = "Backgranund";
    public static final String INT_CARGACURSO_ID = "cargaCursoId";
    public static final String INT_CURSO_ID = "cursoId";
    public static final String INT_NIVEL = "nivelId";
    public static final String INT_SILAVOEVENTO_ID = "silavoEventoId";
    public static final String INT_CALENDARIOPERIODO_ID = "calendarioPeriodoId";
    public static final String INT_RUBROEVALUACIONRESULTADO_ID = "rubroEvaluacionResultadoId";
    public static final String LIST_COMPETENCIAS = "competenciasId";
    public static final String INT_RUBROEVALUACION_ID = "rubroEvaluacionAprendizajeId";
    public static final String INT_PROGRAMA_EDUCATIVO_ID = "programaEducativoId";
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    private static final int UPDATE_RUBRICA = 14563;
    public static final String PARAMETRO_DISENIO_ID = "parametroDisenioId";
    public static final String INT_CARGA_ACADEMICA_ID = "idCargaAcademica";


    TabsSesionPresenter presenter;
    PagerSesionesAdapter adapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_curso)
    TabLayout tabCurso;
    @BindView(R.id.vp_Curso)
    ViewPager vpCurso;
    @BindView(R.id.txtNombreSesion)
    TextView txtNombreSesion;
    @BindView(R.id.txtDetalleSesion)
    TextView txtDetalleSesion;
    @BindView(R.id.ctl_tabcursos)
    CollapsingToolbarLayout ctlTabcursos;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.msg_actualizar)
    TextView msgActualizar;
    @BindView(R.id.conten_comentario)
    FrameLayout contentComentario;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.toolbarprogress)
    ProgressBar toolbarprogress;
    @BindView(R.id.constraintLayoutSessiones)
    ConstraintLayout constraintLayoutSessiones;
    @BindView(R.id.msg_calendario_periodo)
    TextView msgCalendarioPeriodo;
    @BindView((R.id.imgBackgroundAppbar))
    ImageView imgBackgroundAppbar;


    private ListaIndicadoresFragment listaIndicadoresFragment;
    private MyFragmentAdapter fragmentAdapter;
    private Menu menu;
    private CrmeNotificationServiceImpl crmeNotification;


    public static Intent createTabSesionesIntent(Context context, Class<?> cls, Bundle bundle, SesionAprendizajeUi SesionAprendizajeId, int personaId, int backgroudColor, int CargaCursoId, int CursoId, int cargaAcademicaId, int parametroDisenioId, int entidadId, int georeferenciaId) {
        Intent intent = new Intent(ACTION_SEARCH, null, context, cls);
        intent.putExtra(TabsSesionesActivityV2.INT_SESIONAPRENDIZAJEID, SesionAprendizajeId.getSesionAprendizajeId());
        intent.putExtra(TabsSesionesActivityV2.INT_SESIONAPRENDIZAJE_TITULO, SesionAprendizajeId.getTitulo());
        intent.putExtra(TabsSesionesActivityV2.INT_SESIONAPRENDIZAJE_NUMERO, SesionAprendizajeId.getNroSesion());
        intent.putExtra(TabsSesionesActivityV2.INT_SESIONAPRENDIZAJE_UI, SesionAprendizajeId);
        intent.putExtra(TabsSesionesActivityV2.INT_PERSONAID, personaId);
        intent.putExtra(TabsSesionesActivityV2.INT_BACKGROUND, backgroudColor);
        intent.putExtra(TabsSesionesActivityV2.INT_CARGACURSO_ID, CargaCursoId);
        intent.putExtra(TabsSesionesActivityV2.INT_CURSO_ID, CursoId);
        intent.putExtra(TabsSesionesActivityV2.INT_CARGA_ACADEMICA_ID, cargaAcademicaId);
        intent.putExtra(TabsSesionesActivityV2.PARAMETRO_DISENIO_ID, parametroDisenioId);
        CRMBundle crmBundle = new CRMBundle(bundle);
        crmBundle.setSesionAprendizajeId(SesionAprendizajeId.getSesionAprendizajeId());
        crmBundle.setPersonaId(personaId);
        crmBundle.setCargaCursoId(CargaCursoId);
        crmBundle.setCursoId(CursoId);
        crmBundle.setCargaAcademicaId(cargaAcademicaId);
        crmBundle.setEntidadId(entidadId);
        crmBundle.setGeoreferenciaId(georeferenciaId);
        intent.putExtras(crmBundle.instanceBundle());
        Log.d(TAG, " createTabSesionesIntent: ");
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_sesiones);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupToolbar();
        setupPresenter();
        setupDataBaseRealTime();
        Core2.getCore2(this).addCore2Listener(this);
    }

    private void setupPresenter() {
        presenter = new TabsSesionPresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new GetDatosEsenciales(TabsSesionesRepository.getInstance(
                        new TabsSesionesLocalDataSource(),
                        new TabsSesionesRemoteDataSource()
                )),
                new GetFechaCreacionSesionAprendizaje(RepositoryInjector.getBEDatosSesionAprendizajeRepository()),
                new ChangeDataBaseDocenteMentor(new ServiceLocalDataRepositoryImpl(InjectorUtils.provideSessionUserDao())));
        setPresenter(presenter);
    }

    private void setupDataBaseRealTime() {
        crmeNotification = CrmeNotificationServiceImpl.init(this);
        crmeNotification.setEventNotificacion(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        Core2.getCore2(this).removeCore2Listener(this);
        super.onDestroy();
        presenter.onDestroy();
        crmeNotification.onDestroy();
        menu = null;
    }

    @Override
    public void setPresenter(TabsSesionPresenter presenter) {
        presenter.attachView(this);
        presenter.setExtra(getIntent().getExtras());
        presenter.onCreate();
    }


    public void setupToolbar() {
        Bundle bundle = getIntent().getExtras();
        int nroSesion = bundle.getInt(TabsSesionesActivityV2.INT_SESIONAPRENDIZAJE_NUMERO, 0);
        String titulo = bundle.getString(TabsSesionesActivityV2.INT_SESIONAPRENDIZAJE_TITULO, "-");
        txtNombreSesion.setText(Utils.limpiarAcentos("Sesión " + nroSesion));
        txtDetalleSesion.setText(Utils.limpiarAcentos(titulo));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Glide.with(this)
                .load(R.drawable.sesion_portada)
                .apply(new RequestOptions().centerCrop())
                .into(imgBackgroundAppbar);

        try {
            Typeface mFont = Typeface.createFromAsset(getBaseContext().getAssets(), "fonts/coloredcrayons.ttf");
            txtNombreSesion.setTypeface(mFont);
            txtDetalleSesion.setTypeface(mFont);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                appBar.setTransitionName("view");
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.teal));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*Drawable drawable = ContextCompat.getDrawable(getBaseContext(), R.drawable.sesion_portada_default);
        ctlTabcursos.setBackground(drawable);*/
    }


    private List<Fragment> getFragments() {
        return getSupportFragmentManager().getFragments();
    }

    @Override
    public void onSelectIndicadorCampotematicovoid(int competenciaId, int indicadorId, ArrayList<Integer> campotematicoIds) {
        presenter.onSelectIndicadorCampotematicovoid(indicadorId, campotematicoIds);
    }

    @Override
    public void onSalirListaIndicadores() {
        presenter.onSalirListaIndicadores();
    }

    @OnClick({R.id.fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Log.d(TAG, "Bidimencional");
                presenter.onClickFab();
                break;
        }
    }


    @Override
    public void posicionarTab(int posicion) {

    }

    @Override
    public void showListaIndicadores(int sesionAprendizajeId, int rubroEvaluacionResultadoId, int nivel) {
        FragmentManager manager = getSupportFragmentManager();
        listaIndicadoresFragment = ListaIndicadoresFragment.newInstance(ListaIndicadoresFragment.Id.SESION_APRENDIZAJE_ID, sesionAprendizajeId, nivel, 2, 0);
        listaIndicadoresFragment.show(manager, "ListaIndicadoresFragment");
    }


    @Override
    public void hideListaIndicadores() {
        listaIndicadoresFragment.dismiss();
    }

    @Override
    public void showCrearRubro(int sesionAprendizajeId, int indicadorId, ArrayList<Integer> campotematicoIds) {

    }


    @Override
    public void showfloatButon() {

    }

    @Override
    public void hidefloatButon() {
    }

    @Override
    public void updateFragmentoEvaluacion() {
        /*Fragment registroFragment = getRegistroFragment();
        if (registroFragment == null) return;
        FragmentLifecycle fragmentToShow = (FragmentLifecycle) registroFragment;
        fragmentToShow.onResumeFragment();*/
    }

    @Override
    public void updateFragmentosRubro(ArrayList<Integer> competenciasId) {
        Log.d(TAG, "updatefragmentosRubro" + competenciasId.size());
       /* Bundle bundle = new Bundle();
        bundle.putInt(FragmentRubroEvalProcesoLista.TIPO, FragmentRubroEvalProcesoLista.TIPO_UPDATE_COMPETENCIA);
        bundle.putIntegerArrayList(FragmentRubroEvalProcesoLista.LIST_COMPETENCIAS, competenciasId);
        Fragment rubroFragment = getRubroFragment();
        if (rubroFragment == null) return;
        FragmentLifecycle fragmentToShow = (FragmentLifecycle) rubroFragment;
        fragmentToShow.onResumeFragment(bundle);*/
    }

    @Override
    public void startCreateRubBid(int calendarioPeriodoId, int silaboEventoId, int rubroEvalResultadoId, int programaEducativoId) {

        CRMBundle crmBundle = new CRMBundle(getIntent().getExtras());
        crmBundle.setCalendarioPeriodoId(calendarioPeriodoId);
        crmBundle.setSilaboEventoId(silaboEventoId);
        crmBundle.setRubroEvalResultadoId(rubroEvalResultadoId);
        crmBundle.setProgramaEducativoId(programaEducativoId);
        Intent intent = CreateRubBidActivity.getStartCreateRubBidActivity(this, crmBundle);
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
        // CreateRubBidActivity.startActivity(this, args);
    }

    @Override
    public void setupAdapter(final int nivel, final int calendarioPerioId, final int silavoEventoId, final int rubroEvaluacionResultadoId, final int programaEducativoId, final int sesionAprendizajeId, int parametroDisenioId, int cargaAcademicaId, int cursoId, int cargaCursoId, SesionAprendizajeUi sesionAprendizajeUi, int personaId, int backgroudColor, int entidadId, int georeferenciaId) {
        final int idCargaCurso = getIntent().getIntExtra(INT_CARGACURSO_ID, 0);
        final Bundle bundle = getIntent().getExtras();
        bundle.putInt(TabsSesionesActivityV2.INT_NIVEL, nivel);
        bundle.putInt(TabsSesionesActivityV2.INT_CALENDARIOPERIODO_ID, calendarioPerioId);
        bundle.putInt(TabsSesionesActivityV2.INT_SILAVOEVENTO_ID, silavoEventoId);
        bundle.putInt(TabsSesionesActivityV2.INT_RUBROEVALUACIONRESULTADO_ID, rubroEvaluacionResultadoId);
        bundle.putInt(TabsSesionesActivityV2.INT_PROGRAMA_EDUCATIVO_ID, programaEducativoId);
        bundle.putInt(TabsSesionesActivityV2.INT_CARGACURSO_ID, idCargaCurso);
        final int undidadPrendizajeId = getIntent().getIntExtra(INT_SESIONAPRENDIZAJEID, 0);
        final int idCurso = getIntent().getIntExtra(INT_CURSO_ID, 0);


        Log.d(TAG, "unidadAprendizaje" + undidadPrendizajeId + "sessionAprendizaje" + sesionAprendizajeId);

        fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(FragmentAprendizaje.newInstance(bundle), "APRENDIZAJE");
        fragmentAdapter.addFragment(ActividadesFragment.newInstance(bundle), "ACTIVIDADES");
        fragmentAdapter.addFragment(RubricaSesionFragment.newInstance(bundle), "RÚBRICA");
        fragmentAdapter.addFragment(FragmentRubroEvalProcesoLista.newInstance(bundle), "REGISTRO");
        fragmentAdapter.addFragment(FragmentTareasSesiones.newInstanceSesion(bundle, sesionAprendizajeId, idCurso, idCargaCurso, parametroDisenioId, calendarioPerioId), "Tareas");
        fragmentAdapter.addFragment(SituacionFragment.newInstance(undidadPrendizajeId, idCargaCurso, entidadId), "SITUACIÓN");
        //fragmentAdapter.addFragment(NewFragment.newInstance(), "COMENTARIOS");
        vpCurso.setOffscreenPageLimit(6);
        vpCurso.setAdapter(fragmentAdapter);
        tabCurso.setupWithViewPager(vpCurso);
        vpCurso.addOnPageChangeListener(this);


        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(TAG, "onOffsetChanged: " + verticalOffset);
                if (scrollRange == -1) {
                    Log.d(TAG, "scrollRange: " + scrollRange);
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tabCurso.getLayoutParams();
                    params.bottomMargin = 0;
                    params.topMargin = (int) Utils.convertDpToPixel(8f, getApplicationContext());

                    ViewGroup.MarginLayoutParams paramsContent = (ViewGroup.MarginLayoutParams) vpCurso.getLayoutParams();
                    paramsContent.topMargin = 4;

                } else if (isShow) {
                    isShow = false;
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tabCurso.getLayoutParams();
                    params.bottomMargin = (int) Utils.convertDpToPixel(20f, getApplicationContext());
                    params.topMargin = 0;

                    ViewGroup.MarginLayoutParams paramsContent = (ViewGroup.MarginLayoutParams) vpCurso.getLayoutParams();
                    paramsContent.topMargin = (int) Utils.convertDpToPixel(-16f, getApplicationContext());
                }
                Log.d(TAG, "scrollRange: " + scrollRange);
                Log.d(TAG, "isShow: " + isShow);
            }
        });

    }

    private void setupTab(int idCurso, int idCargaCurso, int undidadPrendizajeId, int sesionAprendizajeId, Bundle bundle) {

    }


    @Override
    public void updateFragmentosRubro(int rubroEvaluacionId) {
        /*Bundle bundle = new Bundle();
        bundle.putInt(FragmentRubroEvalProcesoLista.TIPO, FragmentRubroEvalProcesoLista.TIPO_UPDATE_RUBRO_EVALUACION);
        bundle.putInt(FragmentRubroEvalProcesoLista.INT_RUBROEVALUACION_ID, rubroEvaluacionId);
        Fragment rubroFragment = getRubroFragment();
        if (rubroFragment == null) return;
        FragmentLifecycle fragmentToShow = (FragmentLifecycle) rubroFragment;
        fragmentToShow.onResumeFragment(bundle);*/
    }

    @Override
    public void addFragmentosRubro(int addruborEvaluacionId) {
        /*Bundle bundle = new Bundle();
        bundle.putInt(FragmentRubroEvalProcesoLista.TIPO, FragmentRubroEvalProcesoLista.TIPO_ADD_RUBRO_EVALUACION);
        bundle.putInt(FragmentRubroEvalProcesoLista.INT_RUBROEVALUACION_ID, addruborEvaluacionId);
        Fragment rubroFragment = getRubroFragment();
        if (rubroFragment == null) return;
        FragmentLifecycle fragmentToShow = (FragmentLifecycle) rubroFragment;
        fragmentToShow.onResumeFragment(bundle);*/
    }


    @Override
    public void changeFabBg(@DrawableRes int drawable) {
        if (fab != null) {
            fab.hide();
            fab.setImageResource(drawable);
            fab.show();
        }

    }

    @Override
    public void hideFab() {
        if (fab != null)
            fab.hide();
    }


    @Override
    public void showFiltroCamposTematicos(ArrayList<CompetenciaCheck> tipoCompetencia) {
        FilterChooserBottomSheetDialog fragment = FilterChooserBottomSheetDialog.newInstance(tipoCompetencia);
        fragment.show(getSupportFragmentManager(), fragment.getTag());
    }


    @Override
    public void onSussesRubroEvaluacionCompetenciasLista(ArrayList<Integer> competenciasId) {
        //Log.d(TAG, "competenciasId: " + competenciasId.size());
        presenter.onSussesRubroEvaluacionCompetenciasLista(competenciasId);
    }

    @Override
    public void onCancelRubroEvaluacionCompetenciasLista() {
        Log.d(TAG, "onCancelRubroEvaluacionCompetenciasLista");
    }

    @Override
    public void onDeleteRubro() {
        presenter.onDeleteRubro();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* if (data == null) return;
        Bundle bundle = data.getExtras();*/
        presenter.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult requestCode: " + requestCode + ", resultCode: " + resultCode);
        if (requestCode == UPDATE_RUBRICA && resultCode == AppCompatActivity.RESULT_OK) {
            presenter.onChangeRubroUpdate();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab_sesion, menu);
        presenter.onCreateOptionsMenu();
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
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (fragmentAdapter != null)
            presenter.onPageChanged(fragmentAdapter.getItem(position) != null ? fragmentAdapter.getItem(position).getClass() : null);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onActualizarItemRubrica() {
        FragmentRubroEvalProcesoLista rubroResultadoSilaboFragment = getFragment(FragmentRubroEvalProcesoLista.class);
        if (rubroResultadoSilaboFragment != null) {
            rubroResultadoSilaboFragment.onActualizarRegitroSesion();
        }
    }

    @Override
    public void onResumenFragmentAprendizaje() {
        FragmentAprendizaje fragmentAprendizaje = getFragment(FragmentAprendizaje.class);
        Log.d(TAG, "onResumenFragmentAprendizaje");
        if (fragmentAprendizaje != null) fragmentAprendizaje.onResumeFragment();
    }

    @Override
    public void onResumenFragmentActividades() {
        ActividadesFragment actividadesFragment = getFragment(ActividadesFragment.class);
        if (actividadesFragment != null) actividadesFragment.onResumeFragment();
    }

    @Override
    public void onResumenFragmentRubrica() {
        RubricaSesionFragment rubricaSesionFragment = getFragment(RubricaSesionFragment.class);
        CRMBundle crmBundle = new CRMBundle(getIntent().getExtras());
        if (rubricaSesionFragment != null)
            rubricaSesionFragment.onResumen(crmBundle.getCalendarioPeriodoId());
        changeFabBg(R.drawable.ic_add_white_24dp);
    }

    @Override
    public void onResumenFragmentRegistro() {
        FragmentRubroEvalProcesoLista rubroEvalProcesoLista = getFragment(FragmentRubroEvalProcesoLista.class);
        if (rubroEvalProcesoLista != null) rubroEvalProcesoLista.onResumeFragment();
        changeFabBg(R.drawable.ic_filter_list_white_24dp);
    }

    @Override
    public void onResumenTarea() {
        FragmentTareasSesiones fragmentTareasSesiones = getFragment(FragmentTareasSesiones.class);
        if (fragmentTareasSesiones != null) fragmentTareasSesiones.onResumeFragment();
        changeFabBg(R.drawable.ic_add_white_24dp);
    }

    @Override
    public void onResumenFragmentSituacion() {
        SituacionFragment situacionFragment = getFragment(SituacionFragment.class);
        if (situacionFragment != null) situacionFragment.onResumeFragment();
    }

    @Override
    public void showMsgActualizacion() {
        //msgActualizar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMsgActualizacion() {
        //msgActualizar.setVisibility(View.GONE);
    }

    @Override
    public void showActualizarSesionAprendizaje(int sesionAprendizajeId) {
        BEVariables beVariables = new BEVariables();
        beVariables.setSesionEventoId(sesionAprendizajeId);
        ImportarActivity.launchImportarActivity(this, TipoImportacion.SESIONES, beVariables);
    }

    @Override
    public void onRefrescarFragmentRegistro() {
        FragmentRubroEvalProcesoLista rubroEvalProcesoLista = getFragment(FragmentRubroEvalProcesoLista.class);
        if (rubroEvalProcesoLista != null) rubroEvalProcesoLista.onActualizarRegitroSesion();
    }

    @Override
    public void showActualizarRubrica(BEVariables beVariables) {
        Intent intent = ImportarActivity.launchImportarActivityIntent(this, TipoImportacion.RUBROEVALUACION_CALENDARIO, beVariables);
        startActivityForResult(intent, UPDATE_RUBRICA);
    }

    @Override
    public void showActualizarGrupos(BEVariables beVariables) {
        ImportarActivity.launchImportarActivity(this, TipoImportacion.GRUPOS, beVariables);
    }

    @Override
    public void showActualizarRubros(BEVariables beVariables) {
        Intent intent = ImportarActivity.launchImportarActivityIntent(this, TipoImportacion.RUBROEVALUACION_CALENDARIO, beVariables);
        startActivityForResult(intent, UPDATE_RUBRICA);
    }

    @Override
    public void onSesionCompetenciaFragmentFabClicked(ArrayList<CompetenciaCheck> tipoCompetencia) {
        FragmentRubroEvalProcesoLista fragmentRubroEvalProcesoLista = getFragment(FragmentRubroEvalProcesoLista.class);
        if (fragmentRubroEvalProcesoLista != null) {
            fragmentRubroEvalProcesoLista.onParentFabClicked(tipoCompetencia);
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
    public void succesDelete() {
        presenter.onDeleteRubrica();
    }

    @OnClick(R.id.msg_actualizar)
    public void onViewClicked() {
        presenter.onClickActualizar();
    }

    @Override
    public void onBtnSuccesItemFilter(ArrayList<CompetenciaCheck> tipoCompetenciaList) {
        presenter.onBtnSuccesItemFilterRubro(tipoCompetenciaList);
    }

    @Override
    public void onResumenFragmentComentarios() {
        NewFragment newFragment = getFragment(NewFragment.class);
        if (newFragment != null) {
            hideFab();
        }
    }

    @Override
    public void expandirToolbar() {
        appBar.setExpanded(true, true);

    }

    @Override
    public void callSynckService(int programaEducativoId) {
        ComplejoSyncIntenService.start(this, programaEducativoId);
        CMRE.saveNotifyChangeDataBase(this);
    }


    @Override
    public void hideViewPager() {
        vpCurso.setVisibility(View.GONE);
    }

    @Override
    public void showViewPager() {
        vpCurso.setVisibility(View.VISIBLE);
    }

    @Override
    public void initComentarios(SesionAprendizajeUi sesionAprendizajeUi, int personaId, int backgroudColor, int CargaCursoId, int CursoId, int cargaAcademicaId, int parametroDisenioId) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        contentComentario.setVisibility(View.VISIBLE);
        Fragment fragment = ComentariosFragment.newInstanceSesions(sesionAprendizajeUi, personaId, backgroudColor, CargaCursoId, CursoId, cargaAcademicaId, parametroDisenioId);
        fragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.conten_comentario, fragment, "tag-proceso-evaluacion")
                .commitNow();
    }

    @Override
    public void hideFrameLayoutComentarios() {
        contentComentario.setVisibility(View.GONE);
    }

    public void salirFrameLayoutComentarios() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        presenter.salirFrameLayoutComentarios();
        int position = fragmentAdapter.getPosition(NewFragment.class);
        if (position == 0) position++;
        else position--;
        try {
            final int finalPosition = position;
            vpCurso.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    vpCurso.setCurrentItem(finalPosition);
                }
            }, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showToolbar() {
        toolbar.setVisibility(View.VISIBLE);
        ctlTabcursos.setVisibility(View.VISIBLE);
        appBar.setVisibility(View.VISIBLE);
        constraintLayoutSessiones.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideToolbar() {
        toolbar.setVisibility(View.GONE);
        ctlTabcursos.setVisibility(View.GONE);
        constraintLayoutSessiones.setVisibility(View.GONE);
        appBar.setVisibility(View.GONE);
    }

    @Override
    public void startTarea() {
        Log.d(TAG, "onSessionFabButtonClicked");
        FragmentTareasSesiones fragmentTareasSesiones = getFragment(FragmentTareasSesiones.class);
        if (fragmentTareasSesiones != null) {
            fragmentTareasSesiones.onParentFabClicked();
        }
    }

    @Override
    public void changeColorFloatButton() {
        try {
            fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.teal)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onReceive(TipoImportacion tipoImportacion, boolean success, BEVariables beVariables) {
        presenter.onChange(tipoImportacion, success);
    }

    @Override
    public void onStart(Class<?> aClass) {
        if (aClass.equals(getClass())) {
            if (toolbarprogress != null) toolbarprogress.setVisibility(View.GONE);
            /*if (menu != null) {
                MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
                menuItem.setVisible(true);
            }*/
        }
    }

    @Override
    public void onLoad(int count) {

        try {
            if (toolbarprogress.getVisibility() == View.GONE && menu != null) {
                toolbarprogress.setVisibility(View.VISIBLE);
                /*MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
                menuItem.setVisible(false);*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish() {
        try {
            if (toolbarprogress.getVisibility() == View.VISIBLE && menu != null) {
                toolbarprogress.setVisibility(View.GONE);
                /*MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
                menuItem.setVisible(true);*/
                presenter.onChangeFull(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        presenter.onFinishSynck();
    }

    @Override
    public void onFinishSimple() {
        try {
            if (toolbarprogress.getVisibility() == View.VISIBLE && menu != null) {
                toolbarprogress.setVisibility(View.GONE);
               /* MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
                menuItem.setVisible(true);*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        presenter.onFinishSynck();
    }

    @Override
    public void changeColorToolbar() {
        try {
            ctlTabcursos.setContentScrimColor(ContextCompat.getColor(getApplicationContext(), R.color.teal));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onComprobarCPTareas(boolean vigente) {
        /*FragmentTareasSesiones fragmentTareasSesiones = getFragment(FragmentTareasSesiones.class);
        if (fragmentTareasSesiones != null)
            fragmentTareasSesiones.onComprobarCPTareasSesiones(vigente);*/
        if (vigente) {
            changeFabBg(R.drawable.ic_add_white_24dp);
        } else hideFab();

    }

    @Override
    public void onComprobarCPRubro(boolean editar, boolean anclar) {

    }

    @Override
    public void progressUpdateSuccess() {
        if (menu != null) {
            /*MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
            if (menuItem != null) {
                menuItem.setIcon(R.drawable.ic_refresh2);
            }*/
        }
    }

    @Override
    public void progressUpdateError() {
        if (menu != null) {
           /* MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
            if (menuItem != null) {
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_problema_de_sincronizacion);
                if (drawable != null) {
                    drawable.mutate();
                    drawable.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_IN);
                }
                menuItem.setIcon(drawable);
            }*/
        }
    }

    @Override
    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null)
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
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
    public void hideMsgCalendarioPeriodo() {
        msgCalendarioPeriodo.setVisibility(View.GONE);
    }

    @Override
    public void showMsgCalendarioPeriodo() {
        msgCalendarioPeriodo.setVisibility(View.VISIBLE);
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
    public void saveNotifyChangeTabCurso() {
        Intent databack = new Intent();
        setResult(RESULT_OK,databack);
        //CMRE.saveNotifyChangeDataBase(getBaseContext());
    }

}



