package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.consultoraestrategia.ss_crmeducativo.login2.service2.worker.SynckService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.FragmentManager;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.CrearRubroPresenter;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.CrearRubroPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.CamposAccionAdapter;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.CriterioEvalAdapter;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.TipoEvaluacionSpinnerAdapter;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.TipoNotaSpinnerAdapter;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.ValorTipoNotaAdapter;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.CrearRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.colorCondicional.LocalColorCondicional;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.colorCondicional.RemoteColorCondicional;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.criterioRubroEvaluacion.LocalCriterioRubroEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.criterioRubroEvaluacion.RemoteCriterioRubroEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.rubroEvaluacionProcesoCampotematico.LocalRubroEvaluacionProcesoCampotematico;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.exportar.rubroEvaluacionProcesoCampotematico.RemoteRubroEvaluacionProcesoCampotematico;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.local.CrearRubroLocalSource;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.remote.CrearRubroRemoteSource;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.dialog.ColorCondicionalDialog;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.dialog.DialogValorTipoNota;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.dialog.EditCriterioEvaluacionDialog;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.dialogKeyBoard.DialogKeyBoardCriteriosEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.dialogKeyBoard.DialogkeyBoardView;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetCapacidad;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetCriterioEval;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetEstrategiasEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetFormaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetInidicador;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetNotaPresicion;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetTarea;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetTipoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetTipoNota;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetTipoNotaDefault;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.SaveRubro;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ColorCondicionalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CriterioEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.FormaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoIndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.ui.InfoCriterioEvalFragment;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.listener.ColorCondicionalListener;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.listener.CrearRubroListener;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.listener.CriterioEvalListener;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.listener.RecyclerTouchListener;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.selecionarTipoNota.view.TipoNotaListActivity;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.listener.CrearRubroListaIndicadoresListener;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.ui.ListaIndicadoresFragment;
import com.consultoraestrategia.ss_crmeducativo.lib.ColorPicker.ColorPickerDialog;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.util.KeyboardUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CrearRubrosFragment extends BaseFragment<CrearRubroView, CrearRubroPresenter, CrearRubroListener> implements CrearRubroView, CriterioEvalListener, EditCriterioEvaluacionDialog.EditCriterioEvaluacionDialogListener, ColorCondicionalListener, ColorCondicionalDialog.RangoCalificacaion, ColorPickerDialog.OnColorSelectedListener, CrearRubroListaIndicadoresListener, RecyclerTouchListener.ClickListener, View.OnClickListener, DialogKeyBoardCriteriosEvaluacion.CallBack {


    @BindView(R.id.btnEstrategia)
    ImageButton btnEstrategia;
    @BindView(R.id.btn_tipo_evaluacion)
    ImageButton btnTipoEvaluacion;
    @BindView(R.id.txt_titulo)
    EditText txtTitulo;
    @BindView(R.id.txt_subtitulo)
    EditText txtSubtitulo;
    @BindView(R.id.input_tipo_evaluacionn)
    TextInputEditText inputTipoEvaluacionn;
    @BindView(R.id.label_tipo_evaluacionn)
    TextInputLayout labelTipoEvaluacionn;
    @BindView(R.id.input_forma_evaluacion)
    TextInputEditText inputFormaEvaluacion;
    @BindView(R.id.sp_forma_evaluacion)
    TextInputLayout spFormaEvaluacion;
    @BindView(R.id.btn_forma_evaluacion)
    ImageButton btnFormaEvaluacion;
    @BindView(R.id.btnTipoNota)
    ImageView btnTipoNota;
    @BindView(R.id.txt_tipo_nota)
    TextView txtTipoNota;
    @BindView(R.id.txt_titulo_tipo_nota)
    TextView txtTituloTipoNota;
    @BindView(R.id.txt_escala)
    TextView txtEscala;
    @BindView(R.id.img_valor_numerico)
    TextView imgValorNumerico;
    @BindView(R.id.img_selector_numerico)
    TextView imgSelectorNumerico;
    @BindView(R.id.tilTipoNota)
    RecyclerView tilTipoNota;
    @BindView(R.id.txt_descripcion_escala)
    TextView txtDescripcionEscala;
    @BindView(R.id.cons_tipo_nota)
    ConstraintLayout consTipoNota;
    @BindView(R.id.lbl_indicador)
    TextView lblIndicador;
    @BindView(R.id.txt_titulo_criterio_eval)
    TextView txtTituloCriterioEval;
    @BindView(R.id.rec_criterio_eval)
    RecyclerView recCriterioEval;
    @BindView(R.id.text_title_campoaccionlist)
    TextView textTitleCampoaccionlist;
    @BindView(R.id.rc_camp_accion)
    RecyclerView rcCampAccion;
    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.btnIndicador)
    ImageView btnIndicador;
    @BindView(R.id.card_criterio_eval)
    CardView cardCriterioEval;
    @BindView(R.id.imageView26)
    ImageView imageView26;
    @BindView(R.id.txt_competencia)
    TextView txtCompetencia;
    @BindView(R.id.txt_capacidad)
    TextView txtCapacidad;
    @BindView(R.id.txt_desempenio)
    TextView txtDesempenio;
    @BindView(R.id.text_sub_indicador)
    TextView textSubIndicador;
    @BindView(R.id.text_ti_indicador)
    TextView textTiIndicador;
    @BindView(R.id.text_descripcion)
    TextView textDescripcion;
    @BindView(R.id.txt_vermas_desem)
    TextView txtVermasDesem;
    @BindView(R.id.conten_desempenio)
    ConstraintLayout contenDesempenio;
    @BindView(R.id.img_indicador)
    ImageView imgIndicador;
    @BindView(R.id.content1)
    ConstraintLayout contentVistaPrevia;
    @BindView(R.id.content2)
    ConstraintLayout contentVistaPrevia2;

    private String TAG = "CrearRubrosFragment";
    public static final String INT_SESION_APRENDIZAJE_ID = "CrearRubrosFragment.SesionAprenizajeId";
    public static final String INT_INDICADOR_ID = "CrearRubrosFragment.indicadorId";
    public static final String ARRAYLIST_CAMPOTEMATICO_ID = "CrearRubrosFragment.campotematicoIds";
    public static final String INT_SILAVO_EVENTO_ID = "CrearRubrosFragment.silavoEventoId";
    public static final String INT_COMPETENCIA_ID = "CrearRubrosFragment.competenciaId";
    public static final String INT_CALENDARIO_PERIODO_ID = "CrearRubrosFragment.calendarioPeriodoId";

    public static final int SELECT_LISTATIPONOTAS_REQUEST = 131;

    private CrearRubroPresenter presenter;
    private TipoNotaSpinnerAdapter tipoNotaSpinnerAdapter;
    private TipoEvaluacionSpinnerAdapter tipoEvaluacionSpinnerAdapter;
    private CriterioEvalAdapter criterioEvalAdapter;
    private EditCriterioEvaluacionDialog criterioEvalDialog;
    private InfoCriterioEvalFragment infoCriterioEvalFragment;
    private ColorCondicionalDialog rangoCalificacionDialog;

    private ColorPickerDialog colorPicker;
    private CrearRubroListener listener;
    private CamposAccionAdapter camposAccionAdapter;
    private ListaIndicadoresFragment listaIndicadoresFragment;
    private boolean state = false;

    private ValorTipoNotaAdapter valorTipoNotaAdapter;
    private RecyclerTouchListener recyclerTouchListener;


    public static CrearRubrosFragment newInstance(int sesionAprendizajeId, int silavoEventoId, int calendarioPeriodoId, int competenciaId) {
        CrearRubrosFragment fragment = new CrearRubrosFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(INT_SESION_APRENDIZAJE_ID, sesionAprendizajeId);//enviar cero si no es un rubro de un sesion
        bundle.putInt(INT_SILAVO_EVENTO_ID, silavoEventoId);
        bundle.putInt(INT_COMPETENCIA_ID, competenciaId);
        bundle.putInt(INT_CALENDARIO_PERIODO_ID, calendarioPeriodoId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static CrearRubrosFragment newInstance(Bundle bundle) {
        CrearRubrosFragment fragment = new CrearRubrosFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected String getLogTag() {
        return CrearRubrosFragment.class.getSimpleName();
    }

    @Override
    protected CrearRubroPresenter getPresenter() {
        CrearRubroRepository crearRubroRepository = CrearRubroRepository.getInstance(
                new CrearRubroLocalSource(),
                new CrearRubroRemoteSource(),
                new LocalCriterioRubroEvaluacion(),
                new RemoteCriterioRubroEvaluacion(),
                new LocalColorCondicional(),
                new RemoteColorCondicional(),
                new LocalRubroEvaluacionProcesoCampotematico(),
                new RemoteRubroEvaluacionProcesoCampotematico()
        );


        presenter = new CrearRubroPresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new SaveRubro(crearRubroRepository),
                new GetCriterioEval(crearRubroRepository),
                new GetInidicador(crearRubroRepository),
                new GetTipoEvaluacion(crearRubroRepository),
                new GetFormaEvaluacion(crearRubroRepository),
                new GetTipoNota(crearRubroRepository),
                new GetTarea(crearRubroRepository),
                new GetEstrategiasEvaluacion(crearRubroRepository),
                new GetNotaPresicion(),
                new GetTipoNotaDefault(crearRubroRepository),
                new GetCapacidad(crearRubroRepository)
        );
        return presenter;
    }

    @Override
    protected CrearRubroView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_catalogue_list, container, false);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    //region ciclo de vida


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupAdapter();
        setupAdapterTipoNota();
        setupAdapterCampoAccion();

        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onActivityCreated();
        //setupListener();
        txtSubtitulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence texto, int start, int before, int count) {
                presenter.onTextChangedSubtitulo(texto.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Log.d(TAG, "onActivityCreated");
    }

    private void setupAdapterTipoNota() {
        valorTipoNotaAdapter = new ValorTipoNotaAdapter(new ArrayList<ValorTipoNotaUi>(), ValorTipoNotaAdapter.TIPO_SIMPLE);
        tilTipoNota.setAdapter(valorTipoNotaAdapter);
        tilTipoNota.setLayoutManager(new GridLayoutManager(tilTipoNota.getContext(), 6));
        tilTipoNota.setVisibility(View.VISIBLE);
        tilTipoNota.setEnabled(true);
        tilTipoNota.setNestedScrollingEnabled(false);
        tilTipoNota.setHasFixedSize(false);

        //tilTipoNota.setOnClickListener(this);
        if (recyclerTouchListener != null)
            tilTipoNota.addOnItemTouchListener(recyclerTouchListener);
        recyclerTouchListener = new RecyclerTouchListener(root.getContext(), tilTipoNota, this);
        tilTipoNota.addOnItemTouchListener(recyclerTouchListener);

    }

    private void setupAdapterCampoAccion() {
        camposAccionAdapter = new CamposAccionAdapter(new ArrayList<CamposAccionUi>());
        rcCampAccion.setAdapter(camposAccionAdapter);
        rcCampAccion.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //endregion ciclo de vida

    private void setupAdapter() {

        criterioEvalAdapter = new CriterioEvalAdapter(new ArrayList<ValorTipoNotaUi>(), recCriterioEval, this);
        criterioEvalAdapter.setRecyclerView(recCriterioEval);
        recCriterioEval.setAdapter(criterioEvalAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recCriterioEval.setLayoutManager(linearLayoutManager);
        recCriterioEval.setHasFixedSize(true);

    }

    @Override
    public void setPresenter(CrearRubroPresenter presenter) {
        presenter.attachView(this);
        presenter.onStart();
    }

    //region view
    @Override
    public void hideDialog() {
        listener.hideCrearRubro();
    }

    @Override
    public void showSpinnerNotaTipo(List<TipoNotaUi> tipoNotas) {
        tipoNotaSpinnerAdapter.setPersonas(tipoNotas);
    }

    @Override
    public void addCriterio(ValorTipoNotaUi valorTipoNotaUi) {
        criterioEvalAdapter.addCriterio(valorTipoNotaUi);
    }

    @Override
    public int updateCriterio(ValorTipoNotaUi valorTipoNotaUi) {
        return criterioEvalAdapter.updateCriterio(valorTipoNotaUi);
    }

    @Override
    public void deleteCriterio(ValorTipoNotaUi valorTipoNotaUi) {
        criterioEvalAdapter.deleteCriterio(valorTipoNotaUi);
    }

    @Override
    public void setCriterios(List<ValorTipoNotaUi> valorTipoNotaUi) {
        criterioEvalAdapter.setcriterioEvalUis(valorTipoNotaUi);
    }

    @Override
    public void showCriterioEval() {
        txtTituloCriterioEval.setVisibility(View.VISIBLE);
        recCriterioEval.setVisibility(View.VISIBLE);
        cardCriterioEval.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCriterioEval() {
        txtTituloCriterioEval.setVisibility(View.GONE);
        recCriterioEval.setVisibility(View.GONE);
        cardCriterioEval.setVisibility(View.GONE);
    }

    @Override
    public void showDialogCriterioEval(List<ValorTipoNotaUi> valorTipoNotaUi, int indicadorId) {
        FragmentManager manager = getFragmentManager();
        infoCriterioEvalFragment = InfoCriterioEvalFragment.newInstance(valorTipoNotaUi, indicadorId);
        infoCriterioEvalFragment.setTargetFragment(CrearRubrosFragment.this, 200);
        infoCriterioEvalFragment.show(manager, "InfoCriterioEvalFragment");
    }

    @Override
    public void hideDialogCriterioEval() {
        criterioEvalDialog.dismiss();
    }

    @Override
    public void setTituloIndcador(IndicadorUi indicadorUi, TipoIndicadorUi tipoIndicadorUi) {

        /*switch (tipoIndicadorUi) {
            case SER:
                Glide.with(getContext()).load(indicadorUi.getUrl())
                        .apply(Utils.getGlideRequestOptionsSimple()).into(imageView10);
                break;
            case HACER:
                Glide.with(getContext()).load(indicadorUi.getUrl())
                        .apply(Utils.getGlideRequestOptionsSimple()).into(imageView10);
                break;
            case SABER:
                Glide.with(getContext()).load(indicadorUi.getUrl())
                        .apply(Utils.getGlideRequestOptionsSimple()).into(imageView10);
                break;
            case DEFAULT:
                imageView10.setImageDrawable(ContextCompat.getDrawable(imageView10.getContext(), R.drawable.ic_speedometer));
                break;
        }*/

        String indicadorSelect = "";
        if(TextUtils.isEmpty(indicadorUi.getAlias())){
            textSubIndicador.setText(indicadorUi.getTitulo());
            textTiIndicador.setVisibility(View.GONE);
            indicadorSelect = indicadorUi.getTitulo();
        }else {
            textSubIndicador.setText(indicadorUi.getAlias());
            indicadorSelect = indicadorUi.getAlias();
            textTiIndicador.setVisibility(View.VISIBLE);
            String titulo = "Título: "+ indicadorUi.getTitulo();
            textTiIndicador.setText(titulo);
        }

        lblIndicador.setHint(TextUtils.isEmpty(indicadorSelect)?"Definir indicador por competencia":indicadorSelect);

        if (!TextUtils.isEmpty(indicadorUi.getDescripcion())) {
            String descripcion = "Descripción: "+ indicadorUi.getDescripcion();
            textDescripcion.setText(descripcion);
            textDescripcion.setVisibility(View.VISIBLE);
        } else {
            textDescripcion.setVisibility(View.GONE);
        }

        if (indicadorUi.getTipoIndicadorUi() == TipoIndicadorUi.DEFAULT) {
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_speedometer);

            imgIndicador.setImageDrawable(drawable.mutate());
            Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.ic_speedometer);
            btnIndicador.setImageDrawable(drawable2.mutate());
        } else {
            Glide.with(getContext()).load(indicadorUi.getUrl())
                    .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_speedometer)).into(imgIndicador);
            Glide.with(getContext()).load(indicadorUi.getUrl())
                    .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_speedometer)).into(btnIndicador);
        }


        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imgIndicador.setColorFilter(filter);

        txtDesempenio.setText(indicadorUi.getDesempenioDesc());
        txtDesempenio.post(new Runnable() {
            @Override
            public void run() {
                presenter.postCantidadLineasDesempenio(txtDesempenio.getLineCount());
            }
        });
    }

    @Override
    public void enabledVerMas(int maxLinTex) {
        txtVermasDesem.setOnClickListener(this);
        contenDesempenio.setOnClickListener(this);
        formatMinimizarTextDesmepenio(maxLinTex);
    }

    @Override
    public void formatMinimizarTextDesmepenio(int maxLinTex) {
        txtDesempenio.setMaxLines(maxLinTex);
        txtDesempenio.setEllipsize(TextUtils.TruncateAt.END);
        txtVermasDesem.setVisibility(View.VISIBLE);
    }

    @Override
    public void changeTextoVerMasDesempenio(String texto) {
        txtVermasDesem.setText(texto);
    }

    @Override
    public void formatMaximizarTextDesmepenio() {
        txtVermasDesem.setVisibility(View.VISIBLE);
        txtDesempenio.setMaxLines(Integer.MAX_VALUE);
        txtDesempenio.setEllipsize(null);
    }

    @Override
    public void showVistaPrevia() {
        contentVistaPrevia.setVisibility(View.VISIBLE);
        contentVistaPrevia2.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideVistaPrevia() {
        contentVistaPrevia.setVisibility(View.GONE);
        contentVistaPrevia2.setVisibility(View.GONE);
    }

    @Override
    public void showDialogKeyBoard(CriterioEvalUi criterioEvalUi) {
        DialogKeyBoardCriteriosEvaluacion dialogkeyboard = DialogKeyBoardCriteriosEvaluacion.newInstance(criterioEvalUi.getDescripcion());
        dialogkeyboard.setTargetFragment(this, 16);
        dialogkeyboard.show(getFragmentManager(), "dialogkeyboard");
    }

    @Override
    public void showCampoAcionList(List<CamposAccionUi> camposAccionUiList) {
        Log.d(TAG, "showCampoAcionList: " + camposAccionUiList.size());
        camposAccionAdapter.setCamposAccionList(camposAccionUiList);
    }

    @Override
    public void showSpinnerTipoEvaluacion(List<TipoEvaluacionUi> tipoEvaluacionUis) {
        tipoEvaluacionSpinnerAdapter.setTipoNotas(tipoEvaluacionUis);
    }

    @Override
    public void addColorCondicional(ColorCondicionalUi colorCondicionalUi) {
        //colorCondicionalAdapter.addColorCondicional(colorCondicionalUi);
    }

    @Override
    public int updateColorCondicional(ColorCondicionalUi colorCondicionalUi) {
        return 0;
    }

    @Override
    public void deleteColorCondicional(ColorCondicionalUi colorCondicionalUi) {
        //colorCondicionalAdapter.deleteColorCondiconal(colorCondicionalUi);
    }

    @Override
    public void setColoresCondionales(List<ColorCondicionalUi> colorCondicionalUis) {
        //colorCondicionalAdapter.setColorCondicionalUis(colorCondicionalUis);
    }

    @Override
    public void showColorCondicionalDialog(ColorCondicionalUi colorCondicionalUi) {
        FragmentManager manager = getFragmentManager();
        rangoCalificacionDialog = ColorCondicionalDialog.newInstance(colorCondicionalUi);
        rangoCalificacionDialog.setTargetFragment(CrearRubrosFragment.this, 200);
        rangoCalificacionDialog.show(manager, "ColorCondicionalDialog");
    }

    @Override
    public void hideColorCondicionalDialog() {
        rangoCalificacionDialog.dismiss();
    }

    @Override
    public List<ColorCondicionalUi> abstraerColoreCondicionales() {
        return new ArrayList<>();
    }

    @Override
    public void showColorPikerDialog(String titulo, int color) {
        FragmentManager manager = getFragmentManager();
        colorPicker = ColorPickerDialog.newInstance(titulo, color);
        colorPicker.setTargetFragment(CrearRubrosFragment.this, 200);
        colorPicker.show(manager, "ColorPickerDialog");
    }

    @Override
    public void errorTitulo(String error) {
        txtTitulo.setError(error);
    }

    @Override
    public void errorSubtitulo(String error) {
        txtSubtitulo.setError(error);
    }

    @Override
    public void mensajeToast(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CrearRubroListener) {
            listener = (CrearRubroListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.MyCustomObjectListener");
        }
    }

    @Override
    public void listenerSaveRubroSuccess(String rubroEvaluacionProcesoId, CrearRubroEvalUi crearRubroEvalUi, int programaEducativoId) {
        listener.onSaveRubroEvaluacionProcesoSuccess(rubroEvaluacionProcesoId, crearRubroEvalUi);
        CallService.jobServiceExportarTipos(getContext(), TipoExportacion.RUBROEVALUACION);
        SimpleSyncIntenService.start(getContext(), programaEducativoId);
        SynckService.start(getContext(),programaEducativoId);
        CMRE.saveNotifyChangeDataBase(getContext());
    }

    @Override
    public void showSpinnerFormaEvaluacion(List<FormaEvaluacionUi> formaEvaluacionUiList) {
        /*formaEvaluacionAdapter.setTipoNotas(formaEvaluacionUiList);*/
    }

    @Override
    public void showInputTipoEvaluacion(String tipoEvaluacion) {
        inputTipoEvaluacionn.setText(tipoEvaluacion);
    }

    @Override
    public void showInputFormaEvaluacion(String formaEvaluacion) {
        inputFormaEvaluacion.setText(formaEvaluacion);
    }

    @Override
    public void showListaIndicadoresFragment(int silavoEventoId, int sesionAprendizaje, int nivel, int competenciaId, int calendarioPeridoId, int indicadorId, ArrayList<Integer> camposIds) {
        Log.d(TAG, "INDICADORES SELECTED" + indicadorId);
        FragmentManager manager = getFragmentManager();

        if (sesionAprendizaje == 0) {
            listaIndicadoresFragment = ListaIndicadoresFragment.newInstanceCamposTematicos(ListaIndicadoresFragment.Id.SILAVO_EVENTO_ID, silavoEventoId, nivel, competenciaId, calendarioPeridoId, indicadorId, camposIds);

        } else {
            listaIndicadoresFragment = ListaIndicadoresFragment.newInstanceCamposTematicos(ListaIndicadoresFragment.Id.SESION_APRENDIZAJE_ID, sesionAprendizaje, nivel, competenciaId, calendarioPeridoId, indicadorId, camposIds);
        }
        listaIndicadoresFragment.setTargetFragment(this, 200);

        listaIndicadoresFragment.show(manager, ListaIndicadoresFragment.class.getSimpleName());

    }

    @Override
    public void showTecladoNumerico(EscalaEvaluacionUi escalaEvaluacionUi) {
        //cardTecladonumerico.setVisibility(View.VISIBLE);
        txtEscala.setText(escalaEvaluacionUi.getNombre());
        String rango = "(" + escalaEvaluacionUi.getValorMinimo() + "-" + escalaEvaluacionUi.getValorMaximo() + ")";
        //txtRangoEscala.setText(rango);
    }

    @Override
    public void hideTecladoNumerico() {
        //cardTecladonumerico.setVisibility(View.GONE);
    }

    @Override
    public void onSetCamposTematicos(int indicadorId, ArrayList<Integer> camposIds) {

        //listenercampos.onSelectCampoTematico(indicadorId, camposIds);
    }

    @Override
    public void changeStateIndicador() {
        state = true;
    }


    @Override
    public void setDatosRubro(String titulo, String subtitulo) {
        txtTitulo.setText(titulo);
        btnFormaEvaluacion.setEnabled(false);
        btnIndicador.setEnabled(false);
        lblIndicador.setEnabled(false);
        btnTipoNota.setEnabled(false);
        txtTipoNota.setEnabled(false);
        consTipoNota.setEnabled(false);
        txtSubtitulo.setText(subtitulo);
    }

    @Override
    public void showTituloEstrategia(String estrategia) {
        txtTitulo.setText(estrategia);
    }

    @Override
    public void showInputEstrategia() {
        btnEstrategia.setVisibility(View.VISIBLE);
        //txtSubtitulo.setVisibility(View.VISIBLE);
        txtTitulo.setFocusable(false);
    }

    @Override
    public void hideInputEstrategia() {
        txtTitulo.setFocusable(true);
        btnEstrategia.setVisibility(View.GONE);
        //txtSubtitulo.setVisibility(View.GONE);
    }

    @Override
    public void setTitulo(String tituloTarea) {
        txtTitulo.setText(tituloTarea);
    }

    @Override
    public void setSubTitulo(String tituloTarea) {
        txtSubtitulo.setText(tituloTarea);
    }

    @Override
    public void showActivityNivelLogro(int idProgramaEducativo) {
        Intent intent = TipoNotaListActivity.getSeleccionarTipoNotaListaIntent(getActivity(), "Nivel de Logro",
                idProgramaEducativo,
                true,
                TipoUi.Tipo.SELECTOR_ICONOS,
                TipoUi.Tipo.SELECTOR_VALORES,
                TipoUi.Tipo.VALOR_NUMERICO,
                TipoUi.Tipo.SELECTOR_NUMERICO);
        startActivityForResult(intent, SELECT_LISTATIPONOTAS_REQUEST);
    }

    @Override
    public void showNivelLogro(TipoNotaUi tipoNotaUi) {
        txtTituloTipoNota.setText(tipoNotaUi.getNombre());

        EscalaEvaluacionUi escalaUi = tipoNotaUi.getEscalaEvaluacionUi();
        String escala = "";
        String rango = "";
        if (escalaUi != null) {
            escala = escalaUi.getNombre();
            rango = "<b>(</b>" + escalaUi.getValorMinimo() + "  <b> - </b>" + escalaUi.getValorMaximo() + "<b>)</b>";
        }
        txtDescripcionEscala.setText(Html.fromHtml(rango));
        txtEscala.setText(escala);
        switch (tipoNotaUi.getTipoNota()) {
            case DEFECTO:
                tilTipoNota.setVisibility(View.GONE);
                imgValorNumerico.setVisibility(View.GONE);
                imgSelectorNumerico.setVisibility(View.VISIBLE);
                valorTipoNotaAdapter.setList(new ArrayList<ValorTipoNotaUi>());

                break;
            case VALOR_NUMERICO:
                tilTipoNota.setVisibility(View.GONE);
                imgSelectorNumerico.setVisibility(View.GONE);
                imgValorNumerico.setVisibility(View.VISIBLE);
                valorTipoNotaAdapter.setList(new ArrayList<ValorTipoNotaUi>());

                break;
            case IMAGEN:
                imgSelectorNumerico.setVisibility(View.GONE);
                imgValorNumerico.setVisibility(View.GONE);
                tilTipoNota.setVisibility(View.VISIBLE);
                valorTipoNotaAdapter.setList(tipoNotaUi.getValorTipoNotaUis());

                break;
            case TEXTO:
                imgSelectorNumerico.setVisibility(View.GONE);
                imgValorNumerico.setVisibility(View.GONE);
                tilTipoNota.setVisibility(View.VISIBLE);
                valorTipoNotaAdapter.setList(tipoNotaUi.getValorTipoNotaUis());

                break;
        }
    }

    @Override
    public void setCompetencia(String nombre) {
        txtCapacidad.setText(nombre);
    }

    @Override
    public void showDialogInfoTipoNota(String titulo, TipoNotaUi tipoNotaUi) {
        DialogValorTipoNota dialogValorTipoNota = DialogValorTipoNota.newInstance(titulo, tipoNotaUi);
        dialogValorTipoNota.show(getChildFragmentManager(), DialogValorTipoNota.class.getSimpleName());
    }

    @Override
    public void setCapacidad(String nombreCapacidad) {
        txtCompetencia.setText(nombreCapacidad);
    }


    //endregion view

    //region presenter
    @OnClick({R.id.btn_tipo_evaluacion, R.id.btn_forma_evaluacion, R.id.btnIndicador, R.id.btnEstrategia, R.id.txt_tipo_nota, R.id.btnTipoNota, R.id.lbl_indicador, R.id.cons_tipo_nota})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tipo_evaluacion:
                presenter.onBtnTipoEvaluacionClicked();
                break;
           /* case R.id.btn_addColorCondicional:
                presenter.onClickAddColorCondicional();
                break;*/
            case R.id.btn_forma_evaluacion:
                presenter.onClickFormaEvaluacion();
                break;
            case R.id.btnIndicador:
                presenter.onClickIndicador();
                break;
            case R.id.btnEstrategia:
                presenter.onClickEstrategiaEval();
                break;
            case R.id.lbl_indicador:
                presenter.onClickIndicador();
                break;
            case R.id.btnTipoNota:
                presenter.onClickNivel();
                break;
            case R.id.txt_tipo_nota:
                presenter.onClickNivel();
                break;
            case R.id.cons_tipo_nota:
                presenter.onClickNivel();
                break;
        }
    }

    @Override
    public void onClickItemCriterioEval(CriterioEvalUi criterioEvalUi, List<ValorTipoNotaUi> valorTipoNotaUi) {
        if (state) presenter.onClickItemCriterioEval(criterioEvalUi, valorTipoNotaUi);
        else Toast.makeText(getContext(), "Seleccione un Indicador", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void editCriterioEval(List<ValorTipoNotaUi> valorTipoNotaUi) {
        presenter.editCriterioEval(valorTipoNotaUi);
    }


    @Override
    public void onCriterioSaved(ValorTipoNotaUi valorTipoNotaUi) {
        presenter.onClickSaveCriterioEval(valorTipoNotaUi);
    }

    @Override
    public void onCriterioClose() {
        presenter.onClickCloseCriterioEval();
    }

    public void onSelectTipoNota(TipoNotaUi tipoNotaUi) {
        presenter.onSelectTipoNota(tipoNotaUi);
    }

    public void onSelectTipoEvaluacion(TipoEvaluacionUi tipoEvaluacionUi) {
        presenter.onSelectTipoEvaluacion(tipoEvaluacionUi);
    }

    private void onSelectFormaEvaluacion(FormaEvaluacionUi formaEvaluacionUi) {
        presenter.onSelectFormaEvaluacion(formaEvaluacionUi);
    }

    //region Criterios Evaluacion Item
    @Override
    public void onClickRango(ColorCondicionalUi colorCondicionalUi) {
        presenter.onClickItemColorCondicional(colorCondicionalUi);
    }

    @Override
    public void onClickDesde(ColorCondicionalUi colorCondicionalUi) {
        presenter.onClickItemCheckDesdeColorCondicional(colorCondicionalUi);
    }

    @Override
    public void onClickHascta(ColorCondicionalUi colorCondicionalUi) {
        presenter.onClickItemCheckHastaColorCondicional(colorCondicionalUi);
    }

    @Override
    public void onClickEliminar(ColorCondicionalUi colorCondicionalUi) {
        presenter.onClickItemEliminarColorCondicional(colorCondicionalUi);
    }

    @Override
    public void onClickColorTexto(ColorCondicionalUi colorCondicionalUi) {
        presenter.onClickColorTexto(colorCondicionalUi);
    }

    @Override
    public void onClickColorFondo(ColorCondicionalUi colorCondicionalUi) {
        presenter.onClickColorFondo(colorCondicionalUi);
    }
    //endregion Criterios Evaluacion Item

    //region AdapterExample Rango de Calificacion
    @Override
    public void onSaveRangoCalificacion(ColorCondicionalUi colorCondicionalUi) {
        presenter.onClickSaveItemColorCondicional(colorCondicionalUi);
    }

    @Override
    public void onCloseRangoCalificacion() {
        presenter.onClickCloseItemColorCondicional();
    }

    //endregion Rango de Calificacion

    @Override
    public void onColorSelected(int color) {
        presenter.onSelectColor(color);
    }

    public void onSelectTipoNota(String tipoNotaId) {
        Log.d(TAG, "onSelectTipoNota: " + tipoNotaId);
        if (presenter == null) return;
        Log.d(TAG, "presenter.onSelectTipoNota: " + tipoNotaId);
        presenter.onSelectTipoNota(tipoNotaId);
    }

    @Override
    public void showListSingleChooser(String dialogTitle, List<Object> items, int positionSelected) {
        super.showListSingleChooser(dialogTitle, items, positionSelected);

    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    public void onCancelar() {
        presenter.onClickCancelar();
    }

    public void onAceptar() {
        presenter.onClickAceptar(txtTitulo.getText().toString(), txtSubtitulo.getText().toString(), "", criterioEvalAdapter.getValorTipoNotaUis());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onSelectIndicadorCampotematicovoid(int competenciaId, int indicadorId, ArrayList<Integer> campotematicoIds) {
        presenter.onChangeIndicadorCampotematico(competenciaId, indicadorId, campotematicoIds);
        listaIndicadoresFragment.dismiss();
    }

    @Override
    public void onSalirListaIndicadores() {
        listaIndicadoresFragment.dismiss();
    }

    @Override
    protected Integer[] getEditTextList() {
        return new Integer[]{
                R.id.txt_titulo
        };
    }

    //endregion presenter

    /*@OnClick(R.id.txt_hasta1)
    public void onViewClicked() {
        EditSelectorValDialog fragment = EditSelectorValDialog.newInstance();
        fragment.show(getFragmentManager(), "EditSelectorValDialog");
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult requestCode: " + requestCode + ", resultCode: " + resultCode);
        if (requestCode == SELECT_LISTATIPONOTAS_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            Log.d(TAG, "onActivityResult requestCode: " + requestCode + ", resultCode: " + resultCode);
            String tipoNotaId = data.getExtras().getString(TipoNotaListActivity.TIPONOTAID);
            presenter.onSingleItemSelectedTipoNota(tipoNotaId);
        }
    }

    @Override
    public void onClick(View view, int position) {
        presenter.onClickNivel();
    }

    @Override
    public void onLongClick(View view, int position) {

    }


    @OnClick(R.id.btn_info_tipo_nota)
    public void onViewClicked() {
        presenter.onClickInfoTipoNota();
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


    @Override
    public void onClickAceptarDialogkeyboard(String contenido) {
        presenter.onClickAceptarDialogkeyboard(contenido);
    }

    @Override
    public void onDismissDialogkeyboard() {
        presenter.onDismissDialogkeyboard();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                KeyboardUtils.hideKeyboard(getActivity());
            }
        },200);
    }

    @Override
    public void onCreateDialogKeyBoard(DialogkeyBoardView view) {
        presenter.onCreateDialogKeyBoard(view);
    }
}
