package com.consultoraestrategia.ss_crmeducativo.programahorario.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.programahorario.ProgramaHorarioPresenter;
import com.consultoraestrategia.ss_crmeducativo.programahorario.adapter.CursoHorarioAdapter;
import com.consultoraestrategia.ss_crmeducativo.programahorario.adapter.ProgramaHorarioAdapter;
import com.consultoraestrategia.ss_crmeducativo.programahorario.adapter.ProgramaHorarioTableAdapter;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.DiaUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.HoraUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.ProgramaHorarioUi;
import com.evrencoskun.tableview.TableView;

import java.util.ArrayList;
import java.util.List;

public abstract class ProgramaHorarioActivity extends BaseActivity<ProgramaHorarioView, ProgramaHorarioPresenter> implements ProgramaHorarioView, View.OnClickListener, ProgramaHorarioAdapter.ProgramaHorarioListener {


    private ProgramaHorarioTableAdapter adapter;
    private ProgramaHorarioAdapter programaHorAdapter;
    private CursoHorarioAdapter cursoHorarioAdapter;

    protected abstract RecyclerView getRcCurso();
    protected abstract RecyclerView getRcProgramaHorario();
    protected abstract TableView getHorario();
    protected abstract ProgressBar getProgress();
    protected abstract Toolbar getToolbar();

    @Override
    protected String getTag() {
        return ProgramaHorarioActivity.class.getSimpleName();
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }


    @Override
    protected ProgramaHorarioView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }



    @Override
    protected void setContentView() {
        setContentViewFind();
        setupToolbar();
        setupAdapter();
    }

    private void setupToolbar() {
        setSupportActionBar(getToolbar());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //change color of status bar
            Window window = this.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.WHITE);
        }
    }

    protected abstract void setContentViewFind();
    protected abstract void hideTextEmpty();



    private void setupAdapter() {
        programaHorAdapter = new ProgramaHorarioAdapter(new ArrayList<ProgramaHorarioUi>(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        getRcProgramaHorario().setLayoutManager(linearLayoutManager);
        getRcProgramaHorario().setAdapter(programaHorAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getBaseContext(), 3, GridLayoutManager.HORIZONTAL, false);
        getRcCurso().setLayoutManager(gridLayoutManager);
        cursoHorarioAdapter = new CursoHorarioAdapter(new ArrayList<CursoUi>());
        getRcCurso().setAdapter(cursoHorarioAdapter);

        adapter = new ProgramaHorarioTableAdapter(this, this);
        getHorario().setAdapter(adapter);
        getHorario().setIgnoreSelectionColors(false);
        getHorario().setHasFixedWidth(true);
        getHorario().setIgnoreSelectionColors(true);
    }


    @Override
    protected ViewGroup getRootLayout() {
        return getToolbar();
    }

    @Override
    protected ProgressBar getProgressBar() {
        return getProgress();
    }

    @Override
    public void showHorario(List<DiaUi> columna, List<HoraUi> fila, List<List<Object>> celdaHorarioUiListList) {
        Log.d(getTag(), "showTableview");
        adapter.setAllItems(columna, fila, celdaHorarioUiListList);
        getHorario().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.postShowHorario();

            }
        },1000);

    }

    @Override
    public void showListaProgramaEducativo(List<ProgramaHorarioUi> programaHorarioUiList) {
        programaHorAdapter.setList(programaHorarioUiList);
    }

    @Override
    public void showCurso(List<CursoUi> cursoUiList) {
        cursoHorarioAdapter.setList(cursoUiList);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClick(ProgramaHorarioUi programaHorarioUi) {
        presenter.ProgramaHorarioUi(programaHorarioUi);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getHorario().removeAllViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void showTabPrograma() {
        if(getRcProgramaHorario()!=null)getRcProgramaHorario().setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTabPrograma() {
        if(getRcProgramaHorario()!=null)getRcProgramaHorario().setVisibility(View.GONE);
    }

}
