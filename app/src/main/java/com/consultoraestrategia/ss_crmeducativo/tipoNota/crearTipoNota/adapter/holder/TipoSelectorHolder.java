package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.adapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.listener.ValoresListener;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.ValoresUi;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 16/02/2018.
 */

public class TipoSelectorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static String TAG = TipoSelectorHolder.class.getSimpleName();

    @BindView(R.id.viewDetails)
    View viewGroup;
    @BindView(R.id.constraintLayoutItemView)
    ConstraintLayout constraintLayoutItemView;


    public TipoSelectorHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        constraintLayoutItemView.setOnClickListener(this);
    }

    ValoresListener listener;
    ValoresUi valoresUi;

    public void bind(final ValoresUi valoresUi, final ValoresListener listener) {
        this.listener = listener;
        this.valoresUi = valoresUi;

/*        if (valoresUi.getTipoNotaUi().isaBoolean()){
            viewGroup.setVisibility(View.VISIBLE);
        }else{
            viewGroup.setVisibility(View.GONE);
        }*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.constraintLayoutItemView:
                Log.d(TAG,"onClickTipoComportamiento::TipoSelectorHolder");
                listener.itemValores(valoresUi);
                break;
            default:
                break;
        }
    }
}
