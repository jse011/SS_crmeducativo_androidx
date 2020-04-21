package com.consultoraestrategia.ss_crmeducativo.createTeam.adapter.holder;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.createTeam.listener.PersonListener;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by @stevecampos on 19/09/2017.
 */

public class MemberViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imgPicture)
    CircleImageView imgPicture;
    @BindView(R.id.txtItemName)
    TextView txtName;
    @BindView(R.id.imgDelete)
    ImageView imgDelete;
    @BindView(R.id.root)
    ConstraintLayout root;
    public MemberViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(final Person person, Context context, final PersonListener listener) {
        if(person==null)return;
        txtName.setText(person.getName());
        Glide.with(context)
                .load(person.getUrlPicture())
                .apply(Utils.getGlideRequestOptions())
                .into(imgPicture);
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person!=null)listener.onPersonUnselected(person);
            }
        });
    }
}
