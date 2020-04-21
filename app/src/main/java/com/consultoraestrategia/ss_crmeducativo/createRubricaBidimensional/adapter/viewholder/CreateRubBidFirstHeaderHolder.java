package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.viewholder;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 14/12/2017.
 */

public class CreateRubBidFirstHeaderHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    public CreateRubBidFirstHeaderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(String title) {
        txtTitle.setText(title);
    }
}
