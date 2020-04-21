package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtradorDialogFragment;

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
import android.widget.TextView;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtradorDialogFragment.adapter.FiltradoAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtradorDialogFragment.listener.FiltradoCheckItemListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtradorDialogFragment.listener.FiltradoSuccessListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by kike on 11/04/2018.
 */

public class FiltradoDialogFragment extends BottomSheetDialogFragment implements FiltradoView, FiltradoCheckItemListener {

    private static String FILTRADO_DIALOG_TAG = FiltradoDialogFragment.class.getSimpleName();
    private static final double SLIDEOFFSETHIDEN = -0.9f;

    Unbinder unbinder;
    @BindView(R.id.reciclador)
    RecyclerView reciclador;
    @BindView(R.id.textViewTitulo)
    TextView textViewTitulo;
    @BindView(R.id.btn_cancelar)
    Button buttonCancelar;
    @BindView(R.id.btn_aceptar)
    Button buttonAceptar;
    FiltradoAdapter filtradoAdapter;
    FiltradoPresenter presenter;
    FiltradoSuccessListener listener;

    public static FiltradoDialogFragment newInstance() {
        return new FiltradoDialogFragment();
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
        View contentView = View.inflate(getContext(), R.layout.filtrado_dialog_fragment, null);
        unbinder = ButterKnife.bind(this, contentView);
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initPresenter();
        initVistas();
        initListener();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initListener() {
        listener = (FiltradoSuccessListener) getTargetFragment();
        if (listener != null) return;
        if (getContext() instanceof FiltradoSuccessListener) {
            listener = (FiltradoSuccessListener) getContext();
        } else {
            throw new ClassCastException(getContext().toString()
                    + " must implement CrearRubroListaIndicadoresListener");
        }
    }

    private void initVistas() {
        textViewTitulo.setText("Tipo Competencia");
        reciclador.setLayoutManager(new LinearLayoutManager(getActivity()));
        filtradoAdapter = new FiltradoAdapter(new ArrayList<FiltradoUi>(), this);
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(filtradoAdapter);
    }

    private void initPresenter() {
        presenter = new FiltradoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()));
        setPresenter(presenter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
        unbinder.unbind();
    }

    public void hide() {
        dismiss();
        // listener.onCancelRubroEvaluacionCompetenciasLista();
    }

    @Override
    public void setPresenter(FiltradoPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
        //  presenter.cargarLista(new ArrayList<String>());
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void mostrarFiltro(List<FiltradoUi> filtradoUiList) {
        filtradoAdapter.setFiltradoList(filtradoUiList);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(FiltradoUi filtradoUi) {
        listener.onBtnSuccesItemFilter(filtradoUi);
        dismiss();
    }

    @Override
    public void filtradoCheckItemListener(FiltradoUi filtradoUi) {
        presenter.filtradoCheckItemListener(filtradoUi);
        Log.d(FILTRADO_DIALOG_TAG, "filtradoUi : " + filtradoUi.isaBoolean());
    }

    @OnClick({R.id.btn_aceptar, R.id.btn_cancelar})
    public void onClickButton(View view) {
        switch (view.getId()) {
            case R.id.btn_aceptar:
                presenter.onbtnAceptar();
                break;
            case R.id.btn_cancelar:
                dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
