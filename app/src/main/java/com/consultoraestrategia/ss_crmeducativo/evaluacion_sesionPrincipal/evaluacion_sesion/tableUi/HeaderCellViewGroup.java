package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.tableUi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 16/10/2017.
 */

public class HeaderCellViewGroup extends FrameLayout
        implements
        TableFixHeaderAdapter.HeaderBinder<NotaUi> {


    @BindView(R.id.txtTitle)
    TextView txtTitle;
    private Context context;

    public HeaderCellViewGroup(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public HeaderCellViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_evaluacion_session_item_header, this, true);
        ButterKnife.bind(this, view);
    }

    @Override
    public void bindHeader(NotaUi item, int column) {
        txtTitle.setText(item.getContenido());
        //txtSubtitle.setText(item.getSubtitle());
    }

}
