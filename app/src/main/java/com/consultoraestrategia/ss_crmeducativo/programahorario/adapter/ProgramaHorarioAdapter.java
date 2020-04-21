package com.consultoraestrategia.ss_crmeducativo.programahorario.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.ProgramaHorarioUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgramaHorarioAdapter extends RecyclerView.Adapter<ProgramaHorarioAdapter.ProgramaHorarioHolder> {

    private List<ProgramaHorarioUi> programaHorarioUiList;
    private ProgramaHorarioListener listener;

    public ProgramaHorarioAdapter(List<ProgramaHorarioUi> programaHorarioUiList, ProgramaHorarioListener listener) {
        this.programaHorarioUiList = programaHorarioUiList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProgramaHorarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horario_programa, parent, false);
        return new ProgramaHorarioHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramaHorarioHolder holder, int position) {
        holder.bind(programaHorarioUiList.get(position));
    }

    @Override
    public int getItemCount() {
        return programaHorarioUiList.size();
    }

    public void setList(List<ProgramaHorarioUi> programaHorarioUiList) {
        this.programaHorarioUiList.clear();
        this.programaHorarioUiList.addAll(programaHorarioUiList);
        notifyDataSetChanged();
    }

    public class ProgramaHorarioHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ProgramaHorarioListener listener;
        @BindView(R.id.txt_nombre)
        TextView txtNombre;
        @BindView(R.id.linea)
        TextView linea;
        private ProgramaHorarioUi programaHorarioUi;

        public ProgramaHorarioHolder(View itemView, ProgramaHorarioListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        public void bind(ProgramaHorarioUi programaHorarioUi) {
            this.programaHorarioUi = programaHorarioUi;
            txtNombre.setText(programaHorarioUi.getNombre());
            if(programaHorarioUi.isSelect()){
                linea.setVisibility(View.VISIBLE);
            }else {
                linea.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                default:
                    listener.onClick(programaHorarioUi);
                    break;
            }
        }
    }

    public interface ProgramaHorarioListener {
        void onClick(ProgramaHorarioUi programaHorarioUi);
    }

}
