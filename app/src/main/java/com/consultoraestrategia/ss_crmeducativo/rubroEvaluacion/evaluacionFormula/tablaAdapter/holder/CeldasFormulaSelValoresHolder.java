package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class CeldasFormulaSelValoresHolder extends CeldasViewHolder<NotaUi> {
    public static final String TAG = CeldasFormulaSelValoresHolder.class.getSimpleName();
    @BindView(R.id.textViewValorNumerico)
    TextView textViewValorNumerico;
    @BindView(R.id.textViewEscala)
    TextView textViewEscala;
    @BindView(R.id.textViewEmpty)
    TextView textViewEmpty;
    @BindView(R.id.containerResultados)
    ConstraintLayout constraintLayoutResultados;
    @BindView(R.id.root)
    ConstraintLayout root;

    public CeldasFormulaSelValoresHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(NotaUi notaUi) {
        change(notaUi.getRubro());
        String notaUnparsed;
        double nota = notaUi.getNota();
        if (nota == -1) {
            clearNota();
            return;
        }

        try {
            notaUnparsed =  String.format("%.1f", nota);
        }catch (Exception e){
            notaUnparsed = String.format("%.1f", 0.0);
            e.printStackTrace();
        }

        constraintLayoutResultados.setVisibility(View.VISIBLE);
        textViewEmpty.setVisibility(View.GONE);
        textViewValorNumerico.setText(notaUnparsed);
        textViewEscala.setText(notaUi.getAlias());
        Log.d(TAG,"SelectorValores "+ notaUi.getAlias());

//        String valorTipoNotaId = notaUi.getValorTipoNotaId();
//        Log.d(TAG,"valorTipoNotaId "+ valorTipoNotaId);
//        List<ValorTipoNotaUi> valorTipoNotaUiList = notaUi.getTipoNota().getValorTipoNotaList();
//        for (ValorTipoNotaUi valorTipoNoaUi:valorTipoNotaUiList){
//            textViewEscala.setText(valorTipoNoaUi.getTitle());
//            Log.d(TAG, "SelectorValoresFOR : "+ valorTipoNoaUi.getTitle());;
//        }


    }

    private void clearNota() {
        textViewEmpty.setVisibility(View.VISIBLE);
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
