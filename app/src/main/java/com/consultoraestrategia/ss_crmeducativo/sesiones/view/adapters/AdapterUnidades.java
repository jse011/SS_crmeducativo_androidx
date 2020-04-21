package com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ui.ImportarActivity;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadAprendizajeUi;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AdapterUnidades extends RecyclerView.Adapter<AdapterUnidades.ViewHolder>  {

    private List<UnidadAprendizajeUi> vlst_unidadAprendizaje;

    private int vint_backgroudColor;
    public UnidadesListener mListener;
    private int programaEducativoId;

    public static String TAG = AdapterUnidades.class.getSimpleName();

    public void updateItem(UnidadAprendizajeUi unidadAprendizajeUi) {
        int position = vlst_unidadAprendizaje.indexOf(unidadAprendizajeUi);
        if(position!=-1){
            vlst_unidadAprendizaje.set(position, unidadAprendizajeUi);
            notifyItemChanged(position);
        }
    }


    // Provee una referencia a cada item dentro de una vista y acceder a ellos facilmente
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.rv_sesiones)
        RecyclerView rv_sesiones;
        @BindView(R.id.txt_tituloUnidad)
        TextView txt_tituloUnidad;
        @BindView(R.id.txt_ver_mas)
        ImageView txt_vermas;
        @BindView(R.id.img_utils)
        ImageView imgUtils;
        @BindView(R.id.txt_vacio)
        TextView txt_vacio;
        @BindView(R.id.view3)
        View view3;
        @BindView(R.id.aprendizaje)
        ImageView aprendizajeFrames;
        @BindView(R.id.card_view)
        CardView cardView;

        private UnidadAprendizajeUi unidadAprendizaje;
        private List<Object> sesionAprendizajeList=new ArrayList<>();
        private UnidadesListener unidadesListener;
        private int vint_backgroudColor;
        private int programaEducativoId;

        // Cada uno de los elementos de mi vista
        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            imgUtils.setOnClickListener(this);
            aprendizajeFrames.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.card_view:
                    onClickVerMas();
                    break;
                case R.id.img_utils:
                    initOpcionesRubroFormula(view);
                    break;
                case R.id.aprendizaje:
                    unidadesListener.onClickAprendizaje(unidadAprendizaje);
                    break;
            }

        }

        private void onClickVerMas() {
            unidadesListener.onClickVerMas(unidadAprendizaje);
        }

        public void initOpcionesRubroFormula(View view) {
            DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(itemView.getContext(), view);
            DroppyMenuPopup droppyMenu = droppyBuilder
                    //.fromMenu(R.menu.droppy)
                    .addMenuItem(new DroppyMenuItem("Importar"))
                    .triggerOnAnchorClick(false)
                    .setOnClick(new DroppyClickCallbackInterface() {
                        @Override
                        public void call(View v, int positionMenu) {
                            switch (positionMenu){
                                case 0:
                                    Log.d(TAG, "IMPORTAR " + unidadAprendizaje.getTitulo());
                                    onSelectActualizar(unidadAprendizaje);
                                    break;
                            }
                            Log.d("positionMenu:", String.valueOf(positionMenu));

                        }
                    })
                    .setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback() {
                        @Override
                        public void call() {

                        }
                    })
                    .setPopupAnimation(new DroppyFadeInAnimation())
                    .setXOffset(5)
                    .setYOffset(5)
                    .build();
            droppyMenu.show();
        }

        private void onSelectActualizar(UnidadAprendizajeUi unidadAprendizaje) {
            BEVariables beVariables = new BEVariables();
            beVariables.setUnidadAprendizajeId(unidadAprendizaje.getUnidadAprendizajeId());
            ImportarActivity.launchImportarActivity(itemView.getContext(), TipoImportacion.UNIDAD ,beVariables );
        }

        public void bind(UnidadAprendizajeUi unidadAprendizaje, int vint_backgroudColor,int programaEducativoId, UnidadesListener unidadesListener) {
            this.unidadAprendizaje = unidadAprendizaje;
            this.unidadesListener = unidadesListener;
            this.vint_backgroudColor = vint_backgroudColor;
            this.programaEducativoId = programaEducativoId;

            String titulo = "Unidad "+unidadAprendizaje.getNroUnidad()+": "+unidadAprendizaje.getTitulo();
            txt_tituloUnidad.setText(titulo);
            try {
                view3.setBackgroundColor(Color.parseColor(unidadAprendizaje.getColor()));
                txt_tituloUnidad.setTextColor(Color.parseColor(unidadAprendizaje.getColor()));
            }catch (Exception e){
                e.printStackTrace();
            }


           if (unidadAprendizaje.getObjectListSesiones().size() <= 0){
                txt_vermas.setVisibility(View.GONE);
                txt_vacio.setVisibility(View.VISIBLE);
                txt_vacio.setText("En esta Unidad no hay sesiones por el momento");
            }else{
               txt_vacio.setVisibility(View.GONE);
               txt_vermas.setVisibility(View.VISIBLE);
           }


           if(unidadAprendizaje.isVisibleVerMas()){
               cardView.setOnClickListener(this);
               txt_vermas.setVisibility(View.VISIBLE);
           }else {
               cardView.setOnClickListener(null);
               txt_vermas.setVisibility(View.GONE);
           }

           if(!unidadAprendizaje.isVisibleVerMas()){
               setListColumanas();
               //setViewMore();
           }else if(unidadAprendizaje.isToogle()){
                setListColumanas();
                setViewLess();
           }else {
                setListHorizontal();
                setViewMore();
           }

        }

        private void setListHorizontal(){
            Log.d(TAG, "setListHorizontal");
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL, false);
            rv_sesiones.setLayoutManager(linearLayoutManager);
            rv_sesiones.setNestedScrollingEnabled(false);
            rv_sesiones.setHasFixedSize(false);
            rv_sesiones.setAdapter(new AdapterSesiones_v2(unidadAprendizaje.getObjectListSesiones(), unidadesListener,vint_backgroudColor, programaEducativoId));
        }

        private void setViewMore(){
            try{
                Drawable mDrawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.view);
                mDrawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor(unidadAprendizaje.getColor()), PorterDuff.Mode.SRC_IN));
                txt_vermas.setBackground(mDrawable);
                txt_vermas.setRotation(0);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        private void setListColumanas(){
            Log.d(TAG, "setListColumanas");
            AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(itemView.getContext(), OrientationHelper.VERTICAL, false);
            SesionColumnCountProvider  columnCountProvider = new SesionColumnCountProvider(itemView.getContext());
            autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
            rv_sesiones.setLayoutManager(autoColumnGridLayoutManager);
            rv_sesiones.setAdapter(new AdapterSesionesLandscape(unidadAprendizaje.getObjectListSesiones(), unidadesListener,vint_backgroudColor, programaEducativoId));

        }
        private void setViewLess(){
            try{
                Drawable mDrawable = ContextCompat.getDrawable( itemView.getContext() ,R.drawable.view);
                mDrawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor(unidadAprendizaje.getColor()), PorterDuff.Mode.SRC_IN));
                txt_vermas.setBackground(mDrawable);
                txt_vermas.setRotation(180);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    // Constructor
    public AdapterUnidades(List<UnidadAprendizajeUi> unidadSesionArrayList, UnidadesListener listener, int backgroudColor) {
        this.vlst_unidadAprendizaje = unidadSesionArrayList;
        this.mListener = listener;
        this.vint_backgroudColor = backgroudColor;
    }

    // Create new views (invoked by the layout managxer)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflo la vista (vista padre)
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_unidades, parent, false);
        // creo el grupo de vistas
        return new ViewHolder(v);
    }

    // Reemplaza en contenido de la vista
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
       UnidadAprendizajeUi  unidadAprendizaje = vlst_unidadAprendizaje.get(position);
        viewHolder.bind(unidadAprendizaje, vint_backgroudColor, programaEducativoId, mListener);

    }


    // Retorna el tamano de nuestra data
    @Override
    public int getItemCount() {
        return vlst_unidadAprendizaje.size();
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
    }



    public interface UnidadesListener{
        void onClickSesionListener(View view, SesionAprendizajeUi vint_SesionAprendizajeId);
        void onChangeSesionListener();
        void SaveSesionAprendizaje(SesionAprendizajeUi unidadAprendizajeUi);
        void onClickAprendizaje(UnidadAprendizajeUi unidadId);
        void onClickVerMas(UnidadAprendizajeUi unidadAprendizaje);
    }
    public void setUnidadesList(List<UnidadAprendizajeUi> itemUnidadesList, int programaEducativoId) {
        this.vlst_unidadAprendizaje.clear();
        this.vlst_unidadAprendizaje.addAll(itemUnidadesList);
        this.programaEducativoId = programaEducativoId;
        notifyDataSetChanged();
    }


    public void clearUnidades() {
        this.vlst_unidadAprendizaje.clear();
        notifyDataSetChanged();
    }

}