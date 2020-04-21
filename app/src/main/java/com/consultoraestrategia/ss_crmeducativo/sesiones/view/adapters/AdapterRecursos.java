package com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters.viewHolder.ViewHolderRecursos;
import java.util.List;

import butterknife.BindView;

public class AdapterRecursos extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @BindView(R.id.img_recurso)
    TextView imgrecurso;
    @BindView(R.id.txt_descripcion_rec)
    TextView txtdescripcion_rec;
    @BindView(R.id.txt_extencion_rec)
    TextView txtextencion_rec;

    private List<RecursoDidacticoEventoC> items;

    public AdapterRecursos(final List<RecursoDidacticoEventoC> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                View v2 = inflater.inflate(R.layout.layout_item_recursos, viewGroup, false);
                viewHolder = new ViewHolderRecursos(v2);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        RecursoDidacticoEventoC recursoDidacticoEvento = items.get(position);
        ViewHolderRecursos vh2 = (ViewHolderRecursos) viewHolder;
        vh2.bind(recursoDidacticoEvento);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}