package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroEvaluacionProcesoListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CCIE on 21/12/2017.
 */

public class RubroProcesoHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, Runnable {


    private String TAG = RubroProcesoHolder.class.getSimpleName();
    @BindView(R.id.view)
    View view;
    @BindView(R.id.lin_titulo_sesion)
    View linTituloSesion;
    @BindView(R.id.txtTitulo)
    TextView txtTitulo;
    @BindView(R.id.txtFecha)
    TextView txtFecha;
    @BindView(R.id.media)
    TextView txtMedia;
    @BindView(R.id.ImageCheck)
    ImageView imageCheked;
    @BindView(R.id.contItemView)
    ConstraintLayout constraintLayoutRubros;
    @BindView(R.id.recyclerCircleRubro)
    RecyclerView recyclerView;
    @BindView(R.id.card_rubro_proceso)
    CardView cardRubroProceso;
    @BindView(R.id.idheadConstraint)
    ConstraintLayout constraintLayoutCabecera;

    @BindView(R.id.tipo)
    TextView textViewTipoEvaluacion;
    @BindView(R.id.forma)
    TextView textViewTipoFormaEvaluacion;
    @BindView(R.id.origen)
    TextView textViewTipoCursoSilabo;
    @BindView(R.id.imageRubrica)
    ImageView imageRubrica;
    @BindView(R.id.btnCampostematicos)
    ViewGroup btnCampostematicos;
    private RubroEvaluacionProcesoListener listener;
    private RubroProcesoUi rubroProcesoUi;
    private CapacidadUi capacidadUi;

    public RubroProcesoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final RubroProcesoUi rubroProcesoUi, final RubroEvaluacionProcesoListener listener, CapacidadUi capacidadUi) {
        this.listener = listener;
        this.rubroProcesoUi = rubroProcesoUi;
        this.capacidadUi = capacidadUi;
        String titulo = "" + rubroProcesoUi.getPosicion() + ": " + rubroProcesoUi.getTitulo();
        txtTitulo.setText(Html.fromHtml(titulo));
        txtFecha.setText(rubroProcesoUi.getFecha());
        txtMedia.setText((rubroProcesoUi.getMedia() + "(" + rubroProcesoUi.getDesviacionE() + ")"));
        constraintLayoutRubros.setOnClickListener(this);
        constraintLayoutCabecera.setOnClickListener(this);
        constraintLayoutRubros.setOnLongClickListener(this);
        btnCampostematicos.setOnClickListener(this);


        switch (rubroProcesoUi.getOrigen()) {
            case SESION:
              /*  recyclerView.setVisibility(View.GONE);
                constraintLayoutRubros.setVisibility(View.VISIBLE);
                constraintLayoutRubros.setBackgroundColor(Color.TRANSPARENT);*/
                textViewTipoCursoSilabo.setText("Sesión");
                view.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                imageCheked.setVisibility(View.GONE);
                break;
            case SILABO:
                constraintLayoutRubros.setVisibility(View.VISIBLE);
                /*constraintLayoutRubros.setBackgroundColor(rubroProcesoUi.getColorRubro());*/
                textViewTipoCursoSilabo.setText("Área");
                recyclerView.setVisibility(View.VISIBLE);
                if (rubroProcesoUi.isCheck()) {
                    imageCheked.setVisibility(View.VISIBLE);
                    /*if (slideUp == null) return;
                    imageCheked.startAnimation(slideUp);*/
                } else {
                    imageCheked.setVisibility(View.GONE);
                    /*if (slideDown == null) return;
                    imageCheked.startAnimation(slideDown);*/
                }
                break;
            case TAREA:
                textViewTipoCursoSilabo.setText("Tarea");
                view.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                imageCheked.setVisibility(View.GONE);
                break;
        }

       /* LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);*/

        switch (rubroProcesoUi.getFormEvaluacion()) {
            case GRUPAL:
                //  cardRubroProceso.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.card_rubro_proceso_grupal));
                textViewTipoFormaEvaluacion.setText("Grupal");
                break;
            case INDIVIDUAL:
                textViewTipoFormaEvaluacion.setText("Individual");
                // cardRubroProceso.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.card_rubro_proceso_individual));
                break;
            case FORMULA:
                // view.setVisibility(View.GONE);
                textViewTipoFormaEvaluacion.setText("Individual");
                break;
        }


        textViewTipoEvaluacion.setText(rubroProcesoUi.getTipoEvaluacion());

        Log.d(TAG,"rubroProcesoUi.getTipo()"+rubroProcesoUi.getTipo()+"");
        switch (rubroProcesoUi.getTipo()) {
            case UNIDIMENCIONAL:
                imageRubrica.setVisibility(View.GONE);
                break;
            case BIDIMENCIONAL_DETALLE:
                imageRubrica.setVisibility(View.VISIBLE);
                break;
        }

        /*RubrosAsociadosAdapter adapter;
        if (rubroProcesoUi.getRubrosAsociadosUiList() != null) {
            Log.d(TAG, "lleno : " + rubroProcesoUi.getRubrosAsociadosUiList().size());
            adapter = new RubrosAsociadosAdapter(rubroProcesoUi.getRubrosAsociadosUiList());
        } else {
            adapter = new RubrosAsociadosAdapter(new ArrayList<RubrosAsociadosUi>());
        }
        recyclerView.setAdapter(adapter);*/


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contItemView:
                /*Log.d(TAG, "isCheck " + rubroProcesoUi.isCheck());
                if (!rubroProcesoUi.isCheck()) {
                    listener.onClickRubroEvaluacion(rubroProcesoUi);
                } else {
                    Log.d(TAG, "isCheck " + rubroProcesoUi.isCheck());
                }*/
                listener.onClickRubroEvaluacion(capacidadUi, rubroProcesoUi);
                break;
            case R.id.idheadConstraint:
                //listener.onOpciones(capacidadUi, rubroProcesoUi, itemView, anclar, editar);
                break;
            case R.id.btnCampostematicos:
                listener.onClickCamposTematicos(rubroProcesoUi);
                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.contItemView:
                Log.d(TAG, "long clcik isCheck " + rubroProcesoUi.getCapacidadId());
                onLongClickChecked();
                break;
            default:
                break;
        }
        return true;
    }

    private void onLongClickChecked() {
        switch (rubroProcesoUi.getOrigen()) {
            case SILABO:
                showCheckedSilabo();
                imageCheked.postDelayed(this,200);
                break;
            case SESION:
                Log.d(TAG, "longSESION " + rubroProcesoUi.getCapacidadId());
                break;
        }
    }

    private void showCheckedSilabo(){
        imageCheked.setVisibility(View.VISIBLE);
    }

    private void hideCheckedSilabo(){
        imageCheked.setVisibility(View.GONE);
    }


    @Override
    public void run() {
        listener.onClickRubrosAsociados(rubroProcesoUi, capacidadUi);
        hideCheckedSilabo();
    }
}
