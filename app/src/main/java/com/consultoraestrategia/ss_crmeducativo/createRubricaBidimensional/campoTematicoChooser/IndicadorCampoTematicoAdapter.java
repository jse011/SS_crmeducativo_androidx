package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.campoTematicoChooser;

import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CheckedObject;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 8/02/2018.
 */

public class IndicadorCampoTematicoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items ;

    public IndicadorCampoTematicoAdapter(List<Object> items) {
        this.items=items;
    }
    public static final int TYPE_INDICADOR = 1;
    public static final int TYPE_CAMPOTEMATICO = 2;

    @Override
    public int getItemViewType(int position) {
        Object object = items.get(position);
        if (object instanceof IndicadorUi) {
            Log.d(getClass().getSimpleName(), " indicador");
            return TYPE_INDICADOR;
        }else {
            Log.d(getClass().getSimpleName(), " campo tematico");
            return TYPE_CAMPOTEMATICO;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        switch (viewType) {
            case TYPE_INDICADOR:
                View viewCapacidad = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title_imagen, parent, false);
                holder = new TitleViewHolder(viewCapacidad);
                break;
            default:
                View viewIndicador = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkbox_indicador, parent, false);
                holder = new CheckboxViewHolder(viewIndicador);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        switch (itemViewType) {
            case TYPE_INDICADOR:
                IndicadorUi indicador = (IndicadorUi) items.get(position);
                Log.d(getClass().getSimpleName(),indicador.getTitulo()+ " alias "+  indicador.getAlias());
                ((TitleViewHolder) holder).bind(indicador);
                break;
            default:
                CampoAccionUi campotematico = (CampoAccionUi) items.get(position);
                ((CheckboxViewHolder) holder).bind(campotematico);
                break;
        }
    }

    public void setItems(List<Object> objects) {
        this.items.clear();
        this.items.addAll(objects);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtTitle)
        TextView txtTitle;;
        @BindView(R.id.imageView)
        ImageView imageView;

        public TitleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(IndicadorUi indicadorUi) {
            String title = indicadorUi.getTitulo();
            Log.d(getClass().getSimpleName(), "indicador title "+ title);
            if (!TextUtils.isEmpty(title)) {
                txtTitle.setText(title);
                try {
                    Drawable drawable = null;
                    CompetenciaUi competenciaUi = indicadorUi.getCompetenciaOwner();
                    if(competenciaUi.isBase()){
                        switch (indicadorUi.getTipoIndicadorUi()) {
                            case SER:
                                Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                                        .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_speedometer)).into(imageView);
                                break;
                            case HACER:
                                Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                                        .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_speedometer)).into(imageView);
                                break;
                            case SABER:
                                Glide.with(itemView.getContext()).load(indicadorUi.getUrl())
                                        .apply(Utils.getGlideRequestOptionsSimple().error(R.drawable.ic_speedometer)).into(imageView);
                                break;
                            case DEFAULT:
                                drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_speedometer);
                                break;
                            default:
                                drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_speedometer);
                                break;
                        }
                        Log.d(getClass().getSimpleName(), "indicadorUi tipo "+ indicadorUi.getTipoIndicadorUi().toString());
                    }else if(competenciaUi.isEnfoque()){
                        drawable = ContextCompat.getDrawable(itemView.getContext(),  R.drawable.ic_enfoque_1);
                    }else if(competenciaUi.isTrans()){
                        drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_transversal);
                    }else {
                        drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_speedometer);
                    }

                    if (drawable!=null)imageView.setImageDrawable(drawable);
                }catch (Exception e){
                    e.printStackTrace();
                }
                imageView.setVisibility(View.VISIBLE);
            }
        }
    }

    public static class CheckboxViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener{

        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.chbx)
        CheckBox checkBox;
        @BindView(R.id.text_titulo)
        TextView textTitulo;
        private CheckedObject checkedObject;
        public CheckboxViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(CheckedObject checkedObject) {
            Log.d(getClass().getSimpleName(), "campos tematicos ");
            this.checkedObject = checkedObject;
            boolean isChecked = checkedObject.isChecked();
            String title = checkedObject.getTitle();
            checkBox.setChecked(isChecked);
            textTitulo.setText(title);
            Drawable drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_book);
            imageView.setImageDrawable(drawable);
            checkBox.setOnCheckedChangeListener(this);

            CampoAccionUi campoAccionUi = null;
            if(checkedObject instanceof CampoAccionUi){
                campoAccionUi = (CampoAccionUi)checkedObject;
            }
            if(campoAccionUi == null)return;
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
            checkedObject.setChecked(checkBox.isChecked());
        }
    }
}
