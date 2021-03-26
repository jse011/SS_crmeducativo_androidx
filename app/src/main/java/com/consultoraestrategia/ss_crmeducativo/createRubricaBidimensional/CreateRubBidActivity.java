package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.login2.service2.worker.SynckService;
import com.google.android.material.appbar.AppBarLayout;
import androidx.fragment.app.FragmentManager;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.RubroBidICell.RubricaBidCellCallback;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.CompetenciaAdapter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.campoTematicoChooser.CampoTematicoChooserCallback;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.changePesoIndicador.PesoIndicadorCellCallback;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.changePesoIndicador.PesoIndicadorCellDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.local.CreateRubBidLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.remote.CreateRubBidRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.dialogEstrategiasEvaluacion.DialogEstrategiasEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.dialogEstrategiasEvaluacion.EstrategiaCallBack;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.CreateRubBid;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetCamposAccion;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetCompetenciaRubrica;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetCompetencias;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetEscalaList;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetEstrategiaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetFormaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetFormaEvaluacionList;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetNivelesTipoNotaRubrica;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetNotaPresicion;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTarea;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTipoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTipoEvaluacionList;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTipoNota;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTipoNotaDefault;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTipoNotaRubrica;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTipoNotas;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTituloRubrica;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.listener.EditarRubroDetalleCallBack;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.ui.EditarRubroDetalleFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.indicadorChooser.IndicadorChooserCallback;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.ui.InfoRubricaBidimensionalFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.informacionValorTiponota.DialogValorTipoNota;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubricaCabecera.RubricaCabeceraFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubricaDetalle.RubricaDetalleFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.seleccionarCompetencias.CompetenciaListWrapper;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.seleccionarCompetencias.SeleccionarCompetenciasActivity;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarCampoAccion.BuscarCamposAccionActivity;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarCampoAccion.BuscarCamposAccionListWrapper;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarTipoNota.view.TipoNotaListActivity;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.RowHeader;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EstrategiaEvalUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.mainGraficos.adapters.MyFragmentAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.RubricasAbstractFragment;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.evrencoskun.tableview.listener.ITableViewListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by @stevecampos on 26/01/2018.
 */

public class CreateRubBidActivity extends BaseActivity<CreateRubBidView, CreateRubBidPresenter> implements CreateRubBidView, IndicadorChooserCallback, CampoTematicoChooserCallback, RubricaBidCellCallback, PesoIndicadorCellCallback, EditarRubroDetalleCallBack, EstrategiaCallBack {
    public static final String TAG = CreateRubBidActivity.class.getSimpleName();
    public static final int REQUESTCODE = 21548;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static final String RUBRICATAG = "CreateRubBidActivity.rubricaId";

    @BindView(R.id.rc_pre_view)
    RecyclerView rcPreView;
    @BindView(R.id.viewpager)
    NestedScrollView viewpager;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    private MyFragmentAdapter fragmentAdapter;
    private RubricaCabeceraFragment rubricaCabeceraFragment;
    private RubricaDetalleFragment rubricaDetalleFragment;
    private CompetenciaAdapter adapterPreview;


    public static Intent getStartCreateRubBidActivity(Context context, CRMBundle crmBundle) {
        Intent intent = new Intent(context, CreateRubBidActivity.class);
        intent.putExtras(crmBundle.instanceBundle());
        return intent;
    }

    public static Intent getStartCreateRubBidActivity(Context context, CRMBundle crmBundle, String rubricaId) {
        Intent intent = new Intent(context, CreateRubBidActivity.class);
        intent.putExtras(crmBundle.instanceBundle());
        intent.putExtra(RUBRICATAG, rubricaId);
        return intent;
    }

