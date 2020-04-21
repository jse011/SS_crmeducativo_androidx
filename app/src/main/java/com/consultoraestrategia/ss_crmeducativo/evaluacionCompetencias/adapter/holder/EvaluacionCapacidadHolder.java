package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.holder;

import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.FormulaAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.adapter.listener.EvaluacionCompetenciaListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CompetenciaUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CCIE on 09/03/2018.
 */

public class EvaluacionCapacidadHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = EvaluacionCapacidadHolder.class.getSimpleName();
    @BindView(R.id.txtTitulo)
    TextView textViewTitulo;
    @BindView(R.id.txtFecha)
    TextView textViewFecha;
    @BindView(R.id.media)
    TextView textViewMedia;
    @BindView(R.id.card_rubro_proceso)
    CardView cardViewItem;
    EvaluacionCompetenciaListener listener;
    @BindView(R.id.reciclador_formula)
    RecyclerView recyclerView;
    @BindView(R.id.imageTipoAncla)
    ImageView imageViewAncla;
    CapacidadUi capacidadUi;
    CompetenciaUi competenciaUi;
    FormulaAdapter formulaAdapter;
//    @BindView(R.id.textView106)
//    TextView number;

    public EvaluacionCapacidadHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        cardViewItem.setOnClickListener(this);
        imageViewAncla.setOnClickListener(this);
    }

    public void bind(final CapacidadUi capacidadUi, final EvaluacionCompetenciaListener listener, CompetenciaUi competenciaUi, final int position, final Context context) {
        if (listener != null) this.listener = listener;
        this.capacidadUi = capacidadUi;
        this.competenciaUi = competenciaUi;
        initEstadoAncla(capacidadUi);
//        number.setBackgroundColor(Color.parseColor(competenciaUi.getParametroDisenioUi().getColor1()));
//        number.setText(String.valueOf(capacidadUi.getContador()));
        textViewTitulo.setText(String.valueOf(capacidadUi.getContador() + ": " + capacidadUi.getTitulo()));
        textViewFecha.setText(capacidadUi.getFecha());
        textViewMedia.setText(capacidadUi.getMedia());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        formulaAdapter = new FormulaAdapter(capacidadUi.getCapacidadUiList(), competenciaUi.getParametroDisenioUi().getColor1(), context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(formulaAdapter);

    }
    private void initEstadoAncla(CapacidadUi capacidadUi) {
        Log.i(TAG,"initEstadoAncla : " +capacidadUi.getEstadoCapacidad());
        switch (capacidadUi.getEstadoCapacidad()) {
            case EVALUADA:
                imageViewAncla.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_big_anchor_red));
                imageViewAncla.setVisibility(View.VISIBLE);
                break;
            case ANCLADA:
                imageViewAncla.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_big_anchor2));
                imageViewAncla.setVisibility(View.VISIBLE);
                break;
            default:
                imageViewAncla.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClick(View view) {

            switch (view.getId()) {
                case R.id.card_rubro_proceso:
                    Log.d(TAG, "click");
                    itemClickCapacidad(capacidadUi, competenciaUi);
                    break;
                case R.id.imageTipoAncla:
                    opcionEvaluarDialog(capacidadUi,competenciaUi);
                    break;
                default:
                    break;
            }

    }

    private void opcionEvaluarDialog(CapacidadUi capacidadUi, CompetenciaUi competenciaUi) {

        capacidadUi.setCompetenciaUi(competenciaUi);
            listener.onAceptarDialogEvaluar(capacidadUi);
    }

    private void itemClickCapacidad(CapacidadUi capacidadUi, CompetenciaUi competenciaUi) {
        capacidadUi.setCompetenciaUi(competenciaUi);
        listener.onObjectClicked(capacidadUi);
    }
}
