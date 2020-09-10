package com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
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
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.CambiarFotoAlumnoPresenter;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.CambiarFotoPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.adapter.CambiarFotoAlumnoAdapter;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data.CambiarFotoRepository;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data.local.CambiarFotoLocalDataSourse;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data.remote.CambiarFotoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.domain.usecase.GetPersona;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.domain.usecase.GetUploadImagen;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.domain.usecase.Save;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.domain.usecase.SavePersona;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.listener.RepositorioItemUpdateListener;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.iceteck.silicompressorr.SiliCompressor;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CambiarFotoAlumnoActivity extends BaseActivity<CambiarFotoAlumnoView, CambiarFotoAlumnoPresenter> implements CambiarFotoAlumnoView, RepositorioItemUpdateListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.rc_cambiar_foto_alumnos)
    RecyclerView rcCambiarFotoAlumnos;
    @BindView(R.id.searchView)
    SearchView searchView;
    private CambiarFotoAlumnoAdapter cambiarFotoAlumnoAdapter;


    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected CambiarFotoAlumnoPresenter getPresenter() {
        CambiarFotoRepository cambiarFotoRepository = new CambiarFotoRepository(
                new CambiarFotoLocalDataSourse(InjectorUtils.providePersonaDao()),
                new CambiarFotoRemoteDataSource());
        return new CambiarFotoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetPersona(cambiarFotoRepository),
                new SavePersona(cambiarFotoRepository),
                new GetUploadImagen(cambiarFotoRepository));
    }

    @Override
    protected CambiarFotoAlumnoView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_cambiar_foto_alumno);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupAdapter();
        setupSearhcView();
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


    private void setupToolbar() {
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //change color of status bar
            Window window = this.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.md_white_1000));
        }
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
        return progressBar;
    }

    @Override
    public void showListPersonas(List<PersonaUi> personaUiList) {


        // Binds the Adapter to the ListView
        ///list.setAdapter(adapter);
        cambiarFotoAlumnoAdapter.setList(personaUiList);
    }

    @Override
    public void btnAddFoto(PersonaUi personaUi) {
        presenter.btnAddFoto(personaUi);
    }

    @Override
    public void subirFoto(PersonaUi personaUi) {
        presenter.subirFoto(personaUi);
    }

    @Override
    public void startCropImageActivity(String path) {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .setAspectRatio(1, 1).start(this);
    }

    @Override
    public void updatePersona(PersonaUi value) {
        cambiarFotoAlumnoAdapter.update(value);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(getTag(), "onActivityResult: " + requestCode + ", resultCode: " + resultCode + ", data: " + data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == Activity.RESULT_OK) {
                Log.d(getTag(), "uri: " + result.getUri());
                comprimirImagen(result.getUri());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                showMessage(getResources().getString(R.string.unknown_error));
            }
            //return;
        }
    }

    private void comprimirImagen(Uri uri) {

        try {
            Bitmap imageBitmap = SiliCompressor.with(this).getCompressBitmap(uri.getPath());
            Save save = new Save();
            String filePath = save.SaveImage(this, imageBitmap);
            presenter.onCropImageActivityResult(filePath);
        } catch (IOException e) {
            showMessage("Error al guardar la foto");
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
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

    @Override
    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if(connectivityManager!=null)activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
