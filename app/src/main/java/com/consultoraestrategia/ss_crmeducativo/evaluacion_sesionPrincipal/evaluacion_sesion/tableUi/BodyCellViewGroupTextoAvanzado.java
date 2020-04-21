package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi;

import android.content.Context;
import android.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.Estilo;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 16/10/2017.
 */

public class BodyCellViewGroupTextoAvanzado extends BodyCellViewGroup {

    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.txtNota)
    TextView txtNota;
    private NotaUi notaUi;

    public BodyCellViewGroupTextoAvanzado(Context context) {
        super(context);
    }

    @Override
    public void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_evaluacion_sesion_item_body_avanzado, this, true);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void clickViewHold(FrameLayout frameLayout) {
        if (frameLayout == null) return;
        if (frameLayout instanceof BodyCellViewGroupTextoAvanzado) {
            BodyCellViewGroupTextoAvanzado bodyCellViewGroupTexto = (BodyCellViewGroupTextoAvanzado) frameLayout;
            bodyCellViewGroupTexto.root.setBackgroundColor(Color.WHITE);
            bodyCellViewGroupTexto.txtNota.setTextColor(Color.GRAY);
            NotaUi notaUi = bodyCellViewGroupTexto.getNotaUi();
            bodyCellViewGroupTexto.txtNota.setText(String.format("%.1f", notaUi.getNotaEvaluacion()));
        }

    }

    @Override
    public void clickView(Object row, Object colum) {
        notaUi = (NotaUi) colum;
        AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi) row;
        if (!notaUi.getResaltar()) {
            /*txtResultado.setBackgroundColor(ContextCompat.getColor(context,R.color.md_grey_400));
            txtResultado.setTextColor(Color.BLACK);*/
            colorPaintAlumnos(notaUi.getEstilo());
            alumnosEvaluacionUi.setFrameLayout(this);
        } else {
            /*txtResultado.setBackgroundColor(Color.WHITE);
            txtResultado.setTextColor(Color.GRAY);*/
            colorPaintReset();
            alumnosEvaluacionUi.setFrameLayout(null);
        }
        txtNota.setText(String.format("%.1f", notaUi.getNota()));
    }


    @Override
    public void bindBody(AlumnosEvaluacionUi item, int row, int column) {
        List<NotaUi> notaUiList = item.getNotaUis();
        if (notaUiList.size() > column) {
            notaUi = notaUiList.get(column);
            txtNota.setText(String.format("%.1f", notaUi.getNota()));
            if (notaUi.getResaltar()) {
                item.setFrameLayout(this);
                /*txtResultado.setBackgroundColor(ContextCompat.getColor(context,R.color.md_grey_400));
                txtResultado.setTextColor(Color.BLACK);*/
                colorPaintAlumnos(notaUi.getEstilo());
            } else {
                /*txtResultado.setBackgroundColor(Color.WHITE);
                txtResultado.setTextColor(Color.GRAY);*/
                colorPaintReset();
            }
        }

    }


    public void colorPaintAlumnos(Estilo estilo) {
        switch (estilo) {
            case AZUL:
                root.setBackgroundColor(ContextCompat.getColor(context, R.color.md_blue_300));
                txtNota.setTextColor(Color.WHITE);
                break;
            case ROJO:
                root.setBackgroundColor(ContextCompat.getColor(context, R.color.md_pink_50));
                txtNota.setTextColor(ContextCompat.getColor(context, R.color.md_red_300));
                break;
            case ANARANJADO:
                root.setBackgroundColor(ContextCompat.getColor(context, R.color.md_orange_300));
                txtNota.setTextColor(Color.WHITE);
                break;
            case BLANCO:
                //txtResultado.setBackgroundColor(ContextCompat.getColor(context, R.color.md_grey_300));
                break;
            case VERDE:
                root.setBackgroundColor(ContextCompat.getColor(context, R.color.md_green_300));
                txtNota.setTextColor(Color.WHITE);
                break;
        }
    }

    private void colorPaintReset() {
        root.setBackgroundColor(Color.WHITE);
        txtNota.setTextColor(Color.GRAY);

    }

    @Override
    public NotaUi getNotaUi() {
        return notaUi;
    }
}
