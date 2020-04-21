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
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubrosAsociadosUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RecyclerItemClickListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroEvaluacionProcesoListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.NombreRubrosAsociadosAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter.DetalleRubroEvaluacionProcesoListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 17/05/2018.
 */

public class RubroProcesoFormulaHolder extends CrearRubroProcesoHolderBase {
    @BindView(R.id.editText2)
    EditText editText2;

    public static final String RUBRO_PROCESO_FORMULA_HOLDER = RubroProcesoFormulaHolder.class.getSimpleName();


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
    @BindView(R.id.card_rubro_proceso)
    CardView cardRubroProceso;
    @BindView(R.id.ImageCheck)
    ImageView imageCheked;
    @BindView(R.id.imageTipoAncla)
    ImageView imageTipoAncla;
    @BindView(R.id.rc_asociados)
    RecyclerView rcAsociados;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.contItemView)
    ConstraintLayout constraintLayoutCabecera;
    private Animation slideDown;
    private Animation slideUp;

    public RubroProcesoFormulaHolder(View itemView) {
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
        initTipoAnclar(rubroProcesoUi);
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
        List<RubrosAsociadosUi> rubrosAsociadosUiList = rubroProcesoUi.getRubrosAsociadosUiList();
        if(rubrosAsociadosUiList==null)return;
        NombreRubrosAsociadosAdapter nombreRubrosAsociadosAdapter = new NombreRubrosAsociadosAdapter(rubrosAsociadosUiList);
        rcAsociados.setLayoutManager(new LinearLayoutManager(rcAsociados.getContext()));
        rcAsociados.setAdapter(nombreRubrosAsociadosAdapter);
        rcAsociados.addOnItemTouchListener(new RecyclerItemClickListener(rcAsociados,this));
    }

    private void initTipoAnclar(RubroProcesoUi rubroProcesoUi) {
        switch (rubroProcesoUi.getTipoFormula()) {
            case ANCLADA:
                imageTipoAncla.setVisibility(View.VISIBLE);
                if (slideUp == null) return;
                imageTipoAncla.startAnimation(slideUp);
                imageTipoAncla.setColorFilter(itemView.getContext().getResources().getColor(R.color.color_yellow));
                break;
            case EVALUADA:
                imageTipoAncla.setVisibility(View.VISIBLE);
                if (slideUp == null) return;
                imageTipoAncla.startAnimation(slideUp);
                imageTipoAncla.setColorFilter(itemView.getContext().getResources().getColor(R.color.md_red_300));
                break;
            default:
                imageTipoAncla.setVisibility(View.GONE);
                if (slideUp == null) return;
                imageTipoAncla.startAnimation(slideDown);
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
                validacionCheckSilabo(rubroProcesoUiFormula);
                textViewTipoCursoSilabo.setText("Área");
                if (rubroProcesoUiFormula.getSesionAprendizajeId() > 0)
                    textViewTipoCursoSilabo.setText("Sesión");
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

    private void initView(RubroProcesoUi rubroProcesoUiFormula) {
        String titulo = "<b>" + rubroProcesoUi.getPosicion() + ":</b> " + rubroProcesoUi.getTitulo();
        txtTitulo.setText(Html.fromHtml(titulo));
        txtFecha.setText(rubroProcesoUiFormula.getFecha());
        txtMedia.setText((rubroProcesoUiFormula.getMedia() + "(" + rubroProcesoUiFormula.getDesviacionE() + ")"));
    }

    private void validarListener(RubroEvaluacionProcesoListener listener) {
        if (listener != null) this.listener = listener;
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

    private void onLongClickChecked(RubroProcesoUi rubroProcesoUi, RubroEvaluacionProcesoListener listener) {
        switch (rubroProcesoUi.getOrigen()) {
            case SILABO:
                setAnimacion();
                onLongClickCheckedSilabo(rubroProcesoUi);
                listener.onClickRubrosAsociados(rubroProcesoUi,capacidadUi);
                break;
            case SESION:
                Log.d(RUBRO_PROCESO_FORMULA_HOLDER, "long Invalido " + rubroProcesoUi.getCapacidadId());
                break;
        }
    }

    private void setAnimacion() {
        if(this.slideUp==null)this.slideUp = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.slide_up);
        if(this.slideUp==null)this.slideDown = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.slide_down);
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
