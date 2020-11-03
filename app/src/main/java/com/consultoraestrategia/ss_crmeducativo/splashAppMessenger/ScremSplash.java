package com.consultoraestrategia.ss_crmeducativo.splashAppMessenger;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
//import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatsActivity;
import com.consultoraestrategia.ss_crmeducativo.login2.principal.Login2Activity;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.data.source.ScreamSplashRepository;
import com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.data.source.repositorio.ScreamRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.useCase.GetUrlServidorLocal;
import com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.useCase.UseCaseSincronizar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScremSplash extends BaseActivity<ScremSplashView, ScremSplashPresenter> implements ScremSplashView {
    @BindView(R.id.text_progress)
    TextView textProgress;
    @BindView(R.id.pgr_progresshorizontal)
    ProgressBar pgrProgresshorizontal;
    @BindView(R.id.contendoLoading)
    ConstraintLayout contendoLoading;
    @BindView(R.id.progressBar8)
    ProgressBar progressBar8;

    @Override
    protected String getTag() {
        return "ScremSplashTAG";
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ScremSplashPresenter getPresenter() {
        ScreamSplashRepository screamSplashRepository = new ScreamSplashRepository(new ScreamRemoteDataSource(UtilServidor.getInstance()));
        return new ScremSplashPresenteImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new UseCaseSincronizar(screamSplashRepository),
                new GetUrlServidorLocal(screamSplashRepository));
    }

    @Override
    protected ScremSplashView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        //setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setupToolbar();
        contendoLoading.setVisibility(View.VISIBLE);


        //setupTest();

    }

    private void setupToolbar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //change color of status bar
            Window window = this.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#FAFAFA"));

        }
    }

    @Override
    protected ViewGroup getRootLayout() {
        return contendoLoading;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void updateListaEnviar(final int progress) {
/*
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                String[] progressMessages = getResources().getStringArray(R.array.activity_splash_progress_messages_array);
                int length = progressMessages.length;
                int numero = (int) (Math.random() * length);
                String mensaje = progress +"% "+progressMessages[numero];
                textProgress.setText(mensaje);
                pgrProgresshorizontal.setProgress(progress);

            }
        });
*/
    }

    @Override
    public void startMainActivity() {
        Log.d(getTag(), "startMainActivity");
        //Intent intent = new Intent(ScremSplash.this, ChatsActivity.class);
        //startActivity(intent);
        finish();
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
    public void startOfflineMainActivity() {
        /**
        runOnUiThread(new Runnable() {
            public void run() {
                Log.d(getTag(), "startOfflineMainActivity");
                String[] progressMessages = getResources().getStringArray(R.array.activity_splash_progress_messages_array);
                textProgress.setText(progressMessages[1]);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textProgress.setText(R.string.msg_unknow_internet_2);
                        pgrProgresshorizontal.setProgress(1000);
                        Intent intent = new Intent(ScremSplash.this, ChatsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },1000);
            }
        });
        **/
    }

    public void starLoginActivity() {
        runOnUiThread(new Runnable() {
            public void run() {
                Intent intent = new Intent(ScremSplash.this, Login2Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtras(new Bundle());
                startActivity(intent);
            }
        });

    }

    public void close() {
        finish();
    }

}
