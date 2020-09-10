package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.graphics.Color;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroEvaluacionProcesoListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.CamposAccionAdapter;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 17/05/2018.
 */

public class RubroProcesoRubicaHolder extends ViewHolder implements View.OnClickListener, View.OnLongClickListener,Runnable {

    private RubroProcesoUi rubroProcesoUi;
    private CapacidadUi capacidadUi;
    private RubroEvaluacionProcesoListener listener;
    public final static int INDICADOR = 1, CAMPOTEMATICO=2;
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
    @BindView(R.id.imageRubrica)
    ImageView imageRubrica;
    @BindView(R.id.btnCampostematicos)
    ViewGroup btnCamposTematicos;
    @BindView(R.id.txt_titulo_rubrica)
    TextView txtTituloRubrica;
    @BindView(R.id.vacio)
    TextView txtVacio;
    @BindView(R.id.rc_campotematico)
    RecyclerView rcCampotematico;
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @BindView(R.id.imageView8)
    ImageView imageView8;
    @BindView(R.id.morePoints)
    ImageView morePoints;
    @BindView(R.id.txt_indicador)
    TextView txtIndicador;
    private int tipoList;
    private int number;

    public RubroProcesoRubicaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        constraintLayoutRubros.setOnClickListener(this);
        btnCamposTematicos.setOnClickListener(this);
        constraintLayoutRubros.setOnLongClickListener(this);
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
        initViewTipo(rubroProcesoUiFormula);
        initIndicador();
        validarTipoRubrica();
    }

    private void initIndicador() {
        IndicadoresCamposAccionUi indicadoresCamposAccionUi = rubroProcesoUi.getIndicadoresCamposAccionUi();
        List<CamposAccionUi> camposAccionUiList = null;
        if(indicadoresCamposAccionUi != null){
            imageView7.setVisibility(View.VISIBLE);
            txtIndicador.setText(indicadoresCamposAccionUi.getTitulo());
            camposAccionUiList = indicadoresCamposAccionUi.getCampoAccionList();
            switch (indicadoresCamposAccionUi.getTipoCompetencia()){
                case BASE:
                    switch (indicadoresCamposAccionUi.getTipoIndicador()){
                        case SER:
                            Glide.with(itemView.getContext()).load(indicadoresCamposAccionUi.getUrl())
                                    .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_speedometer)).into(imageView7);
                            break;
                        case HACER:
                            Glide.with(itemView.getContext()).load(indicadoresCamposAccionUi.getUrl())
                                    .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_speedometer)).into(imageView7);
                            break;
                        case SABER:
                            Glide.with(itemView.getContext()).load(indicadoresCamposAccionUi.getUrl())
                                    .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_speedometer)).into(imageView7);
                            break;
                        case DEFAULT:
                            imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(),R.drawable.ic_speedometer));
                            break;
                    }
                    break;
                case ENFOQUE:
                    imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(),R.drawable.ic_enfoque_1));
                    break;
                case TRANSVERSAL:
                    imageView7.setImageDrawable(ContextCompat.getDrawable(imageView7.getContext(),R.drawable.ic_transversal));
                    break;
            }

        }else {
            imageView7.setVisibility(View.GONE);
            //imageView8.setVisibility(View.GONE);
        }
        if(camposAccionUiList==null)return;
        if (tipoList==CAMPOTEMATICO){
            if (camposAccionUiList.size()>0)txtVacio.setVisibility(View.GONE);
            else txtVacio.setVisibility(View.VISIBLE);
            CamposAccionAdapter camposAccionAdapter = new CamposAccionAdapter(camposAccionUiList);
            rcCampotematico.setLayoutManager(new LinearLayoutManager(rcCampotematico.getContext()));
            rcCampotematico.setAdapter(camposAccionAdapter);
        }
    }

    private void initViewTipo(RubroProcesoUi rubroProcesoUiFormula) {
        switch (rubroProcesoUiFormula.getTipo()) {
            case UNIDIMENCIONAL:
                imageRubrica.setVisibility(View.GONE);
                break;
            case BIDIMENCIONAL_DETALLE:
                imageRubrica.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contItemView:
                listener.onClickRubroEvaluacion(capacidadUi, rubroProcesoUi);
                break;
            case R.id.idheadConstraint:
                listener.onOpciones(capacidadUi, rubroProcesoUi, view);
                break;
            case R.id.btnCampostematicos:
                listener.onClickCamposTematicos(rubroProcesoUi);
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
            case TAREA:
                textViewTipoCursoSilabo.setText("Tarea");
                validacionCheckSilabo(rubroProcesoUiFormula);
                break;
            case SESION:
                textViewTipoCursoSilabo.setText("Sesión");
                break;
            case SILABO:
                textViewTipoCursoSilabo.setText("Área");
                validacionCheckSilabo(rubroProcesoUiFormula);
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
        txtMedia.setText(rubroProcesoUiFormula.getMedia() + "(" + rubroProcesoUiFormula.getDesviacionE() + ")");
        String tituloRubrica = "<b>Rúbrica:</b> "+rubroProcesoUiFormula.getTituloRubrica();
        txtTituloRubrica.setText(Html.fromHtml(tituloRubrica));
        if(rubroProcesoUiFormula.isExportado()){
            txtTitulo.setTextColor(Color.BLACK);
        }else {
            txtTitulo.setTextColor(Color.RED);
        }
    }

    private void validarListener(RubroEvaluacionProcesoListener listener) {
        if (listener != null) this.listener = listener;
    }

    private void onLongClickChecked(RubroProcesoUi rubroProcesoUi, RubroEvaluacionProcesoListener listener) {
        switch (rubroProcesoUi.getOrigen()) {
            case SILABO:
                showCheckedSilabo();
                imageCheked.postDelayed(this,200);
                break;
            case SESION:
                //Log.d(RUBRO_PROCESO_FORMULA_HOLDER, "long Invalido " + rubroProcesoUi.getCapacidadId());
                break;
        }
    }


    private void showCheckedSilabo(){
        imageCheked.setVisibility(View.VISIBLE);
    }

    private void hideCheckedSilabo(){
        imageCheked.setVisibility(View.GONE);
    }

    private void onLongClickCheckedSilabo(RubroProcesoUi rubroProcesoUi) {
        if (!rubroProcesoUi.isCheck()) {
            imageCheked.setVisibility(View.VISIBLE);
        } else {
            imageCheked.setVisibility(View.GONE);
        }
    }


    @Override
    public void run() {
        listener.onClickRubrosAsociados(rubroProcesoUi, capacidadUi);
        hideCheckedSilabo();
    }
    private void validarTipoRubrica(){
        if (rubroProcesoUi.getTipo() == RubroProcesoUi.Tipo.BIDIMENCIONAL_DETALLE){
            morePoints.setVisibility(View.INVISIBLE);
        } else {
            morePoints.setVisibility(View.VISIBLE);
            constraintLayoutCabecera.setOnClickListener(this);
        }
    }
}
