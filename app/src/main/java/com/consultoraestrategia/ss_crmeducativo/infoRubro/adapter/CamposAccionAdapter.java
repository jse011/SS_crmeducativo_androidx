package com.consultoraestrategia.ss_crmeducativo.infoRubro.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.CampoTematicoUi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kike on 08/05/2018.
 */

public class CamposAccionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int CHILDREN = 1,PARENT = 2, DEFAULD = 3;
    private List<CampoTematicoUi> items = new ArrayList<>();
    private boolean disabledCampoAccion;

    public CamposAccionAdapter(List<CampoTematicoUi> items) {
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
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_rub_tematico_padre, parent, false);
                return new ParentViewHolder(view);
            case CHILDREN:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_rub_tematico_hijo, parent, false);
                return new HijoViewHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_rub_tematico, parent, false);
                return new SimpleViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CampoTematicoUi campoAccionUi = items.get(position);
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

    public void setItems(List<CampoTematicoUi> objects) {
        this.items.clear();
        this.items.addAll(objects);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        CampoTematicoUi campoAccionUi = items.get(position);
        switch (campoAccionUi.getTipoCampoTematicoE()){
            case PARENT:
                return PARENT;
            case CHILDREN:
                return CHILDREN;
            case DEFAULT:
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
        private CampoTematicoUi checkedObject;
        public ParentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(CampoTematicoUi checkedObject) {
            this.checkedObject = checkedObject;
            String title = checkedObject.getTitulo();
            textTitulo.setText(title);


        }


    }

    public static class HijoViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener{
        @BindView(R.id.chbx)
        CheckBox checkBox;
        @BindView(R.id.text_titulo)
        TextView textTitulo;
        private CampoTematicoUi checkedObject;
        public HijoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(CampoTematicoUi checkedObject, boolean disabledCampoAccion) {
            this.checkedObject = checkedObject;
            //boolean isChecked = checkedObject.isChecked();
            String title = checkedObject.getTitulo();
            checkBox.setChecked(true);
            textTitulo.setText(title);
            checkBox.setOnCheckedChangeListener(this);
            checkBox.setEnabled(disabledCampoAccion);

        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            //checkedObject.setChecked(checkBox.isChecked());
        }
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener{

        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.chbx)
        CheckBox checkBox;
        @BindView(R.id.text_titulo)
        TextView textTitulo;
        private CampoTematicoUi checkedObject;
        public SimpleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(CampoTematicoUi checkedObject, boolean disabledCampoAccion) {
            this.checkedObject = checkedObject;
            //boolean isChecked = checkedObject.isChecked();
            String title = checkedObject.getTitulo();
            checkBox.setChecked(true);
            textTitulo.setText(title);
            checkBox.setOnCheckedChangeListener(this);
            checkBox.setEnabled(disabledCampoAccion);

        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            //checkedObject.setChecked(checkBox.isChecked());
        }
    }

}
