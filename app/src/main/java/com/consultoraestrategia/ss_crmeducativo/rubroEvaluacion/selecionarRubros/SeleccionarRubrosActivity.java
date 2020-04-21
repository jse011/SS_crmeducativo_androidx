package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnStaggeredGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.RubroColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.local.RubroEvaluacionProcesoListaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.remote.RubroEvaluacionProcesoListaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetCamposAccionRubro;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.ValidarRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroEvaluacionProcesoListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view.FragmentAbstract;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jse on 01/08/2018.
 */

public class SeleccionarRubrosActivity extends BaseActivity<SeleccionarRubrosView, SeleccionarRubrosPresenter> implements SeleccionarRubrosView, RubroEvaluacionProcesoListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    public final static String EXTRA_CAPACIDAD = "SeleccionarRubrosActivity.capacidadUi";
    @BindView(R.id.recycler_two)
    RecyclerView recyclerTwo;
    @BindView(R.id.txtTitulo)
    TextView txtTitulo;
    @BindView(R.id.txt_competencia)
    TextView txtCompetencia;
    private RubroProcesoAdapter adapter;
    public static String SESION;
    private Menu menu;

    public static Intent getSeleccionarRubrosActivityIntent(Context context, CapacidadUi capacidadUi) {
        SeleccionarRubrosListWrapper wrapper = new SeleccionarRubrosListWrapper(capacidadUi);
        Bundle args = new Bundle();
        Intent intent = new Intent(context, SeleccionarRubrosActivity.class);
        args.putParcelable(EXTRA_CAPACIDAD, Parcels.wrap(wrapper));
        SESION = "true";
        intent.putExtras(args);
        return intent;
    }


    @Override
    protected String getTag() {
        return SeleccionarRubrosActivity.class.getSimpleName();
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected SeleccionarRubrosPresenter getPresenter() {

        RubroEvaluacionProcesoListaRepository rubroEvaluacionProcesoListaRepository=new RubroEvaluacionProcesoListaRepository(
                new RubroEvaluacionProcesoListaLocalDataSource(),
                new RubroEvaluacionProcesoListaRemoteDataSource());

        return new SeleccionarRubrosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetCamposAccionRubro(rubroEvaluacionProcesoListaRepository),new ValidarRubroProceso(rubroEvaluacionProcesoListaRepository)
                );
    }

    @Override
    protected SeleccionarRubrosView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_seleccion_rubros);
        ButterKnife.bind(this);
        setupToolbar();
        setupAdapter();
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

    private void setupAdapter() {

        AutoColumnStaggeredGridLayoutManager autoColumnGridLayoutManager = new AutoColumnStaggeredGridLayoutManager( OrientationHelper.VERTICAL,this);
        RubroColumnCountProvider columnCountProvider = new RubroColumnCountProvider(this);
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);

        recyclerTwo.setLayoutManager(autoColumnGridLayoutManager);
        adapter = new RubroProcesoAdapter(new ArrayList<RubroProcesoUi>(), this, new CapacidadUi());
        recyclerTwo.setHasFixedSize(true);
        recyclerTwo.setAdapter(adapter);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_multiple_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_aceptar:
                presenter.onClickAceptarSelecion();
                break;
            case R.id.action_seleccion:
                if(item.isCheckable()){
                    item.setCheckable(false);
                    presenter.onClickSelecionAll();
                }else {
                    item.setCheckable(true);
                    presenter.onClickSelecionOff();
                }
                break;
            default:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
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
        presenter.onClickRubrosAsociados(rubroProcesoUi, capacidadUi);
    }

    @Override
    public void onOpciones(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, View view) {

    }

    @Override
    public void onClickCamposTematicos(RubroProcesoUi rubroProcesoUi) {

    }

    @Override
    public void onPesoChanged(RubroProcesoUi rubroEvaluacionProcesoUi, CharSequence charSequence) {

    }

    @Override
    public void onClickSelectRubros(CapacidadUi capacidadUi) {

    }

    @Override
    public void onClickAncla(CapacidadUi capacidadUi) {

    }

    @Override
    public void showListaRubros(final CapacidadUi capacidadUi, final List<RubroProcesoUi> rubroProcesoUiList, final int pocisionSelecion) {
       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               adapter.setCapacidadUi(capacidadUi);
               adapter.setItems(rubroProcesoUiList);
               //recyclerTwo.scrollToPosition(pocisionSelecion);
           }
       });

    }

    @Override
    public void changeNombreToolbar(String nombre) {
        getSupportActionBar().setTitle(nombre);
    }

    @Override
    public void changeListRubros() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void succesSeleccion(CapacidadUi capacidadUi) {
        Intent intent = new Intent();
        Bundle args = new Bundle();
        args.putInt(FragmentAbstract.TIPO,FragmentAbstract.TIPO_SELECCIONAR_RUBRO);
        args.putParcelable(EXTRA_CAPACIDAD, Parcels.wrap(new SeleccionarRubrosListWrapper(capacidadUi)));
        intent.putExtras(args);
        setResult(AppCompatActivity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void removerSesion() {
        SESION = null;
    }

    @Override
    public void setTituloCompetencia(String nombre) {
        txtCompetencia.setText(nombre);
    }

    @Override
    public void setTituloCapacidad(String nombre) {
        txtTitulo.setText(nombre);
    }

    @Override
    public void setItemtoolbarCheck(boolean check) {
       MenuItem menuItem = menu.findItem(R.id.action_seleccion);
       menuItem.setCheckable(check);
    }

    @Override
    public void updateRubroValidado(RubroProcesoUi rubroProcesoUi) {
     adapter.updateItem(rubroProcesoUi);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        menu = null;
    }
}
