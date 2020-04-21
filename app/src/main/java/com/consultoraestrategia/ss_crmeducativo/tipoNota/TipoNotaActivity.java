package com.consultoraestrategia.ss_crmeducativo.tipoNota;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioAccesoUI;
import com.consultoraestrategia.ss_crmeducativo.main.ui.MainActivity;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.adapters.TipoNotaAdapter;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.ui.CrearTipoNotaActivity;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.data.source.TipoNotaRepository;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.data.source.local.TipoNotaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.data.source.remote.TipoNotaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.domainUseCase.GetTipoNotaList;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoNotaUi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TipoNotaActivity extends BaseActivity<TipoNotaView, TipoNotaPresenter> implements TipoNotaView {


    public String TIPONOTA_TAG = TipoNotaActivity.class.getSimpleName();


    @BindView(R.id.recycler)
    RecyclerView reciclador;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /*
     @BindView(R.id.appbar)
     AppBarLayout appbar;
     @BindView(R.id.contendoPrincipal)
     CoordinatorLayout contendoPrincipal;
     TipoNotaAdapter tipoNotaAdapter;*/

    TipoNotaAdapter tipoNotaAdapter;


    public static void startActivity(Context context, UsuarioAccesoUI usuarioAccesoUI) {
        Intent intent = new Intent(context, TipoNotaActivity.class);
        Bundle args = new Bundle();
    //    args.putString("nombreAcceso", usuarioAccesoUI.getNombreAcceso());
 //       args.putInt("idAcceso", usuarioAccesoUI.getIdAcceso());
        args.putInt("idPersona", usuarioAccesoUI.getIdPersona());
        args.putInt("idUsuario", usuarioAccesoUI.getIdUsuario());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtras(args);
        context.startActivity(intent, args);
    }


    @Override
    protected String getTag() {
        return TIPONOTA_TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected TipoNotaPresenter getPresenter() {
        TipoNotaRepository tipoNotaRepository = new TipoNotaRepository(new TipoNotaLocalDataSource(), new TipoNotaRemoteDataSource());
        return new TipoNotaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources()
                , new GetTipoNotaList(tipoNotaRepository));
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
        setContentView(R.layout.activity_tipo_nota);
        ButterKnife.bind(this);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        initVistas();
    }

    private void initVistas() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);/*
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        gridLayoutManager.getOrientation());*/


        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                reciclador.getContext(),
                DividerItemDecoration.VERTICAL
        );
        tipoNotaAdapter = new TipoNotaAdapter(new ArrayList<TipoNotaUi>());
        reciclador.setHasFixedSize(true);
        reciclador.addItemDecoration(mDividerItemDecoration);


        /*recyclerView.addItemDecoration(new ItemDecorationColumns(
                getResources().getDimensionPixelSize(R.dimen.table_width),
                getResources().getInteger(R.integer.evaluacion_individual_edtnota_ime_action_id)));*/
        reciclador.setLayoutManager(gridLayoutManager);
        reciclador.setAdapter(tipoNotaAdapter);
    }


    @Override
    public void mostrarLista(List<TipoNotaUi> tipoNotaUiList) {
        tipoNotaAdapter.showListTipoNota(tipoNotaUiList);
    }

    @Override
    public void regresoMainActivity(int idUsuario) {
        Intent intent  = MainActivity.launchMainActivity(this, idUsuario);
        startActivity(intent);
    }


    @Override
    public void extras(int idUsuario) {
        Intent intent  = new Intent(this,CrearTipoNotaActivity.class);
        intent.putExtra("usuarioId",idUsuario);
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* switch (item.getItemId()) {
            case android.R.id.action_profile:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/
        switch (item.getItemId()) {
            default:
                Log.d(TIPONOTA_TAG, "onBackPressed");
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @OnClick(R.id.fab)
    public void onClickAgregar() {
        Log.d(TIPONOTA_TAG, "onClickAgregar ");
        presenter.showActivityCrearTipoNota();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        return true;

    }
}
