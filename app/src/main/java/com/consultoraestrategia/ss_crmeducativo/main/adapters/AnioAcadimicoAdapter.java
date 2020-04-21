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
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioRolGeoReferenciaUi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnioAcadimicoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TEXTO = 1, ANIOACADEMICO = 2;
    private List<Object> anioAcademicoUiList = new ArrayList<>();
    private Listener listener;

    public AnioAcadimicoAdapter(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
       if(type==ANIOACADEMICO){
           return new AnioAcademicoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_selecionar_anio_academico, viewGroup, false));
       }else{
            return new ColegioViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_selecionar_anio_academico_colegio, viewGroup, false));
       }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Object o = anioAcademicoUiList.get(i);
        switch (viewHolder.getItemViewType()){
            case ANIOACADEMICO:
                AnioAcademicoUi anioAcademicoUi = (AnioAcademicoUi) o;
                ((AnioAcademicoViewHolder)viewHolder).bind(anioAcademicoUi, listener);
                break;
            case TEXTO:
                UsuarioRolGeoReferenciaUi usuarioRolGeoReferenciaUi = (UsuarioRolGeoReferenciaUi) o;
                ((ColegioViewHolder)viewHolder).bind(usuarioRolGeoReferenciaUi);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return anioAcademicoUiList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object o = anioAcademicoUiList.get(position);
        if(o instanceof UsuarioRolGeoReferenciaUi){
            return TEXTO;
        }else if(o instanceof AnioAcademicoUi){
            return ANIOACADEMICO;
        }else {
            return 0;
        }
    }

    public void setList(List<Object> listAnioAcademico) {
        this.anioAcademicoUiList.clear();
        this.anioAcademicoUiList.addAll(listAnioAcademico);
        notifyDataSetChanged();
    }

    public static class AnioAcademicoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txt_nombre)
        TextView txtNombre;
        @BindView(R.id.radioButton)
        RadioButton radioButton;
        private AnioAcademicoUi anioAcademicoUi;
        private Listener listener;

        public AnioAcademicoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(AnioAcademicoUi anioAcademicoUi, Listener listener) {
            this.anioAcademicoUi = anioAcademicoUi;
            this.listener = listener;
            //radioButton.setOnCheckedChangeListener(null);
            radioButton.setChecked(anioAcademicoUi.isToogle());
            itemView.setOnClickListener(this);
            String anioAcademico = "Año Acad. "+anioAcademicoUi.getNombre();
            txtNombre.setText(anioAcademico);

        }

        @Override
        public void onClick(View v) {
            listener.onClickAnioSelected(anioAcademicoUi);
        }
    }

    public static class ColegioViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_colegio)
        TextView txtColegio;
        @BindView(R.id.txt_entidad)
        TextView txtEntidad;

        public ColegioViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(UsuarioRolGeoReferenciaUi usuarioRolGeoReferenciaUi) {
            txtColegio.setText(usuarioRolGeoReferenciaUi.getNombreGeoreferencia());
            txtEntidad.setText(usuarioRolGeoReferenciaUi.getNombreEntidad());
        }
    }

    public interface Listener {
        void onClickAnioSelected(AnioAcademicoUi anioAcademicoUi);
    }
}
