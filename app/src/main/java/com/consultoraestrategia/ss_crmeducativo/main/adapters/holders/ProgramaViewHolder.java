package com.consultoraestrategia.ss_crmeducativo.main.adapters.holders;

import android.graphics.Color;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.main.entities.ProgramaEduactivosUI;
import com.consultoraestrategia.ss_crmeducativo.main.listeners.MenuListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by irvinmarin on 09/10/2017.
 */

public class ProgramaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.txtAccesoName)
    TextView txtAccesoName;
    @BindView(R.id.imgIcon)
    ImageView imgIcon;
    private MenuListener listener;
    private ProgramaEduactivosUI programaEduactivosUI;

    public ProgramaViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(final ProgramaEduactivosUI programaEduactivosUI, final MenuListener periodoListener) {
        this.listener = periodoListener;
        this.programaEduactivosUI = programaEduactivosUI;
        txtAccesoName.setText(programaEduactivosUI.getNombrePrograma());
        itemView.setOnClickListener(this);
        if(programaEduactivosUI.isSeleccionado()){
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),R.color.md_blue_50));
        }else {
            itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public void onClick(View view) {
        listener.onMenuSelected(programaEduactivosUI);
    }

}
