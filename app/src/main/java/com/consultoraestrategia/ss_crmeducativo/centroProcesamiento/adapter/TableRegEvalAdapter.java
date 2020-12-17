package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.CabeceraUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.CellTableRegEvalUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.ColumnTableRegEvalUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.RowTableRegEvalUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import android.view.animation.AnimationUtils;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class TableRegEvalAdapter extends AbstractTableAdapter<ColumnTableRegEvalUi, RowTableRegEvalUi, CellTableRegEvalUi> {

    private String color1;
    private String color2;
    private String color3;

    public TableRegEvalAdapter(Context context) {
        super(context);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        return mColumnHeaderItems.get(position).getTipo();
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return  0;
    }

    @Override
    public int getCellItemViewType(int position) {
        return mCellItems.get(0).get(position).getTipo();
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case CellTableRegEvalUi.ALUMNO:
                return new AlumnoCellHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_alumno_registro_evaluacion, parent, false));
            case CellTableRegEvalUi.NOTA:
                return new NotaCellHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_registro_evaluacion, parent, false));
            case CellTableRegEvalUi.ESPACIO_CALENDARIO:
                return new EspacioBimestre(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_espacio_bimestre_table_alumno_evaluacion, parent, false)){};
            default:
                return new CellHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.table_cell_registro_evaluacion, parent, false));

        }

    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel, int columnPosition, int rowPosition) {
        switch (holder.getItemViewType()){
            case CellTableRegEvalUi.ALUMNO:
                AlumnoCellHolder alumnoCellHolder = (AlumnoCellHolder)holder;
                alumnoCellHolder.bind(mCellItems.get(rowPosition).get(columnPosition));
                break;
            case CellTableRegEvalUi.NOTA:
                NotaCellHolder notaCellHolder = (NotaCellHolder)holder;
                notaCellHolder.bind(mCellItems.get(rowPosition).get(columnPosition), color3);
                break;
        }
    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case ColumnTableRegEvalUi.ALUMNO:
                return new ColumnHolder(LayoutInflater.from(mContext).inflate(R.layout.table_column_alumno_registro_evaluacion, parent,false));
            case ColumnTableRegEvalUi.NOTA:
                return new NotaColumnHolder(LayoutInflater.from(mContext).inflate(R.layout.table_column_registro_evaluacion, parent,false));
            case ColumnTableRegEvalUi.FINAL:
                return new FinalColumnHolder(LayoutInflater.from(mContext).inflate(R.layout.table_column_final_registro_evaluacion, parent,false));
            case ColumnTableRegEvalUi.ESPACIO_CALENDARIO:
                return new EspacioBimestre(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_espacio_bimestre_table_alumno_evaluacion, parent, false)){};
            default:
                return new NotaColumnHolder(LayoutInflater.from(mContext).inflate(R.layout.table_column_registro_evaluacion, parent,false));
        }

    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object columnHeaderItemModel, int columnPosition) {
        switch (holder.getItemViewType()){
            case ColumnTableRegEvalUi.ALUMNO:
                ColumnHolder columnHolder = (ColumnHolder)holder;
                columnHolder.bind(color3);
                break;
            case ColumnTableRegEvalUi.NOTA:
                NotaColumnHolder notaColumnHolder = (NotaColumnHolder)holder;
                notaColumnHolder.bind(mColumnHeaderItems.get(columnPosition), color3);
                break;
            case ColumnTableRegEvalUi.FINAL:
                FinalColumnHolder finalColumnHolder = (FinalColumnHolder)holder;
                finalColumnHolder.bind(mColumnHeaderItems.get(columnPosition), color3);
                break;

        }
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        return new RowHolder(LayoutInflater.from(mContext).inflate(R.layout.table_row_registro_evaluacion, parent,false));
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel, int rowPosition) {
        ((RowHolder)holder).bind(mRowHeaderItems.get(rowPosition), rowPosition, color1, color2, color3);
    }

    @Override
    public View onCreateCornerView() {
        CornerHolder cornerHolder = new CornerHolder(LayoutInflater.from(mContext).inflate(R.layout.table_corner_registro_evaluacion, null));
        cornerHolder.bind(color1);
        return cornerHolder.getView();
    }

    public void setTema(String color1, String color2, String color3) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
    }


    public static class FinalColumnHolder extends AbstractViewHolder {
        @BindView(R.id.titulo)
        TextView titulo;
        @BindView(R.id.view2)
        View view2;
        public FinalColumnHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ColumnTableRegEvalUi columnTableRegEvalUi, String color1){
            titulo.setText(columnTableRegEvalUi.getTitulo());
            try {
                view2.setBackgroundColor(Color.parseColor(color1));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static class NotaColumnHolder extends AbstractViewHolder {
        @BindView(R.id.root)
        View root;
        @BindView(R.id.fondo)
        View fondo;
        @BindView(R.id.titulo)
        TextView titulo;
        @BindView(R.id.view)
        View view;
        @BindView(R.id.view2)
        View view2;
        public NotaColumnHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ColumnTableRegEvalUi columnTableRegEvalUi, String color1){
            if(columnTableRegEvalUi.getParendId()>0){
                titulo.setText(columnTableRegEvalUi.getTitulo());
                fondo.setBackgroundColor(Color.TRANSPARENT);
            }else {
                titulo.setText("Promedio");
                fondo.setBackgroundColor(Color.parseColor("#57D1CDCD"));
            }

            try {
                view.setBackgroundColor(Color.parseColor(color1));
                view2.setBackgroundColor(Color.parseColor(color1));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static class ColumnHolder extends AbstractViewHolder {
        @BindView(R.id.view)
        View view;
        @BindView(R.id.view2)
        View view2;
        public ColumnHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(String color1){
            try {
                view.setBackgroundColor(Color.parseColor(color1));
                view2.setBackgroundColor(Color.parseColor(color1));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static class RowHolder extends AbstractViewHolder {
        @BindView(R.id.txt_numero)
        TextView txtNumero;
        @BindView(R.id.imgProfile)
        ImageView imgProfile;
        @BindView(R.id.view3)
        View view3;
        @BindView(R.id.fondo)
        View fondo;
        @BindView(R.id.content)
        CardView contentImagen;

        public RowHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(RowTableRegEvalUi rowTableRegEvalUi, int rowPosition, String color1, String color2, String color3){
            txtNumero.setText(String.valueOf(rowPosition+1)+".");
            Glide.with(imgProfile)
                    .load(rowTableRegEvalUi.getFoto())
                    .apply(Utils.getGlideRequestOptions())
                    .into(imgProfile);

            try {
                view3.setBackgroundColor(Color.parseColor(color3));

                if(rowTableRegEvalUi.getVigencia()){
                    fondo.setBackgroundColor(Color.parseColor(color2));
                }else {
                    fondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_red_600));
                    fondo.setAlpha(0.7f);
                }

                contentImagen.setCardBackgroundColor(Color.parseColor(color3));
            }catch (Exception e){
                e.printStackTrace();
                fondo.setBackgroundColor(Color.TRANSPARENT);
            }



        }
    }
    public static class CellHolder extends AbstractViewHolder {
        public CellHolder(View itemView) {
            super(itemView);
        }
    }

    public static class NotaCellHolder extends AbstractViewHolder {
        @BindView(R.id.text_nota)
        TextView textNota;
        @BindView(R.id.fondo)
        View fondo;
        @BindView(R.id.icono_N_vigente)
        View icono_N_vigente;
        @BindView(R.id.icono_cerrado)
        View icono_cerrado;

        public NotaCellHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(CellTableRegEvalUi cellTableRegEvalUi, String colorFondo) {
            if(cellTableRegEvalUi.isNotaNoGenerada()){
                textNota.setVisibility(View.GONE);
                icono_cerrado.setVisibility(View.GONE);
                icono_N_vigente.setVisibility(View.GONE);

                try {
                    fondo.setBackgroundColor(Color.parseColor(colorFondo));
                }catch (Exception e){
                    e.printStackTrace();
                    fondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_grey_200));
                }

            }else {
                textNota.setVisibility(View.VISIBLE);
                icono_cerrado.setVisibility(View.VISIBLE);
                icono_N_vigente.setVisibility(View.VISIBLE);
                fondo.setBackgroundColor(Color.TRANSPARENT);


                if(cellTableRegEvalUi.getTipoId()==410){

                    int color = 0;
                    if (cellTableRegEvalUi.getNota() < 10.5 && cellTableRegEvalUi.getOrden() > 1) {
                        color = ContextCompat.getColor(itemView.getContext(), R.color.md_red_A700);
                    }else if (cellTableRegEvalUi.getNota()>= 10.5 && cellTableRegEvalUi.getOrden() > 1) {
                        color = ContextCompat.getColor(itemView.getContext(), R.color.md_blue_900);
                    }else {
                        color = Color.BLACK;
                    }

                    textNota.setTextColor(color);

                    if (cellTableRegEvalUi.getOrden() == 3) {
                        String nota = String.format("%.0f", 0.0);
                        if (cellTableRegEvalUi.getNota() != 0) {
                            nota = String.format("%.0f", cellTableRegEvalUi.getNota());
                        }
                        textNota.setText(nota);
                        //html += '<span style="color: ' + mstr_Color + '">' + er.Nota.toFixed(0) + '</span>';
                    }
                    else {
                        String nota = String.format("%.1f", 0.0);
                        if (cellTableRegEvalUi.getNota() != 0) {
                            nota = String.format("%.1f", cellTableRegEvalUi.getNota());
                        }
                        textNota.setText(nota);
                        //html += '<span style="color: ' + mstr_Color + '">' + er.Nota + '</span>';
                    }


                }else if(cellTableRegEvalUi.getTipoId()==412) {
                    int color = 0;
                    if (("B".equals(cellTableRegEvalUi.getTituloNota()) || "C".equals(cellTableRegEvalUi.getTituloNota())) && cellTableRegEvalUi.getOrden() > 1) {
                        color = ContextCompat.getColor(itemView.getContext(), R.color.md_red_A700);
                    }else if (("AD".equals(cellTableRegEvalUi.getTituloNota()) || "A".equals(cellTableRegEvalUi.getTituloNota())) && cellTableRegEvalUi.getOrden() > 1) {
                        color = ContextCompat.getColor(itemView.getContext(), R.color.md_blue_900);
                    }else {
                        color = Color.BLACK;
                    }
                    textNota.setTextColor(color);
                    textNota.setText(cellTableRegEvalUi.getTituloNota());
                }else {
                    String nota = String.format("%.1f", 0.0);
                    if (cellTableRegEvalUi.getNota() != 0) {
                        nota = String.format("%.1f", cellTableRegEvalUi.getNota());
                    }
                    textNota.setText(nota);
                    textNota.setTextColor(Color.BLACK);
                }

                if(cellTableRegEvalUi.getParentId()==0&&cellTableRegEvalUi.getCompetenciaId() == 0){
                    if(cellTableRegEvalUi.isAlumnoVigencia()){
                        fondo.setBackgroundColor(Color.parseColor("#A3B8B8B8"));
                    }else {
                        fondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_red_600));
                        fondo.setAlpha(0.7f);
                    }


                    textNota.setTypeface(null, Typeface.BOLD);
                }else if(cellTableRegEvalUi.getParentId()==0){
                    if(cellTableRegEvalUi.isAlumnoVigencia()){
                        fondo.setBackgroundColor(Color.parseColor("#57D1CDCD"));
                    }else {
                        fondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_red_600));
                        fondo.setAlpha(0.7f);
                    }


                    textNota.setTypeface(null, Typeface.BOLD);
                }else {
                    if(cellTableRegEvalUi.isAlumnoVigencia()){
                        fondo.setBackgroundColor(Color.TRANSPARENT);
                    }else {
                        fondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_red_600));
                        fondo.setAlpha(0.7f);
                    }


                    textNota.setTypeface(null, Typeface.NORMAL);
                }

                if(cellTableRegEvalUi.isBimestrNoVigente()){

                    icono_N_vigente.setVisibility(View.VISIBLE);
                }else {

                    icono_N_vigente.setVisibility(View.GONE);
                }

                if(cellTableRegEvalUi.isBimestrCerrado()){

                    icono_cerrado.setVisibility(View.VISIBLE);
                }else {

                    icono_cerrado.setVisibility(View.GONE);
                }
            }
        }


    }

    public static class AlumnoCellHolder extends AbstractViewHolder {
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtSubtittle)
        TextView txtSubtittle;
        @BindView(R.id.fondo)
        View fondo;

        public AlumnoCellHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(CellTableRegEvalUi cellTableRegEvalUi) {
            RowTableRegEvalUi rowTableRegEvalUi = cellTableRegEvalUi.getAlumnoUi();
            txtTitle.setText(rowTableRegEvalUi.getNombre());
            txtSubtittle.setText(rowTableRegEvalUi.getApellidos());
            if(rowTableRegEvalUi.getVigencia()){
                fondo.setBackgroundColor(Color.TRANSPARENT);
            }else {
                fondo.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.md_red_600));
                fondo.setAlpha(0.7f);
            }
        }
    }
    public static class CornerHolder {
        @BindView(R.id.root)
        View root;

        private final View view;

        private CornerHolder(View view) {
            this.view = view;
            ButterKnife.bind(this, view);
        }

        public void bind(String color){
            try {
                root.setBackgroundColor(Color.parseColor(color));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public View getView() {
            return view;
        }
    }

    public static class EspacioBimestre extends AbstractViewHolder {
        public EspacioBimestre(View itemView) {
            super(itemView);
        }
    }
}
