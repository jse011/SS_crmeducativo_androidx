package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.adapter.holder;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.adapter.RubEvalProcAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.listener.RubricaListener;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 21/02/2018.
 */

public class RubBidHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.titulo)
    TextView textTitle;
    @BindView(R.id.media)
    TextView txtMedia;
    @BindView(R.id.forma)
    TextView textViewTipoBlue;
    @BindView(R.id.tipo)
    TextView textViewForSumRed;
    @BindView(R.id.origen)
    TextView textViewEvaluacionAmarillo;
    @BindView(R.id.contItemView)
    ConstraintLayout root;
    @BindView(R.id.fecha)
    TextView textViewFecha;
    @BindView(R.id.reciclador_rubros)
    RecyclerView reciclador;
    @BindView(R.id.idheadConstraint)
    ConstraintLayout constraintLayoutOpcion;
    @BindView(R.id.img_msje)
    ImageView imgMsje;
    @BindView(R.id.img_publicar)
    ImageView imgPublicar;
    @BindView(R.id.morePoints)
    ImageView morePoints;
    @BindView(R.id.card_rubro_proceso)
    CardView CardRubroProceso;


    RubricaListener listener;
    RubBidUi rubBidUi;



    public RubBidHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        constraintLayoutOpcion.setOnClickListener(this);
    }

    public void bind(final RubBidUi rubBidUi, final RubricaListener listener, final int position) {
        this.rubBidUi = rubBidUi;
        this.listener = listener;
        //String titulo = "<b>" + rubBidUi.getPosicion() + ":</b> " + rubBidUi.getTitle();
        String titulo = "" + position + ": " + rubBidUi.getTitle();
        textTitle.setText(Html.fromHtml(titulo));
        textViewFecha.setText(rubBidUi.getFecha());
        String mediaDesviacionEstandar = (rubBidUi.getMedia() + "(" + rubBidUi.getDesviacionE() + ")");
        txtMedia.setText(mediaDesviacionEstandar);
        TipoUi formaEvaluacion = rubBidUi.getFormaEvaluacion();
        if (formaEvaluacion != null) {
            String title = formaEvaluacion.getTitle();
            if (!TextUtils.isEmpty(title)) {
                textViewTipoBlue.setText(String.valueOf(rubBidUi.getFormaEvaluacion().getTitle()));
                textViewForSumRed.setText(String.valueOf(rubBidUi.getTipoEvaluacion().getTitle()));
                switch (rubBidUi.getOrigenUi()){
                    case AREA:
                        textViewEvaluacionAmarillo.setText(String.valueOf("Área"));
                        break;
                    case TAREA:
                        textViewEvaluacionAmarillo.setText(String.valueOf("Tarea"));
                        break;
                    case SESION:
                        textViewEvaluacionAmarillo.setText(String.valueOf("Sesión"));
                        break;
                }

            }
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemSelected(rubBidUi);
                }
            }
        });
        RubEvalProcAdapter adapter = new RubEvalProcAdapter(rubBidUi.getRubroEvalProcesoList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        reciclador.setLayoutManager(linearLayoutManager);
        reciclador.setHasFixedSize(false);
        reciclador.setNestedScrollingEnabled(false);
        reciclador.setAdapter(adapter);
        initMensaje();


        switch (rubBidUi.getPublicarEval()){
            case TODOS:
                imgPublicar.setImageDrawable(ContextCompat.getDrawable(root.getContext(), R.drawable.ic_tarea_publicada_message));
                imgPublicar.setVisibility(View.VISIBLE);
                break;
            case NINGUNO:
                imgPublicar.setImageDrawable(null);
                imgPublicar.setVisibility(View.GONE);
                break;
            case PARCIAL:
                imgPublicar.setVisibility(View.VISIBLE);
                imgPublicar.setImageDrawable(ContextCompat.getDrawable(root.getContext(), R.drawable.ic_tarea_publicada_message_2));
                break;
        }

        if(rubBidUi.isEditar()){
            morePoints.setVisibility(View.VISIBLE);
        }else {
            morePoints.setVisibility(View.GONE);
        }

        if(rubBidUi.isExportado()){
            textTitle.setTextColor(Color.BLACK);
            //CardRubroProceso.setCardBackgroundColor(Color.WHITE);
            //CardRubroProceso.setCardElevation(Utils.convertDpToPixel(4, itemView.getContext()));
        }else {
            textTitle.setTextColor(Color.RED);
            //CardRubroProceso.setCardBackgroundColor(Color.parseColor("#54FFFFFF"));
            //CardRubroProceso.setCardElevation(0);
        }
    }

    private void initMensaje() {
        if(rubBidUi.getEstadoMsje() == 1){
            imgMsje.setVisibility(View.VISIBLE);
        }else {
            imgMsje.setVisibility(View.GONE);
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.idheadConstraint:
                if(rubBidUi.isEditar())
                    if(listener!=null)listener.onOpciones(rubBidUi,view);
                break;
            default:
                break;
        }
    }
}
