package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.adapter.ViewHolder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ColorCondicionalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.listener.ColorCondicionalListener;
import com.consultoraestrategia.ss_crmeducativo.R;

import java.util.Formatter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 6/11/2017.
 */

public class ColorCondicionalViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.check_desde)
    CheckBox checkDesde;
    @BindView(R.id.check_hasta)
    CheckBox checkHasta;
    @BindView(R.id.button_delete)
    ImageView buttonDelete;
    @BindView(R.id.view_fondo)
    View viewFondo;
    @BindView(R.id.view_texto)
    View viewTexto;
    @BindView(R.id.txt_desde_hasta)
    TextView txtDesdeHasta;
    public ColorCondicionalViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final ColorCondicionalUi colorCondicionalUi, final ColorCondicionalListener lisener){
        try{
            checkDesde.setChecked(colorCondicionalUi.isSelectDesde());
            checkHasta.setChecked(colorCondicionalUi.isSelectHasta());
            viewFondo.setBackgroundColor(colorCondicionalUi.getColorFondo());
            viewTexto.setBackgroundColor(colorCondicionalUi.getColorTexto());
        }catch (Exception e){
            e.printStackTrace();
        }


        txtDesdeHasta.setText( String.valueOf(new Formatter().format("%02d",colorCondicionalUi.getDesde()) + " - " + new Formatter().format("%02d",colorCondicionalUi.getHasta())));
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisener.onClickEliminar(colorCondicionalUi);
            }
        });

        viewFondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisener.onClickColorFondo(colorCondicionalUi);
            }
        });

        viewTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisener.onClickColorTexto(colorCondicionalUi);
            }
        });

        txtDesdeHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisener.onClickRango(colorCondicionalUi);
            }
        });
        checkHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisener.onClickHascta(colorCondicionalUi);
            }
        });
        checkDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lisener.onClickDesde(colorCondicionalUi);
            }
        });
    }
}
