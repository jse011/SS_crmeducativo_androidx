package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubBidTable;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 27/11/2017.
 */

public class FirstBodyCellViewGroup extends FrameLayout
        implements
        TableFixHeaderAdapter.FirstBodyBinder<AlumnoUi> {


    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.root)
    ConstraintLayout root;
    private Context context;

    public FirstBodyCellViewGroup(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public FirstBodyCellViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rubrbid_indicadores, this, true);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bindFirstBody(AlumnoUi item, int row) {
        //txtTitle.setText(item.getName());
    }
}
