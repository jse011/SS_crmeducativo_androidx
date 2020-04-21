package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.cabecera.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.cabecera.CrearRubroCabeceraPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.RubroProcesoWrapper;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.listener.CrearRubroListener;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.ui.CrearRubrosFragment;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view.FragmentAbstract;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.listener.TipoNotaListaListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Intent.ACTION_SEARCH;

/**
 * Created by SCIEV on 28/02/2018.
 */

@SuppressLint("Registered")
public class CrearRubroCabeceraActividad extends BaseActivity<CrearRubroCabeceraView, BasePresenterImpl<CrearRubroCabeceraView>> implements CrearRubroCabeceraView, CrearRubroListener, TipoNotaListaListener {

    final static String TAG = CrearRubroCabeceraActividad.class.getSimpleName();
    public static final String RUBROEVALUACIONID = "CrearRubroCabeceraActividad.rubroEvaluacionId";
    public static final String INT_SESION_APRENDIZAJE_ID = "CrearRubroCabeceraActividad.SesionAprenizajeId";
    public static final String INT_INDICADOR_ID = "CrearRubroCabeceraActividad.indicadorId";
    public static final String ARRAYLIST_CAMPOTEMATICO_ID = "CrearRubroCabeceraActividad.campotematicoIds";
    public static final String INT_SILAVO_EVENTO_ID = "CrearRubroCabeceraActividad.silavoEventoId";
    public static final String INT_COMPETENCIA_ID = "CrearRubroCabeceraActividad.competenciaId";
    public static final String INT_CALENDARIO_PERIODO_ID = "CrearRubroCabeceraActividad.calendarioPeriodoId";
    public static final String INT_PROGRAMA_EDUCATIVO_ID = "CrearRubroCabeceraActividad.programaEducativoId";
    public static final String STR_TAREA_ID = "CrearRubroCabeceraActividad.tareaId";
    public static final String CURSO_ID = "CrearRubroCabeceraActividad.cursoId";
    public static final int REQUESTCODE = 2251;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    FrameLayout viewpager;
    @BindView(R.id.root)
    CoordinatorLayout root;

    public static Intent launchCrearRubroEvaluacionActivity(Context context, int sesionAprendizajeId, int silavoEventoId, int calendarioPeriodoId, int competenciaId, int indicadorId, ArrayList<Integer> campotematicoIds, int programaEducativo, String tareaId, int cursoId) {
        Intent intent = new Intent(ACTION_SEARCH, null, context, CrearRubroCabeceraActividad.class);
        intent.putExtra(INT_SESION_APRENDIZAJE_ID, sesionAprendizajeId);//enviar cero si no es un rubro de un sesion
        intent.putExtra(INT_INDICADOR_ID, indicadorId);
        intent.putExtra(INT_SILAVO_EVENTO_ID, silavoEventoId);
        Log.d(TAG,  " / competenciaId " + competenciaId);
        intent.putExtra(INT_COMPETENCIA_ID, competenciaId);
        intent.putExtra(INT_CALENDARIO_PERIODO_ID, calendarioPeriodoId);
        intent.putExtra(ARRAYLIST_CAMPOTEMATICO_ID, campotematicoIds);
        intent.putExtra(INT_PROGRAMA_EDUCATIVO_ID, programaEducativo);
        intent.putExtra(STR_TAREA_ID, tareaId);
        intent.putExtra(CURSO_ID, cursoId);
        Log.d(TAG,  " / cursoId " + cursoId);
        return intent;
    }
    public static Intent launchEditeRubBrosdActividad(Context context, RubroProcesoWrapper rubroProcesoWrapper) {
        Intent intent = CrearRubroCabeceraActividad.launchCrearRubroEvaluacionActivity(context, rubroProcesoWrapper.getSesionAprendizajeId(), rubroProcesoWrapper.getSilaboEventoId(), rubroProcesoWrapper.getCalendarioId(),rubroProcesoWrapper.getCompetenciaId(),rubroProcesoWrapper.getIndicadorId(), rubroProcesoWrapper.getCampotematicoIds(),rubroProcesoWrapper.getProgramaEducativoId(),null, rubroProcesoWrapper.getCursoId());
        Bundle args = intent.getExtras();
        intent.putExtras(rubroProcesoWrapper.convertBundle(args));
        return intent;
    }


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected BasePresenterImpl<CrearRubroCabeceraView> getPresenter() {
        return new CrearRubroCabeceraPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected CrearRubroCabeceraView getBaseView() {
        return this;
    }


    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.actividad_crear_rubro_bidimencional);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupViewPager();
        setupToolbar();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //change color of status bar
            Window window = this.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#FAFAFA"));
        }
    }


    private void setupViewPager() {
        Log.d(TAG, "setupViewPager");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CrearRubrosFragment crearRubrosFragment = CrearRubrosFragment.newInstance(getIntent().getExtras());
        fragmentTransaction.replace(R.id.viewpager, crearRubrosFragment);
        fragmentTransaction.commit();

        //MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        //fragmentAdapter.addFragment(CrearRubrosFragment.newInstance(getIntent().getExtras()), "Crear Rubro");
        //fragmentAdapter.addFragment(TipoNotaListaFragment.newInstance(getIntent().getExtras(),false, TipoUi.Tipo.values()), getString(R.string.fragment_crear_rubro_competencia_tiponota));
        //viewpager.setOffscreenPageLimit(2);
        //viewpager.setAdapter(fragmentAdapter);
        //tabs.setupWithViewPager(viewpager);
        /*viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                CrearRubrosFragment crearRubrosFragment = getCrearRubrosFragment();
                if (crearRubrosFragment != null) crearRubrosFragment.hideTeclado();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/

    }



    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void hideCrearRubro() {
        finish();
    }


    @Override
    public void onSaveRubroEvaluacionProcesoSuccess(String rubroEvaluacionProcesoId, CrearRubroEvalUi crearRubroEvalUi) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(FragmentAbstract.TIPO, FragmentAbstract.TIPO_ADD_RUBRO_EVALUACION);
        resultIntent.putExtra(FragmentAbstract.INT_RUBROEVALUACION_ID, rubroEvaluacionProcesoId);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onSuccessDialogTipoNotaList(int tipoNotaId, String nombre) {

    }

    @Override
    public void onSuccessDialogTipoNotaList(String tipoNotaId, String nombre) {
        CrearRubrosFragment fragment = getCrearRubrosFragment();
        if (fragment == null) return;
        fragment.onSelectTipoNota(tipoNotaId);

        //tabs.getTabAt(0).select();
    }


    private CrearRubrosFragment getCrearRubrosFragment() {
        List<Fragment> fragments = getActivity().getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof CrearRubrosFragment) {
                return (CrearRubrosFragment) fragment;
            }
        }
        return null;
    }

    @Override
    public void onSelectPager(int posicion) {
        //tabs.getTabAt(posicion).select();
    }

    @Override
    public void onAceptar() {
        CrearRubrosFragment fragment = getCrearRubrosFragment();
        if (fragment == null) return;
        fragment.onAceptar();
    }

    @Override
    public void onCancelar() {
        CrearRubrosFragment fragment = getCrearRubrosFragment();
        if (fragment == null) return;
        fragment.onCancelar();
    }

    @Override
    public void onAcceptButtom() {
        onCancelar();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_create:
                onAceptar();
                break;
            default:
                presenter.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
        Log.d(TAG, "onBackPressed");
    }


    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTittle) {
        super.showFinalMessageAceptCancel(message, messageTittle);
    }


}