    private static Integer parseInt(String o) {
        try {
            return Integer.parseInt(o);
        } catch (Exception e) {
            return 0;
        }
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
    protected CreateRubBidPresenter getPresenter() {
        CreateRubBidRepository repository = new CreateRubBidRepository(
                new CreateRubBidLocalDataSource(
                        InjectorUtils.provideTiposDao(),
                        InjectorUtils.provideEscalaEvaluacionDao(),
                        InjectorUtils.provideSilaboEventoDao(),
                        InjectorUtils.provideCompetenciaDao(),
                        InjectorUtils.provideTipoEvaluacionDao(),
                        InjectorUtils.provideTipoNotaDao(),
                        InjectorUtils.provideTareasDao()
                ),
                new CreateRubBidRemoteDataSource()
        );

        return new CreateRubPresenterImpl(
                new UseCaseHandler(
                        new UseCaseThreadPoolScheduler()),
                getResources(),
                new GetCompetencias(repository),
                new GetTipoNotas(repository),
                new GetTipoNota(repository),
                new GetEscalaList(repository),
                new GetTipoEvaluacionList(repository),
                new GetFormaEvaluacionList(repository),
                new CreateRubBid(repository),
                new GetCamposAccion(repository),
                new GetTipoNotaDefault(repository),
                new GetTarea(repository),
                new GetFormaEvaluacion(repository),
                new GetTipoEvaluacion(repository),
                new GetTipoNotaRubrica(repository),
                new GetCompetenciaRubrica(repository),
                new GetNivelesTipoNotaRubrica(repository),
                new GetTituloRubrica(repository),
                new GetEstrategiaEvaluacion(repository),
                new GetNotaPresicion()
        );
    }

    @Override
    protected CreateRubBidView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.fragment_rubrica_bidimensional);
        ButterKnife.bind(this);
        rubricaCabeceraFragment = new RubricaCabeceraFragment(this, this);
        rubricaDetalleFragment = new RubricaDetalleFragment(this, this);
        setupToolbar();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupScrollView();
        setupAdapter();
    }

    private void setupAdapter() {
        adapterPreview = new CompetenciaAdapter(new ArrayList<Object>());
        rcPreView.setLayoutManager(new LinearLayoutManager(this));
        rcPreView.setAdapter(adapterPreview);
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

    private void setupScrollView() {

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
    public void onBtnTipoEvaluacionClicked() {
        Log.d(TAG, "onBtnTipoEvaluacionClicked");
        presenter.onBtnTipoEvaluacionClicked();
    }

    @Override
    public void onBtnFormaEvaluacionClicked() {
        Log.d(TAG, "onBtnFormaEvaluacionClicked");
        presenter.onBtnFormaEvaluacionClicked();
    }

    @Override
    public void onBtnTipoNotaClicked() {
        Log.d(TAG, "onBtnTipoNotaClicked");
        presenter.onBtnTipoNotaClicked();
    }

    @Override
    public void onBtnEscalaClicked() {
        Log.d(TAG, "onBtnEscalaClicked");
        presenter.onBtnEscalaClicked();
    }

    @Override
    public void onBtnCompetenciaListClicked() {
        Log.d(TAG, "onBtnCompetenciaListClicked");
        presenter.onBtnCompetenciaListClicked();
    }

    @Override
    public void onBtnCampoAccionListClicked() {
        Log.d(TAG, "onBtnCampoAccionListClicked");
        presenter.onBtnCampoAccionListClicked();
    }

    @Override
    public void onBtnBuscarCompetenciaListClicked() {
        presenter.onBtnBuscarCompetenciaListClicked();
    }

    @Override
    public void onBtnEstrategiaEvaluacionClicked() {
        presenter.onBtnEstrategiaEvaluacionClicked();
    }

    @Override
    public void onTextChangedEditarAlias(String texto) {
        presenter.onTextChangedEditarAlias(texto);
    }

    @Override
    public void onSelectedFormaEval(TipoUi selected) {
        presenter.onSelectedFormaEval(selected);
    }

    @Override
    public void onSelectedTipoEval(TipoUi selected) {
        presenter.onSelectedTipoEval(selected);
    }

    @Override
    public String getEdtRubrica() {
        return rubricaCabeceraFragment.getEdtRubrica();
    }

    @Override
    public void showTipoNotaSelected(TipoNotaUi tipoNota) {
        Log.d(TAG, "showTipoNotaSelected");
        rubricaCabeceraFragment.showTipoNotaSelected(tipoNota);
    }


    @Override
    public void showTipoEvaluacionSelected(String tipoEvaluacionTitulo, List<TipoUi> tipoEvaluacionList) {
        rubricaCabeceraFragment.showTipoEvaluacionSelected(tipoEvaluacionTitulo, tipoEvaluacionList);
    }

    @Override
    public void showFormaEvaluacionSelected(String formaEvaluacionTitulo, List<TipoUi> formaEvaluacionList) {
        rubricaCabeceraFragment.showFormaEvaluacionSelected(formaEvaluacionTitulo, formaEvaluacionList);
    }

    @Override
    public void showEscalaSelected(String escalaTitulo) {
        Log.d(TAG, "showEscalaSelected");
        rubricaCabeceraFragment.showEscalaSelected(escalaTitulo);
    }

    @Override
    public void showCompetenciaList(List<CompetenciaUi> competenciaList) {
        Log.d(TAG, "showCompetenciaList");
        rubricaCabeceraFragment.showCompetenciaList(competenciaList);
    }

    @Override
    public void showCampoAccionList(List<CampoAccionUi> campoAccionList) {
        Log.d(TAG, "showCampoAccionList");
        rubricaCabeceraFragment.showCampoAccionList(campoAccionList);
    }

    @Override
    public void showTipoNotaChooser(List<TipoNotaUi> tipoNotaList) {
        Log.d(TAG, "showTipoNotaChooser");
        rubricaCabeceraFragment.showTipoNotaChooser(tipoNotaList);
    }

    @Override
    public void showTipoEvaluacionChooser(List<TipoUi> tipoEvaluacionList) {
        Log.d(TAG, "showTipoEvaluacionChooser");
        rubricaCabeceraFragment.showTipoEvaluacionChooser(tipoEvaluacionList);
    }

    @Override
    public void showFormaEvaluacionChooser(List<TipoUi> formaEvaluacionList) {
        Log.d(TAG, "showFormaEvaluacionChooser");
        rubricaCabeceraFragment.showFormaEvaluacionChooser(formaEvaluacionList);
    }

    @Override
    public void showEscalaChooser(List<EscalaEvaluacionUi> escalaEvaluacionList) {
        Log.d(TAG, "showEscalaChooser");
        rubricaCabeceraFragment.showEscalaChooser(escalaEvaluacionList);
    }


    public static final int SELECT_COMPETENCIA_REQUEST = 100;
    public static final int SELECT_LISTATIPONOTAS_REQUEST = 111;
    public static final int SELECT_BUSCARCAMPOSACCION_REQUEST = 211;

    @Override
    public void showIndicadorChooser(List<CompetenciaUi> competenciaList) {
        Log.d(TAG, "showIndicadorChooser");
        /*RubricaCabeceraFragment fragment = getRubricaCabeceraFragment();
        if (fragment != null) {
            fragment.showIndicadorChooser(competenciaList);
        }*/
        if (competenciaList != null && !competenciaList.isEmpty()) {
            Log.d(TAG, "competenciaListSize: " + competenciaList.size());
            Intent intent = SeleccionarCompetenciasActivity.getSeleccionarCompetenciasIntent(getActivity(), competenciaList);
            startActivityForResult(intent, SELECT_COMPETENCIA_REQUEST);
        }
    }

    @Override
    public void showCampoAccionChooser(List<IndicadorUi> indicadorList) {
        Log.d(TAG, "showCampoAccionChooser");
        rubricaCabeceraFragment.showCampoAccionChooser(indicadorList);
    }

    @Override
    public void showEditarRubroDetalle(IndicadorUi indicador, TipoNotaUi tipoNotaUi, List<Cell> cellList, boolean disabledCampoAccion) {
        Log.d(TAG, "showEditarRubroDetalle");
        FragmentManager fragmentManager = getSupportFragmentManager();
        EditarRubroDetalleFragment dialog = EditarRubroDetalleFragment.newInstance(indicador, tipoNotaUi, cellList, disabledCampoAccion);
        dialog.show(fragmentManager, EditarRubroDetalleFragment.class.getSimpleName());
    }

    @Override
    public void onAcceptButtom() {
        finish();
    }

    @Override
    public void showTipoNotaSingleChooserCabecera(String activityTitle, Object o, int i, int idProgramaEducativo) {
        Intent intent = TipoNotaListActivity.getSeleccionarTipoNotaListaIntent(getActivity(), activityTitle,
                idProgramaEducativo,
                true,
                com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi.Tipo.SELECTOR_ICONOS,
                com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi.Tipo.SELECTOR_VALORES);
        startActivityForResult(intent, SELECT_LISTATIPONOTAS_REQUEST);
    }

    @Override
    public void showDialogEstrategiasEvaluacion(List<EstrategiaEvalUi> lista) {
        Bundle args = new Bundle();
        args.putSerializable(DialogEstrategiasEvaluacion.LISTA_ESTATEGIAS, (Serializable) lista);

        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogEstrategiasEvaluacion dialog = DialogEstrategiasEvaluacion.newInstance(args);
        dialog.show(fragmentManager, DialogEstrategiasEvaluacion.class.getSimpleName());

    }

    @Override
    public void showTituloEstrategiaSelected(String estrategia) {
        Log.d(TAG, "showTituloEstrategiaSelected " + estrategia);
        rubricaCabeceraFragment.showTituloEstrategiaSelected(estrategia);
    }

    @Override
    public void setSubTitulo(String tituloTarea) {
        rubricaCabeceraFragment.setSubTitulo(tituloTarea);
    }

    @Override
    public void setTitulo(String tituloTarea) {
        rubricaCabeceraFragment.setTitulo(tituloTarea);
    }


    @Override
    public void showEstrategiaInput() {
        rubricaCabeceraFragment.showEstrategiaInput();
    }

    @Override
    public void hideEstrategiaInput() {
        rubricaCabeceraFragment.hideEstrategiaInput();
    }

    @Override
    public void showPreview(List<Object> competenciaList) {
        adapterPreview.setItems(competenciaList);
    }

    @Override
    public void showDialogInfoTipoNota(String titulo, TipoNotaUi tipoNotaSelected) {
        DialogValorTipoNota dialogValorTipoNota = DialogValorTipoNota.newInstance(titulo, tipoNotaSelected);
        dialogValorTipoNota.show(getSupportFragmentManager(), DialogValorTipoNota.class.getSimpleName());
    }

    @Override
    public void hideCompetenciaList() {
        rubricaCabeceraFragment.hideCompetenciaList();
    }

    @Override
    public void hideCampoAccionList() {
        rubricaCabeceraFragment.hideCampoAccionList();
    }

    @Override
    public void disabledNivelLogroRubrica() {
        rubricaCabeceraFragment.disabledNivelLogroRubrica();
    }


    @Override
    public void disabledBusquedaCamposAccion() {
        Log.d(TAG, "disabledBusquedaCamposAccion");
        rubricaDetalleFragment.disabledBusquedaCamposAccion();
    }

    @Override
    public void disabledSelectorIndicador() {
        Log.d(TAG, "disabledSelectorIndicador");
        rubricaDetalleFragment.disabledSelectorIndicador();
    }

    @Override
    public void disabledSelectorCamposAccion() {
        Log.d(TAG, "disabledSelectorCamposAccion");
        rubricaDetalleFragment.disabledSelectorCamposAccion();
    }

    @Override
    public void disabledFormaEvaluacion() {
        rubricaCabeceraFragment.disabledFormaEvaluacion();
    }


    @Override
    public void onIndicadorListOk(List<CompetenciaUi> competenciaList) {
        Log.d(TAG, "onIndicadorListOk");
        presenter.onIndicadorListOk(competenciaList);
    }

    @Override
    public void onIndicadorListCancel() {
        Log.d(TAG, "onIndicadorListCancel");
        presenter.onIndicadorListCancel();
    }

    @Override
    public void onCampoTematicoListOk(List<IndicadorUi> indicadorList) {
        Log.d(TAG, "onCamposAccionSucces");
        presenter.onCampoTematicoListOk(indicadorList);
    }

    @Override
    public void onCampoTematicoCancel() {
        Log.d(TAG, "onCamposAccionCancel");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_create:
                if (presenter != null) presenter.onBtnCreateClicked();
                break;
            default:
                if (presenter != null) presenter.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showTipoNivelSelected(TipoNotaUi tipoNotaUi) {
        Log.d(TAG, "showTipoNivelSelected");
        rubricaDetalleFragment.showTipoNivelSelected(tipoNotaUi);
    }

    @Override
    public void showTipoNivelChooser(List<TipoNotaUi> tipoNotaList) {
        Log.d(TAG, "showTipoNivelChooser");
        rubricaDetalleFragment.showTipoNivelChooser(tipoNotaList);
    }

    @Override
    public void refrescar() {

    }

    @Override
    public void showTableview(List<ColumnHeader> headerList, List<RowHeader> rows, List<List<Cell>> bodyList, ITableViewListener listener) {
        Log.d(TAG, "showTableview");
        rubricaDetalleFragment.showTableview(headerList, rows, bodyList, listener);
    }

    @Override
    public void updateCellItem(int x, int y, Cell item) {
        Log.d(TAG, "updateCellItem");
        rubricaDetalleFragment.updateCellItem(x, y, item);
    }


    @Override
    public void onBtnTipoNivelClicked() {
        Log.d(TAG, "onBtnTipoNivelClicked");
        presenter.onBtnTipoNivelClicked();
    }

    @Override
    public void onTableTitleClicked() {
        Log.d(TAG, "onBtnTipoNivelClicked");
        presenter.onTableTitleClicked();
    }

    @Override
    public void onBtnDetalleCampoAccionListClicked() {
        Log.d(TAG, "onBtnCampoAccionListClicked");
        presenter.onBtnCampoAccionListClicked();
    }

    @Override
    public void onBtnDetalleCompetenciaListClicked() {
        Log.d(TAG, "onBtnCompetenciaListClicked");
        presenter.onBtnCompetenciaListClicked();
    }

    @Override
    public void onBtnDetalleBuscarCampoAccionListClicked() {
        Log.d(TAG, "onBtnDetalleBuscarCampoAccionListClicked");
        presenter.onBtnBuscarCompetenciaListClicked();
    }

    @Override
    public void showCellItemDialog(ColumnHeader columnHeader, RowHeader rowHeader, Cell cell, int x, int y) {


    }

    @Override
    public void onSucess(String rubroEvalId, int countIndicadorList, int programaEducativoId) {
        Log.d(TAG, "String rubroEvalId, int count IndicadorList");
        //Este es el nuevo metodo JSE
        Log.d(TAG, "onSucess:rubroEvalId " + rubroEvalId);
        CallService.jobServiceExportarTipos(this, TipoExportacion.RUBROEVALUACION);
        SimpleSyncIntenService.start(this, programaEducativoId);
        SynckService.start(this,programaEducativoId);
        CMRE.saveNotifyChangeDataBase(this);
        // Prepare data intent
        Intent intent = new Intent();
        intent.putExtra(RubricasAbstractFragment.TIPO_RUBRICA, RubricasAbstractFragment.AGREGAR_RUBRICAS_BIDIMENSIONAL);
        intent.putExtra(RubricasAbstractFragment.RUBROEVALUACIONID, rubroEvalId);
        intent.putExtra(RubricasAbstractFragment.COUNT_INDICADOR, countIndicadorList);
        setResult(AppCompatActivity.RESULT_OK, intent);
        finish();
    }


    @Override
    public void showBuscarCampoAccion(List<CampoAccionUi> campoAccionUiList) {

        if (campoAccionUiList == null || campoAccionUiList.isEmpty()) return;
        Log.d(TAG, "campoAccionUiListSixe: " + campoAccionUiList.size());
        Intent intent = BuscarCamposAccionActivity.getSeleccionarCompetenciasIntent(getActivity(), campoAccionUiList);
        startActivityForResult(intent, SELECT_BUSCARCAMPOSACCION_REQUEST);
    }

    @Override
    public void showCriterioEvaluacionItemDialog(CriterioEvaluacionUi criterioEvaluacionUi, int x, int y, IndicadorUi indicadorUi, TipoNotaUi tipoNotaUi) {
//        RubricaBidCellDialogFragment fragment = RubricaBidCellDialogFragment.newInstance(criterioEvaluacionUi, x, y);
//        fragment.show(getSupportFragmentManager(), RubricaBidCellDialogFragment.class.getSimpleName());
//
        InfoRubricaBidimensionalFragment fragment1 = InfoRubricaBidimensionalFragment.
                newInstance(criterioEvaluacionUi, x, y, indicadorUi, tipoNotaUi);
        fragment1.show(getSupportFragmentManager(), InfoRubricaBidimensionalFragment.class.getSimpleName());
    }

    @Override
    public void showCriterioEvaluacionItemDialog(IndicadorUi indicadorUi, int x, int y) {
        PesoIndicadorCellDialogFragment fragment = PesoIndicadorCellDialogFragment.newInstance(indicadorUi, x, y);
        fragment.show(getSupportFragmentManager(), PesoIndicadorCellDialogFragment.class.getSimpleName());
    }

    @Override
    public void onBtnNegativeClicked() {
        Log.d(TAG, "onBtnNegativeClicked");
    }

    @Override
    public void onBtnPostiveCriterioEvalaucion(CriterioEvaluacionUi criterioEvaluacionUi, int x, int y) {
        Log.d(TAG, "onBtnPositiveClicked");
        presenter.onBtnPostiveCriterioEvalaucion(criterioEvaluacionUi, x, y);
    }

    @Override
    public void showListTipoNotaSingleChooser(String activityTitle, final List<Object> items, int positionSelected, int idProgramaEducativo) {
        Intent intent = TipoNotaListActivity.getSeleccionarTipoNotaListaIntent(getActivity(), activityTitle,
                idProgramaEducativo,
                false,
                com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi.Tipo.SELECTOR_ICONOS,
                com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi.Tipo.SELECTOR_VALORES);
        startActivityForResult(intent, SELECT_LISTATIPONOTAS_REQUEST);

        /*FragmentManager manager = getSupportFragmentManager();
        fragment = TipoNotaListaDialogFragment.newInstance(
                com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi.Tipo.SELECTOR_ICONOS,
                com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi.Tipo.SELECTOR_NUMERICO
        );
        fragment.show(manager, "TipoNotaListaDialogFragment");*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult requestCode: " + requestCode + ", resultCode: " + resultCode);
        if (requestCode == SELECT_COMPETENCIA_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            CompetenciaListWrapper wrapper = (CompetenciaListWrapper)data.getExtras().getSerializable(SeleccionarCompetenciasActivity.EXTRA_COMPETENCIALIST);
            onIndicadorListOk(wrapper.getItems());
        } else if (requestCode == SELECT_LISTATIPONOTAS_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            Log.d(TAG, "onActivityResult requestCode: " + requestCode + ", resultCode: " + resultCode);
            String tipoNotaId = data.getExtras().getString(TipoNotaListActivity.TIPONOTAID);
            presenter.onSingleItemSelectedTipoNota(tipoNotaId);
        } else if (requestCode == SELECT_BUSCARCAMPOSACCION_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            BuscarCamposAccionListWrapper wrapper = (BuscarCamposAccionListWrapper)data.getExtras().getSerializable(BuscarCamposAccionActivity.EXTRA_CAMPOSACCIONLIST);
            presenter.onSelectBuscarCamosAccion(wrapper.getItems());
        }
    }

    @Override
    public void onBtnNegativeClickedPesoIndicadorCell() {
        Log.d(TAG, "onBtnNegativeClickedPesoIndicadorCell");
    }

    @Override
    public void onBtnPostivePesoIndicadorCell(IndicadorUi indicadorUi, int x, int y) {
        presenter.onBtnPostivePesoIndicadorCell(indicadorUi, x, y);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClickAcetarEditarRubroDetalle(IndicadorUi indicadorUi) {
        Log.d(TAG, "onClickAcetarEditarRubroDetalle");
        presenter.onSelectAcetarEditarRubroDetalle(indicadorUi);
    }

    @Override
    public void onClickCancelarEditarRubroDetalle() {
        Log.d(TAG, "onClickCancelarEditarRubroDetalle");
        presenter.onSelectCancelarEditarRubroDetalle();
    }

    @Override
    public void sendList(IndicadorUi indicadorUi) {
        presenter.sendList(indicadorUi);
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
        Log.d(TAG, "onBackPressed");
    }


    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTittle) {
        super.showFinalMessageAceptCancel(message, messageTittle);
    }

    @Override
    public void showTxtTableEmprty() {
        rubricaDetalleFragment.showTxtTableEmprty();
    }

    @Override
    public void hideTxtTableEmprty() {
        rubricaDetalleFragment.hideTxtTableEmprty();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onclickEstrategia(EstrategiaEvalUi estrategiaEvalUiselected) {
        presenter.setEstrategiaSelected(estrategiaEvalUiselected);
    }


    private void hideViews() {
        appBarLayout.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
        toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
        /*FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFabButton.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        mFabButton.animate().translationY(mFabButton.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();*/
    }

    private void showViews() {
        appBarLayout.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        //mFabButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }


    @OnClick(R.id.btn_info_tipo_nota)
    public void onViewClicked() {
        presenter.onClikInfoTipoNota();
    }


}
