package com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ui;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.appbar.AppBarLayout;
import androidx.transition.TransitionManager;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.util.RepositoryInjector;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ImportarActivityPresenter;
import com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ImportarActivityPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.services.util.ImportarCountDownTimer;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveBEDatosCalendarioPeriodoImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveBEDatosEnvioGrupoImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveBEDatosEnvioTipoNotaImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveBEDatosListEnvioGrupoImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveBEDatosLoginImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveBEDatosTareaRecursoImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveGEDatosListRubroEvaluacionProcesoImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveGEDatosRubroEvaluacionProcesoImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveGEDatosSesionAprendizajeImport;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.importacion.SaveGEListDatosSesionAprendizajeImport;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImportarActivity extends BaseActivity<ImportarActivityView, ImportarActivityPresenter> implements ImportarActivityView, ImportarCountDownTimer.ImportarCountDownTimerCallback {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout2)
    AppBarLayout appBarLayout2;
    @BindView(R.id.image_logo_progress_page)
    ImageView imageLogoProgressPage;
    @BindView(R.id.pgr_progresshorizontal)
    ProgressBar pgrProgresshorizontal;
    @BindView(R.id.text_progress)
    TextView textProgress;
    @BindView(R.id.root)
    ConstraintLayout root;
    ImportarCountDownTimer importarCountDownTimer;
    public static final String TIPOIMPORTACION = "TipoImportacion";
    public static final String BEVARIABLES = "BEVariables";
    private Animation slideUp;
    private Animation slideDown;

    public static void launchImportarActivity(Context context, TipoImportacion tipoImportacion, BEVariables beVariables) {
        Intent intent = launchImportarActivityIntent(context, tipoImportacion,beVariables);
        context.startActivity(intent);
    }

    public static Intent launchImportarActivityIntent(Context context, TipoImportacion tipoImportacion, BEVariables beVariables) {
        Intent intent = new Intent(context, ImportarActivity.class);
        intent.putExtra(TIPOIMPORTACION, tipoImportacion.toString());
        intent.putExtra(BEVARIABLES, beVariables);
        return intent;
    }

    @Override
    protected String getTag() {
        return ImportarActivity.class.getSimpleName();
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ImportarActivityPresenter getPresenter() {
        return new ImportarActivityPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new SaveGEDatosRubroEvaluacionProcesoImport(RepositoryInjector.getGEDatosRubroEvaluacionProcesoRepositoryInjectorConTareas()),
                new SaveGEDatosSesionAprendizajeImport(RepositoryInjector.getBEDatosSesionAprendizajeRepository()),
                new SaveBEDatosEnvioGrupoImport(RepositoryInjector.getBEDatosEnvioGrupoRepositoryInjector()),
                new SaveGEListDatosSesionAprendizajeImport(RepositoryInjector.getBEDatosSesionAprendizajeRepository()),
                new SaveGEDatosListRubroEvaluacionProcesoImport(RepositoryInjector.getGEDatosRubroEvaluacionProcesoRepositoryInjectorConTareas()),
                new SaveBEDatosListEnvioGrupoImport(RepositoryInjector.getBEDatosEnvioGrupoRepositoryInjector()),
                new SaveBEDatosTareaRecursoImport(RepositoryInjector.getBEDatosTareaRecursosRepositoryConRubros()),
                new SaveBEDatosEnvioTipoNotaImport(RepositoryInjector.getBEDatosEnvioTipoNotaRepository()),
                new SaveBEDatosCalendarioPeriodoImport(RepositoryInjector.getBEDatosCargaAcademicaRepository()),
                new SaveBEDatosLoginImport(RepositoryInjector.getBEObtenerDatosLoginRepository()));
    }

    @Override
    protected ImportarActivityView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_importar);
        ButterKnife.bind(this);
        setupLogoAnim();
        setupAnimations();
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
    public void onImportarCountDownTimerCount(int count) {
        presenter.onImportarCountDownTimerCount(count);
    }

    @Override
    public void onImportarCountDownTimerFinish() {
        presenter.onImportarCountDownTimerFinish();
    }

    @Override
    public void onImportarCountDownProgress(int progress) {
        if(imageLogoProgressPage != null)pgrProgresshorizontal.setProgress(progress);
    }

    @Override
    public void onStartProgress(long millisInFuture, long countDownInterval) {
        if(importarCountDownTimer != null)importarCountDownTimer.onDestroy();
        importarCountDownTimer = new ImportarCountDownTimer(millisInFuture, countDownInterval, this);
        importarCountDownTimer.start();
    }

    @Override
    public void onEndProgress() {
        destruirTimer();
    }

    @Override
    public void updateProgressText(String text) {
        TransitionManager.beginDelayedTransition(root);
        textProgress.setText(text);
    }

    private void setupAnimations() {
        slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down);
    }

    @Override
    public void showImagenSucces() {
        imageLogoProgressPage.startAnimation(slideDown);
        imageLogoProgressPage.post(new Runnable() {
            @Override
            public void run() {
                startAnimationImagenSucces();
            }
        });
    }

    private void startAnimationImagenSucces(){
        imageLogoProgressPage.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.importar_success));
        imageLogoProgressPage.startAnimation(slideUp);
        imageLogoProgressPage.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.postImagenSucces();
            }
        },1000);
    }

    @Override
    public void showImagenError() {
        imageLogoProgressPage.startAnimation(slideDown);
        imageLogoProgressPage.postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnimationImagenError();
            }
        },500);

    }

    private void startAnimationImagenError(){
        imageLogoProgressPage.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.importar_error));
        imageLogoProgressPage.startAnimation(slideUp);
        imageLogoProgressPage.post(new Runnable() {
            @Override
            public void run() {
                presenter.postImagenError();
            }
        });
    }

    @Override
    public void hideActivity() {
        Intent intent = new Intent();
        setResult(AppCompatActivity.RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destruirTimer();
    }

    void destruirTimer(){
        if(importarCountDownTimer != null)importarCountDownTimer.onDestroy();
        importarCountDownTimer = null;
    }


    public void setupLogoAnim() {
        Glide.with(this)
                .asGif()
                .load(Uri.parse("file:///android_asset/importar_data.gif"))
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_error_outline_black).placeholder(R.drawable.logo_consultoraa))
                .into(imageLogoProgressPage);
    }


}
