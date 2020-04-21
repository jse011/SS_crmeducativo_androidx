package com.consultoraestrategia.ss_crmeducativo.infoRubro.ui;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.InfoRubroPresenter;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.InfoRubroPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.adapter.CamposAccionAdapter;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.adapter.ValorTipoNotaAdapter;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.data.source.InfoRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.data.source.local.InfoRubroLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.CampoTematicoUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.DesempenioUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.EscalaUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.RubroEvalProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.listener.InfoRubroCallback;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.usecase.GetRubroDetalle;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by SCIEV on 28/08/2018.
 */

public class InfoRubroFragment extends BaseDialogFragment<InfoRubroView, InfoRubroPresenter, InfoRubroCallback> implements InfoRubroView, View.OnClickListener {


    @BindView(R.id.text_descripcion)
    TextView textDescripcion;
    @BindView(R.id.rc_campos_accion)
    RecyclerView rcCamposAccion;
    @BindView(R.id.txt_competencia)
    TextView txtCompetencia;
    @BindView(R.id.txt_capacidad)
    TextView txtCapacidad;
    @BindView(R.id.txt_vermas_desem)
    TextView txtVermasDesem;
    @BindView(R.id.txt_desempenio)
    TextView txtDesempenio;
    @BindView(R.id.conten_desempenio)
    ConstraintLayout contenDesempenio;
    @BindView(R.id.text_sub_indicador)
    TextView textSubIndicador;
    @BindView(R.id.text_ti_indicador)
    TextView textTiIndicador;
    @BindView(R.id.txt_titulo_tiponota)
    TextView txtTituloTipoNota;
    @BindView(R.id.txt_escala)
    TextView txtEscala;
    @BindView(R.id.txt_rango_escala)
    TextView txtRangoEscala;
    @BindView(R.id.img_valor_numerico)
    TextView imgValorNumerico;
    @BindView(R.id.img_selector_numerico)
    TextView imgSelectorNumerico;
    @BindView(R.id.tipoNota)
    TextView txtTipoNota;
    @BindView(R.id.grid_selector)
    RecyclerView gridSelector;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    @BindView(R.id.txt_sub_title)
    TextView txtSubTitle;
    @BindView(R.id.img_indicador)
    ImageView imgIndicador;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    Unbinder unbinder;
    private CamposAccionAdapter adapter;
    public final static String EXTRA_RUBROEVALPROCESOID = "InfoRubroFragment.rubroEvalProcesoId";

    public static InfoRubroFragment newInstance(String rubroEvalProcesoId) {
        Bundle args = new Bundle();
        args.putString(EXTRA_RUBROEVALPROCESOID, rubroEvalProcesoId);
        InfoRubroFragment fragment = new InfoRubroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return InfoRubroFragment.class.getSimpleName();
    }

