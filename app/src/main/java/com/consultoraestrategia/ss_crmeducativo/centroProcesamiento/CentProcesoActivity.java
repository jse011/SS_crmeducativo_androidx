package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.LifecycleImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.data.CentroProcesamientoRepositorio;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.data.CentroProcesamientoRepositorioImpl;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.dialog.CerrarCurso;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.dialog.GenerarNotas;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.useCase.CerrarCursoResultado;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.useCase.GetCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.useCase.GetMatrizResultado;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.useCase.PromediarNotas;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view.CentProcesoView;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view.CerrarCursoDialogView;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view.GenerarNotasDialogView;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view.TutorialCentView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CentProcesoActivity extends BaseActivity<CentProcesoView, CentProcesoPresenter> implements CentProcesoView, LifecycleImpl.LifecycleListener {
    @BindView(R.id.root)
    FrameLayout root;
    private NavController navController;

    public static void start(Context context, CRMBundle crmBundle) {
        Intent intent = new Intent(context, CentProcesoActivity.class);
        intent.putExtras(crmBundle.instanceBundle());
        context.startActivity(intent);

    }

    @Override
    protected String getTag() {
        return "CentProcesoActivity";
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected CentProcesoPresenter getPresenter() {
        CentroProcesamientoRepositorio centroProcesamientoRepositorio =  new CentroProcesamientoRepositorioImpl();
        return new CentProcesoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(), new GetMatrizResultado(centroProcesamientoRepositorio),
                new GetCalendarioPeriodo(centroProcesamientoRepositorio),
                new PromediarNotas(centroProcesamientoRepositorio),
                new CerrarCursoResultado(centroProcesamientoRepositorio));
    }

    @Override
    protected CentProcesoView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.centro_procesamiento);
        ButterKnife.bind(this);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        getSupportFragmentManager().registerFragmentLifecycleCallbacks(new LifecycleImpl(0,this),true);
        setupToolbar();
    }

    private void setupToolbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //change color of status bar
            Window window = this.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.md_white_1000));
        }
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
    public void onChildsFragmentViewCreated() {

    }

    @Override
    public void onFragmentViewCreated(Fragment f, View view, Bundle savedInstanceState) {

    }

    @Override
    public void onFragmentResumed(Fragment f) {

    }

    @Override
    public void onFragmentViewDestroyed(Fragment f) {
        if(f instanceof RegistroCentProcesamientoView){
            presenter.onDestroyRegistroCentProcesamientoView();
        } else if(f instanceof TutorialCentView){
            presenter.onDestroyTutorialCentView();
        }else if(f instanceof GenerarNotasDialogView){
            presenter.onDestroyGenerarNotasDialogView();
        }else if(f instanceof CerrarCursoDialogView){
            presenter.onDestroyCerrarCursoDialogView();
        }


    }

    @Override
    public void onFragmentActivityCreated(Fragment f, Bundle savedInstanceState) {
        if(f instanceof RegistroCentProcesamientoView){
            ((RegistroCentProcesamientoView)f).setPresenter(presenter);
            presenter.attachView(((RegistroCentProcesamientoView)f));
        }else  if(f instanceof TutorialCentView){
            ((TutorialCentView)f).setPresenter(presenter);
            presenter.attachView(((TutorialCentView)f));
        }else if(f instanceof GenerarNotasDialogView){
            ((GenerarNotasDialogView)f).setPresenter(presenter);
            presenter.attachView(((GenerarNotasDialogView)f));
        }else if(f instanceof CerrarCursoDialogView){
            ((CerrarCursoDialogView)f).setPresenter(presenter);
            presenter.attachView(((CerrarCursoDialogView)f));
        }
    }

    @Override
    public void showDialogGenerarNotas() {
        GenerarNotas generarNotas = new GenerarNotas();
        generarNotas.show(getSupportFragmentManager(), "GenerarNotas");
    }

    @Override
    public void showDialogCerrarEvaluacion() {
        CerrarCurso cerrarCurso = new CerrarCurso();
        cerrarCurso.show(getSupportFragmentManager(), "GenerarNotas");
    }
}
