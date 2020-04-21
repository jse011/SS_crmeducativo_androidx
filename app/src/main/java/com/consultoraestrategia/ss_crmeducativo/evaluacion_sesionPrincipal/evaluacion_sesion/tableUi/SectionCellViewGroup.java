package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;

import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 16/10/2017.
 */

public class SectionCellViewGroup extends FrameLayout
        implements
        TableFixHeaderAdapter.SectionBinder<AlumnosEvaluacionUi> {


    private Context context;

    public SectionCellViewGroup(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public SectionCellViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_evaluacion_sesion_item_section, this, true);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bindSection(AlumnosEvaluacionUi alumnoUi, int i, int i1) {

    }
}