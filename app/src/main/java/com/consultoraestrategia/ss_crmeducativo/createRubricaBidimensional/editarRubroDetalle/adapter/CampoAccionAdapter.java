package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.adapter;

import androidx.recyclerview.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 8/02/2018.
 */

public class CampoAccionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int CHILDREN = 1,PARENT = 2, DEFAULD = 3;
    private List<CampoAccionUi> items = new ArrayList<>();
    private boolean disabledCampoAccion;

    public CampoAccionAdapter(List<CampoAccionUi> items) {
        this.items.clear();
        if (items != null) {
            this.items.addAll(items);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case PARENT:
                 view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_rub_tematico_padre_white, parent, false);
                return new ParentViewHolder(view);
            case CHILDREN:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_rub_tematico_hijo_white, parent, false);
                return new HijoViewHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_rub_tematico_white, parent, false);
                return new SimpleViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CampoAccionUi campoAccionUi = items.get(position);
        switch (holder.getItemViewType()){
            case PARENT:
                ((ParentViewHolder)holder).bind(campoAccionUi);
                break;
            case CHILDREN:
                ((HijoViewHolder)holder).bind(campoAccionUi,disabledCampoAccion);
                break;
            case DEFAULD:
                ((SimpleViewHolder)holder).bind(campoAccionUi,disabledCampoAccion);
                break;
        }

    }

    public void setItems(List<CampoAccionUi> objects) {
        this.items.clear();
        this.items.addAll(objects);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        CampoAccionUi campoAccionUi = items.get(position);
        switch (campoAccionUi.getTipo()){
            case PARENT:
                return PARENT;
            case CHILDREN:
                return CHILDREN;
            case DEFAULD:
                return DEFAULD;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setDisabledCampoAccion(boolean disabledCampoAccion) {
        this.disabledCampoAccion = disabledCampoAccion;
    }

    public static class ParentViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.text_titulo)
        TextView textTitulo;
        private CheckedObject checkedObject;
        public ParentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(CheckedObject checkedObject) {
            this.checkedObject = checkedObject;
            String title = checkedObject.getTitle();
            textTitulo.setText(title);


        }


    }

    public static class HijoViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener{
        @BindView(R.id.chbx)
        CheckBox checkBox;
        @BindView(R.id.text_titulo)
        TextView textTitulo;
        private CheckedObject checkedObject;
        public HijoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(CheckedObject checkedObject, boolean disabledCampoAccion) {
            this.checkedObject = checkedObject;
            boolean isChecked = checkedObject.isChecked();
            String title = checkedObject.getTitle();
            checkBox.setChecked(isChecked);
            textTitulo.setText(title);
            checkBox.setOnCheckedChangeListener(this);
            checkBox.setEnabled(disabledCampoAccion);

        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            checkedObject.setChecked(checkBox.isChecked());
        }
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener{

        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.chbx)
        CheckBox checkBox;
        @BindView(R.id.text_titulo)
        TextView textTitulo;
        private CheckedObject checkedObject;
        public SimpleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(CheckedObject checkedObject, boolean disabledCampoAccion) {
            this.checkedObject = checkedObject;
            boolean isChecked = checkedObject.isChecked();
            String title = checkedObject.getTitle();
            checkBox.setChecked(isChecked);
            textTitulo.setText(title);
            checkBox.setOnCheckedChangeListener(this);
            checkBox.setEnabled(disabledCampoAccion);

        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            checkedObject.setChecked(checkBox.isChecked());
        }
    }
}

