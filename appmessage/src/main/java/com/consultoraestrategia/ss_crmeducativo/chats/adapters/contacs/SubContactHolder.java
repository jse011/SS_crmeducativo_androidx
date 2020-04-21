package com.consultoraestrategia.ss_crmeducativo.chats.adapters.contacs;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ContactUi;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import de.hdodenhof.circleimageview.CircleImageView;

public class SubContactHolder extends RecyclerView.ViewHolder  {

    private CircleImageView image;
    TextView name;
    TextView relation;
    ImageView check;
    ConstraintLayout root;

    public SubContactHolder(@NonNull View itemView) {
        super(itemView);
        image= (CircleImageView)itemView.findViewById(R.id.image_contact);
        name= (TextView)itemView.findViewById(R.id.name);
        relation= (TextView)itemView.findViewById(R.id.relation);
        check= (ImageView)itemView.findViewById(R.id.check);
        root=(ConstraintLayout)itemView.findViewById(R.id.root);
    }

    public void bind(final ContactUi contactUi,final  ChatListener chatListener){
        name.setText(contactUi.getName());
        relation.setText(contactUi.getRol());
        if(contactUi.isEnable())check.setVisibility(View.VISIBLE);
        else check.setVisibility(View.GONE);
        Glide.with(itemView.getContext())
                .load(contactUi.getPhoto())
                .apply(Utils.getGlideRequestOptions())
                .into(image);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatListener.clickChat(contactUi);
            }
        });
    }
}
