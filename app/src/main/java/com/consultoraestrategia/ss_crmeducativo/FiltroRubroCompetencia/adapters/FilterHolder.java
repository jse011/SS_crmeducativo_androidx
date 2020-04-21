package com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.adapters;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.listener.FilterChooserCheckItemListener;
import com.consultoraestrategia.ss_crmeducativo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public String TAG = FilterHolder.class.getSimpleName();
    @BindView(R.id.checkTipos)
    RadioButton checkTipos;
    @BindView(R.id.textfiltrotipo)
    TextView textfiltrotipo;
    @BindView(R.id.itemVistas)
    ConstraintLayout itemVistas;
    FilterChooserCheckItemListener listener;
    CompetenciaCheck items;

    public FilterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemVistas.setOnClickListener(this);
    }
    public void bind(CompetenciaCheck competenciaCheck, FilterChooserCheckItemListener listener){
       this.listener=listener;
       this.items=competenciaCheck;
        textfiltrotipo.setText(String.valueOf(items.getTipoCompetencia().getNombre()));
        if(competenciaCheck.getChecked()){
            checkTipos.setChecked(true);
        }else {
            checkTipos.setChecked(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.itemVistas:
                listener.filtradoCheckItemListener(items);
                break;
            default:
                break;
        }
    }

}
