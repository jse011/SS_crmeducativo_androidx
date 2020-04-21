package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.CreateMensPrePresenter;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.CreateMensPrePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.CreateMensPreView;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source.CreateMensPreRepository;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source.local.CreateMensPreLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.data.source.remote.CreateMensPreRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.domain.usecases.CreateMensajePredeterminadoUseCase;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.intitiesUI.MensajePredeterminadoUI;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by irvinmarin on 01/10/2018.
 */

public class CreateMensPredeActivity extends BaseActivity<CreateMensPreView, CreateMensPrePresenter> implements CreateMensPreView {


    private static final String TAG = CreateMensPredeActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.txtOpcionAlcance)
    TextInputEditText txtOpcionAlcance;
    @BindView(R.id.content_editTextAlcance)
    TextInputLayout contentEditTextAlcance;
    @BindView(R.id.btnSelectorAlcance)
    ImageButton btnSelectorAlcance;
    @BindView(R.id.contentAlcance)
    ConstraintLayout contentAlcance;
    @BindView(R.id.txtOpcionObjetivo)
    TextInputEditText txtOpcionObjetivo;
    @BindView(R.id.content_editTextObjetivo)
    TextInputLayout contentEditTextObjetivo;
    @BindView(R.id.btnSelectorObjetivo)
    ImageButton btnSelectorObjetivo;
    @BindView(R.id.contentObjetivo)
    ConstraintLayout contentObjetivo;
    @BindView(R.id.ContentSpinners)
    LinearLayout ContentSpinners;
    @BindView(R.id.txtAsuntoMensajePred)
    TextInputEditText txtAsuntoMensajePred;
    @BindView(R.id.contentAsunto)
    TextInputLayout contentAsunto;
    @BindView(R.id.txtCabeceraMensajePred)
    TextInputEditText txtCabeceraMensajePred;
    @BindView(R.id.contentCabecera)
    TextInputLayout contentCabecera;
    @BindView(R.id.txtPresentacionMensajePred)
    TextInputEditText txtPresentacionMensajePred;
    @BindView(R.id.contentPresentacion)
    TextInputLayout contentPresentacion;
    @BindView(R.id.txtCuerpoMensajePred)
    TextInputEditText txtCuerpoMensajePred;
    @BindView(R.id.contentCuerpo)
    TextInputLayout contentCuerpo;
    @BindView(R.id.txtDespedidaMensajePred)
    TextInputEditText txtDespedidaMensajePred;
    @BindView(R.id.contentDespedida)
    TextInputLayout contentDespedida;
    @BindView(R.id.progress)
    ProgressBar progress;

    public static final String EXTRA_MENSAJE_PRED_UI = "mensaje_pred_ui";

    public static void launchActivity(Context context, MensajePredeterminadoUI mensajePredeterminadoUI, int idIntencionSelected) {
        Intent intent = new Intent(context, CreateMensPredeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_MENSAJE_PRED_UI, (Serializable) mensajePredeterminadoUI);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected CreateMensPrePresenter getPresenter() {

        CreateMensPreRepository createMensPreRepository = CreateMensPreRepository.getInstance(
                new CreateMensPreLocalDataSource(),
                new CreateMensPreRemoteDataSource());

        return new CreateMensPrePresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(), new CreateMensajePredeterminadoUseCase(createMensPreRepository)
        );
    }

    @Override
    protected CreateMensPreView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.create_mensaje_predeterminado_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        int id = item.getItemId();
        presenter.onSomeViewClick(id);
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected ViewGroup getRootLayout() {
        return toolbar;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progress;
    }

    @OnClick({R.id.btnSelectorAlcance, R.id.btnSelectorObjetivo})
    public void onViewClicked(View view) {
        presenter.onSomeViewClick(view.getId());
    }

    @Override
    public void setAlcanceSelected(String opcionSelected) {
        txtOpcionAlcance.setText(opcionSelected);
    }

    @Override
    public void setObjetivoSelected(String opcionSelected) {
        txtOpcionObjetivo.setText(opcionSelected);
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void getImputs() {
        presenter.setImputs(
                txtAsuntoMensajePred,
                txtPresentacionMensajePred,
                txtCabeceraMensajePred,
                txtCuerpoMensajePred,
                txtDespedidaMensajePred
        );
    }

    @Override
    public void setDataToEditImputs(String asuntoMensaje, String cabeceraMensaje, String presentacionMensaje, String cuerpoMensaje, String despedidaMensaje, String tipoMensaje) {
        txtAsuntoMensajePred.setText(asuntoMensaje);
        txtCabeceraMensajePred.setText(cabeceraMensaje);
        txtPresentacionMensajePred.setText(presentacionMensaje);
        txtCuerpoMensajePred.setText(cuerpoMensaje);
        txtDespedidaMensajePred.setText(despedidaMensaje);
        txtOpcionObjetivo.setText(tipoMensaje);
    }

    @Override
    protected void onDestroy() {
        CallService.jobServiceExportarTipos(this, TipoExportacion.MENSAJE);
        SimpleSyncIntenService.start(this, 0);
        super.onDestroy();
    }
}
