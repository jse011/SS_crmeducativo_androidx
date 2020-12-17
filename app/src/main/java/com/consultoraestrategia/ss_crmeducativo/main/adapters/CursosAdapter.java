package com.consultoraestrategia.ss_crmeducativo.main.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.lib.ImageLoader;
import com.consultoraestrategia.ss_crmeducativo.main.adapters.holders.CursosViewHolder;
import com.consultoraestrategia.ss_crmeducativo.main.adapters.holders.CursosViewHolderV2;
import com.consultoraestrategia.ss_crmeducativo.main.adapters.holders.GradoHolder;
import com.consultoraestrategia.ss_crmeducativo.main.entities.CursosUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.GradoUi;
import com.consultoraestrategia.ss_crmeducativo.main.listeners.CursosListener;

import java.util.List;


/**
 * Created by irvinmarin on 27/02/2017.
 */

public class CursosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG =CursosAdapter.class.getSimpleName();
    private static final int TIPO_CURSO=0, TIPO_GRADO=1;
    private RecyclerViewAnimator mAnimator;

    private List<Object> objectList;

    private ImageLoader imageLoader;
    CursosListener listener;

    public CursosAdapter(List<Object> objectList, CursosListener listener, ImageLoader imageLoader) {
        this.objectList = objectList;
        this.listener = listener;
        this.imageLoader = imageLoader;
    }

    @Override
    public int getItemViewType(int position) {
        Object object= objectList.get(position);
        if(object instanceof  CursosUI )return TIPO_CURSO;
        else return TIPO_GRADO;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType){
            case TIPO_GRADO:
                v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grados, parent, false);
                return new GradoHolder(v);
            default:
                v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cursos_const, parent, false);
                if(mAnimator!=null)mAnimator.onCreateViewHolder(v);
                return new CursosViewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object  object = objectList.get(position);
        if(holder.getItemViewType() == TIPO_GRADO){
            Log.d(TAG , "onBindViewHolder GRADOUI");
            GradoHolder gradoHolder= (GradoHolder)holder;
            if(object instanceof GradoUi )gradoHolder.bind((GradoUi)object, listener, position);
        }else{
            Log.d(TAG , "onBindViewHolder cursouI");
            CursosViewHolder cursosViewHolder= (CursosViewHolder)holder;
            if(object instanceof CursosUI)cursosViewHolder.bind((CursosUI) object, listener, imageLoader);
            if(mAnimator!=null)mAnimator.onBindViewHolder(holder.itemView, position);
        }

    }


    @Override
    public int getItemCount() {
        return objectList.size();
    }

    public void setCursosUIList(List<CursosUI> cursosUIListo) {
        this.objectList.clear();
        this.objectList.addAll(cursosUIListo);
        notifyDataSetChanged();
    }

    public void enabledCLick(CursosUI cursosUI) {
        int position = objectList.indexOf(cursosUI);
        if (position != -1) {
            notifyItemChanged(position);
        }

    }

    public void setmAnimator(RecyclerViewAnimator mAnimator) {
        this.mAnimator = mAnimator;
    }
}