    @Override
    protected InfoRubroPresenter getPresenter() {
        return new InfoRubroPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetRubroDetalle(InfoRubroRepository.getInstance(
                        new InfoRubroLocalDataSource(
                                InjectorUtils.provideRubroEvaluacionDao(),
                                InjectorUtils.provideIndicadorDao(),
                                InjectorUtils.provideDesempenioDao(),
                                InjectorUtils.provideCampoTematicoDao(),
                                InjectorUtils.provideCompetenciaDao(),
                                InjectorUtils.provideTipoNotaDao()
                        )
                )));
    }


    @Override
    protected InfoRubroView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        return inflater.inflate(R.layout.layout_info_rubro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupAdapter();
        gridSelector.setNestedScrollingEnabled(false);
        rcCamposAccion.setNestedScrollingEnabled(false);

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setFocus(view);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void setFocus(View view) {
        if (view instanceof RecyclerView) {
            ((RecyclerView) view).setFocusable(false);
        } else if (view instanceof ViewGroup) {
            int size = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < size; i++) {
                setFocus(((ViewGroup) view).getChildAt(i));
            }
        }

    }

    @Override
    public void onResume() {

        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.90), (int) (size.y * 0.85));
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing

        super.onResume();
    }

    private void setupAdapter() {
        adapter = new CamposAccionAdapter(new ArrayList<CampoTematicoUi>());
        rcCamposAccion.setAdapter(adapter);
        rcCamposAccion.setLayoutManager(new LinearLayoutManager(getContext()));
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
    public void showRubro(RubroEvalProcesoUi rubroEvalProcesoUi) {
        txtTitle.setText(rubroEvalProcesoUi.getTitulo());
        txtSubTitle.setText(rubroEvalProcesoUi.getSubtitulo());
    }

    @Override
    public void showIndicador(IndicadorUi indicadorUi, CompetenciaUi competenciaUi) {

        textTiIndicador.setText(indicadorUi.getTitulo());
        textSubIndicador.setText(indicadorUi.getSubtitulo());

        if (TextUtils.isEmpty(indicadorUi.getAlias())) {
            textSubIndicador.setText(indicadorUi.getTitulo());
            textTiIndicador.setVisibility(View.GONE);
            //txtGuionTitulo.setVisibility(View.GONE);
        } else {
            textSubIndicador.setText(indicadorUi.getAlias());
            textTiIndicador.setVisibility(View.VISIBLE);
            String titulo = "Título: " + indicadorUi.getTitulo();
            textTiIndicador.setText(titulo);
            //txtGuionTitulo.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(indicadorUi.getDescripcion())) {
            String descripcion = "Descripción: " + indicadorUi.getDescripcion();
            textDescripcion.setText(descripcion);
            textDescripcion.setVisibility(View.VISIBLE);
            //txtGuionDescripcion.setVisibility(View.VISIBLE);
        } else {
            textDescripcion.setVisibility(View.GONE);
            //txtGuionDescripcion.setVisibility(View.GONE);
        }

        Drawable drawable = null;
        switch (indicadorUi.getTipoIndicadorUi()) {
            case SER:
                Glide.with(getContext()).load(indicadorUi.getUrl())
                        .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_speedometer)).into(imgIndicador);
                break;
            case HACER:
                Glide.with(getContext()).load(indicadorUi.getUrl())
                        .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_speedometer)).into(imgIndicador);
                break;
            case SABER:
                Glide.with(getContext()).load(indicadorUi.getUrl())
                        .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_speedometer)).into(imgIndicador);
                break;
            case DEFAULT:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_speedometer);
                break;
            default:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_speedometer);
                break;
        }
        if (drawable != null) imgIndicador.setImageDrawable(drawable);

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imgIndicador.setColorFilter(filter);


    }

    @Override
    public void showDesempenio(DesempenioUi desempenioUi) {
        txtDesempenio.setText(desempenioUi.getDescripcion());
        txtDesempenio.post(new Runnable() {
            @Override
            public void run() {
                presenter.postCantidadLineasDesempenio(txtDesempenio.getLineCount());
            }
        });
    }

    @Override
    public void showCompetencia(CompetenciaUi competenciaOwner) {
        txtCompetencia.setText(competenciaOwner.getTitulo());
    }

    @Override
    public void showCapacidad(CapacidadUi capacidadOwner) {
        txtCapacidad.setText(capacidadOwner.getTitulo());
    }

    @Override
    public void showCampoAccionList(List<CampoTematicoUi> campoAccionList) {
        Log.d(getTag(), "showCampoAccionList size: " + campoAccionList.size());
        adapter.setItems(campoAccionList);
        scroll.fullScroll(ScrollView.FOCUS_UP);
    }

    @Override
    public void showTipoNota(TipoNotaUi tipoNotaUi) {
        txtTituloTipoNota.setText(tipoNotaUi.getNombre());
        EscalaUi escalaUi = tipoNotaUi.getEscalaUi();
        txtEscala.setText(String.valueOf(escalaUi.getDescripcion()));
        String rangoEscala = "<b>(</b>" + escalaUi.getValorMinimo() + "  <b> - </b>" + escalaUi.getValorMaximo() + "<b>)</b>";
        txtRangoEscala.setText(Html.fromHtml(rangoEscala));

        TipoUi tipo = tipoNotaUi.getTipoUi();

        imgSelectorNumerico.setVisibility(View.GONE);
        imgValorNumerico.setVisibility(View.GONE);
        gridSelector.setVisibility(View.GONE);
        switch (tipo.getTipo()) {
            case SELECTOR_NUMERICO:
                imgSelectorNumerico.setVisibility(View.VISIBLE);
                break;
            case VALOR_NUMERICO:
                imgValorNumerico.setVisibility(View.VISIBLE);
                break;
            case SELECTOR_ICONOS:
                selectorValoresIconos(tipoNotaUi, tipo.getTipo());
                break;
            case SELECTOR_VALORES:
                selectorValoresIconos(tipoNotaUi, tipo.getTipo());
                break;
        }


    }

    private void selectorValoresIconos(TipoNotaUi tipoNotaUi, TipoUi.Tipo tipo) {
        List<ValorTipoNotaUi> valorTipoNotaUiList = tipoNotaUi.getValorTipoNotaUiList();
        if (valorTipoNotaUiList == null) return;
        ValorTipoNotaAdapter valorTipoNotaAdapter = new ValorTipoNotaAdapter(tipoNotaUi.getValorTipoNotaUiList(), tipo);
        gridSelector.setAdapter(valorTipoNotaAdapter);
        gridSelector.setLayoutManager(new LinearLayoutManager(getContext()));
        gridSelector.setHasFixedSize(true);
        gridSelector.setVisibility(View.VISIBLE);
        gridSelector.setEnabled(true);
        gridSelector.setOnClickListener(this);
        //gridSelector.OnItemTouchListener();
    }

    @Override
    public void formatMinimizarTextDesmepenio(int maxLinTex) {
        txtDesempenio.setMaxLines(maxLinTex);
        txtDesempenio.setEllipsize(TextUtils.TruncateAt.END);
        txtVermasDesem.setVisibility(View.VISIBLE);
    }

    @Override
    public void formatMaximizarTextDesmepenio() {
        txtVermasDesem.setVisibility(View.VISIBLE);
        txtDesempenio.setMaxLines(Integer.MAX_VALUE);
        txtDesempenio.setEllipsize(null);
    }

    @Override
    public void changeTextoVerMasDesempenio(String texto) {
        txtVermasDesem.setText(texto);
    }

    @Override
    public void enabledVerMas(int maxLinTex) {
        txtVermasDesem.setOnClickListener(this);
        contenDesempenio.setOnClickListener(this);
        formatMinimizarTextDesmepenio(maxLinTex);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_vermas_desem:
                onClickVerMas();
                break;
            case R.id.conten_desempenio:
                onClickVerMas();
                break;
        }
    }

    private void onClickVerMas() {
        presenter.onClickVerMasDesempenio();
    }


    @OnClick({R.id.bttn_back})
    public void onViewClicked() {
        dismiss();
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
