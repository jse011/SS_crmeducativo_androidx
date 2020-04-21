package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarTipoNota.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarTipoNota.TipoNotaListActivityPresenter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.selecionarTipoNota.TipoNotaListActivityPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.listener.TipoNotaListaListener;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.view.TipoNotaListaFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 16/04/2018.
 */

@SuppressLint("Registered")
public class TipoNotaListActivity extends BaseActivity<TipoNotaListActivityView, TipoNotaListActivityPresenter> implements TipoNotaListActivityView, TipoNotaListaListener {
    public static final String TIPONOTAID = TipoNotaListActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.root)
    CoordinatorLayout root;
    public static final String ACTIVITYTITLE = "TipoNotaListActivity.Titulo";
    public static final String PROGRMAEDUCATIVO_ID = "TipoNotaListActivity.ProgrmaEducativoId";
    public static final String COMPLEJO = "TipoNotaListActivity.complejo";
    private TipoNotaListaFragment tipoNotaListaFragment;


    public static Intent getSeleccionarTipoNotaListaIntent(Context context, String activityTitle, int programaEducativoId, boolean complejo, TipoUi.Tipo... tipos) {
        Bundle args = new Bundle();
        args.putSerializable(TipoNotaListaFragment.ENUM_TIPO_TIPONOTAS, tipos);
        args.putSerializable(ACTIVITYTITLE, activityTitle);
        args.putSerializable(PROGRMAEDUCATIVO_ID, programaEducativoId);
        args.putSerializable(PROGRMAEDUCATIVO_ID, programaEducativoId);
        args.putBoolean(COMPLEJO, complejo);
        Intent intent = new Intent(context, TipoNotaListActivity.class);
        intent.putExtras(args);
        return intent;

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
    protected String getTag() {
        return TipoNotaListActivity.class.getSimpleName();
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected TipoNotaListActivityPresenter getPresenter() {
        return new TipoNotaListActivityPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler())
                ,getResources());
    }

    @Override
    protected TipoNotaListActivityView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_tipo_nota_lista);
        ButterKnife.bind(this);
        setupToolbar();
        setupFragmetContent();
    }

    private void setupFragmetContent() {
        int progrmaEducativoId = 0;
        boolean complejo = false;
        if(getIntent().getExtras() != null){
            progrmaEducativoId = getIntent().getExtras().getInt(PROGRMAEDUCATIVO_ID,0);
            complejo = getIntent().getExtras().getBoolean(COMPLEJO,false);

        }

        tipoNotaListaFragment = TipoNotaListaFragment.newInstance(progrmaEducativoId,complejo, TipoUi.Tipo.SELECTOR_VALORES, TipoUi.Tipo.SELECTOR_ICONOS);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame, tipoNotaListaFragment, "TipoNotaListaFragment")
                .commitNow();
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onSuccessDialogTipoNotaList(int tipoNotaId, String nombre) {

    }

    @Override
    public void onSuccessDialogTipoNotaList(String tipoNotaId, String nombre) {
        presenter.onSelectedTipoNota(tipoNotaId,nombre);
    }

    @Override
    public void onSucces(String tipoNotaId) {
        Intent intent = new Intent();
        intent.putExtra(TIPONOTAID, tipoNotaId);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_rubrica, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_create:
                presenter.onBtnCreateClicked();
                finish();
                break;
            default:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
