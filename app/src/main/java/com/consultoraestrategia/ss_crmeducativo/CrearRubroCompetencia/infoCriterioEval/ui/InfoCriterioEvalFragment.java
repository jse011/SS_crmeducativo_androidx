package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.InfoCriterioEvalPresenter;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.InfoCriterioEvalPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.adapter.CriterioEvaluacionAdapter;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.data.source.InfoCriterioEvalRepository;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.data.source.local.InfoCriteriosEvalLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.domain.usecase.GetIndicador;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.listener.InfoCriterioEvalListener;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.listener.CriterioEvalListener;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InfoCriterioEvalFragment extends BaseDialogFragment<InfoCriterioEvalView, InfoCriterioEvalPresenter, CriterioEvalListener> implements InfoCriterioEvalView, InfoCriterioEvalListener {

    private InfoCriterioEvalView view;

    @BindView(R.id.text_sub_indicador)
    TextView textIndicador;
    @BindView(R.id.text_ti_indicador)
    TextView informacionIndicador;
    @BindView(R.id.text_descripcion)
    TextView textDescripcion;
    @BindView(R.id.img_valor_numerico)
    TextView imgValorNumerico;
    @BindView(R.id.img_selector_numerico)
    TextView imgSelectorNumerico;
    @BindView(R.id.grid_selector)
    RecyclerView gridSelector;
    @BindView(R.id.tipoNota)
    TextView tipoNota;
    @BindView(R.id.imageView5)
    ImageView imageView;
    @BindView(R.id.root)
    ConstraintLayout root;


    public static final String ARRAYLIST_VALORTIPONOTA = "InfoCriterioEvalFragment.ArrayListValorTipoNota";
    public static final String INDICADOR_ID = "InfoCriterioEvalFragment.IndicadorId";
    private static final String TAG = InfoCriterioEvalFragment.class.getSimpleName();

    private CriterioEvaluacionAdapter criterioEvaluacionAdapter;
    private List<ValorTipoNotaUi> valorTipoNotaUiList;

    public static InfoCriterioEvalFragment newInstance(List<ValorTipoNotaUi> valorTipoNotaUis, int indicadorId) {
        InfoCriterioEvalFragment fragment = new InfoCriterioEvalFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARRAYLIST_VALORTIPONOTA, (Serializable) valorTipoNotaUis);
        bundle.putInt(INDICADOR_ID, indicadorId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return InfoCriterioEvalFragment.class.getSimpleName();
    }

    @Override
    protected InfoCriterioEvalPresenter getPresenter() {
        InfoCriterioEvalRepository mRepository = new InfoCriterioEvalRepository(new InfoCriteriosEvalLocalDataSource());
        return new InfoCriterioEvalPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetIndicador(mRepository)
        );

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();

    }

    @Override
    protected InfoCriterioEvalView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_info_criterio_eval_2, container, false);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void showIndicador(IndicadorUi indicadorUi) {

        if (TextUtils.isEmpty(indicadorUi.getSubtitulo()))
            textIndicador.setText("Verificar Indicador");
        else
            textIndicador.setText(indicadorUi.getSubtitulo());


        if (TextUtils.isEmpty(indicadorUi.getTitulo()))
            informacionIndicador.setText("No existe Informacion del Indicador");
        else
            informacionIndicador.setText(indicadorUi.getTitulo().toUpperCase());


        if (!TextUtils.isEmpty(indicadorUi.getDescripcion()))
            textDescripcion.setText(indicadorUi.getDescripcion());
        else
            textDescripcion.setText(R.string.activity_evalaucion_bidimencional_indicador_detalle);

        switch (indicadorUi.getTipoIndicador()){
            case SER:
                Glide.with(getContext()).load(indicadorUi.getUrl())
                        .apply(Utils.getGlideRequestOptionsSimple()).into(imageView);
                break;
            case HACER:
                Glide.with(getContext()).load(indicadorUi.getUrl())
                        .apply(Utils.getGlideRequestOptionsSimple()).into(imageView);
                break;
            case SABER:
                Glide.with(getContext()).load(indicadorUi.getUrl())
                        .apply(Utils.getGlideRequestOptionsSimple()).into(imageView);
                break;
            case DEFAULT:
                imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.ic_speedometer));
                break;
        }

    }

    @Override
    public void showValorTipoNota(List<ValorTipoNotaUi> valorTipoNotaUis) {
        criterioEvaluacionAdapter.setcriterioEvalUis(valorTipoNotaUis);
        for (ValorTipoNotaUi valorTipoNotaUi : valorTipoNotaUis) {
            TipoNotaUi tipoNotaUi = valorTipoNotaUi.getTipoNotaUi();
            tipoNota.setText(tipoNotaUi.getNombre());
        }
    }


    private void setupAdapter() {

        criterioEvaluacionAdapter = new CriterioEvaluacionAdapter(new ArrayList<ValorTipoNotaUi>(), gridSelector, this);
        criterioEvaluacionAdapter.setRecyclerView(gridSelector);
        gridSelector.setAdapter(criterioEvaluacionAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        gridSelector.setLayoutManager(linearLayoutManager);
        gridSelector.setHasFixedSize(true);
    }

    @Override
    public void saveListCriteriosEval(List<ValorTipoNotaUi> valorTipoNotaUiList) {
        this.valorTipoNotaUiList = valorTipoNotaUiList;


    }

    @OnClick({R.id.bttn_save, R.id.bttn_negative})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bttn_save:
                editValorTipoNota();
                dismiss();
                break;
            case R.id.bttn_negative:
                dismiss();
                break;
        }
    }

    private void editValorTipoNota() {
        if (valorTipoNotaUiList != null)
            listener.editCriterioEval(valorTipoNotaUiList);
        else
            dismiss();

    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }
}
