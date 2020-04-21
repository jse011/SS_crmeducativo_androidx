package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.tableUi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 16/10/2017.
 */

public class FirstHeaderCellViewGroup extends FrameLayout
        implements
        TableFixHeaderAdapter.FirstHeaderBinder<String> {

    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.root)
    LinearLayout root;
    private Context context;

    public FirstHeaderCellViewGroup(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public FirstHeaderCellViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_evaluacion_sesion_item_firstheader, this, true);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bindFirstHeader(String item) {
        txtTitle.setText(item);
    }

}