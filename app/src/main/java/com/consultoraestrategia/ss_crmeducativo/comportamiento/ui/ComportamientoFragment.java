package com.consultoraestrategia.ss_crmeducativo.comportamiento.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.ui.DialogCreareComportamiento;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.adapter.CasoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.adapter.ComportamientoAdapter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.ComportamientoAlumnoActivity;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoDataLocalSource;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetComportamientoList;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.listener.ComportamientoListener;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.presenter.ComportamientoPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.presenter.ComportamientoPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ComportamientoFragment extends BaseFragment<ComportamientoView, ComportamientoPresenter, ComportamientoListener> implements ComportamientoView, ComportamientoListener {

    @BindView(R.id.texvacio)
    TextView texvacio;
    Unbinder unbinder1;
    private String TAG = ComportamientoFragment.class.getSimpleName();
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.rc_comportamiento)
    RecyclerView rc_comportamiento;
    Unbinder unbinder;
    ComportamientoAdapter comportamientoAdapter;
    public static ComportamientoFragment newInstance(CRMBundle crmBundle) {
        ComportamientoFragment fragment = new ComportamientoFragment();
        Bundle args = new Bundle();
        args.putAll(crmBundle.instanceBundle());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected ComportamientoPresenter getPresenter() {
        Log.d(TAG, "getPresenter");
        presenter = new ComportamientoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(), new GetComportamientoList(
                new ComportamientoRepository(new ComportamientoDataLocalSource(InjectorUtils.provideComportamientoDao(), InjectorUtils.providePersonaDao(), InjectorUtils.provideCursoDao()
                        , InjectorUtils.provideParametrosDisenioDao(), InjectorUtils.provideSessionUserDao(), InjectorUtils.provideAlumnoDao()))
        ));
        return presenter;
    }

    @Override
    protected ComportamientoView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_comportamiento, container, false);
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder1 = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        CasoColumnGridLayoutManager columnCountProvider = new CasoColumnGridLayoutManager(getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        rc_comportamiento.setLayoutManager(autoColumnGridLayoutManager);
        comportamientoAdapter = new ComportamientoAdapter(new ArrayList<AlumnoUi>(), this);
        rc_comportamiento.setAdapter(comportamientoAdapter);

    }

    @Override
    public void setComportamientos(List<AlumnoUi> alumnoUiList) {
        Log.d(TAG, " setComportamientos   " + alumnoUiList.size());
        comportamientoAdapter.setList(alumnoUiList);
    }

    @Override
    public void onResumenFragment(String idcaledarioPeriodo) {
        Log.d(TAG, "resumen fragment ");
        presenter.onResumeFragment(idcaledarioPeriodo);
    }

    @Override
    public void lauchActivity(CRMBundle crmBundle ,int alumnoId) {
        Intent intent = ComportamientoAlumnoActivity.lauchActivity(getContext(), crmBundle, alumnoId);
        startActivity(intent);
    }

    @Override
    public void onclick(AlumnoUi alumnoUiselected) {
        Log.d(TAG, " onclick   ");
        presenter.setselectedAlumno(alumnoUiselected);
    }

    public void onParentFabClicked() {
        presenter.onFabClicked();

    }

    @Override
    public void lauchDialogCreate(CRMBundle crmbundle) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        DialogCreareComportamiento dialogCreareComportamiento = DialogCreareComportamiento.newInstance(crmbundle, null);
        dialogCreareComportamiento.show(manager, DialogCreareComportamiento.class.getSimpleName());
    }

    @Override
    public void showEmptyText() {
     texvacio.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyText() {
        texvacio.setVisibility(View.GONE);
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume fragment");
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }
}
