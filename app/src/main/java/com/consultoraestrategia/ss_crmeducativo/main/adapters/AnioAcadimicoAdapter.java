package com.consultoraestrategia.ss_crmeducativo.main.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AnioAcademicoUi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnioAcadimicoAdapter extends RecyclerView.Adapter<AnioAcadimicoAdapter.ViewHolder> {

    private List<AnioAcademicoUi> anioAcademicoUiList = new ArrayList<>();
    private Listener listener;

    public AnioAcadimicoAdapter(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_selecionar_anio_academico, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.bind(anioAcademicoUiList.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return anioAcademicoUiList.size();
    }

    public void setList(List<AnioAcademicoUi> listAnioAcademico) {
        this.anioAcademicoUiList.clear();
        this.anioAcademicoUiList.addAll(listAnioAcademico);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txt_nombre)
        TextView txtNombre;
        @BindView(R.id.radioButton)
        RadioButton radioButton;
        private AnioAcademicoUi anioAcademicoUi;
        private Listener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(AnioAcademicoUi anioAcademicoUi, Listener listener) {
            this.anioAcademicoUi = anioAcademicoUi;
            this.listener = listener;
            //radioButton.setOnCheckedChangeListener(null);
            radioButton.setChecked(anioAcademicoUi.isToogle());
            itemView.setOnClickListener(this);
            txtNombre.setText(anioAcademicoUi.getNombre());

        }

        @Override
        public void onClick(View v) {
            listener.onClickAnioSelected(anioAcademicoUi);
        }
    }

    public interface Listener {
        void onClickAnioSelected(AnioAcademicoUi anioAcademicoUi);
    }
}
