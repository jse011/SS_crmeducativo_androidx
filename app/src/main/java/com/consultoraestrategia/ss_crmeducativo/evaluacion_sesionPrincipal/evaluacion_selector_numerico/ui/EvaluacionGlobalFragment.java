package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_selector_numerico.ui;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.api.RestAPI;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EquipoEvaluacionProceso.LocalEquipoEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EquipoEvaluacionProceso.RemoteEquipoEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EvaluacionProceso.LocalEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EvaluacionProceso.RemoteEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.rubroEvaluacionProceso.LocalRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.rubroEvaluacionProceso.RemoteRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.local.RubroLocalDataSourse;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.remote.RubroRemoteDataSourse;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.SaveAlumnoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.SelectorNumericoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_selector_numerico.EvaluacionGlobalPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_selector_numerico.EvaluacionGlobalPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluacionGlobalFragment extends DialogFragment implements EvaluacionGlobalView {

    public static AlumnosEvaluacionUi _alumnosEvaluacionUi;
    public static SelectorNumericoUi _selectorNumericoUi;
    public static int _sesionAprendizajeId;
    public static RubroEvaluacionUi _rubroEvaluacionUi;
    public static List<AlumnosEvaluacionUi> _alumnosEvaluacionUiList;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.viewBackground)
    View viewBackground;
    @BindView(R.id.imgProfile)
    ImageView imgProfile;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.btnFinalizar)
    Button btnFinalizar;
    @BindView(R.id.btnRetroceder)
    Button btnRetroceder;
    @BindView(R.id.btnAvanzar)
    Button btnAvanzar;
    @BindView(R.id.numberPicker)
    NumberPicker numberPicker;
    @BindView(R.id.textView46)
    TextView textView46;

    @OnClick({R.id.btnFinalizar, R.id.btnRetroceder, R.id.btnAvanzar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnFinalizar:
                presenter.btnFinalizar();
                break;
            case R.id.btnRetroceder:
                presenter.btnRetroceder();
                break;
            case R.id.btnAvanzar:
                presenter.btnAvanzar();
                break;
        }
    }

    private static final String TAG = EvaluacionGlobalFragment.class.getSimpleName();

    Unbinder unbinder;

    private EvaluacionGlobalPresenter presenter;

    public EvaluacionGlobalFragment() {
        // Required empty public constructor
    }

    public static EvaluacionGlobalFragment newInstance(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi, SelectorNumericoUi selectorNumericoUi, List<AlumnosEvaluacionUi> alumnosEvaluacionUiList) {
        _sesionAprendizajeId = sesionAprendizajeId;
        _rubroEvaluacionUi = rubroEvaluacionUi;
        _alumnosEvaluacionUi = alumnosEvaluacionUi;
        _selectorNumericoUi = selectorNumericoUi;
        _alumnosEvaluacionUiList = alumnosEvaluacionUiList;
        return new EvaluacionGlobalFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        if (presenter != null) {
            presenter.onAttach();
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluacion_selector_numerico, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupAdapter();
        return view;
    }

    @Override
    public void setupNumberPicker(String[] values) {
        numberPicker.setMinValue(0);
        numberPicker.setDisplayedValues(values);
        numberPicker.setMaxValue(values.length - 1);
        //Gets whether the selector wheel wraps when reaching the min/max value.
        numberPicker.setWrapSelectorWheel(true);

        //Set a value change listener for NumberPicker
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
              presenter.onValueChange(newVal, oldVal);
            }
        });
    }

    @Override
    public void selectNumberPickert(int posicion) {
        numberPicker.setValue(posicion);
    }

    @Override
    public void hideBtnNextTextColor() {
        btnAvanzar.setVisibility(View.GONE);
    }

    @Override
    public void hideBtnRetroTextColor() {
        btnRetroceder.setVisibility(View.GONE);
    }

    @Override
    public void showBtnNextTextColor() {
        btnAvanzar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBtnRetroTextColor() {
        btnRetroceder.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDialogo() {
        dismiss();
    }

    private void setupAdapter() {
        RubroRepository rubroRepository = RubroRepository.getInstance(
                new RubroLocalDataSourse(InjectorUtils.provideRubroEvaluacionDao(),
                        InjectorUtils.provideTipoNotaDao(),
                        InjectorUtils.provideIndicadorDao(), InjectorUtils.provideEvaluacionProcesoDao()),
                new RubroRemoteDataSourse(),
                new LocalRubroEvaluacionProceso(),
                new RemoteRubroEvaluacionProceso(),
                new LocalEquipoEvaluacionProceso(),
                new RemoteEquipoEvaluacionProceso(),
                new LocalEvaluacionProceso(),
                new RemoteEvaluacionProceso(new RestAPI())
        );

        presenter = new EvaluacionGlobalPresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new SaveAlumnoEvaluacion(rubroRepository)
        );

        setPresenter(presenter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
        presenter.onViewCreated();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
        presenter.onActivityCreated();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(TAG, "onViewStateRestored");
        //presenter.onViewStateRestored();
    }

    @Override
    public void onStart() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        super.onStart();
        Log.d(TAG, "onStart");
        presenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        presenter.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        presenter.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
        unbinder.unbind();
        presenter.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        presenter.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
        presenter.onDetach();
    }

    @Override
    public void setPresenter(EvaluacionGlobalPresenter presenter) {
        presenter.attachView(this);
        presenter.setExtras();
        presenter.onCreate();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showImageProfile(String uri) {
        Glide.with(getContext())
                .load(uri)
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_account_circle))
                .into(imgProfile);
    }

    @Override
    public void showName(String name) {
        txtName.setText(name);
    }

    @Override
    public void setHeaderTitle(String title) {
        txtTitle.setText(title);
    }


}
