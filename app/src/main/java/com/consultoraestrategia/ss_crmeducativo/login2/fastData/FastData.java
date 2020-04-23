package com.consultoraestrategia.ss_crmeducativo.login2.fastData;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.login2.adapter.AdapterFastData;
import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetDatosPreFastData;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData.GetDatosServidorTwo;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ProgramaEducativoUi;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FastData extends BaseActivity<FastDataView, FastDataPresenter> implements FastDataView {

    @BindView(R.id.btn_siguiente_password)
    Button btnSiguientePassword;
    @BindView(R.id.rc_descarga)
    RecyclerView rcDescarga;
    @BindView(R.id.animationView)
    LottieAnimationView animationView;
    @BindView(R.id.progress_init)
    ConstraintLayout progressInit;
    @BindView(R.id.txt_mensaje)
    TextView txtMensaje;
    private AdapterFastData fastDataAdapter;
    private AlertDialog fragmentDialog;


    public static void start(Context context, int anioAcademicoId, int usuarioId, int calendarioPeriodoId, int programaEducativoId) {
        CRMBundle crmBundle = new CRMBundle();
        crmBundle.setAnioAcademico(anioAcademicoId);
        crmBundle.setUsuarioId(usuarioId);
        crmBundle.setCalendarioPeriodoId(calendarioPeriodoId);
        crmBundle.setProgramaEducativoId(programaEducativoId);
        Intent intent = new Intent(context, FastData.class);
        intent.putExtras(crmBundle.instanceBundle());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setupAnimation() {
        int numero = (int) (Math.random() * 6);
        switch (numero) {
            case 0:
                progressInit.setBackgroundColor(Color.parseColor("#BCC3C6"));
                animationView.setAnimation("runningloader2.json");
                break;
            case 1:
                progressInit.setBackgroundColor(Color.parseColor("#BCC3C6"));
                animationView.setAnimation("rainloader.json");
                break;
            case 2:
                progressInit.setBackgroundColor(Color.parseColor("#EB6849"));
                animationView.setAnimation("sheep_play_computer.json");
                break;
            case 3:
                progressInit.setBackgroundColor(Color.WHITE);
                animationView.setAnimation("sheeploader.json");
                //if (fastDataAdapter != null) fastDataAdapter.setTexColor(Color.BLACK);
                break;
            case 4:
                progressInit.setBackgroundColor(Color.BLACK);
                animationView.setAnimation("hand_sanitizer.json");
                //if (fastDataAdapter != null) fastDataAdapter.setTexColor(Color.BLACK);
                break;
            case 5:
                progressInit.setBackgroundColor(Color.BLACK);
                animationView.setAnimation("sheep_drive_car.json");
                //if (fastDataAdapter != null) fastDataAdapter.setTexColor(Color.BLACK);
                break;
        }

        animationView.setRepeatCount(ValueAnimator.INFINITE);
        animationView.playAnimation();
    }

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected FastDataPresenter getPresenter() {
        LoginDataRepository service2Repositorio = new LoginDataRepositoryImpl(ApiRetrofit.getInstance(), InjectorUtils.provideSessionUserDao(), InjectorUtils.provideParametrosDisenioDao(), InjectorUtils.provideCursoDao(), InjectorUtils.provideAlumnoDao());
        return new FastDataPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetDatosPreFastData(service2Repositorio),
                new GetDatosServidorTwo(service2Repositorio));
    }

    @Override
    protected FastDataView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.login_fast_data);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ButterKnife.bind(this);
        initAdapter();
        setupAnimation();
    }


    @Override
    protected ViewGroup getRootLayout() {
        return progressInit;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    private void initAdapter() {
        rcDescarga.setLayoutManager(new LinearLayoutManager(this));
        fastDataAdapter = new AdapterFastData(rcDescarga);
        rcDescarga.setAdapter(fastDataAdapter);
        rcDescarga.setVerticalScrollBarEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (fragmentDialog != null) fragmentDialog.cancel();
    }

    @Override
    public void updateListaActualizar(ProgramaEducativoUi programaEducativoUi) {
        fastDataAdapter.updateOrAddItem(programaEducativoUi);
    }


    @Override
    public void clearListaActualizar() {
        fastDataAdapter.clear();
    }

    @Override
    public void finshedDialogWithError() {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(R.string.msg_confirmacionTitlle);
        builder.setMessage("¿No se logró actualizar los curso por completo?");
        builder.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Log.d(TAG, "Presionos ACEPTAR");
                presenter.reintentarDescarga();
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
    public void cerrarActividad() {
       finish();
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @OnClick(R.id.btn_siguiente_password)
    public void onViewClicked() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Descarga rápida");
        builder.setMessage("¿Estás seguro de cerrar la descarga rápida, recuerde que si cierra la descarga rápida, estas se realizaran cundo ingresé al curso?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Log.d(TAG, "Presionos ACEPTAR");
                dialogInterface.cancel();
                finish();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        //Create AdapterExample
        fragmentDialog = builder.create();
        fragmentDialog.show();
    }

}
