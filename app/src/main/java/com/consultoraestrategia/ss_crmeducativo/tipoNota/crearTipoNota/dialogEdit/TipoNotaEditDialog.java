package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.dialogEdit;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.dialogEdit.listener.ValoresListenerDialog;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.ValoresUi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by kike on 02/04/2018.
 */

public class TipoNotaEditDialog extends DialogFragment implements TipoNotaEditView {
    Unbinder unbinder;
    @BindView(R.id.checkBox)
    CheckBox checkBoxIntervalo;
    @BindView(R.id.groupItemIntervalo)
    View groupItemIntervalo;
    @BindView(R.id.editTextTitulo)
    EditText editTextTitulo;
    @BindView(R.id.editTextDescripcion)
    EditText editTextDescripcion;
    @BindView(R.id.editValorNumerico)
    EditText editValorNumerico;
    @BindView(R.id.editValorMinimo)
    EditText editValorMinimo;
    @BindView(R.id.editValorMaximo)
    EditText editValorMaximo;

    TipoNotaEditPresenter presenter;

    ValoresListenerDialog listenerDialog;

    //public static TipoNotaEditDialog newInstance(RubroProcesoUi rubroEvaluacionProcesoUi) {
    public static TipoNotaEditDialog newInstance() {
        TipoNotaEditDialog fragment = new TipoNotaEditDialog();
        Bundle args = new Bundle();
        //args.putParcelable("rubroEvaluacionProcesoUi", Parcels.wrap(rubroEvaluacionProcesoUi));
        fragment.setArguments(args);
        //fragment.setArguments(bundle);
        return fragment;
    }

    public TipoNotaEditDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tiponota_edit_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        initPresenter();
        initViewCheckBox();
        return view;
    }

    private void initPresenter() {
        presenter = new TipoNotaEditPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()));
        setPresenter(presenter);
    }

    private void initViewCheckBox() {
        checkBoxIntervalo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.onCheckBox(isChecked);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        this.getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT );
        this.getDialog().getWindow();
    }

    @Override
    public void setPresenter(TipoNotaEditPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();

    }

    @Override
    public void mostrarCajatexto() {
        groupItemIntervalo.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarCajaTexto() {
        groupItemIntervalo.setVisibility(View.GONE);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Snackbar.make(groupItemIntervalo, mensaje, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onAceptarSuccessDialog(ValoresUi valoresUi) {
        listenerDialog.onAceptarEditDialog(valoresUi);
    }

    @OnClick({R.id.btnAceptar, R.id.btnCancelar})
    public void onClickButton(View view) {
        switch (view.getId()) {
            case R.id.btnAceptar:
                String titulo = editTextTitulo.getText().toString().trim();
                String descripcion = editTextDescripcion.getText().toString();
                String valorNumerico = editValorNumerico.getText().toString().trim();
                String valorMinimo = editValorMinimo.getText().toString().trim();
                String valorMaximo = editValorMaximo.getText().toString().trim();
                ValoresUi valoresUi = new ValoresUi();
                valoresUi.setTitulo(titulo);
                valoresUi.setDescripcion(descripcion);
                valoresUi.setValorNumerico(valorNumerico);
                valoresUi.setValorMinimo(valorMinimo);
                valoresUi.setValorMaximo(valorMaximo);
                presenter.onbtnAceptar(valoresUi);
                break;
            case R.id.btnCancelar:
                dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listenerDialog = (ValoresListenerDialog) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
