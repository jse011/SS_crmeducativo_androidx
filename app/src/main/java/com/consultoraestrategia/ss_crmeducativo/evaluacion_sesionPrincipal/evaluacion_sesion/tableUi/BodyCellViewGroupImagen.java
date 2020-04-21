package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by @stevecampos on 16/10/2017.
 */

public class BodyCellViewGroupImagen extends BodyCellViewGroup {


    @BindView(R.id.imgResultado)
    CircleImageView imgResultado;
    @BindView(R.id.root)
    ConstraintLayout root;
    private String TAG = "BodyCellViewGroupImagen";
    private NotaUi notaUi;

    public BodyCellViewGroupImagen(Context context) {
        super(context);
    }

    @Override
    public void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_evaluacion_sesion_item_body_imagen, this, true);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void clickViewHold(FrameLayout frameLayout) {
        Log.d(TAG, "clickViewHold");
        if (frameLayout == null) return;
        Log.d(TAG, "holdView");
        if (frameLayout instanceof BodyCellViewGroupImagen) {
            Log.d(TAG, "BodyCellViewGroupImagen");
            BodyCellViewGroupImagen bodyCellViewGroupImagen = (BodyCellViewGroupImagen) frameLayout;
            bodyCellViewGroupImagen.imgResultado.setAlpha(0.3f);
            NotaUi notaUi = bodyCellViewGroupImagen.getNotaUi();
        }
    }


    @Override
    public void clickView(Object row, Object colum) {
        notaUi = (NotaUi) colum;
        AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi) row;
        if (!notaUi.getResaltar()) {
            imgResultado.setAlpha(1.0f);
            alumnosEvaluacionUi.setFrameLayout(this);
            //root.setBackgroundColor(getContext().getColor(R.color.md_grey_200));
        } else {
            imgResultado.setAlpha(0.3f);
            alumnosEvaluacionUi.setFrameLayout(null);
            //root.setBackgroundColor(Color.WHITE);
        }

    }


    @Override
    public void bindBody(AlumnosEvaluacionUi item, int row, int column) {
        List<NotaUi> notaUiList = item.getNotaUis();
        if (notaUiList.size() > column) {
            notaUi = notaUiList.get(column);
            Glide.with(context)
                    .load(notaUi.getIcono())
                    .apply(Utils.getGlideRequestOptions(R.drawable.ic_circle).override(50, 50))
                    .into(imgResultado);
            if (notaUi.getResaltar()) {
                item.setFrameLayout(this);
                imgResultado.setAlpha(1.0f);
                //root.setBackgroundColor(getContext().getColor(R.color.md_grey_200));
            } else {
                imgResultado.setAlpha(0.3f);
                //root.setBackgroundColor(Color.WHITE);
            }

            Log.d(TAG, row + "is null " + (item.getFrameLayout() == null));
        }

    }

    @Override
    public NotaUi getNotaUi() {
        return notaUi;
    }
}
