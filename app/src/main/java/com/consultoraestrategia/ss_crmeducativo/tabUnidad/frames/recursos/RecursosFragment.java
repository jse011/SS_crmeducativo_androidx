package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.recursos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.local.RepositorioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.preferents.RepositorioPreferentsDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote.RepositorioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadRepository;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.local.UnidadLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetRecursoDidactico;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.UpdateSuccesDowloadArchivo;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RecursosDidacticoUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.FragmentListener;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.OpenIntents;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecursosFragment extends BaseFragment<RecursosView, RecursosPresenter, FragmentListener> implements RecursosView, DownloadItemListener {


    @BindView(R.id.rvRecursoDidactico)
    RecyclerView rvRecursosDidacticos;
    @BindView(R.id.fragmentRecursos)
    ConstraintLayout fragmetnRecursos;
    @BindView(R.id.progresRecursos)
    ProgressBar progresBar;
    private DownloadAdapter adapter;

    public static RecursosFragment newInstance(Bundle bundle) {
        RecursosFragment fragment = new RecursosFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return RecursosFragment.class.getSimpleName();
    }

    @Override
    protected RecursosPresenter getPresenter() {
        UnidadRepository repository = new UnidadRepository(new UnidadLocalDataSource(InjectorUtils.provideRubroEvalRNPFormulaDao()));
        RepositorioRepository repositorioRepository = new RepositorioRepository(
                new RepositorioLocalDataSource(),
                new RepositorioPreferentsDataSource(),
                new RepositorioRemoteDataSource(getContext()));
        presenter = new RecursosPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetRecursoDidactico(repository),
                new DowloadImageUseCase(repositorioRepository),
                new UpdateSuccesDowloadArchivo());
        return presenter;
    }

    @Override
    protected RecursosView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_recursos_unidad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();
    }

    private void setupAdapter() {
        adapter = new DownloadAdapter(this,rvRecursosDidacticos);
        rvRecursosDidacticos.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRecursosDidacticos.setAdapter(adapter);
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
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void showRecursoDidactico(List<RecursosDidacticoUi> recursosDidacticoUis) {
        adapter.setList((new ArrayList<RepositorioFileUi>(recursosDidacticoUis)));
    }

    @Override
    public void setUpdateProgress(RepositorioFileUi repositorioFileUi, int count) {
        adapter.updateProgress(repositorioFileUi, count);
    }

    @Override
    public void setUpdate(RepositorioFileUi repositorioFileUi) {
        adapter.update(repositorioFileUi);
    }

    @Override
    public void showVinculo(String url) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error al abrir el vínculo", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showYoutube(String url) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error al abrir el vínculo de youtube", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void leerArchivo(String path) {
        try {
            if (!TextUtils.isEmpty(path))
                OpenIntents.openFile(FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", new File(path)), getContext());

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(), getContext().getString(R.string.cannot_open_file),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgresBar() {
        progresBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progresBar.setVisibility(View.GONE);
    }

    @Override
    public void onClickDownload(RepositorioFileUi repositorioFileUi) {
        presenter.onClickDownload(repositorioFileUi);
    }

    @Override
    public void onClickClose(RepositorioFileUi repositorioFileUi) {
        presenter.onClickClose(repositorioFileUi);
    }

    @Override
    public void onClickArchivo(RepositorioFileUi repositorioFileUi) {
        presenter.onClickArchivo(repositorioFileUi);
    }

    public void showRecursosFragment() {
        fragmetnRecursos.setVisibility(View.VISIBLE);
    }

    public void hideRecursosFragment() {
        fragmetnRecursos.setVisibility(View.GONE);
    }
}
