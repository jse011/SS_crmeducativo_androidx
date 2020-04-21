package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.situacion;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.SItuacionAdapter;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadRepository;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.local.UnidadLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetSituacion;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.SituacionUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.FragmentListener;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SituacionFragment extends BaseFragment<SituacionView, SituacionPresenter, FragmentListener> implements SituacionView {


    @BindView(R.id.txtSemana)
    TextView txtSemana;
    @BindView(R.id.txtSesion)
    TextView txtSesion;
    @BindView(R.id.txtHoras)
    TextView txtHoras;
    @BindView(R.id.imgSemanas)
    ImageView imgSemana;
    @BindView(R.id.imgSesiones)
    ImageView imgSesiones;
//    @BindView(R.id.imgHoras)
//    ImageView imgHoras;
    @BindView(R.id.rvSituacion)
    RecyclerView rvSituacion;
    @BindView(R.id.fragmentSituaicon)
    ConstraintLayout fragmentSituaicon;
    @BindView(R.id.progresBar)
    ProgressBar progressBar;
    SItuacionAdapter situacionAdapter;

    public static SituacionFragment newInstance(Bundle bundle) {
        SituacionFragment fragment = new SituacionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return SituacionFragment.class.getSimpleName();
    }

    @Override
    protected SituacionPresenter getPresenter() {
        UnidadRepository repository = new UnidadRepository(new UnidadLocalDataSource(InjectorUtils.provideRubroEvalRNPFormulaDao()));
        presenter = new SituacionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetSituacion(repository));
        return presenter;
    }

    @Override
    protected SituacionView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_situacion_unidad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();
    }

    private void setupAdapter() {
        situacionAdapter = new SItuacionAdapter(new ArrayList<SituacionUi>());
        rvSituacion.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSituacion.setAdapter(situacionAdapter);
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
    public void showSituacion(List<SituacionUi> situacionUis) {
        situacionAdapter.setSituacionUiList(situacionUis);
    }

    @Override
    public void showSituacionBox(SituacionUi situacionUi) {
        txtHoras.setText("H: "+situacionUi.getHoras());
        txtSemana.setText("Semanas: "+situacionUi.getSemana());
        txtSesion.setText("Session: "+situacionUi.getSessiones());
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    public void showSituacionFragment() {
        fragmentSituaicon.setVisibility(View.VISIBLE);
    }

    public void hideSituacionFragment() {
        fragmentSituaicon.setVisibility(View.GONE);
    }
}
