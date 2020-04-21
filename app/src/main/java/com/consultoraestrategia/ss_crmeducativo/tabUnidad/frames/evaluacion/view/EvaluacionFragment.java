package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.grupal.ui.EvaluacionBimencionalGrupalActividad;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.individual.ui.EvaluacionBimencionalActividad;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.contenedor.EvaluacionContainerActivity;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.ui.RegistroFragment;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.ui.RegistroGrupoFragment;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.silabo.RubroResultadoSilaboFragment;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadRepository;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.local.UnidadLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetIndicadoresUnidad;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetRubricasUnidad;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetRubrosCompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RubricaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.adapters.evaluacionesAdapter.EvaluacionAdapter;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.adapters.rubricasAdapter.RubricasAdapter;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.adapters.rubrosCompetenciasAdapter.CompetenciaRubrosAdapter;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.presenter.EvaluacionUnidadPresenter;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.presenter.EvaluacionUnidadPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EvaluacionFragment extends BaseFragment<EvaluacionView, EvaluacionUnidadPresenter, EvaluacionUnidadListener> implements EvaluacionView, View.OnClickListener, EvaluacionUnidadListener {

    String TAG = EvaluacionFragment.class.getSimpleName();
    @BindView(R.id.titulocard1)
    TextView titulocard1;
    @BindView(R.id.recyclerIndicadores)
    RecyclerView recyclerIndicadores;
    EvaluacionAdapter evaluacionAdapter;
    @BindView(R.id.btnrubrica)
    ImageView btnrubrica;
    @BindView(R.id.btnrubro)
    ImageView btnrubro;
    @BindView(R.id.recyclerRubricas)
    RecyclerView recyclerRubricas;
    @BindView(R.id.contRubricas)
    LinearLayout contRubricas;
    @BindView(R.id.recyclerRubros)
    RecyclerView recyclerRubros;
    @BindView(R.id.contRubros)
    LinearLayout contRubros;
    @BindView(R.id.fragmentEvaluacion)
    NestedScrollView fragmentEvaluacion;
    RubricasAdapter rubBidAdapter;
    CompetenciaRubrosAdapter competenciaRubrosAdapter;
    @BindView(R.id.txtvaciorubricas)
    TextView txtvaciorubricas;
    @BindView(R.id.txtvaciorubros)
    TextView txtvaciorubros;
    Unbinder unbinder;

    @BindView(R.id.click)
    ImageView click;
    @BindView(R.id.cabeceraE)
    LinearLayout cabeceraE;
    boolean toogle;

    public static EvaluacionFragment newInstance(Bundle bundle) {
        EvaluacionFragment fragment = new EvaluacionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected EvaluacionUnidadPresenter getPresenter() {
        UnidadRepository unidadRepository = new UnidadRepository(new UnidadLocalDataSource(InjectorUtils.provideRubroEvalRNPFormulaDao()));
        presenter = new EvaluacionUnidadPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources()
                , new GetIndicadoresUnidad(unidadRepository)
                , new GetRubricasUnidad(unidadRepository),
                new GetRubrosCompetenciaUnidad(unidadRepository));
        return presenter;
    }

    @Override
    protected EvaluacionView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_unidad_evaluacion, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnrubro.setOnClickListener(this);
        btnrubrica.setOnClickListener(this);
        click.setOnClickListener(this);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initAdapterRubros() {
        competenciaRubrosAdapter = new CompetenciaRubrosAdapter(new ArrayList<Object>(), this);
        recyclerRubros.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerRubros.setAdapter(competenciaRubrosAdapter);
        this.toogle=true;
        settogle();
    }

    private void initAdapterRubricas(int cantidad) {

        rubBidAdapter = new RubricasAdapter(new ArrayList<RubricaUi>(), this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), cantidad, GridLayoutManager.HORIZONTAL, false);
        recyclerRubricas.setLayoutManager(gridLayoutManager);
        recyclerRubricas.setAdapter(rubBidAdapter);
    }

    private void initAdapterEvaluaciones() {
        evaluacionAdapter = new EvaluacionAdapter(new ArrayList<IndicadorUi>());
        recyclerIndicadores.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerIndicadores.setAdapter(evaluacionAdapter);
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
    public void setListIndicadores(List<IndicadorUi> indicadorUis) {

        evaluacionAdapter.setList(indicadorUis);
    }

    @Override
    public void setListRubricas(List<RubricaUi> rubricaUiList) {
        int cantidad=1;
        if (rubricaUiList.size() > 3)cantidad=2;
        initAdapterRubricas(cantidad);
        rubBidAdapter.setListRubricas(rubricaUiList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnrubrica:
                titulocard1.setText("RÚBRICAS DE UNIDAD");
                btnrubrica.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_yellow));
                btnrubro.setColorFilter(ContextCompat.getColor(getContext(), R.color.grey_400));
                contRubricas.setVisibility(View.VISIBLE);
                contRubros.setVisibility(View.GONE);
                break;
            case R.id.btnrubro:
                titulocard1.setText("RUBROS DE UNIDAD");
                btnrubrica.setColorFilter(ContextCompat.getColor(getContext(), R.color.grey_400));
                btnrubro.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                contRubricas.setVisibility(View.GONE);
                contRubros.setVisibility(View.VISIBLE);
                break;
            case R.id.click:
                if(toogle)toogle=false;
                else toogle=true;
                settogle();
                break;
        }
    }
    public void settogle(){
        if(toogle){
            cabeceraE.setVisibility(View.VISIBLE);
            recyclerIndicadores.setVisibility(View.VISIBLE);
            rotateFabForward();

        }
        else{
            cabeceraE.setVisibility(View.GONE);
            recyclerIndicadores.setVisibility(View.GONE);
            rotateFabBackward();
        }

    }
    public void rotateFabForward() {
        Log.d(TAG, "true");
        ViewCompat.animate(click)
                .rotation(180f)
                .withLayer()
                .setDuration(300L)
                .setInterpolator(new OvershootInterpolator(10.0F))
                .start();
    }

    public void rotateFabBackward() {
        Log.d(TAG, "false");
        ViewCompat.animate(click)
                .rotation(0f)
                .withLayer()
                .setDuration(300L)
                .setInterpolator(new OvershootInterpolator(10.0F));
    }

    @Override
    public void showEvaluacionGrupalRubrica(CRMBundle bundle, int cargaCursoId, String rubrica) {
        EvaluacionBimencionalGrupalActividad.launchEvalRubBidActivity(getContext(), bundle.instanceBundle(), rubrica, cargaCursoId);
    }

    @Override
    public void showEvaluacionIndividualRubrica(CRMBundle bundle, int cargaCursoId, String rubrica) {
        EvaluacionBimencionalActividad.launchEvaluacionBimencionalActividadIndividual(getContext(), bundle.instanceBundle(), rubrica, cargaCursoId);
    }

    @Override
    public void initAdapter() {
        initAdapterEvaluaciones();
        initAdapterRubricas(1);
        initAdapterRubros();
    }

    @Override
    public void setListCompetenciasRubros(List<Object> objects) {
        competenciaRubrosAdapter.setList(objects);
    }

    @Override
    public void showEvaluacionRubroIndividual(String idRubrica, String titulo, int sesionAprendizajeId, int cargaCursoId, int cursoId, boolean disabledEval, String tipoNotaId, int entidadId, int georeferenciaId, int programaEducativoId) {
        Bundle bundle = getArguments();
        CRMBundle crmBundle = new CRMBundle();
        crmBundle.setProgramaEducativoId(programaEducativoId);
        bundle.putAll(crmBundle.instanceBundle());
        bundle.putInt(RubroResultadoSilaboFragment.TAG_SESIONAPRENDIZAJEID, sesionAprendizajeId);
        bundle.putInt(RubroResultadoSilaboFragment.TAG_CARGACURSO_ID, cargaCursoId);
        bundle.putInt(RubroResultadoSilaboFragment.TAG_CURSO_ID, cursoId);
        bundle.putInt(RubroResultadoSilaboFragment.TAG_ENTIDAD_ID, entidadId);
        bundle.putInt(RubroResultadoSilaboFragment.TAG_GEOREFERENCIA_ID, georeferenciaId);
        bundle.putInt(RubroResultadoSilaboFragment.TAG_PROGRAMA_EDUCATIVO_ID, programaEducativoId);
        Intent intent = EvaluacionContainerActivity.createContainerActivityIntent(getContext(), bundle, idRubrica, titulo, disabledEval, tipoNotaId, RegistroFragment.class);
        startActivityForResult(intent, EvaluacionContainerActivity.REQUESTCODE);
    }

    @Override
    public void showEvaluacionRubroGrupal(String idRubrica, String titulo, int sesionAprendizajeId, int cargaCursoId, int cursoId, int cargaAcademicaId, boolean disable, String tipoNotaId, int programaEducativoId) {
        Bundle bundle = new Bundle();
        bundle.putInt(RubroResultadoSilaboFragment.TAG_SESIONAPRENDIZAJEID, sesionAprendizajeId);
        bundle.putInt(RubroResultadoSilaboFragment.TAG_CARGACURSO_ID, cargaCursoId);
        bundle.putInt(RubroResultadoSilaboFragment.TAG_CURSO_ID, cursoId);
        CRMBundle crmBundle = new CRMBundle();
        crmBundle.setProgramaEducativoId(programaEducativoId);
        bundle.putAll(crmBundle.instanceBundle());

        Intent intent = EvaluacionContainerActivity.createContainerActivityIntent(getContext(), bundle, idRubrica, titulo, disable, tipoNotaId, RegistroGrupoFragment.class);
        startActivityForResult(intent, EvaluacionContainerActivity.REQUESTCODE);
    }

    @Override
    public void showTxtVacioRubrica() {
        txtvaciorubricas.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTxtVacioRubrica() {
        txtvaciorubricas.setVisibility(View.GONE);
    }

    @Override
    public void showTxtVacioRubro() {
        txtvaciorubros.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTxtVacioRubro() {
        txtvaciorubros.setVisibility(View.GONE);
    }


    @Override
    public void onClickRubrica(RubricaUi rubricaUi) {
        Log.d(TAG, "onClickRubrica");
        presenter.onClickRubrica(rubricaUi);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void showEvaluacionFragment() {
        fragmentEvaluacion.setVisibility(View.VISIBLE);
    }

    public void hideEvaluacionFragment() {
        fragmentEvaluacion.setVisibility(View.GONE);
    }
}
