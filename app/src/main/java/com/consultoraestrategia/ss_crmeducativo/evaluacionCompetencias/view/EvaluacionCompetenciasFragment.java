package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.BaseTabFragmentView;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.LifecycleImpl;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.EvaluacionCompetenciasPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.EvaluacionCompetenciasPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.EvaluacionCompetenciaAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.listener.EvaluacionCompetenciaListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.listener.RubricaFragmentListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.EvaluacionCompetenciaRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.local.EvaluacionCompetenciaLocal;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.remote.EvaluacionCompetenciaRemote;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase.AnclarResultados;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase.ChangeToogle;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase.GetCompetenciasLista;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase.GetEnfoqueTransVersalLista;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase.UseEvaluado;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase.ValidarAnclaCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.HijosFinalBimestreUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtroDialog.FiltroDialog;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtroDialog.FiltroView;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.ui.EvaluacionResultadoActivity;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.util.RepositoryInjector;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.view.activities.TabsCursoDocenteActivity;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;

/**
 * Created by CCIE on 09/03/2018.
 */

public class EvaluacionCompetenciasFragment extends BaseFragment<EvaluacionCompetenciasView, EvaluacionCompetenciasPresenter, RubricaFragmentListener> implements EvaluacionCompetenciasView, EvaluacionCompetenciaListener, BaseTabFragmentView, View.OnClickListener, LifecycleImpl.LifecycleListener {

