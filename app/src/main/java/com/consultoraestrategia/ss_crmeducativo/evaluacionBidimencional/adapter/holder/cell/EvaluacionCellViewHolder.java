package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell;

import android.graphics.Color;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ValorTipoNotaUi;

/**
 * Created by SCIEV on 8/03/2018.
 */

public abstract class EvaluacionCellViewHolder extends CellViewHolder<ValorTipoNotaUi> {

    abstract TextView getTextFondo();
    abstract TextView getTexEvalaucion();
    
    TipoNotaUi tipoNotaUi;
    ValorTipoNotaUi valorTipoNotaUi;
    public EvaluacionCellViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(ValorTipoNotaUi cell) {
        valorTipoNotaUi = cell;
        tipoNotaUi = cell.getTipoNotaUi();
        if(cell.isSelected()){
            colorPaint(cell);
        }else {
            colorPaintReset();
        }

        if(tipoNotaUi == null)return;
        if(cell.isSelected()){
            tipoNotaUi.setSelectCell(this);
        }
    }

    public void SelectionColors(){
        colorPaint(valorTipoNotaUi);
        if(tipoNotaUi == null)return;
        tipoNotaUi.setSelectCell(this);
    }

    public void deSelectionColors(){
        colorPaintReset();
        if(tipoNotaUi == null)return;
        tipoNotaUi.setSelectCell(null);
    }

    public void colorPaint(ValorTipoNotaUi valorTipoNotaUi){
        getTexEvalaucion().setVisibility(View.VISIBLE);
        switch (valorTipoNotaUi.getEstilo()){
            case AZUL:
                getTextFondo().setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_blue_300));
                getTexEvalaucion().setTextColor(Color.WHITE);
                break;
            case ROJO:
                getTextFondo().setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_pink_50));
                getTexEvalaucion().setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.md_red_300));
                break;
            case ANARANJADO:
                getTextFondo().setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_orange_300));
                getTexEvalaucion().setTextColor(Color.WHITE);
                break;
            case GREY:
                getTextFondo().setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_grey_300));
                break;
            case VERDE:
                getTextFondo().setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_green_300));
                getTexEvalaucion().setTextColor(Color.WHITE);
                break;
        }
    }

    private void colorPaintReset(){
        getTextFondo().setBackgroundColor(Color.WHITE);
        getTexEvalaucion().setVisibility(View.GONE);
    }

    public ValorTipoNotaUi getValorTipoNotaUi() {
        return valorTipoNotaUi;
    }

    public TipoNotaUi getTipoNotaUi() {
        return tipoNotaUi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EvaluacionCellViewHolder that = (EvaluacionCellViewHolder) o;

        return valorTipoNotaUi != null ? valorTipoNotaUi.equals(that.valorTipoNotaUi) : that.valorTipoNotaUi == null;
    }

    @Override
    public int hashCode() {
        return valorTipoNotaUi != null ? valorTipoNotaUi.hashCode() : 0;
    }

    public void reCalcular(){
        bind(valorTipoNotaUi);
    }
}
