package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.indicadorChooser;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CheckedObject;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 8/02/2018.
 */

public class IndicadorCampoTematicoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CampoAccionUi> items ;
    private CompeteneciaIndicadorAdapter.CompetenciaListener competenciaListener;

    public IndicadorCampoTematicoAdapter(List<CampoAccionUi> items, CompeteneciaIndicadorAdapter.CompetenciaListener competenciaListener) {
        this.items=items;
        this.competenciaListener = competenciaListener;
    }
    public static final int TYPE_CAMPOTEMATICO = 1;
    public static final int TYPE_CAMPOTEMATICO_PADRE = 2;
    public static final int TYPE_CAMPOTEMATICO_HIJO = 3;

    @Override
    public int getItemViewType(int position) {
        CampoAccionUi campoAccionUi = items.get(position);
        switch (campoAccionUi.getTipo()){
            case PARENT:
                return TYPE_CAMPOTEMATICO_PADRE;
            case CHILDREN:
                return TYPE_CAMPOTEMATICO_HIJO;
            case DEFAULD:
                return TYPE_CAMPOTEMATICO;
        }

        return 0;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        switch (viewType) {
            case TYPE_CAMPOTEMATICO_PADRE:
                View viewpadre = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crear_rubrica_seleccionar_campo_accion_padre, parent, false);
                holder = new CampoAccionPadre(viewpadre);
                break;
            case TYPE_CAMPOTEMATICO_HIJO:
                View viewhijo = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crear_rubrica_seleccionar_campo_accion_hijo, parent, false);
                holder = new CampoAccionHijo(viewhijo);
                break;
            default:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crear_rubrica_seleccionar_campo_accion, parent, false);
                holder = new CampoAccion(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CampoAccionUi campoAccionUi = items.get(position);
        switch (holder.getItemViewType()) {
            case TYPE_CAMPOTEMATICO_PADRE:
                ((CampoAccionPadre) holder).bind(campoAccionUi);
                break;
            case TYPE_CAMPOTEMATICO_HIJO:
                ((CampoAccionHijo) holder).bind(campoAccionUi,competenciaListener);
                break;
            case TYPE_CAMPOTEMATICO:
                ((CampoAccion) holder).bind(campoAccionUi,competenciaListener);
                break;
        }
    }

    public void setItems(List<CampoAccionUi> objects) {
        this.items.clear();
        this.items.addAll(objects);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class CampoAccionPadre extends RecyclerView.ViewHolder {

        @BindView(R.id.text_titulo)
        TextView textTitulo;

        CampoAccionPadre(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(CheckedObject checkedObject) {
            String title = checkedObject.getTitle();
            textTitulo.setText(Utils.capitalize(title));
        }

    }

    public static class CampoAccionHijo extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener{

        @BindView(R.id.chbx)
        CheckBox checkBox;
        @BindView(R.id.text_titulo)
        TextView textTitulo;
        private CampoAccionUi campoAccionUi;
        private CompeteneciaIndicadorAdapter.CompetenciaListener competenciaListener;

        public CampoAccionHijo(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(CampoAccionUi campoAccionUi, CompeteneciaIndicadorAdapter.CompetenciaListener competenciaListener) {
            this.competenciaListener= competenciaListener;
            Log.d(getClass().getSimpleName(), "campos tematicos ");
            this.campoAccionUi = campoAccionUi;
            boolean isChecked = campoAccionUi.isChecked();
            String title = campoAccionUi.getTitle();
            //in some cases, it will prevent unwanted situations
            checkBox.setOnCheckedChangeListener(null);
            checkBox.setChecked(isChecked);
            textTitulo.setText(title);
            checkBox.setOnCheckedChangeListener(this);

            switch (campoAccionUi.getTipo()){
                case PARENT:
                    checkBox.setVisibility(View.INVISIBLE);
                    break;
                case CHILDREN:
                    checkBox.setVisibility(View.VISIBLE);
                    break;
                case DEFAULD:
                    checkBox.setVisibility(View.VISIBLE);
                    break;
            }

        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            compoundButton.postDelayed(new Runnable() {
                @Override
                public void run() {
                    competenciaListener.onClickCampoAccion(campoAccionUi);
                }
            },200);
        }
    }

    public static class CampoAccion extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener{

        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.chbx)
        CheckBox checkBox;
        @BindView(R.id.text_titulo)
        TextView textTitulo;
        private CompeteneciaIndicadorAdapter.CompetenciaListener competenciaListener;
        private CampoAccionUi campoAccionUi;

        public CampoAccion(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(CampoAccionUi campoAccionUi, CompeteneciaIndicadorAdapter.CompetenciaListener competenciaListener) {
            this.competenciaListener = competenciaListener;
            this.campoAccionUi = campoAccionUi;
            boolean isChecked = campoAccionUi.isChecked();
            String title = campoAccionUi.getTitle();
            //in some cases, it will prevent unwanted situations
            checkBox.setOnCheckedChangeListener(null);
            checkBox.setChecked(isChecked);
            textTitulo.setText(Utils.capitalize(title));
            checkBox.setOnCheckedChangeListener(this);

            switch (campoAccionUi.getTipo()){
                case PARENT:
                    checkBox.setVisibility(View.INVISIBLE);
                    imageView.setVisibility(View.VISIBLE);
                    break;
                case CHILDREN:
                    checkBox.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.INVISIBLE);
                    break;
                case DEFAULD:
                    checkBox.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.VISIBLE);
                    break;
            }

        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            compoundButton.postDelayed(new Runnable() {
                @Override
                public void run() {
                    competenciaListener.onClickCampoAccion(campoAccionUi);
                }
            },200);

        }
    }
}
