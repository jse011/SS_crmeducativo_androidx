package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_teclado_numerico.ui;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.SaveGrupoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.SelectorNumericoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_teclado_numerico.EvaluacionGlobalPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_teclado_numerico.EvaluacionGlobalPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_teclado_numerico.listener.EvaluacionDialogListener;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluacionTecladoNumericoFragment extends DialogFragment implements EvaluacionGlobalView {

    public static Object _alumnosEvaluacionUi;
    public static SelectorNumericoUi _selectorNumericoUi;
    public static int _sesionAprendizajeId;
    public static RubroEvaluacionUi _rubroEvaluacionUi;
    public static List<Object> _alumnosEvaluacionUiList;
    @BindView(R.id.imgProfile)
    CircleImageView imgProfile;
    @BindView(R.id.btnFinalizar)
    Button btnFinalizar;
    @BindView(R.id.btnRetroceder)
    Button btnRetroceder;
    @BindView(R.id.btnAvanzar)
    Button btnAvanzar;
    @BindView(R.id.textView46)
    TextView textView46;
    @BindView(R.id.edtNota)
    TextInputEditText edtNota;
    @BindView(R.id.tilNota)
    TextInputLayout tilNota;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.btn7)
    Button btn7;
    @BindView(R.id.btn8)
    Button btn8;
    @BindView(R.id.btn9)
    Button btn9;
    @BindView(R.id.btnDot)
    Button btnDot;
    @BindView(R.id.btn0)
    Button btn0;
    @BindView(R.id.btnDelete)
    ImageButton btnDelete;
    @BindView(R.id.text_alumn_lastname)
    TextView textAlumnLastname;
    @BindView(R.id.text_alumn_name)
    TextView textAlumnName;

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

    private static final String TAG = EvaluacionTecladoNumericoFragment.class.getSimpleName();

    Unbinder unbinder;

    private EvaluacionGlobalPresenter presenter;

    public EvaluacionTecladoNumericoFragment() {
        // Required empty public constructor
    }

    public static EvaluacionTecladoNumericoFragment newInstance(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, Object alumnosEvaluacionUi, SelectorNumericoUi selectorNumericoUi, List<Object> alumnosEvaluacionUiList) {
        _sesionAprendizajeId = sesionAprendizajeId;
        _rubroEvaluacionUi = rubroEvaluacionUi;
        _alumnosEvaluacionUi = alumnosEvaluacionUi;
        _selectorNumericoUi = selectorNumericoUi;
        _alumnosEvaluacionUiList = alumnosEvaluacionUiList;
        return new EvaluacionTecladoNumericoFragment();
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
        View view = inflater.inflate(R.layout.fragment_evaluacion_teclado_numerico, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupAdapter();
        return view;
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
        EvaluacionDialogListener evaluacionDialogListener = (EvaluacionDialogListener) getTargetFragment();
        evaluacionDialogListener.hideEvaluacion();
        dismiss();
    }

    @Override
    public void showNota(String nota) {
        edtNota.setText(nota);
    }

    @Override
    public void showNotaError(String error) {
        tilNota.setError(null);
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
                new SaveAlumnoEvaluacion(rubroRepository),
                new SaveGrupoEvaluacion(rubroRepository)
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
    public void showName(String name, String firstName) {
        textAlumnName.setText(name);
        textAlumnLastname.setText(firstName);
    }

    @Override
    public void showErrorInvalidNota() {
        tilNota.setError(getString(R.string.evaluacionindividual_msg_error_invalidnota));
    }

    @Override
    public void showRangoEvaluacion(double valMin, double valMax) {
        textView46.setText(Html.fromHtml("Nota de " + valMin + " hasta " + valMax));
    }


    @Override
    public void setHeaderTitle(String title) {
        //txtTitle.setText(title);
    }

    public final static String BTN_0 = "0", BTN_1 = "1", BTN_2 = "2", BTN_3 = "3", BTN_4 = "4", BTN_5 = "5", BTN_6 = "6", BTN_7 = "7", BTN_8 = "8", BTN_9 = "9", BTN_DOT = ".", BTN_DELETE = "x";

    @OnClick({R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot, R.id.btnDelete})
    public void onViewButonClicked(View view) {
        switch (view.getId()) {
            case R.id.btn0:
                presenter.onBtnClicked(BTN_0);
                break;
            case R.id.btn1:
                presenter.onBtnClicked(BTN_1);
                break;
            case R.id.btn2:
                presenter.onBtnClicked(BTN_2);
                break;
            case R.id.btn3:
                presenter.onBtnClicked(BTN_3);
                break;
            case R.id.btn4:
                presenter.onBtnClicked(BTN_4);
                break;
            case R.id.btn5:
                presenter.onBtnClicked(BTN_5);
                break;
            case R.id.btn6:
                presenter.onBtnClicked(BTN_6);
                break;
            case R.id.btn7:
                presenter.onBtnClicked(BTN_7);
                break;
            case R.id.btn8:
                presenter.onBtnClicked(BTN_8);
                break;
            case R.id.btn9:
                presenter.onBtnClicked(BTN_9);
                break;
            case R.id.btnDot:
                presenter.onBtnClicked(BTN_DOT);
                break;
            case R.id.btnDelete:
                presenter.onBtnClicked(BTN_DELETE);
                break;
        }
    }

}
