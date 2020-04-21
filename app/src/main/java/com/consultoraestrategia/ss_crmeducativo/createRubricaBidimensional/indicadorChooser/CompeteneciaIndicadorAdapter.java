package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.indicadorChooser;

import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
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
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 7/02/2018.
 */

public class CompeteneciaIndicadorAdapter extends SectioningAdapter {

    private static final String TAG = CompeteneciaIndicadorAdapter.class.getSimpleName();

    public static class CompetenciaViewHolder extends HeaderViewHolder {

        @BindView(R.id.textTitle)
        TextView txtTitle;

        public CompetenciaViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(CompetenciaUi competencia) {
            Log.d(TAG, "competencia "+ competencia.getTitle());
            txtTitle.setText(competencia.getTitle());

        }
    }

    public static class CapacidadViewHolder extends ItemViewHolder {

        @BindView(R.id.txtTitle)
        TextView txtTitle;

        public CapacidadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(CapacidadUi capacidadUi) {
            Log.d(TAG, "capacidadUi "+ capacidadUi.getTitle());
            txtTitle.setText(capacidadUi.getTitle());
        }
    }

    public static class IndicadorViewHolder extends ItemViewHolder implements CompoundButton.OnCheckedChangeListener {

        @BindView(R.id.chbx)
        CheckBox checkBox;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.rc_campos_accion)
        RecyclerView rcCamposAccion;
        @BindView(R.id.text_titulo)
        TextView textTitulo;

        private IndicadorUi indicadorUi;
        private CompetenciaListener competenciaListener;
        private IndicadorCampoTematicoAdapter adapter;

        public IndicadorViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(final IndicadorUi indicadorUi, CompetenciaListener competenciaListener) {
            this.indicadorUi = indicadorUi;
            this.competenciaListener = competenciaListener;
            setView();
            ((SimpleItemAnimator) rcCamposAccion.getItemAnimator()).setSupportsChangeAnimations(false);
            adapter = new IndicadorCampoTematicoAdapter(indicadorUi.getCampoAccionList(),competenciaListener);
            rcCamposAccion.setLayoutManager(new LinearLayoutManager(imageView.getContext()));
            rcCamposAccion.setAdapter(adapter);


        }

