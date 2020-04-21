package com.consultoraestrategia.ss_crmeducativo.chats.groups.searchGroups.adapters;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.CategoryUi;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;

public class CategoryGroupHolder extends RecyclerView.ViewHolder {


    ImageView image;
    TextView name;
    ConstraintLayout root;
    public CategoryGroupHolder(@NonNull View itemView) {
        super(itemView);
        name=(TextView)itemView.findViewById(R.id.name);
        image=(ImageView)itemView.findViewById(R.id.image);
        root=(ConstraintLayout)itemView.findViewById(R.id.root);
    }

    public void bind(final CategoryUi categoryUi, final ChatListener chatListener)
    {
        name.setText(categoryUi.getName());
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatListener.clickChat(categoryUi);
            }
        });

        int resource=R.drawable.icon_file_doc;
        switch (  categoryUi.getType()){
          case ACADEMIC:
              resource=R.drawable.academic;
              break;
            case COURSE:
                resource=R.drawable.courses;
                break;
            case PERIOD:
                resource=R.drawable.period;
                break;
            case SECTION:
                resource=R.drawable.section;
                break;
            case TEAM:
                resource=R.drawable.course;
                break;

        }
        image.setImageResource(resource);


    }

}
