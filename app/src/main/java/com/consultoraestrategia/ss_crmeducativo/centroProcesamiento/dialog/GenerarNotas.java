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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.CentProcesoPresenter;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view.GenerarNotasDialogView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GenerarNotas extends DialogFragment implements GenerarNotasDialogView {
    private Unbinder unbinder;
    private CentProcesoPresenter presenter;
    @BindView(R.id.conten_alerta_asignar_notas)
    View contenAlertaAsignarNotas;
    @BindView(R.id.conten_alerta_habilitado)
    View contenAlertahabilitado;
    @BindView(R.id.cont_titulo_bloqueo)
    View contTituloBloqueo;
    @BindView(R.id.mensaje_alerta_habilitado)
    TextView mensajeAlertaHabilitado;
    @BindView(R.id.icon_bloqueo)
    ImageView iconBloqueo;
    @BindView(R.id.cont_titulo_cerrado)
    View contTituloCerrado;
    @BindView(R.id.mensaje_alerta_bloqueado)
    TextView mensajeAlertaBloqueado;
    @BindView(R.id.conten_promediar)
    View contenPromediar;

    @BindView(R.id.mensaje_alerta)
    TextView mensajeAlerta;
    @BindView(R.id.btn_aceptar)
    Button btnAceptar;
    @BindView(R.id.progress)
    LottieAnimationView progress;
    @BindView(R.id.btn_cancelar)
    Button btnCancelar;

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

        View view = inflater.inflate(R.layout.fragment_generador_resultado, null);

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
        contenPromediar.setVisibility(View.GONE);
        if(noAsignado){
            contenAlertaAsignarNotas.setVisibility(View.VISIBLE);
            mensajeAlerta.setText("      No se asignaron las capacidades a evaluar en el "+nombrePeriodo+".");
        }else {
            contenAlertaAsignarNotas.setVisibility(View.GONE);
        }

        if(habilitado){
            contenAlertahabilitado.setVisibility(View.VISIBLE);
            contTituloBloqueo.setVisibility(View.VISIBLE);
            mensajeAlertaHabilitado.setText("      El "+ nombrePeriodo + " esta bloqueado.");

        }else {
            contenAlertahabilitado.setVisibility(View.GONE);
            contTituloBloqueo.setVisibility(View.GONE);
        }

        if(cerrado){
            contenAlertahabilitado.setVisibility(View.VISIBLE);
            contTituloCerrado.setVisibility(View.VISIBLE);
            mensajeAlertaBloqueado.setText("      Su evaluación del " + nombrePeriodo + " esta cerrado.");
            iconBloqueo.setVisibility(View.VISIBLE);



        }else {
            contTituloCerrado.setVisibility(View.GONE);
            iconBloqueo.setVisibility(View.GONE);
        }

        if(!habilitado&&!cerrado){
            contenAlertahabilitado.setVisibility(View.GONE);
        }

        btnAceptar.setVisibility(View.GONE);

    }

    @Override
    public void hideAlertaAsignarNotas() {
        contenPromediar.setVisibility(View.VISIBLE);
        contenAlertaAsignarNotas.setVisibility(View.GONE);
        contenAlertahabilitado.setVisibility(View.GONE);
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
    public void hideButtonAction() {
        btnAceptar.setVisibility(View.GONE);
        btnCancelar.setEnabled(false);
        btnCancelar.setTextColor(ContextCompat.getColor(btnCancelar.getContext(), R.color.md_grey_400));
    }

    @Override
    public void closeDialog() {
        dismiss();
    }

    @OnClick(R.id.btn_cancelar)
    public void onViewClicked() {
        dismiss();
    }

    @OnClick(R.id.btn_aceptar)
    public void onViewGenerar() {
        presenter.onClickGenerarNotas();
    }
}
