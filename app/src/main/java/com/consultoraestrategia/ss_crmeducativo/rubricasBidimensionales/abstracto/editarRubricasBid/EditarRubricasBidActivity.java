package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.RubricasAbstractFragment;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource.EditarRubricaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource.local.EditarRubricaLocalData;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase.GuardarCambios;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase.ObtenerDatosFormaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase.ObtenerDatosNivelLogro;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase.ObtenerDatosTipoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase.TipoEvaluacionLista;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase.TipoFormaLista;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase.TipoNotaLista;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.adapters.TipoNotaListAdapter;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.data.source.TipoNotaListRepository;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.data.source.local.TipoNotaListLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase.GetTipoNota;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kike on 08/06/2018.
 */

public class EditarRubricasBidActivity extends BaseActivity<EditarRubricaBidView, EditarRubricaBidPresenter> implements EditarRubricaBidView {
    public static final String TAG_EDITAR_RUBRICA_BID = EditarRubricasBidActivity.class.getSimpleName();
    public static final String RUBRICA_BID = "rubricaBid";
    public static final String EXTRAS_PROGRAMA_EDUCATIVO_ID = "programaEducativoId";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textViewTitulo)
    EditText editTextTituloRubrica;
    @BindView(R.id.textViewSubtitulo)
    EditText editTextSubtituloRubrica;
    @BindView(R.id.edtTipoEvaluacion)
    EditText editTextTipoEvaluacion;
    @BindView(R.id.edtTipoFormaEvaluacion)
    EditText editTextFormaEvaluacion;
    @BindView(R.id.tilTipoNota)
    RecyclerView tilTipoNota;
    @BindView(R.id.btnTipoNota)
    ImageView btnTipoNota;
    private TipoNotaListAdapter tipoNotaAdapter;


    public static Intent iniciarEditarRubBidActividad(Context context, RubBidUi rubBidUi) {
        Bundle args = new Bundle();
        args.putParcelable(RUBRICA_BID, Parcels.wrap(rubBidUi));
        Intent intent = new Intent(context, EditarRubricasBidActivity.class);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected String getTag() {
        return TAG_EDITAR_RUBRICA_BID;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected EditarRubricaBidPresenter getPresenter() {
        EditarRubricaRepository editarRubricaRepository = new EditarRubricaRepository(new EditarRubricaLocalData(
                InjectorUtils.provideRubroProcesoDao(),
                InjectorUtils.provideTiposDao(),
                InjectorUtils.provideTipoEvaluacionDao(),
                InjectorUtils.provideTipoNotaDao()
        ));
        TipoNotaListRepository repositorytwo = new TipoNotaListRepository(new TipoNotaListLocalDataSource(InjectorUtils.provideTipoNotaDao()));
        return new EditarRubricaBidPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new TipoEvaluacionLista(editarRubricaRepository),
                new TipoFormaLista(editarRubricaRepository),
                new TipoNotaLista(editarRubricaRepository),
                new ObtenerDatosFormaEvaluacion(editarRubricaRepository),
                new ObtenerDatosNivelLogro(editarRubricaRepository),
                new ObtenerDatosTipoEvaluacion(editarRubricaRepository),
                new GuardarCambios(editarRubricaRepository),
                new GetTipoNota(repositorytwo));
    }

    @Override
    protected EditarRubricaBidView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_layout_editar_rubrica_bid);
        ButterKnife.bind(this);
        initToolbar();
        setupAdapterTipoNota();
    }

    private void setupAdapterTipoNota() {
        tipoNotaAdapter = new TipoNotaListAdapter(new ArrayList<TipoNotaUi>(), null);
        tilTipoNota.setAdapter(tipoNotaAdapter);
        tilTipoNota.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @OnClick({R.id.btnTipoEvaluacion, R.id.btnTipoFormaEvaluacion})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTipoEvaluacion:
                presenter.onTipoEvaluacionClicked();
                break;
            case R.id.btnTipoFormaEvaluacion:
                //presenter.onTipoFormaEvaluacionClicked();
                break;
        }

    }


    @Override
    public void mostrarTitulos(String tituloRubrica, String alias) {
        editTextTituloRubrica.setText(tituloRubrica);
        editTextSubtituloRubrica.setText(alias);
    }

    @Override
    public void mostrarTipoFormaSelected(String nombre) {
        editTextFormaEvaluacion.setText(nombre);
    }

    @Override
    public void mostrarTipoEvaluacion(String nombre) {
        editTextTipoEvaluacion.setText(nombre);
    }

    @Override
    public void mostrarCajaTextoTipoEvaluacion(String nombre) {
        editTextTipoEvaluacion.setText(nombre);
    }

    @Override
    public void mostrarCajaTextoTipoNota(String nombre) {

    }

    @Override
    public void mostrarCajaTextoTipoFormaEvaluacion(String nombre) {
        editTextFormaEvaluacion.setText(nombre);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Snackbar.make(editTextFormaEvaluacion, mensaje, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessMensaje(RubBidUi rubBidUi, int programaEducativoId) {
        Bundle args = new Bundle();
        Intent intent = new Intent();
        intent.putExtra(RubricasAbstractFragment.TIPO_RUBRICA, RubricasAbstractFragment.ACTUALIZAR_RUBRICAS_BIDIMENSIONAL);
        args.putParcelable(RUBRICA_BID, Parcels.wrap(rubBidUi));
        intent.putExtras(args);
        getActivity().setResult(AppCompatActivity.RESULT_OK, intent);
        SimpleSyncIntenService.start(getBaseContext(), programaEducativoId);
        CallService.jobServiceExportarTipos(getBaseContext(), TipoExportacion.RUBROEVALUACION);
        CMRE.saveNotifyChangeDataBase(this);
        finish();
    }

    @Override
    public void showTipoNotaSelected(TipoNotaUi tipoNotaUi) {
        List<TipoNotaUi> tipoNotaUiList = new ArrayList<>();
        tipoNotaUiList.add(tipoNotaUi);
        tipoNotaAdapter.setTipoNotaUiList(tipoNotaUiList);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_editar:
                String titulo = editTextTituloRubrica.getText().toString();
                String alias = editTextSubtituloRubrica.getText().toString();
                presenter.onGuardarCambios(titulo, alias);
                break;
            default:
                Log.d(TAG_EDITAR_RUBRICA_BID, "default ");
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editar, menu);
        return true;
    }

}
