package com.consultoraestrategia.ss_crmeducativo.grouplist.adapter.holder;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by @stevecampos on 15/09/2017.
 */

public class GroupTeamViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = GroupTeamViewHolder.class.getSimpleName();
    @BindView(R.id.txtPicture)
    TextView txtPicture;
    @BindView(R.id.imgPicture)
    ImageView imgPicture;
    @BindView(R.id.txt_nombre)
    TextView txtNombre;
    @BindView(R.id.txt_cantidad)
    TextView txtCantidad;


    public GroupTeamViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }


    public void bind(Team team){
       //for(Group group: cursoGrupo.getGrupos())for(Team team: group.getTeams()) {
            Drawable circle = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_circle_unidades);
            int colorTexto = Color.WHITE;
            try {
                circle.mutate().setColorFilter(ContextCompat.getColor(itemView.getContext(), team.getColorFondo()), PorterDuff.Mode.SRC_ATOP);
                colorTexto = ContextCompat.getColor(itemView.getContext(), team.getColorTexto());
            } catch (Exception e) {
                e.printStackTrace();
            }
            txtPicture.setBackground(circle);
            txtPicture.setTextColor(colorTexto);
            txtPicture.setText(team.getEtiqueta());
            txtNombre.setText(team.getName());
            String cantidad = "(" + team.getPersonList().size() + ")";
            if (team.getPersonList() != null) txtCantidad.setText(cantidad);

            if(!TextUtils.isEmpty(team.getUrlImage())){
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(itemView.getContext())
                        .load(team.getUrlImage())
                        .apply(options)
                        .into(imgPicture);
            }
       }
   // }


}
