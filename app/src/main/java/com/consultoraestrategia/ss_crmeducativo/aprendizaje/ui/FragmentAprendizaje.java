package com.consultoraestrategia.ss_crmeducativo.aprendizaje.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.AprendizajePresenter;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.AprendizajePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.AprendizajeView;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.AprendizajeAdapter;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.AprendizajeRepository;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.local.AprendizajeLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.remote.AprendizajeRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.domain.usecase.GetCompetencias;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.domain.usecase.GetEvidencia;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.domain.usecase.GetRecursosDidacticos;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.domain.usecase.GetSesion;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.domain.usecase.UpdateSuccesDowloadArchivo;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.Extras;
import com.consultoraestrategia.ss_crmeducativo.base.BaseTabFragmentView;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapterDownload.adapter.DownloadItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.local.RepositorioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.preferents.RepositorioPreferentsDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote.RepositorioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.view.TabsSesionesActivityV2;
import com.consultoraestrategia.ss_crmeducativo.util.OpenIntents;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by irvinmarin on 23/02/2017.
 */

public class FragmentAprendizaje extends Fragment implements AprendizajeView, BaseTabFragmentView, DownloadItemListener {

    private final String TAG = "FragmentAprendizaje";
    AprendizajePresenter presenter;
    Unbinder unbind;
    AprendizajeAdapter adapter;
    View viewPadre;
    @BindView(R.id.rc_aprendizaje)
    RecyclerView rcAprendizaje;
    @BindView(R.id.progressBar4)
    ProgressBar progressBar4;

    public static FragmentAprendizaje newInstance(Bundle args) {
        FragmentAprendizaje fragment = new FragmentAprendizaje();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPadre = inflater.inflate(R.layout.layout_aprendizaje, container, false);
        unbind = ButterKnife.bind(this, viewPadre);
        setupPresenter();
        setupAdapter();
        presenter.onCreateView();
        return viewPadre;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewCreated();
    }

    @Override
    public void setPresenter(AprendizajePresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
    }

    void setupPresenter() {
        presenter = new AprendizajePresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new GetSesion(AprendizajeRepository.getInstance(
                        new AprendizajeLocalDataSource(),
                        new AprendizajeRemoteDataSource()
                )),
                new GetCompetencias(AprendizajeRepository.getInstance(
                        new AprendizajeLocalDataSource(),
                        new AprendizajeRemoteDataSource()
                )),

                new GetRecursosDidacticos(AprendizajeRepository.getInstance(
                        new AprendizajeLocalDataSource(),
                        new AprendizajeRemoteDataSource()
                )),
                new DowloadImageUseCase(new RepositorioRepository(
                        new RepositorioLocalDataSource(),
                        new RepositorioPreferentsDataSource(),
                        new RepositorioRemoteDataSource(getContext())
                )),
                new UpdateSuccesDowloadArchivo(AprendizajeRepository.getInstance(
                        new AprendizajeLocalDataSource(),
                        new AprendizajeRemoteDataSource()
                )),
                new GetEvidencia(AprendizajeRepository.getInstance(
                        new AprendizajeLocalDataSource(),
                        new AprendizajeRemoteDataSource()
                ))
        );
        Bundle bundle = getArguments();
        presenter.setExtras(new Extras(bundle.getInt(TabsSesionesActivityV2.INT_SESIONAPRENDIZAJEID)));
        setPresenter(presenter);
    }

    //#region ciclo de vida

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onActivityCreated();
        Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
        Log.d(TAG, "onDestroyView");
        unbind.unbind();
    }
//#endregion ciclo de vida

    void setupAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapter = new AprendizajeAdapter(new ArrayList<>(), this, rcAprendizaje);
        rcAprendizaje.setAdapter(adapter);
        rcAprendizaje.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void addAprendizaje(Object item) {
        adapter.addAprendizaje(item);
    }

    @Override
    public void setAprendizaje(List<Object> aprendizaje) {
        adapter.setAprendizaje(aprendizaje);
    }

    @Override
    public void clearAprendizaje() {
        adapter.clearAprendizaje();
    }

    @Override
    public void showProgress() {
        progressBar4.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar4.setVisibility(View.GONE);
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
    public void setUpdate(RepositorioFileUi repositorioFileUi) {
        adapter.update(repositorioFileUi);
    }

    @Override
    public void setUpdateProgress(RepositorioFileUi repositorioFileUi, int count) {
        adapter.updateProgress(repositorioFileUi, count);
    }


    @Override
    public void onResumeFragment() {
        presenter.onResumeFragment();
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
}
