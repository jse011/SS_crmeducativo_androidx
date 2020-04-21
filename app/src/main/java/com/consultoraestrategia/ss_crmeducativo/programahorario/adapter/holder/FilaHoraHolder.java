package com.consultoraestrategia.ss_crmeducativo.programahorario.adapter.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.DiaUi;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.HoraUi;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilaHoraHolder extends AbstractViewHolder {
    @BindView(R.id.fondo)
    View fondo;
    @BindView(R.id.txt_hora)
    TextView txtHora;
    public FilaHoraHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(HoraUi p_jValue, int p_nYPosition) {
        alternarFondoHorario(p_nYPosition);
        String hora = "";
        try {

            String desde = TextUtils.isEmpty(p_jValue.getDesde()) ? "" : p_jValue.getDesde();
            String hasta = TextUtils.isEmpty(p_jValue.getHasta()) ? "" : p_jValue.getHasta();

            desde = desde.length() > 4 ? desde.substring(0, 5)  : desde;
            hasta = hasta.length() > 4 ? hasta.substring(0, 5)  : hasta;

            hora = desde + " - " + hasta;

        }catch (Exception e){
            e.printStackTrace();
        }

        txtHora.setText(hora);
    }


    private void alternarFondoHorario(int p_nYPosition) {
        if((p_nYPosition % 2)==0){
            fondo.setAlpha(0.7f);
        }else {
            fondo.setAlpha(1.0f);
        }
    }
}
