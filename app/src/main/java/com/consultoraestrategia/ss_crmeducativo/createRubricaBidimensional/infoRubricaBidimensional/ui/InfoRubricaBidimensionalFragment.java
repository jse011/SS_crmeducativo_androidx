package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.CreateRubBidListener;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.InfoRubricaBidimensionalPresent;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.InfoRubricaBidimensionalPresentImpl;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.adapter.CellAdapter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.adapter.ValorTipoNotaAdapter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.listener.InfoRubricaBidimensionalListener;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InfoRubricaBidimensionalFragment extends BaseDialogFragment<InfoRubricaBidimensionalView, InfoRubricaBidimensionalPresent, CreateRubBidListener> implements InfoRubricaBidimensionalView, InfoRubricaBidimensionalListener {

    public static final String INDICADOR_UI = "InfoRubricaBidimensionalFragment.IndicadorUi";
    public static final String TIPO_NOTA_UI = "InfoRubricaBidimensionalFragment.TipoNotaUi";


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
    @BindView(R.id.recyclerList)
    RecyclerView recyclerCell;
    @BindView(R.id.tipoNota)
    TextView tipoNota;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView((R.id.img_indicador))
    ImageView imgIndicador;
    @BindView(R.id.grid_selector)
    RecyclerView gridSelector;

    private ValorTipoNotaAdapter valorTipoNotaAdapter;


    private CellAdapter cellAdapter;

    public static final String CRITERIO_UI = "InfoRubricaBidimensionalFragment.CriterioUi";

    public static InfoRubricaBidimensionalFragment newInstance(CriterioEvaluacionUi criterioEvaluacionUi, int x, int y, IndicadorUi indicadorUi, TipoNotaUi tipoNotaUi) {
        InfoRubricaBidimensionalFragment fragment = new InfoRubricaBidimensionalFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(INDICADOR_UI, indicadorUi);
        bundle.putSerializable(TIPO_NOTA_UI, tipoNotaUi);
        bundle.putSerializable(CRITERIO_UI, criterioEvaluacionUi);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();

    }


    @Override
    protected InfoRubricaBidimensionalPresent getPresenter() {
        return new InfoRubricaBidimensionalPresentImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources()) {
        };

    }

    @Override
    protected String getLogTag() {
        return InfoRubricaBidimensionalFragment.class.getSimpleName();
    }


    @Override
    protected InfoRubricaBidimensionalView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_info_criterio_eval, container, false);
    }


    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    @Override
    public void showIndicador(IndicadorUi indicadorUi) {

        informacionIndicador.setText(indicadorUi.getDesempenioDesc());
        textIndicador.setText(indicadorUi.getTitulo());
        textDescripcion.setText(indicadorUi.getDescripcion());
        switch (indicadorUi.getTipoIndicadorUi()) {
            case SER:
                Glide.with(getContext()).load(indicadorUi.getUrl())
                        .apply(Utils.getGlideRequestOptionsSimple()).into(imgIndicador);
                break;
            case HACER:
                Glide.with(getContext()).load(indicadorUi.getUrl())
                        .apply(Utils.getGlideRequestOptionsSimple()).into(imgIndicador);
                break;
            case SABER:
                Glide.with(getContext()).load(indicadorUi.getUrl())
                        .apply(Utils.getGlideRequestOptionsSimple()).into(imgIndicador);
                break;
            case DEFAULT:
                Glide.with(getContext()).load(indicadorUi.getUrl())
                        .apply(Utils.getGlideRequestOptionsSimple()).into(imgIndicador);
                break;
        }
        if (!TextUtils.isEmpty(indicadorUi.getDescripcion())) {
            textDescripcion.setText(indicadorUi.getDescripcion());
        } else {
            textDescripcion.setText(R.string.activity_evalaucion_bidimencional_indicador_detalle);
        }
    }

    @Override
    public void showValorTipoNota(List<ValorTipoNotaUi> valorTipoNotaUiList) {
        valorTipoNotaAdapter.setcriterioEvalUis(valorTipoNotaUiList);
        for (ValorTipoNotaUi valorTipoNotaUi : valorTipoNotaUiList) {
            TipoNotaUi tipoNotaUi = valorTipoNotaUi.getTipoNotaUi();
            tipoNota.setText(tipoNotaUi.getTitle());
        }
    }

    @Override
    public void showCel(List<CriterioEvaluacionUi> cellList) {
        Log.d(getClass().getSimpleName(), "Size : " + cellList.size());
        cellAdapter.setCellList(cellList);
    }

    @Override
    public void saveSuccess(IndicadorUi indicadorUi) {
        listener.sendList(indicadorUi);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    private void setupAdapter() {

        valorTipoNotaAdapter = new ValorTipoNotaAdapter(new ArrayList<ValorTipoNotaUi>(), gridSelector);
        valorTipoNotaAdapter.setRecyclerView(gridSelector);
        gridSelector.setAdapter(valorTipoNotaAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        gridSelector.setLayoutManager(linearLayoutManager);
        gridSelector.setHasFixedSize(true);

        cellAdapter = new CellAdapter(new ArrayList<CriterioEvaluacionUi>(), recyclerCell, this, new ArrayList<Cell>());
        cellAdapter.setRecyclerView(recyclerCell);
        recyclerCell.setAdapter(cellAdapter);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        recyclerCell.setLayoutManager(linearLayoutManager1);
        recyclerCell.setHasFixedSize(true);
    }

    @OnClick({R.id.bttn_save, R.id.bttn_negative})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bttn_save:
               presenter.onClickSave();
                dismiss();
                break;
            case R.id.bttn_negative:
                dismiss();
                break;
        }
    }

    @Override
    public void sendList(List<CriterioEvaluacionUi> criterioEvalUis) {

    }

    @Override
    public void onClickCriterioEvaluacion(CriterioEvaluacionUi criterioEvaluacionUi) {

    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

}
