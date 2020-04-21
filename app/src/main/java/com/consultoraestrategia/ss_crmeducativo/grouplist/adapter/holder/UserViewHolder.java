package com.consultoraestrategia.ss_crmeducativo.grouplist.adapter.holder;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.User;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public class UserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.root)
    ConstraintLayout rootLayout;
    @BindView(R.id.imgPicture)
    CircleImageView imgPicture;
    @BindView(R.id.txtItemName)
    TextView txtName;
    @BindView(R.id.imgDelete)
    ImageView imgDelete;

    public UserViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }


    public void bind(User user) {
        txtName.setText(user.getName());
        Glide.with(itemView.getContext())
                .load(user.getUrlPicture())
                .apply(Utils.getGlideRequestOptions())
                .into(imgPicture);
    }
}
