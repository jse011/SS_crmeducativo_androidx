package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi;

import android.content.Context;
import android.graphics.Color;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.BodyCellViewUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.Estilo;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 16/10/2017.
 */

public class BodyCellViewGroupTextoAvanzado extends BodyCellViewGroup {

    public TextView txtNota;
    private NotaUi notaUi;

    public BodyCellViewGroupTextoAvanzado(Context context) {
        super(context);
    }

    @Override
    public void init() {
        LayoutInflater.from(context).inflate(R.layout.fragment_evaluacion_sesion_item_body_avanzado, this, true);
        root = findViewById(R.id.root);
        txtNota = findViewById(R.id.txtNota);
    }

    @Override
    public void clickViewHold(FrameLayout frameLayout) {
        BodyCellViewGroupTextoAvanzado bodyCellViewGroupTexto = (BodyCellViewGroupTextoAvanzado)frameLayout;
        if(bodyCellViewGroupTexto == null)return;
        /*bodyCellViewGroupTexto.txtResultado.setBackgroundColor(Color.WHITE);
        bodyCellViewGroupTexto.txtResultado.setTextColor(Color.GRAY);*/
        bodyCellViewGroupTexto.colorPaintReset();
        NotaUi notaUi = bodyCellViewGroupTexto.getNotaUi();
        bodyCellViewGroupTexto.txtNota.setText(String.format("%.1f", notaUi.getNotaEvaluacion()));
    }

    @Override
    public void clickView(Object row, Object colum) {
        boolean alumnoColor = false;
        if(row instanceof AlumnosEvaluacionUi){
            alumnoColor = true;

        }else if(row instanceof GrupoEvaluacionUi){
            alumnoColor = false;
        }
        notaUi = (NotaUi) colum;
        BodyCellViewUi bodyCellViewUi = (BodyCellViewUi) row;
        if(notaUi == null)return;
          if(!notaUi.getResaltar()){
                /*txtResultado.setBackgroundColor(ContextCompat.getColor(context,ColorResaltar));
                txtResultado.setTextColor(Color.BLACK);*/
               resaltarRubroEvaluacion(notaUi.getEstilo(),alumnoColor);
                bodyCellViewUi.setFrameLayout(this);
            }else {
                /*txtResultado.setBackgroundColor(Color.WHITE);
                txtResultado.setTextColor(Color.GRAY);*/
              colorPaintReset();
               bodyCellViewUi.setFrameLayout(null);
            }
        txtNota.setText(String.format("%.1f", notaUi.getNota()));
    }

    @Override
    public void bindBody(Object item, int row, int column) {
       boolean alumnoColor = false;

        List<NotaUi> notaUiList = new ArrayList<>();
        if(item instanceof AlumnosEvaluacionUi){
            AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi) item;
            notaUiList = alumnosEvaluacionUi.getNotaUis();
            alumnoColor = true;
        }else if(item instanceof GrupoEvaluacionUi){
            GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi)item;
            notaUiList = grupoEvaluacionUi.getNotaUis();
            alumnoColor = false;
        }

        if (notaUiList.size() > column) {
            notaUi = notaUiList.get(column);
            BodyCellViewUi bodyCellViewUi = (BodyCellViewUi) item;
            txtNota.setText(String.format("%.1f", notaUi.getNota()));
            if(notaUi.getResaltar()){
                /*txtResultado.setBackgroundColor(ContextCompat.getColor(context,ColorResaltar));
                txtResultado.setTextColor(Color.BLACK);*/
                resaltarRubroEvaluacion(notaUi.getEstilo(),alumnoColor);
                bodyCellViewUi.setFrameLayout(this);
            }else {
                /*txtResultado.setBackgroundColor(Color.WHITE);
                txtResultado.setTextColor(Color.GRAY);*/
                colorPaintReset();
            }
        }

    }


    public void resaltarRubroEvaluacion(Estilo estilo, boolean alumnocolor){
       if(alumnocolor){
           colorPaintAlumnos(estilo);
       }else {
           colorPaintGrupos(estilo);
       }
        
    }


    public void colorPaintAlumnos(Estilo estilo ){
        switch (estilo){
            case AZUL:
                root.setBackgroundColor(ContextCompat.getColor(context,R.color.md_blue_300));
                txtNota.setTextColor(Color.WHITE);
                break;
            case ROJO:
                root.setBackgroundColor(ContextCompat.getColor(context,R.color.md_pink_50));
                txtNota.setTextColor(ContextCompat.getColor(context,R.color.md_red_300));
                break;
            case ANARANJADO:
                root.setBackgroundColor(ContextCompat.getColor(context,R.color.md_orange_300));
                txtNota.setTextColor(Color.WHITE);
                break;
            case BLANCO:
                root.setBackgroundColor(ContextCompat.getColor(context,R.color.md_grey_300));
                break;
            case VERDE:
                root.setBackgroundColor(ContextCompat.getColor(context,R.color.md_green_300));
                txtNota.setTextColor(Color.WHITE);
                break;
        }
    }

    public void colorPaintGrupos(Estilo estilo ){
        switch (estilo){
            case AZUL:
                root.setBackgroundColor(ContextCompat.getColor(context,R.color.md_blue_700));
                txtNota.setTextColor(Color.WHITE);
                break;
            case ROJO:
                root.setBackgroundColor(ContextCompat.getColor(context,R.color.md_pink_100));
                txtNota.setTextColor(ContextCompat.getColor(context,R.color.md_red_700));
                break;
            case ANARANJADO:
                root.setBackgroundColor(ContextCompat.getColor(context,R.color.md_orange_A700));
                txtNota.setTextColor(Color.WHITE);
                break;
            case BLANCO:
                root.setBackgroundColor(ContextCompat.getColor(context,R.color.md_light_blue_300));
                txtNota.setTextColor(Color.GRAY);
                break;
            case VERDE:
                root.setBackgroundColor(ContextCompat.getColor(context,R.color.md_green_700));
                txtNota.setTextColor(Color.WHITE);
                break;
        }
    }

    public void colorPaintReset(){
            root.setBackgroundColor(Color.WHITE);
            txtNota.setTextColor(Color.GRAY);
    }
    @Override
    public NotaUi getNotaUi() {
        return notaUi;
    }
}
