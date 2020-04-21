package com.consultoraestrategia.ss_crmeducativo.comentarios.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comentarios.adapter.ComentarioListener;
import com.consultoraestrategia.ss_crmeducativo.comentarios.entidades.ComentarioUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ComentariosHolderEmisor extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static String TAG = ComentarioHolderReceptor.class.getSimpleName();

    @BindView(R.id.com_imgProfileUser)
    CircleImageView imgProfileUser;
    @BindView(R.id.com_nombre)
    TextView nombre;
    @BindView(R.id.com_comentario)
    TextView comentario;
    @BindView(R.id.com_fecha)
    TextView fecha;

    ComentarioListener listener;
    ComentarioUi comentarioUi;

    public ComentariosHolderEmisor(View itemView) {

        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bind(ComentarioUi comentarioUi, int position, ComentarioListener listener){
        this.comentarioUi= comentarioUi;
        this.listener= listener;
        nombre.setText(comentarioUi.getNombre());
        comentario.setText(comentarioUi.getContenido());
        showimagen(comentarioUi.getUrl());
        String fe = Utils.getFechaHora(comentarioUi.getFechaCreacion());
        String str = fe.replace("AM", "am").replace("PM","pm");
        fecha.setText(str);

        //Log.d(ComentariosLocalDataSource.class.getSimpleName(), "receptor"+ comentarioUi.toString());
    }
    @Override
    public void onClick(View v) {

    }

    public void showimagen(String url){
        try{
            Glide.with(itemView.getContext())
                    .load(url)
                    .apply(Utils.getGlideRequestOptions(R.drawable.ic_account_circle))
                    .into(imgProfileUser);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
