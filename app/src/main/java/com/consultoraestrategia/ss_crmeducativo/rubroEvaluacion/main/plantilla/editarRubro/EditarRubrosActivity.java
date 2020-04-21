package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.constraintlayout.widget.Group;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
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
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.dataSource.EditarRubrosRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.dataSource.local.EditarRubrosLocalData;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.GuardarCambios;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.ObtenerDatosFormaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.ObtenerDatosNivelLogro;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.ObtenerDatosTipoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.ObtenerTipoFormula;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.ObtenerValorRedondeo;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.TipoEvaluacionLista;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.TipoFormaLista;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.TipoNotaLista;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view.FragmentAbstract;
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

public class EditarRubrosActivity extends BaseActivity<EditarRubrosView, EditarRubrosPresenter> implements EditarRubrosView {

    public static final String TAG = EditarRubrosActivity.class.getSimpleName();

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
    @BindView(R.id.edtTipoFormula)
    EditText edtTipoFormula;
    @BindView(R.id.edtTipoValorRedondeado)
    EditText edtTipoValorRedondeado;
    @BindView(R.id.group_rub_formula)
    Group groupRubFormula;
    @BindView(R.id.group_rub_simple)
    Group groupRubSimple;
    private TipoNotaListAdapter tipoNotaAdapter;


    public static Intent iniciarEditarRubBrosdActividad(Context context, CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, int programaEducativoId) {
        Bundle args = new Bundle();
        args.putParcelable(FragmentAbstract.EXTRA_CAPACIDAD, Parcels.wrap(capacidadUi));
        args.putParcelable(FragmentAbstract.EXTRA_RUBRO, Parcels.wrap(rubroProcesoUi));
        args.putInt(FragmentAbstract.EXTRA_PROGRAMA_EDUCATIVO, programaEducativoId);
        Intent intent = new Intent(context, EditarRubrosActivity.class);
        intent.putExtras(args);
        return intent;
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
    protected EditarRubrosPresenter getPresenter() {
        EditarRubrosRepository editarRubrosRepository = new EditarRubrosRepository(new EditarRubrosLocalData(
                InjectorUtils.provideRubroProcesoDao(),
                InjectorUtils.provideTiposDao(),
                InjectorUtils.provideTipoEvaluacionDao(),
                InjectorUtils.provideTipoNotaDao()
        ));

        TipoNotaListRepository repositorytwo = new TipoNotaListRepository(new TipoNotaListLocalDataSource(InjectorUtils.provideTipoNotaDao()));

        return new EditarRubrosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new TipoEvaluacionLista(editarRubrosRepository),
                new TipoFormaLista(editarRubrosRepository),
                new TipoNotaLista(editarRubrosRepository),
                new ObtenerDatosFormaEvaluacion(editarRubrosRepository),
                new ObtenerDatosNivelLogro(editarRubrosRepository),
                new ObtenerDatosTipoEvaluacion(editarRubrosRepository),
                new GuardarCambios(editarRubrosRepository),
                new GetTipoNota(repositorytwo),
                new ObtenerTipoFormula(editarRubrosRepository),
                new ObtenerValorRedondeo(editarRubrosRepository));
    }


    @Override
    protected EditarRubrosView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_layout_editar_rubros_proceso);
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
    public void onSuccessMensaje(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, int programaEducativoId) {
        Bundle args = new Bundle();
        Intent intent = new Intent();
        intent.putExtra(FragmentAbstract.TIPO, FragmentAbstract.TIPO_UPDATE_RUBRO_EVALUACION_FORMULA);
        args.putParcelable(FragmentAbstract.EXTRA_CAPACIDAD, Parcels.wrap(capacidadUi));
        args.putParcelable(FragmentAbstract.EXTRA_RUBRO, Parcels.wrap(rubroProcesoUi));
        intent.putExtras(args);
        getActivity().setResult(AppCompatActivity.RESULT_OK, intent);
        CallService.jobServiceExportarTipos(getActivity(), TipoExportacion.RUBROEVALUACION);
        SimpleSyncIntenService.start(this, programaEducativoId);
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
    public void mostrarCajaTextoTipoFormula(String nombre) {
        edtTipoFormula.setText(nombre);
    }

    @Override
    public void mostrarCajaTextoValorFormula(String nombre) {
        edtTipoValorRedondeado.setText(nombre);
    }

    @Override
    public void changeRubroFormula() {
        groupRubFormula.setVisibility(View.VISIBLE);
        groupRubSimple.setVisibility(View.GONE);
    }

    @Override
    public void changeRubSimple() {
        groupRubFormula.setVisibility(View.GONE);
        groupRubSimple.setVisibility(View.VISIBLE);
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
