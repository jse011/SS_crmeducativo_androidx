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

public class ContacsHolder extends RecyclerView.ViewHolder {

    CircleImageView image;
    TextView name;
    ImageView check;
    ConstraintLayout root;
    TextView txtDescripcion;

    public ContacsHolder(@NonNull View itemView) {
        super(itemView);

        image= (CircleImageView)itemView.findViewById(R.id.image_contact);
        name= (TextView)itemView.findViewById(R.id.name);
        check= (ImageView)itemView.findViewById(R.id.check);
        root=(ConstraintLayout)itemView.findViewById(R.id.root);
        txtDescripcion = (TextView)itemView.findViewById(R.id.txt_descripcion);

    }
    public void bind(final ContactUi contactUi, final ChatListener chatListener){

        name.setText(contactUi.getName());
        if(contactUi.isEnable())check.setVisibility(View.VISIBLE);
        else check.setVisibility(View.GONE);
        String url= contactUi.getPhoto();
        Glide.with(itemView.getContext())
                .load(url)
                .apply(Utils.getGlideRequestOptions())
                .into(image);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatListener.clickChat(contactUi);
            }
        });
        txtDescripcion.setText(contactUi.getDescripcion());
    }
}
