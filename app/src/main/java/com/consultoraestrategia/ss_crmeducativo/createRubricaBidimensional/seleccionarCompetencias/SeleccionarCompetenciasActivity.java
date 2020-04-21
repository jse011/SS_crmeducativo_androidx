package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.seleccionarCompetencias;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.DrawableRes;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.indicadorChooser.IndicadorChooserCallback;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.indicadorChooser.IndicadoresChoserDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.mainGraficos.adapters.MyFragmentAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeleccionarCompetenciasActivity extends BaseActivity<SeleccionarCompetenciaView, SeleccionarCompetenciaPresenter> implements SeleccionarCompetenciaView, IndicadorChooserCallback {

    public static final String TAG = SeleccionarCompetenciasActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    public static final String EXTRA_COMPETENCIALIST = "competencia_list";

    public static Intent getSeleccionarCompetenciasIntent(Context context, List<CompetenciaUi> competenciaList) {
        CompetenciaListWrapper wrapper = new CompetenciaListWrapper();
        wrapper.setItems(competenciaList);

        Bundle args = new Bundle();

        Intent intent = new Intent(context, SeleccionarCompetenciasActivity.class);
        args.putSerializable(EXTRA_COMPETENCIALIST, wrapper);
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
        setContentView(R.layout.activity_seleccionar_competencias);
        ButterKnife.bind(this);
        setupToolbar();
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

    private void setupViewPager(List<CompetenciaUi> competenciasBase, List<CompetenciaUi> competenciasTransversales, List<CompetenciaUi> competenciasEnfoque) {
        Log.d(TAG, "setupViewPager");
        Fragment competenciasBaseFragment = IndicadoresChoserDialogFragment.newInstance(competenciasBase);
        Fragment competenciasTransversalesFragment = IndicadoresChoserDialogFragment.newInstance(competenciasTransversales);
        Fragment competenciasEnfoqueFragment = IndicadoresChoserDialogFragment.newInstance(competenciasEnfoque);

        MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(competenciasBaseFragment, "Base");
        fragmentAdapter.addFragment(competenciasTransversalesFragment, "Transversal");
        fragmentAdapter.addFragment(competenciasEnfoqueFragment, "Enfoque");
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(fragmentAdapter);
        //viewpager.setCurrentItem(0);
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
        changeTabIcon(tabPosition, newIcon);
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
    public void showCompetencias(List<CompetenciaUi> competenciasBase, List<CompetenciaUi> competenciasTransversales, List<CompetenciaUi> competenciasEnfoque) {
        setupViewPager(competenciasBase, competenciasTransversales, competenciasEnfoque);
    }

    @Override
    public void devolverResultado(List<CompetenciaUi> competenciaList) {
        Intent intent = new Intent();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_COMPETENCIALIST, new CompetenciaListWrapper(competenciaList));
        intent.putExtras(args);
        setResult(AppCompatActivity.RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (presenter != null) presenter.onAceptarButtonClicked();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onIndicadorListOk(List<CompetenciaUi> competenciaList) {
        Log.d(TAG, "onIndicadorListOk");
    }

    @Override
    public void onIndicadorListCancel() {
        Log.d(TAG, "onIndicadorListCancel");
    }
}
