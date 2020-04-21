package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.aprendizaje;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.CampoTematicoAdapter;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.adapter.CompetenciaAdapter;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadRepository;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.local.UnidadLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetCamposAccion;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetCompetenciaCapacidad;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.FragmentListener;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AprendizajeFragment extends BaseFragment<AprendizajeView, AprendizajePresenter, FragmentListener> implements AprendizajeView {


    @BindView(R.id.rv_Competencia)
    RecyclerView rv_Competencia;
    @BindView(R.id.rv_CamposAccion)
    RecyclerView rvCamposAccion;
    @BindView(R.id.aprendizajeFragmetn)
    FrameLayout constraintLayout;
    @BindView(R.id.vacioCompetencias)
    TextView vacioCompetencias;
    @BindView(R.id.vacioCampos)
    TextView vacioCampos;
    @BindView(R.id.cardView)
    CardView cardCompetencias;
    @BindView(R.id.cardView2)
    CardView cardCamposAccion;
    @BindView(R.id.progressbar2)
    ProgressBar progressBar2;

    private CompetenciaAdapter competenciaAdapter;
    private CampoTematicoAdapter campoTematicoAdapter;
    private ItemDecoration itemDecoration;

    public static AprendizajeFragment newInstance(Bundle bundle) {
        AprendizajeFragment fragment = new AprendizajeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return AprendizajeFragment.class.getSimpleName();
    }

    @Override
    protected AprendizajePresenter getPresenter() {
        UnidadRepository repository = new UnidadRepository(new UnidadLocalDataSource(InjectorUtils.provideRubroEvalRNPFormulaDao()));
        presenter = new AprendizajePresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetCompetenciaCapacidad(repository),
                new GetCamposAccion(repository));
        return presenter;
    }

    @Override
    protected AprendizajeView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_unidad_aprendizaje, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startupAdapter();
    }

    private void startupAdapter() {
        competenciaAdapter = new CompetenciaAdapter(new ArrayList<CompetenciaUi>());
        rv_Competencia.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_Competencia.setAdapter(competenciaAdapter);

        campoTematicoAdapter = new CampoTematicoAdapter(new ArrayList<CampoAccionUi>(), 0);
        rvCamposAccion.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCamposAccion.setAdapter(campoTematicoAdapter);

        itemDecoration = new ItemDecoration(100);
        rvCamposAccion.addItemDecoration(itemDecoration);
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
    public void showCompetencias(List<CompetenciaUi> competenciaUis) {
        competenciaAdapter.setCompetenciaUiList(competenciaUis);
    }

    @Override
    public void showCampoAccion(List<CampoAccionUi> campoAccionUis) {
        campoTematicoAdapter.setCampoAccionUis(campoAccionUis);
    }

    @Override
    public void hideVacioCampos() {
        cardCamposAccion.setVisibility(View.GONE);
    }

    @Override
    public void showVacioCampos() {
        cardCamposAccion.setVisibility(View.VISIBLE);
    }

    @Override
    public void showVacioCompetencias() {
        cardCompetencias.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideVacioCompetencias() {
        cardCompetencias.setVisibility(View.GONE);
    }

    @Override
    public void showProgressCamposTematico() {
        progressBar2.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressCamposTematicos() {
        progressBar2.setVisibility(View.GONE);
    }

    public void filterCompetencia(Object o) {
        presenter.filterCompetencia(o);
    }

}
