package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 16/10/2017.
 */

public class BodyCellViewGroupTecladoNumerico extends BodyCellViewGroup {


    private static final String TAG = BodyCellViewGroupTecladoNumerico.class.getSimpleName();
    public TextView txtResultado;

    public BodyCellViewGroupTecladoNumerico(Context context) {
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
    public void bindBody(Object item, int row, int column) {

        List<NotaUi> notaUiList = new ArrayList<>();
        if(item instanceof AlumnosEvaluacionUi){
            AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi) item;
            notaUiList = alumnosEvaluacionUi.getNotaUis();

        }else if(item instanceof GrupoEvaluacionUi){
            GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi)item;
            notaUiList = grupoEvaluacionUi.getNotaUis();
        }

        if (notaUiList.size() > column) {
                NotaUi notaUi = notaUiList.get(column);
                txtResultado.setText(String.valueOf(notaUi.getNota()));
            }else {
                NotaUi notaUi = notaUiList.get(column);
                txtResultado.setText(String.valueOf(notaUi.getNota()));
        }
    }

}
