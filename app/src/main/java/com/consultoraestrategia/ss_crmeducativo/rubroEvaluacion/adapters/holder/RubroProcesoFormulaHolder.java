package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
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
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroEvaluacionProcesoListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.NombreRubrosAsociadosAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 17/05/2018.
 */

public class RubroProcesoFormulaHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener,Runnable {

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
    @BindView(R.id.contItemView)
    ConstraintLayout constraintLayoutRubros;
    @BindView(R.id.idheadConstraint)
    ConstraintLayout constraintLayoutCabecera;
    @BindView(R.id.ImageCheck)
    ImageView imageCheked;
    @BindView(R.id.imageTipoAncla)
    ImageView imageTipoAncla;
    @BindView(R.id.recyclerCircleRubro)
    RecyclerView recyclerView;
    @BindView(R.id.rc_asociados)
    RecyclerView rcAsociados;
    @BindView(R.id.morePoints)
    ImageView morePoints;

    private Animation slideDown;
    private Animation slideUp;
    private int tipoList;
    private int number;

    public RubroProcesoFormulaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        constraintLayoutRubros.setOnClickListener(this);
        constraintLayoutCabecera.setOnClickListener(this);
        constraintLayoutRubros.setOnLongClickListener(this);
        imageTipoAncla.setOnClickListener(this);
    }


    public void bind(RubroProcesoUi rubroProcesoUiFormula, RubroEvaluacionProcesoListener listener, CapacidadUi capacidadUi, int tipoList, int number) {
        validarListener(listener);
        this.rubroProcesoUi = rubroProcesoUiFormula;
        this.capacidadUi = capacidadUi;
        this.tipoList = tipoList;
        this.number = number;
        initView(rubroProcesoUiFormula);
        initViewTipoOrigen(rubroProcesoUiFormula);
        initViewFormulaEvaluacion(rubroProcesoUiFormula);
        initViewTipoEvaluacion(rubroProcesoUiFormula);
        initTipoAnclar(rubroProcesoUiFormula);
        initFormulaAdapter();
        initViewEstilo(rubroProcesoUiFormula);
        setAnimacion();
        textViewTipoCursoSilabo.setText("Área");
        validarTipoRubrica();
        //initIndicador();
    }

    private void validarTipoRubrica(){
        if (capacidadUi.isCalendarioEditar()){
            morePoints.setVisibility(View.VISIBLE);
        } else {
            morePoints.setVisibility(View.INVISIBLE);
        }
    }

    private void initIndicador() {
            List<RubrosAsociadosUi> rubrosAsociadosUiList = rubroProcesoUi.getRubrosAsociadosUiList();
            if(rubrosAsociadosUiList==null)return;
            NombreRubrosAsociadosAdapter nombreRubrosAsociadosAdapter = new NombreRubrosAsociadosAdapter(rubrosAsociadosUiList);
            rcAsociados.setLayoutManager(new LinearLayoutManager(rcAsociados.getContext()));
            rcAsociados.setAdapter(nombreRubrosAsociadosAdapter);

    }

    private void initTipoAnclar(RubroProcesoUi rubroProcesoUi) {
        Log.d(getClass().getSimpleName(), rubroProcesoUi.getTipoFormula().toString());
        switch (rubroProcesoUi.getTipoFormula()) {
            case ANCLADA:
                imageTipoAncla.setVisibility(View.VISIBLE);
                if (slideUp != null)imageTipoAncla.startAnimation(slideUp);
                imageTipoAncla.setImageDrawable(ContextCompat.getDrawable(imageTipoAncla.getContext(), R.drawable.ic_big_anchor2));
                break;
            case EVALUADA:
                imageTipoAncla.setVisibility(View.VISIBLE);
                if (slideUp != null)imageTipoAncla.startAnimation(slideUp);
                imageTipoAncla.setImageDrawable(ContextCompat.getDrawable(imageTipoAncla.getContext(), R.drawable.ic_big_anchor_red));
                break;
            default:
                imageTipoAncla.setVisibility(View.GONE);
                if (slideUp == null) return;
                imageTipoAncla.startAnimation(slideDown);
                break;
        }
    }

    private void initFormulaAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
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
                constraintLayoutCabecera.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_grey_200 ));
                break;
            case NEGRO:
                constraintLayoutCabecera.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.black ));
                txtTitulo.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.white ));
                break;
            case ANARANJADO:
                constraintLayoutCabecera.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_blue_50));
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
        if (rubroProcesoUiFormula.isCheck()) {
            imageCheked.setVisibility(View.VISIBLE);
            /*if (slideUp == null) return;
            imageCheked.startAnimation(slideUp);*/
        } else {
            imageCheked.setVisibility(View.GONE);
            /*if (slideDown == null) return;
            imageCheked.startAnimation(slideDown);*/
        }
    }

    private void initView(RubroProcesoUi rubroProcesoUiFormula) {
        //String titulo = "<b>" + rubroProcesoUi.getPosicion() + ":</b> " + rubroProcesoUi.getTitulo();
        String titulo = "" + number + ": " + rubroProcesoUi.getTitulo();
        txtTitulo.setText(Html.fromHtml(titulo));
        txtFecha.setText(rubroProcesoUiFormula.getFecha());
        String mediaDesviacionEstandar = (rubroProcesoUiFormula.getMedia() + "(" + rubroProcesoUiFormula.getDesviacionE() + ")");
        txtMedia.setText(mediaDesviacionEstandar);
        if(rubroProcesoUiFormula.isExportado()){
            txtTitulo.setTextColor(Color.BLACK);
        }else {
            txtTitulo.setTextColor(Color.RED);
        }
    }

    private void validarListener(RubroEvaluacionProcesoListener listener) {
        if (listener != null) this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contItemView:
                listener.onClickRubroEvaluacion(capacidadUi,rubroProcesoUi);
                break;
            case R.id.idheadConstraint:
                if(capacidadUi.isCalendarioEditar())listener.onOpciones(capacidadUi, rubroProcesoUi, view);
                break;
            case R.id.imageTipoAncla:
                //listener.onClickAncla(capacidadUi, rubroProcesoUi);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.contItemView:
                onLongClickChecked(rubroProcesoUi, listener);
                break;
            default:
                break;
        }
        return true;
    }

    private void onLongClickChecked(RubroProcesoUi rubroProcesoUi, RubroEvaluacionProcesoListener listener) {
        switch (rubroProcesoUi.getOrigen()) {
            case SILABO:
                showCheckedSilabo();
                imageCheked.postDelayed(this,200);
                break;
            case SESION:
                Log.d(RUBRO_PROCESO_FORMULA_HOLDER, "long Invalido " + rubroProcesoUi.getCapacidadId());
                break;
        }
    }

    private void setAnimacion() {
        this.slideUp = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.slide_up);
        this.slideDown = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.slide_down);
    }



    private void showCheckedSilabo(){
        imageCheked.setVisibility(View.VISIBLE);
        if (slideUp == null) return;
        imageCheked.startAnimation(slideUp);
    }

    private void hideCheckedSilabo(){
        imageCheked.setVisibility(View.GONE);
    }

    private void onLongClickCheckedSilabo(RubroProcesoUi rubroProcesoUi) {
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
    public void run() {
        listener.onClickRubrosAsociados(rubroProcesoUi,capacidadUi);
        hideCheckedSilabo();
    }

}
