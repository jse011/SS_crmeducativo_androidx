package com.consultoraestrategia.ss_crmeducativo.login2.progress;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetListActualizar;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData.GetDatosServidor;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ProgressDialogFragment extends BaseDialogFragment<ProgressView, ProgressPrenter, ListenerProgress> implements ProgressView {
    @BindView(R.id.animationView)
    LottieAnimationView animationView;
    @BindView(R.id.progress_init)
    ConstraintLayout progressInit;
    @BindView(R.id.progress_bar_horizontal)
    ProgressBar progressBarHorizontal;
    @BindView(R.id.btn_cerrar)
    Button btnCerrar;
    private AlertDialog fragmentDialog;

    public static ProgressDialogFragment newInstance(int usuarioId, int empleadoId, int programaEducativoId, int cargaCursoId, int calendarioPeriodoId, int idGeoreferenciaId, int idEntidad, int silaboId, int idCurso, int cargaAcademica, boolean cursoComplejo) {

        CRMBundle crmBundle = new CRMBundle();
        crmBundle.setCalendarioPeriodoId(calendarioPeriodoId);
        crmBundle.setCargaCursoId(cargaCursoId);
        crmBundle.setUsuarioId(usuarioId);
        crmBundle.setEmpleadoId(empleadoId);
        crmBundle.setProgramaEducativoId(programaEducativoId);
        crmBundle.setGeoreferenciaId(idGeoreferenciaId);
        crmBundle.setEntidadId(idEntidad);
        crmBundle.setSilaboEventoId(silaboId);
        crmBundle.setCursoId(idCurso);
        crmBundle.setCargaAcademicaId(cargaAcademica);
        crmBundle.setComplejo(cursoComplejo);

        ProgressDialogFragment fragment = new ProgressDialogFragment();
        fragment.setArguments(crmBundle.instanceBundle());
        return fragment;


    }

    @Override
    protected String getLogTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected ProgressPrenter getPresenter() {
        LoginDataRepository service2Repositorio = new LoginDataRepositoryImpl(ApiRetrofit.getInstance(), InjectorUtils.provideSessionUserDao(), InjectorUtils.provideParametrosDisenioDao(), InjectorUtils.provideCursoDao(), InjectorUtils.provideAlumnoDao());
        return new ProgressPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetListActualizar(service2Repositorio),
                new GetDatosServidor(service2Repositorio));
    }

    @Override
    protected ProgressView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_update_alumnos, container, false);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getDialog().requestWindowFeature(STYLE_NO_TITLE);

        }
        setCancelable(false);
        View view =  super.onCreateView(inflater, container, savedInstanceState);
        setupAnimation();
        return view;
    }

    private void setupAnimation() {
        animationView.setAnimation("constructionsite.json");
        animationView.setRepeatCount(ValueAnimator.INFINITE);
        progressInit.setVisibility(View.VISIBLE);
        animationView.playAnimation();
        progressInit.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_left_in));
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
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void finshedDialog() {
        listener.finshedDialog();
        dismiss();
    }

    @Override
    public void showDialogError() {
        listener.finshedDialogWithError();
        dismiss();
    }

    @Override
    public void updatePorcebtaje(int porcentaje) {
        progressBarHorizontal.setProgress(porcentaje);
    }

    @OnClick(R.id.btn_cerrar)
    public void onViewClicked() {
        listener.finshedDialogWithError();
        //Create AdapterExample
        dismiss();
    }

    @Override
    public void onDestroyView() {
        if (fragmentDialog != null) fragmentDialog.cancel();
        super.onDestroyView();
    }
}
