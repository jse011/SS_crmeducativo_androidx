package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubBidTable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;

import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 27/11/2017.
 */

public class SectionCellViewGroup extends FrameLayout
        implements
        TableFixHeaderAdapter.SectionBinder<AlumnoUi> {

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
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_evaluacion_item_section, this, true);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bindSection(AlumnoUi alumnoUi, int i, int i1) {

    }
}