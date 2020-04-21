package com.consultoraestrategia.ss_crmeducativo.main.adapters.holders;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.main.entities.GradoUi;
import com.consultoraestrategia.ss_crmeducativo.main.listeners.CursosListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GradoHolder extends RecyclerView.ViewHolder  {

    @BindView(R.id.txtGrado)
    TextView periodo;
    @BindView(R.id.anio)
    TextView anho;
    @BindView(R.id.txtSeccion)
    TextView seccion;
    @BindView(R.id.view)
    CardView view;
    @BindView(R.id.view1)
    View view1;
    GradoUi grado;
    RecyclerView.ViewHolder holder;
    CursosListener listener;
    public GradoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bind(final GradoUi gradoUi, final CursosListener listener, int posicion){
        this.grado=gradoUi;
        this.listener=listener;
        String string = gradoUi.getPeriodo();
        String[] parts = string.split(" ");
        String part1 = parts[0];
        periodo.setText(part1);
        seccion.setText(gradoUi.getSeccion());
        holder= this;
        if(gradoUi.isSeleted())seleccionar();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onGradoSelected(gradoUi, holder);
            }
        });
    }
    public void  seleccionar(){
        listener.onSelectedViewHolder(holder);
        grado.setSeleted(true);
        seccion.setTextColor(itemView.getResources().getColor(R.color.white));
        periodo.setTextColor(itemView.getResources().getColor(R.color.white));
        anho.setTextColor(itemView.getResources().getColor(R.color.white));
        view.setBackgroundColor(itemView.getResources().getColor(R.color.colorPrimary));
        view1.setBackgroundColor(itemView.getResources().getColor(R.color.colorPrimary));
    }
    public void  deseleccionar(){
        grado.setSeleted(false);
        seccion.setTextColor(itemView.getResources().getColor(R.color.md_blue_grey_20));
        periodo.setTextColor(itemView.getResources().getColor(R.color.md_blue_grey_25));
        anho.setTextColor(itemView.getResources().getColor(R.color.md_blue_grey_20));
        view1.setBackgroundColor(itemView.getResources().getColor(R.color.md_blue_grey_10));
        view.setBackgroundColor(itemView.getResources().getColor(R.color.white));
    }
}
