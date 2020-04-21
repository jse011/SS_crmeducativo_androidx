package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder;


import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 2/07/2018.
 */

public class CornerHolder {
    private final View.OnClickListener listener;
    private View view;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.root)
    ConstraintLayout root;

    public CornerHolder(View view, View.OnClickListener listener) {
        this.view = view;
        ButterKnife.bind(this, view);
        this.listener = listener;
    }

    public void bind(String titulo) {
        txtTitle.setText(titulo);
        view.setVisibility(View.VISIBLE);
        root.setOnClickListener(listener);
    }

    public View getView() {
        return view;
    }
}
