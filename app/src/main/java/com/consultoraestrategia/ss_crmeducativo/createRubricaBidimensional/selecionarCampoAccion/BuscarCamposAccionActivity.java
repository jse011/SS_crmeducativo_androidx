package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarCampoAccion;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.DrawableRes;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.campoAccionLista.CamposAccionChooserCallback;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.campoAccionLista.CamposAccionChooserDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorCampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.mainGraficos.adapters.MyFragmentAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("Registered")
public class BuscarCamposAccionActivity extends BaseActivity<SeleccionarCompetenciaView, SeleccionarCompetenciaPresenter> implements SeleccionarCompetenciaView, CamposAccionChooserCallback {

    public static final String TAG = "BuscarCamposAccionTAG";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    public static final String EXTRA_CAMPOSACCIONLIST = "camposAccion_list";

    public static Intent getSeleccionarCompetenciasIntent(Context context, List<CampoAccionUi> competenciaList) {
        BuscarCamposAccionListWrapper wrapper = new BuscarCamposAccionListWrapper(competenciaList);
        Bundle args = new Bundle();
        Intent intent = new Intent(context, BuscarCamposAccionActivity.class);
        Log.d(TAG, "getSeleccionarCompetenciasIntent");
        args.putSerializable(EXTRA_CAMPOSACCIONLIST, wrapper);
        intent.putExtras(args);
        Log.d(TAG, "getSeleccionarCompetenciasIntent");
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
    protected SeleccionarCompetenciaPresenter getPresenter() {
        return new SeleccionarCompetenciasPresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources());
    }

    @Override
    protected SeleccionarCompetenciaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_buscar_campoaccion_competencias);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupViewPager(List<Object> competenciasBase, List<Object> competenciasTransversales, List<Object> competenciasEnfoque) {
        Log.d(TAG, "setupViewPager");
        Fragment competenciasBaseFragment = CamposAccionChooserDialogFragment.newInstance(competenciasBase);
        Fragment competenciasTransversalesFragment = CamposAccionChooserDialogFragment.newInstance(competenciasTransversales);
        Fragment competenciasEnfoqueFragment = CamposAccionChooserDialogFragment.newInstance(competenciasEnfoque);

        MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(competenciasBaseFragment, "Base");
        fragmentAdapter.addFragment(competenciasTransversalesFragment, "Transversal");
        fragmentAdapter.addFragment(competenciasEnfoqueFragment, "Ser");
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(fragmentAdapter);
        tabs.setupWithViewPager(viewpager);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //changeIcon(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        /*
        for (int i = 0; i < tabs.getTabCount(); i++) {
            @DrawableRes int drawableRes;
            switch (i) {
                case 0:
                    drawableRes = R.drawable.ic_base;
                    break;
                case 1:
                    drawableRes = R.drawable.ic_transversal;
                    break;
                default:
                    drawableRes = R.drawable.ic_enfoque;
                    break;
            }
            changeTabIcon(i, drawableRes);
        }*/
    }

    private int getNewPosition(@DrawableRes int[] icons, int lastPosition) {
        int newPosition = lastPosition + 1;
        if (newPosition >= icons.length) {
            newPosition = 0;
        }
        return newPosition;
    }

    private int iconsBasePosition = 0;
    private int iconsTransversalPosition = 0;
    private int iconsEnfoquePosition = 0;


    private void changeIcon(TabLayout.Tab tab) {
        @DrawableRes int[] iconsBase = {R.drawable.ic_base, R.drawable.ic_base_1, R.drawable.ic_base_3};
        @DrawableRes int[] iconsTransveral = {R.drawable.ic_transversal, R.drawable.ic_transversal_1, R.drawable.ic_transversal_2, R.drawable.ic_transversal_4};
        @DrawableRes int[] iconsEnfoque = {R.drawable.ic_enfoque, R.drawable.ic_enfoque_1, R.drawable.ic_enfoque_2};

        @DrawableRes int newIcon;

        int tabPosition = tab.getPosition();
        int newPosition;

        switch (tabPosition) {
            case 0:
                newPosition = getNewPosition(iconsBase, iconsBasePosition);
                iconsBasePosition = newPosition;
                newIcon = iconsBase[newPosition];
                break;
            case 1:
                newPosition = getNewPosition(iconsTransveral, iconsTransversalPosition);
                iconsTransversalPosition = newPosition;
                newIcon = iconsTransveral[newPosition];
                break;
            default:
                newPosition = getNewPosition(iconsEnfoque, iconsEnfoquePosition);
                iconsEnfoquePosition = newPosition;
                newIcon = iconsEnfoque[newPosition];
                break;
        }
        //changeTabIcon(tabPosition, newIcon);
    }

    private void changeTabIcon(int tabPosition, @DrawableRes int drawableRes) {
        tabs.getTabAt(tabPosition).setIcon(drawableRes);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return toolbar;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressbar;
    }


    @Override
    public void showCompetencias(List<Object> competenciasBase, List<Object> competenciasTransversales, List<Object> competenciasEnfoque) {
        setupViewPager(competenciasBase, competenciasTransversales, competenciasEnfoque);
    }

    @Override
    public void devolverResultado(List<CampoAccionUi> competenciaList) {
        Intent intent = new Intent();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CAMPOSACCIONLIST, new BuscarCamposAccionListWrapper(competenciaList));
        intent.putExtras(args);
        setResult(AppCompatActivity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void flitroCompetencia(String filtro) {
        List<Fragment> fragments = getActivity().getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof CamposAccionChooserDialogFragment) {
                CamposAccionChooserDialogFragment camposAccionChooserDialogFragment = (CamposAccionChooserDialogFragment)fragment;
                camposAccionChooserDialogFragment.getAdapter().getFilter().filter(filtro);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select_search, menu);

        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search(searchView);

        return true;
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (presenter != null) presenter.onQueryTextChange(newText);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.action_select){
            if (presenter != null) presenter.onAceptarButtonClicked();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedCamposAccion(CampoAccionUi campoAccionUi) {
        if (presenter != null) presenter.onCheckedCamposAccion(campoAccionUi);
    }

    @Override
    public void onCheckedIndicador(IndicadorCampoAccionUi indicadorCompetenciaUi) {
        if (presenter != null) presenter.onCheckedIndicador(indicadorCompetenciaUi);
    }



}
