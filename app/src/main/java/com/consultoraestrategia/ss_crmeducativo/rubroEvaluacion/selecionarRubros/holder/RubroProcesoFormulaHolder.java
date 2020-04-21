package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.RubrosAsociadosAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubrosAsociadosUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RecyclerItemClickListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroEvaluacionProcesoListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.NombreRubrosAsociadosAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 17/05/2018.
 */

public class RubroProcesoFormulaHolder extends RecyclerView.ViewHolder implements View.OnClickListener,RecyclerItemClickListener.RecyclerViewOnItemClickListener {

    public static final String RUBRO_PROCESO_FORMULA_HOLDER = RubroProcesoFormulaHolder.class.getSimpleName();


    private RubroProcesoUi rubroProcesoUi;
    private CapacidadUi capacidadUi;
    private RubroEvaluacionProcesoListener listener;
    @BindView(R.id.txtTitulo)
    TextView txtTitulo;
    @BindView(R.id.idheadConstraint)
    ConstraintLayout idheadConstraint;
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
    @BindView(R.id.recyclerCircleRubro)
    RecyclerView recyclerView;
    @BindView(R.id.rc_asociados)
    RecyclerView rcAsociados;
    private Animation slideDown;
    private Animation slideUp;

    public RubroProcesoFormulaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardRubroProceso.setOnClickListener(this);

    }


    public void bind(RubroProcesoUi rubroProcesoUiFormula, RubroEvaluacionProcesoListener listener, CapacidadUi capacidadUi) {
        validarListener(listener);
        this.rubroProcesoUi = rubroProcesoUiFormula;
        this.capacidadUi = capacidadUi;
        initView(rubroProcesoUiFormula);
        initViewTipoOrigen(rubroProcesoUiFormula);
        initViewFormulaEvaluacion(rubroProcesoUiFormula);
        initViewTipoEvaluacion(rubroProcesoUiFormula);
        initTipoAnclar(rubroProcesoUiFormula);
        initViewEstilo(rubroProcesoUiFormula);
        initFormulaAdapter();
        initIndicador();
        textViewTipoCursoSilabo.setText("Área");
    }

    private void initIndicador() {
        List<RubrosAsociadosUi> rubrosAsociadosUiList = rubroProcesoUi.getRubrosAsociadosUiList();
        if(rubrosAsociadosUiList==null)return;
        NombreRubrosAsociadosAdapter nombreRubrosAsociadosAdapter = new NombreRubrosAsociadosAdapter(rubrosAsociadosUiList);
        rcAsociados.setLayoutManager(new LinearLayoutManager(rcAsociados.getContext()));
        rcAsociados.setAdapter(nombreRubrosAsociadosAdapter);
        rcAsociados.addOnItemTouchListener(new RecyclerItemClickListener(rcAsociados,this));
        rcAsociados.setNestedScrollingEnabled(false);
    }

    private void initTipoAnclar(RubroProcesoUi rubroProcesoUi) {
        switch (rubroProcesoUi.getTipoFormula()) {
            case ANCLADA:
                imageTipoAncla.setVisibility(View.VISIBLE);
                if (slideUp != null) imageTipoAncla.startAnimation(slideUp);
                imageTipoAncla.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_big_anchor2));
                break;
            case EVALUADA:
                imageTipoAncla.setVisibility(View.VISIBLE);
                if (slideUp != null) imageTipoAncla.startAnimation(slideUp);
                imageTipoAncla.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_big_anchor_red));
                break;
            default:
                imageTipoAncla.setVisibility(View.GONE);
                if (slideDown == null) return;
                imageTipoAncla.startAnimation(slideDown);
                break;
        }
    }

    private void initFormulaAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        RubrosAsociadosAdapter adapter;
        if (rubroProcesoUi.getRubrosAsociadosUiList() != null) {
            adapter = new RubrosAsociadosAdapter(rubroProcesoUi.getRubrosAsociadosUiList());
        } else {
            adapter = new RubrosAsociadosAdapter(new ArrayList<RubrosAsociadosUi>());
        }
        recyclerView.setAdapter(adapter);
    }

    private void initViewTipoEvaluacion(RubroProcesoUi rubroProcesoUiFormula) {
        textViewTipoEvaluacion.setText(rubroProcesoUiFormula.getTipoEvaluacion());
    }

    private void initViewEstilo(RubroProcesoUi rubroProcesoUiFormula) {
        switch (rubroProcesoUiFormula.getEstiloFormula()) {
            case PLOMO:
                idheadConstraint.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_grey_200 ));
                break;
            case NEGRO:
                idheadConstraint.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.black ));
                txtTitulo.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.white ));
                break;
            case ANARANJADO:
                idheadConstraint.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_teal_50 ));
                break;
        }
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
                validacionCheckSilabo(rubroProcesoUiFormula);
                break;
            case SILABO:
                validacionCheckSilabo(rubroProcesoUiFormula);
                textViewTipoCursoSilabo.setText("Área");
                if (rubroProcesoUiFormula.getSesionAprendizajeId() > 0)
                    textViewTipoCursoSilabo.setText("Sesión");
                break;
            case TAREA:
                textViewTipoCursoSilabo.setText("Tarea");
                validacionCheckSilabo(rubroProcesoUiFormula);
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
    public void onItemLongClick(RecyclerView.ViewHolder viewHolder, View clickedView) {

    }
}
