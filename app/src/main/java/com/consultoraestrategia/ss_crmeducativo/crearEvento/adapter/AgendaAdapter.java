package com.consultoraestrategia.ss_crmeducativo.crearEvento.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.CompoundButtonCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AgendaUi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.ViewHolder> {

    private List<AgendaUi> agendaUiList = new ArrayList<>();
    private Listener listener;

    public AgendaAdapter(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_agenda_crear_evento, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(agendaUiList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return agendaUiList.size();
    }

    public void setList(List<AgendaUi> agendaUiList) {
        this.agendaUiList.clear();
        this.agendaUiList.addAll(agendaUiList);
        notifyDataSetChanged();
    }

    public void update(AgendaUi agendaUi) {
        int position = agendaUiList.indexOf(agendaUi);
        if(position!=-1){
            agendaUiList.set(position, agendaUi);
            notifyItemChanged(position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.position)
        TextView position;
        @BindView(R.id.txt_nombre)
        TextView txtNombre;
        @BindView(R.id.txt_detalle)
        TextView txtDetalle;
        @BindView(R.id.text_tipo_agenda)
        TextView textTipoAgenda;
        @BindView(R.id.cont_tipo_agenda)
        CardView contTipoAgenda;
        @BindView(R.id.txt_nombre_2)
        TextView txtNombre2;
        @BindView(R.id.txt_tipo_agenda_2)
        TextView txtTipoAgenda2;
        @BindView(R.id.cont_tipo_agenda_2)
        CardView contTipoAgenda2;
        @BindView(R.id.cont_agenda_2)
        ConstraintLayout contAgenda2;
        @BindView(R.id.radioButton2)
        CheckBox radioButton2;
        @BindView(R.id.cont_agenda)
        ConstraintLayout contAgenda;
        private AgendaUi agendaUi;
        private Listener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(AgendaUi agendaUi, Listener listener) {
            this.agendaUi = agendaUi;
            this.listener = listener;
            itemView.setOnClickListener(this);
            if(agendaUi.getCargaCursoId()>0){
                contAgenda2.setVisibility(View.GONE);
                contAgenda.setVisibility(View.VISIBLE);
                Drawable circle = ContextCompat.getDrawable(itemView.getContext(), R.drawable.circled_rounded);
                try {
                    circle.mutate().setColorFilter(Color.parseColor(agendaUi.getColor1()), PorterDuff.Mode.SRC_ATOP);
                    contTipoAgenda.setCardBackgroundColor(Color.parseColor(agendaUi.getColor1()));
                    contTipoAgenda2.setCardBackgroundColor(Color.parseColor(agendaUi.getColor1()));
                    position.setTextColor(Color.WHITE);
                }catch (Exception e){
                    e.printStackTrace();
                }
                position.setBackground(circle);

            }else {
                contAgenda2.setVisibility(View.VISIBLE);
                contAgenda.setVisibility(View.GONE);
                position.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.corner_circled_crear_evento));
                position.setTextColor(Color.BLACK);
            }

            String nombre = agendaUi.getNombre();
            String alias = "";
            if(!TextUtils.isEmpty(nombre)&&nombre.length()>2){
                alias = nombre.charAt(0)+""+nombre.charAt(1);
            }
            position.setText(alias);
            txtNombre.setText(nombre);
            txtNombre2.setText(nombre);
            txtDetalle.setText(agendaUi.getDescripcion());
            radioButton2.setChecked(agendaUi.isSelected());


        }

        @Override
        public void onClick(View v) {
            listener.onClick(agendaUi);
        }
    }


    public interface Listener {

        void onClick(AgendaUi agendaUi);
    }
}
