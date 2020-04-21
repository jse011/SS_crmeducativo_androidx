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

public class BodyCellViewGroupTexto extends BodyCellViewGroup {

    @BindView(R.id.txtResultado)
    TextView txtResultado;
    @BindView(R.id.root)
    ConstraintLayout root;
    private NotaUi notaUi;

    public BodyCellViewGroupTexto(Context context) {
        super(context);
    }

    @Override
    public void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_evaluacion_sesion_item_body, this, true);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void clickViewHold(FrameLayout frameLayout) {
        if (frameLayout == null) return;
        if (frameLayout instanceof BodyCellViewGroupTexto) {
            BodyCellViewGroupTexto bodyCellViewGroupTexto = (BodyCellViewGroupTexto) frameLayout;
            bodyCellViewGroupTexto.root.setBackgroundColor(Color.WHITE);
            bodyCellViewGroupTexto.txtResultado.setTextColor(Color.GRAY);
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
    }


    @Override
    public void bindBody(AlumnosEvaluacionUi item, int row, int column) {
        List<NotaUi> notaUiList = item.getNotaUis();
        if (notaUiList.size() > column) {
            notaUi = notaUiList.get(column);
            txtResultado.setText(notaUi.getContenido());
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
                txtResultado.setTextColor(Color.WHITE);
                break;
            case ROJO:
                root.setBackgroundColor(ContextCompat.getColor(context, R.color.md_pink_50));
                txtResultado.setTextColor(ContextCompat.getColor(context, R.color.md_red_300));
                break;
            case ANARANJADO:
                root.setBackgroundColor(ContextCompat.getColor(context, R.color.md_orange_300));
                txtResultado.setTextColor(Color.WHITE);
                break;
            case BLANCO:
                txtResultado.setBackgroundColor(ContextCompat.getColor(context, R.color.md_grey_300));
                break;
            case VERDE:
                root.setBackgroundColor(ContextCompat.getColor(context, R.color.md_green_300));
                txtResultado.setTextColor(Color.WHITE);
                break;
        }
    }

    private void colorPaintReset() {
        root.setBackgroundColor(Color.WHITE);
        txtResultado.setTextColor(Color.GRAY);

    }

    @Override
    public NotaUi getNotaUi() {
        return notaUi;
    }
}
