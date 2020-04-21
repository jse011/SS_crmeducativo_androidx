package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.adapters.holders;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.adapters.MensajePredeterminadoAdapter;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.intitiesUI.MensajePredeterminadoUI;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by irvinmarin on 06/11/2017.
 */

public class MensajePredeterminadoViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = MensajePredeterminadoViewHolder.class.getSimpleName();


    @BindView(R.id.txtTipoMensaje)
    TextView txtTipoMensaje;
    @BindView(R.id.txtTituloMensaje)
    TextView txtTituloMensaje;
    @BindView(R.id.ibtAccion)
    Button ibtAccionDeleted;
    @BindView(R.id.ibtAccionRestaurar)
    Button ibtAccionRestaurar;
    @BindView(R.id.txtAsuntoMensajePred)
    TextInputEditText txtAsuntoMensajePred;
    @BindView(R.id.contentAsunto)
    TextInputLayout contentAsunto;
    @BindView(R.id.txtCabeceraMensajePred)
    TextInputEditText txtCabeceraMensajePred;
    @BindView(R.id.contentCabecera)
    TextInputLayout contentCabecera;
    @BindView(R.id.txtPresentacionMensajePred)
    TextInputEditText txtPresentacionMensajePred;
    @BindView(R.id.contentPresentacion)
    TextInputLayout contentPresentacion;
    @BindView(R.id.txtCuerpoMensajePred)
    TextInputEditText txtCuerpoMensajePred;
    @BindView(R.id.contentCuerpo)
    TextInputLayout contentCuerpo;
    @BindView(R.id.txtDespedidaMensajePred)
    TextInputEditText txtDespedidaMensajePred;
    @BindView(R.id.contentDespedida)
    TextInputLayout contentDespedida;


    public MensajePredeterminadoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final MensajePredeterminadoUI mensajePredeterminadoUI, final MensajePredeterminadoAdapter.OnMensajePredeterminadoClickedListener listener) {


        txtTituloMensaje.setText(mensajePredeterminadoUI.getAsuntoMensaje());
        txtCabeceraMensajePred.setText(mensajePredeterminadoUI.getCabeceraMensaje());
        txtPresentacionMensajePred.setText(mensajePredeterminadoUI.getPresentacionMensaje());
        txtCuerpoMensajePred.setText(mensajePredeterminadoUI.getCuerpoMensaje());
        txtDespedidaMensajePred.setText(mensajePredeterminadoUI.getDespedidaMensaje());
        txtTipoMensaje.setText(mensajePredeterminadoUI.getTipoMensaje());

        ibtAccionRestaurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null)listener.onRestoreItem(mensajePredeterminadoUI);
            }
        });
        if (mensajePredeterminadoUI.isDeleted()) {
            ibtAccionDeleted.setVisibility(View.GONE);
            ibtAccionRestaurar.setVisibility(View.VISIBLE);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)listener.onItemAlreadyDeleted();
                }
            });

        } else {
            ibtAccionRestaurar.setVisibility(View.GONE);
            ibtAccionDeleted.setVisibility(View.VISIBLE);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)listener.onItemClickListener(mensajePredeterminadoUI);
                }
            });
            ibtAccionDeleted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                Toast.makeText(v.getContext(), "Proximamente", Toast.LENGTH_LONG).show();
                    if(listener!=null)listener.onClickDeleteListener(mensajePredeterminadoUI);
                }
            });
        }
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(listener!=null)listener.onClickEditarListener(mensajePredeterminadoUI);
                return false;
            }


        });


    }


}
