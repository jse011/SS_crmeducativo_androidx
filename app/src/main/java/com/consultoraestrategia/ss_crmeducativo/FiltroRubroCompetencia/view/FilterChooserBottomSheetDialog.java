package com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.adapters.AdapterFilterChooser;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.listener.FilterChooserCheckItemListener;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.listener.FilterChooserSuccessListener;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.presenter.FilterChooserPresenter;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.presenter.FilterChooserPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by SCIEV on 16/05/2018.
 */

@SuppressLint("ValidFragment")
public class FilterChooserBottomSheetDialog extends BottomSheetDialogFragment implements FilterChooserView, FilterChooserCheckItemListener {

    String TAG = FilterChooserBottomSheetDialog.class.getSimpleName();
    @BindView(R.id.textViewTitulo)
    TextView textViewTitulo;
    @BindView(R.id.textView31)
    View textView31;
    @BindView(R.id.reciclador)
    RecyclerView reciclador;
    @BindView(R.id.btn_cancelar)
    Button btnCancelar;
    @BindView(R.id.btn_aceptar)
    Button btnAceptar;
    Unbinder unbinder;

    private ArrayList<CompetenciaCheck> tipoCompetencia = new ArrayList<>();

    AdapterFilterChooser adapterFilterChooser;
    FilterChooserPresenter presenter;
    FilterChooserSuccessListener listener;

    public FilterChooserBottomSheetDialog(ArrayList<CompetenciaCheck> tipoCompetencia) {
        this.tipoCompetencia = tipoCompetencia;
    }

    public static FilterChooserBottomSheetDialog newInstance(ArrayList<CompetenciaCheck> tipoCompetencia) {
        return new FilterChooserBottomSheetDialog(tipoCompetencia);
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.dialogfilter, null);
        dialog.setContentView(contentView);
        unbinder = ButterKnife.bind(this, contentView);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                double SLIDEOFFSETHIDEN = -0.9f;

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
                    if (SLIDEOFFSETHIDEN >= slideOffset) dismiss();
                }
            });
        }
    }

    private void initListener() {
        listener = (FilterChooserSuccessListener) getTargetFragment();
        if (listener != null) return;
        if (getContext() instanceof FilterChooserSuccessListener) {
            listener = (FilterChooserSuccessListener) getContext();
        } else {
            throw new ClassCastException(getContext().toString()
                    + " error no se puede mostrar el dialog filter");
        }
    }


    private void initPresenter() {
        presenter = new FilterChooserPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()));
        presenter.setExtras(tipoCompetencia);
        setPresenter(presenter);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return  new BottomSheetDialog(getContext(), getTheme());
    }


    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initVistas();
        initPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initVistas() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reciclador.setLayoutManager(layoutManager);
        adapterFilterChooser = new AdapterFilterChooser(this, new ArrayList<CompetenciaCheck>());
        reciclador.setAdapter(adapterFilterChooser);
        textViewTitulo.setText("Filtros");
        reciclador.setHasFixedSize(true);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.btn_aceptar, R.id.btn_cancelar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_aceptar:
                presenter.onAceptar();
                break;
            case R.id.btn_cancelar:
                dismiss();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(ArrayList<CompetenciaCheck> tipoCompetenciaList) {
        listener.onBtnSuccesItemFilter(tipoCompetenciaList);
        dismiss();
    }

    @Override
    public void setList(List<CompetenciaCheck> competenciaCheckList) {
        adapterFilterChooser.setList(competenciaCheckList);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewCreate();
    }

    @Override
    public void setPresenter(FilterChooserPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();

    }

    @Override
    public void filtradoCheckItemListener(CompetenciaCheck tipoCompetencia) {
        presenter.filterChooserCheckItem(tipoCompetencia);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initListener();
    }

}
