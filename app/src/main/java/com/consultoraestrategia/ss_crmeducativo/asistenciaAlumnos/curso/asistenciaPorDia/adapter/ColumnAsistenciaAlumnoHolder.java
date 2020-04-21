package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.adapter;

import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.ValorTipoNotaUi;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColumnAsistenciaAlumnoHolder  extends AbstractViewHolder {

    @BindView(R.id.alias)
    TextView alias;

    public ColumnAsistenciaAlumnoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public  void bind(ValorTipoNotaUi valorTipoNotaUi){
       alias.setText(valorTipoNotaUi.getAlias());
       switch (valorTipoNotaUi.getTipo()){
           case TARDE:
               alias.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_orange_A700));
               break;
           case AUSENTE:
               alias.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_red_A400));
               break;
           case PUNTUAL:
               alias.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_green_A700));
               break;
           case TARDE_JTD:
               alias.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_pink_A200));
               break;
           case AUSENTE_JTD:
               alias.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_amber_A700));
               break;
       }

    }
}
