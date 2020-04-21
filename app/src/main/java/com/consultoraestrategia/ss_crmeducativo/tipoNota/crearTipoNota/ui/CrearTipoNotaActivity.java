package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;


import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.CrearTipoNotaPresenter;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.CrearTipoNotaPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.adapter.TipoSelectorAdapter;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.data.source.CrearTipoNotaRepository;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.data.source.local.CrearTipoNotaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.data.source.remote.CrearTipoNotaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.dialogEdit.TipoNotaEditDialog;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.dialogEdit.listener.ValoresListenerDialog;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.domainUseCase.GetTipoEscalaEvaluacionList;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.domainUseCase.GetTipoSelectorList;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.listener.ValoresListener;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.ValoresUi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Intent.ACTION_SEARCH;

/**
 * Created by CCIE on 05/03/2018.
 */

public class CrearTipoNotaActivity extends BaseActivity<CrearTipoNotaView, CrearTipoNotaPresenter> implements CrearTipoNotaView
        , ValoresListener
        , ValoresListenerDialog {

    private static String CREAR_TIPO_NOTA_TAG = CrearTipoNotaActivity.class.getSimpleName();
    @BindView(R.id.recycler)
    RecyclerView reciclador;
    @BindView(R.id.edtTipoSelector)
    TextInputEditText editTextTipoSelector;
    @BindView(R.id.edtTipoEscalaEvaluacion)
    TextInputEditText editTextTipoEscalaEvaluacion;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.checkBox)
    CheckBox checkBoxIntervalo;
    @BindView(R.id.group_scrolling_text)
    View viewTextCheckBox;
    @BindView(R.id.viewGroupSelValores)
    View viewGroupSelValores;
    @BindView(R.id.viewGroupSelNumerico)
    View viewGroupSelNumerico;

    @BindView(R.id.textInputLayoutValorDefecto)
    TextInputLayout textInputLayoutValorDefecto;
    @BindView(R.id.textInputLayoutValorMaximo)
    TextInputLayout textInputLayoutValorMaximo;
    /*@BindView(R.id.spi_tipo_selector)
    Spinner onSelectTipoSelector;
    @BindView(R.id.spi_tipo_escala_evaluacion)
    Spinner onSelectTipoEscalaEvaluacion;
    @BindView(R.id.container2)
    ConstraintLayout constraintLayoutDetalles;
    @BindView(R.id.recycler)
    RecyclerView reciclador;
    @BindView(R.id.floatingActionButton)
    TextView buttonAdd;
    @BindView(R.id.checkBox)
    CheckBox checkBoxIntervalo;
    @BindView(R.id.txtValorMinimo)
    TextView textViewValorMinimo;
    @BindView(R.id.textMaximo)
    TextView textViewValorMaximo;
    CrearTipoNotaPresenter presenter;

    private TipoSelectorAdapter tipoSelectorAdapter;*/
    private TipoSelectorAdapter tipoSelectorAdapter;
    public static final String INT_USUARIO_ID = "usuarioId";

    public static Intent starIntentCrearTipoNota(Context context, Class<?> cls, int usuarioId) {
        Intent intent = new Intent(ACTION_SEARCH, null, context, cls);
        intent.putExtra(INT_USUARIO_ID, usuarioId);
        return intent;
    }

    @Override
    protected String getTag() {
        return CREAR_TIPO_NOTA_TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected CrearTipoNotaPresenter getPresenter() {
        CrearTipoNotaRepository repository = new CrearTipoNotaRepository(new CrearTipoNotaLocalDataSource(), new CrearTipoNotaRemoteDataSource());
        this.presenter = new CrearTipoNotaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources()
                , new GetTipoSelectorList(repository)
                , new GetTipoEscalaEvaluacionList(repository));
        return presenter;
    }

    @Override
    protected CrearTipoNotaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_crear_tipo_nota);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initVistas();
    }

    private void initVistas() {
        setSupportActionBar(toolbar);
        initCheckbox();
        reciclador.setLayoutManager(new LinearLayoutManager(this));
        tipoSelectorAdapter = new TipoSelectorAdapter(new ArrayList<ValoresUi>(), this);
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(tipoSelectorAdapter);
        TipoNotaUi tipoNotaUi = new TipoNotaUi();
        ValoresUi valoresUi = new ValoresUi();
        valoresUi.setTipoNotaUi(tipoNotaUi);
        valoresUi.setTitulo("");
        tipoSelectorAdapter.agregarItem(valoresUi);

    }


    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    private void initCheckbox() {
        checkBoxIntervalo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.onCheckBox(isChecked);
            }
        });
    }


    @Override
    public void hideCheckBoxIntervalo() {
        // checkBoxIntervalo.setVisibility(View.GONE);
    }

    @Override
    public void showValorMinMax() {
        viewTextCheckBox.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideValorMinMax() {
        viewTextCheckBox.setVisibility(View.GONE);
    }

    @Override
    public void showTipoSelectorSelected(String tipoSelectorUi) {
        editTextTipoSelector.setText(tipoSelectorUi);

    }

    @Override
    public void showTipoEscalaEvaluacionSelected(String tipoEscalaEvaluacionUi) {
        editTextTipoEscalaEvaluacion.setText(tipoEscalaEvaluacionUi);
    }

    @Override
    public void showTipoSelector() {
        viewGroupSelValores.setVisibility(View.VISIBLE);
        viewGroupSelNumerico.setVisibility(View.GONE);
        textInputLayoutValorDefecto.setVisibility(View.GONE);
        textInputLayoutValorMaximo.setVisibility(View.GONE);
    }

    @Override
    public void showSelectorIconos() {
        viewGroupSelValores.setVisibility(View.VISIBLE);
        viewGroupSelNumerico.setVisibility(View.GONE);
        textInputLayoutValorDefecto.setVisibility(View.GONE);
        textInputLayoutValorMaximo.setVisibility(View.GONE);
    }

    @Override
    public void showValorNumerico() {
        viewGroupSelValores.setVisibility(View.GONE);
        viewGroupSelNumerico.setVisibility(View.GONE);
        textInputLayoutValorDefecto.setVisibility(View.VISIBLE);
        textInputLayoutValorMaximo.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSelectorNumerico() {
        viewGroupSelValores.setVisibility(View.GONE);
        viewGroupSelNumerico.setVisibility(View.VISIBLE);
        textInputLayoutValorDefecto.setVisibility(View.GONE);
        textInputLayoutValorMaximo.setVisibility(View.GONE);
    }


    @OnClick({R.id.btnTipoSelector, R.id.btnTipoEscalaEvaluacion, R.id.floatingActionButton, R.id.buttonAceptarDetalles})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnTipoSelector:
                presenter.onBnTipoSelectorClicked();
                break;
            case R.id.btnTipoEscalaEvaluacion:
                presenter.onBnTipoEscalaEvaluacionClicked();
                break;
            case R.id.floatingActionButton:
                ValoresUi valoresUi = new ValoresUi();
                valoresUi.setTitulo("");
                tipoSelectorAdapter.agregarItem(valoresUi);
                Log.d(CREAR_TIPO_NOTA_TAG, "onClickFabBoton");
                break;
            case R.id.buttonAceptarDetalles:
                int conteoDetalles = tipoSelectorAdapter.getValoresUiList().size();
                Log.d(CREAR_TIPO_NOTA_TAG, "ACEPTARDETALLES: " + conteoDetalles + "");
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void itemValores(ValoresUi valoresUi) {
        Log.d(CREAR_TIPO_NOTA_TAG, "itemValores");
        TipoNotaEditDialog tipoNotaEditDialog = TipoNotaEditDialog.newInstance();
        tipoNotaEditDialog.show(getSupportFragmentManager(), "TipoNotaEditDialog");
    }


    @Override
    public void onAceptarEditDialog(ValoresUi valoresUi) {
        Log.d(CREAR_TIPO_NOTA_TAG, "onAceptarEditDialog : " + valoresUi.getTitulo());
    }
}
