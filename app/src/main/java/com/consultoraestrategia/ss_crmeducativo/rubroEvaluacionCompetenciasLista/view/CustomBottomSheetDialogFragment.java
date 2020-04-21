package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.RubroEvaluacionCompetenciaPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.RubroEvaluacionCompetenciaPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.adapters.CompetenciaAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.listener.CompetenciaListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.listener.RubroEvaluacionCompetenciasListaListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.source.local.RubroEvaluacionProcesoListaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.source.remote.RubroEvaluacionProcesoListaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.useCase.GetCompetenciaList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by SCIEV on 9/02/2018.
 */

public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment implements RubroEvaluacionCompetenciaView, CompetenciaListener {

    private static final double SLIDEOFFSETHIDEN = -0.9f;
    @BindView(R.id.txt_titulo)
    TextView txtTitulo;
    @BindView(R.id.rec_competencia)
    RecyclerView recCompetencia;
    @BindView(R.id.btn_cancelar)
    Button btnCancelar;
    @BindView(R.id.btn_aceptar)
    Button btnAceptar;
    Unbinder unbinder;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.checkCapacidad)
    CheckBox checkCapacidad;
    @BindView(R.id.txtCapacidad)
    TextView txtCapacidad;
    private RubroEvaluacionCompetenciaPresenter presenter;
    private CompetenciaAdapter adapter;
    private RubroEvaluacionCompetenciasListaListener listener;
    private List<Integer> competenciaIdSelecionados;

    public static CustomBottomSheetDialogFragment newInstance() {
        return new CustomBottomSheetDialogFragment();
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            switch (newState) {

                case BottomSheetBehavior.STATE_COLLAPSED: {

                    Log.d("BSB", "collapsed");
                }
                case BottomSheetBehavior.STATE_SETTLING: {

                    Log.d("BSB", "settling");
                }
                case BottomSheetBehavior.STATE_EXPANDED: {

                    Log.d("BSB", "expanded");
                }
                case BottomSheetBehavior.STATE_HIDDEN: {

                    Log.d("BSB", "hidden");
                }
                case BottomSheetBehavior.STATE_DRAGGING: {

                    Log.d("BSB", "dragging");
                }
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            Log.d("BSB", "sliding " + slideOffset);
            if (SLIDEOFFSETHIDEN >= slideOffset) hide();
        }
    };

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.dialog_modal_prueba, null);
        unbinder = ButterKnife.bind(this, contentView);
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }


    @Override
    public void setPresenter(RubroEvaluacionCompetenciaPresenter presenter) {
        presenter.attachView(this);
        presenter.setExtras(getArguments());
        presenter.onCreate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setupPresenter();
        setupAdapter();
        setupListener();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupPresenter() {
        RubroEvaluacionProcesoListaRepository repository = new RubroEvaluacionProcesoListaRepository(
                new RubroEvaluacionProcesoListaLocalDataSource(),
                new RubroEvaluacionProcesoListaRemoteDataSource());
        presenter = new RubroEvaluacionCompetenciaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new GetCompetenciaList(repository));
        setPresenter(presenter);
    }


    private void setupAdapter() {
        adapter = new CompetenciaAdapter(new ArrayList<CompetenciaUi>(), this);
        adapter.setRecyclerView(recCompetencia);
        recCompetencia.setLayoutManager(new LinearLayoutManager(getContext()));
        recCompetencia.setAdapter(adapter);
    }

    @Override
    public void onClickCompetencia(CompetenciaUi competenciaUi) {
        presenter.onClickCompetencia(competenciaUi);
    }

    @Override
    public void showCompetencias(List<CompetenciaUi> competenciaUis) {
        adapter.setItems(competenciaUis);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideDialog() {
        hide();
    }

    @Override
    public void sussesCompetencias(ArrayList<Integer> competenciasId) {
        listener.onSussesRubroEvaluacionCompetenciasLista(competenciasId);
    }

    @Override
    public void onCheckTotal(boolean check) {
        checkCapacidad.setChecked(check);
    }

    public void hide() {
        dismiss();
        listener.onCancelRubroEvaluacionCompetenciasLista();
    }

    private void setupListener() {
        listener = (RubroEvaluacionCompetenciasListaListener) getTargetFragment();
        if (listener != null) return;
        if (getContext() instanceof RubroEvaluacionCompetenciasListaListener) {
            listener = (RubroEvaluacionCompetenciasListaListener) getContext();
        } else {
            throw new ClassCastException(getContext().toString()
                    + " must implement CrearRubroListaIndicadoresListener");
        }
    }

    @OnClick({R.id.btn_cancelar, R.id.btn_aceptar, R.id.checkCapacidad, R.id.txtCapacidad})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancelar:
                presenter.onClickCancelar();
                break;
            case R.id.btn_aceptar:
                presenter.onClickAceptar();
                break;
            case R.id.checkCapacidad:
                break;
            case R.id.txtCapacidad:
                break;
        }
    }

    @OnClick(R.id.checkCapacidad)
    public void onViewClicked() {
        presenter.onClickTodos();
    }
}