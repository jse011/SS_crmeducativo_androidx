package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.tipoNotaLista;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
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
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.listener.TipoNotaListaListener;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.view.TipoNotaListaFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 16/04/2018.
 */

@SuppressLint("Registered")
public class TipoNotaListActivity extends BaseActivity<TipoNotaView, TipoNotaPresenter> implements TipoNotaView, TipoNotaListaListener {
    public static final String TIPONOTAID = "TipoNotaListActivity.tipoNotaId";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.root)
    CoordinatorLayout root;
    public static final String ACTIVITYTITLE = "TipoNotaListActivity.Titulo";

    public static Intent getSeleccionarTipoNotaListaIntent(Context context, String activityTitle, int programaEducativoId, TipoUi.Tipo... tipos) {
        Bundle args = new Bundle();
        args.putSerializable(TipoNotaListaFragment.ENUM_TIPO_TIPONOTAS, tipos);
        args.putSerializable(ACTIVITYTITLE, activityTitle);
        args.putInt("programaEducativoId", programaEducativoId);
        Intent intent = new Intent(context, TipoNotaListActivity.class);
        intent.putExtras(args);
        return intent;

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
    protected TipoNotaPresenter getPresenter() {
        return new TipoNotaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected TipoNotaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.actividad_tipo_nota_lista);
        ButterKnife.bind(this);
        setupToolbar();
        setupFragmetContent();
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

    public void setupFragmetContent() {
        int progrmaEducativoId = 0;
        if(getIntent().getExtras() != null)progrmaEducativoId = getIntent().getExtras().getInt("programaEducativoId",0);
        TipoNotaListaFragment tipoNotaListaFragment = TipoNotaListaFragment.newInstance(progrmaEducativoId, false, TipoUi.Tipo.values());
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
        Log.d(getTag(), "onSuccessDialogTipoNotaList : " + tipoNotaId);
        presenter.onSelectedTipoNota(tipoNotaId, nombre);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_aceptar:
                finish();
                break;
            default:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        getMenuInflater().inflate(R.menu.menu_tipo_nota_aceptar, menu);
        return true;
    }


}
