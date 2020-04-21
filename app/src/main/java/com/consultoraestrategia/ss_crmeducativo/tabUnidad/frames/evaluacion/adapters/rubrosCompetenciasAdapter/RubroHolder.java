package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.adapters.rubrosCompetenciasAdapter;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RubricaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.view.EvaluacionUnidadListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RubroHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.titulo)
    TextView titulo;
    @BindView(R.id.lin_titulo_sesion)
    View linTituloSesion;
    @BindView(R.id.fecha)
    TextView fecha;
    @BindView(R.id.textView32)
    TextView textView32;
    @BindView(R.id.media)
    TextView media;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.forma)
    TextView forma;
    @BindView(R.id.tipo)
    TextView tipo;
    @BindView(R.id.origen)
    TextView origen;
    @BindView(R.id.containerRubros)
    ConstraintLayout containerRubros;
    @BindView(R.id.img_msje)
    ImageView imgMsje;
    @BindView(R.id.img_publicar)
    ImageView imgPublicar;
    @BindView(R.id.img_indicador)
    ImageView imgIndicador;
    @BindView(R.id.indicador)
    TextView indicador;
    @BindView(R.id.contItemView)
    ConstraintLayout contItemView;
    @BindView(R.id.card_rubro_proceso)
    CardView cardRubroProceso;


    public RubroHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bind(final RubricaUi rubroProcesoUi, final EvaluacionUnidadListener listener){
        Log.d(getClass().getSimpleName(), "rubro "+ rubroProcesoUi.getTitulo());
        try {
            String titulos = "<b>" + rubroProcesoUi.getContador() + ":</b> " + rubroProcesoUi.getTitulo();

            titulo.setText(Html.fromHtml(titulos));
            fecha.setText(rubroProcesoUi.getFecha());
            String mediaDesviacionEstandar = (rubroProcesoUi.getMedia() + "(" + rubroProcesoUi.getDesviacionE() + ")");
            media.setText(mediaDesviacionEstandar);

            tipo.setText(rubroProcesoUi.getTipoEvaluacion());
            String origenrubro;
            switch (rubroProcesoUi.getOrigen())
            {
                case AREA:
                    origenrubro= "Área";
                    break;
                case TAREA:
                    origenrubro= "Tarea";
                    break;
                default:
                    origenrubro= "Sesión";
                    break;
            }
            origen.setText(origenrubro);
            forma.setText(rubroProcesoUi.getFormaEvaluacion());

            if(rubroProcesoUi.getEstadoMensaje() == 1){
                imgMsje.setVisibility(View.VISIBLE);
            }else {
                imgMsje.setVisibility(View.GONE);
            }


            IndicadorUi indicadorUi= rubroProcesoUi.getIndicadorUi();
            indicador.setText(indicadorUi.getNombre());
            int ruta;
            switch (indicadorUi.getTipo()){
                case BASE:
                    ruta= R.drawable.indicador_hacer;
                case TRANSVERSAL:
                    ruta= R.drawable.ic_transversal;
                default:
                    ruta= R.drawable.ic_enfoque_1;
            }
            imgIndicador.setImageResource(ruta);

            switch (rubroProcesoUi.getPublicado()){
                case TODOS:
                    imgPublicar.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_tarea_publicada_message));
                    imgPublicar.setVisibility(View.VISIBLE);
                    break;
                case NINGUNO:
                    imgPublicar.setImageDrawable(null);
                    imgPublicar.setVisibility(View.GONE);
                    break;
                case PARCIAL:
                    imgPublicar.setVisibility(View.VISIBLE);
                    imgPublicar.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_tarea_publicada_message_2));
                    break;
            }

        }catch (Exception e ){

        }

        contItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickRubrica(rubroProcesoUi);
            }
        });

    }
}
