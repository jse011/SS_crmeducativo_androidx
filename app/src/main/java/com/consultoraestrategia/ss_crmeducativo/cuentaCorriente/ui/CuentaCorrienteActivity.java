package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.CuentaCorrientePresenter;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.CuentaCorrientePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.CuentaCorrienteView;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.adapter.CuentaCorrienteAdapter;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.CuentaCorrienteRepository;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.local.CuentaCorrienteLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.remote.CuentaCorrienteRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.domainusecase.GetAniosAcademicosUI;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.domainusecase.GetCuentaCoUI;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.domainusecase.GetPersona;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.entities.CuentaCoUI;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.lib.GlideImageLoader;
import com.consultoraestrategia.ss_crmeducativo.lib.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by irvinmarin on 11/10/2017.
 */

public class CuentaCorrienteActivity extends AppCompatActivity implements CuentaCorrienteView {
    public static final String EXTRA_ID_ALUMNO = "EXTRA_ID_ALUMNO";
    public static final String URL_IMG_PROFILE = "URL_IMG_PROFILE";
    private static final String TAG = CuentaCorrienteActivity.class.getSimpleName();

    @BindView(R.id.txtNombreAlumno)
    TextView txtNombreAlumno;
    @BindView(R.id.spnAnio)
    Spinner spnAnio;
    @BindView(R.id.rvCuentaCorriente)
    RecyclerView rvCuentaCorriente;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgImgProfileAlumno)
    ImageView imgImgProfileAlumno;
    @BindView(R.id.pbCuentaCorriente)
    ProgressBar pbCuentaCorriente;

    CuentaCorrientePresenter presenter;
    ImageLoader imageLoader;
    @BindView(R.id.txtMensajeDeuda)
    TextView txtMensajeDeuda;

    public static void launchCuentaCorrienteActivity(Context context, int idAlumno, String UrlImageProfile) {
        Intent intent = new Intent(context, CuentaCorrienteActivity.class);
        intent.putExtra(EXTRA_ID_ALUMNO, idAlumno);
        intent.putExtra(URL_IMG_PROFILE, UrlImageProfile);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cuenta_corriente);
        ButterKnife.bind(this);
        setupImageLoader();
        setupToolbar();
        setupPresenter();
        setupSpinner();

        SetupRecycler();
    }

    private void setupSpinner() {

    }

    private void setupImageLoader() {
        imageLoader = new GlideImageLoader(this);
    }

    @Override
    public void setUrlImgProfile(String urlImgProfile) {
        imageLoader.loadWithCircular(imgImgProfileAlumno, urlImgProfile);
    }

    private void setupToolbar() {
        toolbar.setTitle("Cuenta Corriente");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupPresenter() {
        presenter = (CuentaCorrientePresenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new CuentaCorrientePresenterImpl(
                    new UseCaseHandler(
                            new UseCaseThreadPoolScheduler()),
                    new GetPersona(CuentaCorrienteRepository.getInstance(
                            new CuentaCorrienteLocalDataSource(),
                            new CuentaCorrienteRemoteDataSource())
                    ),
                    new GetCuentaCoUI(CuentaCorrienteRepository.getInstance(
                            new CuentaCorrienteLocalDataSource(),
                            new CuentaCorrienteRemoteDataSource())
                    ),
                    new GetAniosAcademicosUI(CuentaCorrienteRepository.getInstance(
                            new CuentaCorrienteLocalDataSource(),
                            new CuentaCorrienteRemoteDataSource())
                    )
            );
            presenter.setExtras(getIntent().getExtras());
        }
        setPresenter(presenter);
        Log.d(TAG, "onCreate");
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cuenta_corriente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    CuentaCorrienteAdapter cuentaCorrienteAdapter;

    private void SetupRecycler() {
        cuentaCorrienteAdapter = new CuentaCorrienteAdapter(new ArrayList<CuentaCoUI>());
        rvCuentaCorriente.setLayoutManager(new LinearLayoutManager(this));
        rvCuentaCorriente.setAdapter(cuentaCorrienteAdapter);
    }

    @Override
    public void setPresenter(CuentaCorrientePresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
    }

    @Override
    public void setCuentaCorrienteList(List<CuentaCoUI> cuentaCoUIList) {
        cuentaCorrienteAdapter.setCuentaCorrientes(cuentaCoUIList);
    }

    @Override
    public void setNombreAlumno(Persona persona) {
        txtNombreAlumno.setText(
                persona.getApellidoPaterno() + " " +
                        persona.getApellidoMaterno() + " " +
                        persona.getNombres());
    }

    @Override
    public void setAniosAcademicosList(final List<String> aniosAcademicosList) {
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, aniosAcademicosList);
        spnAnio.setAdapter(arrayAdapter);
        spnAnio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.findCuotas(aniosAcademicosList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Log.d(TAG, "aniosAcademicosList");
    }

    @Override
    public void showProgress() {
        pbCuentaCorriente.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbCuentaCorriente.setVisibility(View.GONE);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void showMessageDeuda(String msj, double restante) {
        txtMensajeDeuda.setVisibility(View.VISIBLE);
        txtMensajeDeuda.setText("Usted tiene una deuda de : " + Double.toString(restante));
    }

    @Override
    public void hideMessageDeuda() {
        txtMensajeDeuda.setVisibility(View.GONE);
    }
}
