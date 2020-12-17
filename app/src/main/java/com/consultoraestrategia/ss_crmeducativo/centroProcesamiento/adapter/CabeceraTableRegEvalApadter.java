package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.CabeceraUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CabeceraTableRegEvalApadter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CabeceraUi> cabeceraUiList = new ArrayList<>();
    private String color1;
    private String color2;
    private String color3;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case CabeceraUi.ALUMNO:
                return new AlumnoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cabecera_table_alumno_registo_evaluacion, parent, false));
            case CabeceraUi.COMPETENCIA:
                return new ColumnoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cabecera_table_registo_evaluacion, parent, false));
            case CabeceraUi.TITULO:
                return new TituloViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cabecera_table_titulo_evaluacion, parent, false));
            case CabeceraUi.TITULO_ALUMNO:
                return new TituloAlumnoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cabecera_table_titulo_alumno_evaluacion, parent, false));
            case CabeceraUi.ESPACIO_CALENDARIO:
                return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_espacio_bimestre_table_alumno_evaluacion, parent, false)){};
            case CabeceraUi.COMPETENCIA_FINAL:
                return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cabecera_table_final_registo_evaluacion, parent, false)){};
            case CabeceraUi.COMPETENCIA_FINAL_TITULO:
                return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cabecera_table_final_titulo_registo_evaluacion, parent, false)){};
            default:
                return new AlumnoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cabecera_table_alumno_registo_evaluacion, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case CabeceraUi.COMPETENCIA:
                ((ColumnoViewHolder)holder).bind(cabeceraUiList.get(position));
                break;
            case CabeceraUi.ALUMNO:
                ((AlumnoViewHolder)holder).bind(cabeceraUiList.get(position));
                break;
            case CabeceraUi.TITULO:
                ((TituloViewHolder)holder).bind(cabeceraUiList.get(position), color3);
                break;
            case CabeceraUi.TITULO_ALUMNO:
                ((TituloAlumnoViewHolder)holder).bind(color3);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return cabeceraUiList.size();
    }

    public void setList(List<CabeceraUi> cabeceraUiList) {
        this.cabeceraUiList.clear();
        this.cabeceraUiList.addAll(cabeceraUiList);
        notifyDataSetChanged();
    }
    public static class AlumnoViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_nota_evaluacion)
        TextView txtNotaEvaluacion;

        public AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(CabeceraUi cabeceraUi) {
            txtNotaEvaluacion.setText(cabeceraUi.getTitulo());
        }
    }

    public static class TituloAlumnoViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.view3)
        View view3;
        @BindView(R.id.view2)
        View view2;

        public TituloAlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(String color) {
            try {
                view2.setBackgroundColor(Color.parseColor(color));
                view3.setBackgroundColor(Color.parseColor(color));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static class ColumnoViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.root)
        View root;
        @BindView(R.id.txt_titulo)
        TextView txtTitulo;

        public ColumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(CabeceraUi cabeceraUi) {
            float whitch = (float)70.2* (float) (cabeceraUi.getRowSpan() > 0? cabeceraUi.getRowSpan(): 1);
            txtTitulo.setText(cabeceraUi.getTitulo());
            root.getLayoutParams().width = (int) Utils.convertDpToPixel(whitch, root.getContext());
        }
    }

    public static class TituloViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.root)
        View root;
        @BindView(R.id.view3)
        View view3;
        @BindView(R.id.view2)
        View view2;

        public TituloViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(CabeceraUi cabeceraUi, String color) {
            float whitch = (float)70.2f* (float) (cabeceraUi.getRowSpan() > 0? cabeceraUi.getRowSpan(): 1);
            ViewGroup.LayoutParams layoutParams = root.getLayoutParams();
            layoutParams.width = (int) Utils.convertDpToPixel(whitch, root.getContext());

            try {
                view2.setBackgroundColor(Color.parseColor(color));
                view3.setBackgroundColor(Color.parseColor(color));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return cabeceraUiList.get(position).getTipo();
    }

    public void setTema(String color1, String color2, String color3){
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
    }
}



