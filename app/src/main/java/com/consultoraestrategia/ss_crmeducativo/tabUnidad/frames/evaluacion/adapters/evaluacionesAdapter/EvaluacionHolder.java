package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.adapters.evaluacionesAdapter;

import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.IndicadorUi;
import com.github.vipulasri.timelineview.TimelineView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EvaluacionHolder extends RecyclerView.ViewHolder {
//    @BindView(R.id.count)
//    TextView count;
    @BindView(R.id.indicador)
    TextView indicador;
    @BindView(R.id.timeline)
    TimelineView mTimelineView;
    @BindView(R.id.instrumento)
    TextView instrumento;
    @BindView(R.id.tecnica)
    TextView tecnica;
    private int viewType;

    public EvaluacionHolder(@NonNull View itemView, int viewType) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mTimelineView.initLine(viewType);
        this.viewType = viewType;
    }
    public void bind(IndicadorUi indicadorUi){
        //count.setText(String.valueOf(indicadorUi.getCount()));
        indicador.setText(indicadorUi.getNombre());
        instrumento.setText(indicadorUi.getInstrumento());
        tecnica.setText(indicadorUi.getTecnica());
        mTimelineView.setMarker(ContextCompat.getDrawable(itemView.getContext(),R.drawable.marker_accent), Color.GRAY);
        mTimelineView.setEndLineColor(R.color.colorPrimary, viewType);
        mTimelineView.setStartLineColor(R.color.colorPrimary, viewType);
    }
}
