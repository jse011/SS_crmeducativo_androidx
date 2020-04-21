package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.adapters;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.ListenerTabCreateComport;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoUi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TipoPadresHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {


    @BindView(R.id.imageView21)
    ImageView imageView21;
    @BindView(R.id.tiponombre)
    TextView tiponombre;
    @BindView(R.id.root)
    ConstraintLayout root;
    ListenerTabCreateComport listener;
    private TipoUi tipoUi;

    public TipoPadresHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }
    public void bind(final TipoUi tipoUi, final ListenerTabCreateComport listener){
        this.listener=listener;
        this.tipoUi = tipoUi;
        tiponombre.setText(tipoUi.getNombre());
        int url;
        switch (tipoUi.getId()){
            case 541:
                url=R.drawable.medal;
                break;
            case 542:
                url= R.drawable.policeman;
                break;
            default:
                url= R.drawable.ic_graduacion;
                break;
        }
        imageView21.setBackgroundResource(url);

        if(tipoUi.isSelected()){
            root.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_blue_200));
        }else {
            root.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_white_1000));
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                listener.onClickTipoMerito(tipoUi);
                break;
        }
    }
}
