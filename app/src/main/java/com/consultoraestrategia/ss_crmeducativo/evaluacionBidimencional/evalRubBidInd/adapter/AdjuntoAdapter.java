package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.lib.imageViewZoom.ImageZomDialog;
import com.consultoraestrategia.ss_crmeducativo.lib.staticProgressBar.CustomProgress;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdjuntoAdapter extends RecyclerView.Adapter<AdjuntoAdapter.ViewHolder> {

    private List<ArchivoUi> tareaArchivoUiList = new ArrayList<>();
    private Listener listener;

    public AdjuntoAdapter(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_archivo_evaluacion, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(tareaArchivoUiList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return tareaArchivoUiList.size();
    }

    public void setList(List<ArchivoUi> list) {
        this.tareaArchivoUiList.clear();
        this.tareaArchivoUiList.addAll(list);
        notifyDataSetChanged();
    }

    public void add(ArchivoUi tareaArchivoUi) {
        this.tareaArchivoUiList.add(tareaArchivoUi);
        notifyItemInserted(tareaArchivoUiList.size()-1);
    }

    public void update(ArchivoUi tareaArchivoUi) {
        int position = tareaArchivoUiList.indexOf(tareaArchivoUi);
        if(position!=-1){
            tareaArchivoUiList.set(position, tareaArchivoUi);
            notifyItemChanged(position);
        }
    }

    public void remove(ArchivoUi tareaArchivoUi) {
        int position = tareaArchivoUiList.indexOf(tareaArchivoUi);
        if(position!=-1){
            tareaArchivoUiList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imgRecurso)
        ImageView imgRecurso;
        @BindView(R.id.conten_recurso)
        ConstraintLayout contenRecurso;
        @BindView(R.id.txtNombreRecurso)
        TextView txtNombreRecurso;
        @BindView(R.id.txtdescripcion)
        TextView txtdescripcion;
        @BindView(R.id.card_view)
        CardView cardView;
        @BindView(R.id.btn_recurso)
        ImageView btnRecurso;
        @BindView(R.id.customProgress)
        CustomProgress customProgress;
        @BindView(R.id.progress)
        FrameLayout progress;
        private Listener listener;
        private ArchivoUi tareaArchivoUi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ArchivoUi tareaArchivoUi, Listener listener) {
            this.listener = listener;
            this.tareaArchivoUi = tareaArchivoUi;
            cardView.setOnClickListener(this);
            btnRecurso.setOnClickListener(this);

            txtNombreRecurso.setText(tareaArchivoUi.getNombre());
            txtdescripcion.setText(tareaArchivoUi.getUrl());
            if(tareaArchivoUi.getUri()!=null){
                this.progress.setVisibility(View.GONE);
                //btnRecurso.setVisibility(View.VISIBLE);
                customProgress.setVisibility(View.VISIBLE);


                float progress = (float) tareaArchivoUi.getProgress()/(float) 100;
                customProgress.setMaximumPercentage(progress);
                customProgress.setDisabledMovementProgress(true);
                customProgress.setProgressBackgroundColor(Color.TRANSPARENT);

                customProgress.updateView();
            }else {
                //btnRecurso.setVisibility(View.GONE);
                this.progress.setVisibility(View.GONE);
                customProgress.setVisibility(View.GONE);
            }
            imgRecurso.setImageResource(R.drawable.ext_img);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_recurso:
                    listener.onClickRemoveTareaArchivo(tareaArchivoUi);
                    break;
                default:
                   listener.onClickTareaArchivo(tareaArchivoUi);
                    break;
            }
        }
    }


    public interface Listener{

        void onClickRemoveTareaArchivo(ArchivoUi archivoUi);

        void onClickTareaArchivo(ArchivoUi tareaArchivoUi);
    }
}
