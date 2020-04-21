package com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemListener;


public class RepositorioBtnAddHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ConstraintLayout btnAddArchivo;
    private RepositorioItemListener listener;

    public RepositorioBtnAddHolder(View itemView) {
        super(itemView);
        btnAddArchivo = itemView.findViewById(R.id.btn_add_archivo);
        btnAddArchivo.setOnClickListener(this);
    }

    public void bind(RepositorioItemListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_add_archivo){
            //listener.onClickAddArchivoComentario();
        }
    }
}