package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell;

import android.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.BodyCellViewUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.Estilo;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextoAvanzadoCellViewHolder extends AbstractViewHolder {
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.txtNota)
    TextView txtNota;
    private NotaUi notaUi;
    private BodyCellViewUi alumnosEvaluacionUi;
    private BodyCellViewUi grupoEvaluacionUi;

    public TextoAvanzadoCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void clickViewHold(TextoAvanzadoCellViewHolder textoAvanzadoCellViewHolder) {
        textoAvanzadoCellViewHolder.root.setBackgroundColor(Color.WHITE);
        textoAvanzadoCellViewHolder.txtNota.setTextColor(Color.GRAY);
        NotaUi notaUi = textoAvanzadoCellViewHolder.getNotaUi();
        textoAvanzadoCellViewHolder.txtNota.setText(String.format("%.1f", notaUi.getNotaEvaluacion()));

    }

    public void clickView(NotaUi notaUi) {
        if (!notaUi.getResaltar()) {
            /*txtResultado.setBackgroundColor(ContextCompat.getColor(context,R.color.md_grey_400));
            txtResultado.setTextColor(Color.BLACK);*/
            colorPaintAlumnos(notaUi.getEstilo());
            if(alumnosEvaluacionUi!=null)alumnosEvaluacionUi.setAbstractViewHolder(this);
            if(grupoEvaluacionUi!=null)grupoEvaluacionUi.setAbstractViewHolder(this);
        } else {
            /*txtResultado.setBackgroundColor(Color.WHITE);
            txtResultado.setTextColor(Color.GRAY);*/
            colorPaintReset();
            if(alumnosEvaluacionUi!=null)alumnosEvaluacionUi.setAbstractViewHolder(null);
            if(grupoEvaluacionUi!=null)grupoEvaluacionUi.setAbstractViewHolder(null);
        }
        txtNota.setText(String.format("%.1f", notaUi.getNota()));
    }


    public void bind(NotaUi notaUi) {
        this.alumnosEvaluacionUi = notaUi.getAlumnoEvaluacion();
        this.grupoEvaluacionUi = notaUi.getGrupoEvaluacion();
        this.notaUi = notaUi;
        txtNota.setText(String.format("%.1f", notaUi.getNota()));
        if (notaUi.getResaltar()) {
            if(alumnosEvaluacionUi!=null)alumnosEvaluacionUi.setAbstractViewHolder(this);
            if(grupoEvaluacionUi!=null)grupoEvaluacionUi.setAbstractViewHolder(this);
                /*txtResultado.setBackgroundColor(ContextCompat.getColor(context,R.color.md_grey_400));
                txtResultado.setTextColor(Color.BLACK);*/
            colorPaintAlumnos(notaUi.getEstilo());
        } else {
                /*txtResultado.setBackgroundColor(Color.WHITE);
                txtResultado.setTextColor(Color.GRAY);*/
            colorPaintReset();
        }

    }


    public void colorPaintAlumnos(Estilo estilo) {
        switch (estilo) {
            case AZUL:
                root.setBackgroundColor(ContextCompat.getColor(root.getContext(), R.color.md_blue_300));
                txtNota.setTextColor(Color.WHITE);
                break;
            case ROJO:
                root.setBackgroundColor(ContextCompat.getColor(root.getContext(), R.color.md_pink_50));
                txtNota.setTextColor(ContextCompat.getColor(root.getContext(), R.color.md_red_300));
                break;
            case ANARANJADO:
                root.setBackgroundColor(ContextCompat.getColor(root.getContext(), R.color.md_orange_300));
                txtNota.setTextColor(Color.WHITE);
                break;
            case BLANCO:
                root.setBackgroundColor(ContextCompat.getColor(root.getContext(), R.color.md_grey_300));
                txtNota.setTextColor(Color.WHITE);
                break;
            case VERDE:
                root.setBackgroundColor(ContextCompat.getColor(root.getContext(), R.color.md_green_300));
                txtNota.setTextColor(Color.WHITE);
                break;
        }
    }

    private void colorPaintReset() {
        root.setBackgroundColor(Color.WHITE);
        txtNota.setTextColor(Color.GRAY);

    }

    public NotaUi getNotaUi() {
        return notaUi;
    }
}
