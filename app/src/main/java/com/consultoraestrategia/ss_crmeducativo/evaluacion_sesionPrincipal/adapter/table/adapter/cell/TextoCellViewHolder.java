package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell;

import android.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.Estilo;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextoCellViewHolder extends AbstractViewHolder {
    @BindView(R.id.txtResultado)
    TextView txtResultado;
    @BindView(R.id.root)
    ConstraintLayout root;
    private NotaUi notaUi;
    private AlumnosEvaluacionUi alumnosEvaluacionUi;
    private GrupoEvaluacionUi grupoEvaluacionUi;

    public TextoCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void clickViewHold(TextoCellViewHolder textoCellViewHolder) {
        textoCellViewHolder.root.setBackgroundColor(Color.WHITE);
        textoCellViewHolder.txtResultado.setTextColor(Color.GRAY);

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
    }


    public void bind(NotaUi notaUi) {
        this.alumnosEvaluacionUi = notaUi.getAlumnoEvaluacion();
        this.grupoEvaluacionUi = notaUi.getGrupoEvaluacion();
        this.notaUi = notaUi;

        txtResultado.setText(notaUi.getContenido());
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
                txtResultado.setTextColor(Color.WHITE);
                break;
            case ROJO:
                root.setBackgroundColor(ContextCompat.getColor(root.getContext(), R.color.md_pink_50));
                txtResultado.setTextColor(ContextCompat.getColor(root.getContext(), R.color.md_red_300));
                break;
            case ANARANJADO:
                root.setBackgroundColor(ContextCompat.getColor(root.getContext(), R.color.md_orange_300));
                txtResultado.setTextColor(Color.WHITE);
                break;
            case BLANCO:
                txtResultado.setBackgroundColor(ContextCompat.getColor(root.getContext(), R.color.md_grey_300));
                break;
            case VERDE:
                root.setBackgroundColor(ContextCompat.getColor(root.getContext(), R.color.md_green_300));
                txtResultado.setTextColor(Color.WHITE);
                break;
        }
    }

    private void colorPaintReset() {
        root.setBackgroundColor(Color.WHITE);
        txtResultado.setTextColor(Color.GRAY);

    }

    public NotaUi getNotaUi() {
        return notaUi;
    }
}
