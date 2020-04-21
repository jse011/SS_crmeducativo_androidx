package com.consultoraestrategia.ss_crmeducativo.tabUnidad.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.base.filterChooser.CallbackFilterChooserBottomDialog;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.ViewpagerAdapter;
import com.consultoraestrategia.ss_crmeducativo.lib.FloatingActionsMenu.FloatingActionButton;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadParametros;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.UnidadItemAdapter;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.TipoEnum;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.UnidadItem;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.aprendizaje.AprendizajeFragment;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.view.EvaluacionFragment;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.productos.ProductosFragment;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.recursos.RecursosFragment;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.situacion.SituacionFragment;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.listener.UnidadListener;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.presenter.UnidadPresenter;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.presenter.UnidadPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UnidadActivity extends BaseActivity<UnidadView, UnidadPresenter> implements UnidadView, BaseFragmentListener, UnidadListener, CallbackFilterChooserBottomDialog {


    @BindView(R.id.tipoUnidad)
    RecyclerView rvTipo;
    @BindView(R.id.unidadTitulo)
    TextView unidadTitulo;
    @BindView(R.id.buttomFilter)
    FloatingActionButton button;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.conten_unidades)
    ViewPager contenUnidades;
    private UnidadItemAdapter adapter;
    private ViewpagerAdapter adapterViewPager;


    @Override
    protected String getTag() {
        return UnidadActivity.class.getSimpleName();
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected UnidadPresenter getPresenter() {
        // UnidadRepository repository = new UnidadRepository(new UnidadLocalDataSource(InjectorUtils.provideRubroEvalRNPFormulaDao()));
        presenter = new UnidadPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
        return presenter;
    }

    @Override
    protected UnidadView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_unidad);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setContentInsetStartWithNavigation(0);
        setupAdapter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    private void setupAdapter() {
        adapter = new UnidadItemAdapter(new ArrayList<UnidadItem>(), this);
        rvTipo.setLayoutManager(new LinearLayoutManager(this));
        rvTipo.setAdapter(adapter);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void setListMenu(List<UnidadItem> listMenu) {
        adapter.setList(listMenu);
    }

    @Override
    public void onChangeMenuItem() {
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onShowFragment(UnidadItem unidadItem, UnidadParametros unidadParametros) {
        switch (unidadItem.getTipo()) {
            case APRENDIZAJE:
                contenUnidades.setCurrentItem(0,false);
                showButtonFiler();
                break;
            case SITUACION:
                contenUnidades.setCurrentItem(1,false);
                hideBUttonFilter();
                break;
            case PRODUCTOS:
                contenUnidades.setCurrentItem(2,false);
                hideBUttonFilter();
                break;
            case RECURSOS:
                contenUnidades.setCurrentItem(3,false);
                hideBUttonFilter();
                break;
            case EVALUACION:
                contenUnidades.setCurrentItem(4,false);
                hideBUttonFilter();
                break;
        }
    }

    @Override
    public void onShowAprendizaje(UnidadParametros unidadParametros) {
//        AprendizajeFragment aprendizajeFragment = getFragment(AprendizajeFragment.class);
//        if (aprendizajeFragment != null) aprendizajeFragment.showAprendizajeFragment();
//        showButtonFiler();
        Bundle bundle = unidadParametros.getBundle();
        bundle.putAll(getIntent().getExtras());
        adapterViewPager = new ViewpagerAdapter(getSupportFragmentManager(),5, null);
        contenUnidades.setOffscreenPageLimit(5);
        adapterViewPager.addFragment(AprendizajeFragment.newInstance(bundle),"");
        adapterViewPager.addFragment(SituacionFragment.newInstance(bundle),"");
        adapterViewPager.addFragment(ProductosFragment.newInstance(bundle),"");
        adapterViewPager.addFragment(RecursosFragment.newInstance(bundle),"");
        adapterViewPager.addFragment(EvaluacionFragment.newInstance(bundle),"");
        contenUnidades.setAdapter(adapterViewPager);

    }


    @Override
    public void showListChoose(TipoEnum tipoEnum) {
        showListFilterChooser("Tipo Competencia", TipoEnum.values(), tipoEnum, this);
    }

    private <T extends Fragment> T getFragment(Class<T> tClass) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment :
                fragments) {
            if (tClass.isInstance(fragment)) {
                return (T) fragment;
            }
        }
        return null;
    }

    public void showTituloUnidad(String titulo) {
        unidadTitulo.setText(titulo);
    }


    @Override
    public void changeToogle(UnidadItem unidadItem) {
        presenter.onChangeToogle(unidadItem);
    }

    @OnClick(R.id.buttomFilter)
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.buttomFilter:
                presenter.onClickFilter();
                break;
        }
    }


    public void showButtonFiler() {
        button.setVisibility(View.VISIBLE);
    }

    public void hideBUttonFilter() {
        button.setVisibility(View.GONE);
    }


    @Override
    public void onAceptarBottomDialog(Object o) {
        presenter.onChangeFilter(o);
        startAprendizajeFilter(o);
    }

    private void startAprendizajeFilter(Object o) {
        AprendizajeFragment aprendizajeFragment = getFragment(AprendizajeFragment.class);
        if (aprendizajeFragment != null) aprendizajeFragment.filterCompetencia(o);
    }

    @Override
    public void onCancelarBottomDialog() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
