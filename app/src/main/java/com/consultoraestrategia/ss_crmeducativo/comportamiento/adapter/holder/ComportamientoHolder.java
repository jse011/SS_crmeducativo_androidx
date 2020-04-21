package com.consultoraestrategia.ss_crmeducativo.comportamiento.adapter.holder;

import android.content.Context;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.adapter.TipoAdapter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.listener.ComportamientoListener;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class ComportamientoHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,RecyclerView.OnItemTouchListener {

    @BindView( R.id.nombre)
    TextView nombre;
    @BindView( R.id.apellido)
    TextView apellido;
    @BindView( R.id.img_picture)
    CircleImageView img_picture;
    @BindView( R.id.rc_tipos)
    RecyclerView rc_tipos;
    Context context;
    @BindView(R.id.cardView)
    CardView cardView;

    TipoAdapter tipoAdapter;
    ComportamientoListener listener;
    AlumnoUi alumnoUiseleted;
    private GestureDetector mGestureDetector;


    public ComportamientoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context=itemView.getContext();
        cardView.setOnClickListener(this);
        if(mGestureDetector==null)mGestureDetector = new GestureDetector(itemView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        rc_tipos.removeOnItemTouchListener(this);
        rc_tipos.addOnItemTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardView:
                listener.onclick(alumnoUiseleted);
                break;
        }

    }

    public void bind(AlumnoUi alumnoUi, ComportamientoListener listener){
        this.alumnoUiseleted=alumnoUi;
        this.listener=listener;
        nombre.setText(alumnoUi.getNombre());
        apellido.setText(alumnoUi.getLastName());
        String url=alumnoUi.getUrlProfile();
        if (!TextUtils.isEmpty(url)) {
            Glide.with(itemView.getContext())
                    .load(url)
                    .apply(Utils.getGlideRequestOptions())
                    .into(img_picture);
        }
        initAdapter();
        tipoAdapter.setTipoUiList(alumnoUi.getTipoUiList());
    }

   private void initAdapter() {
       GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        rc_tipos.setLayoutManager(gridLayoutManager);
        tipoAdapter = new TipoAdapter(new ArrayList<TipoUi>());
        rc_tipos.setAdapter(tipoAdapter);
       rc_tipos.setNestedScrollingEnabled(false);
       rc_tipos.setHasFixedSize(false);
       rc_tipos.setClickable(false);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent motionEvent) {
        try {
            View child = rc_tipos.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                int position = rc_tipos.getChildAdapterPosition(child);
                listener.onclick(alumnoUiseleted);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
