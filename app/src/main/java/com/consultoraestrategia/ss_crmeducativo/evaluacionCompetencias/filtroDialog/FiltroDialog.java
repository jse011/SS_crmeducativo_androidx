package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtroDialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
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

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.EvaluacionCompetenciasPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtradorDialogFragment.adapter.FiltradoAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtradorDialogFragment.listener.FiltradoCheckItemListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FiltroDialog extends BottomSheetDialogFragment implements FiltradoCheckItemListener,FiltroView {

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
        initVistas();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private FiltradoAdapter filtradoAdapter;
    private EvaluacionCompetenciasPresenter presenter;

    public void hide() {
        dismiss();
    }

    @OnClick({R.id.btn_aceptar, R.id.btn_cancelar})
    public void onClickButton(View view) {
        switch (view.getId()) {
            case R.id.btn_aceptar:
                presenter.onbtnAceptarFiltroDialog();
                break;
            case R.id.btn_cancelar:
                dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return  new BottomSheetDialog(getContext(), getTheme());
    }


    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    private void initVistas() {
        textViewTitulo.setText("Filtros");
        reciclador.setLayoutManager(new LinearLayoutManager(getActivity()));
        filtradoAdapter = new FiltradoAdapter(new ArrayList<FiltradoUi>(), this);
        reciclador.setHasFixedSize(true);
        reciclador.setAdapter(filtradoAdapter);
    }

    @Override
    public void filtradoCheckItemListener(FiltradoUi filtradoUi) {
        presenter.filtradoCheckItemFiltroDialog(filtradoUi);
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setPresenter(EvaluacionCompetenciasPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void setLisFiltroDialog(ArrayList<FiltradoUi> filtradoUiList) {
        filtradoAdapter.setFiltradoList(filtradoUiList);
    }

    @Override
    public void hideDialog() {
        dismiss();
    }
}
