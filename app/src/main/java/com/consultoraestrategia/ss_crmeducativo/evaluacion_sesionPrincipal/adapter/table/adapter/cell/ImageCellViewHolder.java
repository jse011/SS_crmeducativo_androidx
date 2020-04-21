package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell;

import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ImageCellViewHolder extends AbstractViewHolder {

    @BindView(R.id.imgResultado)
    CircleImageView imgResultado;
    @BindView(R.id.root)
    ConstraintLayout root;
    private String TAG = "ImageCellViewHolder";
    private NotaUi notaUi;
    private AlumnosEvaluacionUi alumnosEvaluacionUi;
    private GrupoEvaluacionUi grupoEvaluacionUi;
    public ImageCellViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void clickViewHold(ImageCellViewHolder imageCellViewHolder) {
        Log.d(TAG, "clickViewHold");
        imageCellViewHolder.imgResultado.setAlpha(0.3f);
    }

    public void clickView(NotaUi notaUi) {
        if (!notaUi.getResaltar()) {
            imgResultado.setAlpha(1.0f);
            if(alumnosEvaluacionUi!=null)alumnosEvaluacionUi.setAbstractViewHolder(this);
            if(grupoEvaluacionUi!=null)grupoEvaluacionUi.setAbstractViewHolder(this);
            //root.setBackgroundColor(getContext().getColor(R.color.md_grey_200));
        } else {
            imgResultado.setAlpha(0.3f);
            if(alumnosEvaluacionUi!=null)alumnosEvaluacionUi.setAbstractViewHolder(null);
            if(grupoEvaluacionUi!=null)grupoEvaluacionUi.setAbstractViewHolder(null);
            //root.setBackgroundColor(Color.WHITE);
        }

    }

    public void bindBody(NotaUi notaUi) {
        this.alumnosEvaluacionUi = notaUi.getAlumnoEvaluacion();
        this.grupoEvaluacionUi = notaUi.getGrupoEvaluacion();
        this.notaUi = notaUi;
        Glide.with(imgResultado)
                .load(notaUi.getIcono())
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_circle).override(50, 50))
                .into(imgResultado);
        if (notaUi.getResaltar()) {
            if(alumnosEvaluacionUi!=null)alumnosEvaluacionUi.setAbstractViewHolder(this);
            if(grupoEvaluacionUi!=null)grupoEvaluacionUi.setAbstractViewHolder(this);
            imgResultado.setAlpha(1.0f);
            //root.setBackgroundColor(getContext().getColor(R.color.md_grey_200));
        } else {
            imgResultado.setAlpha(0.3f);
            //root.setBackgroundColor(Color.WHITE);
        }

    }

    public NotaUi getNotaUi() {
        return notaUi;
    }

}
