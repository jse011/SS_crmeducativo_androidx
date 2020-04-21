package com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.holder;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.adapter.AprendizajeAdapter;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.DesempenioUi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SCIEV on 13/02/2018.
 */

public class ItemDesempenioViewHolderV2 extends RecyclerView.ViewHolder {
    @BindView(R.id.view10)
    View view10;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.txtDescripcion)
    TextView txtDescripcion;
    @BindView(R.id.rc_icds)
    RecyclerView rcIcds;

    public ItemDesempenioViewHolderV2(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(DesempenioUi desempenioUi, boolean views) {

        if (views){
            textView4.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            txtDescripcion.setVisibility(View.VISIBLE);
            txtDescripcion.setText(desempenioUi.getDescripcion());
            rcIcds.setVisibility(View.VISIBLE);
            view10.setVisibility(View.VISIBLE);
            rcIcds.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            rcIcds.setAdapter(new AprendizajeAdapter(new ArrayList<Object>(desempenioUi.getIcdsUiList()), null, null));

            if (desempenioUi.isOcultaCabecera()) {
                textView3.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
            } else {
                textView3.setVisibility(View.VISIBLE);
                textView4.setVisibility(View.VISIBLE);
            }
        }else {
            textView4.setVisibility(View.GONE);
            textView3.setVisibility(View.GONE);
            txtDescripcion.setVisibility(View.GONE);
            rcIcds.setVisibility(View.GONE);
            view10.setVisibility(View.GONE);
        }

    }
}
