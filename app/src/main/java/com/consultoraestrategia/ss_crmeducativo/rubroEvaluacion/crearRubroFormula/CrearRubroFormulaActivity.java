package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;

import com.consultoraestrategia.ss_crmeducativo.login2.service2.worker.SynckService;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.ValorTipoNotaAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter.DetalleRubroEvaluacionProcesoListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter.DetalleRubroProcesoAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.dialog.EditarRubroFormulaPesoFragment;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.local.RubroEvaluacionProcesoListaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.remote.RubroEvaluacionProcesoListaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.EditarFormula;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetCamposAccionRubro;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetFormulaList;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetNotaPresicion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetTipoFormulaList;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetTipoNotaDefault;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetTipoNotaList;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetTipoNotaRubrica;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetTipoRedondeoList;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.SavedRubroFormula;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.informacionValorTiponota.DialogValorTipoNota;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view.FragmentAbstract;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.tipoNotaLista.TipoNotaListActivity;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;

import static com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.CreateRubBidActivity.SELECT_LISTATIPONOTAS_REQUEST;

/**
 * Created by CCIE on 26/03/2018.
 */

public class CrearRubroFormulaActivity extends BaseActivity<CrearRubroFormulaView, CrearRubroFormulaPresenter> implements CrearRubroFormulaView,
        DetalleRubroEvaluacionProcesoListener,
        EditarRubroFormulaPesoFragment.ModificarRubroEvaluacionProcesoPesoListener {
    private final static String CREAR_RUBRO_FORMULA_TAG = CrearRubroFormulaActivity.class.getSimpleName();


    public static final String RUBROEVALUACIONID = "CrearRubroFormulaActivity.rubroEvaluacionId";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.textViewTitulo)
    EditText textViewTitulo;
    @BindView(R.id.textViewSubtitulo)
    TextInputEditText textViewSubtitulo;
    @BindView(R.id.textinputdescripcion)
    TextInputLayout textInputLayout3;
    @BindView(R.id.tilTipoNota)
    RecyclerView tilTipoNota;
    @BindView(R.id.btnTipoNota)
    ImageView btnTipoNota;
    @BindView(R.id.gdl_mid)
    Guideline gdlMid;
    @BindView(R.id.edtTipoFormula)
    TextInputEditText edtTipoFormula;
    @BindView(R.id.tilTipoFormula)
    TextInputLayout tilTipoFormula;
    @BindView(R.id.btnTipoFormula)
    ImageButton btnTipoFormula;
    @BindView(R.id.edtTipoValorRedondeado)
    TextInputEditText edtTipoValorRedondeado;
    @BindView(R.id.tilTipoValorRedondeado)
    TextInputLayout tilTipoValorRedondeado;
    @BindView(R.id.btnTipoValorRedondeado)
    ImageButton btnTipoValorRedondeado;


    @BindView(R.id.recyclerView)
    RecyclerView recyclerViewRubrosProcesosChecked;

    @BindView(R.id.textviewTotalPeso)
    TextView textViewTotalPeso;
    @BindView(R.id.textView11)
    TextView peso;
    /*@BindView(R.id.view6)
        View viewLinealCountPeso;*/
    @BindView(R.id.img_valor_numerico)
    TextView imgValorNumerico;
    @BindView(R.id.img_selector_numerico)
    TextView imgSelectorNumerico;
    @BindView(R.id.txt_titulo_tipo_nota)
    TextView txtTituloTipoNota;
    @BindView(R.id.txt_descripcion_escala)
    TextView txtDescripcionEscala;
    @BindView(R.id.txt_escala)
    TextView txtEscala;

    /*@BindView(R.id.textView8)
    TextView textView8;*/
    private DetalleRubroProcesoAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private SpotsDialog alertDialog;
    private ValorTipoNotaAdapter valorTipoNotaAdapter;

    @Override
    protected String getTag() {
        return CREAR_RUBRO_FORMULA_TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected CrearRubroFormulaPresenter getPresenter() {
        RubroEvaluacionProcesoListaRepository rubroEvaluacionProcesoListaRepository = new RubroEvaluacionProcesoListaRepository(
                new RubroEvaluacionProcesoListaLocalDataSource(),
                new RubroEvaluacionProcesoListaRemoteDataSource());

        return new CrearRubroFormulaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new GetTipoFormulaList(rubroEvaluacionProcesoListaRepository),
                new GetTipoNotaList(rubroEvaluacionProcesoListaRepository),
                new GetTipoRedondeoList(rubroEvaluacionProcesoListaRepository),
                new SavedRubroFormula(rubroEvaluacionProcesoListaRepository),
                new GetCamposAccionRubro(rubroEvaluacionProcesoListaRepository),
                new GetTipoNotaDefault(rubroEvaluacionProcesoListaRepository),
                new GetNotaPresicion(),
                new GetTipoNotaRubrica(rubroEvaluacionProcesoListaRepository),
                new GetRubroProceso(rubroEvaluacionProcesoListaRepository),
                new GetFormulaList(rubroEvaluacionProcesoListaRepository),
                new EditarFormula(rubroEvaluacionProcesoListaRepository));
    }

    @Override
    protected CrearRubroFormulaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_layout_crear_rubro_formula);
        ButterKnife.bind(this);
        setupToolbar();
        setupAdapter();
        setupDialogProgress();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //change color of status bar
            Window window = this.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#FAFAFA"));
        }
    }

    private void setupDialogProgress() {
        alertDialog = new SpotsDialog(this, R.style.SpotsDialogCrearFormula);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
    }


    private void setupAdapter() {
        gridLayoutManager = new GridLayoutManager(recyclerViewRubrosProcesosChecked.getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyclerViewRubrosProcesosChecked.setLayoutManager(gridLayoutManager);
        adapter = new DetalleRubroProcesoAdapter(new ArrayList<RubroProcesoUi>(), this, new CapacidadUi());
        adapter.setRecyclerView(recyclerViewRubrosProcesosChecked, gridLayoutManager);
        recyclerViewRubrosProcesosChecked.setHasFixedSize(true);
        recyclerViewRubrosProcesosChecked.setAdapter(adapter);

        valorTipoNotaAdapter = new ValorTipoNotaAdapter(new ArrayList<ValorTipoNotaUi>(), ValorTipoNotaAdapter.TIPO_SIMPLE);
        tilTipoNota.setAdapter(valorTipoNotaAdapter);
        tilTipoNota.setLayoutManager(new GridLayoutManager(tilTipoNota.getContext(), 6));
        tilTipoNota.setVisibility(View.VISIBLE);
        tilTipoNota.setEnabled(true);
        tilTipoNota.setNestedScrollingEnabled(false);
        tilTipoNota.setHasFixedSize(false);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return toolbar;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void showListCheckedRubros(CapacidadUi capacidadUi, List<RubroProcesoUi> listaddChecked, final boolean columnPeso) {

        try {
            adapter.setCapacidadUi(capacidadUi);
            adapter.setItems(listaddChecked);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showTipoNotaSelected(TipoNotaUi tipoNotaUi) {
        Log.d(getTag(), "showTipoNotaSelected: " + tipoNotaUi.getNombre());
        txtTituloTipoNota.setText(tipoNotaUi.getNombre());

        EscalaEvaluacionUi escalaUi = tipoNotaUi.getEscalaEvaluacionUi();
        String escala = "";
        String rango = "";
        if (escalaUi != null) {
            escala = escalaUi.getNombre();
            rango = "<b>(</b>" + escalaUi.getValorMinimo() + "  <b> - </b>" + escalaUi.getValorMaximo() + "<b>)</b>";
        }
        txtDescripcionEscala.setText(Html.fromHtml(rango));
        txtEscala.setText(escala);
        switch (tipoNotaUi.getTipo()) {
            case SELECTOR_NUMERICO:
                tilTipoNota.setVisibility(View.GONE);
                imgValorNumerico.setVisibility(View.GONE);
                imgSelectorNumerico.setVisibility(View.VISIBLE);
                valorTipoNotaAdapter.setList(new ArrayList<ValorTipoNotaUi>());

                break;
            case VALOR_NUMERICO:
                tilTipoNota.setVisibility(View.GONE);
                imgSelectorNumerico.setVisibility(View.GONE);
                imgValorNumerico.setVisibility(View.VISIBLE);
                valorTipoNotaAdapter.setList(new ArrayList<ValorTipoNotaUi>());

                break;
            case SELECTOR_ICONOS:
                imgSelectorNumerico.setVisibility(View.GONE);
                imgValorNumerico.setVisibility(View.GONE);
                tilTipoNota.setVisibility(View.VISIBLE);
                valorTipoNotaAdapter.setList(tipoNotaUi.getValorTipoNotaList());

                break;
            case SELECTOR_VALORES:
                imgSelectorNumerico.setVisibility(View.GONE);
                imgValorNumerico.setVisibility(View.GONE);
                tilTipoNota.setVisibility(View.VISIBLE);
                valorTipoNotaAdapter.setList(tipoNotaUi.getValorTipoNotaList());

                break;
        }
    }

    @Override
    public void showTipoFormulaSelected(String name) {
        edtTipoFormula.setText(name);
    }

    @Override
    public void showTipoRedondeadoSelected(String name) {
        edtTipoValorRedondeado.setText(name);
    }

    @Override
    public void showPesoRubro(double countPeso) {
        textViewTotalPeso.setVisibility(View.VISIBLE);
        //viewLinealCountPeso.setVisibility(View.VISIBLE);
        peso.setVisibility(View.VISIBLE);
        textViewTotalPeso.setText(String.valueOf((int) countPeso));
    }

    @Override
    public void hidePesoRubro(double countPeso) {
        textViewTotalPeso.setVisibility(View.GONE);
        //viewLinealCountPeso.setVisibility(View.GONE);
        peso.setVisibility(View.GONE);
    }

    @Override
    public void showMessageLimiteWeigth(double peso) {

        textViewTotalPeso.setTextColor(getResources().getColor(R.color.red));
        textViewTotalPeso.setText(String.valueOf("Limite excedió " + (int) peso));
    }

    @Override
    public void showMessageWeigth(double peso) {
        textViewTotalPeso.setTextColor(getResources().getColor(R.color.md_grey_600));
    }

    @Override
    public void showDialogEditPeso(RubroProcesoUi rubroProcesoUi) {
        EditarRubroFormulaPesoFragment dialog = EditarRubroFormulaPesoFragment.newInstance(rubroProcesoUi);
        dialog.show(getSupportFragmentManager(), CREAR_RUBRO_FORMULA_TAG);
    }

    @Override
    public void showError(String error) {
        Snackbar.make(recyclerViewRubrosProcesosChecked, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void success(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi) {
        Log.d(CREAR_RUBRO_FORMULA_TAG, "success : " + rubroProcesoUi.getRubrosAsociadosUiList().size());
        showMessageToast();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(FragmentAbstract.TIPO, FragmentAbstract.TIPO_ADD_RUBRO_EVALUACION_FORMULA);
        bundle.putParcelable("rubroUi", Parcels.wrap(rubroProcesoUi));
        bundle.putParcelable("capacidadUi", Parcels.wrap(capacidadUi));
        intent.putExtras(bundle);
        //  intent.putExtra(CrearRubroFormulaActivity.RUBROEVALUACIONID, rubroProcesoUi.getId());
        setResult(Activity.RESULT_OK, intent);
        finish();
        /*Intent intent = new Intent(getActivity(), CrearRubroFormulaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("listaRubros", Parcels.wrap(procesoUiList));
        bundle.putParcelable("rubroEvaluacionResul",Parcels.wrap(capacidadUi));
        intent.putExtras(bundle);*/
    }

    @Override
    public void success() {
        finish();
    }

    @Override
    public void disabledListFormula() {
        recyclerViewRubrosProcesosChecked.post(new Runnable() {
            @Override
            public void run() {
                if(recyclerViewRubrosProcesosChecked!=null)
                    disableView(recyclerViewRubrosProcesosChecked);
            }
        });

    }

    @Override
    public void setTitulo(String title) {
        textViewTitulo.setText(title);
    }

    @Override
    public void mostrarListaTipoNota(String dialogTitle, List<Object> items, int positionSelected, int programaEducativoId) {
        Log.d(CREAR_RUBRO_FORMULA_TAG, "mostrarListaTipoNota : " + programaEducativoId);
        Intent intent = TipoNotaListActivity.getSeleccionarTipoNotaListaIntent(getActivity(), dialogTitle,
                programaEducativoId,
                TipoUi.Tipo.SELECTOR_ICONOS,
                TipoUi.Tipo.SELECTOR_VALORES,
                TipoUi.Tipo.SELECTOR_NUMERICO
        );
        startActivityForResult(intent, SELECT_LISTATIPONOTAS_REQUEST);

    }

    @Override
    public void removerRubro(RubroProcesoUi rubroProcesoUi) {
        adapter.deleteItem(rubroProcesoUi);
    }

    @Override
    public void changeListaRubros() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSincronizate(int programaEducatId) {
        //CallService.jobServiceExportarTipos(this, TipoExportacion.RUBROEVALUACION);
        SimpleSyncIntenService.start(this, programaEducatId);
        SynckService.start(this,programaEducatId);
        CMRE.saveNotifyChangeDataBase(this);
    }

    private void showMessageToast() {
        Toast.makeText(this, "Registro Correctamente!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @OnClick({R.id.btnTipoNota, R.id.btnTipoFormula, R.id.btnTipoValorRedondeado, R.id.txt_tipo_nota})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnTipoNota:
                presenter.onBtnTipoNotaClicked();
                break;
            case R.id.btnTipoFormula:
                presenter.onBtnTipoFormulaClicked();
                break;
            case R.id.btnTipoValorRedondeado:
                presenter.onBtnTipoValorRedondeoClicked();
                break;
            case R.id.txt_tipo_nota:
                presenter.onBtnTipoNotaClicked();
                break;
        }
    }

    void disableView(View view){
        if(view instanceof ViewGroup){
            for (int i = 0; i < ((ViewGroup)view).getChildCount(); i++) {
                disableView(((ViewGroup)view).getChildAt(i));
            }
        }else if(view instanceof EditText){
            ((EditText)view).setEnabled(false);
        }
    }

    @Override
    public void onRubroEvaluacionProcesoSaved(RubroProcesoUi rubroEvaluacionProcesoUi) {
        presenter.onRubroEvaluacionProcesoSaved(rubroEvaluacionProcesoUi);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_create:
                RubroProcesoUi rubroEvaluacionProcesoUi = new RubroProcesoUi();
                rubroEvaluacionProcesoUi.setTitulo(textViewTitulo.getText().toString().trim());
                rubroEvaluacionProcesoUi.setSubTitulo(textViewSubtitulo.getText().toString().trim());
                // rubroEvaluacionProcesoUi.setValorPorDefecto(editTextValorDefecto.getText().toString().trim());
                presenter.onSelectButtonAceptar(rubroEvaluacionProcesoUi);
                break;
            default:
                Log.d(CREAR_RUBRO_FORMULA_TAG, "default ");
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_LISTATIPONOTAS_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            Log.d(CREAR_RUBRO_FORMULA_TAG, "onActivityResult requestCode: " + requestCode + ", resultCode: " + resultCode);
            String tipoNotaId = data.getExtras().getString(TipoNotaListActivity.TIPONOTAID);
            Log.d(CREAR_RUBRO_FORMULA_TAG,"TipoNotaId: "+tipoNotaId);
            presenter.onActualizarItemTipoNota(tipoNotaId);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onClickAddRubroEvaluacionCapacidad(CapacidadUi capacidadUi) {

    }

    @Override
    public void onClickCapacidad(CapacidadUi capacidadUi) {

    }

    @Override
    public void onClickRubroEvaluacion(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi) {

    }

    @Override
    public void onClickLongClickEvaluacion(RubroProcesoUi rubroProcesoUi) {

    }

    @Override
    public void onClickRubrosAsociados(RubroProcesoUi rubroProcesoUi, CapacidadUi capacidadUi) {

    }

    @Override
    public void onOpciones(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, View view) {

    }


    @Override
    public void onClickCamposTematicos(RubroProcesoUi rubroProcesoUi) {

    }

    @Override
    public void onPesoChanged(RubroProcesoUi rubroEvaluacionProcesoUi, CharSequence peso) {
        presenter.onTextChangedRubroAsociados(rubroEvaluacionProcesoUi, peso);
    }

    @Override
    public void onClickSelectRubros(CapacidadUi capacidadUi) {

    }

    @Override
    public void onClickAncla(CapacidadUi capacidadUi) {

    }

    @Override
    public void onRubroEvalButtonDeleteClick(RubroProcesoUi rubroProcesoUi) {
        presenter.onDeleteRubroEval(rubroProcesoUi);
    }

    @Override
    public void showDialogProgress() {
        alertDialog.show();
    }

    @Override
    public void hideDialogProgress() {
        Log.d(CREAR_RUBRO_FORMULA_TAG, "hideDialogProgress");
        alertDialog.dismiss();
    }
    @Override
    public void showDialogInfoTipoNota(String titulo, TipoNotaUi tipoNotaSelected) {
        DialogValorTipoNota dialogValorTipoNota = DialogValorTipoNota.newInstance(titulo, tipoNotaSelected);
        dialogValorTipoNota.show(getSupportFragmentManager(), DialogValorTipoNota.class.getSimpleName());
    }

    @Override
    public void setNombreFormula(String titulo) {
        Log.d(CREAR_RUBRO_FORMULA_TAG, "setNombreFormula: "+ titulo);
        textViewTitulo.setText(titulo);
    }

    @OnClick(R.id.btn_info_tipo_nota)
    public void onViewClicked() {
        presenter.onClikInfoTipoNota();
    }

}
