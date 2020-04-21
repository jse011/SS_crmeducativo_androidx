package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RecyclerItemClickListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroEvaluacionProcesoListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.CamposAccionAdapter;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 17/05/2018.
 */

public class RubroProcesoRubicaHolder extends ViewHolder implements View.OnClickListener,RecyclerItemClickListener.RecyclerViewOnItemClickListener {

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
    @BindView(R.id.imageRubrica)
    ImageView imageRubrica;
    @BindView(R.id.card_rubro_proceso)
    CardView cardRubroProceso;
    @BindView(R.id.txt_titulo_rubrica)
    TextView txtTituloRubrica;
    @BindView(R.id.txt_indicador)
    TextView txtIndicador;
    @BindView(R.id.vacio)
    TextView txtVacio;
    @BindView(R.id.rc_campotematico)
    RecyclerView rcCampotematico;
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @BindView(R.id.imageView8)
    ImageView imageView8;
    private Animation slideDown;
    private Animation slideUp;

    public RubroProcesoRubicaHolder(View itemView) {
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
        initIndicador();
    }

    private void initIndicador() {
        IndicadoresCamposAccionUi indicadoresCamposAccionUi = rubroProcesoUi.getIndicadoresCamposAccionUi();
        List<CamposAccionUi> camposAccionUiList = null;
        if(indicadoresCamposAccionUi != null){
            imageView7.setVisibility(View.VISIBLE);
            imageView8.setVisibility(View.VISIBLE);
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
                            Glide.with(itemView.getContext()).load(R.drawable.ic_speedometer)
                                    .apply(Utils.getGlideRequestOptionsSimple()).into(imageView7);
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
            imageView8.setVisibility(View.GONE);
        }
        if(camposAccionUiList==null)return;
        if(camposAccionUiList.size()>0)txtVacio.setVisibility(View.GONE);
        else txtVacio.setVisibility(View.VISIBLE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rcCampotematico.getContext());
            rcCampotematico.setLayoutManager(linearLayoutManager);
        CamposAccionAdapter camposAccionAdapter = new CamposAccionAdapter(camposAccionUiList);
        rcCampotematico.setAdapter(camposAccionAdapter);
        camposAccionAdapter.setList(camposAccionUiList);
        rcCampotematico.addOnItemTouchListener(new RecyclerItemClickListener(rcCampotematico,this));
        rcCampotematico.setNestedScrollingEnabled(false);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_rubro_proceso:
                onClickChecked(rubroProcesoUi, listener);
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
            case TAREA:
                textViewTipoCursoSilabo.setText("Tarea");
                validacionCheckSilabo(rubroProcesoUiFormula);
                break;
            case SESION:
                textViewTipoCursoSilabo.setText("Sesión");
                validacionCheckSilabo(rubroProcesoUiFormula);
                break;
            case SILABO:
                textViewTipoCursoSilabo.setText("Área");
                validacionCheckSilabo(rubroProcesoUiFormula);
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
        txtMedia.setText(rubroProcesoUiFormula.getMedia() + "(" + rubroProcesoUiFormula.getDesviacionE() + ")");
        String tituloRubrica = "<b>Rúbrica:</b> "+rubroProcesoUiFormula.getTituloRubrica();
        txtTituloRubrica.setText(Html.fromHtml(tituloRubrica));
    }

    private void validarListener(RubroEvaluacionProcesoListener listener) {
        if (listener != null) this.listener = listener;
    }

    private void onClickChecked(RubroProcesoUi rubroProcesoUi, RubroEvaluacionProcesoListener listener) {
        switch (rubroProcesoUi.getOrigen()) {
            case SILABO:
                setAnimacion();
                onLongClickCheckedSilabo(rubroProcesoUi);
                listener.onClickRubrosAsociados(rubroProcesoUi, capacidadUi);
                break;
            case SESION:
                setAnimacion();
                onLongClickCheckedSilabo(rubroProcesoUi);
                listener.onClickRubrosAsociados(rubroProcesoUi, capacidadUi);
                break;
            case TAREA:
                setAnimacion();
                onLongClickCheckedSilabo(rubroProcesoUi);
                listener.onClickRubrosAsociados(rubroProcesoUi, capacidadUi);
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
    public void onItemClick(ViewHolder viewHolder, View clickedView) {
        onClickChecked(rubroProcesoUi, listener);
    }

    @Override
    public void onItemLongClick(ViewHolder viewHolder, View clickedView) {

    }
}
