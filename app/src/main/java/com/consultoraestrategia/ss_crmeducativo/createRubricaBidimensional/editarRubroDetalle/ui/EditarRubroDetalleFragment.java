package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.ui;


import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.dialogKeyBoard.DialogKeyBoardCriteriosEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.dialogKeyBoard.DialogkeyBoardView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.EditarRubroDetallePresenter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.EditarRubroDetallePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.adapter.CampoAccionAdapter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.adapter.CellAdapter;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.listener.EditarRubroDetalleCallBack;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.listener.InfoRubricaBidimensionalListener;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.lib.justify.JustifiedTextView;
import com.consultoraestrategia.ss_crmeducativo.util.KeyboardUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by @stevecampos on 28/02/2018.
 */

public class EditarRubroDetalleFragment extends BaseDialogFragment<EditarRubroDetalleView, EditarRubroDetallePresenter, EditarRubroDetalleView> implements EditarRubroDetalleView, View.OnClickListener, Serializable, InfoRubricaBidimensionalListener,DialogKeyBoardCriteriosEvaluacion.CallBack {
    public static final String TAG = EditarRubroDetalleFragment.class.getSimpleName();
    public static final String ARG_INDICADOR = "EditarRubroDetalleFragment.IndicadorUi";
    public static final String ARG_TIPONOTA = "EditarRubroDetalleFragment.TipoNotaUi";
    public static final String ARG_LISTDETALLE = "EditarRubroDetalleFragment.ListDetalle";
    public static final String ARG_DISABLEDCAMPOACCION = "EditarRubroDetalleFragment.disabledCampoAccion";
    @BindView(R.id.edit_text_ti_indicador)
    EditText editTextTiIndicador;
    @BindView(R.id.txt_titulo_criterio_eval)
    TextView txtTituloCriterioEval;
    @BindView(R.id.rec_criterio_eval)
    RecyclerView recCriterioEval;
    @BindView(R.id.card_criterio_eval)
    CardView cardCriterioEval;
    @BindView(R.id.rc_campos_accion)
    RecyclerView rcCamposAccion;
    @BindView(R.id.txt_competencia)
    TextView txtCompetencia;
    @BindView(R.id.txt_capacidad)
    TextView txtCapacidad;
    @BindView(R.id.text_sub_indicador)
    TextView textSubIndicador;
    @BindView(R.id.text_ti_indicador)
    TextView textTiIndicador;
    @BindView(R.id.txt_vermas_desem)
    TextView txtVermasDesem;
    @BindView(R.id.txt_desempenio)
    JustifiedTextView txtDesempenio;
    @BindView(R.id.conten_desempenio)
    ConstraintLayout contenDesempenio;
    @BindView(R.id.text_descripcion)
    TextView textDescripcion;
    @BindView(R.id.scroll_edit_rubro_det)
    NestedScrollView scrollEditRubroDet;
    @BindView(R.id.bttn_negative)
    ImageView bttnNegative;
    @BindView(R.id.bttn_positive)
    Button bttnPositive;
    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.img_indicador)
    ImageView imgIndicador;
    @BindView(R.id.textView57)
    TextView txtGuionDescripcion;
    @BindView(R.id.textView155)
    TextView txtGuionTitulo;


    private EditarRubroDetalleCallBack callback;
    private CampoAccionAdapter campoAccionAdapter;
    private CellAdapter cellAdapter;

    public static EditarRubroDetalleFragment newInstance(IndicadorUi indicadorUi, TipoNotaUi tipoNotaUi, List<Cell> cellList, boolean disabledCampoAccion) {
        EditarRubroDetalleFragment frag = new EditarRubroDetalleFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_INDICADOR, indicadorUi);
        args.putSerializable(ARG_TIPONOTA, tipoNotaUi);
        args.putSerializable(ARG_LISTDETALLE, (Serializable) cellList);
        args.putSerializable(ARG_DISABLEDCAMPOACCION, disabledCampoAccion);

        frag.setArguments(args);
        return frag;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected EditarRubroDetallePresenter getPresenter() {
        return new EditarRubroDetallePresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
    }

    @Override
    protected EditarRubroDetalleView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.layout_edit_rub_detalle, container, false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onResume() {

        /*
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.90), (int) (size.y * 0.85));
        window.setGravity(Gravity.CENTER);*/
        // Call super onResume after sizing

        super.onResume();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupTextListner();
        setupAdapterCampoAccion();
        setupAdapterCriterios();
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupTextListner() {
        editTextTiIndicador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                presenter.onTextChangedTitulo(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setupAdapterCriterios() {
        cellAdapter = new CellAdapter(new ArrayList<CriterioEvaluacionUi>(), recCriterioEval, this);
        cellAdapter.setRecyclerView(recCriterioEval);
        recCriterioEval.setAdapter(cellAdapter);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        recCriterioEval.setLayoutManager(linearLayoutManager1);
        recCriterioEval.setHasFixedSize(true);
        recCriterioEval.setNestedScrollingEnabled(false);
    }

    private void setupAdapterCampoAccion() {
        campoAccionAdapter = new CampoAccionAdapter(new ArrayList<CampoAccionUi>());
        rcCamposAccion.setAdapter(campoAccionAdapter);
        rcCamposAccion.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcCamposAccion.setNestedScrollingEnabled(false);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return scrollEditRubroDet;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EditarRubroDetalleCallBack) {
            callback = (EditarRubroDetalleCallBack) context;
        } else
            throw new ClassCastException(context.getClass().getSimpleName() + "must be implement EditarRubroDetalleCallBack");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        callback = null;
    }

    @Override
    public void showIndicador(IndicadorUi indicadorUi) {
        editTextTiIndicador.setText(indicadorUi.getTituloRubro());

        if(TextUtils.isEmpty(indicadorUi.getAlias())){
            textSubIndicador.setText(indicadorUi.getTitulo());
            textTiIndicador.setVisibility(View.GONE);
            txtGuionTitulo.setVisibility(View.GONE);
        }else {
            textSubIndicador.setText(indicadorUi.getAlias());
            textTiIndicador.setVisibility(View.VISIBLE);
            String titulo = "Título: "+ indicadorUi.getTitulo();
            textTiIndicador.setText(titulo);
            txtGuionTitulo.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(indicadorUi.getDescripcion())) {
            String descripcion = "Descripción: "+ indicadorUi.getDescripcion();
            textDescripcion.setText(descripcion);
            textDescripcion.setVisibility(View.VISIBLE);
            txtGuionDescripcion.setVisibility(View.VISIBLE);
        } else {
            textDescripcion.setVisibility(View.GONE);
            txtGuionDescripcion.setVisibility(View.GONE);
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

        txtDesempenio.setText(indicadorUi.getDesempenioDesc());
        txtDesempenio.post(new Runnable() {
            @Override
            public void run() {
                presenter.postCantidadLineasDesempenio(txtDesempenio.getLineCount());
            }
        });

        //txtSubTitle.setText(indicadorUi.getTituloRubro());
        //editTextSubTitulo.setText(indicadorUi.getTituloRubro());
    }

    @Override
    public void showCompetencia(CompetenciaUi competenciaOwner) {
        txtCompetencia.setText(competenciaOwner.getTitle());
    }

    @Override
    public void showCapacidad(CapacidadUi capacidadOwner) {
        txtCapacidad.setText(capacidadOwner.getTitle());
    }

    @Override
    public void showCampoAccionList(List<CampoAccionUi> campoAccionList) {
        Log.d(TAG, "showCampoAccionList size: " + campoAccionList.size());
        campoAccionAdapter.setItems(campoAccionList);
        scrollEditRubroDet.fullScroll(ScrollView.FOCUS_UP);
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


    @OnClick({R.id.bttn_negative, R.id.bttn_positive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bttn_negative:
                presenter.onCampoTematicoCancel();
                break;
            case R.id.bttn_positive:
                presenter.onCampoTematicoListOk();
                break;
           /* case R.id.img_edit:
                presenter.onClickEditar();
                break;*/
        }
    }


    private void onClickVerMas() {
        presenter.onClickVerMasDesempenio();
    }


    @Override
    public void onClickAcetarEditarRubroDetalle(IndicadorUi indicadorUi) {
        callback.onClickAcetarEditarRubroDetalle(indicadorUi);
        dismiss();
    }

    @Override
    public void onClickCancelarEditarRubroDetalle() {
        callback.onClickCancelarEditarRubroDetalle();
        dismiss();
    }

    @Override
    public void showTipoNota(TipoNotaUi tipoNotaUi, List<Cell> cellList) {
        EscalaEvaluacionUi escalaUi = tipoNotaUi.getEscalaEvaluacionUi();
        String rangoEscala = "<b>(</b>" + escalaUi.getValorMinimo() + "  <b> - </b>" + escalaUi.getValorMaximo() + "<b>)</b>";
        /*txtRangoEscala.setText(Html.fromHtml(rangoEscala));
        imgSelectorNumerico.setVisibility(View.GONE);
        imgValorNumerico.setVisibility(View.GONE);
        gridSelector.setVisibility(View.GONE);
        switch (tipoNotaUi.getTipo()) {
            case SELECTOR_NUMERICO:
                imgSelectorNumerico.setVisibility(View.VISIBLE);
                break;
            case VALOR_NUMERICO:
                imgValorNumerico.setVisibility(View.VISIBLE);
                break;
            case SELECTOR_ICONOS:
                selectorValoresIconos(tipoNotaUi, cellList);
                break;
            case SELECTOR_VALORES:
                selectorValoresIconos(tipoNotaUi, cellList);
                break;
        }*/

    }

    @Override
    public void disabledCampoAccion() {
        campoAccionAdapter.setDisabledCampoAccion(true);
    }

    @Override
    public void enabledCampoAccion() {
        campoAccionAdapter.setDisabledCampoAccion(false);
    }

    @Override
    public void showCriterioEvaluacion(List<CriterioEvaluacionUi> criterioEvaluacionUiList) {
        recCriterioEval.setVisibility(View.VISIBLE);
        txtTituloCriterioEval.setVisibility(View.VISIBLE);
        cellAdapter.setCellList(criterioEvaluacionUiList);
    }

    @Override
    public void hideCriterioEvaluacion() {
        recCriterioEval.setVisibility(View.GONE);
        txtTituloCriterioEval.setVisibility(View.GONE);
    }

    @Override
    public void setTituloRubro(String editableTitulo) {
        editTextTiIndicador.setText(editableTitulo);
    }

    @Override
    public void showDialogEditCriterio(CriterioEvaluacionUi criterioEvaluacionUi) {
        DialogKeyBoardCriteriosEvaluacion dialogkeyboard = DialogKeyBoardCriteriosEvaluacion.newInstance(criterioEvaluacionUi.getTitulo());
        dialogkeyboard.setTargetFragment(this, 16);
        dialogkeyboard.show(getFragmentManager(), "dialogkeyboard");
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

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void sendList(List<CriterioEvaluacionUi> criterioEvalUis) {

    }

    @Override
    public void onClickCriterioEvaluacion(CriterioEvaluacionUi criterioEvaluacionUi) {
      presenter.onClickCriterioEvaluacion(criterioEvaluacionUi);
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
