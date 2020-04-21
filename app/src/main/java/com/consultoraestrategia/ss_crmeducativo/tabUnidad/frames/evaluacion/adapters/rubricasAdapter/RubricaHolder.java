package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.adapters.rubricasAdapter;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RubricaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.view.EvaluacionUnidadListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RubricaHolder extends RecyclerView.ViewHolder {

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
    ItemEvalRubrosAdapter adapterRubrosRelacionados;


    public RubricaHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final RubricaUi rubricaUi, final EvaluacionUnidadListener listener){
        String titulo = "<b>" + rubricaUi.getContador() + ":</b> " + rubricaUi.getTitulo();
        textTitle.setText(Html.fromHtml(titulo));
        textViewFecha.setText(rubricaUi.getFecha());
        String mediaDesviacionEstandar = (rubricaUi.getMedia() + "(" + rubricaUi.getDesviacionE() + ")");
        txtMedia.setText(mediaDesviacionEstandar);
        morePoints.setVisibility(View.GONE);
        textViewForSumRed.setText(rubricaUi.getTipoEvaluacion());
        String origen;
        switch (rubricaUi.getOrigen())
        {
            case AREA:
                origen= "Área";
                break;
            case TAREA:
                origen= "Tarea";
                break;
            default:
                origen= "Sesión";
                break;
        }
        textViewEvaluacionAmarillo.setText(origen);
        textViewTipoBlue.setText(rubricaUi.getFormaEvaluacion());

        if(rubricaUi.getEstadoMensaje() == 1){
            imgMsje.setVisibility(View.VISIBLE);
        }else {
            imgMsje.setVisibility(View.GONE);
        }


        switch (rubricaUi.getPublicado()){
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
        initAdapter();
        adapterRubrosRelacionados.setList(rubricaUi.getCantEvalRubros());
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickRubrica(rubricaUi);
            }
        });

    }

    private void initAdapter() {
        adapterRubrosRelacionados= new ItemEvalRubrosAdapter(new ArrayList<Integer>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        reciclador.setLayoutManager(linearLayoutManager);
        reciclador.setHasFixedSize(false);
        reciclador.setNestedScrollingEnabled(false);
        reciclador.setAdapter(adapterRubrosRelacionados);
    }
}
