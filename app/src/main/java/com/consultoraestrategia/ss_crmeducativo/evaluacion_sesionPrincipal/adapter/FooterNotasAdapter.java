package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.holder.NotaImagenHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.holder.NotaTextoHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;

import java.util.List;

public class FooterNotasAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int NOTAIMAGEN = 1, NOTATEXTO = 2;
    private List<NotaUi> notaUiList;
    private RubroEvaluacionUi.TipoNota tipoNota;

    public FooterNotasAdapter(List<NotaUi> notaUiList, RubroEvaluacionUi.TipoNota tipoNota) {
        this.notaUiList = notaUiList;
        this.tipoNota = tipoNota;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder;
        switch (viewType){
            case NOTAIMAGEN:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_footer_nota_imagen, parent, false);
                viewHolder = new NotaImagenHolder(view);
                break;
             default:
                 view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_footer_nota_texto, parent, false);
                 viewHolder = new NotaTextoHolder(view);
                 break;
        }

        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NotaUi notaUi = notaUiList.get(position);
        switch (holder.getItemViewType()){
            case NOTAIMAGEN:
                ((NotaImagenHolder)holder).bind(notaUi);
                break;
            default:
                ((NotaTextoHolder)holder).bind(notaUi);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return notaUiList.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (tipoNota){
            case IMAGEN:
                return NOTAIMAGEN;
            case TEXTO:
                return NOTATEXTO;
                default:
                return NOTATEXTO;
        }
    }

    public void set(List<NotaUi> notaUiList) {
        this.notaUiList.clear();
        this.notaUiList.addAll(notaUiList);
        notifyDataSetChanged();
    }

    public void setTipo(RubroEvaluacionUi.TipoNota tipo) {
        this.tipoNota = tipo;
    }

    public void updateAlumnoEvaluacion(List<AlumnosEvaluacionUi> alumnosEvaluacionUiList){
        for (AlumnosEvaluacionUi alumnosEvaluacionUi:alumnosEvaluacionUiList)
            update(alumnosEvaluacionUi.getNotaUi());
    }

    public void update(NotaUi notaUi) {
        int position = notaUiList.indexOf(notaUi);
        if (position != -1) {
            notaUiList.set(position, notaUi);
            notifyItemChanged(position);
        }
    }
}