        private void setView() {
            boolean isChecked = indicadorUi.isChecked();
            String title = indicadorUi.getTitulo();
            //in some cases, it will prevent unwanted situations
            checkBox.setOnCheckedChangeListener(null);
            checkBox.setChecked(isChecked);
            Log.d(TAG,"title INDICADOR  " +title);
            textTitulo.setText(title);
            checkBox.setOnCheckedChangeListener(this);

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

                }else if(competenciaUi.isEnfoque()){
                    drawable = ContextCompat.getDrawable(itemView.getContext(),  R.drawable.ic_enfoque_1);
                }else if(competenciaUi.isTrans()){
                    drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_transversal);
                }else {
                    drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_speedometer);
                }

                if (drawable != null)imageView.setImageDrawable(drawable);
            }catch (Exception e){
                e.printStackTrace();
            }

            imageView.setVisibility(View.VISIBLE);
        }

        void notifyChanged() {
            setView();
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            competenciaListener.onClickIndicador(indicadorUi);
        }
    }

    private List<CompetenciaUi> competenciaList = new ArrayList<>();
    private CompetenciaListener competenciaListener;

    public CompeteneciaIndicadorAdapter(List<CompetenciaUi> competenciaList, CompetenciaListener competenciaListener) {
        this.competenciaList.clear();
        this.competenciaList.addAll(competenciaList);
        this.competenciaListener = competenciaListener;
        notifyDataSetChanged();
    }

    /**
     * @return Number of sections
     */
    @Override
    public int getNumberOfSections() {
        return competenciaList.size();
    }

    /**
     * @param sectionIndex index of the section in question
     * @return the number of items in the specified section
     */
    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        Log.d(TAG, "getNumberOfItemsInSection");
        int numberOfItemsInSection = competenciaList.get(sectionIndex).getItems().size();
        Log.d(TAG, "numberOfItemsInSection: " + numberOfItemsInSection);
        return numberOfItemsInSection;
    }

    /**
     * @param sectionIndex index of the section in question
     * @return true if this section has a header
     */
    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public boolean doesSectionHaveFooter(int sectionIndex) {
        return false;
    }

    public static final int TYPE_CAPACIDAD = 6;
    public static final int TYPE_INDICADOR = 7;


    @Override
    public int getSectionItemUserType(int sectionIndex, int itemIndex) {
        CompetenciaUi competencia = competenciaList.get(sectionIndex);
        Object object = competencia.getItems().get(itemIndex);
        if (object instanceof CapacidadUi) {
            return TYPE_CAPACIDAD;
        }
        return TYPE_INDICADOR;
    }


    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemUserType) {
        ItemViewHolder holder;
        switch (itemUserType) {
            case TYPE_CAPACIDAD:
                View viewCapacidad = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, parent, false);
                holder = new CapacidadViewHolder(viewCapacidad);
                break;
            default:
                View viewIndicador = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkbox_indicador, parent, false);
                holder = new IndicadorViewHolder(viewIndicador);
                break;
        }
        return holder;
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerUserType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.activity_createrubbid_itemheader_competencia, parent, false);
        return new CompetenciaViewHolder(v);
    }

    @Override
    public GhostHeaderViewHolder onCreateGhostHeaderViewHolder(ViewGroup parent) {
        final View ghostView = new View(parent.getContext());
        ghostView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return new GhostHeaderViewHolder(ghostView);
    }

    @Override
    public void onBindItemViewHolder(ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemType) {
        CompetenciaUi competencia = competenciaList.get(sectionIndex);
        switch (itemType) {
            case TYPE_CAPACIDAD:
                CapacidadUi capacidadUi = (CapacidadUi) competencia.getItems().get(itemIndex);
                ((CapacidadViewHolder) viewHolder).bind(capacidadUi);
                break;
            default:
                IndicadorUi indicadorUi = (IndicadorUi) competencia.getItems().get(itemIndex);
                ((IndicadorViewHolder) viewHolder).bind(indicadorUi,competenciaListener);
                break;
        }
    }

    @Override
    public void onBindHeaderViewHolder(HeaderViewHolder viewHolder, int sectionIndex, int headerType) {
        CompetenciaUi competencia = competenciaList.get(sectionIndex);
        CompetenciaViewHolder hvh = (CompetenciaViewHolder) viewHolder;
        String title = competencia.getTitle();
        Log.d(TAG , "onBindHeaderViewHolder "+ competencia.getTitle());
        hvh.txtTitle.setText(title);
    }

    public interface CompetenciaListener{
        void onClickCampoAccion(CampoAccionUi campoAccionUi);
        void onClickIndicador(IndicadorUi indicadorUi);
    }


    void notifyAllChildChanged(final RecyclerView recyclerView) {
        for (int i=0; i<recyclerView.getChildCount(); i++){
            RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
            if(holder instanceof IndicadorViewHolder){
                IndicadorViewHolder indicadorViewHolder =  (IndicadorViewHolder)holder;
                indicadorViewHolder.notifyChanged();
            }
        }
    }

    public void updateItemsectionIndex(CompetenciaUi competenciaUi){
        int sectionIndex =  this.competenciaList.indexOf(competenciaUi);
        if(sectionIndex!=-1){
            this.competenciaList.set(sectionIndex, competenciaUi);
            notifySectionDataSetChanged(sectionIndex);
        }
    }

    public void updateItemIndex(CompetenciaUi competenciaUi,IndicadorUi indicadorUi){
        int sectionIndex =  this.competenciaList.indexOf(competenciaUi);
        if(sectionIndex!=-1){
            this.competenciaList.set(sectionIndex, competenciaUi);
            //notifySectionDataSetChanged(sectionIndex);
            int itemIndex = competenciaUi.getCapacidadList()==null?-1:competenciaUi.getItems().indexOf(indicadorUi);
            if(itemIndex!=-1){
                competenciaUi.getItems().set(itemIndex, indicadorUi);
                notifySectionItemChanged(sectionIndex, itemIndex);
            }
        }

    }

    public void updateItemIndexStatus(CompetenciaUi competenciaUi, IndicadorUi indicadorUi, CampoAccionUi campoAccionUi) {
        int sectionIndex =  this.competenciaList.indexOf(competenciaUi);
        if(sectionIndex!=-1){
            this.competenciaList.set(sectionIndex, competenciaUi);
            //notifySectionDataSetChanged(sectionIndex);
            int itemIndex = competenciaUi.getCapacidadList()==null?-1:competenciaUi.getItems().indexOf(indicadorUi);
            if(itemIndex!=-1){
                competenciaUi.getItems().set(itemIndex, indicadorUi);
                notifySectionItemChanged(sectionIndex, itemIndex);
            }
        }
    }


    public void setList(List<CompetenciaUi> competenciaList) {
        this.competenciaList.clear();
        this.competenciaList.addAll(competenciaList);
        notifyAllSectionsDataSetChanged();
    }
}
