package com.consultoraestrategia.ss_crmeducativo.chats.adapters.groups;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;

public class GroupHolder extends RecyclerView.ViewHolder {

    TextView sectionName;
    TextView name;
    ImageView circle;
    ConstraintLayout root;

    public GroupHolder(@NonNull View itemView) {
        super(itemView);
        sectionName=(TextView)itemView.findViewById(R.id.section);
        name=(TextView)itemView.findViewById(R.id.name);
        circle=(ImageView)itemView.findViewById(R.id.circle);
        root=(ConstraintLayout)itemView.findViewById(R.id.root);
    }

    public void bind(final GroupUi groupUi,final ChatListener chatListener){
        sectionName.setText(groupUi.getSection());
        Log.d(getClass().getSimpleName(), groupUi.getSection()+ ", "+ groupUi.getYear()+", "+ groupUi.getProgramEducate());
        name.setText(groupUi.getSection()+ ", "+ groupUi.getYear()+", "+ groupUi.getProgramEducate());
        sectionName.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.light_blank));
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatListener.clickChat(groupUi);
            }
        });

    }
}