    private static final String TAG = EvaluacionCompetenciasFragment.class.getSimpleName();
    private static String EVALUACION_COMPENTENCIAS_FRAGMENT_TAG = EvaluacionCompetenciasFragment.class.getSimpleName();
    private static int TIPO_CAPACIDAD = 0, TIPO_COMPETENCIA = 1, TIPO_FINAL = 2;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recicladorCompentencias)
    RecyclerView recicladorEvaluacion;
    @BindView(R.id.textViewMensaje)
    TextView textViewMensaje;
    EvaluacionCompetenciaAdapter evaluacionCompetenciaAdapter;
    @BindView(R.id.cerrarCurso)
    TextView cerrarCurso;
    @BindView(R.id.viewGroup)
    LinearLayout viewGroup;

    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.constraintLayoutRubrica)
    ConstraintLayout constraintLayoutRubrica;
    @BindView(R.id.bntAnclaTotal)
    ImageView bntAnclaTotal;
    @BindView(R.id.anclatext)
    TextView anclatext;
    @BindView(R.id.msg_calendario)
    TextView msgCalendario;
    @BindView(R.id.btn_cerrar)
    ImageView btnCerrar;
    @BindView(R.id.card)
    CardView cardCerrrar;


    public static EvaluacionCompetenciasFragment newInstance(Bundle args) {
        EvaluacionCompetenciasFragment fragment = new EvaluacionCompetenciasFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return EVALUACION_COMPENTENCIAS_FRAGMENT_TAG;
    }

    @Override
    protected EvaluacionCompetenciasPresenter getPresenter() {
        EvaluacionCompetenciaRepository competenciaRepository = new EvaluacionCompetenciaRepository(
                new EvaluacionCompetenciaLocal(InjectorUtils.provideRubroEvaluacionResultadoDao(), InjectorUtils.provideParametrosDisenioDao()
                        , InjectorUtils.provideCalendarioPeriodo(),
                        InjectorUtils.providePersonaDao()),
                new EvaluacionCompetenciaRemote());
        return new EvaluacionCompetenciasPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new GetCompetenciasLista(competenciaRepository,
                        RepositoryInjector.getBEDatosEvaluacionResultadoRepository()),
                new GetEnfoqueTransVersalLista(competenciaRepository),
                new UseEvaluado(competenciaRepository),
                new ChangeToogle(competenciaRepository),
                new AnclarResultados(competenciaRepository),
                new ValidarAnclaCalendarioPeriodo(competenciaRepository));
        //camb
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getChildFragmentManager().registerFragmentLifecycleCallbacks(new LifecycleImpl(0,this), true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  super.onCreateView(inflater, container, savedInstanceState);
        bntAnclaTotal.setOnClickListener(this);
        return  view;
    }

    @Override
    protected EvaluacionCompetenciasView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.layout_evaluacion_competencias, container, false);

    }

    @Override
    protected ViewGroup getRootLayout() {
        return constraintLayoutRubrica;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void mostrarObjectLista(List<Object> objectList) {

        evaluacionCompetenciaAdapter.setObjectLista(objectList);
    }

    @Override
    public void mostrarListaCompetencia(int size) {

    }

    @Override
    public void ocultaListaCompetencia() {

    }

    @Override
    public void mostrarMensaje(String mensaje) {
        textViewMensaje.setVisibility(View.VISIBLE);
        textViewMensaje.setText(mensaje);
    }

    @Override
    public void ocultarMensaje() {
        textViewMensaje.setVisibility(View.GONE);
    }

    @Override
    public void actualizarCapacidad(Object o) {
        evaluacionCompetenciaAdapter.updatObject(o);
    }

    @Override
    public void startEvalResultActivity(Object object) {
        Log.d(TAG, "startEvalResultActivity");

        try {
            if (object instanceof CapacidadUi) {
                CapacidadUi capacidadUi = (CapacidadUi) object;
                int rubroEvalResId = Integer.parseInt(capacidadUi.getRubroEvaluacionId());
                switch (capacidadUi.getTipo()) {
                    case DEFAULT:
                        break;
                    case RESULTADO_CAPACIDAD:
                        EvaluacionResultadoActivity.launch(getActivity(), getArguments(), rubroEvalResId, TIPO_CAPACIDAD);//true
                        break;
                    case RESULTADO_COMPETENCIA:
                        EvaluacionResultadoActivity.launch(getActivity(), getArguments(), rubroEvalResId, TIPO_COMPETENCIA);//false
                        break;

                }
            } else {
                HijosFinalBimestreUi hijosFinalBimestreUi = (HijosFinalBimestreUi) object;
                switch (hijosFinalBimestreUi.getTipoFinalBimestre()) {
                    case TIPO_FINAL_BASE:
                        Log.d(TAG, "rubroEval ID " + hijosFinalBimestreUi.getRubroEvalId());
                        EvaluacionResultadoActivity.launch(getActivity(), getArguments(), hijosFinalBimestreUi.getRubroEvalId(), TIPO_COMPETENCIA);//false
                        break;
                    case TIPO_FINAL_BIMESTRE:
                        Log.d(TAG, "rubroEval padre " + hijosFinalBimestreUi.getRubroEvalId());
                        EvaluacionResultadoActivity.launch(getActivity(), getArguments(), hijosFinalBimestreUi.getRubroEvalId(), TIPO_FINAL);//false
                        break;
                }

            }

        } catch (Exception ex) {
            showMessage(getResources().getString(R.string.unknown_error));
        }
    }

    @Override
    public void changeFragmentRubros(int calendarioPeriodoId, boolean calendarioActivo) {

    }

    @Override
    public void showDialogWaitAncla(AlertDialog alertDialog) {
        alertDialog.show();
    }

    @Override
    public void hideAlerDialogwaitAncla(AlertDialog alertDialog) {
        alertDialog.dismiss();
    }

    @Override
    public void setParametroDisenio(String color) {
        try {
            view3.setBackgroundColor(Color.parseColor(color));
            anclatext.setTextColor(Color.parseColor(color));
            cerrarCurso.setTextColor(Color.parseColor(color));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void setAnchorCheck() {

    }

    @Override
    public void hideContent() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void simpleSyncIntenService(int programaEducativoId) {
        SimpleSyncIntenService.start(getContext(), programaEducativoId);
        CMRE.saveNotifyChangeDataBase(getContext());
    }

    @Override
    public void hideAncla() {
        bntAnclaTotal.setVisibility(View.GONE);
        anclatext.setText("");
    }

    @Override
    public void showAncla() {
        bntAnclaTotal.setVisibility(View.VISIBLE);
        anclatext.setText("ENTREGA OFICIAL");
    }

    @Override
    public void showDialogPeriodoDesactivado(String string) {
        msgCalendario.setVisibility(View.VISIBLE);
        msgCalendario.setText(string);

    }

    @Override
    public void hideDialogPeriodoDesactivado() {
        msgCalendario.setVisibility(View.GONE);
    }

    @Override
    public void hideCerrarCurso() {
        cardView.setVisibility(View.GONE);
        cardCerrrar.setVisibility(View.GONE);
    }

    @Override
    public void showCerrarCurso() {
        cardView.setVisibility(View.VISIBLE);
        cardCerrrar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFiltroDialog() {
        try {
            DialogFragment fragment = new FiltroDialog();
            fragment.show(getChildFragmentManager(), fragment.getTag());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void reloadActivity() {
        TabsCursoDocenteActivity tabsCursoDocenteActivity = null;
        if(getActivity()!=null) tabsCursoDocenteActivity = (TabsCursoDocenteActivity)getActivity();
        if(tabsCursoDocenteActivity!=null){
            tabsCursoDocenteActivity.reloadActivity();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVistas();
    }

    private void initVistas() {
        evaluacionCompetenciaAdapter = new EvaluacionCompetenciaAdapter(new ArrayList<Object>(), getContext());
        evaluacionCompetenciaAdapter.setListener(this);
        recicladorEvaluacion.setLayoutManager(new LinearLayoutManager(getContext()));
        recicladorEvaluacion.setAdapter(evaluacionCompetenciaAdapter);
        presenter.onCreate();
    }

    public int getCalendarioPeriodoId() {
        int periodoId = 0;
        if (presenter != null) {
            periodoId = presenter.getCalendarioPeriodoId();
        }
        return periodoId;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onObjectClicked(Object object) {
        Log.d(TAG, "onObjectClicked");
        startEvalResultActivity(object);

    }

    @Override
    public void showMessage(CharSequence error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAceptarDialogEvaluar(final Object object) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Confirmación");
        builder.setMessage("Estas seguro que desea Evaluar ?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "Presionos ACEPTAR");
                presenter.onAceptarDialogEvaluado(object);
                dialogInterface.dismiss();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        //Create AdapterExample
        builder.create().show();
    }

    @Override
    public void clickToogle(CompetenciaUi competenciaUi) {
        presenter.clickCompetencia(competenciaUi);
    }


    public void onParentFabClicked(Bundle bundle) {
        Log.d(TAG, "onParentFabClicked");
        presenter.onResumeFragment(bundle);
    }


    public void onActualizarResultado() {
        //Log.d(TAG, "onActualizarResultado : " + rubroProcesoUi.getTitulo());
        presenter.onRefrescarItems();
    }

    @Override
    public void onResumeFragment() {
        presenter.onResumeFragment();
    }

    public void onActualizarResultadosPeriodos(String idCalenadarioPeriodo, boolean status) {
        presenter.actualizarResultadosPeriodo(idCalenadarioPeriodo, status);
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bntAnclaTotal:
                showDialogAnclarResultados(getResources().getString(R.string.title_anclar_resultados));
                break;
        }
    }

    public void showDialogAnclarResultados(CharSequence message) {

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Confirmación");
        builder.setMessage(message)
                .setPositiveButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        AlertDialog alertDialog = new SpotsDialog(getContext(), R.style.SpotsDialogResultado);
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.setCancelable(false);
                        presenter.executeAnclado(alertDialog);
                        dialogInterface.dismiss();

                    }
                })
                .setNegativeButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();

    }


    @OnClick(R.id.btn_cerrar)
    public void onClickedBtnCerrar() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Confirmación");
        builder.setMessage(getResources().getString(R.string.title_cerrar_curso))
                .setPositiveButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.onClickedBtnCerrar();

                    }
                })
                .setNegativeButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onChildsFragmentViewCreated() {

    }

    @Override
    public void onFragmentViewCreated(Fragment f, View view, Bundle savedInstanceState) {

    }

    @Override
    public void onFragmentResumed(Fragment f) {
        if(f instanceof FiltroView){
            presenter.onResumeDialogFiltro();
        }
    }

    @Override
    public void onFragmentViewDestroyed(Fragment f) {
        if(f instanceof FiltroView){
            presenter.onDestroyDialogFiltro();
        }
    }

    @Override
    public void onFragmentActivityCreated(Fragment f, Bundle savedInstanceState) {
        if(f instanceof FiltroView){
            FiltroDialog filtroDialog = (FiltroDialog)f;
            filtroDialog.setPresenter(presenter);
            presenter.attachView((FiltroView)filtroDialog);
        }
    }

    public void showFiltro() {
       if(presenter!=null)presenter.onClickFiltro();
    }
}
