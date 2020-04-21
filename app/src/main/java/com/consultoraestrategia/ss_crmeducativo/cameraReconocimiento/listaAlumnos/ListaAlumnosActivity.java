package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.listaAlumnos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.appbar.AppBarLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.LifecycleImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.adapter.CambiarFotoAlumnoAdapter;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.preferent.ReconocimientoRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.CameraReconocimientoRepository;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.local.CameraReconocimientoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.remote.CameraReconocimientoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.GetFotoPreferents;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase.GetPersonas;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.listener.RepositorioItemUpdateListener;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.preview.PreviewActivity;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaAlumnosActivity extends BaseActivity<ListaAlumnosView, ListaAlumnosPresenter> implements ListaAlumnosView, LifecycleImpl.LifecycleListener, RepositorioItemUpdateListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.rc_cambiar_foto_alumnos)
    RecyclerView rcCambiarFotoAlumnos;
    @BindView(R.id.root)
    ConstraintLayout root;
    private CambiarFotoAlumnoAdapter cambiarFotoAlumnoAdapter;

    @Override
    protected String getTag() {
        return "PreviewActivityTAG";
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ListaAlumnosPresenter getPresenter() {
        CameraReconocimientoRepository repository = new CameraReconocimientoRepository(
                new CameraReconocimientoLocalDataSource(InjectorUtils.providePersonaDao()),
                new CameraReconocimientoRemoteDataSource());
        return new ListaAlumnosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetPersonas(repository),
                new GetFotoPreferents(new ReconocimientoRepositoryImpl(this)));
    }

    @Override
    protected ListaAlumnosView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_lista_usuario);
        ButterKnife.bind(this);
        setupToolbar();
        setupAdapter();
        setupSearhcView();
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

    private void setupSearhcView() {
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Busca aquí");
        searchView.clearFocus();
        //adapter = new ListViewAdapter(this, arraylist);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cambiarFotoAlumnoAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void setupAdapter() {
        cambiarFotoAlumnoAdapter = new CambiarFotoAlumnoAdapter(this);
        rcCambiarFotoAlumnos.setAdapter(cambiarFotoAlumnoAdapter);
        rcCambiarFotoAlumnos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

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
    public void onChildsFragmentViewCreated() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onFragmentViewCreated(Fragment f, View view, Bundle savedInstanceState) {

    }

    @Override
    public void onFragmentResumed(Fragment f) {

    }

    @Override
    public void onFragmentViewDestroyed(Fragment f) {

    }

    @Override
    public void onFragmentActivityCreated(Fragment f, Bundle savedInstanceState) {

    }

    @Override
    public void showListPersonas(List<PersonaUi> personaUiList) {
        cambiarFotoAlumnoAdapter.setList(personaUiList);
    }

    @Override
    public void showActivityPreview(int personaId, int cargacursoId, int entidadId, int georeferenciaId) {
        Intent intent = new Intent(this, PreviewActivity.class);
        CRMBundle crmBundle = new CRMBundle(getIntent().getExtras());
        crmBundle.setCargaCursoId(cargacursoId);
        crmBundle.setEntidadId(entidadId);
        crmBundle.setPersonaId(personaId);
        crmBundle.setGeoreferenciaId(georeferenciaId);
        intent.putExtras(crmBundle.instanceBundle());
        startActivity(intent);
    }

    @Override
    public void subirFoto(PersonaUi personaUi) {
        presenter.onClikSubirFoto(personaUi);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
