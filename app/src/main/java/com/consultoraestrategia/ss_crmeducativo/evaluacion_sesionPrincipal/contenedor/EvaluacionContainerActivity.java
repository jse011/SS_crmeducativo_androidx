package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.contenedor;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.local.RubroLocalDataSourse;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.remote.RubroRemoteDataSourse;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.CalcularMediaDesviacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.UpdateEvaluacionFormulas;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.ui.RegistroFragment;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.ui.RegistroGrupoFragment;
import com.consultoraestrategia.ss_crmeducativo.grouplist.ui.ListaGrupoFragment;
import com.consultoraestrategia.ss_crmeducativo.login2.service2.worker.SynckService;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view.FragmentAbstract;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.sesion.view.FragmentRubroEvalProcesoLista;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.util.RepositoryInjector;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ui.ImportarActivity;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.validacion.GetFechaCreacionRubroEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;

import static android.content.Intent.ACTION_SEARCH;

/**
 * Created by SCIEV on 8/02/2018.
 */

public class EvaluacionContainerActivity extends BaseActivity<EvaluacionContainerView, EvaluacionContainerPresenter> implements EvaluacionContainerView, ListaGrupoFragment.RegistroGroupList, BaseFragmentListener {


    private static String TAG = EvaluacionContainerActivity.class.getSimpleName();
    public static final String RUBROEVALUACIONID = "ContainerActivity.rubroEvaluacionId";
    public static final String RUBROEVALUACIONTIPONOTAID = "ContainerActivity.rubroEvaluacionTipoNotaId";
    private static final String TITULO = "ContainerActivity.titulo";
    private static final String FRAGEMENTO = "ContainerActivity.fragmento";
    public static final String DESAVILITAR_EVALUACION = "ContainerActivity.disabledEval";
    public static final int REQUESTCODE = 37;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.root)
    FrameLayout root;
    @BindView(R.id.edtRubro)
    TextView edtRubro;
    @BindView(R.id.msg_actualizar)
    TextView msgActualizar;
    @BindView(R.id.contenedor)
    CoordinatorLayout contenedor;
    @BindView(R.id.msg_calendario_periodo)
    TextView msgCalendarioPeriodo;
    private MenuItem search;
    private Fragment fragment;
    private Menu menu;
    private SpotsDialog alertDialog;


    public static Intent createContainerActivityIntent(Context context, Bundle bundle, int rubroEvaluacionId, String titulo, boolean disabledEval, Class<?> fragmento) {
        Intent intent = new Intent(ACTION_SEARCH, null, context, EvaluacionContainerActivity.class);
        intent.putExtras(bundle);
        intent.putExtra(RUBROEVALUACIONID, rubroEvaluacionId);
        intent.putExtra(TITULO, titulo);
        intent.putExtra(FRAGEMENTO, fragmento.getSimpleName());
        intent.putExtra(DESAVILITAR_EVALUACION, disabledEval);
        return intent;
    }

    public static Intent createContainerActivityIntent(Context context, Bundle bundle, String rubroEvaluacionId, String titulo, boolean disabledEval, String rubroEvaluacionTipoNotaId,Class<?> fragmento) {
        Intent intent = new Intent(ACTION_SEARCH, null, context, EvaluacionContainerActivity.class);
        intent.putExtras(bundle);
        intent.putExtra(RUBROEVALUACIONID, rubroEvaluacionId);
        intent.putExtra(RUBROEVALUACIONTIPONOTAID, rubroEvaluacionTipoNotaId);
        intent.putExtra(TITULO, titulo);
        intent.putExtra(FRAGEMENTO, fragmento.getSimpleName());
        Log.d(TAG, "CALENDARIO ACTIVO "+ disabledEval);
        intent.putExtra(DESAVILITAR_EVALUACION, disabledEval);
        return intent;
    }

    public static Intent createContainerActivityIntent(Context context, Bundle bundle, String rubroEvaluacionId, String titulo, boolean disabledEval, Class<?> fragmento, int sesionAprendizajeId, int cargaCursoId, int cursoId, String rubroEvaluacionTipoNotaId) {
        Intent intent = createContainerActivityIntent(context, bundle, rubroEvaluacionId, titulo, disabledEval, rubroEvaluacionTipoNotaId,fragmento);
        intent.putExtra(FragmentRubroEvalProcesoLista.TAG_SESIONAPRENDIZAJEID, sesionAprendizajeId);
        intent.putExtra(FragmentRubroEvalProcesoLista.TAG_CARGACURSO_ID, cargaCursoId);
        intent.putExtra(FragmentRubroEvalProcesoLista.TAG_CURSO_ID, cursoId);
        intent.putExtras(bundle);
        return intent;
    }


    @Override
    protected String getTag() {
        return EvaluacionContainerActivity.class.getSimpleName();
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected EvaluacionContainerPresenter getPresenter() {
        RubroRepository rubroRepository= new RubroRepository(
                new RubroLocalDataSourse(
                        InjectorUtils.provideRubroEvaluacionDao(),
                        InjectorUtils.provideTipoNotaDao(),
                        InjectorUtils.provideIndicadorDao(), InjectorUtils.provideEvaluacionProcesoDao()),
                new RubroRemoteDataSourse());
        return new EvaluacionContainerPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetFechaCreacionRubroEvaluacion(RepositoryInjector.getGEDatosRubroEvaluacionProcesoRepositoryInjectorConTareas()),
                new GetRubro(rubroRepository),
                new CalcularMediaDesviacion(rubroRepository),
                new UpdateEvaluacionFormulas(rubroRepository));
    }

    @Override
    protected EvaluacionContainerView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.container_evalucion_bidimencional_activity);
        ButterKnife.bind(this);
        setupToolbar();
        setupContent();
        setupDialogProgress();
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


    private void setupDialogProgress() {
        alertDialog = new SpotsDialog(this, R.style.SpotsDialogRubro);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected ViewGroup getRootLayout() {
        return contenedor;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    private void setupContent() {
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString(TITULO);
        String nombreFragmento = bundle.getString(FRAGEMENTO);
        edtRubro.setText(title);

        switch (nombreFragmento) {
            case "RegistroGrupoFragment":
                fragment = RegistroGrupoFragment.newInstance();
                toolbar.post(new Runnable() {
                    @Override
                    public void run() {
                        if (search != null) search.setVisible(false);
                    }
                });
                break;
            case "RegistroFragment":
                fragment = RegistroFragment.newInstance();
                break;
        }

        if (fragment == null) return;
        fragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.root, fragment, "tag-proceso-evaluacion")
                .commitNow();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            default:
                Log.d(TAG, "is atras: " + id);
                presenter.onUpdateRubro();
                //sendFinish();
                break;
            case R.id.action_help:
                showTutorialRubroSimple(true);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        presenter.onUpdateRubro();
        super.onDestroy();

    }

    @Override
    public void showActividadImportacion(BEVariables beVariables) {
        ImportarActivity.launchImportarActivity(this, TipoImportacion.RUBROEVALUACION, beVariables);
    }

    @Override
    public void showMsgActualizacion() {
        msgActualizar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMsgActualizacion() {
        msgActualizar.setVisibility(View.GONE);
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
    public void serviceUpdateRubro(String rubroEvaluacionId) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(FragmentAbstract.TIPO, FragmentAbstract.TIPO_UPDATE_RUBRO_EVALUACION);
        resultIntent.putExtra(FragmentAbstract.INT_RUBROEVALUACION_ID, rubroEvaluacionId);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void updateTableView(FiltroTableUi filtroTableUi) {
        RegistroFragment registroFragment = getFragment(RegistroFragment.class);
        if (registroFragment != null) registroFragment.updateTableView(filtroTableUi);
        RegistroGrupoFragment registroGrupoFragment = getFragment(RegistroGrupoFragment.class);
        if (registroGrupoFragment != null) registroGrupoFragment.updateTableView(filtroTableUi);
    }

    @Override
    public void changeSwiteOn() {
        /*btnFooter.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_footer));*/
    }

    @Override
    public void changeSwiteOff() {
        /*btnFooter.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_document_footer));*/
    }

    @Override
    public void changeEyeSimple() {
        /*btnEye.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_eeye));*/
    }

    @Override
    public void changerEyeFocus() {
        /*btnEye.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_vision));*/
    }

    @Override
    public void showFooter() {
        RegistroFragment registroFragment = getFragment(RegistroFragment.class);
        if (registroFragment != null) registroFragment.showFooter();
        RegistroGrupoFragment registroGrupoFragment = getFragment(RegistroGrupoFragment.class);
        if (registroGrupoFragment != null) registroGrupoFragment.showFooter();

    }

    @Override
    public void hideFooter() {
        RegistroFragment registroFragment = getFragment(RegistroFragment.class);
        if (registroFragment != null) registroFragment.hideFooter();
        RegistroGrupoFragment registroGrupoFragment = getFragment(RegistroGrupoFragment.class);
        if (registroGrupoFragment != null) registroGrupoFragment.hideFooter();
    }

    @Override
    public void changeTableAvanzado() {
        /*btnEye.post(new Runnable() {
            @Override
            public void run() {
                RegistroFragment registroFragment = getFragment(RegistroFragment.class);
                if (registroFragment != null) registroFragment.changeTableAvanzado();
                RegistroGrupoFragment registroGrupoFragment = getFragment(RegistroGrupoFragment.class);
                if (registroGrupoFragment != null) registroGrupoFragment.changeTableAvanzado();
            }
        });*/

    }

    @Override
    public void changeTableSimple() {
        /*btnEye.post(new Runnable() {
            @Override
            public void run() {
                RegistroFragment registroFragment = getFragment(RegistroFragment.class);
                if (registroFragment != null) registroFragment.changeTableSimple();
                RegistroGrupoFragment registroGrupoFragment = getFragment(RegistroGrupoFragment.class);
                if (registroGrupoFragment != null) registroGrupoFragment.changeTableSimple();
            }
        });*/

    }

    @Override
    public void changeImageClearOn() {
        /*RegistroFragment registroFragment = getFragment(RegistroFragment.class);
        if (registroFragment != null) registroFragment.cleanAllList();
        RegistroGrupoFragment registroGrupoFragment = getFragment(RegistroGrupoFragment.class);
        if (registroGrupoFragment != null) registroGrupoFragment.clearAllList();*/

    }

    @Override
    public void showBtnClean(boolean isCalendarioVigente) {
        /*if (isCalendarioVigente)btnClear.setVisibility(View.VISIBLE);
            else btnClear.setVisibility(View.GONE);*/
    }

    @Override
    public void showBtnFooter() {
        /*btnFooter.setVisibility(View.VISIBLE);*/
    }

    @Override
    public void hideBtnFooter() {
        /*btnFooter.setVisibility(View.GONE);*/
    }

    @Override
    public void showTutorialRubroNormal() {

    }

    @Override
    public void showTutorialRubricaDetalle() {

    }

    @Override
    public void showBtnHelp() {
        MenuItem itemMenu = menu.findItem(R.id.action_search);
        itemMenu.setVisible(true);
    }

    @Override
    public void hideBtnHelp() {
        MenuItem itemMenu = menu.findItem(R.id.action_search);
        itemMenu.setVisible(false);
    }

    @Override
    public void onSincronizate(int programaEducativoId, String rubroEvaluacionId) {
        CallService.jobServiceExportarTipos(this, TipoExportacion.RUBROEVALUACION_FORMULA_ASOCIADOS);
        SimpleSyncIntenService.start(this, programaEducativoId);
        SynckService.start(this,programaEducativoId);
    }

    @OnClick(R.id.msg_actualizar)
    public void onViewClicked() {
        presenter.onBtnActualizarRubro();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_rubro, menu);
        this.menu = menu;
        search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search(searchView);
        presenter.onCreateOptionsMenu();
        return true;
    }


    private void showTutorialRubroSimple(boolean notSecuence) {

        if(fragment instanceof RegistroFragment){
            RegistroFragment registroFragment = (RegistroFragment)fragment;
            View txtInidicador = registroFragment.txtInidicador;
            //TableFixHeaders tablefixheaders = registroFragment.tablefixheaders;
            //Tutorial.showTutorialEvaluacionDos(this ,txtInidicador, tablefixheaders.columnViewList, tablefixheaders.bodyViewTable, search,btnClear, btnFooter, btnEye, notSecuence);
        }else if(fragment instanceof RegistroGrupoFragment) {
            RegistroGrupoFragment registroGrupoFragment = (RegistroGrupoFragment)fragment;
            View txtInidicador = registroGrupoFragment.txtInidicador;
            //TableFixHeaders tablefixheaders = registroGrupoFragment.tablefixheaders;
            //Tutorial.showTutorialEvaluacionDos(this ,txtInidicador, tablefixheaders.columnViewList, tablefixheaders.bodyViewTable, search, btnClear, btnFooter, btnEye, notSecuence);
        }
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (presenter != null) presenter.onQueryTextSubmit(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (presenter != null) presenter.onQueryTextChange(newText);
                return true;
            }
        });
    }

    public void onClikCornerTableView() {
        presenter.onClikCornerTableView();
    }

    private <T extends Fragment> T getFragment(Class<T> tClass) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment :
                fragments) {
            if (tClass.isInstance(fragment)) {
                return (T) fragment;
            }
        }
        return null;
    }

    @Override
    public void onSaveGroupList() {
        //constraint.setVisibility(View.VISIBLE);
        RegistroGrupoFragment registroFragment = getFragment(RegistroGrupoFragment.class);
        if (registroFragment != null) registroFragment.onSaveGroupList();
    }

    @Override
    public void onCancelarGroupList() {
        //constraint.setVisibility(View.GONE);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            presenter.onUpdateRubro();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void initTutorial() {
        //presenter.initTutorial();
    }

    @Override
    public void showDialogProgress() {
        if(!((Activity) this).isFinishing())
        {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if(alertDialog!=null)alertDialog.show();
                    // Stuff that updates the UI
                }
            });
        }

    }

    @Override
    public void hideDialogProgress() {
        Log.d(TAG, "hideDialogProgress");
        if(!((Activity) this).isFinishing())
        {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if(alertDialog!=null)alertDialog.dismiss();
                    // Stuff that updates the UI
                }
            });
        }


    }
}
