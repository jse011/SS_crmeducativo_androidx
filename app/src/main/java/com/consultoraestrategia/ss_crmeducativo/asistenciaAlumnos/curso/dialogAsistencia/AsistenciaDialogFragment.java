package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.dialogAsistencia;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.ui.AsistenciaActivity;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.bundle.AsistenciaBundle;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoRepository;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase.GenerarAsistencia;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class AsistenciaDialogFragment extends BaseDialogFragment<AsistenciaDialogView, AsistenciaDialogPresenter, AsistenciaDialogListener> implements AsistenciaDialogView {


    @BindView(R.id.titulo)
    TextView textTitle;
    @BindView(R.id.bttn_negative)
    Button bttnNegative;
    @BindView(R.id.bttn_positive)
    Button bttnPositive;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.root)
    ConstraintLayout root;

    private static String colorCurso;

    public static final String ARG_ASISTENCIA_UI = "AsistenciaDialogFragment.AsistenciaUi";

    public static AsistenciaDialogFragment newInstance(AsistenciaBundle asistenciaBundle) {
        colorCurso = asistenciaBundle.getColor();
        AsistenciaDialogFragment frag = new AsistenciaDialogFragment();
        frag.setArguments(asistenciaBundle.getBundle());
        frag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        return frag;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDialog();
    }

    private void initDialog() {
        textTitle.setText("¿Desea generar la asistencia del curso?");
        try {
            if (colorCurso != null)
                textTitle.setTextColor(Color.parseColor(colorCurso));
            view.setBackgroundColor(Color.parseColor(colorCurso));
            bttnNegative.setTextColor(Color.parseColor(colorCurso));
            bttnPositive.setTextColor(Color.parseColor(colorCurso));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected AsistenciaDialogPresenter getPresenter() {
        return new AsistenciaDialogPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GenerarAsistencia(
                        new AsistenciaAlumnoRepository(
                                new AsistenciaAlumnoLocalDataSource(
                                        InjectorUtils.provideAsistenciaSesionAlumnoDao(),
                                        InjectorUtils.provideTipoNotaDao(), InjectorUtils.provideValorTipoNotaDao(), InjectorUtils.provideParametrosDisenioDao())
                        )
                ));
    }

    @Override
    protected String getLogTag() {
        return AsistenciaDialogFragment.class.getSimpleName();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(getLogTag(), "onResume");
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    protected AsistenciaDialogView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_asistencia_generado, container, false);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    @OnClick({R.id.bttn_negative, R.id.bttn_positive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bttn_negative:
                close();
                break;
            case R.id.bttn_positive:
                presenter.onCLickGenerarAsistencia();
                break;
        }
    }

    @Override
    public void onClickAsistencia(AsistenciaUi asistenciaUi) {
        Intent intent = AsistenciaActivity.getAsistenciaActivity(getContext(), new AsistenciaBundle(asistenciaUi));
        startActivity(intent);
    }

    @Override
    public void close() {
        dismiss();
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }
}
