package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.adapter;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.adapter.holder.AsistenciaGeneradaHolder;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.adapter.holder.AsistenciaNoGeneradaHolder;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.adapter.holder.AsistenciaSemanaHolder;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaBannerUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.FechaAsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.listener.AsistenciaCursoListener;
import com.consultoraestrategia.ss_crmeducativo.util.parallaxrecyclerview.ParallaxImageView;
import com.consultoraestrategia.ss_crmeducativo.util.parallaxrecyclerview.ParallaxViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsistenciaCursoListaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BANNER = 0, ITEM = 1, TITULO = 2, GENERADO = 3, NO_GENERADO = 4, SEMANA = 5;

    private List<Object> asistenciaUiList = new ArrayList<>();

    private AsistenciaCursoListener asistenciaCursoListener;

    private int calendarioPeriodoId;

    private int getCalendarioPeriodoSelectId;


    public AsistenciaCursoListaAdapter(List<Object> asistenciaUiList, AsistenciaCursoListener asistenciaCursoListener, int calendarioPeriodoId) {
        this.asistenciaUiList.addAll(asistenciaUiList);
        this.asistenciaCursoListener = asistenciaCursoListener;
        this.calendarioPeriodoId = calendarioPeriodoId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case GENERADO:
                return new AsistenciaGeneradaHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asistencia_curso_generado, parent, false));
            case NO_GENERADO:
                return new AsistenciaNoGeneradaHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asistencia_no_generado, parent, false));
           case SEMANA:
                return new AsistenciaSemanaHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asistencia_semana, parent, false));
            default:
                return new AsistenciaBannerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asistencia_banner, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object o = asistenciaUiList.get(position);
        switch (holder.getItemViewType()) {
            case GENERADO:
                ((AsistenciaGeneradaHolder) holder).bind(((FechaAsistenciaUi) o), asistenciaCursoListener, calendarioPeriodoId, getCalendarioPeriodoSelectId);
                break;
            case NO_GENERADO:
                ((AsistenciaNoGeneradaHolder) holder).bind(((FechaAsistenciaUi) o), asistenciaCursoListener, calendarioPeriodoId, getCalendarioPeriodoSelectId);
                break;
            case SEMANA:
                ((AsistenciaSemanaHolder) holder).bind((String) o);
                break;
            default:
                if (o instanceof AsistenciaBannerUi) {
                    ((AsistenciaBannerHolder) holder).bind((AsistenciaBannerUi) o);
                }
                break;
        }
    }


    @Override
    public int getItemCount() {
        return asistenciaUiList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object o = asistenciaUiList.get(position);
        if (o instanceof FechaAsistenciaUi) {
            FechaAsistenciaUi fechaAsistenciaUi = (FechaAsistenciaUi) o;
            if (fechaAsistenciaUi.getTipo() != null)
                switch (fechaAsistenciaUi.getTipo()) {
                    case GENERADO:
                        return GENERADO;
                    case NO_GENERADO:
                        return NO_GENERADO;
                }
        }

        if (o instanceof String) {
            return SEMANA;
        } else {
            return BANNER;
        }
    }

    public void setList(List<Object> asistenciaUiList, int calendarioPeriodoId) {

        this.asistenciaUiList.clear();
        this.asistenciaUiList.addAll(asistenciaUiList);
        this.getCalendarioPeriodoSelectId = calendarioPeriodoId;
        notifyDataSetChanged();
    }

    static class AsistenciaBannerHolder extends ParallaxViewHolder {
        @BindView(R.id.backgroundImage)
        ParallaxImageView backgroundImage;
        @BindView(R.id.txt_titulo)
        TextView txtTitulo;

        @Override
        public int getParallaxImageId() {
            return R.id.backgroundImage;
        }

        AsistenciaBannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(AsistenciaBannerUi asistenciaBannerUi) {
            try {
                int recuros;
                switch (asistenciaBannerUi){
                    case ENERO:
                        recuros = R.drawable.january;
                        break;
                    case FEBRERO:
                        recuros = R.drawable.february;
                        break;
                    case MARZO:
                        recuros =  R.drawable.march;
                        break;
                    case ABRIL:
                        recuros = R.drawable.april;
                        break;
                    case MAYO:
                        recuros = R.drawable.may;
                        break;
                    case JUNIO:
                        recuros = R.drawable.january;
                        break;
                    case JULIO:
                        recuros =  R.drawable.july;
                        break;
                    case AGOSTO:
                        recuros =  R.drawable.august;
                        break;
                    case SETIEMBRE:
                        recuros = R.drawable.september;
                        break;
                    case OCTUBRE:
                        recuros =  R.drawable.october;
                        break;
                    case NOVIEMBRE:
                        recuros = R.drawable.november;
                        break;
                    case DICIEMBRE:
                        recuros = R.drawable.december;
                        break;
                    default:
                        recuros =  R.drawable.april;
                        break;

                }
                Glide.with(itemView.getContext())
                        .load(recuros)
                        .into(backgroundImage);
                String titulo = asistenciaBannerUi.getTitulo();
                txtTitulo.setText(titulo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }




}
