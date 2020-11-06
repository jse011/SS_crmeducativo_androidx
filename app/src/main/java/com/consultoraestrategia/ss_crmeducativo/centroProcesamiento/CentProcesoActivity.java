package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CentProcesoActivity extends BaseActivity<CentProcesoView, CentProcesoPresenter> implements CentProcesoView {
    @BindView(R.id.root)
    FrameLayout root;
    private NavController navController;

    public static void start(Context context) {
        context.startActivity(new Intent(context, CentProcesoActivity.class));
    }

    @Override
    protected String getTag() {
        return "CentProcesoActivity";
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected CentProcesoPresenter getPresenter() {
        return new CentProcesoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected CentProcesoView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.centro_procesamiento);
        ButterKnife.bind(this);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }
}
