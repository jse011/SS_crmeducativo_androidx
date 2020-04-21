package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BodyCellViewGroupPublicar extends BodyCellViewGroup {

    @BindView(R.id.imgResultado)
    ImageView imgResultado;
    @BindView(R.id.root)
    ConstraintLayout root;

    public BodyCellViewGroupPublicar(Context context) {
        super(context);
    }

    @Override
    public void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.evaluacion_sesion_item_option_publicar, this, true);
        ButterKnife.bind(this, view);
    }

    @Override
    public void clickViewHold(FrameLayout frameLayout) {

    }

    @Override
    public void clickView(Object row, Object colum) {
        OptionPublicar optionPublicar = (OptionPublicar) colum;
        if (!optionPublicar.isSelected()) {
            imgResultado.setImageDrawable(ContextCompat.getDrawable(root.getContext(), R.drawable.ic_tarea_publicada_message));
        }else {
            imgResultado.setImageDrawable(ContextCompat.getDrawable(root.getContext(), R.drawable.ic_tarea_publicada));
        }
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
        NotaUi notaUi = notaUiList.get(column);
        OptionPublicar optionPublicar = (OptionPublicar) notaUi;
        if (optionPublicar.isSelected()) {
            imgResultado.setImageDrawable(ContextCompat.getDrawable(root.getContext(), R.drawable.ic_tarea_publicada_message));
        }else {
            imgResultado.setImageDrawable(ContextCompat.getDrawable(root.getContext(), R.drawable.ic_tarea_publicada));
        }
    }

}
