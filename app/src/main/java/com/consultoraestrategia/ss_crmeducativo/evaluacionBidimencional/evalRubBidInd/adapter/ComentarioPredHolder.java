package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.adapter;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComentarioPredHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {


    @BindView(R.id.txtdescriocion)
    TextView descripcion;
    @BindView(R.id.fecha)
    TextView fecha;
    private MensajeUi mensajeUi;
    private ComentarioPredeAdapter.ArchivoComentarioListener listener;

    public ComentarioPredHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bind(final MensajeUi mensajeUi, final ComentarioPredeAdapter.ArchivoComentarioListener listener){
        this.mensajeUi = mensajeUi;
        this.listener = listener;
        fecha.setText(Utils.getHoraConFecha(mensajeUi.getFechaCreacion()));
        descripcion.setText(mensajeUi.getDescripcion());
        itemView.setOnLongClickListener(this);

    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            default:
                PopupMenu popup = new PopupMenu(fecha.getContext(), fecha);
                // Inflate the menu from xml
                popup.getMenu().add(Menu.NONE, 1, 1, "Eliminar");
                // Setup menu item selection
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case 1:
                                listener.onClickComentarioNormal(mensajeUi);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                // Show the menu
                popup.show();
                break;
        }

        return false;
    }
}
