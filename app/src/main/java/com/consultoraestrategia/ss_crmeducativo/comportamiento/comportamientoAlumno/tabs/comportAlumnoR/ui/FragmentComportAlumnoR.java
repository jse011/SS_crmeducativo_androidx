package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoR.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoR.adapters.ComportDestinosAdpter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoR.presenter.ComportAlumnoRPreImpl;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoR.presenter.ComportAlumnoRPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoDataLocalSource;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetComportamientosDestinos;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentComportAlumnoR extends BaseFragment<ComportAlumnoRview, ComportAlumnoRPresenter, ListenerComportAlumnoR> implements ComportAlumnoRview {
    @BindView(R.id.rc_alumno_com_destinos)
    RecyclerView rcAlumnoComDestinos;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.textViewEmpty)
    TextView textViewEmpty;
    Unbinder unbinder;

    private String TAG = FragmentComportAlumnoR.class.getSimpleName();
    ComportDestinosAdpter comportamiDestinosAdpter;

    public static FragmentComportAlumnoR newInstance(Bundle bndle) {
        FragmentComportAlumnoR fragment = new FragmentComportAlumnoR();
        fragment.setArguments(bndle);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected ComportAlumnoRPresenter getPresenter() {
        ComportamientoRepository comportamientoRepository = new ComportamientoRepository(new ComportamientoDataLocalSource(
                InjectorUtils.provideComportamientoDao(), InjectorUtils.providePersonaDao(),
                InjectorUtils.provideCursoDao(), InjectorUtils.provideParametrosDisenioDao(), InjectorUtils.provideSessionUserDao(), InjectorUtils.provideAlumnoDao()));

        presenter = new ComportAlumnoRPreImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(), new GetComportamientosDestinos(comportamientoRepository));
        return presenter;
    }

    @Override
    protected ComportAlumnoRview getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_comport_destinos, container, false);
        return view;
    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void showList(List<ComportamientoUi> comportamientoUiList) {

        textViewEmpty.setVisibility(View.GONE);
        comportamiDestinosAdpter.setComportamientoUiList(comportamientoUiList);
    }

    @Override
    public void showEmptyText() {
        textViewEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initadapter();
    }

    private void initadapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcAlumnoComDestinos.setLayoutManager(layoutManager);
        comportamiDestinosAdpter = new ComportDestinosAdpter(new ArrayList<ComportamientoUi>());
        rcAlumnoComDestinos.setAdapter(comportamiDestinosAdpter);
        rcAlumnoComDestinos.setHasFixedSize(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }
}
