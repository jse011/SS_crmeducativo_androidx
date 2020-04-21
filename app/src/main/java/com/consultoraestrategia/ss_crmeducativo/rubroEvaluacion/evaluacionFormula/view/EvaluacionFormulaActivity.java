package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.listener.InfoRubroCallback;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.ui.InfoRubroFragment;
import com.consultoraestrategia.ss_crmeducativo.infoUsuario.InfoUsuarioFragment;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.local.RubroEvaluacionProcesoListaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.remote.RubroEvaluacionProcesoListaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroEvaluacionProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.EvaluacionFormulaPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.EvaluacionFormulaPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EvaluacionFormula_RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.EvaluacionFormulaAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaCelda;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaColumnaCabecera;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaFilaCabecera;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.ColumnaRubroNormalHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.ColumnaRubroRubricaHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.FilasAlumnosHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.EvaluacionFormula;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.listener.ITableViewListener;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by CCIE on 03/05/2018.
 */

public class EvaluacionFormulaActivity extends BaseActivity<EvaluacionFormulaView, EvaluacionFormulaPresenter> implements EvaluacionFormulaView, ITableViewListener, InfoRubroCallback {
    public static final String EVALUACION_FORMULA_TAG = EvaluacionFormulaActivity.class.getSimpleName();
    public static final String ARG_RUBRO_PROCE = "rubroEvaluacionProcesoUi";
    public static final String ARG_LISTA_PROCE = "listaRubroProcesoUi";

    @BindView(R.id.textViewTitulo)
    TextView editTextTituloRubro;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.table)
    TableView table;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private EvaluacionFormulaAdapter adapter;
    private InfoRubroFragment infoRubroFragment;
    private InfoUsuarioFragment infoUsuarioFragment;

    public static void launchEvaluacionFormulaActivity(Context context, Bundle args, RubroEvaluacionProcesoUi rubroEvaluacionProcesoUi) {
        Intent intent = new Intent(context, EvaluacionFormulaActivity.class);
        intent.putExtra(ARG_RUBRO_PROCE, Parcels.wrap(rubroEvaluacionProcesoUi));
        intent.putExtras(args);
        context.startActivity(intent);
    }

    @Override
    protected String getTag() {
        return EVALUACION_FORMULA_TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected EvaluacionFormulaPresenter getPresenter() {
        RubroEvaluacionProcesoListaRepository repository = new RubroEvaluacionProcesoListaRepository(
                new RubroEvaluacionProcesoListaLocalDataSource(),
                new RubroEvaluacionProcesoListaRemoteDataSource());

        return new EvaluacionFormulaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new EvaluacionFormula(repository));
    }

    @Override
    protected EvaluacionFormulaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.evaluacion_formula_activity);
        ButterKnife.bind(this);
        setupToolbar();
        Log.d(EVALUACION_FORMULA_TAG, "setContentView");
        //String nameTitleProceso = getIntent().getExtras().getString("procesoUi");
        //mostrarTitulo(nameTitleProceso);
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //xsetupFragment();
    }


    @Override
    protected ViewGroup getRootLayout() {
        return toolbar;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarTitulo(String titulo) {
        Log.d(EVALUACION_FORMULA_TAG, "mostrarTitulo" + titulo);
        editTextTituloRubro.setText(titulo);
    }

    @Override
    public void mostrarListaTablas(List<FormulaColumnaCabecera> columnaCabeceraList, List<FormulaFilaCabecera> filaCabeceraList, List<List<FormulaCelda>> bodyList) {
        adapter = new EvaluacionFormulaAdapter(getActivity());
        table.setAdapter(adapter);
        table.setTableViewListener(this);
        table.setIgnoreSelectionColors(false);
        table.setHasFixedWidth(false);
        adapter.setAllItems(columnaCabeceraList, filaCabeceraList, bodyList);
        table.setIgnoreSelectionColors(true);
    }

    @Override
    public void showInfoRubroEvalProceso(EvaluacionFormula_RubroEvaluacionUi rubroProcesoUi) {
        FragmentManager manager = getSupportFragmentManager();
        infoRubroFragment = InfoRubroFragment.newInstance(rubroProcesoUi.getId());
        infoRubroFragment.show(manager, "InfoRubroFragments");
    }

    @Override
    public void showInfoUsuario(AlumnosUi alumnosUi) {
        FragmentManager manager = getSupportFragmentManager();
        String titulo = alumnosUi.getName() + " " + alumnosUi.getLastName();
        infoUsuarioFragment = InfoUsuarioFragment.newInstance(null, alumnosUi.getUrlProfile(), titulo, Integer.parseInt(alumnosUi.getKey()), "", new CRMBundle(getIntent().getExtras()));
        infoUsuarioFragment.show(manager, "InfoUsuarioFragment");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder p_jCellView, int p_nXPosition, int p_nYPosition) {

    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder p_jColumnHeaderView, int p_nXPosition) {
        if (p_jColumnHeaderView instanceof ColumnaRubroNormalHolder) {
            ColumnaRubroNormalHolder columnaRubroNormalHolder = (ColumnaRubroNormalHolder) p_jColumnHeaderView;
            presenter.onClickRubroEvalProceso(columnaRubroNormalHolder.getRubroProcesoUi());
        } else if (p_jColumnHeaderView instanceof ColumnaRubroRubricaHolder) {
            ColumnaRubroRubricaHolder columnaRubroRubricaHolder = (ColumnaRubroRubricaHolder) p_jColumnHeaderView;
            presenter.onClickRubroEvalProceso(columnaRubroRubricaHolder.getRubroProcesoUi());
        }
    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jColumnHeaderView, int p_nXPosition) {

    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder p_jRowHeaderView, int p_nYPosition) {
        if (p_jRowHeaderView instanceof FilasAlumnosHolder) {
            FilasAlumnosHolder filasAlumnosHolder = (FilasAlumnosHolder) p_jRowHeaderView;
            presenter.onClickAlumno(filasAlumnosHolder.getAlumnosUi());
        }
    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jRowHeaderView, int p_nYPosition) {

    }
}
