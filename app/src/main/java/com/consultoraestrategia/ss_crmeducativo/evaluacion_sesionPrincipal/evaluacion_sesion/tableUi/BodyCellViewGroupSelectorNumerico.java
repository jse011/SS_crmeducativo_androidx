package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.SelectorNumericoUi;

import java.util.List;

/**
 * Created by @stevecampos on 16/10/2017.
 */

public class BodyCellViewGroupSelectorNumerico extends BodyCellViewGroup {


    private static final String TAG = BodyCellViewGroupSelectorNumerico.class.getSimpleName();
    public TextView txtResultado;

    public BodyCellViewGroupSelectorNumerico(Context context) {
        super(context);
    }

    @Override
    public void init() {
        LayoutInflater.from(context).inflate(R.layout.fragment_evaluacion_sesion_item_body, this, true);
        txtResultado = findViewById(R.id.txtResultado);
        root = findViewById(R.id.root);
    }

    @Override
    public void clickViewHold(FrameLayout frameLayout) {

    }

    @Override
    public void clickView(Object row, Object colum) {

    }

    @Override
    public NotaUi getNotaUi() {
        return null;
    }


    @Override
    public void bindBody(AlumnosEvaluacionUi item, int row, int column) {
        List<NotaUi> notaUiList = item.getNotaUis();
        if (notaUiList.size() > column) {
            NotaUi notaUi = notaUiList.get(column);
            txtResultado.setText(String.valueOf(notaUi.getNota()));
        }

    }

    @Override
    public void destroyView() {
        txtResultado = null;
        root = null;
    }
}
