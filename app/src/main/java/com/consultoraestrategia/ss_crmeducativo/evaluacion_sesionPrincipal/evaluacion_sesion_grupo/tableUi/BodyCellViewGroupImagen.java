package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.BodyCellViewUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 16/10/2017.
 */

public class BodyCellViewGroupImagen extends BodyCellViewGroup {

    public ImageView imgResultado;
    private NotaUi notaUi;

    public BodyCellViewGroupImagen(Context context) {
        super(context);
    }

    @Override
    public void init() {
        LayoutInflater.from(context).inflate(R.layout.fragment_evaluacion_sesion_item_body_imagen, this, true);
        imgResultado = findViewById(R.id.imgResultado);
        root = findViewById(R.id.root);
    }

    @Override
    public void clickViewHold(FrameLayout frameLayout) {
        if (frameLayout == null) return;
        if (frameLayout instanceof BodyCellViewGroupImagen) {
            BodyCellViewGroupImagen bodyCellViewGroupImagen = (BodyCellViewGroupImagen) frameLayout;
            bodyCellViewGroupImagen.imgResultado.setAlpha(0.3f);
            NotaUi notaUi = bodyCellViewGroupImagen.getNotaUi();
        }
    }

    @Override
    public void clickView(Object row, Object colum) {

        notaUi = (NotaUi) colum;
        BodyCellViewUi bodyCellViewUi = (BodyCellViewUi) row;
        if (!notaUi.getResaltar()) {
            imgResultado.setAlpha(1.0f);
            bodyCellViewUi.setFrameLayout(this);
            //root.setBackgroundColor(getContext().getColor(R.color.md_grey_200));
        } else {
           imgResultado.setAlpha(0.3f);
            bodyCellViewUi.setFrameLayout(null);
            //root.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public void bindBody(Object item, int row, int column) {

        List<NotaUi> notaUiList = new ArrayList<>();
        if (item instanceof AlumnosEvaluacionUi) {
            AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi) item;
            notaUiList = alumnosEvaluacionUi.getNotaUis();

        } else if (item instanceof GrupoEvaluacionUi) {
            GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi) item;
            notaUiList = grupoEvaluacionUi.getNotaUis();
        }

        if (notaUiList.size() > column) {
            notaUi = notaUiList.get(column);
            Glide.with(context)
                    .load(notaUi.getIcono())
                    .apply(Utils.getGlideRequestOptions(R.drawable.ic_circle).override(50, 50))
                    .into(imgResultado);
            if (notaUi.getResaltar()) {
                imgResultado.setAlpha(1.0f);
                BodyCellViewUi bodyCellViewUi = (BodyCellViewUi) item;
                bodyCellViewUi.setFrameLayout(this);
                //root.setBackgroundColor(getContext().getColor(R.color.md_grey_200));
            } else {
                imgResultado.setAlpha(0.3f);
                //root.setBackgroundColor(Color.WHITE);
            }
        }

    }
    @Override
    public NotaUi getNotaUi() {
        return notaUi;
    }
}
