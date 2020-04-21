package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RecyclerItemClickListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroEvaluacionProcesoListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.CamposAccionAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter.DetalleRubroEvaluacionProcesoListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 18/05/2018.
 */

public class RubroProcesoNormalHolder extends CrearRubroProcesoHolderBase {
    @BindView(R.id.editText2)
    EditText editText2;
    public static final String RUBRO_PROCESO_NORMAL_HOLDER = RubroProcesoNormalHolder.class.getSimpleName();
    private RubroProcesoUi rubroProcesoUi;
    private CapacidadUi capacidadUi;
    private RubroEvaluacionProcesoListener listener;
    @BindView(R.id.txtTitulo)
    TextView txtTitulo;
    @BindView(R.id.txtFecha)
    TextView txtFecha;
    @BindView(R.id.media)
    TextView txtMedia;
    @BindView(R.id.tipo)
    TextView textViewTipoEvaluacion;
    @BindView(R.id.forma)
    TextView textViewTipoFormaEvaluacion;
    @BindView(R.id.origen)
    TextView textViewTipoCursoSilabo;
    @BindView(R.id.ImageCheck)
    ImageView imageCheked;
    @BindView(R.id.card_rubro_proceso)
    CardView cardRubroProceso;
    @BindView(R.id.txt_indicador)
    TextView txtIndicador;
    @BindView(R.id.rc_campotematico)
    RecyclerView rcCampotematico;
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @BindView(R.id.imageView8)
    ImageView imageView8;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.contItemView)
    ConstraintLayout constraintLayoutCabecera;
    private Animation slideDown;
    private Animation slideUp;

    private boolean isActivarLongProlongado = false;

    public RubroProcesoNormalHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardRubroProceso.setOnClickListener(this);
    }

    @Override
    protected FrameLayout getFrameLayout() {
        return frameLayout;
    }

    @Override
    protected View getLayout() {
        return constraintLayoutCabecera;
    }

    public void bind(RubroProcesoUi rubroProcesoUiFormula, DetalleRubroEvaluacionProcesoListener listener, CapacidadUi capacidadUi) {
        validarListener(listener);
        this.rubroProcesoUi = rubroProcesoUiFormula;
        this.capacidadUi = capacidadUi;
        initView(rubroProcesoUiFormula);
        initViewTipoOrigen(rubroProcesoUiFormula);
        initViewFormulaEvaluacion(rubroProcesoUiFormula);
        initViewTipoEvaluacion(rubroProcesoUiFormula);
        initIndicador();
        initEditPeso();
    }

    private void initEditPeso() {
        if (rubroProcesoUi.isCheck()) {
            editText2.setVisibility(View.VISIBLE);
            if (rubroProcesoUi.getPeso() == 0.0) {
                editText2.setText(String.valueOf((int)rubroProcesoUi.getPeso()));
                editText2.setVisibility(View.GONE);
            } else {
                editText2.setVisibility(View.VISIBLE);
                editText2.setText(String.valueOf((int)rubroProcesoUi.getPeso()));
                editText2.addTextChangedListener(this);
            }
        } else {
            Log.d(PesoRubroHolder.class.getSimpleName(), "falseee");
            editText2.setVisibility(View.GONE);
        }
    }

    private void initIndicador() {
        IndicadoresCamposAccionUi indicadoresCamposAccionUi = rubroProcesoUi.getIndicadoresCamposAccionUi();
        List<CamposAccionUi> camposAccionUiList = null;
        if(indicadoresCamposAccionUi != null){
            imageView7.setVisibility(View.VISIBLE);
            //imageView8.setVisibility(View.VISIBLE);
            txtIndicador.setText(indicadoresCamposAccionUi.getTitulo());
            camposAccionUiList = indicadoresCamposAccionUi.getCampoAccionList();
        }else {
            imageView7.setVisibility(View.GONE);
            //imageView8.setVisibility(View.GONE);
        }
        if(camposAccionUiList==null)return;
        CamposAccionAdapter camposAccionAdapter = new CamposAccionAdapter(camposAccionUiList);
        rcCampotematico.setLayoutManager(new LinearLayoutManager(rcCampotematico.getContext()));
        rcCampotematico.setAdapter(camposAccionAdapter);
        rcCampotematico.addOnItemTouchListener(new RecyclerItemClickListener(rcCampotematico,this));

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_rubro_proceso:
                onLongClickChecked(rubroProcesoUi, listener);
                break;
            default:
                break;
        }
    }

    private void initViewTipoEvaluacion(RubroProcesoUi rubroProcesoUiFormula) {
        textViewTipoEvaluacion.setText(rubroProcesoUiFormula.getTipoEvaluacion());
    }

    private void initViewFormulaEvaluacion(RubroProcesoUi rubroProcesoUiFormula) {
        switch (rubroProcesoUiFormula.getFormEvaluacion()) {
            case GRUPAL:
                textViewTipoFormaEvaluacion.setText("Grupal");
                break;
            case INDIVIDUAL:
                textViewTipoFormaEvaluacion.setText("Individual");
                break;
            case FORMULA:
                textViewTipoFormaEvaluacion.setText("Individual");
                break;
        }
    }

    private void initViewTipoOrigen(RubroProcesoUi rubroProcesoUiFormula) {
        switch (rubroProcesoUiFormula.getOrigen()) {
            case SESION:
                textViewTipoCursoSilabo.setText("Sesión");
                break;
            case SILABO:
                textViewTipoCursoSilabo.setText("Área");
                validacionCheckSilabo(rubroProcesoUiFormula);
                if (rubroProcesoUiFormula.getSesionAprendizajeId() > 0)
                    textViewTipoCursoSilabo.setText("Sesión");
                break;
        }
    }


    private void initView(RubroProcesoUi rubroProcesoUiFormula) {
        String titulo = "<b>" + rubroProcesoUi.getPosicion() + ":</b> " + rubroProcesoUi.getTitulo();
        txtTitulo.setText(Html.fromHtml(titulo));
        txtFecha.setText(rubroProcesoUiFormula.getFecha());
        txtMedia.setText(rubroProcesoUiFormula.getMedia() + "(" + rubroProcesoUiFormula.getDesviacionE() + ")");
    }

    private void validarListener(RubroEvaluacionProcesoListener listener) {
        if (listener != null) this.listener = listener;
    }

    private void onLongClickChecked(RubroProcesoUi rubroProcesoUi, RubroEvaluacionProcesoListener listener) {
        Log.d(RUBRO_PROCESO_NORMAL_HOLDER, "onLongClickChecked " + rubroProcesoUi.getOrigen().name());
        switch (rubroProcesoUi.getOrigen()) {
            case SILABO:
                setAnimacion();
                onLongClickCheckedSilabo(rubroProcesoUi);
                listener.onClickRubrosAsociados(rubroProcesoUi, capacidadUi);

                break;
            case SESION:
                Log.d(RUBRO_PROCESO_NORMAL_HOLDER, "long Invalido " + rubroProcesoUi.getCapacidadId());
                break;
        }
    }

    private void validacionCheckSilabo(RubroProcesoUi rubroProcesoUiFormula) {
        if(rubroProcesoUi.isCheckDisbled())return;
        if (rubroProcesoUiFormula.isCheck()) {
            imageCheked.setVisibility(View.VISIBLE);
        } else {
            imageCheked.setVisibility(View.GONE);
        }
    }

    private void setAnimacion() {
        this.slideUp = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.slide_up);
        this.slideDown = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.slide_down);
    }

    private void onLongClickCheckedSilabo(RubroProcesoUi rubroProcesoUi) {
        if(rubroProcesoUi.isCheckDisbled())return;
        if (!rubroProcesoUi.isCheck()) {
            imageCheked.setVisibility(View.VISIBLE);
            if (slideUp == null) return;
            imageCheked.startAnimation(slideUp);
        } else {
            imageCheked.setVisibility(View.GONE);
            if (slideDown == null) return;
            imageCheked.startAnimation(slideDown);
        }
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View clickedView) {
        onLongClickChecked(rubroProcesoUi, listener);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        listener.onPesoChanged(rubroProcesoUi,charSequence);
    }
}
