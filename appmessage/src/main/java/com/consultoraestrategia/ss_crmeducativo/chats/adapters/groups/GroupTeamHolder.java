package com.consultoraestrategia.ss_crmeducativo.chats.adapters.groups;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupTeamHolder extends RecyclerView.ViewHolder {

    CircleImageView circleImageView;
    TextView name;
    ConstraintLayout root;


    public GroupTeamHolder(@NonNull View itemView) {
        super(itemView);
        circleImageView=(CircleImageView)itemView.findViewById(R.id.image);
        name=(TextView)itemView.findViewById(R.id.name);
        root=(ConstraintLayout)itemView.findViewById(R.id.root);
    }
    public void bind(final GroupUi groupUi,final ChatListener chatListener){
        name.setText(groupUi.getName());
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatListener.clickChat(groupUi);
            }
        });
    }
}
