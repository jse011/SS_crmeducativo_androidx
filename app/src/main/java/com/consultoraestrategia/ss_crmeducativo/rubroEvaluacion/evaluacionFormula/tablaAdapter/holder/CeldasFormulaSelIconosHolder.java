package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EvaluacionFormula_RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.EvaluacionFormulaAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder.abstracto.CeldasViewHolder;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 16/05/2018.
 */

public class CeldasFormulaSelIconosHolder extends CeldasViewHolder<NotaUi> {
    @BindView(R.id.textViewValorNumerico)
    TextView textViewValorNumerico;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textViewEmpty)
    TextView textViewEmpty;
    @BindView(R.id.containerResultados)
    ConstraintLayout constraintLayoutResultados;
    @BindView(R.id.root)
    ConstraintLayout root;

    public CeldasFormulaSelIconosHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(NotaUi notasUi) {
        change(notasUi.getRubro());
        double nota= notasUi.getNota();
        String notaStrint;
        if (nota == -1) {
            clearNota();
            return;
        }

        try {
            notaStrint =  String.format("%.1f", nota);
        }catch (Exception e){
            notaStrint = String.format("%.1f", 0.0);
            e.printStackTrace();
        }


        constraintLayoutResultados.setVisibility(View.VISIBLE);
        textViewEmpty.setVisibility(View.GONE);
        textViewValorNumerico.setText(notaStrint);
        if(TextUtils.isEmpty(notasUi.getIcono())){
            imageView.setVisibility(View.GONE);
        }else {
            imageView.setVisibility(View.VISIBLE);
        }

        Glide.with(itemView.getContext())
                .load(notasUi.getIcono())
                .apply(Utils.getGlideRequestOptions().override(50,50))
                .into(imageView);
    }


    private void clearNota() {
        constraintLayoutResultados.setVisibility(View.GONE);
        textViewEmpty.setText("-");
        textViewEmpty.setVisibility(View.VISIBLE);
    }

    private void change(EvaluacionFormula_RubroEvaluacionUi rubroEvaluacionUi) {
        ViewGroup.LayoutParams layoutParams = root.getLayoutParams();
        if (rubroEvaluacionUi.getTipoRubro() == EvaluacionFormulaAdapter.COLUMMNA_RUBRO_FORMULA) {
            layoutParams.width = (int) root.getResources().getDimension(R.dimen.table_header_width_eval_session);
        } else {
            layoutParams.width = (int) Utils.convertDpToPixel(90, root.getContext());
        }
    }

}
