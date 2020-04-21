package com.consultoraestrategia.ss_crmeducativo.programahorario.adapter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.programahorario.entities.CursoUi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CursoHorarioAdapter extends RecyclerView.Adapter<CursoHorarioAdapter.CursoHorarioHolder> {

    private List<CursoUi> cursosUIList;

    public CursoHorarioAdapter(List<CursoUi> cursosUIList) {
        this.cursosUIList = cursosUIList;
    }

    @NonNull
    @Override
    public CursoHorarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_programa_horario_curso, parent, false);
        return new CursoHorarioHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoHorarioHolder holder, int position) {
        holder.bind(cursosUIList.get(position));
    }

    @Override
    public int getItemCount() {
        return cursosUIList.size();
    }

    public void setList(List<CursoUi> cursoUiList) {
        this.cursosUIList.clear();
        this.cursosUIList.addAll(cursoUiList);
        notifyDataSetChanged();
    }

    public class CursoHorarioHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.position)
        TextView position;
        @BindView(R.id.txt_nombre)
        TextView txtNombre;
        @BindView(R.id.txt_detalle)
        TextView txtDetalle;
        public CursoHorarioHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(CursoUi cursosUI) {
            Drawable circle = ContextCompat.getDrawable(itemView.getContext(), R.drawable.circled_rounded);
            try {
                circle.mutate().setColorFilter(Color.parseColor(cursosUI.getColor1()), PorterDuff.Mode.SRC_ATOP);
            }catch (Exception e){
                e.printStackTrace();
            }
            position.setBackground(circle);
            position.setText(String.valueOf(cursosUI.getPosicion()));
            position.setTextColor(Color.parseColor("#ffffff"));
            txtNombre.setText(cursosUI.getNombre());
            txtDetalle.setText(cursosUI.getDetalle());

        }
    }

}
