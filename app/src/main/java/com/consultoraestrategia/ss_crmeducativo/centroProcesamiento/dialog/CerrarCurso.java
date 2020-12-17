package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.dialog;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.CentProcesoPresenter;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view.CerrarCursoDialogView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CerrarCurso extends DialogFragment implements CerrarCursoDialogView {
    @BindView(R.id.mensaje_alerta)
    TextView mensajeAlerta;
    @BindView(R.id.constraintLayout21)
    ConstraintLayout constraintLayout21;
    @BindView(R.id.conten_alerta_asignar_notas)
    LinearLayout contenAlertaAsignarNotas;
    @BindView(R.id.constraintLayout26)
    ConstraintLayout constraintLayout26;
    @BindView(R.id.conten_cerrar_evaluacion)
    LinearLayout contenCerrarEvaluacion;
    @BindView(R.id.mensaje_alerta_habilitado)
    TextView mensajeAlertaHabilitado;
    @BindView(R.id.cont_titulo_bloqueo)
    ConstraintLayout contTituloBloqueo;
    @BindView(R.id.mensaje_alerta_bloqueado)
    TextView mensajeAlertaBloqueado;
    @BindView(R.id.cont_titulo_cerrado)
    ConstraintLayout contTituloCerrado;
    @BindView(R.id.constraintLayout23)
    ConstraintLayout constraintLayout23;
    @BindView(R.id.icon_bloqueo)
    ImageView iconBloqueo;
    @BindView(R.id.conten_alerta_habilitado)
    LinearLayout contenAlertaHabilitado;
    @BindView(R.id.btn_cancelar)
    Button btnCancelar;
    @BindView(R.id.progress)
    LottieAnimationView progress;
    @BindView(R.id.btn_aceptar)
    Button btnAceptar;
    @BindView(R.id.txt_titulo)
    TextView txtTitulo;
    private Unbinder unbinder;
    private CentProcesoPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getDialog().requestWindowFeature(STYLE_NO_TITLE);

        }


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createSimpleDialog();
    }

    /**
     * Crea un diálogo de alerta sencillo
     *
     * @return Nuevo diálogo
     */
    public AlertDialog createSimpleDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_cerrar_curso_procesamiento, null);

        unbinder = ButterKnife.bind(this, view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        //alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        return alertDialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(CentProcesoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showAlertaAsignarNotas(String nombrePeriodo, boolean noAsignado, boolean habilitado, boolean cerrado) {
        contenCerrarEvaluacion.setVisibility(View.GONE);
        if (noAsignado) {
            contenAlertaAsignarNotas.setVisibility(View.VISIBLE);
            mensajeAlerta.setText("      No se asignaron las capacidades a evaluar en el " + nombrePeriodo + ".");
        } else {
            contenAlertaAsignarNotas.setVisibility(View.GONE);
        }

        if (habilitado) {
            contenAlertaHabilitado.setVisibility(View.VISIBLE);
            contTituloBloqueo.setVisibility(View.VISIBLE);
            mensajeAlertaHabilitado.setText("      El " + nombrePeriodo + " esta bloqueado.");

        } else {
            contenAlertaHabilitado.setVisibility(View.GONE);
            contTituloBloqueo.setVisibility(View.GONE);
        }

        if (cerrado) {
            contenAlertaHabilitado.setVisibility(View.VISIBLE);
            contTituloCerrado.setVisibility(View.VISIBLE);
            mensajeAlertaBloqueado.setText("      Su evaluación del " + nombrePeriodo + " esta cerrado.");
            iconBloqueo.setVisibility(View.VISIBLE);


        } else {
            contTituloCerrado.setVisibility(View.GONE);
            iconBloqueo.setVisibility(View.GONE);
        }

        if (!habilitado && !cerrado) {
            contenAlertaHabilitado.setVisibility(View.GONE);
        }

        btnAceptar.setVisibility(View.GONE);
    }

    @Override
    public void hideAlertaAsignarNotas() {
        contenCerrarEvaluacion.setVisibility(View.VISIBLE);
        contenAlertaAsignarNotas.setVisibility(View.GONE);
        contenAlertaHabilitado.setVisibility(View.GONE);
        btnAceptar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progress.setAnimation("progress_portal_alumno.json");
        progress.setRepeatCount(ValueAnimator.INFINITE);
        progress.playAnimation();
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
        progress.cancelAnimation();
    }

    @Override
    public void showButtonAction() {
        btnAceptar.setVisibility(View.VISIBLE);
        btnCancelar.setEnabled(true);
        btnCancelar.setTextColor(ContextCompat.getColor(btnCancelar.getContext(), R.color.md_grey_700));
    }

    @Override
    public void setTitulo(String tipoName) {
        txtTitulo.setText("Cerrar la evaluación del "+tipoName);
    }

    @Override
    public void hideButtonAction() {
        btnAceptar.setVisibility(View.GONE);
        btnCancelar.setEnabled(false);
        btnCancelar.setTextColor(ContextCompat.getColor(btnCancelar.getContext(), R.color.md_grey_400));
    }

    @Override
    public void closeDialog() {
        dismiss();
    }

    @OnClick({R.id.btn_cancelar, R.id.btn_aceptar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancelar:
                dismiss();
                break;
            case R.id.btn_aceptar:
                presenter.onClickCerrarEvaluacion();
                break;
        }
    }
}
